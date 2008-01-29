Liferay.Util.actsAsAspect(Liferay.Dock);

Liferay.Dock.before(
	'init',
	function() {
		jQuery('.interactive-mode').removeClass('interactive-mode');
	}
);