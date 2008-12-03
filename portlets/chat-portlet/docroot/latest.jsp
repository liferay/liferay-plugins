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
if (!themeDisplay.isSignedIn()) {
	return;
}

String activeBrowserKey = ParamUtil.getString(request, "activeBrowserKey");
boolean activeBrowser = ParamUtil.getBoolean(request, "activeBrowser");

// Status

Status status = null;

try {
	status = StatusLocalServiceUtil.getUserStatus(themeDisplay.getUserId());
}
catch (NoSuchStatusException nsse) {
}

if (!activeBrowser && (status != null) && !status.getActiveBrowserKey().equals(activeBrowserKey)) {

	// JSON

	JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

	jsonObj.put("activeBrowser", false);
%>

	<%= jsonObj %>

<%
	return;
}

// Entries

Map<Long, Long> latestCreateDates = new HashMap<Long, Long>();

long createDate = ParamUtil.getLong(request, "createDate");

if ((createDate == 0) || (createDate == 1)) {
	if (status != null) {
		createDate = status.getModifiedDate();
	}
	else {
		createDate = System.currentTimeMillis() - Time.MINUTE;
	}
}

List<Entry> entries = EntryLocalServiceUtil.getNewEntries(themeDisplay.getUserId(), createDate, 0, SearchContainer.DEFAULT_DELTA);

Collections.reverse(entries);

JSONArray entriesJSON = JSONFactoryUtil.createJSONArray();

for (Entry entry : entries) {
	JSONObject entryJSON = JSONFactoryUtil.createJSONObject();

	entryJSON.put("createDate", entry.getCreateDate());
	entryJSON.put("fromUserId", entry.getFromUserId());

	if (entry.getFromUserId() != themeDisplay.getUserId()) {
		User fromUser = UserLocalServiceUtil.getUserById(entry.getFromUserId());

		entryJSON.put("fromFullName", fromUser.getFullName());
		entryJSON.put("fromPortraitId", fromUser.getPortraitId());
	}

	entryJSON.put("toUserId", entry.getToUserId());
	entryJSON.put("content", HtmlUtil.escape(entry.getContent()));

	entriesJSON.put(entryJSON);

	if (Validator.isNotNull(entry.getContent())) {
		latestCreateDates.put(entry.getToUserId(), entry.getCreateDate());
	}
}

// Buddies

%>

<%@ include file="/buddies.jsp" %>

<%
JSONArray buddiesJSON = JSONFactoryUtil.createJSONArray();

for (Object[] buddy : buddies) {
	long userId = (Long)buddy[0];
	String firstName = (String)buddy[1];
	String middleName = (String)buddy[2];
	String lastName = (String)buddy[3];
	long portraitId = (Long)buddy[4];
	boolean awake = (Boolean)buddy[5];

	String fullName = ContactConstants.getFullName(firstName, middleName, lastName);

	if (userId == themeDisplay.getUserId()) {
		continue;
	}

	String statusMessage = null;

	try {
		Status buddyStatus = StatusLocalServiceUtil.getUserStatus(userId);

		awake = buddyStatus.getAwake();
		statusMessage = buddyStatus.getMessage();
	}
	catch (NoSuchStatusException nsse) {
	}

	Long latestCreateDate = latestCreateDates.get(userId);

	JSONObject curUserJSON = JSONFactoryUtil.createJSONObject();

	curUserJSON.put("userId", userId);
	curUserJSON.put("fullName", fullName);
	curUserJSON.put("portraitId", portraitId);
	curUserJSON.put("awake", awake);
	curUserJSON.put("statusMessage", statusMessage);
	curUserJSON.put("latestCreateDate", (latestCreateDate != null) ? latestCreateDate.longValue() : 0);

	buddiesJSON.put(curUserJSON);
}

// Status

boolean online = ParamUtil.getBoolean(request, "online", true);
boolean awake = ParamUtil.getBoolean(request, "awake", true);
String activePanelId = ParamUtil.getString(request, "activePanelId");
String statusMessage = ParamUtil.getString(request, "statusMessage");
boolean playSound = ParamUtil.getBoolean(request, "playSound", true);

StatusLocalServiceUtil.updateStatus(themeDisplay.getUserId(), online, awake, activeBrowserKey, activePanelId, statusMessage, playSound);

// JSON

JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

jsonObj.put("activeBrowser", true);
jsonObj.put("buddies", buddiesJSON);
jsonObj.put("entries", entriesJSON);
%>

<%= jsonObj %>