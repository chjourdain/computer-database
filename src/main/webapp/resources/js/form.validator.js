$(document).ready(function() {
    jQuery.validator.addMethod("greaterThan", function(value, element, param) {
        if (value == null) return true;
        if (value == "") return true;
        if ($(param).val() == "") return true;
        return new Date(value) >= new Date($(param).val());
    });

    jQuery.validator.addMethod("notBefore70", function(value, element, param) {
        if (value == null) return true;
        if (value == "") return true;
        return new Date(value) > new Date("1970-01-01");
    });

    jQuery.validator.addMethod("isDate", function(value, element, param) {
        if (value == null) return true;
        if (value == "") return true;
        if (value == "0000-00-00") return false;

        var regEx = /^\d{4}-\d{2}-\d{2}$/;
        return value.match(regEx) != null;
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
                notBefore70: true
            },
            discontinued: {
                required: false,
                isDate: true,
                greaterThan: "#introduced",
                notBefore70: true
            }
        },
        messages: {
            name: {
            	required: translated['required'],
            	allSpace: translated['notblank']
            },
            introduced: {
                isDate: translated['isdate'],
                notBefore70: translated['before70']
            },
            discontinued: {
                isDate: translated['isdate'],
                greaterThan: translated['greaterthan'],
                notBefore70: translated['before70']
            }
        }
    });
});