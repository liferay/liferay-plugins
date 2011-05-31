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
String randomNamespace = PortalUtil.generateRandomKey(request, "search-term_list.jsp") + StringPool.UNDERLINE;

Facet facet = (Facet)request.getAttribute("search-search.jsp-facet");

String fieldParam = ParamUtil.getString(request, facet.getFieldName());

FacetConfiguration facetConfiguration = facet.getFacetConfiguration();

JSONObject dataJSONObject = facetConfiguration.getData();

int frequencyThreshold = 0;

if (dataJSONObject.has("frequencyThreshold")) {
	frequencyThreshold = dataJSONObject.getInt("frequencyThreshold");
}

int maxTerms = 0;

if (dataJSONObject.has("maxTerms")) {
	maxTerms = dataJSONObject.getInt("maxTerms");
}

FacetCollector facetCollector = facet.getFacetCollector();

List<TermCollector> termCollectors = facetCollector.getTermCollectors();
%>

<aui:input name="<%= facet.getFieldName() %>" type="hidden" value="<%= fieldParam %>" />

<%
String termListNavigation = _buildTermListNavigation(fieldParam, frequencyThreshold, maxTerms, termCollectors);

if (Validator.isNotNull(termListNavigation)) {
%>

	<aui:field-wrapper cssClass='<%= randomNamespace + "term_list term_list" %>' label="" name="<%= facet.getFieldName() %>">
		<%= termListNavigation %>
	</aui:field-wrapper>

<%
}
else {
%>

	<div class="portlet-msg-info">
		<liferay-ui:message key="there-are-no-matching-terms" />
	</div>

<%
}
%>

<aui:script position="inline" use="aui-base">
	var container = A.one('.advanced-search-portlet .menu .search-term_list .<%= randomNamespace %>term_list');

	if (container) {
		container.delegate(
			'click',
			function(event) {
				var term = event.currentTarget;

				var wasSelfSelected = false;

				var field = document.<portlet:namespace />fm['<portlet:namespace /><%= facet.getFieldName() %>'];

				var currentTerms = A.all('.advanced-search-portlet .menu .search-term_list .<%= randomNamespace %>term_list .entry.current-term a');

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
private String _buildTermListNavigation(String selectedTerm, int frequencyThreshold, int maxTerms, List<TermCollector> termCollectors) throws Exception {
	if (termCollectors.isEmpty()) {
		return null;
	}

	StringBundler sb = new StringBundler();

	sb.append("<ul class=\"term-list\">");

	for (int i = 0; i < termCollectors.size(); i++) {
		TermCollector termCollector = termCollectors.get(i);

		String term = termCollector.getTerm();
		int frequency = termCollector.getFrequency();

		if (((maxTerms > 0) && (i >= maxTerms)) || ((frequencyThreshold > 0) && (frequency < frequencyThreshold))) {
			break;
		}

		sb.append("<li class=\"entry");

		if (term.equals(selectedTerm)) {
			sb.append(" current-term");
		}

		sb.append("\"><a href=\"#\">");
		sb.append(term);
		sb.append("</a> <span class=\"frequency\">(");
		sb.append(frequency);
		sb.append(")</span></li>");
	}

	sb.append("</ul>");

	return sb.toString();
}
%>