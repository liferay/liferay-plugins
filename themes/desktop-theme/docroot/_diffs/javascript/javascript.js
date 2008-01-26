/* ---------- Functions for jQuery(document).ready ---------- */
Liferay.DesktopTheme = {};

Liferay.DesktopTheme.clock = function() {
	var updateClock;
	return {
		init: function() {
			var clock = new Date();
			var month, day, dayNum, h, m, meridiem, time;
			var days = new Array('Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat');
			var months = new Array('Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec');

			day = days[clock.getDay()];
			dayNum = clock.getDate();
			month = months[clock.getMonth()];
			h = clock.getHours() == 0 ? 12 : (clock.getHours() > 12 ? clock.getHours() - 12 : clock.getHours());
			m = (clock.getMinutes() < 10 ? "0" : "") + clock.getMinutes();
			meridiem = clock.getHours() < 12 ? "AM" : "PM";

			if (document.getElementById("taskbar-clock-vista") != null) {
				time = h + ":" + m + " " + meridiem;
				el = document.getElementById("taskbar-clock-vista");
			} else if (document.getElementById("taskbar-clock-mac") != null) {
				time = day + " " + h + ":" + m + " " + meridiem;
				el = document.getElementById("taskbar-clock-mac");
			} else if (document.getElementById("taskbar-clock-linux") != null) {
				time = day + " " + month + " " + dayNum +", " + h + ":" + m + " " + meridiem;
				el = document.getElementById("taskbar-clock-linux");
			}
			else {
				time = day + " " + month + " " + dayNum +", " + h + ":" + m + " " + meridiem;
				el = document.getElementById("taskbar-clock");
			}

			if(!el.childNodes.length){
				el.appendChild(document.createTextNode(""));
			};
			el.childNodes[0].nodeValue=time;

			updateClock = setTimeout("Liferay.DesktopTheme.clock.init()", 5000);
		},
		resetTimer: function() {
			clearTimeout(updateClock);
		}
	};
}();

Liferay.DesktopTheme.dockMenu = function() {
	return {
		init: function() {
			var instance = this;

			if (jQuery(".linux").size() >= 1) {
				menuColor = '#0a246a';
				highlight = '#3DA5D5';
			} else if (jQuery(".mac").size() >= 1) {
				menuColor = '#336eca';
				highlight = '#3DA5D5';
			} else {
				menuColor = '#074f67';
				highlight = '#3DA5D5';
			}

			instance.placesInit();
			instance.navMenu = jQuery('ul.lfr-dock-list > li.home');
			instance.navSubmenu = jQuery('ul.nav-dock-list > li');
			instance.placesMenu = jQuery('ul.lfr-dock-list > li.places');
			instance.placesSubmenu = jQuery('ul.lfr-dock-list > li.places > ul > li');
			instance.navMenu.hover(instance.navHover,instance.navHoverOut);
			instance.navSubmenu.hover(instance.navHover,instance.navHoverOut);
			instance.placesMenu.hover(instance.navHover,instance.navHoverOut);
			instance.placesSubmenu.hover(instance.placesHover,instance.placesHoverOut);

		},
		placesInit: function() {
			var li = jQuery('ul.lfr-dock-list > li.places > ul > li');
			var childH3 = li.find('> h3');
			li.append(jQuery('<div class="submenu-arrow-black"></div>'));
			jQuery("li:has(.current)").find('> h3').css({ "background-color":highlight,color:"#ffffff" });
		},
		navHover: function() {
			var li = jQuery(this);
			var childLink = li.find('> a');
			var selectedLink = li.find('.selected');
			var item = li.find('> ul');
			childLink.css({ "background-color":menuColor,color:"#ffffff" });
			selectedLink.css({ "background-color":"",color:"" });
			item.append(jQuery('<div class="submenu-arrow-white"></div>'));
			item.css({ display:"block" });
		},
		navHoverOut: function() {
			var li = jQuery(this);
			var childLink = li.find('> a');
			var item = li.find('> ul');
			childLink.css({ "background-color":"",color:"" });
			item.find('div:last').remove();
			item.css({ display:"none" });
		},
		placesHover: function() {
			var li = jQuery(this);
			var childH3 = li.find('> h3');
			var item = li.find('> ul');
			childH3.css({ "background-color":menuColor,color:"#ffffff" });
			li.find('div:last').css({ display:"none" });
			item.append(jQuery('<div class="submenu-arrow-white"></div>'));
			item.css({ display:"block" });
		},
		placesHoverOut: function() {
			var li = jQuery(this);
			var childH3 = li.find('> h3');
			var item = li.find('> ul');
			childH3.css({ "background-color":"",color:"" });
			li.find('div:last').css({ display:"block" });
			item.find('div:last').remove();
			item.css({ display:"none" });
			jQuery("li:has(.current)").find('> h3').css({ "background-color":highlight,color:"#ffffff" });
		}
	};
}();

