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
String randomNamespace = PortalUtil.generateRandomKey(request, "search-range.jsp") + StringPool.UNDERLINE;

Facet facet = (Facet)request.getAttribute("search-search.jsp-facet");

String fieldParam = ParamUtil.getString(request, facet.getFieldName());

FacetConfiguration facetConfiguration = facet.getFacetConfiguration();

JSONObject dataJSONObject = facetConfiguration.getData();

String tagDisplayStyle = "cloud";

if (dataJSONObject.has("displayStyle")) {
	tagDisplayStyle = dataJSONObject.getString("displayStyle");
}

int frequencyThreshold = 0;

if (dataJSONObject.has("frequencyThreshold")) {
	frequencyThreshold = dataJSONObject.getInt("frequencyThreshold");
}

int maxTerms = 10;

if (dataJSONObject.has("maxTerms")) {
	maxTerms = dataJSONObject.getInt("maxTerms");
}

boolean showAssetCount = true;

if (dataJSONObject.has("showAssetCount")) {
	showAssetCount = dataJSONObject.getBoolean("showAssetCount");
}

FacetCollector facetCollector = facet.getFacetCollector();
%>

<aui:input name="<%= facet.getFieldName() %>" type="hidden" value="<%= fieldParam %>" />

<%
String tagsNavigation = _buildTagsNavigation(fieldParam, tagDisplayStyle, frequencyThreshold, maxTerms, showAssetCount, facetCollector.getTermCollectors());

if (Validator.isNotNull(tagsNavigation)) {
%>

	<aui:field-wrapper cssClass='<%= randomNamespace + "asset_tags asset_tags" %>' label="" name="assetTags">
		<%= tagsNavigation %>
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
	var container = A.one('.advanced-search-portlet .menu .search-asset_tags .<%= randomNamespace %>asset_tags');

	if (container) {
		container.delegate(
			'click',
			function(event) {
				var term = event.currentTarget;

				var wasSelfSelected = false;

				var field = document.<portlet:namespace />fm['<portlet:namespace /><%= facet.getFieldName() %>'];

				var currentTerms = A.all('.advanced-search-portlet .menu .search-asset_tags .<%= randomNamespace %>asset_tags .entry.current-term a');

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

					field.value = term.text();
				}

				submitForm(document.<portlet:namespace />fm);
			},
			'.entry a'
		);
	}
</aui:script>

<%!
private String _buildTagsNavigation(String selectedTerm, String displayStyle, int frequencyThreshold, int maxTerms, boolean showAssetCount, List<TermCollector> termCollectors) throws Exception {
	if (termCollectors.isEmpty()) {
		return null;
	}

	StringBundler sb = new StringBundler();

	sb.append("<ul class=\"");

	if (showAssetCount && displayStyle.equals("cloud")) {
		sb.append("tag-cloud");
	}
	else {
		sb.append("tag-list");
	}

	sb.append("\">");

	int maxCount = 1;
	int minCount = 1;

	if (showAssetCount && displayStyle.equals("cloud")) {
		for (int i = 0; i < termCollectors.size(); i++) {
			if (i >= maxTerms) {
				break;
			}

			TermCollector termCollector = termCollectors.get(i);

			int count = termCollector.getFrequency();

			if (frequencyThreshold > count) {
				continue;
			}

			maxCount = Math.max(maxCount, count);
			minCount = Math.min(minCount, count);
		}
	}

	double multiplier = 1;

	if (maxCount != minCount) {
		multiplier = (double)5 / (maxCount - minCount);
	}

	for (int i = 0; i < termCollectors.size(); i++) {
		if (i >= maxTerms) {
			break;
		}

		TermCollector termCollector = termCollectors.get(i);

		String term = termCollector.getTerm();

		int frequency = termCollector.getFrequency();

		int popularity = (int)(1 + ((maxCount - (maxCount - (frequency - minCount))) * multiplier));

		if (frequencyThreshold > frequency) {
			continue;
		}

		sb.append("<li class=\"entry tag-popularity-");
		sb.append(popularity);

		if (term.equals(selectedTerm)) {
			sb.append(" current-term");
		}

		sb.append("\"><a href=\"#\">");
		sb.append(term);
		sb.append("</a>");

		if (showAssetCount) {
			sb.append(" <span class=\"frequency\">(");
			sb.append(frequency);
			sb.append(")</span>");
		}

		sb.append("</li>");
	}

	sb.append("</ul>");

	return sb.toString();
}
%>