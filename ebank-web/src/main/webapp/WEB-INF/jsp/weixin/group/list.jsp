<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/jsp-templ.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<tmpl:override name="title">分组列表</tmpl:override>

<tmpl:override name="page_css">
    <style>
        .form-group label { margin: 0; }
        .button-wrapper .dk_container { display: inline-block; line-height: 1.2; float: none; }
        .button-wrapper .dk_container ul { height: inherit; padding: 0px; }
    </style>
</tmpl:override>

<tmpl:override name="rightBox">
    <%-- 标题 --%>
    <span class="title">分组列表</span>
    <div class="greyLine"></div>

    <%-- 条件查询及按钮操作区域 --%>
    <div class="button-wrapper">
        <div class="left button-group-wrapper">
            <sec:authorize url="/weixin/group/add">
                <div class="button-group">
                    <a href="javascript:CURD.add('${pageContext.request.contextPath}/weixin/group/add', '新增', '600px', '400px');" class="button">
                        <img src="${pageContext.request.contextPath}/resources/images/btn_add_n.png" height="18" width="18"
                             alt="添加">
                        <span>添加</span>
                    </a>
                </div>
            </sec:authorize>
            <sec:authorize url="/weixin/group/sync">
                <div class="button-group">
                    <a href="javascript:sync('${pageContext.request.contextPath}/weixin/group/sync');" class="button">
                        <img src="${pageContext.request.contextPath}/resources/images/btn_Reset_n.png" height="18" width="18"
                             alt="拉取分组">
                        <span>拉取分组</span>
                    </a>
                </div>
            </sec:authorize>
        </div>
        <div class="right">
            <form class="form-inline" role="form" action="${pageContext.request.contextPath}/weixin/group/list">
                <div class="form-group mr-5">
                    <label>名称</label>
                    <input name="name" type="text" class="form-control" value="${group.name}">
                </div>
                <div class="form-group">
                    <label>类型</label>
                    <select name="type">
                        <option value="ALL">请选择</option>
                        <option value="WECHAT" <c:if test="${group.type eq 'WECHAT'}">selected</c:if>>微信分组</option>
                        <option value="VIRTUAL" <c:if test="${group.type eq 'VIRTUAL'}">selected</c:if>>虚拟分组</option>
                    </select>
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
                <th>名称</th>
                <th>用户数量</th>
                <th>组类型</th>
                <th>备注</th>
                <th>操作</th>
            </tr>
            <c:if test="${pagination ne null && pagination.data ne null}">
                <c:forEach var="group" items="${pagination.data}">
                    <tr>
                        <td>${group.name}</td>
                        <td>${group.count}</td>
                        <td>${group.type.name}</td>
                        <td>${group.remark}</td>
                        <td class="last-td">
                            <sec:authorize url="/weixin/group/view/">
                                <a href="javascript:CURD.view('${pageContext.request.contextPath}/weixin/group/view/${group.id}', '查看', '650px', '400px')" title="查看">
                                    <img src="${pageContext.request.contextPath}/resources/images/eye.png">
                                </a>
                            </sec:authorize>
                            <sec:authorize url="/weixin/group/edit/">
                                <a href="javascript:CURD.edit('${pageContext.request.contextPath}/weixin/group/edit/${group.id}', '编辑', '600px', '400px')" title="编辑">
                                    <img src="${pageContext.request.contextPath}/resources/images/edit.png" alt="编辑">
                                </a>
                            </sec:authorize>
                            <sec:authorize url="/weixin/group/delete/">
                                <a href="javascript:CURD.delete('${pageContext.request.contextPath}/weixin/group/delete/${group.id}')" title="删除">
                                    <img src="${pageContext.request.contextPath}/resources/images/btn_delete_n.png" alt="删除">
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
        <jsp:param name="paginationURL" value="${pageContext.request.contextPath}/weixin/group/list?name=${group.name}&type=${group.type}"/>
    </jsp:include>
</tmpl:override>

<tmpl:override name="page_script">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/jquery.dropkick-min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/pagination.js"></script>
    <script type="text/javascript">
        // 初始化CURD
        CURD.init(window, window);
        $('.button-wrapper select').dropkick();

        <sec:authorize url="/weixin/group/sync">
            // 拉取微信分组
            function sync(url) {
                var index = layer.load();
                $.get(url, function (result) {
                    layer.alert(result.message, function () {
                        layer.close(index);
                        window.location.reload();
                    });
                });
            }
        </sec:authorize>
    </script>
</tmpl:override>

<%@ include file="../../shared/decorator.jsp" %>