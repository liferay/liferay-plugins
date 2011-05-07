<%
/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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
String randomNamespace = PortalUtil.generateRandomKey(request, "advanced_search-vocabulary.jsp") + StringPool.UNDERLINE;

Facet facet = (Facet)request.getAttribute("search-search.jsp-facet");

String fieldName = facet.getFieldName();
String[] assetCategoryIds = StringUtil.split(ParamUtil.getString(request, fieldName));

boolean matchByName = false;
long vocabularyId = 0;

JSONObject data = facet.getFacetConfiguration().getData();

if (data.has("matchByName")) {
	matchByName = data.getBoolean("matchByName");
}

if (data.has("vocabularyId")) {
	vocabularyId = data.getLong("vocabularyId");
}

List<AssetVocabulary> vocabularies = new ArrayList<AssetVocabulary>();

if (vocabularyId > 0) {
	vocabularies.add(AssetVocabularyServiceUtil.getVocabulary(vocabularyId));
}
else {
	vocabularies = AssetVocabularyServiceUtil.getGroupsVocabularies(new long[] {themeDisplay.getScopeGroupId(), themeDisplay.getParentGroupId()});
}

FacetCollector facetCollector = facet.getFacetCollector();
%>

<aui:input name="<%= fieldName %>" type="hidden" value="<%= StringUtil.merge(assetCategoryIds) %>" />

<aui:field-wrapper cssClass='<%= randomNamespace + "vocabulary vocabulary" %>' label="" name="<%= fieldName %>">

	<%
	for (AssetVocabulary vocabulary : vocabularies) {
		String name = HtmlUtil.escape(vocabulary.getName());

		if (vocabularyId > 0) {
			name = StringPool.BLANK;
		}

		String vocabularyNavigation = _buildVocabularyNavigation(vocabulary, assetCategoryIds, matchByName, facetCollector);
	%>

		<c:if test="<%= Validator.isNotNull(vocabularyNavigation) %>">
			<%= vocabularyNavigation %>
		</c:if>

	<%
	}
	%>

</aui:field-wrapper>

<aui:script position="inline" use="aui-base">
	var container = A.one('.advancedsearch-portlet .menu .search-vocabulary .<%= randomNamespace %>vocabulary');

	if (container) {
		container.delegate(
			'click',
			function(event) {
				var term = event.currentTarget;
				var wasSelfSelected = false;

				var field = document.<portlet:namespace />fm['<portlet:namespace /><%= fieldName %>'];

				var currentTerms = A.all('.advancedsearch-portlet .menu .search-vocabulary .<%= randomNamespace %>vocabulary .entry.current-term a');

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