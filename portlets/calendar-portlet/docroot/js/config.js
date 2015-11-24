;(function() {
	var PATH_CALENDAR_WEB = Liferay.ThemeDisplay.getPathContext() + '/calendar-portlet';

	AUI().applyConfig(
		{
			groups: {
				components: {
					base: PATH_CALENDAR_WEB + '/',
					modules: {
						'scheduler-mobile': {
							path: 'js/mobile.js',
							requires: ['aui-scheduler']
						},
						'liferay-calendar-recurrence-converter': {
							path: 'js/recurrence_converter.js',
							requires: []
						}
					},
					root: PATH_CALENDAR_WEB + '/'
				}
			}
		}
	);
})();