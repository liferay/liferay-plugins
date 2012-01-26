AUI.add(
	'liferay-contacts-result',
	function(A) {
		var ContactsResult = A.Base.create(
			'contactsResult',
			A.Base,
			[A.AutoCompleteBase],
			{
				initializer: function(config) {
					this._listNode = A.one(config.listNode);

					this._bindUIACBase();
					this._syncUIACBase();
				}
			}
		);

		Liferay.namespace('Contacts');

		Liferay.ContactsResult = ContactsResult;
	},
	'',
	{
		requires: ['aui-base', 'autocomplete-base']
	}
);

AUI().use(
	'aui-dialog',
	'aui-io-plugin',
	'datasource-io',
	'json-parse',
	'liferay-contacts-result',
	function(A) {
		var Array = A.Array;

		var TPL_BLOCK_IMG =
			'<span>' +
				'<img class="icon" alt="" src="' + themeDisplay.getPathThemeImages() + '/social/block.png">' +
				'<span class="taglib-text">' + Liferay.Language.get('blocked') + '</span>' +
			'</span>';

		var TPL_CONNECTED_IMG =
			'<span>' +
				'<img class="icon" alt="" src="' + themeDisplay.getPathThemeImages() + '/social/coworker.png">' +
				'<span class="taglib-text">' + Liferay.Language.get('connected') + '</span>' +
			'</span>';

		var TPL_CONNECTION_REQUESTED_IMG =
			'<span>' +
				'<img class="icon" alt="" src="' + themeDisplay.getPathThemeImages() + '/social/coworker.png">' +
				'<span class="taglib-text">' + Liferay.Language.get('connection-requested') + '</span>' +
			'</span>';

		var TPL_FOLLOWING_IMG =
			'<span>' +
				'<img class="icon" alt="" src="' + themeDisplay.getPathThemeImages() + '/social/following.png">' +
				'<span class="taglib-text">' + Liferay.Language.get('following') + '</span>' +
			'</span>';

		var TPL_USER_DATA =
			'{lastNameAnchor}' +
			'<div class="lfr-contact">' +
				'<div class="lfr-contact-checkbox">' +
					'<input type="checkbox" value="{userId}" {disabled} {checked} name="contact-ids-{userId}" class="contact-ids" label="" />' +
				'</div>' +
				'<div class="lfr-contact-grid-item" data-viewSummaryURL="{viewSummaryURL}" data-userId="{userId}">' +
					'<div class="lfr-contact-thumb">' +
						'<img alt="{fullName}" src="{portraitURL}" />' +
					'</div>' +
					'<div class="lfr-contact-info">' +
						'<div class="lfr-contact-name">' +
							'<a>{lastName} {firstName}</a>' +
						'</div>' +
						'<div class="lfr-contact-extra">' +
							'{emailAddress}' +
						'</div>' +
					'</div>' +
					'<div class="clear"></div>' +
				'</div>' +
			'</div>';

		var TPL_USER_DETAIL_DATA =
			'<div class="lfr-contact-grid-item" id="user-{userId}">' +
				'<input type="hidden" value="{userId}" name="selected-user-{userId}" />' +
				'<div class="lfr-contact-thumb">' +
					'<img alt="{fullName}" src="{portraitURL}" />' +
				'</div>' +
				'<div class="lfr-contact-info">' +
					'<div class="lfr-contact-name">' +
						'<a>{lastName} {firstName}</a>' +
					'</div>' +
					'<div class="lfr-contact-extra">' +
						'{emailAddress}' +
					'</div>' +
					'<div class="lfr-social-relations">' +
						'{connectionRequested}' + '{connected}' + '{following}' + '{block}' +
					'</div>' +
				'</div>' +
				'<div class="clear"></div>' +
			'</div>';

		Liferay.namespace('Contacts');

		var ContactsCenter = A.Component.create(
			{
				EXTENDS: A.Base,

				NAME: 'contactscenter',

				prototype: {
					init: function(config) {
						var instance = this;

						instance._namespace = config.namespace;

						instance._blockButton = A.one('#' + instance._namespace + 'blockButton');
						instance._unblockButton = A.one('#' + instance._namespace + 'unblockButton');
						instance._addConnectionButton = A.one('#' + instance._namespace + 'addConnectionButton');
						instance._removeConnectionButton = A.one('#' + instance._namespace + 'removeConnectionButton');
						instance._followButton = A.one('#' + instance._namespace + 'followButton');
						instance._unfollowButton = A.one('#' + instance._namespace + 'unfollowButton');

						instance._buttonAddConnectionUserIds = [];
						instance._buttonBlockUserIds = [];
						instance._buttonFollowUserIds = [];
						instance._buttonRemoveConnectionUserIds = [];
						instance._buttonUnBlockUserIds = [];
						instance._buttonUnFollowUserIds = [];

						instance._numSelectedContacts = 0;

						instance._createContactList(config);
					},

					addContactResult: function(data, namespace) {
						var instance = this;

						var contactResultContent = A.one('.contacts-portlet .contacts-container-content');

						if (contactResultContent) {
							var contact = instance._renderContact(data);

							if (instance._numSelectedContacts <= 0) {
								contactResultContent.html(contact);

								A.one('#contactCenterToolbarButtons').show();

								A.one('#userToolbarButtons').empty();
							}
							else {
								contactResultContent.append(contact);
							}

							instance._numSelectedContacts++;

							instance._updateToolbarButtonsAdd(data, namespace);
						}
					},

					createDataSource: function(url) {
						return new A.DataSource.IO(
							{
								ioConfig: {
									method: "post"
								},
								on: {
									request: function(event) {
										var contactFilterContainer = A.one('.contacts-portlet .contact-group-filter');

										var socialRelationType = 0;

										if (contactFilterContainer) {
											socialRelationType = contactFilterContainer.one('select').get('value');
										}

										var data = event.request;

										event.cfg.data = {
											end: data.end || 100,
											keywords: data.keywords || '',
											socialRelationType: data.socialRelationType || socialRelationType,
											start: data.start || 0
										}
									}
								},
								source: url
							}
						)
					},

					clearContactResult: function() {
						var instance = this;

						var contactResultContent = A.one('.contacts-portlet .contacts-container-content');

						contactResultContent.empty();

						A.all('.lfr-contact .lfr-contact-checkbox input').set('checked', false);

						instance._buttonAddConnectionUserIds = [];
						instance._buttonBlockUserIds = [];
						instance._buttonFollowUserIds = [];
						instance._buttonRemoveConnectionUserIds = [];
						instance._buttonUnBlockUserIds = [];
						instance._buttonUnFollowUserIds = [];

						A.all('.aui-toolbar-content button').hide();
						A.one('#contactCenterToolbarButtons').hide();

						instance._numSelectedContacts = 0;
					},

					deleteContactResult: function(userId) {
						var instance = this;

						var user = A.one('#user-' + userId);

						if (user) {
							user.remove(true);

							instance._numSelectedContacts--;

							instance._updateToolbarButtonsRemove(userId);

							if (instance._numSelectedContacts <= 0) {
								instance.clearContactResult();
							}
						}
					},

					getRequestTemplate: function(socialRelationType) {
						return function(query) {
							return {
								end: 100,
								keywords: query,
								socialRelationType: socialRelationType,
								start: 0
							}
						};
					},

					showMoreResult: function(responseData, lastNameAnchor) {
						var instance = this;

						var data = A.JSON.parse(responseData);

						var buffer = instance._renderResult(data, false, lastNameAnchor);

						var contactResultContent = A.one('.contacts-portlet .contacts-result-content');
						var moreResults = A.one('.contacts-portlet .more-results');

						moreResults.remove(true);

						contactResultContent.append(buffer.join(''));
					},

					updateContacts: function(keywords, socialRelationType) {
						var instance = this;

						if (instance._contactList) {
							instance._contactList.sendRequest(keywords, instance.getRequestTemplate(socialRelationType));
						}
					},

					_createContactList: function(config) {
						var instance = this;

						var contactsResultContainer = config.contactsResultContainer;

						var contactsResult = config.contactsResult;
						var contactsResultURL = config.contactsResultURL;
						var contactsSearchInput = config.contactsSearchInput;

						var contactsResult = new Liferay.ContactsResult(
							{
								requestTemplate: function(query) {
									return {
										keywords: query
									}
								},

								inputNode: contactsSearchInput,
								listNode: contactsResult,
								minQueryLength: 0,
								source: instance.createDataSource(contactsResultURL)
							}
						);

						contactsResult.on('results', A.bind(instance._updateContactsResult, instance));

						instance._contactList = contactsResult;
					},

					_renderContact: function(data) {
						var instance = this;

						var user = data.user;

						var buffer =
							A.Lang.sub(
								TPL_USER_DETAIL_DATA,
								{
									block: user.block ? TPL_BLOCK_IMG : '',
									connectionRequested: user.connectionRequested ? TPL_CONNECTION_REQUESTED_IMG : '',
									connected: user.connected ? TPL_CONNECTED_IMG : '',
									emailAddress: user.emailAddress,
									firstName: user.firstName,
									following: user.following ? TPL_FOLLOWING_IMG : '',
									fullName: user.fullName,
									lastName: user.lastName,
									portraitURL: user.portraitURL,
									userId: user.userId
								}
							);

						return buffer;
					},

					_renderResult: function(data, displayMessage, lastNameAnchor) {
						var instance = this;

						var results = data.users;
						var count = data.count;

						var options = data.options;

						var buffer = [];

						if ((results.length == 0) && displayMessage) {
							buffer.push(
								'<div class="empty">' + Liferay.Language.get('there-are-no-results') + '</div>'
							);
						}
						else {
							var selectedUsersNodes = A.all('.lfr-contact-grid-item input');

							var selectedUsersIds = [];

							if (selectedUsersNodes.size() > 0) {
								selectedUsersIds = selectedUsersNodes.val();
							}

							buffer.push(
								A.Array.map(
									results,
									function(result) {
										var displayLastNameAnchor = false;

										var nameAnchor;

										if (result.lastName) {
											nameAnchor = result.lastName.substring(0, 1).toUpperCase();
										}
										else {
											nameAnchor = Liferay.Language.get('no-last-name');
										}

										if (nameAnchor != lastNameAnchor) {
											displayLastNameAnchor = true;

											lastNameAnchor = nameAnchor;
										}

										return A.Lang.sub(
											TPL_USER_DATA,
											{
												checked: ((Array.indexOf(selectedUsersIds, result.userId) != -1) ? 'checked="true"' : ''),
												disabled: (themeDisplay.getUserId() == result.userId) ? 'disabled="true"' : '',
												emailAddress: (result.emailAddress ? result.emailAddress : ''),
												lastNameAnchor: (displayLastNameAnchor ? '<div class="lastNameAnchor"><a>' + lastNameAnchor + '</a></div>' : ''),
												firstName: (result.firstName ? result.firstName : ''),
												fullName: (result.fullName ? result.fullName : ''),
												lastName: (result.lastName ? result.lastName + ',' : ''),
												portraitURL: (result.portraitURL ? result.portraitURL : ''),
												userId: result.userId,
												viewSummaryURL: (result.viewSummaryURL ? result.viewSummaryURL : '')
											}
										);
									}
								).join('')
							);
						}

						if (count > options.end) {
							buffer.push(
								'<div class="more-results">' +
									'<a href="javascript:;" data-end="' + options.end + '" data-lastNameAnchor="' + lastNameAnchor + '">' + Liferay.Language.get('view-more') + ' (' + (count - options.end) + ')' + '</a>' +
								'</div>'
							);
						}

						return buffer;
					},

					_updateContactsResult: function(event) {
						var instance = this;

						var data = A.JSON.parse(event.data.responseText);

						var buffer = instance._renderResult(data, true, ' ');

						event.currentTarget._listNode.html(buffer.join(''));
					},

					_updateToolbarButtonsAdd: function(data) {
						var instance = this;

						var user = data.user;

						if (user.block) {
							if (instance._unblockButton) {
								instance._unblockButton.show();

								instance._buttonUnBlockUserIds.push(user.userId);
							}
						}
						else {
							if (instance._blockButton) {
								instance._blockButton.show();

								instance._buttonBlockUserIds.push(user.userId);
							}

							if (!user.connectionRequested) {
								if (user.connected) {
									if (instance._removeConnectionButton) {
										instance._removeConnectionButton.show();

										instance._buttonRemoveConnectionUserIds.push(user.userId);
									}
								}
								else {
									if (instance._addConnectionButton) {
										instance._addConnectionButton.show();

										instance._buttonAddConnectionUserIds.push(user.userId);
									}
								}
							}

							if (user.following) {
								if (instance._unfollowButton) {
									instance._unfollowButton.show();

									instance._buttonUnFollowUserIds.push(user.userId);
								}
							}
							else {
								if (instance._followButton) {
									instance._followButton.show();

									instance._buttonFollowUserIds.push(user.userId);
								}
							}
						}
					},

					_updateToolbarButtonsRemove: function(userId) {
						var instance = this;

						var blockUserIdIndex = instance._buttonBlockUserIds.indexOf(userId);

						if (blockUserIdIndex >= 0) {
							Array.remove(instance._buttonBlockUserIds, blockUserIdIndex);

							if (instance._buttonBlockUserIds.length <= 0) {
								if (instance._blockButton) {
									instance._blockButton.hide();
								}
							}
						}

						var unblockUserIdIndex = instance._buttonUnBlockUserIds.indexOf(userId);

						if (unblockUserIdIndex >= 0) {
							Array.remove(instance._buttonUnBlockUserIds, unblockUserIdIndex);

							if (instance._buttonUnBlockUserIds.length <= 0) {
								if (instance._unblockButton) {
									instance._unblockButton.hide();
								}
							}
						}

						var addConnectionUserIdIndex = instance._buttonAddConnectionUserIds.indexOf(userId);

						if (addConnectionUserIdIndex >= 0) {
							Array.remove(instance._buttonAddConnectionUserIds, addConnectionUserIdIndex);

							if (instance._buttonAddConnectionUserIds.length <= 0) {
								if (instance._addConnectionButton) {
									instance._addConnectionButton.hide();
								}
							}
						}

						var removeConnectionUserIdIndex = instance._buttonRemoveConnectionUserIds.indexOf(userId);

						if (removeConnectionUserIdIndex >= 0) {
							Array.remove(instance._buttonRemoveConnectionUserIds, removeConnectionUserIdIndex);

							if (instance._buttonRemoveConnectionUserIds.length <= 0) {
								if (instance._removeConnectionButton) {
									instance._removeConnectionButton.hide();
								}
							}
						}

						var followUserIdIndex = instance._buttonFollowUserIds.indexOf(userId);

						if (followUserIdIndex >= 0) {
							Array.remove(instance._buttonFollowUserIds, followUserIdIndex);

							if (instance._buttonFollowUserIds.length <= 0) {
								if (instance._followButton) {
									instance._followButton.hide();
								}
							}
						}

						var unFollowUserIdIndex = instance._buttonUnFollowUserIds.indexOf(userId);

						if (unFollowUserIdIndex >= 0) {
							Array.remove(instance._buttonUnFollowUserIds, unFollowUserIdIndex);

							if (instance._buttonUnFollowUserIds.length <= 0) {
								if (instance._unfollowButton) {
									instance._unfollowButton.hide();
								}
							}
						}
					}
				}
			}
		);

		Liferay.Contacts = ContactsCenter;
	}
);