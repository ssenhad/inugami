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
package org.inugami.monitoring.core.interceptors;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.inugami.api.exceptions.FatalException;

/**
 * ResponseWrapper
 * 
 * @author patrickguillerm
 * @since Jan 8, 2019
 */
final class ResponseWrapper implements ServletResponse, HttpServletResponse {
    
    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    private final HttpServletResponse response;
    
    private final OutputWriterWrapper outputWrapper;
    
    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================
    ResponseWrapper(final ServletResponse response) {
        this.response = (HttpServletResponse) response;
        
        try {
            this.outputWrapper = new OutputWriterWrapper(response.getOutputStream());
        }
        catch (final IOException e) {
            throw new FatalException(e.getMessage(), e);
        }
    }
    
    // =========================================================================
    // METHODES
    // =========================================================================
    String getData() {
        return outputWrapper.getData();
    }
    
    // =========================================================================
    // OVERRIDES
    // =========================================================================
    @Override
    public String getCharacterEncoding() {
        return response.getCharacterEncoding();
    }
    
    @Override
    public String getContentType() {
        return response.getContentType();
    }
    
    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return outputWrapper;
    }
    
    @Override
    public PrintWriter getWriter() throws IOException {
        return response.getWriter();
    }
    
    @Override
    public void setCharacterEncoding(final String charset) {
        response.setCharacterEncoding(charset);
    }
    
    @Override
    public void setContentLength(final int len) {
        response.setContentLength(len);
    }
    
    @Override
    public void setContentLengthLong(final long len) {
        response.setContentLengthLong(len);
    }
    
    @Override
    public void setContentType(final String type) {
        response.setContentType(type);
    }
    
    @Override
    public void setBufferSize(final int size) {
        response.setBufferSize(size);
    }
    
    @Override
    public int getBufferSize() {
        return response.getBufferSize();
    }
    
    @Override
    public void flushBuffer() throws IOException {
        response.flushBuffer();
    }
    
    @Override
    public void resetBuffer() {
        response.resetBuffer();
    }
    
    @Override
    public boolean isCommitted() {
        return response.isCommitted();
    }
    
    @Override
    public void reset() {
        response.reset();
    }
    
    @Override
    public void setLocale(final Locale loc) {
        response.setLocale(loc);
    }
    
    @Override
    public Locale getLocale() {
        return response.getLocale();
    }
    
    @Override
    public void addCookie(final Cookie cookie) {
        response.addCookie(cookie);
    }
    
    @Override
    public boolean containsHeader(final String name) {
        return response.containsHeader(name);
    }
    
    @Override
    public String encodeURL(final String url) {
        return response.encodeURL(url);
    }
    
    @Override
    public String encodeRedirectURL(final String url) {
        return response.encodeRedirectURL(url);
    }
    
    @Deprecated
    @Override
    public String encodeUrl(final String url) {
        return response.encodeUrl(url);
    }
    
    @Deprecated
    @Override
    public String encodeRedirectUrl(final String url) {
        return response.encodeRedirectUrl(url);
    }
    
    @Override
    public void sendError(final int sc, final String msg) throws IOException {
        response.sendError(sc, msg);
    }
    
    @Override
    public void sendError(final int sc) throws IOException {
        response.sendError(sc);
    }
    
    @Override
    public void sendRedirect(final String location) throws IOException {
        response.sendRedirect(location);
    }
    
    @Override
    public void setDateHeader(final String name, final long date) {
        response.setDateHeader(name, date);
        
    }
    
    @Override
    public void addDateHeader(final String name, final long date) {
        response.addDateHeader(name, date);
        
    }
    
    @Override
    public void setHeader(final String name, final String value) {
        response.setHeader(name, value);
        
    }
    
    @Override
    public void addHeader(final String name, final String value) {
        response.setHeader(name, value);
    }
    
    @Override
    public void setIntHeader(final String name, final int value) {
        response.setIntHeader(name, value);
    }
    
    @Override
    public void addIntHeader(final String name, final int value) {
        response.addIntHeader(name, value);
        
    }
    
    @Override
    public void setStatus(final int sc) {
        response.setStatus(sc);
    }
    
    @Deprecated
    @Override
    public void setStatus(final int sc, final String sm) {
        response.setStatus(sc, sm);
    }
    
    @Override
    public int getStatus() {
        return response.getStatus();
    }
    
    @Override
    public String getHeader(final String name) {
        return response.getHeader(name);
    }
    
    @Override
    public Collection<String> getHeaders(final String name) {
        return response.getHeaders(name);
    }
    
    @Override
    public Collection<String> getHeaderNames() {
        return response.getHeaderNames();
    }
    
}
