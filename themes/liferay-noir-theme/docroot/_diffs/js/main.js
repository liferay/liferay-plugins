AUI().ready(
	'aui-base',
	function(A) {
		if (Liferay.Browser.isIe()) {
			A.all('#footer ul li:last-child').addClass('last-child');
		}

		var searchForm = A.one('#banner .search'),
			searchInput = searchForm.one('input[type=image]'),
			searchLink = A.Node.create('<a class="search-input-link" href="javascript:;"></a>');

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