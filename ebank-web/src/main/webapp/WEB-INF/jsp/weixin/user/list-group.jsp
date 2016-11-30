<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/jsp-templ.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<tmpl:override name="title">微信用户列表</tmpl:override>

<tmpl:override name="page_css">
    <link href="${pageContext.request.contextPath}/resources/js/plugins/layui/css/layui.css" rel="stylesheet" type="text/css">
    <style>
        body {background: inherit;margin: 0 auto;width: 95%;}
        .layui-form-switch { margin-top: 0; }
        .layui-form-onswitch:before { padding-right: 16px; }
        .button-wrapper .dk_container { display: inline-block; line-height: 1; float: none; }
        .button-wrapper .dk_container ul { height: inherit; padding: 0px; }
        .form-group label { margin-right: 0px; }
    </style>
</tmpl:override>

<tmpl:override name="body">
    <%-- 条件查询及按钮操作区域 --%>
    <div class="button-wrapper">
        <div class="left button-group-wrapper">
            <sec:authorize url="/weixin/user/sync">
                <div class="button-group mr-20">
                    <a href="javascript:sync('${pageContext.request.contextPath}/weixin/user/sync');" class="button">
                        <img src="${pageContext.request.contextPath}/resources/images/btn_Reset_n.png" height="18" width="18"
                             alt="拉取用户">
                        <span>拉取用户</span>
                    </a>
                </div>
            </sec:authorize>
        </div>

        <div class="right">
            <form class="form-inline" role="form" action="${pageContext.request.contextPath}/weixin/user/list/group/${user.groupWechatId}">
                <div class="form-group">
                    <label>名称</label>
                    <input name="nickname" type="text" class="form-control" value="${user.nickname}">
                </div>

                <div class="form-group">
                    <a href="javascript:void(0);" onclick="CURD.search(this)" class="button"><img
                            src="${pageContext.request.contextPath}/resources/images/btn_search_n.png" alt="查询"
                            height="18"
                            width="18">
                        <span>查询</span>
                    </a>
                </div>

                <div class="form-group">
                    <a href="javascript:void(0);" onclick="CURD.reset(this)" class="button"><img
                            src="${pageContext.request.contextPath}/resources/images/btn_Reset_n.png" alt="重置"
                            height="18"
                            width="18">
                        <span>重置</span>
                    </a>
                </div>
            </form>
        </div>
    </div>
    <div class="clearfix"></div>

    <%-- 表格数据 --%>
    <div id="table">
        <table class="table" cellspacing="0">
            <tr>
                <th>昵称</th>
                <th>性别</th>
                <th>微信分组</th>
                <th>是否关注</th>
                <th>操作</th>
            </tr>
            <c:if test="${pagination ne null && pagination.data ne null}">
                <c:forEach var="user" items="${pagination.data}">
                    <tr>
                        <td>${user.nickname}</td>
                        <td><c:if test="${user.sex ne null}">${user.sex.name}</c:if></td>
                        <td><c:if test="${user.groupWechat ne null}">${user.groupWechat.name}</c:if></td>
                        <td>
                            <div class="layui-unselect layui-form-switch <c:if test="${user.subscribeType eq 'SUBSCRIBED'}">layui-form-onswitch</c:if>">
                                <i></i>
                            </div>
                        </td>
                        <td class="last-td">
                            <sec:authorize url="/weixin/user/view/">
                                <a href="javascript:CURD.view('${pageContext.request.contextPath}/weixin/user/view/${user.id}', '查看', '650px', '700px')" title="查看">
                                    <img src="${pageContext.request.contextPath}/resources/images/eye.png">
                                </a>
                            </sec:authorize>
                            <sec:authorize url="/weixin/user/group/wechat/">
                                <a href="javascript:CURD.edit('${pageContext.request.contextPath}/weixin/user/group/wechat/${user.id}', '修改微信分组', '650px', '335px')" title="修改微信分组">
                                    <img src="${pageContext.request.contextPath}/resources/images/grouping.png">
                                </a>
                            </sec:authorize>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
        </table>
    </div>

    <%-- 引入分页 --%>
    <jsp:include page="../../shared/pagination.jsp" flush="true">
        <jsp:param name="pagination" value="${pagination}"/>
        <jsp:param name="paginationURL" value="${pageContext.request.contextPath}/weixin/user/list/group/${user.groupWechatId}?nickname=${user.nickname}"/>
    </jsp:include>
</tmpl:override>

<tmpl:override name="page_script">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/jquery.dropkick-min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/pagination.js"></script>
    <script type="text/javascript">
        // 初始化CURD
        CURD.init(window, parent);
        $('.button-wrapper select').dropkick();

        <sec:authorize url="/weixin/user/sync">
            function sync(url) {
                layer.confirm('由于拉取微信用户信息需要大量时间,您确定拉取信息并等待完成?', {icon: 0, title:'提示'}, function(index){
                    var loadIndex = layer.load();
                    $.get(url, function (result) {
                        layer.alert(result.message, function () {
                            layer.close(loadIndex);
                            window.location.reload();
                        });
                    });
                    layer.close(index);
                });
            }
        </sec:authorize>
    </script>
</tmpl:override>

<%@ include file="../../shared/decorator.jsp" %>