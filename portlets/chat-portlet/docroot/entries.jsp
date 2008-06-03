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

<%@ include file="/json_init.jsp" %>

<%
long createDate = ParamUtil.getLong(request, "createDate");

List<Entry> entries = EntryLocalServiceUtil.getEntries(themeDisplay.getUserId(), createDate, 0, 20);

JSONObject jsonObj = new JSONObject();

JSONArray jsonArray = new JSONArray();

JSONUtil.put(jsonObj, "entries", jsonArray);

for (Entry entry : entries) {
	JSONObject entryJSON = new JSONObject();

	JSONUtil.put(entryJSON, "userId", entry.getUserId());
	JSONUtil.put(entryJSON, "createDate", entry.getCreateDate());
	JSONUtil.put(entryJSON, "content", entry.getContent());
	JSONUtil.put(entryJSON, "receiverUserId", entry.getReceiverUserId());

	jsonArray.put(entryJSON);
}
%>

<%= jsonObj %>