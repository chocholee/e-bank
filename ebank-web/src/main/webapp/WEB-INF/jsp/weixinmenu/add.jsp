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
    <title>菜单创建</title>
</head>
<body>
<form:form action="/weixin/menu/add" method="post" modelAttribute="menu">
    <div>
        <form:label path="name">微信菜单名称</form:label>
        <form:input path="name"/>
    </div>
    <div>
        <form:label path="key">菜单KEY值</form:label>
        <form:input path="key"/>
    </div>
    <div>
        <form:label path="url">网页链接</form:label>
        <form:input path="url"/>
    </div>
    <div>
        <form:label path="templateId">消息模板id</form:label>
        <form:input path="templateId"/>
    </div>
    <div>
        <form:label path="order">排序</form:label>
        <form:input path="order"/>
    </div>
    <div>
        <label for="type">菜单类型</label>
        <select name="type" id="type">
            <option value="">请选择</option>
            <option value="CLICK">消息触发</option>
            <option value="VIEW">网页链接</option>
        </select>
    </div>
    <div>
        <label for="msgType">消息类型</label>
        <select name="msgType" id="msgType">
            <option value="">请选择</option>
            <option value="TEXT">文本</option>
            <option value="NEWS">图文</option>
            <option value="EXPAND">扩展</option>
        </select>
    </div>
    <div>
        <label for="parent">关联自身</label>
        <select name="parent" id="parent">
            <option value="">请选择</option>
            <c:forEach var="tempMenu" items="${menus}">
                <option value="${tempMenu.id}">${tempMenu.name}</option>
            </c:forEach>
        </select>
    </div>
    <input type="submit" value="创建">
</form:form>
</body>
</html>
