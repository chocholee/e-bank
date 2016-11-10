/**
 * Created by liwenhe on 2016/11/1.
 */
// 表单提交时操作
layui.use('form', function(){
    var form = layui.form();
    form.on('submit', function (data) {
        var _this= this;
        $.ajax({
            url:  this.action,
            type: this.method,
            data: $(this).serialize(),
            success: function (result) {
                if (result.type === "SUCCESS") {
                    var parentWindow = parent.window;
                    parent.layer.alert(result.message, function () {
                        parentWindow.location.reload();
                        parent.layer.closeAll();
                    });
                } else {
                    if (result.data !== null && result.data !== undefined) {
                        $.each(result.data, function () {
                            var elem = $(_this).find("*[name=" + this.field + "]");
                            layer.tips(this.defaultMessage, elem, {tips: [3, '#c00']})
                            return false;
                        });
                    } else {
                        parent.layer.alert(result.message);
                    }
                }
            }
        });
        return false;
    });

    window.submit = function () {
        $(".layui-form").submit();
    }
});