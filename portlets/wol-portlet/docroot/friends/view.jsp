<%
/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

<%@ include file="/init.jsp" %>

<%
PortletURL portletURL = renderResponse.createRenderURL();

SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, 5, portletURL, null, null);

int total = UserLocalServiceUtil.getSocialUsersCount(user2.getUserId(), SocialRelationConstants.TYPE_BI_FRIEND);

searchContainer.setTotal(total);

List<User> results = UserLocalServiceUtil.getSocialUsers(user2.getUserId(), SocialRelationConstants.TYPE_BI_FRIEND, searchContainer.getStart(), searchContainer.getEnd(), new UserLoginDateComparator());

searchContainer.setResults(results);

List resultRows = searchContainer.getResultRows();

for (int i = 0; i < results.size(); i++) {
	User friend = results.get(i);

	ResultRow row = new ResultRow(friend, friend.getUserId(), i);

	// User display

	row.addJSP("/friends/user_display.jsp", application, request, response);

	// Add result row

	resultRows.add(row);
}
%>

<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" paginate="<%= false %>" />

<c:if test="<%= results.size() > 0 %>">
	<div class="taglib-search-iterator-page-iterator-bottom" id="<portlet:namespace />searchFriends">
		<liferay-ui:search-paginator searchContainer="<%= searchContainer %>" type="article" />
	</div>
</c:if>

<script type="text/javascript">
	jQuery(
		function () {
			var searchFriends = jQuery('#<portlet:namespace />searchFriends');

			searchFriends.find('a').click(
				function(event) {
					var url = this.href.replace(/p_p_state=normal/i, 'p_p_state=exclusive');
					var parent = searchFriends.parent();

					parent.html('<div class="loading-animation" />');

					jQuery.ajax(
						{
							url: url,
							success: function(response) {
								parent.html(response);
							}
						}
					);

					return false;
				}
			);
		}
	);
</script>