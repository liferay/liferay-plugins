<%
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

<%@ include file="init.jsp" %>

<aui:script use="node">

allFlds = A.all('div.fld');

allFlds.on('click', function (e) {
	var currentTarget = e.currentTarget;

	allFlds.removeClass('selected');
	currentTarget.addClass('selected');

	currentTarget.one('input.r');

	currentTarget.one('span.r input').set('checked', true);
});

</aui:script>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<aui:form action="<%= configurationURL %>" cssClass="digg-widget-configuration" method="post">
	<input name="<portlet:namespace /><%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

	<div class="fld <%= domain ? "selected" : "" %>">
		<aui:input checked="<%= !!domain %>" cssClass="news-type r" inlineField="true" label="" name="newsType" type="radio" value="domain" /> <aui:select  cssClass="source-pop-or-up" inlineField="true" label="" name="sourcePopOrUp">
			<aui:option label="Popular" selected='<%= sourcePopOrUp.equals("popular") %>' value="popular" />
			<aui:option label="Upcoming" selected='<%= sourcePopOrUp.equals("upcoming") %>' value="upcoming" />
			<aui:option label="All" selected='<%= sourcePopOrUp.equals("all") %>' value="all" />
		</aui:select> <label>stories from the source site</label> <aui:input cssClass="url" inlineField="true" label="" name="url" size="17" value="<%= url %>" />

		<div class="ffld">
			<aui:select cssClass="url-sort" inlineField="true" label="" name="urlSort">
				<aui:option label="sorted by date" selected='<%= urlSort.equals("promote_date-desc") %>' value="promote_date-desc" />
				<aui:option label="sorted by diggs" selected='<%= urlSort.equals("digg_count-desc") %>' value="digg_count-desc" />
			</aui:select> in the <aui:select cssClass="min-date" inlineField="true" label="" name="minDate">
				<aui:option>last millenium</aui:option>
				<aui:option label="last 24 hours" selected='<%= minDate.equals("1") %>' value="1" />
				<aui:option label="last 7 days" selected='<%= minDate.equals("7") %>' value="7" />
				<aui:option label="last 30 days" selected='<%= minDate.equals("30") %>' value="30" />
				<aui:option label="last 365 days" selected='<%= minDate.equals("365") %>' value="365" />
			</aui:select> <aui:input cssClass="fallback" inlineField="true" label="" name="fallback" type="checkbox" value="<%= !!fallback %>" /> Use fallback content if too few stories are found
		</div>
	</div>

	<div class="fld <%= front ? "selected" : "" %>">
		<aui:input checked="<%= !!front %>" cssClass="news-type r" inlineField="true" label="" name="newsType" type="radio" value="front" /> <label>All popular stories in</label> <aui:select cssClass="news-front" inlineField="true" label="" name="newsFront">
			<aui:option>All Topics</aui:option>
			<aui:option label="Apple" selected='<%= newsFront.equals("apple") %>' value="apple" />
			<aui:option label="Design" selected='<%= newsFront.equals("design") %>' value="design" />
			<aui:option label="Gadgets" selected='<%= newsFront.equals("gadgets") %>' value="gadgets" />
			<aui:option label="Hardware" selected='<%= newsFront.equals("hardware") %>' value="hardware" />
			<aui:option label="Industry News" selected='<%= newsFront.equals("tech_news") %>' value="tech_news" />
			<aui:option label="Linux/Unix" selected='<%= newsFront.equals("linux_unix") %>' value="linux_unix" />
			<aui:option label="Microsoft" selected='<%= newsFront.equals("microsoft") %>' value="microsoft" />
			<aui:option label="Mods" selected='<%= newsFront.equals("mods") %>' value="mods" />
			<aui:option label="Programming" selected='<%= newsFront.equals("programming") %>' value="programming" />
			<aui:option label="Security" selected='<%= newsFront.equals("security") %>' value="security" />
			<aui:option label="Software" selected='<%= newsFront.equals("software") %>' value="software" />
			<aui:option label="Business &amp; Finance" selected='<%= newsFront.equals("business_finance") %>' value="business_finance" />
			<aui:option label="World News" selected='<%= newsFront.equals("world_news") %>' value="world_news" />
			<aui:option label="Political News" selected='<%= newsFront.equals("politics") %>' value="politics" />
			<aui:option label="Political Opinion" selected='<%= newsFront.equals("political_opinion") %>' value="political_opinion" />
			<aui:option label="Celebrity" selected='<%= newsFront.equals("celebrity") %>' value="celebrity" />
			<aui:option label="Movies" selected='<%= newsFront.equals("movies") %>' value="movies" />
			<aui:option label="Music" selected='<%= newsFront.equals("music") %>' value="music" />
			<aui:option label="Television" selected='<%= newsFront.equals("television") %>' value="television" />
			<aui:option label="Comics &amp; Animation" selected='<%= newsFront.equals("comics_animation") %>' value="comics_animation" />
			<aui:option label="Industry News" selected='<%= newsFront.equals("gaming_news") %>' value="gaming_news" />
			<aui:option label="PC Games" selected='<%= newsFront.equals("pc_games") %>' value="pc_games" />
			<aui:option label="Playable Web Games" selected='<%= newsFront.equals("playable_web_games") %>' value="playable_web_games" />
			<aui:option label="Nintendo" selected='<%= newsFront.equals("nintendo") %>' value="nintendo" />
			<aui:option label="PlayStation" selected='<%= newsFront.equals("playstation") %>' value="playstation" />
			<aui:option label="Xbox" selected='<%= newsFront.equals("xbox") %>' value="xbox" />
			<aui:option label="Baseball" selected='<%= newsFront.equals("baseball") %>' value="baseball" />
			<aui:option label="Environment" selected='<%= newsFront.equals("environment") %>' value="environment" />
			<aui:option label="General Sciences" selected='<%= newsFront.equals("general_sciences") %>' value="general_sciences" />
			<aui:option label="Basketball" selected='<%= newsFront.equals("basketball") %>' value="basketball" />
			<aui:option label="Extreme" selected='<%= newsFront.equals("extreme_sports") %>' value="extreme_sports" />
			<aui:option label="Space" selected='<%= newsFront.equals("space") %>' value="space" />
			<aui:option label="Football - US/Canada" selected='<%= newsFront.equals("football") %>' value="football" />
			<aui:option label="Golf" selected='<%= newsFront.equals("golf") %>' value="golf" />
			<aui:option label="Hockey" selected='<%= newsFront.equals("hockey") %>' value="hockey" />
			<aui:option label="Motorsport" selected='<%= newsFront.equals("motorsport") %>' value="motorsport" />
			<aui:option label="Olympics" selected='<%= newsFront.equals("olympics") %>' value="olympics" />
			<aui:option label="Soccer" selected='<%= newsFront.equals("soccer") %>' value="soccer" />
			<aui:option label="Tennis" selected='<%= newsFront.equals("tennis") %>' value="tennis" />
			<aui:option label="Other Sports" selected='<%= newsFront.equals("other_sports") %>' value="other_sports" />
			<aui:option label="Arts &amp; Culture" selected='<%= newsFront.equals("arts_culture") %>' value="arts_culture" />
			<aui:option label="Autos" selected='<%= newsFront.equals("autos") %>' value="autos" />
			<aui:option label="Educational" selected='<%= newsFront.equals("educational") %>' value="educational" />
			<aui:option label="Food &amp; Drink" selected='<%= newsFront.equals("food_drink") %>' value="food_drink" />
			<aui:option label="Health" selected='<%= newsFront.equals("health") %>' value="health" />
			<aui:option label="Travel &amp; Places" selected='<%= newsFront.equals("travel_places") %>' value="travel_places" />
			<aui:option label="Comedy" selected='<%= newsFront.equals("comedy") %>' value="comedy" />
			<aui:option label="Odd Stuff" selected='<%= newsFront.equals("odd_stuff") %>' value="odd_stuff" />
			<aui:option label="People" selected='<%= newsFront.equals("people") %>' value="people" />
			<aui:option label="Pets & Animals" selected='<%= newsFront.equals("pets_animals") %>' value="pets_animals" />
		</aui:select>
	</div>

	<div class="fld <%= top ? "selected" : "" %>">
		<aui:input checked="<%= !!top %>" cssClass="news-type r" inlineField="true" label="" name="newsType" type="radio" value="top" /> <label>Top 10 list from</label> <aui:select cssClass="news-top" inlineField="true" label="" name="newsTop">
			<aui:option>All Topics</aui:option>
			<aui:option label="Technology" selected='<%= newsTop.equals("technology") %>' value="technology" />
			<aui:option label="Science" selected='<%= newsTop.equals("science") %>' value="science" />
			<aui:option label="World &amp; Business" selected='<%= newsTop.equals("world_business") %>' value="world_business" />
			<aui:option label="Sports" selected='<%= newsTop.equals("sports") %>' value="sports" />
			<aui:option label="Entertainment" selected='<%= newsTop.equals("entertainment") %>' value="entertainment" />
			<aui:option label="Gaming" selected='<%= newsTop.equals("gaming") %>' value="gaming" />
			<aui:option label="Lifestyle" selected='<%= newsTop.equals("lifestyle") %>' value="lifestyle" />
			<aui:option label="Offbeat" selected='<%= newsTop.equals("offbeat") %>' value="offbeat" />
			<aui:option label="News" selected='<%= newsTop.equals("news") %>' value="news" />
			<aui:option label="Offbeat" selected='<%= newsTop.equals("offbeat") %>' value="offbeat" />
			<aui:option label="News" selected='<%= newsTop.equals("news") %>' value="news" />
			<aui:option label="Videos" selected='<%= newsTop.equals("videos") %>' value="videos" />
			<aui:option label="Images" selected='<%= newsTop.equals("images") %>' value="images" />
		</aui:select>
	</div>

	<div class="fld <%= user ? "selected" : "" %>">
		<aui:input checked="<%= !!user %>" cssClass="news-type r" inlineField="true" label="" name="newsType" type="radio" value="user" /> <label for="news-type3">Stories</label> <aui:select cssClass="news-user" inlineField="true" label="" name="newsUser">
			<aui:option label="dugg" selected='<%= newsUser.equals("dugg") %>' value="dugg" />
			<aui:option label="submitted" selected='<%= newsUser.equals("submissions") %>' value="submissions" />
		</aui:select> <label>by user</label> <aui:input cssClass="user-username" inlineField="true" label="" name="userUsername" size="17" value="<%= userUsername %>" />
	</div>

	<div class="fld <%= search ? "selected" : "" %>">
		<aui:input checked="<%= !!search %>" cssClass="news-type r" inlineField="true" label="" name="newsType" type="radio" value="search" /> <label>Search results for</label> <aui:input cssClass="api-search" inlineField="true" label="" name="apiSearch" size="17" value="<%= apiSearch %>" /> in <aui:select cssClass="search-topics" inlineField="true" label="" name="searchTopics">
			<aui:option>All Topics</aui:option>
			<aui:option label="Technology" selected='<%= searchTopics.equals("technology") %>' value="technology" />
			<aui:option label="Science" selected='<%= searchTopics.equals("science") %>' value="science" />
			<aui:option label="World &amp; Business" selected='<%= searchTopics.equals("world_business") %>' value="world_business" />
			<aui:option label="Sports" selected='<%= searchTopics.equals("sports") %>' value="sports" />
			<aui:option label="Entertainment" selected='<%= searchTopics.equals("entertainment") %>' value="entertainment" />
			<aui:option label="Gaming" selected='<%= searchTopics.equals("gaming") %>' value="gaming" />
			<aui:option label="Lifestyle" selected='<%= searchTopics.equals("lifestyle") %>' value="lifestyle" />
			<aui:option label="Offbeat" selected='<%= searchTopics.equals("offbeat") %>' value="offbeat" />
		</aui:select> <aui:select cssClass="search-sort" inlineField="true" label="" name="searchSort">
			<aui:option label="sorted by date" selected='<%= searchSort.equals("promote_date-desc") %>' value="promote_date-desc" />
			<aui:option label="sorted by diggs" selected='<%= searchSort.equals("digg_count-desc") %>' value="digg_count-desc" />
		</aui:select>
	</div>

	<div class="fld <%= friends ? "selected" : "" %> f">
		<aui:input checked="<%= !!friends %>" cssClass="news-type r" inlineField="true" label="" name="newsType" type="radio" value="friends" /> <aui:select cssClass="news-friends"  inlineField="true" label="Stories your friends have" name="newsFriends">
			<aui:option label="dugg" selected='<%= newsFriends.equals("dugg") %>' value="dugg" />
			<aui:option label="submitted" selected='<%= newsFriends.equals("submissions") %>' value="submissions" />
			<aui:option label="commented on" selected='<%= newsFriends.equals("commented") %>' value="commented" />
		</aui:select> <span id="yourusername">Your username: <aui:input cssClass="friends-username" inlineField="true" label="" name="friendsUsername" value="<%= friendsUsername %>" /></span>
	</div>

	<div class="pfld">
		<aui:input cssClass="story-count" inlineField="true" label="Number of Items" maxlength="2" name="storyCount" size="2" value="<%= storyCount %>" />

		<aui:input cssClass="show-diggs" inlineField="true" label="Show Diggs" name="showDiggs" type="checkbox" value="<%= !!showDiggs %>" />

		<aui:input cssClass="show-descriptions" inlineField="true" label="Show Descriptions" name="showDesc" type="checkbox" value="<%= !!showDesc %>" />

		<aui:input cssClass="show-thumbnails" inlineField="true" label="Show Thumbnails" name="showThumbs" type="checkbox" value="<%= !!showThumbs %>" />

		<aui:input cssClass="show-footer" inlineField="true" label="Show Footer" name="showFooter" type="checkbox" value="<%= !!showFooter %>" />

		<aui:input cssClass="new-window" inlineField="true" label="Open links in a new window" name="newWindow" type="checkbox" value="<%= !!newWindow %>" />
	</div>

	<div class="pfld">
		<aui:input cssClass="width" inlineField="true" label="width" name="width" value="<%= width %>" />
		<aui:input cssClass="height" inlineField="true" label="height" name="height" value="<%= height %>" />
	</div>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button type="reset" />
	</aui:button-row>
</aui:form>

<div class="digg-widget" id="<portlet:namespace />widget"></div>