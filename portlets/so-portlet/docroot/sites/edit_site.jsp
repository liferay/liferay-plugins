<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */
--%>

<%@ include file="/init.jsp" %>

<%
PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setWindowState(LiferayWindowState.EXCLUSIVE);

portletURL.setParameter("jspPage", "/sites/edit_site.jsp");
%>

<portlet:actionURL name="addSite" var="addSiteURL" />

<aui:form action="<%= addSiteURL %>" method="post" name="dialogFm">
	<aui:model-context model="<%= Group.class %>" />

	<div class="portlet-msg-success aui-helper-hidden">
		<liferay-ui:message key="your-request-processed-successfully" />
	</div>

	<div class="portlet-msg-error aui-helper-hidden">
		<liferay-ui:message key="your-request-failed-to-complete" />
	</div>

	<div class="section-container">
		<div class="section site-information">
			<liferay-ui:header title="site-information" />

			<aui:fieldset>
				<aui:input name="name" />

				<aui:input name="description" />
			</aui:fieldset>
		</div>

		<div class="section site-settings aui-helper-hidden">
			<liferay-ui:header title="site-settings" />

			<%
			LayoutSetPrototype defaultLayoutSetPrototype = null;
			%>

			<aui:column columnWidth="<%= 40 %>" first="<%= true %>">

				<%
				List<LayoutSetPrototype> layoutSetPrototypes = LayoutSetPrototypeServiceUtil.search(company.getCompanyId(), Boolean.TRUE, null);
				%>

				<aui:fieldset>
					<aui:select id="layoutSetPrototypeSelect" label="default-pages" name="layoutSetPrototypeId">
						<aui:option label="none" selected="<%= true %>" value="0" />

						<%
						for (LayoutSetPrototype layoutSetPrototype : layoutSetPrototypes) {
							Boolean socialOfficeDefault = (Boolean)layoutSetPrototype.getExpandoBridge().getAttribute("socialOfficeDefault");

							if (socialOfficeDefault.booleanValue()) {
								defaultLayoutSetPrototype = layoutSetPrototype;
							}
						%>

							<aui:option selected="<%= socialOfficeDefault %>" value="<%= layoutSetPrototype.getLayoutSetPrototypeId() %>"><%= layoutSetPrototype.getName(user.getLanguageId()) %></aui:option>

						<%
						}
						%>

					</aui:select>

					<aui:select name="type">
						<aui:option label="open" value="<%= GroupConstants.TYPE_COMMUNITY_OPEN %>" />
						<aui:option label="restricted" value="<%= GroupConstants.TYPE_COMMUNITY_RESTRICTED %>" />
						<aui:option label="private" value="<%= GroupConstants.TYPE_COMMUNITY_PRIVATE %>" />
					</aui:select>
				</aui:fieldset>
			</aui:column>

			<aui:column columnWidth="<%= 60 %>">
				<div class="template-details">
					<h3 class="name"><%= defaultLayoutSetPrototype.getName(locale) %></h3>

					<p class="description">
						<%= defaultLayoutSetPrototype.getDescription() %>
					</p>

					<span>
						<liferay-ui:message key="included-pages" />
					</span>

					<ul class="pages">

						<%
						Group layoutSetPrototypeGroup = defaultLayoutSetPrototype.getGroup();

						List<Layout> prototypeLayouts = LayoutLocalServiceUtil.getLayouts(layoutSetPrototypeGroup.getGroupId(), true, 0);

						for (Layout prototypeLayout : prototypeLayouts) {
						%>

							<li><%= prototypeLayout.getName(locale) %></li>

						<%
						}
						%>

					</ul>

					<div style="clear: both;"></div>
				</div>
			</aui:column>
		</div>
	</div>

	<div style="clear: both;"></div>

	<aui:button-row>
		<aui:button disabled="<%= true %>" id="previous" onClick='<%= renderResponse.getNamespace() + "previous()" %>' value="previous" />

		<aui:button id="next" onClick='<%= renderResponse.getNamespace() + "next()" %>' value="next" />
	</aui:button-row>
