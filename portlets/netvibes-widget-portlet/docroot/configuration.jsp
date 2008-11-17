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

<%@ include file="/init.jsp" %>

<%
String query = ParamUtil.getString(renderRequest, "query", StringPool.BLANK);
String sort = ParamUtil.getString(renderRequest, "sort", StringPool.BLANK);
int category = ParamUtil.getInteger(renderRequest, "category", 0);
String region = ParamUtil.getString(renderRequest, "region", "all");
int pageNumber = ParamUtil.getInteger(renderRequest, "pageNumber", 1);

List<NetvibesWidget> widgets = NetvibesUtil.getNetvibesWidgets(query, sort, category, region, pageNumber);

List<NetvibesCategory> categories = NetvibesUtil.getNetvibesCategories();
List<NetvibesRegion> regions = NetvibesUtil.getNetvibesRegions();
List<NetvibesSort> sorts = NetvibesUtil.getNetvibesSorts();
int numberOfWidgets = NetvibesUtil.getNumberOfWidgets();
int numberOfPages = (numberOfWidgets + NetvibesUtil.NUMBER_OF_WIDGETS_PER_PAGE-1)/NetvibesUtil.NUMBER_OF_WIDGETS_PER_PAGE;

String curWidgetAuthor = preferences.getValue("widgetAuthor", "author");
String curWidgetSummary = preferences.getValue("widgetSummary", "summary");
String curWidgetThumbnail = preferences.getValue("widgetThumbnail", "author");
%>

<script type="text/javascript">
	function <portlet:namespace />addWidgetByUrl() {
		document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = "<%= Constants.ADD %>";

		submitForm(document.<portlet:namespace />fm);
	}

	function <portlet:namespace />chooseWidget(url, title, author, summary, thumbnail) {
		document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = "<%= Constants.UPDATE %>";
		document.<portlet:namespace />fm.<portlet:namespace />widgetUrl.value = url;
		document.<portlet:namespace />fm.<portlet:namespace />widgetTitle.value = title;
		document.<portlet:namespace />fm.<portlet:namespace />widgetAuthor.value = author;
		document.<portlet:namespace />fm.<portlet:namespace />widgetSummary.value = summary;
		document.<portlet:namespace />fm.<portlet:namespace />widgetThumbnail.value = thumbnail;

		submitForm(document.<portlet:namespace />fm);
	}

	function <portlet:namespace />page(number) {
		document.<portlet:namespace />fm.<portlet:namespace />pageNumber.value = number;

		<portlet:namespace />search();
	}

	function <portlet:namespace />search() {
		document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = "<%= Constants.SEARCH %>";

		submitForm(document.<portlet:namespace />fm);
	}
</script>

<form action="<liferay-portlet:actionURL portletConfiguration="true" />" class="uni-form netvibes-widget-portlet" method="post" id="<portlet:namespace />fm" name="<portlet:namespace />fm">
<input name="<portlet:namespace /><%= Constants.CMD %>" type="hidden" value="" />
<input name="<portlet:namespace />widgetUrl" type="hidden" value="" />
<input name="<portlet:namespace />widgetTitle" type="hidden" value="" />
<input name="<portlet:namespace />widgetAuthor" type="hidden" value="" />
<input name="<portlet:namespace />widgetSummary" type="hidden" value="" />
<input name="<portlet:namespace />widgetThumbnail" type="hidden" value="" />
<input name="<portlet:namespace />pageNumber" type="hidden" value="" />

