<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

<%
long groupId = ParamUtil.getLong(request, "groupId");

Group group = themeDisplay.getScopeGroup();

String keywords = ParamUtil.getString(request, "keywords");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("keywords", keywords);

SearchContainer sc = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_DELTA, portletURL, null, LanguageUtil.format(pageContext, "no-results-were-found-that-matched-the-keywords-x", "<strong>" + HtmlUtil.escape(keywords) + "</strong>"));

SearchContext searchContext = SearchContextFactory.getInstance(request);

searchContext.setStart(sc.getStart());
searchContext.setEnd(sc.getEnd());
searchContext.setAttribute("paginationType", "more");

Facet facet = new ScopeFacet(searchContext);
facet.setStatic(true);
searchContext.addFacet(facet);

facet = new AssetEntriesFacet(searchContext);
facet.setStatic(true);
searchContext.addFacet(facet);

for (FacetConfiguration facetConfiguration : facetConfigurationList) {
	facet = FacetFactoryUtil.create(searchContext, facetConfiguration);

	searchContext.addFacet(facet);
}

Hits hits = FacetedSearcher.getInstance().search(searchContext);

String[] queryTerms = hits.getQueryTerms();

int rightColumn = 100;
%>

<liferay-portlet:renderURL varImpl="searchURL" />

