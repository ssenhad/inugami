// :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
// CHECK API
// :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
org.inugami.checks = {
    /**
     * Allow to check if value is null
     * @param value
     * @returns true if value is null
     */
    isNull: function (value) {
        return (value === undefined || value === null);
    },
    /**
     * Allow to check if value isn't null
     * @param value
     * @returns true if value isn't null
     */
    isNotNull: function (value) {
        return !org.inugami.checks.isNull(value);
    },


    notEmpty: function (value) {
        return value !== undefined && value !== null && value.length > 0;
    },

    indexOf : function(value, list, functionEquals){
        var result = -1;
        if(org.inugami.checks.isNotNull(list)){
            var useFunction = org.inugami.checks.isNotNull(functionEquals);
            for(var i=list.length-1; i>=0;i--){

                var same = useFunction ? functionEquals(list[i],value) :list[i]===value;
                if(same){
                    result = i;
                    break;
                }
            }
        }
        return result;
    },

    contains : function(value, list, functionEquals){
        return org.inugami.checks.indexOf(value, list,functionEquals) != -1;
    },
    containsDefault : function(value, list){
        return org.inugami.checks.indexOf(value, list,function(ref,value){
            return ref===value;
        }) != -1;
    },

    isArray : function(value){
        var result = false;
        if(value!=null){
            result = ((typeof value) === 'array') || (value instanceof Array);
        }
        
        return result;
    }
    
};
// :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
// CHECK API SHORTCUT
// :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
function isNull(value){
    return org.inugami.checks.isNull(value);
}

function isNotNull(value){
    return org.inugami.checks.isNotNull(value);
}

function notEmpty(value){
    return org.inugami.checks.isNotNull(value);
}

function indexOf(value, list, functionEquals){
    return org.inugami.checks.indexOf(value, list, functionEquals);
}

function contains(value, list, functionEquals){
    var result = null;
    if(org.inugami.checks.isNull(functionEquals)){
        result =  org.inugami.checks.containsDefault(value, list);
    }else{
        result =  org.inugami.checks.contains(value, list, functionEquals);
    }
    return result;
}

function isArray(value){
    return org.inugami.checks.isArray(value);
}


// :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
// VALIDATOR API
// :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
org.inugami.validators = {
    notNull  : function(value){
        return isNotNull(value)?null:"javax.validation.constraints.NotNull.message";
    },
    isNumber : function(value){
        var result = null;
        if(isNotNull(value)){
            if(isNaN(value)){
                result = "org.inugami.validator.type.not.number";
            }
        }
        return result;
    },
    notNegativeNumber : function(value){
        var result = org.inugami.validators.isNumber(value);
        if(isNotNull(value) && isNull(result)){
            if(value<0){
                result = "javax.validation.constraints.PositiveOrZero.message";
            }
        }
        return result;
    },
    notEmptyString: function(value){
        var result = org.inugami.validators.notNull(value);
        if(isNull(result)){
            result  = (""+value).trim().length==0?"javax.validation.constraints.NotEmpty.message":null;
        }
        return result;
    }
}