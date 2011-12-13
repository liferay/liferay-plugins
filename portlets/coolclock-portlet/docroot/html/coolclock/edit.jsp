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
	<aui:column columnWidth="70" first="true">
		<aui:fieldset>
		<aui:form>
			<aui:input name="clockTheme" />
			
		</aui:form>
		</aui:fieldset>
	</aui:column>

	<aui:column columnWidth="30" last="true">
		<canvas id="clk1" class="CoolClock:fancy myClock"></canvas>
	</aui:column>
</aui:fieldset>
