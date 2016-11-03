/**
 * Created by liwenhe on 2016/11/1.
 */
$('.pagination-select').dropkick();
$(".pagination-select").on("change", function () {
    var request;
    var action = $(this).parents("form").attr("action");
    if (action.indexOf("?") != -1) {
        request = action + "&" + $(this).parents("form").serialize();
    } else {
        request = action + "?" + $(this).parents("form").serialize();
    }
    window.location.href = request;
});