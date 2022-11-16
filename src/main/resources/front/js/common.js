$(function () {
	// 分享
	$('body').append(`<div class="share-box" style="display:none;">
		<a href="javascript:void(0);" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a>
		<a href="http://v.t.sina.com.cn/share/share.php?url=http://bbs.360.cn/http%3A%2F%2Fbbs.360.cn%2Fthread-14611207-1-1.html&amp;appkey=1874498659&amp;title=【倒计时】360N4S骁龙版发布还有4天！——来自@360社区" class="share_xinlang" title="分享到新浪微博" target="_blank"></a>
		<a href="http://connect.qq.com/widget/shareqq/index.html?url=http%3A%2F%2Fbbs.360.cn%2Fhttp%3A%2F%2Fbbs.360.cn%2Fthread-14611207-1-1.html&amp;desc=%E3%80%90%E5%80%92%E8%AE%A1%E6%97%B6%E3%80%91360N4S%E9%AA%81%E9%BE%99%E7%89%88%E5%8F%91%E5%B8%83%E8%BF%98%E6%9C%894%E5%A4%A9%EF%BC%81&amp;title=%E3%80%90%E5%80%92%E8%AE%A1%E6%97%B6%E3%80%91360N4S%E9%AA%81%E9%BE%99%E7%89%88%E5%8F%91%E5%B8%83%E8%BF%98%E6%9C%894%E5%A4%A9%EF%BC%81&amp;summary=%E3%80%90%E5%80%92%E8%AE%A1%E6%97%B6%E3%80%91360N4S%E9%AA%81%E9%BE%99%E7%89%88%E5%8F%91%E5%B8%83%E8%BF%98%E6%9C%894%E5%A4%A9%EF%BC%81&amp;pics=&amp;site=360%E7%A4%BE%E5%8C%BA" class="share_qq" title="分享到qq好友" target="_blank"></a>
		<a href="javascript:void(0);" class="share_qqone" title="分享到QQ空间" target="_blank"></a>
		<a href="javascript:void(0);" class="bds_tieba" data-cmd="tieba" title="分享到百度贴吧"></a>
	</div>`)
	var shareBox = $('.share-box')
	var mouseIn = false
	var shareClose = function () {
		setTimeout(function () {
			if (mouseIn) {
				shareClose()
			} else {
				shareBox.hide()
			}
		}, 1000);
	}
	shareBox.on('mouseenter', function () {
		mouseIn = true
	}).on('mouseleave', function () {
		mouseIn = false
	})
	$('.share').on('mouseenter', function () {
		mouseIn = true
		let ts = $(this)
		let of = ts.offset()
		shareBox.show().insertAfter(ts).offset({
			left: of.left - (shareBox.width() - ts.width()) / 2,
			top: of.top + ts.height() + 10
		})
	}).on('mouseleave', function () {
		mouseIn = false
		shareClose()
	})

	// 返回顶部
	$(window).on('scroll', function () {
		if ($(window).scrollTop() > 1000) {
			$('#backTop').show()
		} else {
			$('#backTop').hide()
		}
	})
	$('#backTop').on('click', function () {
		$(window).scrollTop(0)
	})
})
