AUI().use(
	'aui-base',
	function(A) {
		Liferay.namespace('AssetFeedShared');

		var TPL_PREVIEW = '<div class="link-preview">' +
			'<div class="image-container">' +
				'<a href="{url}"><img src="{imageURL}" /></a>' +
			'</div>' +
			'<div class="content-container">' +
				'<div class="title">' +
					'<a href="{url}">{title}</a>' +
				'</div>' +
				'<div class="description">' +
					'<a href="{url}">{description}</a>' +
				'</div>' +
				'<div class="short-url">' +
					'<a href="{url}">{shortURL}</a>' +
				'</div>' +
			'</div>' +
		'</div>';

		Liferay.AssetFeedShared = {
			getLinkPreview: function(obj) {
				return A.Lang.sub(
					TPL_PREVIEW,
					{
						description: obj.description,
						imageURL: obj.imageURL,
						shortURL: obj.shortURL,
						title: obj.title,
						url: obj.url
					}
				);
			}
		};
	}
);