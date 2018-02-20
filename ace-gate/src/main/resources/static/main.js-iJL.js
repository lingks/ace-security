

var blackcandy = (function () {

    var pagMoreFlag = 1;

    var init = function () {
        scroll2Top();
        handlePagination();
        handleSearch();
        showDescendantMenu();
        showVendor();
        noticeCarousel();
        showSidebar();
        showSupport();
        showWechat();
        handleImgBox();
        handleVideo();
        setLayoutType();
    };
    var scroll2Top = function () {
        $('.scrollTop').click(function(){
            $('body,html').animate({scrollTop:0},600);
            return false;
        });
    };
    var handlePagination = function () {
        $(window).scroll(function() {
            if (pagType == "infinite" && $(document).scrollTop() + $(window).height() > $(document).height() - 80 && pagMoreFlag == 1) {
                getMoreByPagination($(".pagination .more a"));
            }
        });
        $(".pagination .more a").click(function(){
            getMoreByPagination($(this));
            return false;
        });
    };

    var getMoreByPagination = function (_this) {
        pagMoreFlag = 0;
        _this.text("加载中...");
        $.ajax({
            type: "POST",
            url: _this.attr("href"),
            success: function(data){
                result = $(data).find(".post-wrap").children();
                nextHref = $(data).find(".pagination .more a").attr("href");
                // ajax content fadeIn
                $(".post-wrap").append(result.fadeIn(500));
                pagMoreFlag = 1;
                _this.text("加载更多");
                if ( nextHref != undefined ) {
                    _this.attr("href", nextHref);
                } else {
                    // without more articles, remove the pagination navigatino
                    _this.remove();
                    pagMoreFlag = 0;
                    $(".pagination .more").append("加载更多");
                }
                echo.init({
                    offset: 0,
                    throttle: 250,
                    unload: false,
                    callback: function (element, op) {
                    }
                });
            }
        });
    };

    var handleSearch = function () {
        var searchWrap = $(".search-wrap");
        $(".search-button").click(function () {
            searchWrap.toggleClass("search-wrap-animate");
            $(this).toggleClass("rotate-90");
        });
    };
    var showDescendantMenu = function () {
        // show sub/child/grand menu
        $(".menu-wrap .menu-item-has-children").click(function (e) {
            e.stopPropagation();
            if ($(this).hasClass("active")) {
                $(this).children(".sub-menu").slideUp(300);
                $(this).removeClass("active");
            } else {
                $(this).addClass("active");
                $(this).children(".sub-menu").slideDown(300);
            }
        });

        $('.header-menu .menu-item-has-children').bind({
            mouseenter: function() {
                jq(this).children("a").addClass("active");
                jq(this).children('.sub-menu').css({
                    'transform': 'scale3d(1,1,1)',
                    'opacity': 1
                });
            },mouseleave: function() {
                jq(this).children("a").removeClass("active");
                jq(this).children('.sub-menu').css({
                    'transform': 'scale3d(0,0,0)',
                    'opacity': 0
                });
            }
        });
    };
    var showVendor = function () {
        var vendorDom = $(".vendor");
        $(".btn-vendor").click(function () {
            vendorDom.toggleClass("hidden");
        });
    };
    var noticeCarousel = function () {
        var noticeUl = $(".notice-wrap").find("ul");
        var noticeEvent = function () {
            noticeUl.animate({
                "top": "-24px"
            }, 450, function () {
                jq(this).css("top", 0);
                jq(this).append(jq(this).find("li:first"));
            });
        };
        var noticeNumbers = noticeUl.find("li").length;
        if (noticeNumbers > 1) {
            setInterval(noticeEvent, 3000);
        }
    };

    var showSidebar = function () {
        $(".menu-button").click(function(){
            if ($(this).find(".nav-bar").hasClass("nav-bar-animate")) {
                $(this).find(".nav-bar").removeClass("nav-bar-animate");
                $(".menu-wrap").removeClass("open-menu-wrap");
                $(".menu-wrap-backdrop").animate({
                    opacity: 0
                }, 430, function(){
                    $(this).remove();
                });
            } else {
                $(this).find(".nav-bar").addClass("nav-bar-animate");
                $(".menu-wrap").addClass("open-menu-wrap");
                $(this).append("<div class='menu-wrap-backdrop fixed-fluid'></div>")
            }
        });
    };

    var showSupport = function () {
        var supportImg = $(".article-support-img");
        $('.article-support-button .btn').bind({
            mouseenter: function(){
                supportImg.show();
                supportImg.animate({
                    bottom: '46px',
                    opacity: 1
                }, 300);
            }, mouseleave: function() {
                supportImg.animate({
                    bottom: '58px',
                    opacity: 0
                }, 300, function(){
                    $(this).hide();
                });
            }
        });
    };

    var showWechat = function () {
        $(".follow-wechat").bind({
            mouseenter: function () {
                $(this).find('.follow-wechat-popup').show();
                $(this).find('.follow-wechat-popup').animate({
                    bottom: '48px',
                    opacity: 1
                }, 300);
            }, mouseleave: function () {
                $(this).find('.follow-wechat-popup').animate({
                    bottom: '58px',
                    opacity: 0
                }, 300, function () {
                    $(this).hide();
                });
            }
        });
    };

    var handleImgBox = function () {
        if (fancyboxSwitcher && !isHomePage) {
            var siteTitle = $("title").html();
            $(".article-body img, .page-common img").each(function () {
                var alt = this.alt;
                var src = $(this).attr("data-echo");
                if (!alt) {
                    $(this).attr("alt", siteTitle);
                }
                $(this).wrap("<a href='"+ src +"' class='fancybox' rel='fancybox-group' title='"+ alt +"'></a>");
            });

            $(".fancybox").fancybox({
                'padding': 0,
                'opacity': true,
                'cyclic': true
            });
        }
    };
    var handleVideo = function () {
        var articleW = $(".article-body").width();
        var iframeW = $(".article-body iframe").attr("width");
        var iframeH = $(".article-body iframe").attr("height");
        if ($(".article-body iframe").width() == articleW) {
            $(".article-body iframe").height(articleW * iframeH / iframeW - 80);
        }
    };
    var setLayoutType = function () {
        $(".layout-type i").click(function () {
            document.cookie = "layout=" + $(this).data("type");
            window.location.reload(true);
        });
    };
    return {
        init: init
    }
})();
blackcandy.init();

