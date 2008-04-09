LiferayDesktop = {};

LiferayDesktop.clock = function() {
	var updateClock = null;

	return {
		init: function() {
			var instance = this;
			
			instance.updateTime();
			if (!updateClock) {
				updateClock = setInterval(
					function() {
						instance.updateTime();
					}, 5000);
			}
		},

		resetTimer: function() {
			clearTimeout(updateClock);
		},
		
		updateTime: function() {
			var instance = this;

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

			time = day + " " + month + " " + dayNum +", " + h + ":" + m + " " + meridiem;
			
			var clock = jQuery('#taskbar-clock');
			var body = jQuery(document.body);
			
			if (body.is('.vista')) {
				time = h + ":" + m + " " + meridiem;
			}
			else if (body.is('.osx')) {
				time = day + " " + h + ":" + m + " " + meridiem;
			}
			else {
				time = day + " " + month + " " + dayNum +", " + h + ":" + m + " " + meridiem;
			}

			clock.text(time);
		}
	};
}();

LiferayDesktop.dockMenu = function() {
	return {
		init: function() {
			var instance = this;
			
			if (!jQuery(document.body).is('.vista')) {
				return;
			}

			Liferay.Util.createFlyouts(
				{
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
				}
			);
			
			return;	


			if (jQuery(".vista").size() >= 1) {
				menuColor = '#074f67';
				highlight = '#3DA5D5';
			} else if (jQuery(".osx").size() >= 1) {
				menuColor = '#336eca';
				highlight = '#3DA5D5';
			} else {
				menuColor = '#0a246a';
				highlight = '#3DA5D5';
			}

			instance.placesInit();
			instance.navMenu = jQuery('ul.lfr-dock-list > li.home');
			instance.navSubmenu = jQuery('ul.nav-dock-list > li');
			instance.placesMenu = jQuery('ul.lfr-dock-list > li.my-places');
			instance.placesSubmenu = jQuery('ul.lfr-dock-list > li.my-places > ul > li');
			instance.navMenu.hover(instance.navHover,instance.navHoverOut);
			instance.navSubmenu.hover(instance.navHover,instance.navHoverOut);
			instance.placesMenu.hover(instance.navHover,instance.navHoverOut);
			instance.placesSubmenu.hover(instance.placesHover,instance.placesHoverOut);
			
			
		},
		placesInit: function() {
			var li = jQuery('ul.lfr-dock-list > li.my-places > ul > li');
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

LiferayDesktop.portletMax = function() {
	return {
		init: function() {
			var instance = this;

			if (jQuery(".osx").size() >= 1) {
				offsetHeight = 160;
			}
			else if (jQuery(".vista").size() >= 1) {
				offsetHeight = 180;
			}
			else {
				offsetHeight = 150;
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

LiferayDesktop.nonFreeformLayout = function() {
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

LiferayDesktop.fixDock = function() {
	Liferay.Util.actsAsAspect(Liferay.Dock);
	Liferay.Dock.after('init',
		function() {
			var dock = jQuery('.lfr-dock');
			dock.css('position', 'fixed');
		}
	);
};

jQuery(document).ready(

	/*
	This function gets loaded when all the HTML, not including the portlets, is
	loaded.
	*/

	function() {
		LiferayDesktop.clock.init();
		 LiferayDesktop.dockMenu.init(); 
		LiferayDesktop.portletMax.init();
		LiferayDesktop.nonFreeformLayout.init();
		LiferayDesktop.fixDock();
		
		jQuery(window).unload(
			function(event) {
				LiferayDesktop.clock.resetTimer();
			}
		)

	}
);