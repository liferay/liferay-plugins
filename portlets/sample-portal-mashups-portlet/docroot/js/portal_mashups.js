Liferay.PortalMashups = {

	// Insert a page fragment in current page
	//	url					the url of the page to load.
	//	remoteSelector		a jQuery selector for the fragment to load in the url.
	//	localSelector		a jQuery selector of the place to load the fragment.
	//	options				a map with several fine-tuning options.
	//
	// Available options
	//	rewriteURLs:		if true, URLS in links and forms will be rewritten
	//						to request a new fragment.
	//	internalURLFilter:	used to determine wheter a URL is internal or not.
	//							If not specified, it is guessed.
	//	afterInsert:		function to be executed after fragment
	//							insertion
	insert: function(url, remoteSelector, localSelector, options) {
		var options = options || {};

		if (url != "") {
			jQuery(localSelector).html("<div align='center'><img src='" + themeDisplay.getPathThemeImages() + "/application/loading_indicator.gif' alt='loading...'/></div>");

			if (options.rewriteURLs) {
				Liferay.PortalMashups._loadAdaptedFragment(url, remoteSelector, localSelector, options);
			}
			else {
				jQuery(localSelector).load(url + " " + remoteSelector);
				if (options.afterInsert) {
					options.afterInsert();
				}
			}
		}
	},

	_adaptFragment: function(remoteSelector, localSelector, options) {
		jQuery(localSelector + " a").each(function(index, domElement) {
			if ((domElement.href.search("javascript:") == -1) && domElement.href.search(options.internalURLFilter)) {
				jQuery(this).click(function() {
					Liferay.PortalMashups._loadAdaptedFragment(domElement.href, remoteSelector, localSelector, options);

					return false;
				});
			}
		});

		var localDiv = jQuery(localSelector);

		jQuery(localSelector + " form").each(function(index, domElement) {
			if(domElement.action.search(options.internalURLFilter)) {
				jQuery(this).ajaxForm({
					complete: function(res, status){
						if ( status == "success" || status == "notmodified" ) {
							jQuery(localSelector).html(
								jQuery("<div/>")
									.append(res.responseText.replace(/<script(.|\s)*?\/script>/g, ""))
									.find(remoteSelector));
							Liferay.PortalMashups._adaptFragment(remoteSelector, localSelector, options);
						}
					}
				});
			}
		});

		if (options.afterInsert) {
			options.afterInsert();
		}
	},

	_loadAdaptedFragment:  function(url, remoteSelector, localSelector, options) {
		if (url != null) {
			if (!options.internalURLFilter) {
				if (url.search("p_p_id=[^&]+") != -1) {
					options.internalURLFilter = url.match("(p_p_id=[^&]+)")[1];
				}
				else if (url.search("\/widget\/") != -1 && url.search("\/-\/.") != -1) {
					var matches = url.match("(.*\/widget\/.*)/-/([^\?]+).*");
					options.internalURLFilter = "/" + matches[2] + "/";

					if ((options.internalURLFilter.search("^\d+$") != -1) ||
						(options.internalURLFilter.search("^\d+_INSTANCE_....$") != -1)) {
						options.internalURLFilter = "p_p_id=" + options.internalURLFilter;
						url = matches[1] + "?" + options.internalURLFilter;
					}
				}
				else {
					options.internalURLFilter = url.substring(0, url.search("\?"));
				}
			};

			jQuery(localSelector).load(url + " " + remoteSelector, function (responseText, textStatus, XMLHttpRequest) {
				Liferay.PortalMashups._adaptFragment(remoteSelector, localSelector, options);
			});
		}
	}
};
