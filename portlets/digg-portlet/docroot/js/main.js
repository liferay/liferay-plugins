DiggPortletWidget = function() {
	var instance = this;

	instance.callbackName = '';
	instance.fallback = '';
	instance.intervalTime = 60000;
	instance.nodes = {};
	instance.poll = true;
	instance.query = {};
	instance.showDescriptions = false;
	instance.showDiggs = true;
	instance.showThumbnails = false;
	instance.target = '_blank';
};

DiggPortletWidget.prototype = {
	callback: function(response) {
		var instance = this;

		if (instance.nodes.view) {
			if (response && response.stories && response.stories.length) {
				instance.nodes.view.innerHTML = instance.createDiggs(response.stories);
			}
			else {
				instance.nodes.view.innerHTML = ['<div class="no-results">', instance.fallback, '</div>'].join('');
			}
		}
	},
	createDiggs: function(results) {
		var instance = this,
			i = -1,
			resultsLength = results.length,
			html = [];

		while (++i < resultsLength) {
			var result = results[i],
				last = !results[i + 1];

			html.push([
				'<div class="digg',
				(!instance.showDiggs) ? ' digg-no-diggs' : '',
				(last) ? ' digg-last' : '',
				'"><div class="header">',
				(instance.showDiggs) ? [
					'<a class="count" href="',
					'http://digg.com',
					result.href,
					'" target="',
					instance.target,
					'"><span>',
					result.diggs,
					'</span></a>'
				].join('') : '',
				(instance.showThumbnails && result.thumbnail) ? [
					'<span class="thumb"><img src="',
					result.thumbnail.src,
					'" /></span>'
				].join('') : '',
				'<a class="title" href="',
				result.link,
				'" target="',
				instance.target,
				'">',
				result.title,
				'</a></div>',
				(instance.showDescriptions) ? [
					'<div class="text">',
					result.description,
					'</div>'
				].join('') : '',
				'</div>'
			].join(''));
		}

		return html.join('');
	},
	createHowLongAgo: function(since) {
		var ms = (new Date()) - (new Date(since));

		var sec = Math.floor(ms / 1000);

		ms = ms % 1000;

		var min = Math.floor(sec / 60);

		sec = sec % 60;

		var hr = Math.floor(min / 60);

		min = min % 60;

		var day = Math.floor(hr / 24);

		hr = hr % 24;

		var week = Math.floor(day / 7);

		day = day % 7;

		var ret = week || day || hr || min || sec;

		return 'about ' + ret + ' ' + ((week & (ret = week)) ? 'week' : (day & (ret = day)) ? 'day' : (hr & (ret = hr)) ? 'hour' : (min && (ret = min)) ? 'minute' : (sec && (ret = sec)) ? 'second' : '') + ((ret > 1) ? 's' : '') + ' ago';
	},
	createInterval: function() {
		var instance = this;

		window.clearInterval(instance.intervalTask);

		instance.intervalTask = window.setInterval(
			function() {
				instance.createScript.apply(instance);
			},
			instance.intervalTime
		);
	},
	createParams: function() {
		var instance = this,
			query = instance.query,
			queryArray = [],
			i,
			ii;

		for (i in query) {
			if (query[i] || query[i] == '') {
				queryArray.push(i + '=' + encodeURIComponent(query[i]));
			}
		}

		return queryArray.join('&')
	},
	createScript: function() {
		var instance = this;

		instance.nodes.head = instance.nodes.head || document.getElementsByTagName('head')[0];

		if (instance.nodes.script && instance.nodes.script.parentNode) {
			instance.nodes.head.removeChild(instance.nodes.script);
		}

		instance.nodes.script = document.createElement('script');

		instance.nodes.script.setAttribute('src', instance.createScriptURL());

		instance.nodes.head.appendChild(instance.nodes.script);
	},
	createScriptURL: function() {
		var instance = this;

		return ['http://widgets.digg.com/widgets/stories?endPoint=/1.0/endpoint&', instance.createParams(), '&type=javascript&size=a&callback=', instance.callbackName, '.callback', '#', (new Date()).getTime()].join('');
	},
	render: function(options) {
		var instance = this;

		if (options) {
			instance.query = options.query || instance.query;
			instance.callbackName = options.name || instance.callBackName;
			instance.fallback = options.fallback || instance.fallback;
			instance.intervalTime = (options.rate * 1000) || instance.intervalTime;
			instance.nodes.view = options.view || instance.nodes.view;
			instance.poll = (options.poll !== undefined) ? options.poll : instance.poll;
			instance.showDescriptions = (options.showDescriptions !== undefined) ? options.showDescriptions : instance.showDescriptions;
			instance.showDiggs = (options.showDiggs !== undefined) ? options.showDiggs : instance.showDiggs;
			instance.showThumbnails = (options.showThumbnails !== undefined) ? options.showThumbnails : instance.showThumbnails;
			instance.target = (options.target !== undefined)  || instance.target;
		}

		if (instance.poll) {
			instance.createInterval();
		}

		instance.createScript();
	},
	callbackName: '',
	intervalTask: null
};