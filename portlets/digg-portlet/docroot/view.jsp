<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/init.jsp" %>

<div id="twitter_widget<portlet:namespace />node"></div>

<aui:script>
	digg_widget<portlet:namespace />options = (function (newsType) {
		var options = {
			fallback: '<%= LanguageUtil.get(pageContext, "no-results") %>',
			name: 'digg_widget<portlet:namespace />',
			poll: true,
			query: {
				count: '10',
				domain: 'CBSNews.com',
				method: 'story.getPopular',
				sort: 'promote_date-desc'
			},
			rate: '20',
			showDiggs: <%= showDiggs %>,
			showDescriptions: <%= showDesc %>,
			showThumbnails: <%= showThumbs %>,
			target: '<%= targetBlank ? "_blank" : "" %>',
			view: document.getElementById('twitter_widget<portlet:namespace />node')
		};

		switch (newsType) {
			case 'domain':
				options.query.domain = '<%= url %>';
				options.query.<%= sourcePopOrUp.equals("popular") ? "min_promote" : "min_submit" %> = '<%= minDate %>';
				options.query.method = (<%= sourcePopOrUp.equals("popular") %>) ? 'story.getPopular' : (<%= sourcePopOrUp.equals("upcoming") %>) ? 'story.getUpcoming' : 'story.getAll',
				options.query.sort = '<%= urlSort %>';
				break;
			case 'front':
				options.query.method = 'story.getPopular';
				options.query.topic = '<%= newsFront %>';
				break;
			case 'top':
				options.query.container = '<%= newsTop %>';
				options.query.method = 'story.getTop';
				break;
			case 'user':
				options.query.method = '<%= newsUser.equals("dugg") ? "user.getDugg" : "user.getSubmissions" %>';
				options.query.username = '<%= userUsername %>';
				break;
			case 'search':
				options.query.container = '<%= searchTopics %>';
				options.query.method = 'search.stories';
				options.query.query = '<%= apiSearch %>';
				options.query.sort = '<%= searchSort %>';
				break;
			case 'friends':
				options.query.method = (<%= newsFriends.equals("dugg") %>) ? 'friend.getDugg' : (<%= newsFriends.equals("submissions") %>) ? 'friend.getSubmissions' : 'friend.getCommented';
				options.query.username = '<%= friendsUsername %>';
				break;
		}

		return options;
	}('<%= newsType %>'));

	if (typeof digg_widget<portlet:namespace /> == 'undefined') {
		digg_widget<portlet:namespace /> = new DiggPortletWidget();
	}

	digg_widget<portlet:namespace />.render(digg_widget<portlet:namespace />options);
</aui:script>