(
	function($) {
		var win = window,
		doc = document,
		loc = win.location,
		url = {
			index: history.length,
			history: []
		},
		root = loc.protocol + '//' + loc.hostname + ((loc.port) ? ':' + loc.port : ''),
		newFragment,
		$wrap,
		$page,
		i = 1;

		url.history[url.index] = loc.hash.substr(1) || loc.href.replace(root, '');

		$.fragmentChange(true);

		$(doc).bind(
			'ready fragmentChange',
			function () {
				var hash = loc.hash.substr(1) || loc.href.replace(root, '');

				$wrap = $wrap || $(doc.getElementById('wrap'));
				$page = $page || $(doc.getElementById('page'));

				/* The content is not already loaded */
				if (hash !== url.history[url.index] || loc.hash.substr(1)) {
					/* Page is first load with hash */
					if (hash === url.history[url.index]) {
						url.anim = {
							el: doc.getElementById('next'),
							left: 0
						};
					}

					/* Page is moving forward */
					else if (hash !== url.history[url.index - 1]) {
						url.index++;
						url.history[url.index] = hash;
						url.anim = {
							el: doc.getElementById('next'),
							left: '-100%'
						};
					}

					/* Page is moving backward */
					else {
						url.index--;
						url.anim = {
							el: doc.getElementById('prev'),
							left: '100%'
						};
						hash !== url.history[url.index]
					}					

					/* Load page */
					$.ajax(
						{
							cache: false,
							dataType: 'html',
							url: hash,
							error: function() {
								win.history.back();
							},
							success: function(r) {
								/* Capture HTML */
								var capture = doc.createElement('body');
								capture.innerHTML = r;

								if (!(capture = $(capture).find('#page')[0])) {
									win.history.back();
									return true;
								}

								capture = $.trim(capture.innerHTML);

								url.anim.el.innerHTML = capture;

								/* Page animation */
								$wrap.animate(
									{
										left: url.anim.left
									},
									1000 * 0.33,
									'swing',
									function () {
										win.setTimeout(
											function() {
												win.scrollTo(0, 0);
											},
											100
										);

										$page.html(capture);

										$wrap.css(
											{
												left: 0
											}
										);

										setTimeout(
											function() {
												url.anim.el.innerHTML = '';
											},
											1000 * 0.33
										);
									}
								);
							}
						}
					);
				}
				else {
					$('html').addClass('iphone');
					win.setTimeout(
						function() {
							win.scrollTo(0, 0);
						},
						1000 * 0.33
					);
				}

				/* Hijack most hyperlinks */
				$('a:urlInternal').live(
					'click',
					function() {
						if (!/selected/.test(this.className)) {
							loc.hash = this.href.replace(root, '');
						}
						return false;
					}
				);
			}
		);
	}(jQuery)
);
