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
    <title>用户授权</title>
    <style>
        thead > tr,
        tbody > tr {
            text-align: center;
        }

        thead > tr > th,
        tbody > tr > td {
            border: 1px solid #ccc;
            padding: 5px 5px;
        }
    </style>
</head>
<body>
<form action="/user/authorize/${id}" method="post">
    <table>
        <thead>
            <tr>
                <th></th>
                <th>角色名称</th>
                <th>角色描述</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="role" items="${roles}">
            <tr>
                <td>
                    <c:forEach var="user" items="role.userEntities">
                        <c:choose>
                            <c:when test="${user.id == id}">
                                <input type="checkbox" name="roleIds" value="${role.id}" checked>
                            </c:when>
                            <c:otherwise>
                                <input type="checkbox" name="roleIds" value="${role.id}">
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </td>
                <td>${role.name}</td>
                <td>${role.description}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <input type="submit" value="创建">
</form>
</body>
</html>
