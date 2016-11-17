<%--suppress ALL --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/jsp-templ.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<tmpl:override name="title">查看图文项</tmpl:override>

<tmpl:override name="page_css">
    <link href="${pageContext.request.contextPath}/resources/css/form.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/js/plugins/layui/css/layui.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/css/news-template.css" rel="stylesheet" type="text/css">
</tmpl:override>

<tmpl:override name="body">
    <div class="item-preview">
        <c:forEach var="item" items="${items}" varStatus="status">
            <div class="item" data-id="${item.id}">
                <c:choose>
                    <c:when test="${status.first}">
                        <img src="${templateHost}/${item.picUrl}"/>
                        <div class="item-description">
                            <p>${item.description}</p>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="item-block">
                            <p>${item.description}</p>
                            <img src="${templateHost}/${item.picUrl}"/>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </c:forEach>
    </div>
    <div class="mask">
        <div class="btn-group">
            <sec:authorize url="/weixin/template/news/item/edit/">
                <a href="javascript:void(0);" title="编辑图文项">
                    <img src="${pageContext.request.contextPath}/resources/images/edit.png" alt="编辑图文项">
                </a>
            </sec:authorize>
            <sec:authorize url="/weixin/template/news/item/delete/">
                <a href="javascript:void(0);" title="删除图文项">
                    <img src="${pageContext.request.contextPath}/resources/images/btn_delete_n.png" alt="删除图文项">
                </a>
            </sec:authorize>
        </div>
        <div class="bg"></div>
    </div>
</tmpl:override>

<tmpl:override name="page_script">
    <c:if test="${fn:length(items) eq 0}">
        <script type="text/javascript">
            parent.layer.alert('请添加图文项信息!', {title: "警告"}, function () {
                parent.layer.closeAll(); //再执行关闭所有层
            });
        </script>
    </c:if>
    <script type="text/javascript">
        // 初始化CURD
        CURD.init(window, parent);

        // 增加遮罩
        $(".item").on("mouseenter", function () {
            $(".mask").show();

            var itemId = $(this).attr("data-id");
            var width  = $(this).width() + 10;
            var height = $(this).height() + 5;
            var top    = $(this).offset().top;
            var left   = $(this).offset().left - 5;
            var maskBtnGroupHeight = $(".mask > .btn-group").height();
            var maskBtnGroupWidth  = $(".mask > .btn-group").width();

            $(".mask").css({"position": "absolute", "top": top, "left": left});
            $(".mask").width(width);
            $(".mask").height(height);
            $(".mask > .btn-group").css({
                "top": height / 2 - maskBtnGroupHeight / 2,
                "left": width / 2 - maskBtnGroupWidth / 2
            });

            var editURL   = "${pageContext.request.contextPath}/weixin/template/news/item/edit/" + itemId;
            var deleteURL = "${pageContext.request.contextPath}/weixin/template/news/item/delete/" + itemId;
            $(".mask > .btn-group > a:first").attr("href", "javascript:CURD.edit('" + editURL + "', '编辑图文项', '900px', '495px');");
            $(".mask > .btn-group > a:last").attr("href", "javascript:CURD.delete('" + deleteURL + "');");
        });
    </script>
</tmpl:override>

<%@ include file="../../../shared/decorator.jsp" %>