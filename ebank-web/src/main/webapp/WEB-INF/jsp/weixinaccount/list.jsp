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
    <title>公众号列表</title>
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
    <a href="/weixin/account/add">添加</a>
    <table>
        <thead>
            <tr>
                <th>公众号名称</th>
                <th>公众号token</th>
                <th>公众号</th>
                <th>公众号微信号</th>
                <th>公众号appId</th>
                <th>公众号appSecret</th>
                <th>公众号email</th>
                <th>公众号描述</th>
                <th>公众号类型</th>
                <th>关联用户</th>
                <th>创建日期</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="account" items="${pagination.data}">
                <tr>
                    <td>${account.name}</td>
                    <td>${account.token}</td>
                    <td>${account.number}</td>
                    <td>${account.accountId}</td>
                    <td>${account.appId}</td>
                    <td>${account.appSecret}</td>
                    <td>${account.email}</td>
                    <td>${account.description}</td>
                    <td>${account.type.name}</td>
                    <td>
                        <c:if test="${account.user ne null}">
                            ${account.user.username}
                        </c:if>
                    </td>
                    <td>${account.createdDate}</td>
                    <td>
                        <a href="/weixin/account/edit/${account.id}">编辑</a>
                        <a href="/weixin/account/delete/${account.id}">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
