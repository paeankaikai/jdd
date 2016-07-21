
// for div reizse;
(function($,h,c){var a=$([]),e=$.resize=$.extend($.resize,{}),i,k="setTimeout",j="resize",d=j+"-special-event",b="delay",f="throttleWindow";e[b]=250;e[f]=true;$.event.special[j]={setup:function(){if(!e[f]&&this[k]){return false}var l=$(this);a=a.add(l);$.data(this,d,{w:l.width(),h:l.height()});if(a.length===1){g()}},teardown:function(){if(!e[f]&&this[k]){return false}var l=$(this);a=a.not(l);l.removeData(d);if(!a.length){clearTimeout(i)}},add:function(l){if(!e[f]&&this[k]){return false}var n;function m(s,o,p){var q=$(this),r=$.data(this,d);r.w=o!==c?o:q.width();r.h=p!==c?p:q.height();n.apply(this,arguments)}if($.isFunction(l)){n=l;return m}else{n=l.handler;l.handler=m}}};function g(){i=h[k](function(){a.each(function(){var n=$(this),m=n.width(),l=n.height(),o=$.data(this,d);if(m!==o.w||l!==o.h){n.trigger(j,[o.w=m,o.h=l])}});g()},e[b])}})(jQuery,this);

var extend = {};

extend.ex = {};
extend.ex.msg = "系统出错,请刷新页面重试或联系管理员!";
extend.ex.show = function() { extend.msgBox.alert( {message : extend.ex.msg} ); };

// msgBox
extend.msgBox = function () {
	var _init = function() {
		if ($("#msgBox").length == 0) { _create(); };
	};
	var _create = function() {
		var html = "<div id=\"msgBox\" class=\"modal fade\" role=\"dialog\" style=\"z-index:9998\">"
		  + "<div class=\"modal-dialog\">"
          + "<div class=\"modal-content\">"
          + "<div class=\"modal-header\">"
          + "<div class=\"popTitles popTitles-warn\">"
          + "<h4 id=\"msgbox_title\" class=\"modal-title\"></h4>"
          + "<i class=\"btn_popClose\" data-dismiss=\"modal\"></i>" 
          + "</div>"
          + "</div>"
          + "<div class=\"modal-body\" style=\"min-height:30px; height:auto;\">"
          + "<h3 id=\"msgbox_body\" style=\"font-weight:normal;\">Modal body</h3>"
          + "</div>"
          + "<div class=\"modal-footer\">"
          + "</div>"
          + "</div>"
          + "</div>";
		$("body:eq(0)").append(html);
	};
	var _show = function() {
		$("#msgBox").modal("show");
	};
	var _close = function() {
		$("#msgBox").modal("hide");
	};

	return {
		init : function() {
			_init();
		},
		alert : function(option) {
		    if (!option) { option = {}; };
            $("#msgBox #msgbox_body").html(option.message || "");
            $("#msgBox #msgbox_title").html(option.title || "温馨提示");
            $("#msgBox .modal-footer").html("<div class=\"btnCenterBox\">"
                    + "<span class=\"popBtn popBtn_blue\">确定</span></div>");
            $("#msgBox .modal-footer .popBtn_blue").bind("click", _close);
            
            $("#msgBox").css("width", option.width || "600px");
            if (option.width && option != "") {
            	$("#msgBox").css("margin-left","0px");
                var windowWidth = $(window).width();
                var optionWidth = option.width;
                optionWidth = optionWidth.substring(0, optionWidth.length - 2);
                windowWidth = (windowWidth - optionWidth) / 2;
                var leftWidth = windowWidth +"px";
                $("#msgBox").css("left", leftWidth);
            } else {
            	$("#msgBox").css("margin-left","0px");
                var windowWidth = $(window).width();
                var optionWidth = 600;
             	windowWidth = (windowWidth - optionWidth) / 2;
                var leftWidth = windowWidth +"px";
                $("#msgBox").css("left", leftWidth);
            }
			_show();
		},
		confirm : function(option) {
            if (!option) { option = {}; };
            $("#msgBox #msgbox_body").html(option.message || "");
            $("#msgBox #msgbox_title").html(option.title || "温馨提示");
            $("#msgBox .modal-footer").html("<div class=\"btnCenterBox\">"
                    + "<span class=\"popBtn popBtn_blue\">确定</span>"
                    + "<span class=\"popBtn popBtn_grey\">取消</span></div>");
            if (option.cancelFun) {
                $("#msgBox .modal-footer .popBtn_grey").bind("click", option.cancelFun );
            }
            if (option.okFun) {
                $("#msgBox .modal-footer .popBtn_blue").bind("click", option.okFun );
            }
            $("#msgBox .modal-footer .popBtn_grey").bind("click", _close);
            $("#msgBox .modal-footer .popBtn_blue").bind("click", _close);
            
            $("#msgBox").css("width", option.width || "600px");
            if (option.width && option != "") {
            	$("#msgBox").css("margin-left","0px");
                var windowWidth = $(window).width();
                var optionWidth = option.width;
                optionWidth = optionWidth.substring(0, optionWidth.length - 2);
                windowWidth = (windowWidth - optionWidth) / 2;
                var leftWidth = windowWidth +"px";
                $("#msgBox").css("left", leftWidth);
            } else {
            	$("#msgBox").css("margin-left","0px");
                var windowWidth = $(window).width();
                var optionWidth = 600;
             	windowWidth = (windowWidth - optionWidth) / 2;
                var leftWidth = windowWidth +"px";
                $("#msgBox").css("left", leftWidth);
            }
            
            _show();
		},
		close : function() {
			_close();
		}
	};
}();

