<aui:form name="fm">
	<aui:fieldset>
		<aui:layout>
			<aui:input id="fromAddress" inlineLabel="left" label="from" name="fromAddress" type="text" />

			<aui:input id="toAddress" inlineField="<%= true %>" inlineLabel="left" label="to" name="toAddress" type="text" />

			<aui:button name="getDirectionsButton" onClick='<%= renderResponse.getNamespace() + "getDirections();" %>' value="search" />
		</aui:layout>

		<div id="baidu-map-content" style="height: <%= height %>px;">
			<div class="map-canvas" id="<portlet:namespace />mapCanvas"></div>

			<div class="direction-steps" id="<portlet:namespace />directionSteps"></div>
		</div>
	</aui:fieldset>
</aui:form>