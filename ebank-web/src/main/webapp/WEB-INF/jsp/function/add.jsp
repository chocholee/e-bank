<%--
  Created by IntelliJ IDEA.
  User: liwenhe
  Date: 2016/9/28
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>功能创建</title>
</head>
<body>
<form:form action="/function/add" method="post" modelAttribute="function">
    <div>
        <form:label path="name">名称</form:label>
        <form:input path="name"/>
        <form:errors path="name"/>
    </div>
    <div>
        <form:label path="code">编码</form:label>
        <form:input path="code"/>
    </div>
    <div>
        <form:label path="uri">路径</form:label>
        <form:input path="uri"/>
    </div>
    <div>
        <form:label path="iconId">图标</form:label>
        <form:input path="iconId"/>
    </div>
    <div>
        <form:label path="description">描述</form:label>
        <form:input path="description"/>
    </div>
    <div>
        <form:label path="order">排序</form:label>
        <form:input path="order"/>
    </div>
    <div>
        <form:label path="type">类型</form:label>
        <form:select path="type">
            <form:option value="FIRST">一级菜单</form:option>
            <form:option value="SECOND">二级菜单</form:option>
            <form:option value="THIRD">三级菜单</form:option>
        </form:select>
    </div>
    <div>
        <label for="parentId">父资源</label>
        <select name="parent.id" id="parentId">
            <option value="">请选择</option>
            <c:forEach var="tmpFunction" items="${functions}">
                <c:choose>
                    <c:when test="${function.parent ne null && tmpFunction.id == function.parent.id}">
                        <option value="${tmpFunction.id}" selected>${tmpFunction.name}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${tmpFunction.id}">${tmpFunction.name}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
    </div>
    <input type="submit" value="创建">
</form:form>
</body>
</html>
