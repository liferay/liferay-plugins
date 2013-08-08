AUI().use(
	'aui-base',
	'transition',
	function(A) {
		Liferay.namespace('SO');

		Liferay.SO.Activities = {
			addNewComment: function(commentsList, responseData) {
				var commentEntryTemplate =
					'<div class="comment-entry">' +
						'<div class="user-portrait">' +
							'<span class="avatar">' +
								'<a href={userDisplayURL}>' +
									'<img alt={userName} src={userPortraitURL} />' +
								'</a>' +
							'</span>' +
						'</div>' +
						'<div class="comment-body">' +
							'<span class="user-name"><a href={userDisplayURL}>{userName}</a></span>' +
							'<span class="message">{body}</span>' +
						'</div>' +
						'<div class="comment-info">' +
							'<span class="post-date">{modifiedDate} </span>' +
							'<span class="edit-comment">' +
								'<a data-entryId={entryId} href="javascript:;">' + Liferay.Language.get('edit') + '</a>' +
							'</span>' +
							'<span class="delete-comment">' +
								'<a data-entryId={entryId} href="javascript:;">' + Liferay.Language.get('delete') + '</a>' +
							'</span>' +
						'</div>' +
					'</div>';

				var commentEntryHtml = A.Lang.sub(
					commentEntryTemplate,
					{
						body: responseData.body,
						entryId: responseData.entryId,
						modifiedDate: responseData.modifiedDate,
						userDisplayURL: responseData.userDisplayURL ? responseData.userDisplayURL : '',
						userName: responseData.userName,
						userPortraitURL: responseData.userPortraitURL
					}
				);

				commentsList.append(commentEntryHtml);
			},

			toggleEntry: function(event, portletNamespace) {
				var entryId = event.currentTarget.attr('data-entryId');

				var entry = A.one('#' + portletNamespace + entryId);

				var body = entry.one('.grouped-activity-body');
				var bodyContainer = entry.one('.grouped-activity-body-container');
				var control = entry.one('.toggle-entry');
				var subentryHeight = entry.one('.activity-subentry').outerHeight();

				var minHeight = (subentryHeight * 3) + 'px';

				var bodyHeight = minHeight;

				if (entry.hasClass('toggler-content-collapsed')) {
					entry.removeClass('toggler-content-collapsed');

					bodyContainer.setStyle('height', minHeight);
					bodyContainer.setStyle('maxHeight', 'none');

					bodyHeight = body.height() + 'px';

					if (control) {
						control.html(Liferay.Language.get('view-less'));
					}
				}
				else {
					entry.addClass('toggler-content-collapsed');

					if (control) {
						control.html(Liferay.Language.get('view-more'));
					}
				}

				bodyContainer.transition(
					{
						duration: 0.5,
						easing: 'ease-in-out',
						height: bodyHeight
					}
				);
			}
		};
	}
);