</aui:form>

<aui:script>
	var A = AUI();

	var form = A.one(document.<portlet:namespace />dialogFm);

	var sectionContainer = A.one('.so-portlet-sites-dialog .section-container');

	var previousButton = A.one('.so-portlet-sites-dialog #previous');
	var nextButton = A.one('.so-portlet-sites-dialog #next');

	Liferay.provide(
		window,
		'<portlet:namespace />addSite',
		function() {
			nextButton.set('disabled', true);

			A.io.request(
				form.getAttribute('action'),
				{
					after: {
						success: function(event, id, obj) {
							var data = this.get('responseData');

							if (data.result == 'success') {
								form.one('.portlet-msg-success').show();
								form.one('.portlet-msg-error').hide();

								form.one('.section-container').hide();

								Liferay.SO.Sites.updateSites();

								var callback = function() {
									var dialog = Liferay.SO.Sites.getPopup();

									dialog.hide();
								}

								setTimeout(callback, 3000);
							}
							else if (data.result == 'failure') {
								var errorMsg = form.one('.portlet-msg-error');

								if (data.message) {
									errorMsg.html(data.message);
								}

								errorMsg.show();

								var section = A.one('.so-portlet-sites-dialog .section');

								<portlet:namespace />showSection(section);
							}
						}
					},
					dataType: 'JSON',
					form: {
						id: form.getDOM()
					}
				}
			);
		}
	);

	Liferay.provide(
		window,
		'<portlet:namespace />previous',
		function() {
			var section = A.one('.so-portlet-sites-dialog .section:not(.aui-helper-hidden)').previous();

			<portlet:namespace />showSection(section);
		}
	);

	Liferay.provide(
		window,
		'<portlet:namespace />showSection',
		function(section) {
			sectionContainer.all('.section').hide();

			if (!section.previous()) {
				previousButton.set('disabled', true);
				previousButton.ancestor('.aui-button').addClass('aui-button-disabled');
			}
			else {
				previousButton.set('disabled', false);
				previousButton.ancestor('.aui-button').removeClass('aui-button-disabled');
			}

			if (!section.next()) {
				nextButton.set('value', '<liferay-ui:message key="save" />');
			}
			else {
				nextButton.set('disabled', false);
				nextButton.set('value', '<liferay-ui:message key="next" />');
			}

			previousButton.blur();
			nextButton.blur();

			section.show();
		}
	);

	Liferay.provide(
		window,
		'<portlet:namespace />next',
		function() {
			var section = A.one('.so-portlet-sites-dialog .section:not(.aui-helper-hidden)').next();

			if (section) {
				<portlet:namespace />showSection(section);
			}
			else {
				<portlet:namespace />addSite();
			}
		},
		['aui-io', 'aui-dialog']
	);

	Liferay.Util.focusFormField(document.<portlet:namespace />dialogFm.<portlet:namespace />name);
</aui:script>

<aui:script use="aui-base,aui-io">
	var templateSelect = A.one('.so-portlet-sites-dialog #<portlet:namespace />layoutSetPrototypeSelect');

	var templateDescription = A.one('.so-portlet-sites-dialog .template-details');

	templateSelect.on(
		'change',
		function(event) {
			var templateId = templateSelect.get('value');

			A.io.request(
				'<portlet:resourceURL id="getLayoutSetPrototypeDescription" />',
				{
					after: {
						success: function(event, id, obj) {
							var data = this.get('responseData');

							window.testData = data;

							var name = templateDescription.one('.name');
							var description = templateDescription.one('.description');
							var pages = templateDescription.one('.pages');


							name.html(data.name);
							description.html(data.description);

							var layouts = data.layouts;

							var buffer = [''];

							for (var i in layouts) {
								var layout = layouts[i];

								buffer.push('<li>' + layout.name + '</li>');
							}

							pages.html(buffer.join(''));
						}
					},
					data: {
						layoutSetPrototypeId: templateId
					},
					dataType: 'JSON'
				}
			);
		}
	);
</aui:script>