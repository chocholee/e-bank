<%--suppress ALL --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/jsp-templ.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tmpl:override name="title">新增个性化菜单规则</tmpl:override>

<tmpl:override name="page_css">
    <link href="${pageContext.request.contextPath}/resources/css/form.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/js/plugins/layui/css/layui.css" rel="stylesheet" type="text/css">
</tmpl:override>

<tmpl:override name="body">
    <div class="block">
        <form class="layui-form" action="${pageContext.request.contextPath}/weixin/menu/custom/rule/add" method="post">
            <div class="layui-form-item">
                <label class="layui-form-label red-star">名称</label>
                <div class="layui-input-block">
                    <input type="text" name="name" lay-verify="required" autocomplete="off" class="layui-input"
                           value="${menuCustomRule.name}">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label red-star">个性化菜单</label>
                <div class="layui-input-block">
                    <button class="layui-btn" id="select-menu-custom" type="button" lay-verify="required"
                            style="margin-bottom:4px;margin-left:4px;">选择个性化菜单
                    </button>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">微信分组</label>
                <div class="layui-input-block">
                    <button class="layui-btn" id="select-group-wechat" type="button"
                            style="margin-bottom:4px;margin-left:4px;">选择微信分组
                    </button>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-block">
                    <select name="sex">
                        <option value="">请选择</option>
                        <option value="">请选择</option>
                        <option value="MALE">男</option>
                        <option value="FEMALE">女</option>
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">国家</label>
                <div class="layui-input-block">
                    <select name="country" id="country" lay-filter="country">
                        <option value="">请选择</option>
                        <option value="">请选择</option>
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">省份</label>
                <div class="layui-input-block">
                    <select name="province" id="province" lay-filter="province">
                        <option value="">请选择</option>
                        <option value="">请选择</option>
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">城市</label>
                <div class="layui-input-block">
                    <select name="city" id="city" lay-filter="city">
                        <option value="">请选择</option>
                        <option value="">请选择</option>
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">客户端类别</label>
                <div class="layui-input-block">
                    <select name="platform">
                        <option value="">请选择</option>
                        <option value="">请选择</option>
                        <option value="IOS">IOS系统</option>
                        <option value="ANDROID">Android系统</option>
                        <option value="OTHERS">其它</option>
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">语言</label>
                <div class="layui-input-block">
                    <select name="language">
                        <option value="">请选择</option>
                        <option value="">请选择</option>
                        <option value="zh_CN">简体中文</option>
                        <option value="zh_TW">繁体中文TW</option>
                        <option value="zh_HK">繁体中文HK</option>
                        <option value="en">英文</option>
                        <option value="id">印尼</option>
                        <option value="ms">马来</option>
                        <option value="es">西班牙</option>
                        <option value="ko">韩国</option>
                        <option value="it">意大利</option>
                        <option value="ja">日本</option>
                        <option value="pl">波兰</option>
                        <option value="pt">葡萄牙</option>
                        <option value="ru">俄国</option>
                        <option value="th">泰文</option>
                        <option value="vi">越南</option>
                        <option value="ar">阿拉伯语</option>
                        <option value="hi">北印度</option>
                        <option value="he">希伯来</option>
                        <option value="tr">土耳其</option>
                        <option value="de">德语</option>
                        <option value="fr">法语</option>
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                    <textarea name="remark" placeholder="请输入备注" class="layui-textarea">${menuCustomRule.remark}</textarea>
                </div>
            </div>
        </form>
    </div>
</tmpl:override>