<aui:form action='<%= searchURL.toString() %>' name="fm" method="post">
	<liferay-portlet:renderURLParams varImpl="searchURL" />
	<aui:input name="<%= SearchContainer.DEFAULT_CUR_PARAM %>" type="hidden" value="<%= sc.getCurParam() %>" />

	<aui:layout cssClass="search-layout">
		<aui:column cssClass="menu" first="<%= true %>">
			<aui:fieldset>
				<aui:input inlineField="<%= true %>" label="" name="keywords" size="30" value="<%= keywords %>" />

				<aui:select inlineField="<%= true %>" label="" name="groupId">
					<aui:option label="everything" selected="<%= groupId == 0 %>" value="0" />
					<aui:option label='<%= "this-" + (group.isOrganization() ? "organization" : "site") %>' selected="<%= groupId != 0 %>" value="<%= group.getGroupId() %>" />
				</aui:select>

				<aui:input align="absmiddle" border="0" inlineField="<%= true %>" label="" name="search" src='<%= themeDisplay.getPathThemeImages() + "/common/search.png" %>' title="search" type="image" />

				<portlet:renderURL var="startURL">
					<portlet:param name="action" value="start" />
					<portlet:param name="groupId" value="0" />
				</portlet:renderURL>

				<aui:a href="<%= startURL %>" title="start-over">
					<img align="absmiddle" alt='<liferay-ui:message key="start-over" />' border="0" src='<%= themeDisplay.getPathThemeImages() + "/common/undo.png" %>' />
				</aui:a>
			</aui:fieldset>

			<liferay-ui:panel-container id='<%= renderResponse.getNamespace() + "a" %>' persistState="<%= true %>">

				<%
				List<Facet> facets = ListUtil.fromCollection(searchContext.getFacets().values());

				facets = ListUtil.sort(facets, new PropertyComparator("facetConfiguration.weight", false, false));

				for (Facet curFacet : facets) {
					FacetConfiguration facetConfiguration = curFacet.getFacetConfiguration();

					boolean isStatic = curFacet.isStatic();

					if (!isStatic) {
						String panelLabel = facetConfiguration.getLabel();
						String facetDisplayStyle = facetConfiguration.getDisplayStyle();
						String cssClass = "search-facet search-".concat(facetDisplayStyle);
						String includePage = "/facets/".concat(facetDisplayStyle).concat(".jsp");

						request.setAttribute("search-search.jsp-facet", curFacet);
				%>

						<liferay-ui:panel cssClass="<%= cssClass %>" title="<%= LanguageUtil.get(pageContext, panelLabel) %>">
							<liferay-util:include page="<%= includePage %>" servletContext="<%= application %>" />
						</liferay-ui:panel>

				<%
					}
				}
				%>

			</liferay-ui:panel-container>
		</aui:column>

		<aui:column cssClass="result" last="<%= true %>">
			<c:if test="<%= (hits != null) && (hits.getLength() > 0) %>">
				<liferay-ui:search-container
					searchContainer="<%= sc %>"
				>
					<liferay-ui:search-container-results
						results="<%= hits.toList() %>"
						total="<%= hits.getLength() %>"
					/>

					<liferay-ui:search-container-row
						className="com.liferay.portal.kernel.search.Document"
						escapedModel="<%= false %>"
						modelVar="doc"
						keyProperty="uID"
						stringKey="<%= true %>"
					>

						<liferay-ui:search-container-column-text
							name="document"
						>

							<span class="document">
								<%
								String className = doc.get(Field.ENTRY_CLASS_NAME);
								long classPK = GetterUtil.getLong(doc.get(Field.ENTRY_CLASS_PK));
								%>

								<span class="toggle-details">[+]</span>

								<span class="document-name">

									<%
									String name = doc.get(Field.NAME);

									if (Validator.isNull(name)) {
										name = doc.get(Field.TITLE);
									}

									if (Validator.isNull(name)) {
										name = doc.get("fullName");
									}
									%>

									<%= name %>
								</span>

								<br />

								<span class="document-type"><%= LanguageUtil.get(pageContext, "model.resource." + className) %></span>

								<%

								// Tags

								String[] tags = doc.getValues(Field.ASSET_TAG_NAMES);

								if (Validator.isNotNull(tags[0])) {
									StringBundler rowSB = new StringBundler();

									for (int k = 0; k < tags.length; k++) {
										String tag = tags[k];

										String newKeywords = tag.trim();

										PortletURL tagURL = PortletURLUtil.clone(portletURL, renderResponse);

										tagURL.setParameter(Field.ASSET_TAG_NAMES, newKeywords);

										if (k == 0) {
											rowSB.append("<div class=\"entry-tags\">");
											rowSB.append("<div class=\"taglib-asset-tags-summary\">");
										}

										rowSB.append("<a class=\"tag\" href=\"");
										rowSB.append(tagURL.toString());
										rowSB.append("\">");
										rowSB.append(tag);
										rowSB.append("</a>");

										if ((k + 1) == tags.length) {
											rowSB.append("</div>");
											rowSB.append("</div>");
										}
									}
								%>
									<%= rowSB.toString() %>
								<%
								}

								// Categories

								String[] categoryIds = doc.getValues(Field.ASSET_CATEGORY_IDS);

								if (Validator.isNotNull(categoryIds[0])) {
									StringBundler rowSB = new StringBundler();

									for (int k = 0; k < categoryIds.length; k++) {
										String categoryId = categoryIds[k];

										AssetCategory category = null;

										try {
											category = AssetCategoryLocalServiceUtil.getCategory(GetterUtil.getLong(categoryId));
										}
										catch (NoSuchCategoryException nsce) {
											continue;
										}

										AssetVocabulary vocabulary = AssetVocabularyLocalServiceUtil.getVocabulary(category.getVocabularyId());

										PortletURL categoryURL = PortletURLUtil.clone(portletURL, renderResponse);

										categoryURL.setParameter(Field.ASSET_CATEGORY_NAMES, category.getName());

										if (k == 0) {
											rowSB.append("<div class=\"entry-categories\">");
											rowSB.append("<div class=\"taglib-asset-categories-summary\">");
											rowSB.append("<span class=\"asset-vocabulary\">");
											rowSB.append(HtmlUtil.escape(vocabulary.getTitle(locale)));
											rowSB.append(":</span> ");
										}

										rowSB.append("<a class=\"asset-category\" href=\"");
										rowSB.append(categoryURL.toString());
										rowSB.append("\">");
										rowSB.append(_buildCategoryPath(category, locale));
										rowSB.append("</a>");

										if ((k + 1) == categoryIds.length) {
											rowSB.append("</div>");
											rowSB.append("</div>");
										}
									}
								%>
									<%= rowSB.toString() %>
								<%
								}
								%>

								<table class="lfr-table document-fields aui-helper-hidden">
									<thead>
										<tr>
											<th class="key"><liferay-ui:message key="key" /></th>
											<th class="value"><liferay-ui:message key="value" /></th>
										</tr>
									</thead>

									<tbody>

										<%
										List<Map.Entry<String,Field>> fields = new LinkedList(doc.getFields().entrySet());

										Collections.sort(fields, new Comparator<Map.Entry<String,Field>>() {
											public int compare(Map.Entry<String,Field> o1, Map.Entry<String,Field> o2) {
												return o1.getKey().compareTo(o2.getKey());
											}
										});

										for (Map.Entry<String,Field> entry : fields) {
											Field field = entry.getValue();

											if (field.getName().equals(Field.UID)) {
												continue;
											}

											boolean isArray = field.getValues().length > 1;
										%>
											<tr>
												<td class="key" valign="top">
													<strong><%= HtmlUtil.escape(field.getName()) %></strong>

													<br />

													<em>
														<liferay-ui:message key="array" /> = <%= isArray %>,
														<liferay-ui:message key="boost" /> = <%= field.getBoost() %>, <br />
														<liferay-ui:message key="numeric" /> = <%= field.isNumeric() %>,
														<liferay-ui:message key="tokenized" /> = <%= field.isTokenized() %>
													</em>
												</td>
												<td class="value" valign="top">
													<div class="container">
														<code>
															<%
															String[] values = field.getValues();
															%>

															<c:if test="<%= isArray %>">[</c:if><%for (int i = 0; i < values.length; i++) {%><c:if test="<%= i > 0 %>">, </c:if><c:if test="<%= !field.isNumeric() %>">"</c:if><%= HtmlUtil.escape(values[i]) %><c:if test="<%= !field.isNumeric() %>">"</c:if><%}%><c:if test="<%= isArray %>">]</c:if>
														</code>
													</div>
												</td>
											</tr>
										<%
										}
										%>

									</tbody>
								</table>
							</span>
						</liferay-ui:search-container-column-text>

					</liferay-ui:search-container-row>

					<div class="search-paginator-container">
						<liferay-ui:search-paginator searchContainer="<%= sc %>" type="more" />
					</div>

					<liferay-ui:search-iterator paginate="<%= false %>" />

					<div class="search-paginator-container">
						<liferay-ui:search-paginator searchContainer="<%= sc %>" type="more" />
					</div>
				</liferay-ui:search-container>
			</c:if>
		</aui:column>
	</aui:layout>

	<br />

	<c:if test="<%= (hits != null) && (hits.getQuery() != null) %>">
		<table class="full-query">
			<tr>
				<td valign="top">
					<div class="container">
						<code>
							<%= HtmlUtil.escape(hits.getQuery().toString()) %>
						</code>
					</div>
				</td>
			</tr>
		</table>

		<em>Completed in <%= hits.getSearchTime() %> seconds.</em>
	</c:if>
