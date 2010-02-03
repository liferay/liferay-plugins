AUI().ready(
	'aui-base',
	function(A) {
		if (Liferay.Browser.isIe()) {
			A.all('#footer ul li:last-child').addClass('last-child');
		}

		var searchForm = A.one('#banner .search'),
			searchInput = searchForm.one('input[type=image]'),
			searchLink = A.Node.create('<a class="search-input-link" href="javascript:;"></a>'),
			hideTask = new A.DelayedTask(
				function(event) {
					var childMenu = this.one('.child-menu');

					if (childMenu) {
						childMenu.addClass('init');
						childMenu.setStyle('display', 'none');
					}
				}
			);

		A.all('.parent-nav-item').on(
			{
				mouseenter: function(event) {
					var childMenu = this.one('.child-menu');

					if (childMenu) {
						childMenu.removeClass('init');
						childMenu.setStyle('display', 'block');
					}
				},
				
				mouseleave: function(event) {
					hideTask.delay(25, null, this, event);
				}
			}
		);

		searchLink.on(
			'click',
			function() {
				this.ancestor('form').submit();
			}
		);

		searchInput.hide();

		searchInput.placeBefore(searchLink);
	}
);