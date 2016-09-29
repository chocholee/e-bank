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
    <title>角色创建</title>
</head>
<body>
<form:form action="/role/add" method="post" modelAttribute="role">
    <div>
        <form:label path="name">名称</form:label>
        <form:input path="name"/>
        <form:errors path="name"/>
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
        <label for="parentId">父角色</label>
        <select name="parent.id" id="parentId">
            <option value="">请选择</option>
            <c:forEach var="tmpRole" items="${roles}">
                <c:choose>
                    <c:when test="${role.parent ne null && tmpRole.id == role.parent.id}">
                        <option value="${tmpRole.id}" selected>${tmpRole.name}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${tmpRole.id}">${tmpRole.name}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
    </div>
    <input type="submit" value="创建">
</form:form>
</body>
</html>