//dialog
extend.dialog = function () {
    
    var _init = function () {
        if ($("#dialogBox").length == 0) { _create(); };
    };
    
    var _create = function() {
        var html = "<div id=\"dialogBox\" class=\"modal fade\" role=\"dialog\">"
          + "<div class=\"modal-dialog\">"
          + "<div class=\"modal-content\">"
          + "<div class=\"modal-header\">"
          + "<div class=\"popTitles popTitles-normal\">"
          + "<h4 id=\"dialogBox_title\" class=\"modal-title\"></h4>"
          + "<i class=\"btn_popClose\" data-dismiss=\"modal\"></i>" 
          + "</div>"
          + "</div>"
          + "<div class=\"modal-body\">"
          + "<div class=\"body-context\"></div>"
          + "</div>"
          + "<div class=\"modal-footer\">"
          + "</div>"
          + "</div>"
          + "</div>";
        $("body:eq(0)").append(html);
    };
    
    var _show = function() {
        $("#dialogBox").modal("show");
    };
    
    var _close = function() {
        $("#dialogBox").modal("hide");
    };
    
    return {
        init : function() {
            _init();
        }
        , show : function(option) {
        
            if (!option) { option = {}; };
            
            $("#dialogBox #dialogBox_title").html(option.title || "JDD | JDD");

            $("#dialogBox .modal-body").empty();
            $("#dialogBox .modal-body").append( $("<div></div>").addClass("body-context") );
            $("#dialogBox .body-context").append( $(option.body).css("display", "block") );

            $("#dialogBox .modal-footer").empty();
            var buttons = option.buttons || [];
            if (buttons.length > 0) {
                
                $("#dialogBox .modal-footer").html("<div class=\"btnRightBox\"></div>");
                for (var i in buttons) {
                   var display = buttons[i].display || "按钮";
                    var displayCss = buttons[i].displayCss || "btnOrange";
                    var clickFun = buttons[i].click || _close();
                    
                    var button = $("<span></span>");
                    button.html(display);
                    button.addClass("popBtn");
                    button.addClass(displayCss);
                    button.bind("click", clickFun);
                    button.appendTo($("#dialogBox .btnRightBox"));
                }
            }

            $("#dialogBox").css("width", option.width || "");

            if (option.width && option != "") {
            	$("#dialogBox").css("margin-left","0px");
                var windowWidth = $(window).width();
                var optionWidth = option.width;
                optionWidth = optionWidth.substring(0, optionWidth.length - 2);
                windowWidth = (windowWidth - optionWidth) / 2;
                var leftWidth = windowWidth +"px";
                $("#dialogBox").css("left", leftWidth);
            } else {
            	$("#dialogBox").css("margin-left","0px");
                var windowWidth = $(window).width();
                var optionWidth = 600;
             	windowWidth = (windowWidth - optionWidth) / 2;
                var leftWidth = windowWidth +"px";
                $("#dialogBox").css("left", leftWidth);
            }
            
            _show();
        }
        , close : function() {
            _close();
        }
        , showMsg : function(msg) {
            var html = "<div class=\"alert alert-error \">"
                + "<a class=\"close\" data-dismiss=\"alert\">×</a>"
                + "<strong>提示:&nbsp&nbsp&nbsp</strong>" + msg
                + "</div>";
            $("#dialogBox .modal-body").children(":first").before($(html));
        }
        , showEx : function() {
            $(".modal-body .alert").remove();
            var html = "<div class=\"alert alert-error \">"
                + "<a class=\"close\" data-dismiss=\"alert\">×</a>"
                + "<strong>提示:&nbsp&nbsp&nbsp</strong>" + extend.ex.msg
                + "</div>";
            $("#dialogBox .body-context").children(":first").before($(html));
        }
        , clearMsg : function() {
            $("#dialogBox .modal-body .alert").remove();
        }

    };
}();
