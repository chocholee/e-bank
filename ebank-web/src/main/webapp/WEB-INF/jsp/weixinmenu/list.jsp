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
    <title>菜单列表</title>
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
    <a href="/weixin/menu/add">添加</a>
    <a href="/weixin/menu/sync">同步菜单至微信</a>
    <table>
        <thead>
            <tr>
                <th>菜单名称</th>
                <th>菜单KEY值</th>
                <th>网页链接</th>
                <th>菜单类型</th>
                <th>消息类型</th>
                <th>父菜单</th>
                <th>所属公众号</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="menu" items="${pagination.data}">
                <tr>
                    <td>${menu.name}</td>
                    <td>${menu.key}</td>
                    <td>${menu.url}</td>
                    <td>
                        <c:if test="${menu.type ne null}">
                            ${menu.type.name}
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${menu.msgType ne null}">
                            ${menu.msgType.name}
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${menu.parent ne null}">
                            ${menu.parent.name}
                        </c:if>
                    </td>
                    <td>${menu.accountEntity.name}</td>
                    <td>
                        <a href="/weixin/menu/edit/${menu.id}">编辑</a>
                        <a href="/weixin/menu/delete/${menu.id}">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
