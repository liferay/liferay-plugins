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

		A.getBody().delegate('click', eventHandler, 'a.logo');

		var eventHandler = function(event) {
			event.preventDefault();
			alert(event.currentTarget.attr('title'));
		}

		var signIn = A.one('li.sign-in a');

		if (signIn && signIn.getData('redirect') !== 'true') {
			signIn.plug(Liferay.SignInModal);
		}

		var footer = A.one('p.powered-by a');

		if (footer) {
			var frame = '<iframe src="http://www.liferay.com" style="height: 98%; width: 99%; z-index: 500;"></iframe>';

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

		if (themeDisplay.isSignedIn() !== true) {
			var crumb = A.one('.breadcrumb li a');
			
			if (crumb) {
				crumb.on('click', 
					function(event) {
						event.preventDefault();

						var signIn = A.io.request(
							'http://localhost:8080/c/portal/login',
							{
								on: {
									success: function() {
										var data = this.get('responseData');

										var modal = new A.Modal(
									      {
									        bodyContent: data,
									        centered: true,
									        headerContent: 'Sign In',
									        height: 800,
									        render: '#modal-sign-in',
									        width: 500
									      }
									    ).render();
									}
								}
							}
						);
					}
				);
			}
		} 
	}
);