<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/jsp-templ.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<tmpl:override name="title">个性化菜单列表</tmpl:override>

<tmpl:override name="page_css">
</tmpl:override>

<tmpl:override name="rightBox">
    <%-- 标题 --%>
    <span class="title">个性化菜单列表</span>
    <div class="greyLine"></div>

    <%-- 条件查询及按钮操作区域 --%>
    <div class="button-wrapper">
        <div class="left button-group-wrapper">
            <sec:authorize url="/weixin/menu/custom/add">
                <div class="button-group">
                    <a href="javascript:CURD.add('${pageContext.request.contextPath}/weixin/menu/custom/add', '新增', '600px', '345px');" class="button">
                        <img src="${pageContext.request.contextPath}/resources/images/btn_add_n.png" height="18" width="18"
                             alt="添加">
                        <span>添加</span>
                    </a>
                </div>
            </sec:authorize>
            <sec:authorize url="/weixin/menu/custom/menu/list/">
                <div class="button-group">
                    <a href="javascript:package('${pageContext.request.contextPath}/weixin/menu/custom/menu/list');" class="button">
                        <img src="${pageContext.request.contextPath}/resources/images/btn_edit_n.png" height="18" width="18"
                             alt="编辑菜单包">
                        <span>编辑菜单包</span>
                    </a>
                </div>
            </sec:authorize>
        </div>
        <div class="right">
            <form class="form-inline" role="form" action="${pageContext.request.contextPath}/weixin/menu/custom/list">
                <div class="form-group">
                    <label>名称</label>
                    <input name="name" type="text" class="form-control" value="${menuCustom.name}">
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
                <th></th>
                <th>名称</th>
                <th>备注</th>
                <th>创建日期</th>
                <th>操作</th>
            </tr>
            <c:if test="${pagination ne null && pagination.data ne null}">
                <c:forEach var="menuCustom" items="${pagination.data}">
                    <tr>
                        <td><input type="checkbox" value="${menuCustom.id}"></td>
                        <td>${menuCustom.name}</td>
                        <td>${menuCustom.remark}</td>
                        <td>${menuCustom.createdDate}</td>
                        <td class="last-td">
                            <sec:authorize url="/weixin/menu/custom/view/">
                                <a href="javascript:CURD.view('${pageContext.request.contextPath}/weixin/menu/custom/view/${menuCustom.id}', '查看', '650px', '355px')" title="查看">
                                    <img src="${pageContext.request.contextPath}/resources/images/eye.png">
                                </a>
                            </sec:authorize>
                            <sec:authorize url="/weixin/menu/custom/edit/">
                                <a href="javascript:CURD.edit('${pageContext.request.contextPath}/weixin/menu/custom/edit/${menuCustom.id}', '编辑', '600px', '345px')" title="编辑">
                                    <img src="${pageContext.request.contextPath}/resources/images/edit.png" alt="编辑">
                                </a>
                            </sec:authorize>
                            <sec:authorize url="/weixin/menu/custom/delete/">
                                <a href="javascript:CURD.delete('${pageContext.request.contextPath}/weixin/menu/custom/delete/${menuCustom.id}')" title="删除">
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
        <jsp:param name="paginationURL" value="${pageContext.request.contextPath}/weixin/menu/custom/list?name=${menuCustom.name}"/>
    </jsp:include>
</tmpl:override>

<tmpl:override name="page_script">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/jquery.dropkick-min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/pagination.js"></script>
    <script type="text/javascript">
        // 初始化CURD
        CURD.init(window, window);

        <sec:authorize url="/weixin/menu/custom/menu/list/">
            // 编辑菜单包
            function package(url) {
                var checkboxes = $("#table").find("input[type=checkbox]").filter(":checked");
                if (checkboxes.length != 1) {
                    layer.alert("请择一条记录进行操作!")
                    return false;
                } else {
                    url = url + "/" + $(checkboxes).val();
                    layer.open({
                        type: 2,
                        title: "编辑菜单包",
                        shadeClose: true,
                        shade: [0.5],
                        area: ["800px", "600px"],
                        content: url,
                        maxmin: false
                    })
                }
            }
        </sec:authorize>
    </script>
</tmpl:override>

<%@ include file="../../shared/decorator.jsp" %>