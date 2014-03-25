<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

<%@ include file="/html/portlet/shopping/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

ShoppingOrder order = (ShoppingOrder)request.getAttribute(WebKeys.SHOPPING_ORDER);

long orderId = BeanParamUtil.getLong(order, request, "orderId");
%>

<portlet:actionURL var="editOrderURL">
	<portlet:param name="struts_action" value="/shopping/edit_order" />
</portlet:actionURL>

<aui:form action="<%= editOrderURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="orderId" type="hidden" value="<%= orderId %>" />
	<aui:input name="number" type="hidden" value="<%= order.getNumber() %>" />
	<aui:input name="emailType" type="hidden" />
	<aui:input name="deleteOrderIds" type="hidden" value="<%= orderId %>" />

	<c:choose>
		<c:when test="<%= windowState.equals(LiferayWindowState.POP_UP) %>">
			<h3 class="header-title">
				<aui:a href="<%= themeDisplay.getURLHome() %>"><img alt="<liferay-ui:message key="logo" />" src="<%= themeDisplay.getCompanyLogo() %>" /></aui:a>

				<liferay-ui:message key="invoice" />
			</h3>
		</c:when>
		<c:otherwise>
			<liferay-ui:header
				backURL="<%= redirect %>"
				title="order"
			/>
		</c:otherwise>
	</c:choose>

	<div class="well">
		<table class="lfr-table">
		<tr>
			<th class="text-left">
				<liferay-ui:message key="order" /> #:
			</th>
			<td>
				<strong><%= HtmlUtil.escape(order.getNumber()) %></strong>
			</td>
		</tr>
		<tr>
			<th class="text-left">
				<liferay-ui:message key="order-date" />:
			</th>
			<td>
				<%= dateFormatDateTime.format(order.getCreateDate()) %>
			</td>
		</tr>
		<tr>
			<th class="text-left">
				<liferay-ui:message key="last-modified" />:
			</th>
			<td>
				<%= dateFormatDateTime.format(order.getModifiedDate()) %>
			</td>
		</tr>
		</table>
	</div>

	<div class="row-fluid">
		<div class="span6">
			<div class="well">
				<h4><liferay-ui:message key="billing-address" /></h4>

				<%@ include file="/html/portlet/shopping/checkout_second_billing_address.jspf" %>
			</div>
		</div>

		<div class="span6">
			<div class="well">
				<h4><liferay-ui:message key="shipping-address" /></h4>

				<%@ include file="/html/portlet/shopping/checkout_second_shipping_address.jspf" %>
			</div>
		</div>
	</div>

	<div class="well">
		<c:choose>
			<c:when test="<%= shoppingPrefs.usePayPal() %>">
				<aui:model-context bean="<%= order %>" model="<%= ShoppingOrder.class %>" />

				<aui:fieldset label="PayPal">
					<aui:select label="status" name="ppPaymentStatus">

						<%
						for (int i = 0; i < ShoppingOrderConstants.STATUSES.length; i++) {
						%>

							<aui:option label="<%= ShoppingOrderConstants.STATUSES[i] %>" selected="<%= ShoppingUtil.getPpPaymentStatus(ShoppingOrderConstants.STATUSES[i]).equals(order.getPpPaymentStatus()) %>" />

						<%
						}
						%>

					</aui:select>

					<aui:input label="transaction-id" name="ppTxnId" />

					<aui:input label="payment-gross" name="ppPaymentGross" value="<%= doubleFormat.format(order.getPpPaymentGross()) %>" />

					<aui:input label="receiver-email-address" name="ppReceiverEmail" />

					<aui:input label="payer-email-address" name="ppPayerEmail" />

					<c:if test="<%= order.getPpPaymentStatus().equals(ShoppingOrderConstants.STATUS_CHECKOUT) %>">
						<aui:field-wrapper label="paypal-order">

							<%
							String payPalLinkOpen = "<a href=\"" + ShoppingUtil.getPayPalRedirectURL(shoppingPrefs, order, ShoppingUtil.calculateTotal(order), ShoppingUtil.getPayPalReturnURL(renderResponse.createActionURL(), order), ShoppingUtil.getPayPalNotifyURL(themeDisplay)) + "\"><strong><u>";
							String payPalLinkClose = "</u></strong></a>";
							%>

							<liferay-ui:message arguments="<%= new Object[] {payPalLinkOpen, payPalLinkClose} %>" key="please-complete-your-order" translateArguments="<%= false %>" />
						</aui:field-wrapper>
					</c:if>
				</aui:fieldset>
			</c:when>
			<c:otherwise>
				<h4><liferay-ui:message key="credit-card" /></h4>

				<table class="lfr-table">
				<tr>
					<th class="text-left">
						<liferay-ui:message key="full-name" />:
					</th>
					<td>
						<%= HtmlUtil.escape(order.getCcName()) %>
					</td>
				</tr>
				<tr>
					<th class="text-left">
						<liferay-ui:message key="type" />:
					</th>
					<td>
						<liferay-ui:message key='<%= "cc_" + order.getCcType() %>' />
					</td>
				</tr>
				<tr>
					<th class="text-left">
						<liferay-ui:message key="number" />:
					</th>
					<td>
						<%= CreditCard.hide(order.getCcNumber()) %>
					</td>
				</tr>
				<tr>
					<th class="text-left">
						<liferay-ui:message key="expiration-date" />:
					</th>
					<td>
						<%= CalendarUtil.getMonths(locale)[order.getCcExpMonth()] %>, <%= order.getCcExpYear() %>
					</td>
				</tr>

				<c:if test="<%= Validator.isNotNull(order.getCcVerNumber()) %>">
					<tr>
						<th class="text-left">
							<liferay-ui:message key="verification-number" />:
						</th>
						<td>
							<%= HtmlUtil.escape(order.getCcVerNumber()) %>
						</td>
					</tr>
				</c:if>

				</table>
			</c:otherwise>
		</c:choose>
	</div>

	<c:if test="<%= Validator.isNotNull(order.getComments()) %>">
		<div class="well">
			<h4><liferay-ui:message key="comments" /></h4>

			<%= HtmlUtil.escape(order.getComments()) %>
		</div>
	</c:if>

	<%
	StringBuilder itemIds = new StringBuilder();

	SearchContainer searchContainer = new SearchContainer();

	List<String> headerNames = new ArrayList<String>();

	headerNames.add("sku");
	headerNames.add("description");
	headerNames.add("quantity");
	headerNames.add("price");
	headerNames.add("total");

	searchContainer.setHeaderNames(headerNames);
	searchContainer.setHover(false);

	List<ShoppingOrderItem> results = ShoppingOrderItemLocalServiceUtil.getOrderItems(order.getOrderId());

	int total = results.size();

	searchContainer.setTotal(total);

	List resultRows = searchContainer.getResultRows();

	for (int i = 0; i < total; i++) {
		ShoppingOrderItem orderItem = results.get(i);

		ShoppingItem item = null;

		try {
			item = ShoppingItemServiceUtil.getItem(ShoppingUtil.getItemId(orderItem.getItemId()));
		}
		catch (Exception e) {
		}

		String[] fieldsArray = StringUtil.split(ShoppingUtil.getItemFields(orderItem.getItemId()), '&');

		int quantity = orderItem.getQuantity();

		ResultRow row = new ResultRow(item, orderItem.getOrderItemId(), i);

		PortletURL rowURL = null;

		if (item != null) {
			rowURL = renderResponse.createRenderURL();

			rowURL.setParameter("struts_action", "/shopping/view_item");
			rowURL.setParameter("itemId", String.valueOf(item.getItemId()));
		}

		// SKU

		row.addText(HtmlUtil.escape(orderItem.getSku()), rowURL);

		// Description

		if (fieldsArray.length > 0) {
			StringBundler sb = new StringBundler(4);

			sb.append(HtmlUtil.escape(orderItem.getName()));
			sb.append(" (");
			sb.append(HtmlUtil.escape(StringUtil.replace(StringUtil.merge(fieldsArray, ", "), "=", ": ")));
			sb.append(StringPool.CLOSE_PARENTHESIS);

			row.addText(sb.toString(), rowURL);
		}
		else {
			row.addText(HtmlUtil.escape(orderItem.getName()), rowURL);
		}

		// Quantity

		row.addText(String.valueOf(quantity), rowURL);

		// Price

		row.addText(currencyFormat.format(orderItem.getPrice()), rowURL);

		// Total

		row.addText(currencyFormat.format(orderItem.getPrice() * quantity), rowURL);

		// Add result row

		resultRows.add(row);
	}
	%>

	<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />

	<div class="well">
		<table class="lfr-table">
		<tr>
			<th class="text-left">
				<liferay-ui:message key="subtotal" />:
			</th>
			<td>
				<%= currencyFormat.format(ShoppingUtil.calculateActualSubtotal(results)) %>
			</td>
		</tr>
		<tr>
			<th class="text-left">
				<liferay-ui:message key="tax" />:
			</th>
			<td>
				<%= currencyFormat.format(order.getTax()) %>
			</td>
		</tr>
		<tr>
			<th class="text-left">
				<liferay-ui:message key="shipping" /> <%= Validator.isNotNull(order.getAltShipping()) ? "(" + HtmlUtil.escape(order.getAltShipping()) + ")" : StringPool.BLANK %>
			</th>
			<td>
				<%= currencyFormat.format(order.getShipping()) %>
			</td>
		</tr>

		<c:if test="<%= order.isInsure() %>">
			<tr>
				<th class="text-left">
					<liferay-ui:message key="insurance" />:
				</th>
				<td>
					<%= currencyFormat.format(order.getInsurance()) %>
				</td>
			</tr>
		</c:if>

		<c:if test="<%= Validator.isNotNull(order.getCouponCodes()) %>">
			<tr>
				<th class="text-left">
					<liferay-ui:message key="coupon-discount" />:
				</th>
				<td>
					<%= currencyFormat.format(order.getCouponDiscount()) %>

					<portlet:renderURL var="viewCouponURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
						<portlet:param name="struts_action" value="/shopping/view_coupon" />
						<portlet:param name="code" value="<%= order.getCouponCodes() %>" />
					</portlet:renderURL>

					<%
					String taglibOpenCouponWindow = "var viewCouponWindow = window.open('" + viewCouponURL + "', 'viewCoupon', 'directories=no,height=200,location=no,menubar=no,resizable=no,scrollbars=yes,status=no,toolbar=no,width=280'); void(''); viewCouponWindow.focus();";
					%>

					<aui:a href='<%= "javascript:" + taglibOpenCouponWindow %>' label='<%= "(" + LanguageUtil.get(pageContext, order.getCouponCodes()) + ")" %>' />
				</td>
			</tr>
		</c:if>

		<tr>
			<th class="text-left">
				<liferay-ui:message key="total" />:
			</th>
			<td>
				<%= currencyFormat.format(ShoppingUtil.calculateTotal(order)) %>
			</td>
		</tr>
		</table>
	</div>

	<c:if test="<%= !windowState.equals(LiferayWindowState.POP_UP) %>">
		<aui:button-row>
			<c:if test="<%= shoppingPrefs.usePayPal() %>">
				<aui:button onClick='<%= renderResponse.getNamespace() + "saveOrder();" %>' value="save" />
			</c:if>

			<portlet:renderURL var="viewInvoiceURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="struts_action" value="/shopping/edit_order" />
				<portlet:param name="orderId" value="<%= String.valueOf(orderId) %>" />
			</portlet:renderURL>

			<%
			String taglibOpenInvoiceWindow = "window.open('" + viewInvoiceURL + "');";
			%>

			<aui:button onClick="<%= taglibOpenInvoiceWindow %>" value="invoice" />

			<%
			String taglibSendEmailConfirmation = renderResponse.getNamespace() + "sendEmail('confirmation');";
			%>

			<aui:button onClick="<%= taglibSendEmailConfirmation %>" value='<%= LanguageUtil.get(pageContext, (order.isSendOrderEmail() ? "" : "re") + "send-confirmation-email") %>' />

			<%
			String taglibSendEmailShipping = renderResponse.getNamespace() + "sendEmail('shipping');";
			%>

			<aui:button onClick="<%= taglibSendEmailShipping %>" value='<%= LanguageUtil.get(pageContext, (order.isSendShippingEmail() ? "" : "re") + "send-shipping-email") %>' />

			<c:if test="<%= ShoppingOrderPermission.contains(permissionChecker, scopeGroupId, order, ActionKeys.DELETE) %>">
				<aui:button onClick='<%= renderResponse.getNamespace() + "deleteOrder();" %>' value="delete" />
			</c:if>

			<aui:button href="<%= redirect %>" type="cancel" />
		</aui:button-row>
	</c:if>
