<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/jsp-templ.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tmpl:override name="title">微信公众号</tmpl:override>

<tmpl:override name="rightBox">
    <%-- 标题 --%>
    <span class="title">微信公众号</span>
    <div class="greyLine"></div>

    <%-- 条件查询及按钮操作区域 --%>
    <div class="button-wrapper">
        <div class="left button-group-wrapper">
            <div class="button-group mr-20">
                <a href="${pageContext.request.contextPath}/weixin/account/add" class="button">
                    <img src="${pageContext.request.contextPath}/resources/images/btn_add_n.png" height="18" width="18"
                         alt="添加">
                    <span>添加</span>
                </a>
            </div>
            <div class="button-group">
                <a href="${pageContext.request.contextPath}/weixin/account/reset" class="button">
                    <img src="${pageContext.request.contextPath}/resources/images/btn_Reset_n.png" height="18" width="18"
                         alt="重置微信Token">
                    <span>重置微信Token</span>
                </a>
            </div>
        </div>
        <div class="right">
            <form id="form1" class="form-inline" role="form" action="/weixin/account/list">
                <div class="form-group">
                    <label>公众号名称</label>
                    <input name="name" type="text" class="form-control" value="${account.name}">
                </div>
                <div class="form-group">
                    <label>公众号appId</label>
                    <input name="appId" type="text" class="form-control" value="${account.appId}">
                </div>
                <div class="form-group">
                    <a href="javascript:search();" class="button"><img
                            src="${pageContext.request.contextPath}/resources/images/btn_search_n.png" alt="查询"
                            height="18" width="18"><span>查询</span></a>
                </div>
                <div class="form-group">
                    <a href="javascript:reset();" class="button"><img
                            src="${pageContext.request.contextPath}/resources/images/btn_Reset_n.png" alt="重置"
                            height="18"
                            width="18"><span>重置</span></a>
                </div>
            </form>
        </div>
    </div>
    <div class="clearfix"></div>

    <%-- 表格数据 --%>
    <div id="table">
        <table class="table" cellspacing="0">
            <tr>
                <th></th>
                <th>公众号名称</th>
                    <%--<th>公众号token</th>--%>
                <th>公众号微信号</th>
                <th>公众号微信号(原始ID)</th>
                <th>公众号appId</th>
                <th>公众号appSecret</th>
                <th>公众号email</th>
                <th>公众号描述</th>
                <th>公众号类型</th>
                <th>关联用户</th>
                <th>创建日期</th>
                <th>操作</th>
            </tr>
            <c:forEach var="account" items="${pagination.data}">
                <tr>
                    <td><input type="checkbox" value="${account.id}"></td>
                    <td>${account.name}</td>
                        <%--<td>${account.token}</td>--%>
                    <td>${account.number}</td>
                    <td>${account.accountId}</td>
                    <td>${account.appId}</td>
                    <td>${account.appSecret}</td>
                    <td>${account.email}</td>
                    <td>${account.description}</td>
                    <td>${account.type.name}</td>
                    <td>
                        <c:if test="${account.user ne null}">
                            ${account.user.username}
                        </c:if>
                    </td>
                    <td>${account.createdDate}</td>
                    <td class="last-td">
                        <a href="javascript:view('${pageContext.request.contextPath}/weixin/account/view/${account.id}')"><img
                                src="${pageContext.request.contextPath}/resources/images/eye.png" alt="查看"></a>
                        <a href="/weixin/account/edit/${account.id}"><img
                                src="${pageContext.request.contextPath}/resources/images/edit.png" alt="编辑"></a>
                        <a href="/weixin/account/delete/${account.id}"><img
                                src="${pageContext.request.contextPath}/resources/images/btn_delete_n.png" alt="删除"></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</tmpl:override>

<tmpl:override name="page_script">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/layer/layer.js"></script>
    <script>
        $(function () {
            $("#table").niceScroll({cursorborder: "none", horizrailenabled: true});
        });

        function view(url) {
            layer.open({
                type: 2,
                title: '查看',
                shadeClose: true,
                shade: [0.5],
                area: ['600px', '600px'],
                content: url,
                maxmin: false,
                btn: ["确定"],
                yes: function(index, layero){
                    var body = layer.getChildFrame('body', index);
                    var iframeWin = window[layero.find('iframe')[0]['name']];
                    console.log(iframeWin)
//                    iframeWin.hello();
                },
                end: function () {
                    alert("hahahaha1111")
                }
            });
        }
    </script>
</tmpl:override>

<%@ include file="../../shared/decorator.jsp" %>