<tmpl:override name="page_script">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/layui/layui.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/submit.js"></script>
    <script type="text/javascript">
        // 个性化菜单按钮事件处理
        $(document).on("click", "#select-menu-custom", function () {
            var _this = this;
            parent.layer.open({
                type: 2,
                title: "选择个性化菜单",
                shadeClose: true,
                shade: [0.5],
                area: ['800px', '500px'],
                content: "${pageContext.request.contextPath}/weixin/menu/custom/list/select",
                maxmin: false,
                btn: ["确定"],
                yes: function (index, cLayer) {
                    var iframeWin = parent.window[cLayer.find('iframe')[0]['name']];
                    var ids = iframeWin.select();

                    if (ids.length != 1) {
                        parent.layer.alert("只能选择一条记录");
                        return false;
                    } else {
                        var _inputHidden = "<input type='hidden' name='menuCustomId' value='" + ids[0].id + "'>";
                        var _inputShow = "<input class='layui-input' lay-verify='required' type='text' disabled"
                                + " name='menuCustomName' style='display:inline-block;width:60%;'"
                                + " value='" + ids[0].name + "'>";

                        $(_this).prevAll().remove();
                        $(_this).before(_inputHidden);
                        $(_this).before(_inputShow);
                        $(_this).removeAttr("lay-verify");

                        parent.layer.close(index);
                    }
                }
            });
        });

        // 微信分组按钮事件处理
        $(document).on("click", "#select-group-wechat", function () {
            var _this = this;
            parent.layer.open({
                type: 2,
                title: "选择微信分组",
                shadeClose: true,
                shade: [0.5],
                area: ['800px', '500px'],
                content: "${pageContext.request.contextPath}/weixin/group/list/select/wechat",
                maxmin: false,
                btn: ["确定"],
                yes: function (index, cLayer) {
                    var iframeWin = parent.window[cLayer.find('iframe')[0]['name']];
                    var ids = iframeWin.select();

                    if (ids.length > 1) {
                        parent.layer.alert("只能选择一条记录");
                        return false;
                    } else if (ids.length == 0) {
                        $(_this).prevAll().remove();
                        parent.layer.close(index);
                    } else {
                        var _inputHidden = "<input type='hidden' name='groupWechatId' value='" + ids[0].id + "'>";
                        var _inputShow = "<input class='layui-input' type='text' disabled"
                                + " name='groupWechatName' style='display:inline-block;width:64%;'"
                                + " value='" + ids[0].name + "'>";

                        $(_this).prevAll().remove();
                        $(_this).before(_inputHidden);
                        $(_this).before(_inputShow);

                        parent.layer.close(index);
                    }
                }
            });
        });

        layui.use('form', function() {
            var form = layui.form();
            // 初始化国家
            $.get("${pageContext.request.contextPath}/weixin/area/countries", function (result) {
                $(result).each(function () {
                    $("#country").append(
                            "<option value='" + this.countryValue + "'>" + this.countryName + "</option>"
                    );
                });
                form.render("select");
            });

            form.on("select(country)", function (data) {
                if (data.value !== "" && data.value !== undefined) {
                    $.get("${pageContext.request.contextPath}/weixin/area/provinces/" + data.value, function (result) {
                        // 清空province
                        $("#province").empty();
                        $("#province").append("<option value=''>请选择</option>");
                        $("#province").append("<option value=''>请选择</option>");

                        // 清空city
                        $("#city").empty();
                        $("#city").append("<option value=''>请选择</option>");
                        $("#city").append("<option value=''>请选择</option>");

                        $(result).each(function () {
                            $("#province").append(
                                    "<option value='" + this.provinceValue + "'>" + this.provinceName + "</option>"
                            );
                        });
                        form.render("select");
                    });
                }
            });

            form.on("select(province)", function (data) {
                if (data.value !== "" && data.value !== undefined) {
                    $.get("${pageContext.request.contextPath}/weixin/area/cities/" + data.value, function (result) {
                        // 清空city
                        $("#city").empty();
                        $("#city").append("<option value=''>请选择</option>");
                        $("#city").append("<option value=''>请选择</option>");

                        $(result).each(function () {
                            $("#city").append(
                                    "<option value='" + this.cityValue + "'>" + this.cityName + "</option>"
                            );
                        });
                        form.render("select");
                    });
                }
            })
        });
    </script>
</tmpl:override>

<%@ include file="../../shared/decorator.jsp" %>
