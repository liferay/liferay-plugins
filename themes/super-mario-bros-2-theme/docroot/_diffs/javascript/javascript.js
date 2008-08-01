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

		jQuery('.lfr-dock h2').hover(function () {
			if (jQuery('.lfr-dock ul').css('display') !== 'block') {
				soundPlay('pick');
				jQuery(this).addClass('hover');
			}
		}, function () {
			jQuery(this).removeClass('hover');
		});

		jQuery('.lfr-dock h2').click(function () {
			var mario = jQuery(this);
			var dock = jQuery('.lfr-dock ul');

			if (dock.css('display') === 'block') {
				soundPlay('toss', true);

				mario.removeClass('lifted');
				mario.addClass('throwing');

				dock.animate({
					left: -600,
					top: 10,
					opacity: 0
				}, {
					duration: 750,
					complete: function () {
						dock.hide();
						dock.css({
							left: 'auto',
							top: 'auto',
							opacity: 1
						});
						mario.removeClass('throwing');
					}
				});
			}
			else {
				mario.removeClass('hover');
				mario.addClass('lifting');

				soundPlay('pull');

				setTimeout(function () {
					soundPlay('door', true);

					mario.removeClass('lifting');
					mario.addClass('lifted');

					dock.show();
				}, 1000);
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