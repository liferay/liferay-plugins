<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

portletURL.setParameter("mvcPath", "/sites/edit_site.jsp");
%>

<portlet:actionURL name="addSite" var="addSiteURL" />

<aui:form action="<%= addSiteURL %>" method="post" name="dialogFm">
	<aui:model-context model="<%= Group.class %>" />

	<div class="hide portlet-msg-success">
		<liferay-ui:message key="your-request-processed-successfully" />
	</div>

	<div class="hide portlet-msg-error">
		<liferay-ui:message key="your-request-failed-to-complete" />
	</div>

	<div class="section-container">
		<div class="section site-information" data-step='<%= LanguageUtil.format(request, "step-x-of-x", new String[] {"1", "2"}, false) %>' data-title='<%= LanguageUtil.get(request, "add-site-information") %>'>
			<aui:fieldset>
				<aui:input name="name" />

				<aui:input name="description" />
			</aui:fieldset>
		</div>

		<%
		LayoutSetPrototype defaultLayoutSetPrototype = null;
		%>

		<div class="hide section site-settings" data-step='<%= LanguageUtil.format(request, "step-x-of-x", new String[] {"2", "2"}, false) %>' data-title='<%= LanguageUtil.get(request, "add-site-settings") %>'>
			<div class="site-options">

				<%
				List<LayoutSetPrototype> layoutSetPrototypes = LayoutSetPrototypeServiceUtil.search(company.getCompanyId(), Boolean.TRUE, null);
				%>

				<aui:select id="layoutSetPrototypeSelect" label="default-pages" name="layoutSetPrototypeId">
					<aui:option label="none" selected="<%= true %>" value="0" />

					<%
					for (LayoutSetPrototype layoutSetPrototype : layoutSetPrototypes) {
						UnicodeProperties settingsProperties = layoutSetPrototype.getSettingsProperties();

						String customJspServletContextName = settingsProperties.getProperty("customJspServletContextName", StringPool.BLANK);

						if (!customJspServletContextName.equals("so-hook")) {
							continue;
						}

						String layoutSetPrototypeKey = (String)layoutSetPrototype.getExpandoBridge().getAttribute(SocialOfficeConstants.LAYOUT_SET_PROTOTYPE_KEY);

						boolean layoutSetPrototypeSite = layoutSetPrototypeKey.equals(SocialOfficeConstants.LAYOUT_SET_PROTOTYPE_KEY_SITE);

						if (layoutSetPrototypeSite) {
							defaultLayoutSetPrototype = layoutSetPrototype;
						}
					%>

						<aui:option selected="<%= layoutSetPrototypeSite %>" value="<%= layoutSetPrototype.getLayoutSetPrototypeId() %>"><%= layoutSetPrototype.getName(user.getLanguageId()) %></aui:option>

					<%
					}
					%>

				</aui:select>

				<aui:select id="typeSelect" label="type" name="type">
					<c:if test="<%= enableOpenSites %>">
						<aui:option label="<%= GroupConstants.getTypeLabel(GroupConstants.TYPE_SITE_OPEN) %>" value="<%= GroupConstants.TYPE_SITE_OPEN %>" />
					</c:if>

					<c:if test="<%= enablePublicRestrictedSites %>">
						<aui:option label="<%= GroupConstants.getTypeLabel(GroupConstants.TYPE_SITE_PUBLIC_RESTRICTED) %>" value="<%= GroupConstants.TYPE_SITE_PUBLIC_RESTRICTED %>" />
					</c:if>

					<c:if test="<%= enablePrivateRestrictedSites %>">
						<aui:option label="<%= GroupConstants.getTypeLabel(GroupConstants.TYPE_SITE_PRIVATE_RESTRICTED) %>" value="<%= GroupConstants.TYPE_SITE_PRIVATE_RESTRICTED %>" />
					</c:if>

					<c:if test="<%= enablePrivateSites %>">
						<aui:option label="<%= GroupConstants.getTypeLabel(GroupConstants.TYPE_SITE_PRIVATE) %>" value="<%= GroupConstants.TYPE_SITE_PRIVATE %>" />
					</c:if>
				</aui:select>
			</div>

			<div class="template-details">
				<h3 class="name"><%= defaultLayoutSetPrototype.getName(locale) %></h3>

				<p class="description">
					<%= defaultLayoutSetPrototype.getDescription() %>
				</p>

				<aui:row>
					<aui:col width="<%= 30 %>">
						<span class="included-pages"><liferay-ui:message key="included-pages" />:</span>

						<aui:input name="deleteLayoutIds" type="hidden" />

						<div class="delete-layouts-container">
							<c:if test="<%= defaultLayoutSetPrototype != null %>">

								<%
								Group layoutSetPrototypeGroup = defaultLayoutSetPrototype.getGroup();

								List<Layout> prototypeLayouts = LayoutLocalServiceUtil.getLayouts(layoutSetPrototypeGroup.getGroupId(), true, 0);

								for (Layout prototypeLayout : prototypeLayouts) {
								%>

									<div class="page">
										<input checked data-layoutId="<%= prototypeLayout.getLayoutId() %>" id="layout<%= prototypeLayout.getLayoutId() %>" type="checkbox" />

										<label for="layout<%= prototypeLayout.getLayoutId() %>"><%= prototypeLayout.getName(locale) %></label>
									</div>

								<%
								}
								%>

							</c:if>
						</div>
					</aui:col>

					<aui:col width="<%= 70 %>">
						<div class="type-details">
							<div class="permission">
								<liferay-ui:message key="permissions" />:
							</div>

							<div class="message">

								<%
								String description = StringPool.BLANK;

								if (enableOpenSites) {
									description = "open-sites-are-listed-pages-are-public-and-users-are-free-to-join-and-collaborate";
								}
								else if (enablePublicRestrictedSites) {
									description = "public-restricted-sites-are-listed-pages-are-public-and-users-must-request-to-join-and-collaborate";
								}
								else if (enablePrivateRestrictedSites) {
									description = "private-restricted-sites-are-listed-pages-are-private-and-users-must-request-to-join-and-collaborate";
								}
								else if (enablePrivateSites) {
									description = "private-sites-are-not-listed-pages-are-private-and-users-must-be-invited-to-collaborate";
								}
								%>

								<liferay-ui:message key="<%= description %>" />
							</div>
						</div>
					</aui:col>
				</aui:row>
			</div>
		</div>
	</div>

	<aui:button-row cssClass="dialog-footer">
		<div class="buttons-left">
			<aui:button disabled="<%= true %>" id="previous" onClick='<%= renderResponse.getNamespace() + "previous()" %>' value="previous" />

			<aui:button id="next" onClick='<%= renderResponse.getNamespace() + "next()" %>' value="next" />
		</div>

		<div class="step" id="<portlet:namespace />step">
			<span>
				<liferay-ui:message arguments="<%= new Integer[] {1, 2} %>" key="step-x-of-x" translateArguments="<%= false %>" />
			</span>
		</div>

		<div class="buttons-right">
			<aui:button id="save" onClick='<%= renderResponse.getNamespace() + "save()" %>' value="save" />
		</div>
	</aui:button-row>
