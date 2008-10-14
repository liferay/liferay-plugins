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

<%@ include file="/knowledge_base/init.jsp" %>

<%
KBArticle article = (KBArticle) request.getAttribute(KnowledgeBaseKeys.ARTICLE);

KBFeedbackEntry feedbackEntry = (KBFeedbackEntry) request.getAttribute(KnowledgeBaseKeys.FEEDBACK_ENTRY);

KBFeedbackStats feedbackStats = (KBFeedbackStats) request.getAttribute(KnowledgeBaseKeys.FEEDBACK_STATS);

// Article

long resourcePrimKey = article.getResourcePrimKey();

String title = article.getTitle();
String htmlTitle = article.getHtmlTitle();

String[] attachments = article.getAttachmentsFiles();

boolean print = ParamUtil.getBoolean(request, Constants.PRINT);

if (!article.isTemplate()) {
	TagsAssetLocalServiceUtil.incrementViewCounter(KBArticle.class.getName(), resourcePrimKey);
}

String className = KBArticle.class.getName();

if (article.isTemplate()) {
	className += ".template";
}

List childArticles = article.getChildArticles();

// Feedback entry

int score = BeanParamUtil.getInteger(feedbackEntry, request, "score");
int vote = BeanParamUtil.getInteger(feedbackEntry, request, "vote");

String comments = BeanParamUtil.getString(feedbackEntry, request, "comments");

// Feedback stats

int totalVotes = BeanParamUtil.getInteger(feedbackStats, request, "totalVotes");
int yesVotes = BeanParamUtil.getInteger(feedbackStats, request, "yesVotes");

int noPercentage = 0;
int yesPercentage = 0;

if (totalVotes > 0) {
	yesPercentage = (int)(yesVotes / totalVotes) * 100;
	noPercentage = 100 - yesPercentage;
}

// Portlet URLs

PortletURL addChildArticleURL = renderResponse.createRenderURL();

addChildArticleURL.setParameter("view", "edit_article");
addChildArticleURL.setParameter("redirect", currentURL);
addChildArticleURL.setParameter("editTitle", "1");
addChildArticleURL.setParameter("parentResourcePrimKey", String.valueOf(resourcePrimKey));

PortletURL editArticleURL = renderResponse.createRenderURL();

editArticleURL.setParameter("view", "edit_article");
editArticleURL.setParameter("tabs", "edit");
editArticleURL.setParameter("redirect", currentURL);
editArticleURL.setParameter("title", title);

PortletURL printArticleURL = renderResponse.createRenderURL();

printArticleURL.setParameter("view", "view_article");
printArticleURL.setParameter("title", title);
printArticleURL.setParameter("print", "true");
printArticleURL.setWindowState(LiferayWindowState.POP_UP);

PortletURL taggedArticlesURL = renderResponse.createRenderURL();

taggedArticlesURL.setParameter("view", "view_tagged_articles");

PortletURL viewAllURL = renderResponse.createRenderURL();

PortletURL viewArticleURL = renderResponse.createRenderURL();

viewArticleURL.setParameter("view", "view_article");
viewArticleURL.setParameter("resourcePrimKey", String.valueOf(resourcePrimKey));

PortletURL viewAttachmentsURL = renderResponse.createRenderURL();

viewAttachmentsURL.setParameter("view", "view_article_attachments");
viewAttachmentsURL.setParameter("tabs", "attachments");
viewAttachmentsURL.setParameter("title", title);

PortletURL viewHistoryURL = renderResponse.createRenderURL();

viewHistoryURL.setParameter("view", "view_article_history");
viewHistoryURL.setParameter("tabs", "history");
viewHistoryURL.setParameter("redirect", currentURL);
viewHistoryURL.setParameter("resourcePrimKey", String.valueOf(resourcePrimKey));

ResourceURL feedbackURL = renderResponse.createResourceURL();
%>

<c:if test="<%= print %>">
	<div class="popup-print">
		<liferay-ui:icon image="print" message="print" url="javascript: print();" />
	</div>
</c:if>

<c:if test="<%= article.hasParent() %>">
	<div class="breadcrumbs">

		<%
		PortletURL viewParentArticleURL = renderResponse.createRenderURL();

		viewParentArticleURL.setParameter("view", "view_article");

		List parentArticles = article.getParentArticles(themeDisplay.getUserId());

		for (int i = 0; i < parentArticles.size(); i++) {
			KBArticle curParentArticle = (KBArticle)parentArticles.get(i);

			viewParentArticleURL.setParameter("title", curParentArticle.getTitle());
		%>

			<a href="<%= viewParentArticleURL %>"><%= curParentArticle.getHtmlTitle() %></a>

			<c:if test="<%= i < parentArticles.size() %>">
				&raquo;
			</c:if>

		<%
		}
		%>

	</div>
