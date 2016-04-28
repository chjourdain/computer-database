$(document).ready(function() {
    jQuery.validator.addMethod("greaterThan", function(value, element, param) {
        if (value == null) return true;
        if (value == "") return true;
        if ($(param).val() == "") return true;
        return new Date(value) >= new Date($(param).val());
    });

    jQuery.validator.addMethod("isDate", function(value, element, param) {
        if (value == null) return true;
        if (value == "") return true;
        if (value == "0000-00-00") return false;
        var regExFr = "^^(0[1-9]{1}|[1-2]{1}[0-9]{1}|3[0-1]{1})-(1[0-2]{1}|0[1-9]{1})-(19[7-9]{1}[0-9]{1}|20[0-2]{1}[0-9]{1}|203[0-7]{1})$|^$";
        var regExEn ="^^(1[0-2]{1}|0[1-9]{1})-(0[1-9]{1}|[1-2]{1}[0-9]{1}|3[0-1]{1})-(19[7-9]{1}[0-9]{1}|20[0-2]{1}[0-9]{1}|203[0-7]{1})$|^$";
        return (value.match(regExEn) != null || value.match(regExFr) != null);
    });
    
    jQuery.validator.addMethod("allSpace", function(value, element, param) {
    	return value.trim().length != 0;
    });


    $("#form").validate({
        rules: {
            name: {
                required : true,
                allSpace: true
            },
            introduced: {
                required: false,
                isDate: true,
            },
            discontinued: {
                required: false,
                isDate: true,
                greaterThan: "#introduced"
            }
        },
        messages: {
            name: {
            	required: translated['required'],
            	allSpace: translated['notblank']
            },
            introduced: {
                isDate: translated['isdate'],
            },
            discontinued: {
                isDate: translated['isdate'],
                greaterThan: translated['greaterthan'],
                notBefore70: translated['before70']
            }
        }
    });
});