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
<form:form action="/weixin/account/add" method="post" modelAttribute="account">
    <div>
        <form:label path="name">公众号名称</form:label>
        <form:input path="name"/>
        <form:errors path="name"/>
    </div>
    <div>
        <form:label path="token">公众号token</form:label>
        <form:input path="token"/>
        <form:errors path="token"/>
    </div>
    <div>
        <form:label path="number">公众号</form:label>
        <form:input path="number"/>
        <form:errors path="number"/>
    </div>
    <div>
        <form:label path="accountId">公众号微信号</form:label>
        <form:input path="accountId"/>
        <form:errors path="accountId"/>
    </div>
    <div>
        <form:label path="appId">公众号appId</form:label>
        <form:input path="appId"/>
        <form:errors path="appId"/>
    </div>
    <div>
        <form:label path="appSecret">公众号appSecret</form:label>
        <form:input path="appSecret"/>
        <form:errors path="appSecret"/>
    </div>
    <div>
        <form:label path="email">公众号email</form:label>
        <form:input path="email"/>
    </div>
    <div>
        <form:label path="description">公众号描述</form:label>
        <form:input path="description"/>
    </div>
    <div>
        <label for="type">公众号类型</label>
        <select name="type" id="type">
            <option value="">请选择</option>
            <option value="SERVICE">服务号</option>
            <option value="SUBSCRIPTION">订阅号</option>
            <option value="ENTERPRISE">企业号</option>
        </select>
    </div>
    <input type="submit" value="创建">
</form:form>
</body>
</html>
