<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

<%@ include file="/admin/init.jsp" %>

<%
KBArticle kbArticle = (KBArticle)request.getAttribute(WebKeys.KNOWLEDGE_BASE_KB_ARTICLE);
%>

<c:if test="<%= enableKBArticleRatings %>">

	<%
	int kbCommentsCount = KBCommentLocalServiceUtil.getKBCommentsCount(themeDisplay.getUserId(), KBArticle.class.getName(), kbArticle.getClassPK());
	%>

	<div id="<portlet:namespace />kbArticleRatingsContainer">
		<liferay-ui:ratings
			className="<%= KBArticle.class.getName() %>"
			classPK="<%= kbArticle.getResourcePrimKey() %>"
			numberOfStars="<%= PortletPropsValues.KNOWLEDGE_BASE_RATINGS_NUMBER_OF_STARS %>"
			type="<%= kbArticleRatingsType %>"
		/>

		<c:if test="<%= themeDisplay.isSignedIn() %>">
			<div class="kb-article-feedback-actions" id="<portlet:namespace />additionalFeedbackActionsContainer">
				<c:choose>
					<c:when test="<%= kbCommentsCount == 0 %>">
						<a data-target-node-id="<portlet:namespace />feedbackContainer" href="javascript:void(0)">
							<liferay-ui:message key="do-you-have-any-suggestions" />
						</a>
					</c:when>
					<c:when test="<%= kbCommentsCount == 1 %>">
						<a href="javascript:void(0)">
							<liferay-ui:message key="your-previous-suggestion" />
						</a>
					</c:when>
					<c:otherwise>
						<a href="javascript:void(0)">
							<liferay-ui:message arguments="<%= new Object[]{ kbCommentsCount } %>" key="your-previous-x-suggestions" />
						</a>
					</c:otherwise>
				</c:choose>
			</div>
		</c:if>
	</div>

	<c:if test='<%= kbArticleRatingsType.equals("thumbs") && themeDisplay.isSignedIn() %>'>
		<div class="hide kb-article-feedback" id="<portlet:namespace />feedbackContainer">
			<liferay-portlet:actionURL name="updateKBComment" var="updateKBCommentURL" />

			<aui:form action="<%= updateKBCommentURL %>" method="post" name="feedbackFm">
				<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.ADD %>" />
				<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
				<aui:input name="classNameId" type="hidden" value="<%= PortalUtil.getClassNameId(KBArticle.class) %>" />
				<aui:input name="classPK" type="hidden" value="<%= kbArticle.getResourcePrimKey() %>" />

				<liferay-ui:error exception="<%= KBCommentContentException.class %>" message="please-enter-valid-content" />

				<aui:model-context model="<%= KBComment.class %>" />

				<aui:fieldset>
					<span class="kb-helpful-text">
						<liferay-ui:message key="what-did-you-like-the-most-what-would-you-improve" />
					</span>

					<aui:input label="" name="content" />

					<aui:button-row cssClass="kb-submit-buttons">
						<aui:button type="submit" value="submit" />
						<aui:button cssClass="kb-article-feedback-cancel-button" value="cancel" />
					</aui:button-row>
				</aui:fieldset>
			</aui:form>
		</div>

		<%
		boolean paginating = GetterUtil.getBoolean(ParamUtil.getBoolean(request, "paginating"), false);
		%>

		<div class='kb-article-previous-comments <%= paginating ? "" : "hide" %>' id="<portlet:namespace />previousCommentsContainer">
			<c:if test="<%= kbCommentsCount > 0 %>">
				<aui:button cssClass="kb-article-add-comment" name="enableAddSuggestionForm" value="add-new" />

				<div class="hide kb-article-feedback" id="<portlet:namespace />addSuggestionForm">
					<liferay-portlet:actionURL name="updateKBComment" var="updateKBCommentURL" />

					<aui:form action="<%= updateKBCommentURL %>" id="suggestionFm" method="post" name="suggestionFm">
						<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.ADD %>" />
						<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
						<aui:input name="classNameId" type="hidden" value="<%= PortalUtil.getClassNameId(KBArticle.class) %>" />
						<aui:input name="classPK" type="hidden" value="<%= kbArticle.getResourcePrimKey() %>" />

						<aui:model-context model="<%= KBComment.class %>" />

						<aui:fieldset>
							<aui:input label="" name="content" />

							<aui:button-row cssClass="kb-submit-buttons">
								<aui:button type="submit" value="submit" />
								<aui:button cssClass="kb-article-feedback-cancel-button" value="cancel" />
							</aui:button-row>
						</aui:fieldset>
					</aui:form>
				</div>

				<liferay-portlet:renderURL varImpl="iteratorURL">
					<portlet:param name="paginating" value="true" />
				</liferay-portlet:renderURL>

				<liferay-ui:search-container
						emptyResultsMessage="no-comments-found"
						iteratorURL="<%= iteratorURL %>"
				>

					<liferay-ui:search-container-results
							results="<%= KBCommentLocalServiceUtil.getKBComments(themeDisplay.getUserId(), KBArticle.class.getName(), kbArticle.getClassPK(), searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator()) %>"
							total="<%= kbCommentsCount %>"
					/>

					<liferay-ui:search-container-row
							className="com.liferay.knowledgebase.model.KBComment"
							escapedModel="true"
							modelVar="kbComment"
					>
						<div class="kb-article-comment">
							<p class="kb-article-comment-body">
								<%= kbComment.getContent() %>
							</p>

							<div class="kb-article-comment-post-date">
								<i class="icon-calendar"></i>

								<%
								DateSearchEntry dateSearchEntry = new DateSearchEntry();

								dateSearchEntry.setDate(kbComment.getModifiedDate());
								%>

								<%= dateSearchEntry.getName(pageContext) %>
							</div>
						</div>
					</liferay-ui:search-container-row>

					<liferay-ui:search-iterator />
				</liferay-ui:search-container>
			</c:if>
		</div>

		<aui:script use="aui-base">
			var feedbackFm = A.one('#<portlet:namespace />feedbackFm');

			feedbackFm.on(
				'submit',
				function(event) {
					var helpful = this.one('<portlet:namespace />helpful');

					helpful.val(thumbUp.hasClass('rating-on'));
				}
			);

			var additionalFeedbackActions = A.all('#<portlet:namespace />additionalFeedbackActionsContainer a');

			additionalFeedbackActions.each(
				function(node) {
					var targetForm = A.one('#' + node.getData('target-node-id'));

					node.on(
						'click',
						function(event) {
							targetForm.toggleView();
						}
					);
				}
			);

			var suggestionForm = A.one('#<portlet:namespace />addSuggestionForm');

			var enableAddSuggestionFormButton = A.one('#<portlet:namespace />enableAddSuggestionForm');

			if (enableAddSuggestionFormButton) {
				enableAddSuggestionFormButton.on(
					'click',
					function(event) {
						suggestionForm.toggleView();

						var textArea = suggestionForm.one('textarea');

						textArea.focus();
					}
				);
			}

			var cancelButtons = A.all('.kb-article-feedback-cancel-button');

			cancelButtons.on(
				'click',
				function(event) {
					this.each(
						function(node) {
							var container = node.ancestor('.kb-article-feedback');

							container.hide();
							container.all('textarea').val('');
						}
					);
				}
			);
		</aui:script>
	</c:if>
</c:if>