AUI.add(
	'liferay-so-user-menu',
	function(A) {
		var UserMenu = function(config) {
			var hideClass = config.hideClass;
			var hideOn = config.hideOn || 'close-menus';
			var preventDefault = config.preventDefault || false;
			var showClass = config.showClass;
			var showOn = config.showOn || 'click';

			var node = A.one(config.node);

			var target = A.one(config.target) || node;

			A.on(
				'close-menus',
				function(event) {
					target.fire('close-menus', event);
				}
			);

			target.on(
				'click',
				function(event) {
					event.stopPropagation();
				}
			);

			target.on(
				'hideOn|close-menus',
				function(event) {
					if (hideClass && !target.hasClass(hideClass)) {
						target.addClass(hideClass);
					}

					if (showClass && target.hasClass(showClass)) {
						target.removeClass(showClass);
					}
				}
			);

			var trigger = A.one(config.trigger) || node;

			trigger.on(
				showOn,
				function(event) {
					if (preventDefault) {
						event.preventDefault();
					}

					A.fire('close-menus', event);

					if (hideClass && target.hasClass(hideClass)) {
						setTimeout(
							function() {
								target.removeClass(hideClass);
							},
							10
						);
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