</aui:form>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />save',
		function() {
			var A = AUI();

			var dialog = A.one('.so-portlet-sites-dialog');

			if (dialog) {
				var nextButton = dialog.one('#next');

				if (nextButton) {
					nextButton.set('disabled', true);
				}

				var loadingMask = new A.LoadingMask(
					{
						'strings.loading': '<%= UnicodeLanguageUtil.get(request, "creating-a-new-site") %>',
						target: dialog
					}
				);

				loadingMask.show();

				var sectionContainer = dialog.one('.section-container');

				if (sectionContainer) {
					var layoutElems = sectionContainer.all('.delete-layouts-container .page input:not(:checked)');

					var deleteLayoutIds = [];

					layoutElems.each(
						function(item, index) {
							deleteLayoutIds.push(item.getAttribute('data-layoutId'));
						}
					);
				}

				var deleteLayoutIdsElem = A.one('#<portlet:namespace />deleteLayoutIds');

				if (deleteLayoutIdsElem) {
					deleteLayoutIdsElem.set('value', deleteLayoutIds.join(','));
				}

				var form = A.one(document.<portlet:namespace />dialogFm);

				if (form) {
					A.io.request(
						form.getAttribute('action'),
						{
							after: {
								success: function(event, id, obj) {
									var data = this.get('responseData');

									if (data.result == 'success') {
										form.one('.portlet-msg-error').hide();

										var sites = Liferay.SO.Sites;

										sites.updateSites(true);

										var callback = function() {
											var popup = sites.getPopup();

											popup.hide();

											loadingMask.hide();
										};

										setTimeout(callback, 1000);
									}
									else if (data.result == 'failure') {
										var errorMsg = form.one('.portlet-msg-error');

										if (data.message) {
											errorMsg.html(data.message);
										}

										errorMsg.show();

										var section = dialog.one('.section');

										<portlet:namespace />showSection(section);

										loadingMask.hide();
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
			}
		},
		['aui-base', 'aui-io-request-deprecated', 'aui-loading-mask-deprecated']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />previous',
		function() {
			var A = AUI();

			var dialog = A.one('.so-portlet-sites-dialog');

			if (dialog) {
				var section = dialog.one('.section:not(.hide)').previous();

				if (section) {
					<portlet:namespace />showSection(section);
				}
			}
		},
		['aui-base']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />showSection',
		function(section) {
			var A = AUI();

			var dialog = A.one('.so-portlet-sites-dialog');

			if (dialog) {
				var sites = Liferay.SO.Sites;

				sites.setTitle(section.getAttribute('data-title'));

				var step = A.one('#<portlet:namespace />step');

				if (step) {
					step.html('<span>' + section.getAttribute('data-step') + '</span>');
				}

				var sectionContainer = dialog.one('.section-container');

				if (sectionContainer) {
					sectionContainer.all('.section').hide();
				}

				var previousButton = dialog.one('#previous');

				if (previousButton) {
					if (!section.previous('.section')) {
						sites.disableButton(previousButton);
					}
					else {
						sites.enableButton(previousButton);
					}

					previousButton.blur();
				}

				var nextButton = dialog.one('#next');

				if (nextButton) {
					if (!section.next('.section')) {
						sites.disableButton(nextButton);
					}
					else {
						sites.enableButton(nextButton);
					}

					nextButton.blur();
				}

				section.show();
			}
		},
		['aui-base']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />next',
		function() {
			var A = AUI();

			var dialog = A.one('.so-portlet-sites-dialog');

			if (dialog) {
				var section = dialog.one('.section:not(.hide)').next();

				if (section) {
					<portlet:namespace />showSection(section);
				}
			}
		},
		['aui-base']
	);
</aui:script>

<aui:script use="aui-base,aui-io-deprecated">
	Liferay.Util.focusFormField(document.<portlet:namespace />dialogFm.<portlet:namespace />name);

	var form = A.one(document.<portlet:namespace />dialogFm);

	form.on(
		'submit',
		function(event) {
			event.halt();
		}
	);

	var dialog = A.one('.so-portlet-sites-dialog');

	var descriptionContainer = dialog.one('.template-details');

	var description = descriptionContainer.one('.description');
	var name = descriptionContainer.one('.name');

	var deleteLayoutsContainer = dialog.one('.delete-layouts-container');

	var templateSelect = dialog.one('#<portlet:namespace />layoutSetPrototypeSelect');

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

							var inputBuffer = [];

							for (var i in layouts) {
								var layout = layouts[i];

								inputBuffer.push(
									'<div class="page">' +
										'<input checked data-layoutId="' + layout.layoutId + '" id="layout' + layout.layoutId + '" type="checkbox" />' +
										'<label for="layout' + layout.layoutId + '">' + layout.name + '</label>' +
									'</div>'
								);
							}

							deleteLayoutsContainer.html(
								inputBuffer.join('')
							);
						}
					},
					data: {
						<portlet:namespace />layoutSetPrototypeId: templateId
					},
					dataType: 'JSON'
				}
			);
		}
	);

	var typeSelect = dialog.one('#<portlet:namespace />typeSelect');

	typeSelect.on(
		'change',
		function(event) {
			var type = typeSelect.get('value');

			var message = '';

			if (type == <%= GroupConstants.TYPE_SITE_OPEN %>) {
				message = '<%= UnicodeLanguageUtil.get(request, "open-sites-are-listed-pages-are-public-and-users-are-free-to-join-and-collaborate") %>';
			}
			else if (type == <%= GroupConstants.TYPE_SITE_PUBLIC_RESTRICTED %>) {
				message = '<%= UnicodeLanguageUtil.get(request, "public-restricted-sites-are-listed-pages-are-public-and-users-must-request-to-join-and-collaborate") %>';
			}
			else if (type == <%= GroupConstants.TYPE_SITE_PRIVATE_RESTRICTED %>) {
				message = '<%= UnicodeLanguageUtil.get(request, "private-restricted-sites-are-listed-pages-are-private-and-users-must-request-to-join-and-collaborate") %>';
			}
			else if (type == <%= GroupConstants.TYPE_SITE_PRIVATE %>) {
				message = '<%= UnicodeLanguageUtil.get(request, "private-sites-are-not-listed-pages-are-private-and-users-must-be-invited-to-collaborate") %>';
			}

			dialog.one('.type-details .message').html(message);
		}
	);
</aui:script>