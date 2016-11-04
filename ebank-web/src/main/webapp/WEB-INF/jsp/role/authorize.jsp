<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/jsp-templ.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>

<tmpl:override name="title">角色授权</tmpl:override>

<tmpl:override name="page_css">
    <link href="${pageContext.request.contextPath}/resources/js/plugins/layui/css/layui.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/js/plugins/fancytree/css/ui.fancytree.css" rel="stylesheet" type="text/css">
    <style>
        body { background: inherit; }
        th { text-align:left;font-weight:500; }
        thead { background:rgb(244,244,244); }
        tbody tr td { padding-top:5px; padding-bottom: 5px; }
    </style>
</tmpl:override>

<tmpl:override name="body">
    <div id="table">
        <form class="layui-form" action="${pageContext.request.contextPath}/role/authorize/${id}" method="post">
            <table id="treetable" style="width:100%;outline:0;">
                <colgroup>
                    <col width="*"/>
                </colgroup>
                <thead>
                <tr>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </form>
    </div>
</tmpl:override>

<tmpl:override name="page_script">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/layui/layui.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/submit.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/jquery-ui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/fancytree/jquery.fancytree.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/fancytree/jquery.fancytree.table.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/fancytree/jquery.fancytree.filter.js"></script>
    <script>
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
            icon: function(node, data) {
                data.node.icon = data.node.data.icon;
            },
            source: {
                url: "${pageContext.request.contextPath}/function/dataset"
            },
            postProcess: function (event, data) {
                $.get("${pageContext.request.contextPath}/role/functions/${id}", function (result) {
                    data.node.visit(function (node) {
                        node.title = node.data.name;
                        $.each(result, function () {
                            if (node.data.id == this.id) {
                                node.setSelected(true);
                                return false;
                            }
                        });
                    });
                    data.tree.render({deep:true});
                });
            },
            renderColumns: function(event, data) {
                var node = data.node, $tdList = $(node.tr).find(">td");

                // 初始化title
                $tdList.eq(0).find(".fancytree-title").text(node.data.name);

                // 判断checkbox是否选中
                if (node.isSelected()) {
                    $tdList.eq(0)
                            .find(".fancytree-expander")
                            .after( "<span class='fancytree-checkbox'>" +
                                    "<input style='display: none;' type='checkbox' name='functionIds' value='" + node.data.id + "' checked='checked'>" +
                                    "</span>" );
                } else {
                    $tdList.eq(0)
                            .find(".fancytree-expander")
                            .after( "<span class='fancytree-checkbox'>" +
                                    "<input style='display: none;' type='checkbox' name='functionIds' value='" + node.data.id + "'>" +
                                    "</span>" );
                }

                // 展开
                node.setExpanded(node.isSelected());
                node.visit(function (children) {
                    children.setExpanded(children.isSelected());
                });
            },
            select: function (event, data) {
                if (data.targetType == "checkbox") {
                    $(data.node.tr).find("input[name=functionIds]").attr("checked", data.node.selected);
                    if (data.node.selected) data.node.setExpanded(data.node.selected);
                }
            }
        });
    </script>
</tmpl:override>

<%@ include file="../shared/decorator.jsp" %>