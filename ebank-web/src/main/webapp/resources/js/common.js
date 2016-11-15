/**
 * Created by liwenhe on 2016/11/1.
 */
// 初始化加载
$(document).ready(function () {
    if ($("#rightbox") != undefined) $("#rightbox").niceScroll({cursorborder: "none", horizrailenabled: true});
    if ($("#table") != undefined) $("#table").niceScroll({cursorborder: "none", horizrailenabled: true});
});

// 通用查询方法
function _search(_this) {
    var form = _this.nodeName === "FORM" ? _this : $(_this).parents("form")[0];
    form.submit();
}

// 通用重置方法
function _reset(_this) {
    var form = _this.nodeName === "FORM" ? _this : $(_this).parents("form")[0];
    var formElements = $(form).find("input,select");
    $.each(formElements, function () {
        switch (this.nodeName.toLowerCase()) {
            case "input":
                $(this).val("");
                break;
            case "select":
                $(this).find("option:first").attr('selected', 'selected');
                break;
        }
    });
    _search(_this);
}

// 通用查看方法
function _view() {
    // 校验参数
    var returnValue = {};
    validateArgus(arguments, returnValue);

    // 默认参数
    var url          = returnValue.url;
    var title        = returnValue.title  || "查看";
    var width        = returnValue.width  || "100%";
    var height       = returnValue.height || "100%";
    var isShadeClose = returnValue.isShadeClose || true;

    layer.open({
        type: 2,
        title: title,
        shadeClose: isShadeClose,
        shade: [0.5],
        area: [width, height],
        content: url,
        maxmin: false
    });
}

// 通用新增方法
function _add() {
    if (arguments.length == 1) _edit(arguments[0]);
    if (arguments.length == 2) _edit(arguments[0], arguments[1]);
    if (arguments.length == 3) _edit(arguments[0], arguments[1], arguments[2]);
    if (arguments.length == 4) _edit(arguments[0], arguments[1], arguments[2], arguments[3]);
    if (arguments.length == 5) _edit(arguments[0], arguments[1], arguments[2], arguments[3], arguments[4]);
}

// 通用编辑方法
function _edit() {
    // 校验参数
    var returnValue = {};
    validateArgus(arguments, returnValue);

    // 默认参数
    var url          = returnValue.url;
    var title        = returnValue.title  || "查看";
    var width        = returnValue.width  || "100%";
    var height       = returnValue.height || "100%";
    var isShadeClose = returnValue.isShadeClose || true;

    layer.open({
        type: 2,
        title: title,
        shadeClose: isShadeClose,
        shade: [0.5],
        area: [width, height],
        content: url,
        maxmin: false,
        btn: ["确定"],
        yes: function (index, cLayer) {
            var iframeWin = window[cLayer.find('iframe')[0]['name']];
            iframeWin.submit();
        }
    });
}

// 通用删除方法
function _delete(url) {
    layer.confirm('真的要删除该记录吗?', {icon: 7, title:'警告'}, function(){
        var index = layer.load();
        $.get(url, function (result) {
            layer.alert(result.message, function () {
                layer.close(index);
                window.location.reload();
            });
        });
    });
}

// 校验参数
function validateArgus (argus, returnValue) {
    // 判断参数个数
    if (argus.length == 0) throw new Error("请求地址不能为空");
    $.each(argus, function (index) {
        switch (index) {
            case 0:
                var reg1 = /[a-zA-z]+:\/\/[^\s]*/;
                var reg2 = /(\/.+)+/;
                if (reg1.test(argus[0]) || reg2.test(argus[0])) {
                    returnValue.url = argus[0];
                } else {
                    throw new Error("请求地址格式错误");
                }
                break;
            case 1:
                if (typeof argus[1] == "string")
                    returnValue.title = argus[1];
                else
                    throw new Error("[title]参数类型不正确");
                break;
            case 2:
                if (typeof argus[2] == "string")
                    returnValue.width = argus[2];
                else
                    throw new Error("[width]参数类型不正确");
                break;
            case 3:
                if (typeof argus[3] == "string")
                    returnValue.height = argus[3];
                else
                    throw new Error("[height]参数类型不正确");
                break;
            case 4:
                if (typeof argus[4] == "boolean")
                    returnValue.isShadeClose = argus[4];
                else
                    throw new Error("[isShadeClose]参数类型不正确");
                break;
            default:
                throw new Error("参数错误")
        }
    });
}