/**
 * Created by liwenhe on 2016/10/14.
 */
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

    $(document).on("click", ".menuBar > a", function () {
        var _this = this;
        $(this).parent(".menuBar").find("ul").slideToggle("normal", "linear", function () {
            if ($(this).css("display") == "none") {
                $(_this).removeClass("current");
                var bgImg = $(_this).css("background-image");
                $(_this).addClass("current");
                $(_this).css("background-image", bgImg);
            } else {
                $(_this).css("background-image", "");
            }
        });
        $(this).parent(".menuBar").siblings().find("a img").each(function () {
            $(this).attr("src", $(this).attr("data-src-before"));
        });
        $(this).parent(".menuBar").siblings().find("ul").slideUp();
        $(this).parent(".menuBar").siblings().children("a").removeClass("current");
        $(this).addClass("current");
        if ($(this).find("img").attr("src") === $(this).find("img").attr("data-src-before")) {
            $(this).find("img").attr("src", $(this).find("img").attr("data-src-after"));
        } else {
            $(this).find("img").attr("src", $(this).find("img").attr("data-src-before"));
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

    // screenWidth();
});