<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/jsp-templ.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<tmpl:override name="title">微信菜单列表</tmpl:override>

<tmpl:override name="page_css">
    <link href="${pageContext.request.contextPath}/resources/js/plugins/fancytree/css/ui.fancytree.css" rel="stylesheet" type="text/css">
    <style>
        body {background: inherit;margin: 0 auto;width: 98%;}
        th { text-align:center;font-weight:500;padding: 15px 0; }
        thead { background:rgb(244,244,244); }
        tbody tr td { padding-top:5px; padding-bottom: 5px; }
    </style>
</tmpl:override>

<tmpl:override name="body">
    <div class="button-wrapper">
        <div class="left button-group-wrapper ">
            <sec:authorize url="/weixin/menu/add">
                <div class="button-group">
                    <a href="javascript:CURD.add('${pageContext.request.contextPath}/weixin/menu/custom/menu/add/${id}', '新增微信主菜单', '600px', '365px');" class="button">
                        <img src="${pageContext.request.contextPath}/resources/images/btn_add_n.png" height="18" width="18"
                             alt="添加主菜单">
                        <span>添加主菜单</span>
                    </a>
                </div>
            </sec:authorize>
            <div class="button-group">
                <a href="javascript:void(0);" class="button" id="expand">
                    <img src="${pageContext.request.contextPath}/resources/images/ico_nav_Monitor_h.png" height="18"
                         width="18" alt="">
                    <span>展开/折叠</span>
                </a>
            </div>
        </div>
        <div class="right">
            <form class="form-inline" role="form" action="${pageContext.request.contextPath}/weixin/menu/custom/menu/list/${id}">
                <div class="form-group">
                    <p>
                        <label>筛选</label>
                        <input id="search" placeholder="菜单名称..." autocomplete="off" class="form-control">
                    </p>
                </div>
                <div class="form-group">
                    <a href="javascript:void(0);" onclick="CURD.reset(this)" class="button"><img
                            src="${pageContext.request.contextPath}/resources/images/btn_Reset_n.png" alt="" height="18"
                            width="18"><span>刷新</span></a>
                </div>
            </form>
        </div>
    </div>

    <table id="treetable" style="width:100%;outline:0;">
        <colgroup>
            <col width="30%"/>
            <col width="30%"/>
            <col width="*"/>
            <col width="150px"/>
        </colgroup>
        <thead>
        <tr>
            <th>菜单名称</th>
            <th>菜单类型</th>
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
    <script type="text/javascript">
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
                url: "${pageContext.request.contextPath}/weixin/menu/custom/menu/dataset/${id}"
            },
            icon: function (event, data) {
                if (data.node.key != 1 && data.node.data.icon == null) {
                    if (data.node.hasChildren()) {
                        data.node.folder = true;
                    }
                }
            },
            renderTitle: function (event, data) {
                if (data.node.key != 1) {
                    data.node.title = data.node.data.name;
                    data.node.visit(function (node) {
                        node.title = node.data.name;
                    });
                }
            },
            renderColumns: function(event, data) {
                var node = data.node, $tdList = $(node.tr).find(">td");

                $tdList.eq(1).text(node.data.type).css({"text-align": "center"});
                $tdList.eq(2).text(node.data.order).css({"text-align": "center"});

                var operateElem = "";
                <sec:authorize url="/weixin/menu/add/child/">
                    if (node.data.flag != undefined && node.data.flag == true) {
                        operateElem += "<a class='mr-5' "
                                + "href=\"javascript:CURD.add('${pageContext.request.contextPath}/weixin/menu/add/child/"
                                + node.data.id + "', '新增子菜单', '600px', '365px');\" title='新增子菜单'>"
                                + "<img src='${pageContext.request.contextPath}/resources/images/add.png'>"
                                + "</a>";
                    }
                </sec:authorize>

                <sec:authorize url="/weixin/menu/view/">
                operateElem += "<a class='mr-5' "
                        + "href=\"javascript:CURD.view('${pageContext.request.contextPath}/weixin/menu/view/"
                        + node.data.id + "', '查看菜单', '600px', '365px');\" title='查看菜单'>"
                        + "<img src='${pageContext.request.contextPath}/resources/images/eye.png'>"
                        + "</a>";
                </sec:authorize>

                <sec:authorize url="/weixin/menu/edit/">
                operateElem += "<a class='mr-5' "
                        + "href=\"javascript:CURD.edit('${pageContext.request.contextPath}/weixin/menu/edit/"
                        + node.data.id + "', '编辑菜单', '600px', '365px');\" title='编辑菜单'>"
                        + "<img src='${pageContext.request.contextPath}/resources/images/edit.png'>"
                        + "</a>";
                </sec:authorize>

                <sec:authorize url="/weixin/menu/delete/">
                operateElem += "<a "
                        + "href=\"javascript:CURD.delete('${pageContext.request.contextPath}/weixin/menu/delete/"
                        + node.data.id + "');\" title='删除菜单'>"
                        + "<img src='${pageContext.request.contextPath}/resources/images/btn_delete_n.png'>"
                        + "</a>";
                </sec:authorize>

                $tdList.eq(3).html(operateElem).css({'text-align': 'center'});
            }
        });

        // 展开与折叠
        $("#expand").on("click", function (e) {
            e.preventDefault();
            var activeNode = $("#treetable").fancytree("getActiveNode");
            if (null != activeNode) {
                activeNode.toggleExpanded();
                activeNode.visit(function (node) {
                    node.toggleExpanded();
                });
            } else {
                $("#treetable").fancytree("getRootNode").visit(function (node) {
                    node.toggleExpanded();
                });
            }
        });

        $("#search").on("keyup", function (e) {
            var n, tree = $("#treetable").fancytree("getTree");
            var opts = { autoExpand: true, leavesOnly: true }, match = $(this).val();
            if(e && e.which === $.ui.keyCode.ESCAPE || $.trim(match) === ""){
                filterReset(this, tree);
            } else {
                tree.filterNodes(match, opts);
            }
        });

        $("#search").on("keydown", function (e) {
            if(e.keyCode == 13){
                return false;
            }
        });

        var filterReset = function (_this, tree) {
            $(_this).val("");
            tree.clearFilter();
        };
    </script>
</tmpl:override>

<%@ include file="../../shared/decorator.jsp" %>