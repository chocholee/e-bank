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
    <title>用户创建</title>
</head>
<body>
<form:form action="/user/add" method="post" modelAttribute="user">
    <div>
        <form:label path="username">用户名</form:label>
        <form:input path="username"/>
        <form:errors path="username"/>
    </div>
    <div>
        <form:label path="password">密码</form:label>
        <form:password path="password"/>
        <form:errors path="password"/>
    </div>
    <div>
        <form:label path="realname">真实姓名</form:label>
        <form:input path="realname"/>
    </div>
    <div>
        <form:label path="phone">手机号码</form:label>
        <form:input path="phone"/>
    </div>
    <div>
        <form:label path="email">邮箱</form:label>
        <form:input path="email"/>
    </div>
    <div>
        <form:label path="remark">备注</form:label>
        <form:input id="remark" path="remark"/>
    </div>
    <div>
        <label for="parentId">父用户</label>
        <select name="parent.id" id="parentId">
            <option value="">请选择</option>
            <c:forEach var="tmpUser" items="${users}">
                <c:choose>
                    <c:when test="${user.parent ne null && tmpUser.id == user.parent.id}">
                        <option value="${tmpUser.id}" selected>${tmpUser.username}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${tmpUser.id}">${tmpUser.username}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
    </div>
    <input type="submit" value="创建">
</form:form>
</body>
</html>
