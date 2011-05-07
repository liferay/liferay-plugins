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
String randomNamespace = PortalUtil.generateRandomKey(request, "search-term_list.jsp") + StringPool.UNDERLINE;

Facet facet = (Facet)request.getAttribute("search-search.jsp-facet");

String fieldName = facet.getFieldName();
String fieldParam = ParamUtil.getString(request, fieldName);

int frequencyThreshold = 0;
int maxTerms = 0;

JSONObject data = facet.getFacetConfiguration().getData();

if (data.has("frequencyThreshold")) {
	frequencyThreshold = data.getInt("frequencyThreshold");
}

if (data.has("maxTerms")) {
	maxTerms = data.getInt("maxTerms");
}

FacetCollector facetCollector = facet.getFacetCollector();

List<TermCollector> termCollectors = facetCollector.getTermCollectors();
%>

<aui:input name="<%= fieldName %>" type="hidden" value="<%= fieldParam %>" />

<%
String termListNavigation = _buildTermListNavigation(fieldParam, frequencyThreshold, maxTerms, termCollectors);

if (Validator.isNotNull(termListNavigation)) {
%>

	<aui:field-wrapper cssClass='<%= randomNamespace + "term_list term_list" %>' label="" name="<%= fieldName %>">
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
	var container = A.one('.advancedsearch-portlet .menu .search-term_list .<%= randomNamespace %>term_list');

	if (container) {
		container.delegate(
			'click',
			function(event) {
				var term = event.currentTarget;
				var wasSelfSelected = false;

				var field = document.<portlet:namespace />fm['<portlet:namespace /><%= fieldName %>'];

				var currentTerms = A.all('.advancedsearch-portlet .menu .search-term_list .<%= randomNamespace %>term_list .entry.current-term a');

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