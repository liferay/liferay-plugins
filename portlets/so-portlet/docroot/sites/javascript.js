Liferay.SO = Liferay.SO || {};

Liferay.SO.Sites = {
	init: function(params) {
		var instance = this;

		instance._popup = null;

		instance._assignEvents();
	},

	closePopup: function() {
		var instance = this;

		var popup = instance._popup;

		popup.parents('.site-dialog').hide();
	},

	displayPopup: function(popupUrl, popupTitle) {
		var instance = this;

		var popup = instance._popup;

		if ((popup == null) || !(popup[0].parentNode && popup[0].parentNode.tagName)) {
			popup = new Liferay.Popup(
				{
					title: popupTitle,
					className: 'site-dialog',
					resizable: false,
					height: 'auto',
					position: [15,15],
					width: '600px',
					close: function() {
						popup = null;
					}
				}
			);
		}
		else {
			var popupContainer = popup.parents('.ui-dialog');

			popupContainer.find('.ui-dialog-title').text(popupTitle);

			var popupHelper = popupContainer.data('ui-helper-drag');

			if (popupHelper) {
				popupHelper.find('.ui-dialog-title').text(popupTitle);
			}

			popup.parents('.site-dialog').show();
		}

		popup.html('<div class="loading-animation" />');

		jQuery.ajax(
			{
				url: popupUrl,
				success: function(message) {
					popup.html(message);
				}
			}
		);

		instance._popup = popup;

		return popup;
	},

	_assignEvents: function() {
		var instance = this;

		jQuery('.description-toggle').livequery(
			'click',
			function() {
				var downImage = 'arrow_down.png';
				var rightImage = 'arrow_right.png';

				if (this.src.indexOf('down.png') > -1) {
					jQuery(".description", this.parentNode).slideUp();

					this.src = this.src.replace(downImage, rightImage);
				}
				else {
					jQuery(".description", this.parentNode).slideDown();

					this.src = this.src.replace(rightImage, downImage);
				}
			}
		);
	}
};