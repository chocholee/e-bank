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
    <title>用户列表</title>
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
    <a href="/user/add">添加</a>
    <table>
        <thead>
            <tr>
                <th >用户名</th>
                <th>真实姓名</th>
                <th>备注</th>
                <th>登录日期</th>
                <th>上次登录日期</th>
                <th>创建日期</th>
                <th>更新日期</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="user" items="${pagination.data}">
                <tr>
                    <td>${user.username}</td>
                    <td>${user.realname}</td>
                    <td>${user.remark}</td>
                    <td>${user.loginDate}</td>
                    <td>${user.lastLoginDate}</td>
                    <td>${user.createdDate}</td>
                    <td>${user.updatedDate}</td>
                    <td>${user.status.name}</td>
                    <td>
                        <a href="/user/edit/${user.id}">编辑</a>
                        <a href="/user/delete/${user.id}">删除</a>
                        <a href="/user/lock/${user.id}">启用/禁用</a>
                        <a href="/user/authorize/${user.id}">授权</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
