jQuery(document).ready(

	/*
	This function gets loaded when all the HTML, not including the portlets, is
	loaded.
	*/

	function() {
		var soundPlay = function (sound, reset) {
			if (reset) {
				soundFX.html('');
			}
			soundFX.append('<embed wmode="transparent" width="1" height="1" src="' + themeDisplay.getPathThemeRoot() + 'javascript/' + sound + '.swf" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />');
		};

		var soundFX = jQuery('<div style="position: absolute; top: 0; left: 0; "></div>').appendTo('#banner');

		jQuery('#navigation li').hover(function () {
			soundPlay('coin');
		}, function () {});

		jQuery('.lfr-dock h2').click(function () {
			var mario = jQuery(this);
			var dock = jQuery('.lfr-dock ul');

			if (dock.css('display') === 'block') {
				soundPlay('pipe', true);
				dock.animate({
					display: 'none',
					opacity: 0,
					top: -28
				}, {
					duration: 600,
					complete: function () {
						dock.hide();
						mario.removeClass('hit');
					}
				});
			}
			else {
				soundPlay('grow', true);
				mario.addClass('hit');
				dock.css({
					display: 'block',
					opacity: 1,
					top: 0
				});
				dock.animate({
					top: -58
				}, {
					duration: 500
				});
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