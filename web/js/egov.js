
FormItem = function(label, id) {
    this.label = label;
    this.id = id;
}

EgovUtil = function() {

    this.isEmpty = function (formItems) {
        for (var i = 0; i < formItems.length; i++) {
            var formItem = formItems[i];
            if (document.getElementById(formItem.id).value == "") {
                alert(formItem.label + "不能为空，请重新输入");
                document.getElementById(formItem.id).focus();
                return false;
            }
        }
        return true;
    }

    this.isSame = function(formItem1, formItem2) {

        if (document.getElementById(formItem1.id).value != document.getElementById(formItem2.id).value) {
            alert(formItem1.label + "和" + formItem2.label + "不一致，请修改！");
            document.getElementById(formItem1.id).value = "";
            document.getElementById(formItem2.id).value = "";
            document.getElementById(formItem1.id).focus();
            return false;
        }
        return true;
    }
}

var $ = new EgovUtil();


