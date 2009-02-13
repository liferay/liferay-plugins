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

Code:

<br /><br />

<pre>&lt;liferay-ui:tabs
&nbsp;&nbsp;&nbsp;&nbsp;names="One,Two,Three"
&nbsp;&nbsp;&nbsp;&nbsp;refresh="&lt;%= false %&gt;"
&gt;
&nbsp;&nbsp;&nbsp;&nbsp;&lt;liferay-ui:section&gt;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;One body
&nbsp;&nbsp;&nbsp;&nbsp;&lt;/liferay-ui:section&gt;
&nbsp;&nbsp;&nbsp;&nbsp;&lt;liferay-ui:section&gt;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Two body
&nbsp;&nbsp;&nbsp;&nbsp;&lt;/liferay-ui:section&gt;
&nbsp;&nbsp;&nbsp;&nbsp;&lt;liferay-ui:section&gt;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Three body
&nbsp;&nbsp;&nbsp;&nbsp;&lt;/liferay-ui:section&gt;
&lt;/liferay-ui:tabs&gt;</pre>

<div class="separator"></div>

Result:

<br /><br />

<liferay-ui:tabs
	names="One,Two,Three"
	refresh="<%= false %>"
>
	<liferay-ui:section>
		One body
	</liferay-ui:section>
	<liferay-ui:section>
		Two body
	</liferay-ui:section>
	<liferay-ui:section>
		Three body
	</liferay-ui:section>
</liferay-ui:tabs>

<div class="separator"></div>

&laquo; <a href="<portlet:renderURL />">Back</a>