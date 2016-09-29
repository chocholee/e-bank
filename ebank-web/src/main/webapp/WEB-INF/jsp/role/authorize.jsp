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
    <title>角色授权</title>
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
<form action="/role/authorize/${role.id}" method="post">
    <table>
        <thead>
            <tr>
                <th></th>
                <th>功能名称</th>
                <th>功能描述</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="function" items="${functions}">
            <tr>
                <td>
                    <c:set var="flag" value="false"/>
                    <c:forEach var="roleFunction" items="${role.functionEntities}">
                        <c:if test="${roleFunction.id == function.id}">
                            <input type="checkbox" name="functionIds" value="${function.id}" checked>
                            <c:set var="flag" value="true"/>
                        </c:if>
                    </c:forEach>
                    <c:if test="${flag != true}">
                        <input type="checkbox" name="functionIds" value="${function.id}">
                    </c:if>
                </td>
                <td>${function.name}</td>
                <td>${function.description}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <input type="submit" value="创建">
</form>
</body>
</html>
