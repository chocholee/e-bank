<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="leftbox" class="left">
    <ul>
        <c:forEach var="firstFunc" items="${firstFuncList}">
            <li class="menuBar">
                <c:set var="secondFuncs"/>
                <c:forEach var="map" items="${secondFuncMap}">
                    <c:if test="${map.key ne null && map.key eq firstFunc.id}">
                        <c:set var="secondFuncs" value="${map.value}"/>
                    </c:if>
                </c:forEach>
                <c:choose>
                    <c:when test="${secondFuncs ne null && fn:length(secondFuncs) ne 0}">
                        <a href="javascript:void(0);">
                            <c:choose>
                                <c:when test="${firstFunc.iconEntity ne null}">
                                    <img src="${iconHost}/${firstFunc.iconEntity.beforeHoverPath}"
                                         data-src-before="${iconHost}/${firstFunc.iconEntity.beforeHoverPath}"
                                         data-src-after="${iconHost}/${firstFunc.iconEntity.afterHoverPath}" width="18"
                                         height="18">
                                </c:when>
                                <c:otherwise>
                                    <img src="" data-src-before="" data-src-after="">
                                </c:otherwise>
                            </c:choose>
                            <span>${firstFunc.name}</span>
                        </a>
                        <ul>
                            <c:forEach var="secondFunc" items="${secondFuncs}">
                                <li>
                                    <a href="${pageContext.request.contextPath}${secondFunc.uri}">${secondFunc.name}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}${firstFunc.uri}" style="background-image: none;">
                            <c:choose>
                                <c:when test="${firstFunc.iconEntity ne null}">
                                    <img src="${iconHost}/${firstFunc.iconEntity.beforeHoverPath}"
                                         data-src-before="${iconHost}/${firstFunc.iconEntity.beforeHoverPath}"
                                         data-src-after="${iconHost}/${firstFunc.iconEntity.afterHoverPath}" width="18"
                                         height="18">
                                </c:when>
                                <c:otherwise>
                                    <img src="" data-src-before="" data-src-after="">
                                </c:otherwise>
                            </c:choose>
                            <span>${firstFunc.name}</span>
                        </a>
                    </c:otherwise>
                </c:choose>
            </li>
        </c:forEach>
    </ul>
</div>