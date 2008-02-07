function insertPlayer(sw) {
	var player = themeDisplay.getPathThemeRoot()+'javascript/mp3player.swf';
	var snd = themeDisplay.getPathThemeRoot()+'javascript/' + sw + '.mp3';
	var mp3html = '<embed wmode="transparent" width="1" height="1" ';
		mp3html += 'src="' + player + '?'
		mp3html += 'showDownload=false&file=' + snd + '&autoStart=true';
		mp3html += '&backColor=ffffff&frontColor=ffffff';
		mp3html += '&repeatPlay=false&songVolume=50" ';
		mp3html += 'type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />';
	return mp3html;
}

jQuery(document).ready(

	/*
	This function gets loaded when all the HTML, not including the portlets, is
	loaded.
	*/

	function() {
		jQuery("#dock").toggle(function() {
			jQuery("#player_div").empty();
			jQuery("#player_div").prepend(insertPlayer('grow'));
			jQuery("#above-dock").SlideInDown(600);
			jQuery(this).addClass('hover');
		},
		function() {
			jQuery("#player_div").empty();
			jQuery("#player_div").prepend(insertPlayer('down'));
			jQuery("#above-dock").SlideOutDown(600);
			jQuery(this).removeClass('hover');
		}
		);
		jQuery("#navigation ul li a").hover(function(){
			jQuery("#player_div").prepend(insertPlayer('coin'));
		},
		function() {}
		);
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