// ANGULAR CORE MODULES --------------------------------------------------------
import {RouterModule,Routes}            from '@angular/router';
import {NgModule}                       from '@angular/core'
import {CommonModule}                   from '@angular/common'
import {BrowserModule}                  from '@angular/platform-browser'
import {HttpModule}                     from '@angular/http'
import {ReactiveFormsModule}            from '@angular/forms'
import {FormsModule}                    from '@angular/forms'
import {BrowserAnimationsModule}        from '@angular/platform-browser/animations';
import {PrimeNgModule}                  from './primeng.module';

// SCOPES ----------------------------------------------------------------------
import {SessionScope}                    from './scopes/session.scope';

// COMPONENTS ------------------------------------------------------------------
import {PluginInfos}                    from './components/plugin_infos/plugin.info';
import {PluginInfosBehaviors}           from './components/plugin_infos_behaviors/plugin.infos.behaviors';
import {AdminBloc}                      from './components/admin_bloc/admin.bloc';
import {EventInfos}                     from './components/event_infos/event.infos';
import {SimpleEventInfos}               from './components/simple_event_infos/simple.event.infos';

import {Msg}                            from './components/msg/msg';
import {Bloc}                           from './components/display/bloc/bloc';
import {Image}                          from './components/display/image/image';
import {SvgComponent}                   from './components/charts/svg_component/svg.component';
import {SystemNotification}             from './components/system_notification/system.notification';
import {AlertingTab}                    from './components/alerting_tab/alerting.tab';
import {InputBloc}                      from './components/forms/input.bloc';
import {Carousel}                       from './components/carousel/carousel';
import {PropertiesEditor}               from './components/properties_editor/properties.editor';

import {BarChart}                       from './components/charts/bar_chart/bar.chart';
import {BubbleLegend}                   from './components/charts/bubble_legend/bubble_legend';
import {CurveChart}                     from './components/charts/curve_chart/curve.chart';
import {DoubleBubble}                   from './components/charts/double_bubble/double.bubble';
import {EvolutionValue}                 from './components/charts/evolution_value/evolution.value';
import {SimpleGraph}                    from './components/charts/simple_graph/simple_graph';
import {SimpleValue}                    from './components/charts/simple_value/simple.value';
import {TimeHandler}                    from './components/charts/time_handler/time.handler';
import {Value}                          from './components/charts/value/value';
import {ValueBloc}                      from './components/charts/value_bloc/value.bloc';
import {ValueChart}                     from './components/charts/value_chart/value.chart';

// VIEWS -----------------------------------------------------------------------
import {HomeView}                       from './view/home.view';
import {HelpView}                       from './view/help.view';
import {AdminView}                      from './view/admin.view';
import {AdminViewAlerts}                from './view/admin/alerts/admin.view.alerts';
import {AdminViewAlertEdit}             from './view/admin/alerts/admin.view.alert.edit';
import {LoginView}                      from './view/login.view';


// SERVICES --------------------------------------------------------------------
import {PluginsService}                 from './services/plugins.service';
import {AdminService}                   from './services/admin.services';
import {SecurityServices}               from './services/security.services';
import {HeaderServices}                 from './services/header.services';
import {SoundServices}                  from './services/sound.services';
import {HttpServices}                   from './services/http/http.services';
import {GenericCrudServices}            from './services/http/generic.crud.services';
import {AlertsCrudServices}             from './services/http/alerts.crud.services';


// MODULE ----------------------------------------------------------------------
@NgModule({
  imports: [CommonModule, BrowserModule, ReactiveFormsModule,FormsModule, HttpModule,RouterModule,BrowserAnimationsModule,PrimeNgModule],
  declarations: [
    HomeView,
    HelpView,
    AdminView,
    LoginView,
    AdminBloc,
    PluginInfos,
    PluginInfosBehaviors,
    EventInfos,
    SimpleEventInfos,
    
    Bloc,
    Image,
    SvgComponent,
    
    SystemNotification,
    AlertingTab,
    Msg,
    AdminViewAlerts,
    AdminViewAlertEdit,
    InputBloc,
    Carousel,
    PropertiesEditor,

    BarChart,
    BubbleLegend,
    CurveChart,
    DoubleBubble,
    EvolutionValue,
    SimpleGraph,
    SimpleValue,
    TimeHandler,
    Value,
    ValueBloc,
    ValueChart

  ],
  entryComponents: [],
  exports: [
    Bloc,
    Image,
    SvgComponent,
    
    SystemNotification,
    AlertingTab,
    Msg,
    InputBloc,
    Carousel, 

    BarChart,
    BubbleLegend,
    CurveChart,
    DoubleBubble,
    EvolutionValue,
    SimpleGraph,
    SimpleValue,
    TimeHandler,
    Value,
    ValueBloc,
    ValueChart,
    PropertiesEditor
  ],
  providers: [
    PluginsService,
    AdminService,
    SessionScope,
    SecurityServices,
    HeaderServices,
    SoundServices,
    HttpServices,
    AlertsCrudServices
  ],
  entryComponents: [
    Image,
    BarChart,
    BubbleLegend,
    CurveChart,
    DoubleBubble,
    EvolutionValue,
    SimpleGraph,
    SimpleValue,
    TimeHandler,
    Value,
    ValueBloc,
    ValueChart,
    Carousel
  ],
  bootstrap: []
})
export class AppRootModule {
}


PLUGINS_COMPONENTS['image']           = Image;
PLUGINS_COMPONENTS['bar-chart']       = BarChart;
PLUGINS_COMPONENTS['bubble-legend']   = BubbleLegend;
PLUGINS_COMPONENTS['curve-chart']     = CurveChart;
PLUGINS_COMPONENTS['double-bubble']   = DoubleBubble;
PLUGINS_COMPONENTS['evolution-value'] = EvolutionValue;
PLUGINS_COMPONENTS['simple-graph']    = SimpleGraph;
PLUGINS_COMPONENTS['simple-value']    = SimpleValue;
PLUGINS_COMPONENTS['time-handler']    = TimeHandler;
PLUGINS_COMPONENTS['value']           = Value;
PLUGINS_COMPONENTS['value-bloc']      = ValueBloc;
PLUGINS_COMPONENTS['value-chart']     = ValueChart;
PLUGINS_COMPONENTS['carousel']        = Carousel;


const  AppRootModuleRoutesConfig: Routes = [
    { path: 'help' , component: HelpView },
    { path: 'admin', component: AdminView },
    { path: 'login', component: LoginView },
    { path: ''     , component: HomeView , pathMatch:'full' }
];
export const AppRootModuleRoutes = RouterModule.forRoot(AppRootModuleRoutesConfig);
