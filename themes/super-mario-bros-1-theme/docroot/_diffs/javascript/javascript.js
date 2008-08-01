jQuery(document).ready(

	/*
	This function gets loaded when all the HTML, not including the portlets, is
	loaded.
	*/

	function() {
		var soundPlay = function (snd) {
			var inject = '<embed wmode="transparent" width="1" height="1" src="' + themeDisplay.getPathThemeRoot() + 'javascript/mp3player.swf?file=' + themeDisplay.getPathThemeRoot() + 'javascript/' + snd + '.mp3&autoStart=true&volume=50" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />';
			if (snd == 'grow' || snd == 'down') {
				soundFX.html(inject);
			}
			else {
				soundFX.append(inject);
			}
		};

		var soundFX = jQuery('<div style="position: absolute; top: 0; left: 0; "></div>').appendTo('#banner');

		jQuery('#navigation li').hover(function () {
			soundPlay('coin');
		}, function () {});

		jQuery('.lfr-dock h2').click(function () {
			var instance = jQuery('.lfr-dock ul');
			if (instance.css('display') === 'block') {
				soundPlay('down');
				instance.hide(200);
			}
			else {
				soundPlay('grow');
				instance.show(500);
			}
		});
	}
);

Liferay.Portlet.ready(

	/*
	This function gets loaded after each and every portlet on the page.

	portletId: the current portlet's id
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