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
List<Foo> foos = FooLocalServiceUtil.getFoos(new Field4Comparator());

DateFormat df = new SimpleDateFormat("MMMMM d, yyyy h:mm a");
%>
<b>Welcome to the Sample Service Builder Portlet!</b>

<br />
<br />

<div class="portlet-msg-info">
	There are currently <%= foos.size() %> objects in the database.
</div>

<c:if test="<%= !foos.isEmpty() %>">
	<table class="lfr-table">
		<tr>
			<th>
				<br />
			</th>
			<th>
				Key
			</th>
			<th>
				Field 1
			</th>
			<th>
				Field 2
			</th>
			<th>
				Field 3
			</th>
			<th>
				Field 4
			</th>
			<th>
				Field 5
			</th>
		</tr>

		<%
		for (Foo foo : foos) {
		%>
			<tr>
				<td style="text-align: center;">
					<a href="<portlet:actionURL><portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" /><portlet:param name="fooId" value="<%= String.valueOf(foo.getFooId()) %>" /></portlet:actionURL>">
						<img src="<%= themeDisplay.getPathThemeImages() %>/arrows/02_x.png" />
					</a>
				</td>
				<td>
					<%= foo.getFooId() %>
				</td>
				<td>
					<%= foo.getField1() %>
				</td>
				<td>
					<%= foo.getField2() %>
				</td>
				<td>
					<%= foo.getField3() %>
				</td>
				<td>
					<%= df.format(foo.getField4()) %>
				</td>
				<td>
					<%= foo.getField5() %>
				</td>
			</tr>
		<%
		}
		%>
	</table>
</c:if>

<br />

<div>
	Add new entry:
</div>

<form action="<portlet:actionURL />" method="post" name="<portlet:namespace />fm" onsubmit="submitForm(document.<portlet:namespace />fm);">
	<input name="<portlet:namespace /><%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<input name="<portlet:namespace />redirect" type="hidden" value="<%= PortalUtil.getCurrentURL(request) %>" />

	<table class="lfr-table">
		<tr>
			<td>
				Field 1
			</td>
			<td>
				<liferay-ui:input-field model="<%= Foo.class %>" field="field1" />
			</td>
		</tr>
		<tr>
			<td>
				Field 2
			</td>
			<td>
				<liferay-ui:input-field model="<%= Foo.class %>" field="field2" />
			</td>
		</tr>
		<tr>
			<td>
				Field 3
			</td>
			<td>
				<liferay-ui:input-field model="<%= Foo.class %>" field="field3" />
			</td>
		</tr>
		<tr>
			<td>
				Field 4
			</td>
			<td>
				<liferay-ui:input-field model="<%= Foo.class %>" field="field4" />
			</td>
		</tr>
		<tr>
			<td>
				Field 5
			</td>
			<td>
				<liferay-ui:input-field model="<%= Foo.class %>" field="field5" />
			</td>
		</tr>
	</table>

	<input type="submit" value="<liferay-ui:message key="add" />" />
</form>