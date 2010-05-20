<%
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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
%>

<%@ include file="init.jsp" %>

<div class="digg-widget" id="<portlet:namespace />digg-widget" style="height: <%= height %>px; width: <%= width %>px;""></div>

<aui:script>
	(function (newsType) {
		var s,
		s1,
		config = {
			descriptions: '<%= showDesc ? "show" : "" %>',
			height: '<%= height %>',
			hide: {
				header: true,
				footer: <%= !showFooter %>,
				diggs: <%= !showDiggs %>,
				thumb: <%= !showThumbs %>
			},
			id: '<portlet:namespace />digg-widget',
			layout: 1,
			requests: [{
				p: {
					count: '<%= storyCount %>'
				}
			}],
			suppressCss: 1,
			target: '<%= newWindow ? "_blank" : "" %>',
			width: '<%= width %>'
		};

		switch (newsType) {
			case 'domain':
				config.requests[0].p.domain = '<%= url %>';
				if (<%= !fallback %>) {
					config.nofallback = <%= fallback %>;
				}
				config.requests[0].p.<%= sourcePopOrUp.equals("popular") ? "min_promote" : "min_submit" %> = '<%= minDate %>';
				config.requests[0].p.method = (<%= sourcePopOrUp.equals("popular") %>) ? 'story.getPopular' : (<%= sourcePopOrUp.equals("upcoming") %>) ? 'story.getUpcoming' : 'story.getAll',
				config.requests[0].p.sort = '<%= urlSort %>';
				config.requests[0].t = (<%= sourcePopOrUp.equals("popular") %>) ? 'Popular' : (<%= sourcePopOrUp.equals("upcoming") %>) ? 'Upcoming' : 'All';
				config.title = (<%= sourcePopOrUp.equals("popular") %>) ? 'Popular Stories' : (<%= sourcePopOrUp.equals("upcoming") %>) ? 'Upcoming Stories' : 'All Stories';
				break;
			case 'front':
				config.requests[0].p.method = 'story.getPopular';
				config.requests[0].p.topic = '<%= newsFront %>';
				config.requests[0].t = '<%= newsFront %>' || 'Popular';
				config.title = ('<%= newsFront %>') ? 'Popular <%= newsFront %> Stories' : 'Popular Stories';
				break;
			case 'top':
				config.requests[0].p.container = '<%= newsTop %>';
				config.requests[0].p.method = 'story.getTop';
				config.requests[0].t = '<%= newsTop %>' || 'Top';
				config.title = ('<%= newsTop %>') ? 'Top <%= newsTop %> Stories' : 'Top Stories';
				break;
			case 'user':
				config.requests[0].p.method = '<%= newsUser.equals("dugg") ? "user.getDugg" : "user.getSubmissions" %>';
				config.requests[0].p.username = '<%= userUsername %>';
				config.requests[0].t = '<%= userUsername %>';
				config.title = 'Stories <%= newsUser %> by <%= userUsername %>';
				break;
			case 'search':
				config.requests[0].p.container = '<%= searchTopics %>';
				config.requests[0].p.method = 'search.stories';
				config.requests[0].p.query = '<%= apiSearch %>';
				config.requests[0].p.sort = '<%= searchSort %>';
				config.requests[0].t = '<%= apiSearch %>';
				config.title = '<%= apiSearch %> Stories';
				break;
			case 'friends':
				config.requests[0].p.method = (<%= newsFriends.equals("dugg") %>) ? 'friend.getDugg' : (<%= newsFriends.equals("submissions") %>) ? 'friend.getSubmissions' : 'friend.getCommented';
				config.requests[0].p.username = '<%= friendsUsername %>';
				config.requests[0].t = 'Friends';
				config.title = 'Stories dugg by friends';
				break;
		}

		if (window.DiggWidget && (typeof DiggWidget == 'function')) {
			new DiggWidget(config);
		}
	}('<%= newsType %>'));
</aui:script>