</c:if>

<h1 class="article-title">
	<%= htmlTitle %>
</h1>

<c:if test="<%= !article.isHead() %>">
	<div class="portlet-msg-info">
		<liferay-ui:message key="you-are-viewing-an-archived-version-of-this-article" />&nbsp;&nbsp;<a href="<%= viewArticleURL %>"><liferay-ui:message key="go-to-the-latest-version" /></a>
	</div>
</c:if>

<div class="knowledge-base-content-container">
	<table class="lfr-table" width="100%">
	<tr>
		<td valign="top">
			<div class="knowledge-base-content">
				<div class="knowledge-base-body">
					<%= article.getContent() %>
				</div>

				<c:if test="<%= (childArticles.size() > 0) %>">
					<p class="child-articles-message"><liferay-ui:message key="children" /></p>

					<ul class="child-articles">

						<%
						PortletURL curArticleURL = renderResponse.createRenderURL();
						curArticleURL.setParameter("view", "view_article");

						for (int i = 0; i < childArticles.size(); i++) {
							KBArticle curArticle = (KBArticle)childArticles.get(i);

							curArticleURL.setParameter("title", curArticle.getTitle());
						%>

							<li>
								<a href="<%= curArticleURL %>"><%= curArticle.getHtmlTitle() %></a>
							</li>

						<%
						}
						%>

					</ul>
				</c:if>

				<c:if test="<%= !print && !article.isDraft() && article.isHead() && !article.isTemplate() %>">
					<table border="0" cellpadding="0" cellspacing="0" class="taglib-ratings stars">
					<tr>
						<c:if test="<%= themeDisplay.isSignedIn() %>">
							<td>
								<div style="font-size: xx-small; padding-bottom: 2px;">
									<liferay-ui:message key="your-rating" />
								</div>

								<div id="<portlet:namespace />yourRating">
									<img src="<%= themeDisplay.getPathThemeImages() %>/ratings/star_off.png" /><img src="<%= themeDisplay.getPathThemeImages() %>/ratings/star_off.png" /><img src="<%= themeDisplay.getPathThemeImages() %>/ratings/star_off.png" /><img src="<%= themeDisplay.getPathThemeImages() %>/ratings/star_off.png" /><img src="<%= themeDisplay.getPathThemeImages() %>/ratings/star_off.png" /><img src="<%= themeDisplay.getPathThemeImages() %>/ratings/star_off.png" /><img src="<%= themeDisplay.getPathThemeImages() %>/ratings/star_off.png" /><img src="<%= themeDisplay.getPathThemeImages() %>/ratings/star_off.png" /><img src="<%= themeDisplay.getPathThemeImages() %>/ratings/star_off.png" /><img src="<%= themeDisplay.getPathThemeImages() %>/ratings/star_off.png" />
								</div>
							</td>
							<td style="padding-left: 30px;"></td>
						</c:if>

						<td>
							<div id="<portlet:namespace />totalEntries" style="font-size: xx-small; padding-bottom: 2px;">
								<liferay-ui:message key="average" /> (<%= feedbackStats.getTotalScoreEntries() %> <%= LanguageUtil.get(pageContext, (feedbackStats.getTotalScoreEntries() == 1) ? "vote" : "votes") %>)
							</div>

							<div id="<portlet:namespace />averageRating" onmousemove="Liferay.Portal.ToolTip.show(event, this, '<%= MathUtil.format(feedbackStats.getAverageScore(), 1, 1) %> <liferay-ui:message key="stars" />')">
								<img src="<%= themeDisplay.getPathThemeImages() %>/ratings/star_off.png" /><img src="<%= themeDisplay.getPathThemeImages() %>/ratings/star_off.png" /><img src="<%= themeDisplay.getPathThemeImages() %>/ratings/star_off.png" /><img src="<%= themeDisplay.getPathThemeImages() %>/ratings/star_off.png" /><img src="<%= themeDisplay.getPathThemeImages() %>/ratings/star_off.png" /><img src="<%= themeDisplay.getPathThemeImages() %>/ratings/star_off.png" /><img src="<%= themeDisplay.getPathThemeImages() %>/ratings/star_off.png" /><img src="<%= themeDisplay.getPathThemeImages() %>/ratings/star_off.png" /><img src="<%= themeDisplay.getPathThemeImages() %>/ratings/star_off.png" /><img src="<%= themeDisplay.getPathThemeImages() %>/ratings/star_off.png" />
							</div>
						</td>
					</tr>
					</table>
					<div class="knowledge-base-feedback">
						<liferay-ui:tabs names="feedback" />

						<c:choose>
							<c:when test="<%= themeDisplay.isSignedIn() %>">
								<script type="text/javascript">
									function <portlet:namespace />saveFeedbackComments() {
										Liferay.KnowledgeBase.saveFeedbackComments();
									}

									function <portlet:namespace />saveFeedbackVote(vote) {
										Liferay.KnowledgeBase.saveFeedbackVote(vote);
									}
								</script>

								<div
									id="<portlet:namespace />feedbackContainer"
									<c:if test="<%= feedbackEntry != null %>">
										style="display: none;"
									</c:if>
								>
									<div class="portlet-msg-info" id="<portlet:namespace />feedbackStatus">
										<liferay-ui:message key="let-us-know-what-you-thought" />
									</div>

									<div id="<portlet:namespace />feedbackForm">
										<form method="post" name="<portlet:namespace />fm">

										<div class="ctrl-holder-feedback-ratings">
											<p class="ratings-message"><liferay-ui:message key="did-you-find-this-helpful" /></p>

											<label class="ratings-label" for="<portlet:namespace />entryRatingYes"><input <%= vote == 1 ? "checked" : StringPool.BLANK %> name="entryRating" id="<portlet:namespace />entryRatingYes" value="1" type="radio" onClick="<portlet:namespace />saveFeedbackVote(1);" /> <liferay-ui:message key="yes" /></label>

											<label class="ratings-label" for="<portlet:namespace />entryRatingNo"><input <%= vote == -1 ? "checked" : StringPool.BLANK %> name="entryRating" id="<portlet:namespace />entryRatingNo" value="-1" type="radio" onClick="<portlet:namespace />saveFeedbackVote(-1);" /> <liferay-ui:message key="no" /></label>
										</div>

										<div
											class="ctrl-holder-feedback-comments"
											id="<portlet:namespace />ctrlHolderFeedbackComments"
											<c:if test="<%= feedbackEntry == null %>">
												style="display: none;"
											</c:if>
										>
											<c:choose>
												<c:when test="<%= vote == -1 %>">
													<p class="comments-message" id="<portlet:namespace />commentsMessage"><liferay-ui:message key="please-let-us-know-why-you-found-this-unhelpful" /></p>
												</c:when>
												<c:otherwise>
													<p class="comments-message" id="<portlet:namespace />commentsMessage"><liferay-ui:message key="glad-it-helped-what-did-you-find-most-helpful" /></p>
												</c:otherwise>
											</c:choose>

											<liferay-ui:input-field model="<%= KBFeedbackEntry.class %>" bean="<%= feedbackEntry %>" field="comments" />

											<br /><br />

											<input type="button" value='<%= (comments == StringPool.BLANK) ? LanguageUtil.get(pageContext, "add-comments") : LanguageUtil.get(pageContext, "update-comments") %>' onClick="<portlet:namespace />saveFeedbackComments();" />
										</div>

										</form>
									</div>
								</div>

								<a
									href="javascript: Liferay.KnowledgeBase.updateLink();"
									id="<portlet:namespace />feedbackUpdateLink"
									<c:if test="<%= feedbackEntry == null %>">
										style="display: none;"
									</c:if>
								>
									<liferay-ui:message key="update-feedback" />
								</a>
							</c:when>
							<c:otherwise>
								<div class="portlet-msg-info">
									<a href="<%= themeDisplay.getURLSignIn() %>"><liferay-ui:message key="sign-in-to-rate-this-article" /></a>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
				</c:if>

			</div>
		</td>

		<c:if test="<%= !print %>">
			<td valign="top" width="180px">
				<div class="side-boxes">
					<div class="side-box">
						<div class="side-box-title"><liferay-ui:message key="details" /></div>
						<div class="side-box-content">
							<ul class="lfr-component">
								<li>
									<b><liferay-ui:message key="id" /></b>: <%= resourcePrimKey %>
								</li>
								<li>
									<b><liferay-ui:message key="version" /></b>: <%= article.getVersion() %>
								</li>
								<li>
									<b><liferay-ui:message key="updated" /></b>: <%= dateFormatDateTime.format(article.getModifiedDate()) %>
								</li>
								<li>
									<b><liferay-ui:message key="by" /></b>: <%= PortalUtil.getUserName(article.getUserId(), article.getUserName()) %>
								</li>
								<c:if test="<%= !article.isTemplate() %>">
									<li>
										<b><liferay-ui:message key="views" /></b>:
										<%
										TagsAsset asset = TagsAssetLocalServiceUtil.getAsset(KBArticle.class.getName(), resourcePrimKey);
										%>
										<%= asset.getViewCount() %>
									</li>
								</c:if>
								<li>
									<liferay-ui:tags-summary
										className="<%= className %>"
										classPK="<%= resourcePrimKey %>"
										portletURL="<%= taggedArticlesURL %>"
										folksonomy="false"
										message="categories"
									/>
								</li>
								<li>
									<liferay-ui:tags-summary
										className="<%= className %>"
										classPK="<%= article.getResourcePrimKey() %>"
										portletURL="<%= taggedArticlesURL %>"
										folksonomy="true"
									/>
								</li>
							</ul>
						</div>
					</div>
					<c:if test="<%= !article.isDraft() && article.isHead() && !article.isTemplate() %>">
						<div class="side-box">
							<div class="side-box-title"><liferay-ui:message key="attachments" /></div>
							<div class="side-box-content">
								<liferay-ui:icon image="clip" message='<%= attachments.length + " " + LanguageUtil.get(pageContext, "attachments") %>' url="<%= viewAttachmentsURL.toString() %>" method="get" label="<%= true %>" />
							</div>
						</div>
					</c:if>
					<div class="side-box">
						<div class="side-box-title"><liferay-ui:message key="actions" /></div>
						<div class="side-box-content">
							<liferay-ui:icon-list>
								<c:if test="<%= KBArticlePermission.contains(permissionChecker, article, ActionKeys.UPDATE) && article.isHead() %>">
									<liferay-ui:icon image="edit" label="<%= true %>" url="<%= editArticleURL.toString() %>" />
								</c:if>

								<c:if test="<%= !article.isTemplate() && KBArticlePermission.contains(permissionChecker, article, KnowledgeBaseKeys.ADD_CHILD_ARTICLE) %>">
									<liferay-ui:icon image="add_article" message="add-child-article" url="<%= addChildArticleURL.toString() %>" label="<%= true %>" />
								</c:if>

								<liferay-ui:icon image="print" label="<%= true %>" message="print" url='<%= "javascript: " + renderResponse.getNamespace() + "printArticle();" %>' />

								<c:if test="<%= !article.isDraft() && article.isHead() && !article.isTemplate() %>">

									<%
									atom10URL.setParameter("resourcePrimKey", String.valueOf(resourcePrimKey));
									rss10URL.setParameter("resourcePrimKey", String.valueOf(resourcePrimKey));
									rss20URL.setParameter("resourcePrimKey", String.valueOf(resourcePrimKey));
									%>

									<c:if test="<%= ArrayUtil.contains(rssTypes, RSSUtil.ATOM_1_0) %>">
										<liferay-ui:icon image="rss" message="<%= RSSUtil.ATOM_1_0 %>" method="get" url='<%= atom10URL.toString() %>' target="_blank" label="<%= true %>" />
									</c:if>

									<c:if test="<%= ArrayUtil.contains(rssTypes, RSSUtil.RSS_1_0) %>">
										<liferay-ui:icon image="rss" message="<%= RSSUtil.RSS_1_0 %>" method="get" url='<%= rss10URL.toString() %>' target="_blank" label="<%= true %>" />
									</c:if>

									<c:if test="<%= ArrayUtil.contains(rssTypes, RSSUtil.RSS_2_0) %>">
										<liferay-ui:icon image="rss" message="<%= RSSUtil.RSS_2_0 %>" method="get" url='<%= rss20URL.toString() %>' target="_blank" label="<%= true %>" />
									</c:if>

									<%
									String[] extensions = preferences.getValues("extensions", new String[] {"pdf"});

									for (String extension : extensions) {
										ResourceURL convertURL = renderResponse.createResourceURL();

										convertURL.setParameter("actionName", "convert");
										convertURL.setParameter("title", article.getTitle());
										convertURL.setParameter("version", String.valueOf(article.getVersion()));
										convertURL.setParameter("targetExtension", extension);
									%>

										<liferay-ui:icon
											image='<%= "../document_library/" + extension %>'
											message="<%= extension.toUpperCase() %>"
											url='<%= convertURL.toString() %>'
										/>

									<%
									}
									%>

									<c:if test="<%= KBArticlePermission.contains(permissionChecker, article, ActionKeys.SUBSCRIBE) && !article.isDraft() && article.isHead() && !article.isTemplate() %>">
										<c:choose>
											<c:when test="<%= SubscriptionLocalServiceUtil.isSubscribed(user.getCompanyId(), user.getUserId(), KBArticle.class.getName(), resourcePrimKey) %>">
												<portlet:actionURL var="unsubscribeURL">
													<portlet:param name="actionName" value="unsubscribeArticle" />
													<portlet:param name="redirect" value="<%= currentURL %>" />
													<portlet:param name="resourcePrimKey" value="<%= String.valueOf(resourcePrimKey) %>" />
												</portlet:actionURL>

												<liferay-ui:icon image="unsubscribe" url="<%= unsubscribeURL %>" label="<%= true %>" />
											</c:when>
											<c:otherwise>
												<portlet:actionURL var="subscribeURL">
													<portlet:param name="actionName" value="subscribeArticle" />
													<portlet:param name="redirect" value="<%= currentURL %>" />
													<portlet:param name="resourcePrimKey" value="<%= String.valueOf(resourcePrimKey) %>" />
												</portlet:actionURL>

												<liferay-ui:icon image="subscribe" url="<%= subscribeURL %>" label="<%= true %>" />
											</c:otherwise>
										</c:choose>
									</c:if>
								</c:if>

								<liferay-ui:icon image="history" method="get" url="<%= viewHistoryURL.toString() %>" label="<%= true %>" />

								<c:if test="<%= KBArticlePermission.contains(permissionChecker, article, ActionKeys.DELETE) && article.isHead() %>">

									<%
									PortletURL deleteArticleURL = renderResponse.createActionURL();

									deleteArticleURL.setParameter("actionName", Constants.DELETE);
									deleteArticleURL.setParameter("resourcePrimKey", String.valueOf(resourcePrimKey));
									deleteArticleURL.setParameter("redirect", viewAllURL.toString());
									%>

									<liferay-ui:icon-delete url="<%= deleteArticleURL.toString() %>" label="<%= true %>" />
								</c:if>
							</liferay-ui:icon-list>
						</div>
					</div>
					<c:if test="<%= !article.isDraft() && article.isHead() && !article.isTemplate() %>">
						<div class="side-box">
							<div class="side-box-title"><liferay-ui:message key="user-opinions" /></div>
							<div class="side-box-content">
								<ul class="lfr-component">
									<li>
										<b><liferay-ui:message key="helpful" /></b>: <span id="<portlet:namespace />yesPercentage"><%= yesPercentage %>%</span>
									</li>
									<li>
										<b><liferay-ui:message key="unhelpful" /></b>: <span id="<portlet:namespace />noPercentage"><%= noPercentage %>%</span>
									</li>
								</ul>

								<div class="total-votes" id="<portlet:namespace />totalVotes">
									(<%= totalVotes %> <%= LanguageUtil.get(pageContext, totalVotes == 1 ? "vote" : "votes") %>)
								</div>
							</div>
						</div>
					</c:if>
				</div>
			</td>
		</c:if>

	</tr>
	</table>
