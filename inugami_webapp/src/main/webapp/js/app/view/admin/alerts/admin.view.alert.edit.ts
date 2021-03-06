import {Component,Inject,OnInit,Input,
        Output,forwardRef,EventEmitter}                     from '@angular/core';
import {NG_VALUE_ACCESSOR,ControlValueAccessor}             from '@angular/forms';

import {AlertsCrudServices}                                 from './../../../services/http/alerts.crud.services';
import {AlertEntity}                                        from './../../../models/alert.entity';
import {InputBloc}                                          from './../../../components/forms/input.bloc';
import {Msg}                                                from './../../../components/msg/msg';

export const ADMIN_VIEW_ALERT_EDIT_VALUE_ACCESSOR: any = {
    provide: NG_VALUE_ACCESSOR,
    useExisting: forwardRef(() => AdminViewAlertEdit),
    multi: true
    };

    

@Component({
  selector      : 'admin-view-alert-edit',
  templateUrl   : 'js/app/view/admin/alerts/admin.view.alert.edit.html',
  providers     : [ADMIN_VIEW_ALERT_EDIT_VALUE_ACCESSOR],
  directives    : [InputBloc,Msg]
})
export class AdminViewAlertEdit implements AfterViewInit{

    /**************************************************************************
    * ATTRIBUTES
    **************************************************************************/
    @Input() styleClass                 : string;
    @Input() edit                       : boolean = false;

    private detailData                  : string;
    private validators                  : any = org.inugami.validators;
    private innerValue                  : AlertEntity; 
    private isNotEdit                   : boolean;

    @Output() onClose                   : EventEmitter<any> = new EventEmitter();
    @Output() onError                   : EventEmitter<any> = new EventEmitter();
    @Output() onSuccess                 : EventEmitter<any> = new EventEmitter();
    @Output() onCleanMessage            : EventEmitter<any> = new EventEmitter();


    /**************************************************************************
    * CONSTRUCTOR
    **************************************************************************/
    constructor(private alertsCrudServices : AlertsCrudServices) {
        this.initValue();
    }
    ngAfterContentInit(){
        this.isNotEdit = !this.edit;
    }

    /**************************************************************************
    * INIT
    **************************************************************************/
    private initValue(){
        if(isNull(this.innerValue)){
            this.innerValue = new AlertEntity();
        }
        if(isNull(this.innerValue.duration)){
            this.innerValue.duration = 60;
        }
        if(isNull(this.innerValue.level)){
            this.innerValue.level= "info";
        }
        if(isNull(this.innerValue.channel)){
            this.innerValue.channel = "@all";
        }
        
        if(isNull(this.innerValue.data)){
            this.detailData = null;
        }else{
            let strJson = this.innerValue.data.trim();
            if(strJson.startsWith('"{')){
                strJson = strJson.substring(1,strJson.length);
            }
            if(strJson.endsWith('}"')){
                strJson = strJson.substring(0,strJson.length-1);
            }
            this.detailData =  strJson.split("\\u0022").join("\""); 
            
            
        }
    }
    

    /**************************************************************************
    * ACTIONS
    **************************************************************************/
    onSuccessSave(){
        let msg= org.inugami.formatters.messageValue("action.success");
        this.onSuccess.emit({"severity":'success', "summary":msg});
    }
    cleanMessage(){
        this.onCleanMessage.emit();
    }
    handlerError(error){
        let errorMsg = null;
        if(isNotNull(error.data) && isNotNull(error.data.errorCode)){
            errorMsg = org.inugami.formatters.messageValue(["dashboard.tv.error",error.data.errorCode].join("."))
        }
        if(isNull(errorMsg)){
            errorMsg = error.statusText;
        }

        this.onSuccess.emit({"severity":'error', "summary":errorMsg});
    }
    
    /*************************************************************************
    * ACTIONS
    **************************************************************************/
    saveAlert(){
        this.cleanMessage();
        let alerts = [this.innerValue];
        if(this.edit){
            this.alertsCrudServices.merge(alerts)
                                   .then((data)=>{
                                        this.onSuccessSave();
                                    })
                                    .catch((error)=>this.handlerError(error));
        }else{
            this.alertsCrudServices.save(alerts)
                                   .then((data)=>{
                                        this.onSuccessSave();
                                    })
                                    .catch((error)=>this.handlerError(error));
        }
    }


    saveAlertAndClose(){
        this.saveAlert();
        this. initValue();
    }

    close(){
        this.initValue();
        this.onClose.emit();
    }

 /*****************************************************************************
  * IMPLEMENTS ControlValueAccessor
  *****************************************************************************/
  writeValue(value: any) {
    this.innerValue = value;
    this.initValue();
  }
  registerOnChange(fn: any) {
    this.onChangeCallback = fn;
  }
  registerOnTouched(fn: any) {
    this.onTouchedCallback = fn;
  }
}
