<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/jsp-templ.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<tmpl:override name="title">添加按钮</tmpl:override>

<tmpl:override name="page_css">
    <link href="${pageContext.request.contextPath}/resources/js/plugins/layui/css/layui.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/js/plugins/fancytree/css/ui.fancytree.css" rel="stylesheet" type="text/css">
    <style>
        body { background: inherit; }
        th { text-align:center;font-weight:500;padding: 15px 0; }
        thead { background:rgb(244,244,244); }
        tbody tr td { padding-top:5px; padding-bottom: 5px; }
        .button-wrapper {height: inherit;}
        .button-group-wrapper {line-height: 40px; padding-left: 5px; }
        .fancytree-checkbox input[type=checkbox] { display: none; }
        .fancytree-checkbox .layui-form-checkbox { display: none; }
    </style>
</tmpl:override>

<tmpl:override name="body">
    <div class="button-wrapper">
        <div class="left button-group-wrapper">
            <sec:authorize url="/function/add">
                <div class="button-group">
                    <a href="javascript:CURD.add('${pageContext.request.contextPath}/function/btn/add/${id}', '添加按钮', '600px', '450px');" class="button">
                        <img src="${pageContext.request.contextPath}/resources/images/btn_add_n.png" height="18" width="18"
                             alt="添加按钮">
                        <span>添加按钮</span>
                    </a>
                </div>
            </sec:authorize>
        </div>
    </div>

    <table id="treetable" style="width:100%;outline:0;">
        <colgroup>
            <col width="200px"/>
            <col width="*"/>
            <col width="150px"/>
        </colgroup>
        <thead>
        <tr>
            <th>按钮名称</th>
            <th style="cursor:pointer;">顺序</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</tmpl:override>

<tmpl:override name="page_script">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/jquery-ui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/fancytree/jquery.fancytree.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/fancytree/jquery.fancytree.table.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/fancytree/jquery.fancytree.filter.js"></script>
    <script>
        // 初始化CURD
        CURD.init(window, parent);

        // 初始化插件
        $("#treetable").fancytree({
            debugLevel: 0,
            strings: {loading: "加载中…", loadError: "加载出错!", moreData: "更多…", noData: "没有可用数据."},
            extensions: ["table", "filter"],
            checkbox: false,
            table: {
                indentation: 20,      // indent 20px per node level
                nodeColumnIdx: 0     // render the node title into the 1rd column
            },
            filter:{
                autoApply: true,
                counter: true,
                fuzzy: false,
                hideExpandedCounter: true,
                highlight: true,
                mode: "dimm",
                autoExpand:true
            },
            source: {
                url: "${pageContext.request.contextPath}/function/btn/dataset/${id}"
            },
            icon: function (event, data) {
                if (data.node.key != 1 && data.node.data.icon == null) {
                    if (data.node.hasChildren()) {
                        data.node.folder = true;
                    }
                }
            },
            renderColumns: function(event, data) {
                var node = data.node, $tdList = $(node.tr).find(">td");

                // 初始化title
                $tdList.eq(0).find(".fancytree-title").text(node.data.name);

                $tdList.eq(0)
                        .find(".fancytree-expander")
                        .after( "<span class='fancytree-checkbox'>" +
                                "<input style='display: none;' type='checkbox' name='functionId' value='" + node.data.id + "'>" +
                                "<input type='hidden' name='functionType' value='" + node.data.type + "'>" +
                                "</span>" );

                $tdList.eq(1).text(node.data.order).css({"text-align": "center"});
                var operateElem = "";
                <sec:authorize url="/function/view/">
                operateElem +=  "<a class='mr-5' href=\"javascript:CURD.view('${pageContext.request.contextPath}/function/view/" + node.data.id + "', '查看', '600px', '450px');\" title='查看'>" +
                        "<img src='${pageContext.request.contextPath}/resources/images/eye.png'>" +
                        "</a>";
                </sec:authorize>
                <sec:authorize url="/function/edit/">
                operateElem +=  "<a class='mr-5' href=\"javascript:CURD.edit('${pageContext.request.contextPath}/function/edit/" + node.data.id + "', '编辑', '600px', '450px');\" title='查看'>" +
                        "<img src='${pageContext.request.contextPath}/resources/images/edit.png'>" +
                        "</a>";
                </sec:authorize>
                <sec:authorize url="/function/delete/">
                operateElem +=  "<a href=\"javascript:CURD.delete('${pageContext.request.contextPath}/function/delete/" + node.data.id + "');\" title='删除'>" +
                        "<img src='${pageContext.request.contextPath}/resources/images/btn_delete_n.png'>" +
                        "</a>";
                </sec:authorize>
                $tdList.eq(2).html(operateElem).css({'text-align': 'center'});
            }
        });
    </script>
</tmpl:override>

<%@ include file="../shared/decorator.jsp" %>