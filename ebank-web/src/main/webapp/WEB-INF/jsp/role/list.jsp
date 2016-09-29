<%--
  Created by IntelliJ IDEA.
  User: liwenhe
  Date: 2016/9/28
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>角色列表</title>
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
    <a href="/role/add">添加</a>
    <table>
        <thead>
            <tr>
                <th>角色名称</th>
                <th>角色描述</th>
                <th>角色排序</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="role" items="${pagination.data}">
                <tr>
                    <td>${role.name}</td>
                    <td>${role.description}</td>
                    <td>${role.order}</td>
                    <td>
                        <a href="/role/edit/${role.id}">编辑</a>
                        <a href="/role/delete/${role.id}">删除</a>
                        <a href="/role/authorize/${role.id}">授权</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
