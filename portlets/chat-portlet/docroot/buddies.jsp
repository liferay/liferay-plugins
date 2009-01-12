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

<%
long buddiesModifiedDate = System.currentTimeMillis() - Time.MINUTE;

List<Object[]> buddies = null;

if (_BUDDY_LIST_STRATEGY.equals("all")) {
	buddies = StatusLocalServiceUtil.getAllStatuses(themeDisplay.getUserId(), buddiesModifiedDate, 0, SearchContainer.DEFAULT_DELTA);
}
else if (_BUDDY_LIST_STRATEGY.equals("communities")) {
	buddies = StatusLocalServiceUtil.getGroupStatuses(themeDisplay.getUserId(), buddiesModifiedDate, 0, SearchContainer.DEFAULT_DELTA);
}
else if (_BUDDY_LIST_STRATEGY.equals("friends")) {
	buddies = StatusLocalServiceUtil.getSocialStatuses(themeDisplay.getUserId(), SocialRelationConstants.TYPE_BI_FRIEND, buddiesModifiedDate, 0, SearchContainer.DEFAULT_DELTA);
}
else if (_BUDDY_LIST_STRATEGY.equals("communities,friends")) {
	List<Object[]> groupBuddies = StatusLocalServiceUtil.getGroupStatuses(themeDisplay.getUserId(), buddiesModifiedDate, 0, SearchContainer.DEFAULT_DELTA);
	List<Object[]> socialBuddies = StatusLocalServiceUtil.getSocialStatuses(themeDisplay.getUserId(), SocialRelationConstants.TYPE_BI_FRIEND, buddiesModifiedDate, 0, SearchContainer.DEFAULT_DELTA);

	buddies = new ArrayList<Object[]>(groupBuddies.size() + socialBuddies.size());

	buddies.addAll(groupBuddies);

	BuddyComparator buddyComparator = new BuddyComparator(true);

	for (Object[] socialBuddy : socialBuddies) {
		if (Collections.binarySearch(groupBuddies, socialBuddy, buddyComparator) < 0) {
			buddies.add(socialBuddy);
		}
	}

	Collections.sort(buddies, buddyComparator);
}
else {
	buddies = new ArrayList<Object[]>();
}
%>

<%!
private static final String _BUDDY_LIST_STRATEGY = GetterUtil.getString(PortletProps.get("buddy.list.strategy"));
%>