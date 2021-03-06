/* --------------------------------------------------------------------
 *  Inugami  
 * --------------------------------------------------------------------
 * 
 * This program is free software: you can redistribute it and/or modify  
 * it under the terms of the GNU General Public License as published by  
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU 
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License 
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.inugami.api.processors.fifo;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.inugami.api.loggers.Loggers;
import org.inugami.api.providers.concurrent.LifecycleBootstrap;

/**
 * FifoProcessor
 * 
 * @author patrick_guillerm
 * @since 26 sept. 2017
 */
public class FifoProcessorService<Q, I> implements LifecycleBootstrap {
    
    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    private transient volatile BlockingQueue<Q> dataToProcess    = new LinkedBlockingQueue<Q>();
    
    private final static long                   DEFAULT_INTERVAL = 500;
    
    private final transient FifoMapper<Q, I>    mapper;
    
    private final transient FifoProcessor<Q>    processor;
    
    private final ScheduledExecutorService      executor;
    
    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================
    public FifoProcessorService(final FifoMapper<Q, I> mapper, final FifoProcessor<Q> processor,
                                final String threadName) {
        this(mapper, processor, DEFAULT_INTERVAL, threadName);
    }
    
    public FifoProcessorService(final FifoMapper<Q, I> mapper, final FifoProcessor<Q> processor, final long timeToSleep,
                                final String threadName) {
        this(mapper, processor, null, timeToSleep, threadName);
    }
    
    public FifoProcessorService(final FifoProcessorGlobaleProcessor<Q> globaleProcessor, final long timeToSleep,
                                final String threadName) {
        this(null, null, globaleProcessor, timeToSleep, threadName);
    }
    
    public FifoProcessorService(final FifoMapper<Q, I> mapper, final FifoProcessorGlobaleProcessor<Q> globaleProcessor,
                                final long timeToSleep, final String threadName) {
        this(mapper, null, globaleProcessor, timeToSleep, threadName);
    }
    
    protected FifoProcessorService(final FifoMapper<Q, I> mapper, final FifoProcessor<Q> processor,
                                   final FifoProcessorGlobaleProcessor<Q> globaleProcessor, final long interval,
                                   final String threadName) {
        super();
        //@formatter:off
        this.mapper              = mapper      != null ? mapper      : (input) -> (Q) input  ;
        this.processor           = processor   != null ? processor   : (data) -> {}          ;
        final long currentInterval  = interval >= 0    ? interval : DEFAULT_INTERVAL ;
        final String currentThreadName          = threadName  != null ? threadName  : String.join("_", "FifoProcessorService", String.valueOf(System.identityHashCode(this)));
        //@formatter:on
        executor = Executors.newSingleThreadScheduledExecutor((runable) -> new Thread(runable, currentThreadName));
        executor.scheduleAtFixedRate(new FifoProcessorThread(globaleProcessor), 0, currentInterval,
                                     TimeUnit.MILLISECONDS);
    }
    
    // =========================================================================
    // METHODS INPUT
    // =========================================================================
    public void add(final I input) {
        if (input != null) {
            final Q data = mapper.convert(input);
            addData(data);
        }
    }
    
    private synchronized void addData(final Q data) {
        dataToProcess.add(data);
    }
    
    // =========================================================================
    // THREAD CONTROL
    // =========================================================================
    @Override
    public void start() {
        
    }
    
    @Override
    public void shutdown() {
        executor.shutdown();
        if (!executor.isShutdown()) {
            try {
                executor.awaitTermination(0, TimeUnit.MILLISECONDS);
            }
            catch (final InterruptedException e) {
                Loggers.DEBUG.error(e.getMessage(), e);
            }
        }
    }
    
    // =========================================================================
    // THREAD
    // =========================================================================
    private class FifoProcessorThread implements Runnable {
        private final FifoProcessorGlobaleProcessor<Q> globaleProcessor;
        
        public FifoProcessorThread(final FifoProcessorGlobaleProcessor<Q> globaleProcessor) {
            this.globaleProcessor = globaleProcessor;
        }
        
        @Override
        public void run() {
            if (globaleProcessor == null) {
                processData(dataToProcess);
            }
            else {
                globaleProcessor.process(dataToProcess);
            }
        }
        
        private void processData(final BlockingQueue<Q> dataToProcess) {
            Q data;
            do {
                data = null;
                if (!dataToProcess.isEmpty()) {
                    data = dataToProcess.poll();
                }
                
                if (data != null) {
                    processor.process(data);
                }
            }
            while (data != null);
        }
        
    }
}
