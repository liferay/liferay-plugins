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
String randomNamespace = PortalUtil.generateRandomKey(request, "advanced_search-vocabulary.jsp") + StringPool.UNDERLINE;

Facet facet = (Facet)request.getAttribute("search-search.jsp-facet");

String[] assetCategoryIds = StringUtil.split(ParamUtil.getString(request, facet.getFieldName()));

FacetConfiguration facetConfiguration = facet.getFacetConfiguration();

JSONObject dataJSONObject = facetConfiguration.getData();

boolean matchByName = false;

if (dataJSONObject.has("matchByName")) {
	matchByName = dataJSONObject.getBoolean("matchByName");
}

long vocabularyId = 0;

if (dataJSONObject.has("vocabularyId")) {
	vocabularyId = dataJSONObject.getLong("vocabularyId");
}

List<AssetVocabulary> vocabularies = new ArrayList<AssetVocabulary>();

if (vocabularyId > 0) {
	vocabularies.add(AssetVocabularyServiceUtil.getVocabulary(vocabularyId));
}
else {
	vocabularies = AssetVocabularyServiceUtil.getGroupsVocabularies(new long[] {themeDisplay.getScopeGroupId(), themeDisplay.getParentGroupId()});
}
%>

<aui:input name="<%= facet.getFieldName() %>" type="hidden" value="<%= StringUtil.merge(assetCategoryIds) %>" />

<aui:field-wrapper cssClass='<%= randomNamespace + "vocabulary vocabulary" %>' label="" name="<%= facet.getFieldName() %>">

	<%
	for (AssetVocabulary vocabulary : vocabularies) {
		String name = HtmlUtil.escape(vocabulary.getName());

		if (vocabularyId > 0) {
			name = StringPool.BLANK;
		}

		String vocabularyNavigation = _buildVocabularyNavigation(vocabulary, assetCategoryIds, matchByName, facet.getFacetCollector());
	%>

		<c:if test="<%= Validator.isNotNull(vocabularyNavigation) %>">
			<%= vocabularyNavigation %>
		</c:if>

	<%
	}
	%>

</aui:field-wrapper>

<aui:script position="inline" use="aui-base">
	var container = A.one('.advanced-search-portlet .menu .search-vocabulary .<%= randomNamespace %>vocabulary');

	if (container) {
		container.delegate(
			'click',
			function(event) {
				var term = event.currentTarget;

				var wasSelfSelected = false;

				var field = document.<portlet:namespace />fm['<portlet:namespace /><%= facet.getFieldName() %>'];

				var currentTerms = A.all('.advanced-search-portlet .menu .search-vocabulary .<%= randomNamespace %>vocabulary .entry.current-term a');

				if (currentTerms) {
					currentTerms.each(
						function(item, index, collection) {
							item.ancestor('.entry').removeClass('current-term');

							if (item == term) {
								wasSelfSelected = true;
							}
						}
					);

					field.value = '';
				}

				if (!wasSelfSelected) {
					term.ancestor('.entry').addClass('current-term');

					field.value = term.attr('data-value');
				}

				submitForm(document.<portlet:namespace />fm);
			},
			'.entry a'
		);
	}
</aui:script>

<%!
private void _buildCategoriesNavigation(List<AssetCategory> categories, String[] assetCategoryIds, boolean matchByName, FacetCollector facetCollector, StringBundler sb) throws Exception {
	for (AssetCategory category : categories) {
		long categoryId = category.getCategoryId();
		String name = HtmlUtil.escape(category.getName());
		String term = String.valueOf(categoryId);
		int frequency = 0;

		if (matchByName) {
			term = name;
		}

		TermCollector termCollector = facetCollector.getTermCollector(term);

		if (termCollector != null) {
			frequency = termCollector.getFrequency();
		}

		sb.append("<li class=\"entry");

		if (ArrayUtil.contains(assetCategoryIds, term)) {
			sb.append(" current-term");
		}

		sb.append("\"><a href=\"#\" data-value=\"");
		sb.append(HtmlUtil.escapeAttribute(term));
		sb.append("\">");
		sb.append(term);
		sb.append("</a> <span class=\"frequency\">(");
		sb.append(frequency);
		sb.append(")</span>");

		List<AssetCategory> categoriesChildren = AssetCategoryServiceUtil.getChildCategories(category.getCategoryId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		if (!categoriesChildren.isEmpty()) {
			sb.append("<ul>");

			_buildCategoriesNavigation(categoriesChildren, assetCategoryIds, matchByName, facetCollector, sb);

			sb.append("</ul>");
		}

		sb.append("</li>");
	}
}

private String _buildVocabularyNavigation(AssetVocabulary vocabulary, String[] assetCategoryIds, boolean matchByName, FacetCollector facetCollector) throws Exception {
	List<AssetCategory> categories = AssetCategoryServiceUtil.getVocabularyRootCategories(vocabulary.getVocabularyId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

	if (categories.isEmpty()) {
		return null;
	}

	StringBundler sb = new StringBundler();

	sb.append("<div class=\"search-vocabulary-list-container\"><ul class=\"search-vocabulary-list\">");

	_buildCategoriesNavigation(categories, assetCategoryIds, matchByName, facetCollector, sb);

	sb.append("</ul></div>");

	return sb.toString();
}
%>