</aui:form>

<c:if test="<%= PropsValues.SHOPPING_ORDER_COMMENTS_ENABLED && !windowState.equals(LiferayWindowState.POP_UP) %>">
	<liferay-ui:panel-container extended="<%= true %>"  id="shoppingEditOrderPanelContainer" persistState="<%= true %>">
		<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" id="shoppingEditOrderCommentsPanel" persistState="<%= true %>" title="comments">
			<portlet:actionURL var="discussionURL">
				<portlet:param name="struts_action" value="/shopping/edit_order_discussion" />
			</portlet:actionURL>

			<liferay-ui:discussion
				className="<%= ShoppingOrder.class.getName() %>"
				classPK="<%= order.getOrderId() %>"
				formAction="<%= discussionURL %>"
				formName="fm2"
				redirect="<%= currentURL %>"
				userId="<%= order.getUserId() %>"
			/>
		</liferay-ui:panel>
	</liferay-ui:panel-container>
</c:if>

<aui:script>
	function <portlet:namespace />deleteOrder() {
		document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = "<%= Constants.DELETE %>";
		document.<portlet:namespace />fm.<portlet:namespace />redirect.value = "<%= HtmlUtil.escapeURL(redirect) %>";

		submitForm(document.<portlet:namespace />fm);
	}

	function <portlet:namespace />saveOrder() {
		document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = "<%= Constants.UPDATE %>";

		submitForm(document.<portlet:namespace />fm);
	}

	function <portlet:namespace />sendEmail(emailType) {
		document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = "sendEmail";
		document.<portlet:namespace />fm.<portlet:namespace />emailType.value = emailType;

		submitForm(document.<portlet:namespace />fm);
	}
</aui:script>