AUI().ready(
	'liferay-hudcrumbs', 'liferay-navigation-interaction', 'liferay-sign-in-modal', 'aui-modal', 'aui-node', 'aui-io-request',
	function(A) {
		var navigation = A.one('#navigation');

		if (navigation) {
			navigation.plug(Liferay.NavigationInteraction);
		}

		var siteBreadcrumbs = A.one('#breadcrumbs');

		if (siteBreadcrumbs) {
			siteBreadcrumbs.plug(A.Hudcrumbs);
		}

		var eventHandler = function(event) {
			event.preventDefault();
			alert(event.currentTarget.attr('title'));
		}

		A.getBody().delegate('click', eventHandler, 'a.logo');

		var signIn = A.one('li.sign-in a');

		if (signIn && signIn.getData('redirect') !== 'true') {
			signIn.plug(Liferay.SignInModal);
		}

		// Footer Popup

		var footer = A.one('p.powered-by a'),
			href = footer.attr('href');

		if (footer) {
			var frame = '<iframe src=' + href + ' style="height: 98%; width: 99%; z-index: 500;"></iframe>';

			footer.on('click',
				function(event) {
					event.preventDefault();

					var winHeight = window.innerHeight,
						winWidth = window.innerWidth,
						height = (winHeight * 0.92),
						width = (winWidth * 0.90);

					var modal = new A.Modal(
				      {
				        bodyContent: frame,
				        centered: true,
				        height: height,
				        render: '#modal-external',
				        width: width
				      }
				    ).render();
				}
			);
		}

		//Breadcrumbs Modal Sign-in
		
		if (themeDisplay.isSignedIn() !== true) {
			var crumbs = A.one('ul.breadcrumb'),
				
				signInDialogue = function(event) {
					event.preventDefault();

					if (crumbs) {
						var url = new Liferay.PortletURL.createRenderURL();

						url.setPortletId(58);
						url.setWindowState('exclusive');
						
						url = url.toString();

						var signInDialogue = A.io.request(
							url,
							{
								on: {
									success: function() {
										var data = this.get('responseData');

										var modal = new A.Modal(
									      {
									        bodyContent: data,
									        centered: true,
									        headerContent: 'Sign In',
									        height: 420,
									        render: '#modal-sign-in',
									        width: 280
									      }
									    ).render();

									    Liferay.Util.focusFormField('div.modal-body input:text');

									}
								}
							}
						);
					}
				};

			crumbs.delegate('click', signInDialogue, 'a');
		}
	}
);