Liferay.DesktopTheme.portletMax = function() {
	return {
		init: function() {
			var instance = this;

			if (jQuery(".linux").size() >= 1) {
				offsetHeight = 180;
			} else if (jQuery(".mac").size() >= 1) {
				offsetHeight = 160;
			} else {
				offsetHeight = 180;
			}

			if (jQuery(".columns-max").size() == 1) {
				var portletMax = jQuery(instance).find('.portlet-content-container > div');
				if((navigator.userAgent.toLowerCase().indexOf("msie")) != -1){
					var widthSize = jQuery(instance).find('.portlet-boundary');
					temp=navigator.appVersion.split("MSIE");
					version=parseFloat(temp[1]);
					if (version < 7.0) {
						portletMax.css({ height:document.body.offsetHeight-offsetHeight,width:document.body.offsetWidth-89,overflow:"scroll","overflow-x":"hidden" });
						widthSize.css({ width:document.body.offsetWidth-17 });
					} else {
						portletMax.css({ height:document.body.offsetHeight-offsetHeight,overflow:"auto","overflow-x":"hidden" });
						widthSize.css({ width:document.body.offsetWidth });
					}

				} else {
					portletMax.css({ height:document.body.clientHeight-offsetHeight,overflow:"auto" });
				}

			}
		}
	};
}();

Liferay.DesktopTheme.nonFreeformLayout = function() {
	return {
		init: function() {
			var instance = this;

			if ((jQuery(".freeform").size() == 0) && (jQuery(".columns-max").size() == 0)) {
				var scrollbar = jQuery("#wrapper > #content-wrapper");

				if((navigator.userAgent.toLowerCase().indexOf("msie")) != -1){
					scrollbar.css({ height:document.body.offsetHeight-28,width:document.body.offsetWidth-18,"padding-right":"18px",position:"relative",overflow:"scroll","overflow-x":"hidden" });
				} else {
					scrollbar.css({ height:document.body.clientHeight-28,overflow:"auto" });
				}
			}
		}
	};
}();

Liferay.DesktopTheme.fixDock = function() {
	Liferay.Util.actsAsAspect(Liferay.Dock);
	Liferay.Dock.after('init',
		function() {
			var dock = jQuery('.lfr-dock');
			dock.css('position', 'fixed');
			
		}
	);
};

/* ---------- Main jQuery Calls ---------- */

jQuery(document).ready(

	/*
	This function gets loaded when all the HTML, not including the portlets, is
	loaded.
	*/

	function() {
		Liferay.DesktopTheme.clock.init();
		window.onunload = Liferay.DesktopTheme.clock.resetTimer;
		Liferay.DesktopTheme.dockMenu.init();
		Liferay.DesktopTheme.portletMax.init();
		Liferay.DesktopTheme.nonFreeformLayout.init();
		Liferay.DesktopTheme.fixDock();
	}
);

Liferay.Portlet.ready(

	/*
	This function gets loaded after each and every portlet on the page.

	portletId: the current portlet"s id
	jQueryObj: the jQuery wrapped object of the current portlet
	*/

	function(portletId, jQueryObj) {
	}
);

jQuery(document).last(

	/*
	This function gets loaded when everything, including the portlets, is on
	the page.
	*/

	function() {
	}
);