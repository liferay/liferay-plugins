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
String categoryId = ParamUtil.getString(request, "categoryId", "all");
int start = ParamUtil.getInteger(request, "start", 0);

String url = "http://www.google.com/ig/directory?synd=open&cat=" + categoryId + "&start=" + start + "&sa=N";

GGData data = GGUtil.getData(url);
%>

<%--<liferay-portlet:preview
	portletName="<%= portletResource %>"
/>

<div class="separator"><!-- --></div>--%>

<c:choose>
	<c:when test="<%= data != null %>">
		<script type="text/javascript">
			function <portlet:namespace />chooseGadget(gadgetId) {
				document.<portlet:namespace />fm.<portlet:namespace />gadgetId.value = gadgetId;
				submitForm(document.<portlet:namespace />fm);
			}
		</script>

		<form action="<liferay-portlet:actionURL portletConfiguration="true" />" method="post" name="<portlet:namespace />fm">
		<input name="<portlet:namespace /><%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
		<input name="<portlet:namespace />gadgetId" type="hidden" value="" />

		<table class="lfr-table" width="100%">
		<tr>
			<td valign="top">
				<liferay-ui:message key="categories" />

				<br /><br />

				<%
				List categories = data.getCategories();

				for (int i = 0; i < categories.size(); i++) {
					GGCategory category = (GGCategory)categories.get(i);
				%>

					<c:choose>
						<c:when test="<%= Validator.isNotNull(category.getName()) %>">
							<a href="<liferay-portlet:actionURL portletConfiguration="true"><portlet:param name="categoryId" value="<%= category.getCategoryId() %>" /></liferay-portlet:actionURL>"><%= category.getName() %></a><br />
						</c:when>
						<c:otherwise>
							<br />
						</c:otherwise>
					</c:choose>

				<%
				}
				%>

			</td>
			<td>
				<liferay-ui:message key="add-a-google-gadget-by-specifying-a-gadget-url,-or-choose-from-the-gadgets-below" />

				<br /><br />

				<input name="<portlet:namespace />customGadgetId" size="70" type="text" /> <input onclick="<portlet:namespace />chooseGadget(document.<portlet:namespace />fm.<portlet:namespace />customGadgetId.value);" type="button" value="<liferay-ui:message key="add" />" />

				<%
				GGPagination pagination = data.getPagination();
				%>

				<div style="text-align: right;">
					<%= pagination.getStart() %> - <%= pagination.getEnd() %> of <%= pagination.getTotal() %>

					<span style="padding-left: 10px;">
						<c:if test="<%= pagination.getStart() >= pagination.getDelta() %>">
							<a href="<liferay-portlet:actionURL portletConfiguration="true"><portlet:param name="categoryId" value="<%= categoryId %>" /><portlet:param name="start" value="<%= String.valueOf(start - pagination.getDelta()) %>" /></liferay-portlet:actionURL>">&laquo; <liferay-ui:message key="previous" /></a>
						</c:if>
					</span>

					<span style="padding-left: 10px;">
						<c:if test="<%= (pagination.getStart() + pagination.getDelta()) <= pagination.getTotal() %>">
							<a href="<liferay-portlet:actionURL portletConfiguration="true"><portlet:param name="categoryId" value="<%= categoryId %>" /><portlet:param name="start" value="<%= String.valueOf(start + pagination.getDelta()) %>" /></liferay-portlet:actionURL>"><liferay-ui:message key="next" /> &raquo;</a>
						</c:if>
					</span>
				</div>

				<br />

				<liferay-ui:table-iterator
					list="<%= data.getEntries() %>"
					listType="com.liferay.portlet.googlegadget.model.GGEntry"
					rowLength="4"
					width="100%"
				>
					<div style="text-align: center;">
						<%= tableIteratorObj.getName() %>

						<div style="padding: 5px;">
							<img src="<%= tableIteratorObj.getImage() %>" />
						</div>

						<input onclick="<portlet:namespace />chooseGadget('<%= tableIteratorObj.getGadgetId() %>');" type="button" value="<liferay-ui:message key="choose" />" />
					</div>

					<div style="padding-top: 20px;" />
				</liferay-ui:table-iterator>

				<div style="text-align: right;">
					<%= pagination.getStart() %> - <%= pagination.getEnd() %> of <%= pagination.getTotal() %>

					<span style="padding-left: 10px;">
						<c:if test="<%= pagination.getStart() >= pagination.getDelta() %>">
							<a href="<liferay-portlet:actionURL portletConfiguration="true"><portlet:param name="categoryId" value="<%= categoryId %>" /><portlet:param name="start" value="<%= String.valueOf(start - pagination.getDelta()) %>" /></liferay-portlet:actionURL>">&laquo; <liferay-ui:message key="previous" /></a>
						</c:if>
					</span>

					<span style="padding-left: 10px;">
						<c:if test="<%= (pagination.getStart() + pagination.getDelta()) <= pagination.getTotal() %>">
							<a href="<liferay-portlet:actionURL portletConfiguration="true"><portlet:param name="categoryId" value="<%= categoryId %>" /><portlet:param name="start" value="<%= String.valueOf(start + pagination.getDelta()) %>" /></liferay-portlet:actionURL>"><liferay-ui:message key="next" /> &raquo;</a>
						</c:if>
					</span>
				</div>
			</td>
		</tr>
		</table>

		</form>
	</c:when>
	<c:otherwise>
		<span class="portlet-msg-error">
		<liferay-ui:message key="an-unexpected-error-occurred-while-connecting-to-google" />
		</span>
	</c:otherwise>
</c:choose>