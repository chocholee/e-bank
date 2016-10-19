<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%-- 分页 --%>
<c:if test="${pagination ne null && pagination.data ne null}">
    <form id="pageForm" action="${param.paginationURL}">
        <div class="page right">
                <%-- 分页显示数目 --%>
            <select name="pageSize" class="pagination-select select left mr-10">
                <option value="10" <c:if test="${pagination.pageSize == 10}">selected</c:if>>显示10条</option>
                <option value="20" <c:if test="${pagination.pageSize == 20}">selected</c:if>>显示20条</option>
                <option value="30" <c:if test="${pagination.pageSize == 30}">selected</c:if>>显示30条</option>
                <option value="40" <c:if test="${pagination.pageSize == 40}">selected</c:if>>显示40条</option>
            </select>

                <%-- 总共数据数量 --%>
            <span class="left mr-10 tipNum">共<span>${pagination.count}</span>条</span>

                <%-- 判断是否到达页首 --%>
            <c:choose>
                <c:when test="${pagination.page <= 1 || pagination.pageSize >= pagination.count}">
                    <div class="left prevent"><a href="javascript:void(0);" class="left mr-10 prevIcon"></a></div>
                </c:when>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${fn:contains(param.paginationURL, '?')}">
                            <div class="left"><a
                                    href="${param.paginationURL}&page=${pagination.page - 1}&pageSize=${pagination.pageSize}"
                                    class="left mr-10 prevIcon"></a></div>
                        </c:when>
                        <c:otherwise>
                            <div class="left"><a
                                    href="${param.paginationURL}?page=${pagination.page - 1}&pageSize=${pagination.pageSize}"
                                    class="left mr-10 prevIcon"></a></div>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>

                <%-- 加载分页 --%>
            <select name="page" class="pagination-select select left mr-10">
                <fmt:formatNumber var="totalPage"
                                  value="${pagination.count / pagination.pageSize + (pagination.count % pagination.pageSize == 0 ? 0 : 0.5)}"
                                  pattern="#"/>
                    <%-- 加载前3页 --%>
                <c:forEach var="page" begin="0" end="${pagination.page - 1}">
                    <c:if test="${(pagination.page - page) <= 3 && page != 0}">
                        <option value="${page}">第${page}页</option>
                    </c:if>
                </c:forEach>
                    <%-- 加载当前页及后3页 --%>
                <c:forEach var="page" begin="${pagination.page}" end="${totalPage}">
                    <c:choose>
                        <c:when test="${pagination.page == page}">
                            <option value="${page}" selected>第${page}页</option>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${page - pagination.page <= 3}">
                                <option value="${page}">第${page}页</option>
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>

                <%-- 判断是否到达页尾 --%>
            <c:choose>
                <c:when test="${pagination.page >= pagination.count || pagination.pageSize >= pagination.count}">
                    <div class="left prevent"><a href="javascript:void(0);" class="nextIcon"></a></div>
                </c:when>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${fn:contains(param.paginationURL, '?')}">
                            <div class="left"><a
                                    href="${param.paginationURL}&page=${pagination.page + 1}&pageSize=${pagination.pageSize}"
                                    class="left nextIcon"></a></div>
                        </c:when>
                        <c:otherwise>
                            <div class="left"><a
                                    href="${param.paginationURL}?page=${pagination.page + 1}&pageSize=${pagination.pageSize}"
                                    class="left nextIcon"></a></div>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
        </div>

        <%-- js --%>
        <script>
            $(".pagination-select").on("change", function () {
                var request;
                var action = $("#pageForm").attr("action");
                if (action.indexOf("?") != -1) {
                    request = action + "&" + $("#pageForm").serialize();
                } else {
                    request = action + "?" + $("#pageForm").serialize();
                }
                window.location.href = request;
            });
        </script>
    </form>
</c:if>
