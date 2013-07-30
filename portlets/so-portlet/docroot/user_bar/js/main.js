AUI.add(
	'liferay-so-user-menu',
	function(A) {
		var UserMenu = function(config) {
			var hideClass = config.hideClass;
			var hideOn = config.hideOn || 'mouseleave';
			var showClass = config.showClass;
			var showOn = config.showOn || 'click';

			var node = A.one(config.node);

			var target = A.one(config.target) || node;

			target.on(
				hideOn,
				function(event) {
					if (hideClass && !target.hasClass(hideClass)) {
						target.addClass(hideClass);
					}

					if (showClass && target.hasClass(showClass)) {
						target.removeClass(showClass);
					}

					trigger.blur();
				}
			);

			var trigger = A.one(config.trigger) || node;

			trigger.on(
				showOn,
				function(event) {
					event.preventDefault();

					if (hideClass && target.hasClass(hideClass)) {
						target.removeClass(hideClass);
					}

					if (showClass && !target.hasClass(showClass)) {
						target.addClass(showClass);
					}
				}
			);
		}

		Liferay.namespace('SO');

		Liferay.SO.UserMenu = UserMenu;
	},
	'',
	{
		requires: ['aui-base', 'node-core']
	}
);