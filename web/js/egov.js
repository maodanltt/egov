
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
}