<div class="lfr-widget-list">
	<div class="float-container lfr-current-widget">
		<h3><liferay-ui:message key="current-widget" /></h3>

		<img alt="<%= curWidgetTitle %>" class="widget-screenshot" src="<%= curWidgetThumbnail %>" title="<%= curWidgetTitle %>" />

		<div class="widget-details">
			<h4 class="widget-title"><%= curWidgetTitle %></h4>

			<dl>
				<c:if test="<%= Validator.isNotNull(curWidgetSummary) %>">
					<dt>
						<liferay-ui:message key="summary" />
					</dt>
					<dd>
						<%= curWidgetSummary %>
					</dd>
				</c:if>

				<c:if test="<%= Validator.isNotNull(curWidgetAuthor) %>">
					<dt>
						<liferay-ui:message key="author" />
					</dt>
					<dd>
						<%= curWidgetAuthor %>
					</dd>
				</c:if>
			</dl>
		</div>
	</div>

	<div class="float-container lfr-available-widgets">
		<h3>
			<span class="header-title">
				<%= LanguageUtil.format(pageContext, "available-widgets-x", numberOfWidgets) %>
			</span>

			<span class="install-widgets">
				<%@ include file="/pages.jspf" %>
			</span>
		</h3>

		<fieldset class="block-labels">
			<div>
				<div class="ctrl-holder ">
					<input id="<portlet:namespace/>query" name="<portlet:namespace/>addWidget" size="50" type="text" value="" />
				</div>

				<div class="button-holder">
					<input type="button" value="<liferay-ui:message key="add-widget-by-url" />" onclick="<portlet:namespace />addWidgetByUrl(); return false;" />
				</div>
			</div>
		</fieldset>

		<fieldset class="block-labels">

			<div class="ctrl-holder">
				<label for="<portlet:namespace/>sort"><liferay-ui:message key="sort" /></label>
				<select id="<portlet:namespace/>sort" name="<portlet:namespace/>sort">
					<option <%= Validator.isNull(sort) ? "selected" : ""%> value=""><liferay-ui:message key="all" /></option>

					<%
					for (NetvibesSort netvibesSort : sorts) {
					%>
						<option <%= Validator.equals(netvibesSort.getValue(), sort) ? "selected" : ""%> value="<%= netvibesSort.getValue() %>"><liferay-ui:message key="<%= netvibesSort.getLabel() %>" /></option>
					<%
					}
					%>
				</select>
			</div>

			<div class="ctrl-holder">
				<label for="<portlet:namespace/>category"><liferay-ui:message key="category" /></label>
				<select id="<portlet:namespace/>category" name="<portlet:namespace/>category">
					<option <%= category == 0 ? "selected" : ""%> value="0"><liferay-ui:message key="all" /></option>

					<%
					for (NetvibesCategory netvibesCategory : categories) {
					%>
						<option <%= netvibesCategory.getId() == category ? "selected" : ""%> value="<%= netvibesCategory.getId() %>"><liferay-ui:message key="<%= netvibesCategory.getLabel() %>" /></option>
					<%
					}
					%>
				</select>
			</div>

			<div class="ctrl-holder">
				<label for="<portlet:namespace/>region"><liferay-ui:message key="region" /></label>
				<select id="<portlet:namespace/>region" name="<portlet:namespace/>region">
					<option <%= Validator.equals(region,"all") ? "selected" : ""%> value="all"><liferay-ui:message key="all" /></option>

					<%
					for (NetvibesRegion netvibesRegion : regions) {
					%>
						<option <%= Validator.equals(netvibesRegion.getId(), region) ? "selected" : ""%> value="<%= netvibesRegion.getId() %>"><liferay-ui:message key="<%= netvibesRegion.getLabel() %>" /></option>
					<%
					}
					%>
				</select>
			</div>

			<div class="ctrl-holder ">
				<label for="<portlet:namespace/>region"><liferay-ui:message key="text" /></label>

				<input id="<portlet:namespace/>query" name="<portlet:namespace/>query" type="text" value="<%= query %>" />
			</div>

			<div class="button-holder">
				<label>&nbsp;</label>

				<input type="button" value="<liferay-ui:message key="search-widgets" />" onclick="<portlet:namespace />search(); return false;" />
			</div>

		</fieldset>

		<c:choose>
			<c:when test="<%= widgets.isEmpty() %>">
				<span class="portlet-msg-info">
					<liferay-ui:message key="no-widgets-were-found" />
				</span>
			</c:when>
			<c:otherwise>
				<ul class="lfr-component lfr-widget-list">
					<%
					for (NetvibesWidget widget : widgets){
					%>
						<li>
							<a class="widget-entry" href="javascript: ;" onclick="<portlet:namespace />chooseWidget('<%= HtmlUtil.escape(widget.getUrl())%>','<%= UnicodeFormatter.toString(widget.getTitle())%>','<%= UnicodeFormatter.toString(widget.getAuthor())%>','<%= UnicodeFormatter.toString(widget.getSummary())%>','<%= UnicodeFormatter.toString(widget.getThumbnail())%>'); return false;">
								<span class="widget-title">
									<%= StringUtil.shorten(widget.getTitle(),25) %>
								</span>

								<img alt="<%= widget.getTitle() %>" class="widget-thumbnail" src="<%= widget.getThumbnail()%>" title="<%= widget.getTitle() %>" />
								<span class="widget-summary">
									<%= StringUtil.shorten(widget.getSummary(),215) %>
								</span>
							</a>
						</li>
					<%
					}
					%>
				</ul>
			</c:otherwise>
		</c:choose>

		<div class="page">
			<%@ include file="/pages.jspf" %>
		</div>
	</div>
</div>
</form>