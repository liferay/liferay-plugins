<%--
/**
* Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

<%@ include file="/html/init.jsp"%>

<script>
	AUI().ready(function() {
		CoolClock.findAndCreateClocks();
		alert();
	});
</script>

<aui:fieldset>
<aui:form>
<aui:field-wrapper name="skins">
	<aui:input name="themes" type="radio" label="Swiss Rail" value="swissRail" />
	<aui:input name="themes" type="radio" label="Chunky Swiss" value="chunkySwiss" />
	<aui:input name="themes" type="radio" label="Fancy" value="fancy" />
	<aui:input name="themes" type="radio" label="Machine" value="machine" />
	<aui:input name="themes" type="radio" label="Classic" value="classic" />
	<aui:input name="themes" type="radio" label="Modern" value="modern" />
	<aui:input name="themes" type="radio" label="Simple" value="simple" />
</aui:field-wrapper>
<aui:input name="secondHand" label="secondHand" type="checkbox" inlineLabel="left" />
</aui:form>
</aui:fieldset>
