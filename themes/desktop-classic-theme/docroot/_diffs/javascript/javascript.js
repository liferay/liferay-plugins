var LiferayDesktop = function () {
	var $ = jQuery;

	return {

		/* --- Bottom Navigation --- */

		bottomNavigationInit: function() {
			$("html").css({overflow: "hidden"});

			$("#bottom-navigation").Fisheye({
				maxWidth: 40,
				items: "a",
				itemsText: "span",
				container: "#bottom-container",
				itemWidth: 40,
				proximity: 40,
				alignment : "left",
				valign: "bottom",
				halign: "center"
			});
		},

		/* --- Portlet Modes --- */

		maximizePortlet: function(portletId) {
			var windowHeight = 0;

			$(window).load(
				function() {
					var portletHeight = $(".portlet-content").height();

					if ($(".ie6").size() != 0) {
						windowHeight = document.documentElement.offsetHeight-4;
					}
					else if ($(".ie").size() != 0) {
						windowHeight = document.body.offsetHeight;
					}
					else {
						windowHeight = window.innerHeight;
					}

					$("#portlet-wrapper-" + portletId + " .middle-center").css({height: windowHeight - 54 + "px"});
				}
			);

			if ($(".ie").size() != 0) {
				var resizeTimer = null;

				$(window).resize(
					function() {
						if (resizeTimer) clearTimeout(resizeTimer);

						resizeTimer = setTimeout(
							function() {
								var portletHeight = $(".portlet-content").height();

								if ($(".ie6").size() != 0) {
									windowHeight = document.documentElement.offsetHeight-4;
								}
								else {
									windowHeight = document.body.offsetHeight;
								}

								$("#portlet-wrapper-" + portletId + " .middle-center").css({height: windowHeight - 54 + "px"});
							},
							100
						);
					}
				);
			}
			else {
				$(window).resize(
					function() {
						var portletHeight = $(".portlet-content").height();

						$("#portlet-wrapper-" + portletId + " .middle-center").css({height: window.innerHeight - 54 + "px"});
					}
				);
			}
		},

		minimizePortlet: function(plid, portletId, doAsUserId, pathMain, iconClick) {
			var portlet = $("#p_p_id_" + portletId + "_");
			var parent = portlet.parent();

			portlet.appendTo("#" + parent.attr("id"));

			if (!iconClick) {
				$("#bottom-navigation ." + portletId).css({top: "5px"});

				$("#bottom-navigation ." + portletId).Bounce(
					"20",
					function() {
						$("#bottom-navigation ." + portletId).css({top: "", position:""});
					}
				);
			}

			portlet.DropOutDown("normal");

			var portletIcon = $("#bottom-navigation ." + portletId);
			var portletState = portletIcon.attr("href");

			portletState = portletState.replace("minimizePortlet", "restorePortlet");
			portletIcon.attr("href", portletState);

			AjaxUtil.request(pathMain + "/portal/update_layout" + "?p_l_id=" + plid + "&p_p_id=" + portletId + "&p_p_restore=" + "false" + "&doAsUserId=" + doAsUserId + "&cmd=minimize",
				{
					onComplete: "",
					returnArgs: ""
				}
			);
		},

		restorePortlet: function(plid, portletId, doAsUserId, pathMain) {
			var portlet = $("#p_p_id_" + portletId + "_");
			var parent = portlet.parent();

			portlet.appendTo("#" + parent.attr("id"));

			portlet.DropInDown("normal");

			var portletIcon = $("#bottom-navigation ." + portletId);
			var portletState = portletIcon.attr("href");

			portletState = portletState.replace("restorePortlet", "minimizePortlet");
			portletIcon.attr("href", portletState);

			AjaxUtil.request(pathMain + "/portal/update_layout" + "?p_l_id=" + plid + "&p_p_id=" + portletId + "&p_p_restore=" + "true" + "&doAsUserId=" + doAsUserId + "&cmd=minimize",
				{
					onComplete: "",
					returnArgs: ""
				}
			);
		},

		removePortlet: function(plid, portletId, doAsUserId, pathMain) {
			var portlet = $("#p_p_id_" + portletId + "_");
			var parent = portlet.parent();

			if ($("#content-wrapper").is(".freeform")) {
				portlet.appendTo("#" + parent.attr("id"));
			}

			$("#bottom-navigation ." + portletId + " img").fadeOut(
				"normal",
				function() {
					$("#bottom-navigation ." + portletId).remove();

					$("#bottom-navigation").Fisheye({
						maxWidth: 40,
						items: "a",
						itemsText: "span",
						container: "#bottom-container",
						itemWidth: 40,
						proximity: 40,
						alignment : "left",
						valign: "bottom",
						halign: "center"
					});
				}
			);

			portlet.fadeOut(
				"normal",
				function() {
					closePortlet(plid, portletId, doAsUserId, true);
				}
			);
		},

		addPortlet: function(plid, portletId, doAsUserId, pathMain, portletTitle, bottomImagesFolder) {
			var imagePath = "";

			if (portletId == "1") {
				imagePath = "/mail.png"
			}
			else if (portletId == "8") {
				imagePath = "/calendar.png"
			}
			else if (portletId == "65") {
				imagePath = "/clock.png"
			}
			else if (portletId.indexOf("39") != "-1") {
				imagePath = "/rss.png"
			}
			else if (portletId.indexOf("53") != "-1") {
				imagePath = "/flash.png"
			}
			else {
				imagePath = "/application.png"
			}

			$("#bottom-navigation #bottom-container").append('<a class="bottom-item ' + portletId + '" href="javascript: LiferayDesktop.minimizePortlet(\'' + plid + '\',\'' + portletId + '\',\'' + doAsUserId + '\',\'' + pathMain + '\',\'true\');"><span>' + portletTitle + '</span><img src="' + bottomImagesFolder + imagePath + '" alt="' + portletTitle + '" /></a>');

			$("#bottom-navigation").Fisheye({
				maxWidth: 40,
				items: "a",
				itemsText: "span",
				container: "#bottom-container",
				itemWidth: 40,
				proximity: 40,
				alignment : "left",
				valign: "bottom",
				halign: "center"
			});

			$("#bottom-navigation ." + portletId).css({top: "5px"});

			$("#bottom-navigation ." + portletId).Bounce(
				"25",
				function() {
					$("#bottom-navigation ." + portletId).css({top: "",position:""});
				}
			);

			var portlet = $("#portlet-wrapper-" + portletId).parent();
			var parent = portlet.parent();

			portlet.appendTo("#" + parent.attr("id"));
		},

		/* --- Sign In Portlet --- */

		signInPage: function() {
			$("#bottom-navigation .sign-in").css({top: "5px"});

			$("#bottom-navigation .sign-in").Bounce(
				"20",
				function() {
					$("#bottom-navigation .sign-in").css({top: "",position: "",left: "40px"});
				}
			);
		}
	};
}();