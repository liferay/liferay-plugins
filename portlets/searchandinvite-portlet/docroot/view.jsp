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

<%
/**
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at http://www.sun.com/cddl/cddl.html and
 * legal/CDDLv1.0.txt. See the License for the specific language governing
 * permission and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL Header Notice in each file
 * and include the License file at legal/CDDLv1.0.txt.
 *
 * If applicable, add the following below the CDDL Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Copyright 2008 Sun Microsystems Inc. All rights reserved.
 */
%>

<%@ include file="/init.jsp" %>

<%
PortletURL portletURL = renderResponse.createRenderURL();
portletURL.setWindowState(WindowState.MAXIMIZED);
pageContext.setAttribute("portletURL", portletURL);

String portletURLString = portletURL.toString();
SearchContainer searchContainer1 = new SearchContainer(renderRequest, new SearchDisplayTerms(renderRequest), new UserSearchTerms(renderRequest), SearchContainer.DEFAULT_CUR_PARAM, 5, portletURL, null, null);
SearchDisplayTerms displayTerms = (SearchDisplayTerms)searchContainer1.getDisplayTerms();
LinkedHashMap userParams = new LinkedHashMap();
%>

<form action="<%= portletURLString %>" method="POST" name="<portlet:namespace />fm" >

	<input type="hidden" name="doSearch" value="true" />

	<liferay-ui:search-container
		searchContainer="<%= searchContainer1 %>"
		>

		<%@ include file="/user_search.jspf" %>

		<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">

			<liferay-ui:search-container-results>

				<%@ include file="/user_search_results.jspf" %>

			</liferay-ui:search-container-results>

			<liferay-ui:search-container-row
				className="com.liferay.portal.model.User"
				keyProperty="userId"
				modelVar="user2"
				>

					<portlet:resourceURL var="addFriendUrl">
						<portlet:param name="jspPage" value="/addFriend.jsp"/>
						<portlet:param name="friendId" value="<%=Long.toString(user2.getUserId())%>"/>
					</portlet:resourceURL>

					<liferay-ui:search-container-column-text
						name="first-name"
						property="firstName"
						/>

					<liferay-ui:search-container-column-text
						name="last-name"
						property="lastName"
						/>

					<liferay-ui:search-container-column-text
						name="screen-name"
						property="screenName"
						/>

					<liferay-ui:search-container-column-text
						name="job-title"
						value="<%= user2.getContact().getJobTitle() %>"
						/>

					<liferay-ui:search-container-column-text
						name="">

						<c:choose>
							<c:when test="<%= !SocialRelationLocalServiceUtil.hasRelation(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_BI_FRIEND) && !(themeDisplay.getUserId() == user2.getUserId()) %>">

								<liferay-ui:icon
									image="join"
									message="add-as-friend"
									url="<%= \"javascript:addFriend('\" + addFriendUrl + \"')\"%>"  label="<%= true %>"
									/>

							</c:when>
						</c:choose>

					</liferay-ui:search-container-column-text>

			</liferay-ui:search-container-row>

		<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />

		</c:if>

	</liferay-ui:search-container>

</form>

<script language="javascript">
	function addFriend(addFriendUrl) {
		jQuery.ajax(
			{
				url: addFriendUrl,
				success: function(message) {
					Liferay.Popup(
						{
							width: 550,
							modal: true,
							title: Liferay.Language.get('add-as-friend'),
							message: message
						}
					);
				}
			}
		);
	}
</script>