</div>

<script type="text/javascript">
	if (<%= print %>) {
		print();
	}
	else {
		function <portlet:namespace />printArticle() {
			window.open('<%= printArticleURL %>', '', "directories=0,height=480,left=80,location=1,menubar=1,resizable=1,scrollbars=yes,status=0,toolbar=0,top=180,width=640");
		}
	}

	Liferay.KnowledgeBase.initViewArticle({
		articleResourcePrimKey: '<%= article.getResourcePrimKey() %>',
		averageScore: '<%= feedbackStats.getAverageScore() %>',
		namespace: '<portlet:namespace />',
		feedbackURL: '<%= feedbackURL %>',
		score: '<%= score %>',
		userId: '<%= themeDisplay.getUserId() %>',
		textAverage: '<%= LanguageUtil.get(pageContext, "average") %>',
		textNo: '<%= LanguageUtil.get(pageContext, "please-let-us-know-why-you-found-this-unhelpful") %>',
		textSuccess: '<%= LanguageUtil.get(pageContext, "your-request-processed-successfully") %>',
		textThanksComment: '<%= LanguageUtil.get(pageContext, "thanks-your-feedback-will-help-us-to-improve-this-article") %>',
		textThanksVote: '<%= LanguageUtil.get(pageContext, "thank-you-we-appreciate-your-feedback") %>',
		textUpdateFeedback: '<%= LanguageUtil.get(pageContext, "update-your-feedback-for-this-article") %>',
		textVote: '<%= LanguageUtil.get(pageContext, "vote") %>',
		textVotes: '<%= LanguageUtil.get(pageContext, "votes") %>',
		textYes: '<%= LanguageUtil.get(pageContext, "glad-it-helped-what-did-you-find-most-helpful") %>'
	});
</script>