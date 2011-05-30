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
Facet facet = (Facet)request.getAttribute("search-search.jsp-facet");

String fieldName = facet.getFieldName();
String fieldParam = ParamUtil.getString(request, fieldName);

int frequencyThreshold = 0;
String[] values = new String[0];

JSONObject data = facet.getFacetConfiguration().getData();

if (data.has("frequencyThreshold")) {
	frequencyThreshold = data.getInt("frequencyThreshold");
}

if (data.has("values")) {
	JSONArray valuesJSONArray = data.getJSONArray("values");

	values = new String[valuesJSONArray.length()];

	for (int i = 0; i < valuesJSONArray.length(); i++) {
		values[i] = valuesJSONArray.getString(i);
	}
}

FacetCollector facetCollector = facet.getFacetCollector();
%>

<aui:input name="<%= fieldName %>" type="hidden" value="<%= fieldParam %>" />

<%
String assetEntriesNavigation = _buildAssetEntriesNavigation(pageContext, locale, fieldParam, frequencyThreshold, values, facetCollector);

if (Validator.isNotNull(assetEntriesNavigation)) {
%>

	<aui:field-wrapper cssClass="asset_entries" label="" name="<%= fieldParam %>">
		<%= assetEntriesNavigation %>
	</aui:field-wrapper>

<%
}
else {
%>

	<div class="portlet-msg-info">
		<liferay-ui:message key="there-are-no-matches" />
	</div>

<%
}
%>

<aui:script position="inline" use="aui-base">
	var container = A.one('.advanced-search-portlet .menu .search-asset_entries .asset_entries');

	console.log('container', container);

	if (container) {
		container.delegate(
			'click',
			function(event) {
				var term = event.currentTarget;
				var wasSelfSelected = false;

				var field = document.<portlet:namespace />fm['<portlet:namespace /><%= fieldName %>'];

				var currentTerms = A.all('.advanced-search-portlet .menu .search-asset_entries .asset_entries .entry.current-term a');

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
private String _buildAssetEntriesNavigation(PageContext pageContext, Locale locale, String selectedTerm, long frequencyThreshold, String[] values, FacetCollector facetCollector) throws Exception {
	List<TermCollector> termCollectors = facetCollector.getTermCollectors();

	if (termCollectors.isEmpty()) {
		return null;
	}

	List<String> assetTypes = new ArrayList<String>();

	for (String className : values) {
		if (!assetTypes.contains(className)) {
			if (ArrayUtil.contains(values, className)) {
				assetTypes.add(className);
			}
		}
	}

	assetTypes = ListUtil.sort(assetTypes, new ModelResourceComparator(locale));

	StringBundler sb = new StringBundler();

	sb.append("<ul class=\"asset-type\">");

	for (int i = 0; i < assetTypes.size(); i++) {
		String assetType = assetTypes.get(i);

		TermCollector termCollector = facetCollector.getTermCollector(assetType);

		int frequency = 0;

		if (termCollector != null) {
			frequency = termCollector.getFrequency();
		}

		if (frequency < frequencyThreshold) {
			continue;
		}

		sb.append("<li class=\"entry");

		if (assetType.equals(selectedTerm)) {
			sb.append(" current-term");
		}

		sb.append("\"><a href=\"#\" data-value=\"");
		sb.append(HtmlUtil.escapeAttribute(assetType));
		sb.append("\">");
		sb.append(LanguageUtil.get(pageContext, "model.resource." + assetType));
		sb.append("</a> <span class=\"frequency\">(");
		sb.append(frequency);
		sb.append(")</span></li>");
	}

	sb.append("</ul>");

	return sb.toString();
}
%>