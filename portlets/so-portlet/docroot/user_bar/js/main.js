AUI.add(
	'liferay-so-user-menu',
	function(A) {
		var UserMenu = function(config) {
			var node = A.one(config.node);

			var hideClass = config.hideClass;
			var hideOn = config.hideOn || 'mouseleave';
			var showClass = config.showClass;
			var showOn = config.showOn || 'click';
			var target = A.one(config.target) || node;
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
			)
		}

		Liferay.namespace('SO');

		Liferay.SO.UserMenu = UserMenu;
	},
	'',
	{
		requires: ['aui-base', 'node-core']
	}
);