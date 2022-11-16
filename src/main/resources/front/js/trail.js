/// <reference path="../node_modules/jquery/jquery.min.js" />
$(function () {
    var i = 0;
    function next() {
        if (++i == 3) { i = 0; };
        $("container2>img").animate({ left: --i * 1240 }, 1000)
    }

    setInterval(next, 1000);
    $(".we > div >a").hide();

    $("we").mousemove(function () {
        $(".we > div >.s1").css("top",0).show();

    }).mouseout(function () {
        $(this).hide();
    })
    $(".we > div >.s2").mousemove(function () {
        $(this).css("top", 0).show();
    }).mouseout(function () {
        $(this).hide();
    })
    $(".we > div >.s3").mousemove(function () {
        $(this).css("top", 0).show();
    }).mouseout(function () {
        $(this).hide();
    })
    $(".we > div >.s4").mousemove(function () {
        $(this).css("top", 0).show();
    }).mouseout(function () {
        $(this).hide();
    })
    $(".we > div >.s5").mousemove(function () {
        $(this).css("top", 0).show();
    }).mouseout(function () {
        $(this).hide();
    })
    $(".we > div >.s6").mousemove(function () {
        $(this).css("top", 0).show();
    }).mouseout(function () {
        $(this).hide();
    })
})