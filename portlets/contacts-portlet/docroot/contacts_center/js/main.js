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
	'aui-base',
	'aui-dialog',
	'aui-io-plugin',
	'datasource-io',
	'json-parse',
	'liferay-contacts-result',
	function(A) {
		Liferay.namespace('Contacts');

		Liferay.Contacts = {
			init: function(config) {
				var instance = this;

				instance._createContactList(config);
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

				contactsResult.on('results', instance._updateContactsResult);

				instance._contactList = contactsResult;
			},

			_renderResult: function(data, displayMessage, lastNameAnchor) {
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
					var userTemplate =
						'{lastNameAnchor}' +
						'<div class="lfr-contact-grid-item" data-viewSummaryURL="{viewSummaryURL}">' +
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
						'</div>';

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
									userTemplate,
									{
										emailAddress: (result.emailAddress ? result.emailAddress : ''),
										lastNameAnchor: (displayLastNameAnchor ? '<div class="lastNameAnchor"><a>' + lastNameAnchor + '</a></div>' : ''),
										firstName: (result.firstName ? result.firstName : ''),
										lastName: (result.lastName ? result.lastName + ',' : ''),
										portraitURL: (result.portraitURL ? result.portraitURL : ''),
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

				var buffer = Liferay.Contacts._renderResult(data, true, ' ');

				instance._listNode.html(buffer.join(''));
			}
		};
	}
);