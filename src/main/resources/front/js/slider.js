// var dfsj_jq = jQuery.noConflict();
var dfsj_jq = jQuery;
dfsj_jq.fn.focusShow = function() {
    var obj = dfsj_jq(this);

    //幻灯切换
    var topCol = obj.find(".topCol");

    var topItem = topCol.find("a");
    var topItemLength = topItem.length;

    var topNow = 0;
    var timer;
    //and by zengyueming 隐藏第一个ul
    var topColUl = topCol.find("ul");
    topColUl.first().css("display", "none");
    //end
    var points = dfsj_jq("<ul></ul>");
    topCol.append(points);

    for (var i = 0; i < topItemLength; i++) {
        points.append("<li></li>")
    }
    var point = points.find("li");
    point.first().addClass("now");

    var scrollTop = function() {
        point.filter(".now").removeClass("now");
        point.eq(topNow).addClass("now");

        topItem.filter(":not(:eq(" + topNow + "))").fadeOut();
        topItem.eq(topNow).fadeIn();

        topNow++;
        if (topNow == topItemLength) {
            topNow = 0;
        }
        timer = setTimeout(scrollTop, 5000);
    }
    scrollTop();

    //为数字按钮添加鼠标滑入事件，以显示相应的内容，酷站代码整理
    point
        .mouseenter(function() {
            var itemLi = point.index(this);
            topNow=itemLi;
            point.filter(".now").removeClass("now");
            point.eq(itemLi).addClass("now");

            topItem.filter(":not(:eq(" + itemLi + "))").fadeOut();
            topItem.eq(itemLi).fadeIn();
        });

    //鼠标高亮
    var item = obj.find("a");

    item
        .each(function() {
            dfsj_jq(this).prepend("<em></em>")
        })
        .mouseenter(function() {
            var shadow = item.not(dfsj_jq(this)).find("em");
            shadow.stop().animate({
                opacity: 0.4
            }, (0.4 - shadow.css("opacity")) / 0.4 * 500)
        })
        .mouseleave(function() {
            var shadow = item.not(dfsj_jq(this)).find("em");
            item.find("em").stop().animate({
                opacity: 0
            }, (shadow.css("opacity")) / 0.4 * 500)
        })
    //加上上一页下一页控制
    obj.hover(function(){
        dfsj_jq("#index-pre").fadeIn();
        dfsj_jq("#index-next").fadeIn();
    },function(){
        dfsj_jq("#index-pre").fadeOut();
        dfsj_jq("#index-next").fadeOut();
    });

    obj.find("#index-pre").click(function(){
        clearTimeout(timer);
        topNow-=2;
        if(topNow<0){
            topNow=topItemLength+topNow;
        }
        point.filter(".now").removeClass("now");
        point.eq(topNow).addClass("now");

        topItem.filter(":not(:eq(" + topNow + "))").fadeOut();
        topItem.eq(topNow).fadeIn();
        scrollTop();
    });
    obj.find("#index-next").click(function(){
        clearTimeout(timer);
        if(topNow>=topItemLength){
            topNow=0;
        }
        point.filter(".now").removeClass("now");
        point.eq(topNow).addClass("now");

        topItem.filter(":not(:eq(" + topNow + "))").fadeOut();
        topItem.eq(topNow).fadeIn();
        scrollTop();
    });
}

dfsj_jq.fn.slideShow = function() {
    var obj = dfsj_jq(this);

    var pics = obj.find("li");
    var pic_num = pics.length;
    var pic_cur = 0;

    var timer;

    obj.append("<div class=\"toggle\"></div>");
    var toggles = obj.find(".toggle");
    for (var i = 0; i < pic_num; i++) {
        toggles.append("<a></a>");
    }

    var toggle = toggles.find("a");
    toggle.each(function() {
        dfsj_jq(this).click(function() {
            pic_cur = dfsj_jq(this).index();
            rollPic();
            return false
        })
    })

    var showPic = function(id) {
        toggle.filter(".current").removeClass("current");
        toggle.eq(id).addClass("current");

        pics.filter(":visible").hide();
        pics.eq(id).show();

        return false
    }

    var rollPic = function() {
        clearTimeout(timer);

        if (pic_cur >= pic_num) {
            pic_cur = 0;
        }

        showPic(pic_cur);

        pic_cur++;

        timer = setTimeout(rollPic, 5000);
    }

    if (pic_num > 0) {
        rollPic();

        obj.mouseover(function() {
            clearTimeout(timer)
        })

        obj.mouseleave(function() {
            timer = setTimeout(rollPic, 5000)
        })
    }
}
dfsj_jq(document).ready(function() {
    dfsj_jq(".dfsj_focus").focusShow();
    dfsj_jq(".newestact_focus").focusShow();
    dfsj_jq("#picShow").slideShow();
	//热门推荐和最新主贴切换
	dfsj_jq(".hot-posts").click(function(){
		dfsj_jq(".hot-posts").addClass("posts-selected");
		dfsj_jq(".new-posts").removeClass("posts-selected");
		dfsj_jq(".hot-posts-pannel").show();
		dfsj_jq(".new-posts-pannel").hide();
	});
	dfsj_jq(".new-posts").click(function(){
		dfsj_jq(".new-posts").addClass("posts-selected");
		dfsj_jq(".hot-posts").removeClass("posts-selected");
		dfsj_jq(".hot-posts-pannel").hide();
		dfsj_jq(".new-posts-pannel").show();
	});
	dfsj_jq(".baywindow-close").click(function(event){
		dfsj_jq(this).parent().hide();
		event.stopPropagation();
		return false;
	});

})
