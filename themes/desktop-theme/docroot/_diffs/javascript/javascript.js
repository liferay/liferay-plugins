var LiferayDesktop = function () {
	var $ = jQuery;

	return {

		/* ---------- Init Functions ---------- */

		initVistaMenu: function() {
			var instance = this;

			if (jQuery(document.body).is('.vista')) {
				Liferay.Util.createFlyouts({
					container: jQuery('.navigation-menu')[0],
					mouseOver: function(event) {
						if (this.className.indexOf('my-places') > -1) {
							jQuery('.current-community > ul', this).show();
						}
						else if (this.parentNode.className.indexOf('taglib-my-places') > -1) {
							jQuery('ul', this.parentNode).hide();
							jQuery('> ul', this).show();
						}
					}
				});
			}
		},
		initIE6Fix: function() {
			if (jQuery('.ie6').size() > 0) {
				jQuery(".tabs li").css({"position":"relative"});
			}
		},

		/* ---------- Non-Free-Form Layouts - Body Scrollbar Function ---------- */

		nonFreeFormLayout: function() {
			var mainContainer = $("#main-container");
			var mainContent = $("#main-content");
			var sidebarPosition = "fixed"

			var height = 0;
			var width = 0;

			var containerSpacer = 0;
			var scrollbarSpacer = 17;

			height = document.documentElement.offsetHeight;
			width = document.documentElement.offsetWidth;

			if ($('.ie6').size() > 0) {
				height = document.documentElement.offsetHeight;
				width = document.documentElement.offsetWidth;

				containerSpacer = 4;
				scrollbarSpacer = 21;
				sidebarPosition = "absolute";
			}
			else if ($('.ie').size() > 0) {
				height = document.body.offsetHeight;
				width = document.body.offsetWidth;
			}
			else {
				height = window.innerHeight;
				width = window.innerWidth;
			}

			var dock = $("#dock-wrapper");
			var sidebar = $("#sidebar-wrapper");

			mainContainer.css({"height":height-dock.height()-containerSpacer+"px","width":width-containerSpacer+"px","overflow":"auto","position":"relative"});
			sidebar.css({"position":sidebarPosition});

			if ((height < mainContent.height()) && (width < mainContent.width())) {
				mainContent.css({"height":mainContent.height()-scrollbarSpacer+"px"});
				mainContent.css({"width":mainContent.width()-scrollbarSpacer+"px"});

				if ($('.ie6').size() == 0) {
					sidebar.css({"right":scrollbarSpacer+"px"});
				}
			}
			else if (height < mainContent.height()) {
				mainContent.css({"height":mainContent.height()+"px"});
				mainContent.css({"width":width-scrollbarSpacer+"px"});

				if ($('.ie6').size() == 0) {
					sidebar.css({"right":scrollbarSpacer+"px"});
				}
			}
			else if (width < mainContent.width()) {
				mainContent.css({"height":height-scrollbarSpacer+"px"});
				mainContent.css({"width":mainContent.width()+"px"});

				if ($('.ie6').size() == 0) {
					sidebar.css({"right":""});
				}
			}
			else {
				mainContent.css({"height":""});
				mainContent.css({"width":""});

				if ($('.ie6').size() == 0) {
					sidebar.css({"right":""});
				}
			}

			if ($('.ie6').size() > 0) {
				mainContainer.scroll(function () { 
					var offsetTop = mainContainer.scrollTop();
					var offsetRight = sidebar.css("right");

					if ((mainContent.height() > mainContainer.height()) && (offsetTop > mainContent.height()-sidebar.height())) {
						offsetTop = mainContent.height()-sidebar.height();
					}

					if (offsetRight == "-1px") {
						offsetRight = "0px";
					}
					else {
						offsetRight = "-1px";
					}

					sidebar.css({"top":offsetTop+"px"});
					sidebar.css({"right":offsetRight});
				});
			}
		},

		/* ---------- Portlet Maximized - Portlet Scrollbar Function ---------- */

		portletMaxResize: function() {
			var portletScrollbars = $('#portlet-scrollbars');
			var portletContainer = $('.portlet-content-container');
			var height = 0;
			var width = 0;
			var innerWidth = 0;

			var taskbarHeight = 0;
			var portletTopper = 0;

			// Color Scheme
			if (jQuery(document.body).is('.osx')) {
				taskbarHeight = 23;
				portletTopper = 76;
			}
			else {
				taskbarHeight = 28;
				portletTopper = 59;
			}

			if ($('.ie6').size() > 0) {
				height = document.documentElement.offsetHeight-taskbarHeight-portletTopper-21-20+15;
				width = document.documentElement.offsetWidth-21-21-20+15;
			}
			else if ($('.ie').size() > 0) {
				height = document.body.offsetHeight-taskbarHeight-portletTopper-21-20+15;
				width = document.body.offsetWidth-21-21-20+15;
			}
			else {
				height = window.innerHeight-taskbarHeight-portletTopper-21-20+15;
				width = window.innerWidth-21-21-20+15;
			}

			// If vertical scollbar appears, resize width to remove horizontal sidebar
			if (portletContainer.height()+20 >= height) {
				innerWidth = width-20-17;
				portletContainer.css({width:innerWidth+"px"});
			}
			else {
				innerWidth = width-20;
				portletContainer.css({width:innerWidth+"px"});
			}

			portletScrollbars.css({height:height+"px",width:width+"px",'overflow':'auto','position':'relative'});
		},

		/* ---------- Desktop Sidebar - Add Content Functions ---------- */

		addContentInit: function() {
			var sidebar = $("#sidebar");
			var sidebarWrapper = $('#sidebar-wrapper');

			if (sidebarWrapper.size() == 1) {
				sidebarWrapper.prepend('<div id="sidebar-link"><a href="javascript: LiferayDesktop.addContentAnimate();"></a></div>');
				sidebarWrapper.css({'display':''});

				var plid = sidebar.attr('plid');
				var ppid = sidebar.attr('ppid');
				var doAsUserId = sidebar.attr('doasuserid');

				if (doAsUserId == undefined) {
					doAsUserId = '';
				}

				DesktopAddContent.show(plid,ppid,doAsUserId);
			}
		},
		addContentAnimate: function() {
			var sidebarWrapper = $("#sidebar-wrapper");
			var sidebar = $('#sidebar');
			var arrow = $('#sidebar-arrow');
			var link = $('#sidebar-link a');
			var clock = $('#sidebar-top .clock');
			var calendar = $('#sidebar-top .calendar');
			arrow.remove();

			if (link.is('.expanded')) {
				clock.css({'display':''});
				sidebarWrapper.animate({width:'20px'}, 600,'linear');
				sidebar.animate({opacity:0}, 600,'linear',function(){sidebar.css({'display':'none'});});
				calendar.animate({opacity:0}, 200,'linear');
				link.removeClass('expanded');
			}
			else {
				calendar.css({'display':'block'});
				sidebar.css({'display':'block'});
				sidebarWrapper.animate({width:'270px'}, 600,'linear',function(){clock.css({'display':'block'});});
				sidebar.animate({opacity:1}, 600,'linear');
				link.addClass('expanded');
				calendar.animate({opacity:1}, 1000,'linear');
			}
		},
		addPortlet: function(portletId) {
			var portletContainer = jQuery("#p_p_id_" + portletId + '_');
			var portletTopper = portletContainer.find(".portlet-topper");
			var taskbarLink =  jQuery('#p_p_id_' + portletId + '_taskbar');

			if (taskbarLink.size() == 0) {
				LiferayDesktop.addTaskbarLink(portletId);
				LiferayDesktop.selectTaskbarLink(portletId);
				LiferayDesktop.addPortletGroupName(portletId);

				jQuery(".portlet-content").wrap("<div id='portlet-scrollbars'></div>");
				jQuery(".taglib-search-iterator").wrap("<div class='taglib-search-iterator-wrapper'></div>");
				jQuery(".portlet-content-container").css({'overflow':''});

				portletContainer.click(
					function() {
						LiferayDesktop.selectTaskbarLink(portletId);
					}
				);

				portletTopper.hover(
					function() {
						LiferayDesktop.portletModesHover(portletId);
					},
					function() {
						LiferayDesktop.portletModesHoverOut(portletId);
					}
				);

				if ($('.ie6').size() > 0) {
					LiferayDesktop.initIE6();
				}
			}
		},

		/* ---------- Portlet Title - Get Group Name Function ---------- */

		addPortletGroupName: function(portletId) {
			var group = $("li.current-community h3 a").text();

			if (group != '') {
				var portletGroupName = $('#p_p_id_' + portletId + '_ #portlet-group-name');
				var portletGroupArrow = $('#p_p_id_' + portletId + '_ .portlet-group-name');
				portletGroupName.append(group);
				portletGroupArrow.css({'display':'inline'});
			}
		},
		
		/* ---------- Desktop Taskbar - Portlet Link Functions ---------- */

		initSelectTaskbarLink: function() {
			if ($("#content-wrapper").is(".freeform")) {
				var container = $("#layout-column_column-1");
				var portlet = container.find("div.portlet-boundary:last");
				var selectTaskbarLink = $("#" + portlet.attr('id') + "taskbar");
				selectTaskbarLink.addClass("selected");
			}
		},
		clearSelectTaskbarLink: function(event) {
			var $target = $(event.target);

			if ($target.is("#wrapper")) {
				var selectedTaskbarLink = $(".taskbar-link.selected");
				selectedTaskbarLink.removeClass('selected');
			}
		},
		selectTaskbarLink: function(portletId) {
			if ($("#content-wrapper").is(".freeform") && (portletId != '')) {
				var selectedTaskbarLink = $(".taskbar-link.selected");
				var taskbarLink =  $('#p_p_id_' + portletId + '_taskbar');
				selectedTaskbarLink.removeClass('selected');
				taskbarLink.addClass("selected");

				// Check if user removed portlet
				if (taskbarLink.size() == 1) {
					var container = $("#layout-column_column-1");
					var portletLink = container.find("div.portlet-boundary:last").attr('id');
					var selectTaskbarLink = 'p_p_id_' + portletId + '_';

					if (selectTaskbarLink != portletLink) {
						LiferayDesktop.restorePortlet('',portletId,'','');
					}
				}
			}
		},
		addTaskbarLink: function(portletId) {
			var checkTaskbarLink = $('#p_p_id_' + portletId + '_taskbar');

			if (checkTaskbarLink.size() == 0) {
				var taskbar = $('#taskbar-portlets');
				var portlet = $('#p_p_id_' + portletId + '_');
				var portletMin = portlet.find("span.portlet-min a");

				var titleImgStr = portlet.find("span.portlet-title img").attr("src");
				var titleText = $.trim(portlet.find("span.portlet-title").text());

				// Default LEP portlet
				if (titleImgStr != null) {
					titleText = "<img src='" + titleImgStr + "'/>" + titleText;
				}
				// For borderless and custom portlets
				else {
					var borderlessTitle = $('#p_p_id_' + portletId + '_');

					if (borderlessTitle.size() == 1) {
						borderlessTitle = borderlessTitle.attr('class').split(" ");
						borderlessTitle = borderlessTitle[borderlessTitle.length-1];
						borderlessTitle = borderlessTitle.replace('portlet-','');
						borderlessTitle = borderlessTitle.replace('-',' ');
						borderlessTitle = borderlessTitle.replace(/\w+/g, function(a){return a.charAt(0).toUpperCase() + a.substr(1).toLowerCase();});

						titleText = borderlessTitle;
					}
					else {
						titleText = 'Customized Portlet';
					}
				}

				titleText = "<span class='taskbar-link-title'>" + titleText + "</span>";

				if (portletMin.is(".min")) {
					var restoreHtmlStr = portletMin.attr('href');
					restoreHtmlStr = restoreHtmlStr.replace("minimizePortlet", "restorePortlet");
				}
				else {
					var restoreHtmlStr = "javascript: LiferayDesktop.restorePortlet('','" + portletId + "','','');";
				}

				taskbar.append('<li id="p_p_id_' + portletId + '_taskbar" class="taskbar-link"><a href="' + restoreHtmlStr + '">' + titleText + '</a></li>');

				if (!jQuery(document.body).is('.osx')) {
					var taskbarLinks = $('#taskbar-portlets .taskbar-link');
					var count = taskbarLinks.size();
					var width = 100/count;
					var taskbarWrapper = $("#taskbar-portlets-wrapper");
					var totalWidth = taskbarWrapper.width()-52-10;

					if (count*200 < totalWidth) {
						taskbarLinks.css({"width":"200px"});
					}
					else {
						taskbarLinks.css({'width':width + '%'});
					}
				}
			}
		},

		/* ---------- Portlet Mode Icons - Hover Function ---------- */

		portletModesHover: function(portletId) {
			var portletModes = $('#p_p_id_' + portletId + '_ .portlet-modes');
			portletModes.css({"display":"block"});
		},
		portletModesHoverOut: function(portletId) {
			var portletModes = $('#p_p_id_' + portletId + '_ .portlet-modes');
			portletModes.css({"display":""});
		},

		/* ---------- Portlet - Window State Functions ---------- */

		minimizePortlet: function(plid,portletId,doAsUserId,pathMain) {
			var portlet = $('#p_p_id_' + portletId + '_');
			portlet.css({display:"none"});

			AjaxUtil.request(pathMain + '/portal/update_layout' + '?p_l_id=' + plid + '&p_p_id=' + portletId + '&p_p_restore=' + 'false' + '&doAsUserId=' + doAsUserId + '&cmd=minimize',
				{
					onComplete: '',
					returnArgs: ''
				}
			);
		},
		restorePortlet: function(plid,portletId,doAsUserId,pathMain) {
			var portlet = $('#p_p_id_' + portletId + '_');
			var parent = portlet.parent();

			if ($("#content-wrapper").is(".freeform")) {
				portlet.appendTo("#" + parent.attr('id'));
				portlet.css({display:""});
			}
			else {
				portlet.css({display:""});
			}

			var selectTaskbarLink = $(".taskbar-link.selected").attr('id');
			var portletLink = 'p_p_id_' + portletId + '_taskbar';

			if (selectTaskbarLink != portletLink) {
				LiferayDesktop.selectTaskbarLink(portletId);
			}

			if (plid != '') {
				AjaxUtil.request(pathMain + '/portal/update_layout' + '?p_l_id=' + plid + '&p_p_id=' + portletId + '&p_p_restore=' + 'true' + '&doAsUserId=' + doAsUserId + '&cmd=minimize',
					{
						onComplete: '',
						returnArgs: ''
					}
				);
			}
		},
		removePortlet: function(plid,portletId,doAsUserId) {
			var taskbarLink = $('#p_p_id_' + portletId + '_taskbar');
			taskbarLink.remove();

			if (!jQuery(document.body).is('.osx')) {
				var taskbarLinks = $('#taskbar-portlets .taskbar-link');
				var count = taskbarLinks.size() - 1;

				if (count < 1) {
					count = 1;
				}

				var taskbarWrapper = $("#taskbar-portlets-wrapper");
				var totalWidth = taskbarWrapper.width()-52-10;

				if (count*200 < totalWidth) {
					taskbarLinks.css({"width":"200px"});
				}
				else {
					taskbarLinks.css({'width':100/count + '%'});
				}
			}

			closePortlet(plid, portletId, doAsUserId,'true');
		}
	};
}();