var mainWidth = $('#main').innerWidth();
window.onload = function () {
    $(window).trigger("scroll");
};
$(window).resize(function () {
    mainWidth = jq('#main').innerWidth();
    jq(window).trigger("scroll");
    handleCarousel();
});

$(".vendor-search").click(function () {
    $(".search-button").click();
});

if (isHomePage) {
    var customCarouselDom = $(".custom-carousel");
    customCarouselDom.owlCarousel({
        loop: true,
        margin: 30,
        nav: true,
        items: 1,
        responsive: {
            0:{
                items: 1,
                nav: false
            },
            576:{
                items: 2
            },
            992:{
                items: 3,
                nav:true
            }
        }
    });
}
// carousel
handleCarousel();
function handleCarousel() {
    if (carouselSwitcher && isHomePage) {
        var carouselDom = $("#carousel");
        var margin = 0;
        jq(".carousel-overlay").css("opacity", parseInt(carouselOpacity) / 100);
        if (carouselType == "slide" || carouselType == "image") {
            if (carouselType == "slide") {
                margin = 15;
            }
            carouselDom.find(".carousel-item").each(function() {
                jq(this).innerWidth(mainWidth);
            });
            carouselDom.owlCarousel({
                autoplay: true,
                autoplayTimeout: carouselSpeed,
                smartSpeed: carouselAnimateSpeed,
                loop: true,
                autoplayHoverPause: true,
                navText: '',
                nav : true,
                autoWidth:true,
                center: true,
                margin: margin
            });
        }
        if (carouselType == "one") {
            if (carouselAnimation == "fade") {
                carouselDom.owlCarousel({
                    items: 1,
                    loop: true,
                    autoplay: true,
                    autoplayTimeout: carouselSpeed,
                    smartSpeed: carouselAnimateSpeed,
                    autoplayHoverPause: true,
                    nav : true,
                    navText:'',
                    animateOut: "fadeOut",
                    animateIn: "fadeIn"
                });
            } else {
                carouselDom.owlCarousel({
                    items: 1,
                    loop: true,
                    autoplay: true,
                    autoplayTimeout: carouselSpeed,
                    smartSpeed: carouselAnimateSpeed,
                    autoplayHoverPause: true,
                    nav : true,
                    navText:''
                });
            }

        }

        var carouselNavDom = carouselDom.find(".owl-next, .owl-prev");
        carouselNavDom.addClass("hidden");
        carouselDom.bind({
            mouseenter: function() {
                carouselNavDom.removeClass("hidden");
                jq(this).addClass("hover");
            }, mouseleave: function() {
                carouselNavDom.addClass("hidden");
                jq(this).removeClass("hover");
            }
        });
    }
}

if (carouselSwitcher && carouselMouseSwitcher) {
    var carouselDom = $("#carousel");
    carouselDom.on('mousewheel', '.owl-stage', function (e) {
        if (e.deltaY>0) {
            carouselDom.trigger('http://www.bitett.com/template/kuzhan_ht/public/js/next.owl');
        } else {
            carouselDom.trigger('http://www.bitett.com/template/kuzhan_ht/public/js/prev.owl');
        }
        e.preventDefault();
    });
}
