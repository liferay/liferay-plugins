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
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */
--%>

<%@ include file="/sites/init.jsp" %>

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
			<liferay-ui:header title="information" />

			<aui:fieldset>
				<aui:input name="name" />

				<aui:input name="description" />
			</aui:fieldset>
		</div>

		<%
		LayoutSetPrototype defaultLayoutSetPrototype = null;
		%>

		<div class="section site-settings aui-helper-hidden">
			<liferay-ui:header title="settings" />

			<aui:column columnWidth="<%= 50 %>" first="<%= true %>">

				<%
				List<LayoutSetPrototype> layoutSetPrototypes = LayoutSetPrototypeServiceUtil.search(company.getCompanyId(), Boolean.TRUE, null);
				%>

				<aui:fieldset>
					<aui:select id="layoutSetPrototypeSelect" label="default-pages" name="layoutSetPrototypeId">
						<aui:option label="none" selected="<%= true %>" value="0" />

						<%
						for (LayoutSetPrototype layoutSetPrototype : layoutSetPrototypes) {
							UnicodeProperties settingsProperties = layoutSetPrototype.getSettingsProperties();

							String customJspServletContextName = settingsProperties.getProperty("customJspServletContextName", StringPool.BLANK);

							if (!customJspServletContextName.equals("so-hook")) {
								continue;
							}

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
						<aui:option label="open" value="<%= GroupConstants.TYPE_SITE_OPEN %>" />
						<aui:option label="restricted" value="<%= GroupConstants.TYPE_SITE_RESTRICTED %>" />
						<aui:option label="private" value="<%= GroupConstants.TYPE_SITE_PRIVATE %>" />
					</aui:select>
				</aui:fieldset>
			</aui:column>

			<aui:column columnWidth="<%= 50 %>">
				<div class="template-details">
					<c:if test="<%= defaultLayoutSetPrototype != null %>">
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
					</c:if>

					<div style="clear: both;"></div>
				</div>
			</aui:column>
		</div>

		<div class="section site-customization aui-helper-hidden">
			<liferay-ui:header title="customization" />

			<div class="set-label">
				<liferay-ui:message key="included-pages" />
			</div>

			<div class="tip">
				<liferay-ui:message key="uncheck-the-pages-to-exclude-from-your-site" />
			</div>

			<aui:input name="deleteLayoutIds" type="hidden" />

			<div class="delete-layouts-container">
				<c:if test="<%= defaultLayoutSetPrototype != null %>">

					<%
					Group layoutSetPrototypeGroup = defaultLayoutSetPrototype.getGroup();

					List<Layout> prototypeLayouts = LayoutLocalServiceUtil.getLayouts(layoutSetPrototypeGroup.getGroupId(), true, 0);

					for (Layout prototypeLayout : prototypeLayouts) {
					%>

						<span class="page">
							<input checked data-layoutId="<%= prototypeLayout.getLayoutId() %>" id="layout<%= prototypeLayout.getLayoutId() %>" type="checkbox" />

							<label for="layout<%= prototypeLayout.getLayoutId() %>"><%= prototypeLayout.getName(locale) %></label>
						</span>

					<%
					}
					%>

				</c:if>
			</div>
		</div>
	</div>

	<div style="clear: both;"></div>

	<aui:button-row>
		<div class="buttons-left">
			<aui:button disabled="<%= true %>" id="previous" onClick='<%= renderResponse.getNamespace() + "previous()" %>' value="previous" />

			<aui:button id="next" onClick='<%= renderResponse.getNamespace() + "next()" %>' value="next" />
		</div>

		<div class="buttons-right">
			<aui:button id="save" onClick='<%= renderResponse.getNamespace() + "save()" %>' value="save" />
		</div>
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
		'<portlet:namespace />save',
		function() {
			nextButton.set('disabled', true);

			var layoutElems = sectionContainer.all('.delete-layouts-container .page input:not(:checked)');

			var deleteLayoutIds = [];

			layoutElems.each(
				function(layoutElem, index, collection) {
					deleteLayoutIds.push(layoutElem.getAttribute('data-layoutId'));
				}
			);

			var deleteLayoutIdsElem = A.one('#<portlet:namespace />deleteLayoutIds');

			deleteLayoutIdsElem.set('value', deleteLayoutIds.join(','));

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

								setTimeout(callback, 1000);
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
				Liferay.SO.Sites.disableButton(previousButton);
			}
			else {
				Liferay.SO.Sites.enableButton(previousButton);
			}

			if (!section.next()) {
				Liferay.SO.Sites.disableButton(nextButton);
			}
			else {
				Liferay.SO.Sites.enableButton(nextButton);
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

			<portlet:namespace />showSection(section);
		}
	);

	Liferay.Util.focusFormField(document.<portlet:namespace />dialogFm.<portlet:namespace />name);
</aui:script>

<aui:script use="aui-base,aui-io">
	var form = A.one(document.<portlet:namespace />dialogFm);

	form.on(
		'submit',
		function(event) {
			event.halt();
		}
	);

	var templateSelect = A.one('.so-portlet-sites-dialog #<portlet:namespace />layoutSetPrototypeSelect');

	var descriptionContainer = A.one('.so-portlet-sites-dialog .template-details');

	var name = descriptionContainer.one('.name');
	var description = descriptionContainer.one('.description');
	var pages = descriptionContainer.one('.pages');

	var deleteLayoutsContainer = A.one('.so-portlet-sites-dialog .delete-layouts-container');

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

							name.html(data.name);
							description.html(data.description);

							var layouts = data.layouts;

							var listBuffer = [];

							for (var i in layouts) {
								var layout = layouts[i];

								listBuffer.push('<li>' + layout.name + '</li>');
							}

							pages.html(listBuffer.join(''));

							var inputBuffer = [];

							for (var i in layouts) {
								var layout = layouts[i];

								inputBuffer.push(
									'<span class="page">' +
										'<input checked data-layoutId="' + layout.layoutId + '" id="layout' + layout.layoutId + '" type="checkbox" />' +
										'<label for="layout' + layout.layoutId + '">' + layout.name + '</label>' +
									'</span>');
							}

							deleteLayoutsContainer.html(inputBuffer.join(''));
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