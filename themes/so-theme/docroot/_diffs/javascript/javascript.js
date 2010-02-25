AUI().use(
	'aui-base',
	function(A) {
		LiferayInc = {
			init: function() {
				var instance = this;

				instance.handleMySitesDropDown();
			},

			handleMySitesDropDown: function() {
				var mySites = A.one('#navigation-top .my-sites');
				
				mySites.on(
					'mouseover',
					function(event) {
						var menu = event.currentTarget;
				
						menu.addClass('open');
						menu.one('.child-menu').show();
					}
				);
				
				mySites.on(
					'mouseout',
					function(event) {
						var menu = event.currentTarget;
				
						menu.removeClass('open');
						menu.one('.child-menu').hide();
					}
				);
				
			}
		};
	}
);

AUI().ready(
	function(A) {
		LiferayInc.init();
	}
);