</aui:form>

<aui:script use="aui-base">
	var pageLinks = A.one('.advanced-search-portlet .result .page-links');

	if (pageLinks) {
		pageLinks.delegate(
			'click',
			function(event) {
				document.<portlet:namespace />fm.<portlet:namespace /><%= SearchContainer.DEFAULT_CUR_PARAM %>.value = 1;

				submitForm(document.<portlet:namespace />fm);

				event.preventDefault();
			},
			'a.first'
		);

		pageLinks.delegate(
			'click',
			function(event) {
				submitForm(document.<portlet:namespace />fm);

				event.preventDefault();
			},
			'a.previous'
		);

		pageLinks.delegate(
			'click',
			function(event) {
				document.<portlet:namespace />fm.<portlet:namespace /><%= SearchContainer.DEFAULT_CUR_PARAM %>.value = parseInt(document.<portlet:namespace />fm.<portlet:namespace /><%= SearchContainer.DEFAULT_CUR_PARAM %>.value) + 2;

				submitForm(document.<portlet:namespace />fm);

				console.log("here");

				event.preventDefault();
			},
			'a.next'
		);
	}

	A.one('.advanced-search-portlet .result').delegate(
		'click',
		function(event) {
			var handle = event.currentTarget;
			var rowTD = handle.ancestor('.results-row td');

			var documentFields = rowTD.one('.document-fields');

			if (handle.text() == '[+]') {
				documentFields.show();
				handle.text('[-]');
			}
			else if (handle.text() == '[-]') {
				documentFields.hide();
				handle.text('[+]');
			}
		},
		'.results-row td .toggle-details'
	);
</aui:script>

<%!
private String _buildCategoryPath(AssetCategory category, Locale locale) throws PortalException, SystemException {
	List<AssetCategory> ancestorCategories = category.getAncestors();

	if (ancestorCategories.isEmpty()) {
		return category.getName();
	}

	Collections.reverse(ancestorCategories);

	StringBundler sb = new StringBundler(ancestorCategories.size() * 2 + 1);

	for (AssetCategory ancestorCategory : ancestorCategories) {
		sb.append(ancestorCategory.getTitle(locale));
		sb.append(" &raquo; ");
	}

	sb.append(category.getName());

	return sb.toString();
}
%>