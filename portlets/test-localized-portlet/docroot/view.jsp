<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

<%@ include file="/init.jsp" %>

<%
ResourceBundle portletConfigResourceBundle = portletConfig.getResourceBundle(locale);
%>

Test only in

<a href="/zh<%= PortalUtil.getPathMain() %>/portal/layout?p_l_id=<%= themeDisplay.getPlid() %>&p_p_id=<%= portletDisplay.getId() %>&p_p_state=maximized">Chinese</a>,

<a href="/en<%= PortalUtil.getPathMain() %>/portal/layout?p_l_id=<%= themeDisplay.getPlid() %>&p_p_id=<%= portletDisplay.getId() %>&p_p_state=maximized">English</a>,

<a href="/fr<%= PortalUtil.getPathMain() %>/portal/layout?p_l_id=<%= themeDisplay.getPlid() %>&p_p_id=<%= portletDisplay.getId() %>&p_p_state=maximized">French</a>, or

<a href="/es<%= PortalUtil.getPathMain() %>/portal/layout?p_l_id=<%= themeDisplay.getPlid() %>&p_p_id=<%= portletDisplay.getId() %>&p_p_state=maximized">Spanish</a>.

<br /><br />

<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th>
				Description
			</th>
			<th>
				Key
			</th>
			<th>
				Resource Bundle Value
			</th>
			<th>
				Tag Library Value
			</th>
			<th></th>
			<th></th>
		</tr>
	</thead>
	<tbody>

		<%
		String[] expectedResourceBundleValues = null;

		String[] expectedTaglibValues = null;

		if (locale.getLanguage().equals("fr")) {
			expectedResourceBundleValues = new String[] {
				"first-name",
				"Bienvenue 2.0",
				"playing-basketball-is-fun",
				"Veuillez prendre une boisson fra\u00eeche.",
				"this-is-an-unlocalized-hook-message",
				"This is an unlocalized portlet message.",
				"post",
				"post[adjective]",
				"post[noun]",
				"post[verb]",
				"comment",
				"comment[adjective]",
				"comment[noun]",
				"comment[verb]"
			};

			expectedTaglibValues = new String[] {
				"Pr\u00e9nom 2.0",
				"Bienvenue 2.0",
				"Jouer au basket-ball est amusement.",
				"Veuillez prendre une boisson fra\u00eeche.",
				"This is an unlocalized hook message.",
				"This is an unlocalized portlet message.",
				"Envoyer",
				"Envoyer",
				"Envoyer",
				"Envoyer",
				"Commentaire",
				"Commentaire",
				"Commentaire",
				"Commentaire"
			};
		}
		else if (locale.getLanguage().equals("es")) {
			expectedResourceBundleValues = new String[] {
				"first-name",
				"Recepci\u00f3n 2.0",
				"playing-basketball-is-fun",
				"Tome por favor una bebida fresca.",
				"this-is-an-unlocalized-hook-message",
				"This is an unlocalized portlet message.",
				"post",
				"post[adjective]",
				"post[noun]",
				"post[verb]",
				"comment",
				"comment[adjective]",
				"Comentario 2.0",
				"Comentar 2.0"
			};

			expectedTaglibValues = new String[] {
				"Nombre 2.0",
				"Recepci\u00f3n 2.0",
				"Jugar a baloncesto es diversi\u00f3n.",
				"Tome por favor una bebida fresca.",
				"This is an unlocalized hook message.",
				"This is an unlocalized portlet message.",
				"Mensaje",
				"Mensaje",
				"Mensaje 2.0",
				"Publicar 2.0",
				"Comentario",
				"Comentario",
				"Comentario 2.0",
				"Comentar 2.0"
			};
		}
		else if (locale.getLanguage().equals("zh")) {
			expectedResourceBundleValues = new String[] {
				"first-name",
				"Welcome 2.0",
				"playing-basketball-is-fun",
				"Please take a cool drink.",
				"this-is-an-unlocalized-hook-message",
				"This is an unlocalized portlet message.",
				"post",
				"post[adjective]",
				"post[noun]",
				"post[verb]",
				"comment",
				"comment[adjective]",
				"comment[noun]",
				"comment[verb]"
			};

			expectedTaglibValues = new String[] {
				"\u540d\u5b57",
				"Welcome 2.0",
				"Playing basketball is fun.",
				"Please take a cool drink.",
				"This is an unlocalized hook message.",
				"This is an unlocalized portlet message.",
				"\u53d1\u5e03",
				"\u53d1\u5e03",
				"\u53d1\u5e03",
				"\u53d1\u5e03",
				"\u8bc4\u8bba",
				"\u8bc4\u8bba",
				"\u8bc4\u8bba",
				"\u8bc4\u8bba"
			};
		}
		else {
			expectedResourceBundleValues = new String[] {
				"first-name",
				"Welcome 2.0",
				"playing-basketball-is-fun",
				"Please take a cool drink.",
				"this-is-an-unlocalized-hook-message",
				"This is an unlocalized portlet message.",
				"post",
				"post[adjective]",
				"post[noun]",
				"post[verb]",
				"comment",
				"comment[adjective]",
				"comment[noun]",
				"comment[verb]"
			};

			expectedTaglibValues = new String[] {
				"First Name 2.0",
				"Welcome 2.0",
				"Playing basketball is fun.",
				"Please take a cool drink.",
				"This is an unlocalized hook message.",
				"This is an unlocalized portlet message.",
				"Post",
				"Post",
				"Post",
				"Post",
				"Comment",
				"Comment",
				"Comment",
				"Comment"
			};
		}

		String[][] tests = new String[][] {
			new String[] {"Override a portal language entry from a hook.", "first-name"},
			new String[] {"Override a portal language entry from a portlet.", "welcome"},
			new String[] {"Add a new portlet language entry from a hook.", "playing-basketball-is-fun"},
			new String[] {"Add a new portlet language entry from a portlet.", "please-take-a-cool-drink"},
			new String[] {"Add a new unlocalized language entry from a hook.", "this-is-an-unlocalized-hook-message"},
			new String[] {"Add a new unlocalized language entry from a portlet.", "this-is-an-unlocalized-portlet-message"},
			new String[] {"Override the default modifier for a portal language entry from a hook.", "post"},
			new String[] {"Override the adjective modifier for a portal language entry from a hook.", "post[adjective]"},
			new String[] {"Override the noun modifier for a portal language entry from a hook.", "post[noun]"},
			new String[] {"Override the verb modifier for a portal language entry from a hook.", "post[verb]"},
			new String[] {"Override the default modifier for a portal language entry from a portlet.", "comment"},
			new String[] {"Override the adjective modifier for a portal language entry from a portlet.", "comment[adjective]"},
			new String[] {"Override the noun modifier for a portal language entry from a portlet.", "comment[noun]"},
			new String[] {"Override the verb modifier for a portal language entry from a portlet.", "comment[verb]"}
		};

		for (int i = 0; i < tests.length; i++) {
			String[] test = tests[i];

			String description = test[0];
			String key = test[1];
		%>

			<tr>
				<td>
					<%= test[0] %>
				</td>
				<td>
					<%= key %>
				</td>
				<td>

					<%
					String resourceBundleActualValue = key;

					try {
						resourceBundleActualValue = portletConfigResourceBundle.getString(key);
					}
					catch (Exception e) {
					}
					%>

					<%= resourceBundleActualValue %>
				</td>
				<td>
					<liferay-util:buffer var="taglibActualValue"><liferay-ui:message key="<%= key %>" /></liferay-util:buffer>

					<%= taglibActualValue %>
				</td>
				<td>

					<%
					if (expectedResourceBundleValues[i].equals(resourceBundleActualValue) && expectedTaglibValues[i].equals(taglibActualValue)) {
					%>

						PASSED

					<%
					}
					else {
					%>

						FAILED

					<%
					}
					%>

				</td>
				<td>

					<%
					if (!expectedResourceBundleValues[i].equals(resourceBundleActualValue)) {
					%>

						The resource bundle is returning <strong><%= resourceBundleActualValue %></strong> when the expected value is <strong><%= expectedResourceBundleValues[i] %></strong>.

					<%
					}
					else if (!expectedTaglibValues[i].equals(taglibActualValue)) {
					%>

						The tag library is returning <strong><%= taglibActualValue %></strong> when the expected value is <strong><%= expectedTaglibValues[i] %></strong>.

					<%
					}
					%>

				</td>
			</tr>

		<%
		}
		%>

	</tbody>
</table>