var A = AUI();

var rtlTest = function() {
	return document.documentElement.dir === 'rtl'
};

var LIFERAY_JS_PATH = Liferay.AUI.getJavaScriptRootPath() + '/liferay/';

A.applyConfig(
	{
		modules: {
			'liferay-resize-rtl': {
				condition: {
					test: rtlTest,
					trigger: 'resize-base'
				},
				fullpath: LIFERAY_JS_PATH + 'resize_rtl.js'
			}
		}
	}
);