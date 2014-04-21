AUI.add(
	'liferay-resize-rtl',
	function(A) {
		var RULES = A.Resize.RULES;

		var tmpL = RULES.l;

		RULES.l = RULES.r;
		RULES.r = tmpL;
	},
	'',
	{
		requires: ['resize-base']
	}
);