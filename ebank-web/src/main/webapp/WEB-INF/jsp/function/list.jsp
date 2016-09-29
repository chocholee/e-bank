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
    <title>功能列表</title>
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
    <a href="/function/add">添加</a>
    <table>
        <thead>
            <tr>
                <th>功能名称</th>
                <th>功能编码</th>
                <th>功能标识</th>
                <th>功能描述</th>
                <th>功能排序</th>
                <th>功能类型</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="function" items="${pagination.data}">
                <tr>
                    <td>${function.name}</td>
                    <td>${function.code}</td>
                    <td>${function.uri}</td>
                    <td>${function.description}</td>
                    <td>${function.order}</td>
                    <td>${function.type.name}</td>
                    <td>
                        <a href="/function/edit/${function.id}">编辑</a>
                        <a href="/function/delete/${function.id}">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
