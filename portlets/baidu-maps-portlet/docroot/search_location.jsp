<aui:form name="fm">
	<aui:fieldset>
		<aui:layout>
			<aui:input id="mapAddress" inlineField="<%= true %>" inlineLabel="left" label="" name="mapAddress" />

			<aui:button name="getLocationsButton" onclick='<%= renderResponse.getNamespace() + "getLocations();" %>' value="search" />
		</aui:layout>

		<div id="baidu-map-content" style="height: <%= height %>px;">
			<div class="map-canvas" id="<portlet:namespace />mapCanvas"></div>

			<div class="locations" id="<portlet:namespace />locations"></div>
		</div>
	</aui:fieldset>
</aui:form>