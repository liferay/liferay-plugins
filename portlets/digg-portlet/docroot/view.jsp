<%
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
%>

<%@ include file="init.jsp" %>

<div class="digg-widget" id="<portlet:namespace />digg-widget" style="<%= widgetDim %>"></div>

<script>
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

	if (window.DiggWidget && typeof DiggWidget == 'function') {
		new DiggWidget(config);
	}
}('<%= newsType %>'));

</script>