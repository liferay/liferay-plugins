/*
 * Mark external Links and set Title
 */
	var ie6 = (navigator.appName == "Microsoft Internet Explorer" && parseInt(navigator.appVersion) == 4 && navigator.appVersion.indexOf("MSIE 6.0") != -1);
	var ie55 = (navigator.appName == "Microsoft Internet Explorer" && parseInt(navigator.appVersion) == 4 && navigator.appVersion.indexOf("MSIE 5.5") != -1);
	var ie5 = (navigator.appName == "Microsoft Internet Explorer" && parseInt(navigator.appVersion) == 4 && navigator.appVersion.indexOf("MSIE 5.0") != -1);

	if (!ie5) {
	jQuery.noConflict();
	jQuery(document).ready(function(){
		jQuery("a[@rel='external']").each(function(i){
			if (typeof jQuery(this).attr('title') == "undefined" )
				jQuery(this).attr('title',jQuery(this).attr('href')+'    (external link)');
			else
				jQuery(this).attr('title',jQuery(this).attr('title')+'    (external link)');
			/*jQuery(this).addClass('external');
			jQuery(this).click(function(){
				window.open(jQuery(this).attr('href')); return false;
			});*/
			jQuery(this).append('<span class="external" title="'+jQuery(this).attr('title')+'">&nbsp;</span>').click(function(){
				window.open(jQuery(this).attr('href')); return false;
			});
		});
	});
	}
