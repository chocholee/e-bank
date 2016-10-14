/**
 * Created by liwenhe on 2016/10/14.
 */
// 初始化菜单
/*(function () {
    // 定义ajax路径
    var queryAllFirstMenuURL    = "/function/query_all_first_menu";
    var queryByParentIdURL      = "/function/query";

    // 开始处理
    $.ajax({
        type: "get",
        url: queryAllFirstMenuURL,
        dataType: "json",
        success: function (result) {
            $.each(result, function () {
                var parent = this;
                var icon = this.iconEntity;
                $.ajax({
                    type: "get",
                    url: queryByParentIdURL,
                    data: {parentId: this.id},
                    dataType: "json",
                    success: function (result) {
                        console.log(result)
                        // 添加一级菜单
                        var menubarLi;
                        if (result.length == 0) {
                            if (icon !== undefined) {
                                menubarLi = "<li class=\"menuBar\">"
                                    + "<a href='" + parent.uri + "'>"
                                    + "<img src='" + icon.beforeHoverPath + "' data-src-before='" + icon.beforeHoverPath + "' data-src-after='" + icon.afterHoverPath + "'>"
                                    + "<span>" + parent.name + "</span>"
                                    + "</a>";
                            } else {
                                menubarLi = "<li class=\"menuBar\">"
                                    + "<a href='" + parent.uri + "'>"
                                    + "<span>" + parent.name + "</span>"
                                    + "</a>";
                            }
                        } else {
                            if (icon !== undefined) {
                                menubarLi = "<li class=\"menuBar\">"
                                    + "<a href='javascript:void(0);'>"
                                    + "<img src='" + icon.beforeHoverPath + "' data-src-before='" + icon.beforeHoverPath + "' data-src-after='" + icon.afterHoverPath + "'>"
                                    + "<span>" + parent.name + "</span>"
                                    + "</a>";
                            } else {
                                menubarLi = "<li class=\"menuBar\">"
                                    + "<a href='javascript:void(0);'>"
                                    + "<span>" + parent.name + "</span>"
                                    + "</a>";
                            }
                        }

                        // 添加二级菜单
                        $.each(result, function () {
                            menubarLi += "<ul>"
                                + "<li><a href='" + this.uri + "'>" + this.name + "</a></li>"
                                + "</ul>";
                        });

                        // 添加操作
                        $("#leftbox > ul").append(menubarLi);
                    }
                });
            });
        }
    });
})();*/

// 菜单事件处理
$(document).ready(function () {
    // 对菜单做初始化处理
    $(".menuBar").each(function () {
        var $this = $(this);
        var currentMenuHref = $(this).children("a").attr("href");
        var pathname = window.location.pathname;
        if (pathname === currentMenuHref) {
            $this.children("a").addClass("current");
        } else {
            $this.find("ul li a").each(function () {
                if ($(this).attr("href") === pathname) {
                    $(this).addClass("active");
                    $this.children("a").addClass("current");
                    $this.find("ul").slideToggle("normal", "linear");
                    return false;
                }
            });
        }
    });

    $(document).on("click", ".menuBar", function () {
        $(this).find("ul").slideToggle("normal", "linear");
        $(this).siblings().find("a img").each(function () {
            $(this).attr("src", $(this).attr("data-src-before"));
        });
        $(this).siblings().find("ul").slideUp();
        $(this).children("a").addClass("current");
        $(this).siblings().children("a").removeClass("current");
        $(this).siblings().find("ul li a").removeClass("active");
        if ($(this).find("a img").attr("src") === $(this).find("a img").attr("data-src-before")) {
            $(this).find("a img").attr("src", $(this).find("a img").attr("data-src-after"));
        } else {
            $(this).find("a img").attr("src", $(this).find("a img").attr("data-src-before"));
        }
    });

    $(document).on("click", ".menuBar ul li a", function (e) {
        $(this).toggleClass("active");
        $(this).parent().siblings().find(" a").removeClass("active");
        e.stopPropagation();
        e.cancelBubble = true;
    });

    $(document).on("click", ".setting a", function () {
        $(this).toggleClass("active").siblings().removeClass("active");
    });

    function screenWidth() {
        if (window.screen.width < 1366) {
            $("#content").css('width', '90%');
            $("#leftbox").css('width', '160px');
            $("#rightbox").css('left', '160px');
            $(".setting").css('fontSize', '14px');
            $("#leftbox ul li").css('fontSize', '14px');
            $("#leftbox ul li ul li").css({
                "font-size": "12px",
                "text-indent": "-0.1em"
            });
            $("#leftbox ul .menuBar a img").css('margin', '11px 10px 0 11px');
        } else {
            $("#content").css('width', '73%');
        }
    }

    screenWidth();
});