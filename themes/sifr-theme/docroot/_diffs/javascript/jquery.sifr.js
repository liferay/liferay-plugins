jQuery.fn.sifr = function (settings) {
	// jQuery extend functionality.
	settings = jQuery.extend({}, settings);
	var hex = function(N) {
		if (N==null) return "00";
		N = parseInt(N);
		if (N==0||isNaN(N)) return "00";
		N = Math.max(0,N);
		N = Math.min(N,255);
		N = Math.round(N);
		return "0123456789ABCDEF".charAt((N-N%16)/16)+"0123456789ABCDEF".charAt(N%16);
	};
	// Converts various colors to their six-digit hex representation.
	var colorToHex = function(color) {
		color = or(color,'000000');
		if (color.search('rgb')>-1) {
			color = color.substr(4,color.length-5).split(', ');
			color = hex(color[0])+hex(color[1])+hex(color[2]);
		}
		color = color.replace('#','');
		if (color.length<6) {
			color = color.substr(0,1) + color.substr(0,1) + color.substr(1,1) + color.substr(1,1) + color.substr(2,1) + color.substr(2,1);
		}
		return color;
	};
	// Picks between one thing if it things exists, or another.
	var or = function(orThis, orThat) {
		return (orThis) ? orThis : (orThat) ;
	};
	// This is the main portion of the jQuery Plug-In.
	return this.each(function() {
		// Check to see if sifr has been here.
		if (jQuery(this).attr('class')&&jQuery(this).attr('class').search('sifr')>-1) {
			jQuery(this).unsifr();
		}
		// Run jQuery Flash, build our settings, and execute.
		jQuery(this).flash(
			{},
			{},
			function (htmlOptions) {
				// Add the 'sifr' class.
				jQuery(this).addClass('sifr');
				// Begin sifting data.
				var offsetX = (settings['offsetX']) ? parseInt(jQuery(this).css('fontSize'))*settings['offsetX'] : 0;
				var offsetY = (settings['offsetY']) ? parseInt(jQuery(this).css('fontSize'))*settings['offsetY'] : 0;
				var path = or(settings['path'], '');
				var font = path + or(settings['font'], jQuery(this).css('fontFamily').split(',')[0].replace(/\"|\'/gm , ''));
				var vAlign = or(settings['vAlign'], or(jQuery(this).css('verticleAlign'), 'top'));
				var hAlign = or(settings['hAlign'], or(jQuery(this).css('textAlign'), 'left'));
				var color = or(colorToHex(settings['color']), colorToHex(jQuery(this).css('color')));
				var underline = or((settings['underline']) ? true : false, (jQuery(this).css('textDecoration')=='underline') ? 'true' : false, false);
				var alpha = or(settings['alpha'], '');
				var link = or(settings['link'], '');
				var hover = or(settings['hover'], '');
				var shadow = or(settings['shadow'], '');
				var shadowX = or(settings['shadowX'], '');
				var shadowY = or(settings['shadowY'], '');
				var shadowAlpha = or(settings['shadowAlpha'], '');
				var backgroundColor = or(settings['backgroundColor'], '');
				jQuery(this).html('<span style="display:inline !important;margin:0 !important;padding:0 !important;float:none !important;width:auto !important;height:auto !important;">'+jQuery(this).html()+'</span>');
				var content = or(settings['content'], jQuery(this.firstChild).html());
				var width = or(settings['width'], jQuery(this.firstChild).width());
				var height = or(settings['height'],jQuery(this.firstChild).height());
				jQuery(this.firstChild).attr('style','display:none;');
				// Begin setting data.
				htmlOptions.wmode = 'transparent';
				htmlOptions.style = 'vertical-align:' + vAlign + ';';
				htmlOptions.src = font +'.swf';
				htmlOptions.flashvars.txt = content;
				if (alpha) { htmlOptions.flashvars.textalpha = alpha; }
				if (color) { htmlOptions.flashvars.textcolor = color; }
				if (link) { htmlOptions.flashvars.linkColor = link; }
				if (hover) { htmlOptions.flashvars.hoverColor = hover; }
				if (underline) { htmlOptions.flashvars.underline = underline; }
				if (backgroundColor) { htmlOptions.flashvars.bgColor = backgroundColor; }
				if (shadow) { htmlOptions.flashvars.shadowcolor = shadow; }
				if (shadowAlpha) { htmlOptions.flashvars.shadowalpha = shadowAlpha; }
				if (shadowX) { htmlOptions.flashvars.shadowxmove = shadowX; }
				if (shadowY) { htmlOptions.flashvars.shadowymove = shadowY; }
				htmlOptions.width = width;
				htmlOptions.height = height;
				htmlOptions.flashvars.w = width;
				htmlOptions.flashvars.h = height;
				htmlOptions.flashvars.textalign = hAlign;
				htmlOptions.flashvars.offsetTop = offsetY;
				htmlOptions.flashvars.offsetLeft = offsetX;
				// Append sIFR element to element.
				jQuery(this).append(jQuery.fn.flash.transform(htmlOptions));
		});
	});
};
jQuery.fn.unsifr = function() {
	return this.each(function() {
		// Check to see if sifr has been here.
		if (jQuery(this).attr('class')&&jQuery(this).attr('class').search('sifr')>-1) {
			jQuery(this).html(jQuery(this.firstChild).html());
			jQuery(this).removeClass('sifr');
		}
	});
};