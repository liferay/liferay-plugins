;(function() {
	var PATH_CALENDAR_WEB = Liferay.ThemeDisplay.getPathContext() + '/calendar-portlet';

	AUI().applyConfig(
		{
			groups: {
				components: {
					base: PATH_CALENDAR_WEB + '/',
					modules: {
						'liferay-calendar-recurrence-converter': {
							path: 'js/recurrence_converter.js',
							requires: []
						},
						'scheduler-mobile': {
							path: 'js/mobile.js',
							requires: ['aui-scheduler']
						}
					},
					root: PATH_CALENDAR_WEB + '/'
				}
			}
		}
	);
})();