jQuery(document).ready(

	/*
	This function gets loaded when all the HTML, not including the portlets, is
	loaded.
	*/

	function() {
		LiferayDesktop.initIE6Fix();
		LiferayDesktop.initVistaMenu();
		LiferayDesktop.addContentInit();

		if (jQuery(".columns-max").size() > 0) {
			var resizeTimer = null;

			LiferayDesktop.portletMaxResize();
			jQuery(window).bind('resize', function() {
				if (resizeTimer) clearTimeout(resizeTimer);
				resizeTimer = setTimeout(function() {LiferayDesktop.portletMaxResize();}, 50);
			});
		}

		if ((jQuery(".freeform").size() == 0) && 
				(jQuery(".columns-max").size() == 0) &&
				(jQuery("#portlet-wrapper-sign-in").size() == 0)) {
			var resizeTimer = null;

			LiferayDesktop.nonFreeFormLayout();
			jQuery(window).bind('resize', function() {
				if (resizeTimer) clearTimeout(resizeTimer);
				resizeTimer = setTimeout(function() {LiferayDesktop.nonFreeFormLayout();}, 50);
			});
		}
	}
);

Liferay.Portlet.ready(

	/*
	This function gets loaded after each and every portlet on the page.

	portletId: the current portlet's id
	jQueryObj: the jQuery wrapped object of the current portlet
	*/

	function(portletId, jQueryObj) {
		var taskbarLink =  jQuery('#p_p_id_' + portletId + '_taskbar');
		var portletContainer = jQuery('#p_p_id_' + portletId + '_');

		if ((taskbarLink.size() == 0) && (portletContainer.size() != 0)) {
			LiferayDesktop.addTaskbarLink(portletId);
			LiferayDesktop.addPortletGroupName(portletId);

			var portletBoundary = jQuery('#p_p_id_' + portletId + '_');
			portletBoundary.click(function() {LiferayDesktop.selectTaskbarLink(portletId);});

			var portletTopper = jQueryObj.find(".portlet-topper");
			portletTopper.hover(function() {LiferayDesktop.portletModesHover(portletId);},function() {LiferayDesktop.portletModesHoverOut(portletId);});
		}
	}
);

jQuery(document).last(

	/*
	This function gets loaded when everything, including the portlets, is on
	the page.
	*/

	function() {
		LiferayDesktop.initSelectTaskbarLink();

		jQuery("body").click(function(event) {LiferayDesktop.clearSelectTaskbarLink(event);});
		jQuery(".portlet-content").wrap("<div id='portlet-scrollbars'></div>");
		jQuery(".taglib-search-iterator").wrap("<div class='taglib-search-iterator-wrapper'></div>");
		jQuery(".portlet-content-container").css({'overflow':''});
	}
);