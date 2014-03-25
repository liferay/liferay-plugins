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
ShoppingCart cart = ShoppingUtil.getCart(renderRequest);

Map<ShoppingCartItem, Integer> items = cart.getItems();

ShoppingCoupon coupon = cart.getCoupon();

int altShipping = cart.getAltShipping();
String altShippingName = shoppingPrefs.getAlternativeShippingName(altShipping);

ShoppingOrder order = (ShoppingOrder)request.getAttribute(WebKeys.SHOPPING_ORDER);
%>

<portlet:actionURL var="checkoutSecondURL">
	<portlet:param name="struts_action" value="/shopping/checkout" />
</portlet:actionURL>

<aui:form action="<%= checkoutSecondURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.SAVE %>" />
	<aui:input name="billingFirstName" type="hidden" value="<%= order.getBillingFirstName() %>" />
	<aui:input name="billingLastName" type="hidden" value="<%= order.getBillingLastName() %>" />
	<aui:input name="billingEmailAddress" type="hidden" value="<%= order.getBillingEmailAddress() %>" />
	<aui:input name="billingCompany" type="hidden" value="<%= order.getBillingCompany() %>" />
	<aui:input name="billingStreet" type="hidden" value="<%= order.getBillingStreet() %>" />
	<aui:input name="billingCity" type="hidden" value="<%= order.getBillingCity() %>" />
	<aui:input name="billingState" type="hidden" value="<%= order.getBillingState() %>" />
	<aui:input name="billingZip" type="hidden" value="<%= order.getBillingZip() %>" />
	<aui:input name="billingCountry" type="hidden" value="<%= order.getBillingCountry() %>" />
	<aui:input name="billingPhone" type="hidden" value="<%= order.getBillingPhone() %>" />
	<aui:input name="shipToBilling" type="hidden" value="<%= order.isShipToBilling() %>" />
	<aui:input name="shippingFirstName" type="hidden" value="<%= order.getShippingFirstName() %>" />
	<aui:input name="shippingLastName" type="hidden" value="<%= order.getShippingLastName() %>" />
	<aui:input name="shippingEmailAddress" type="hidden" value="<%= order.getShippingEmailAddress() %>" />
	<aui:input name="shippingCompany" type="hidden" value="<%= order.getShippingCompany() %>" />
	<aui:input name="shippingStreet" type="hidden" value="<%= order.getShippingStreet() %>" />
	<aui:input name="shippingCity" type="hidden" value="<%= order.getShippingCity() %>" />
	<aui:input name="shippingState" type="hidden" value="<%= order.getShippingState() %>" />
	<aui:input name="shippingZip" type="hidden" value="<%= order.getShippingZip() %>" />
	<aui:input name="shippingCountry" type="hidden" value="<%= order.getShippingCountry() %>" />
	<aui:input name="shippingPhone" type="hidden" value="<%= order.getShippingPhone() %>" />
	<aui:input name="ccName" type="hidden" value="<%= order.getCcName() %>" />
	<aui:input name="ccType" type="hidden" value="<%= order.getCcType() %>" />
	<aui:input name="ccNumber" type="hidden" value="<%= order.getCcNumber() %>" />
	<aui:input name="ccExpMonth" type="hidden" value="<%= order.getCcExpMonth() %>" />
	<aui:input name="ccExpYear" type="hidden" value="<%= order.getCcExpYear() %>" />
	<aui:input name="ccVerNumber" type="hidden" value="<%= order.getCcVerNumber() %>" />
	<aui:input name="comments" type="hidden" value="<%= order.getComments() %>" />

	<liferay-util:include page="/html/portlet/shopping/tabs1.jsp">
		<liferay-util:param name="tabs1" value="cart" />
	</liferay-util:include>

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

	<c:if test="<%= !shoppingPrefs.usePayPal() %>">
		<div class="well">
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
					<liferay-ui:message key='<%= "cc_" + HtmlUtil.escape(order.getCcType()) %>' />
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
		</div>
	</c:if>

	<c:if test="<%= Validator.isNotNull(order.getComments()) %>">
		<div class="well">
			<h4><liferay-ui:message key="comments" /></h4>

			<%= HtmlUtil.escape(order.getComments()) %>
		</div>
	</c:if>

	<%
	boolean showAvailability = PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.SHOPPING_ITEM_SHOW_AVAILABILITY);

	StringBundler itemIds = new StringBundler();

	SearchContainer searchContainer = new SearchContainer();

	List<String> headerNames = new ArrayList<String>();

	headerNames.add("sku");
	headerNames.add("description");

	if (showAvailability) {
		headerNames.add("availability");
	}

	headerNames.add("quantity");
	headerNames.add("price");
	headerNames.add("total");

	searchContainer.setHeaderNames(headerNames);
	searchContainer.setHover(false);

	int total = items.size();

	searchContainer.setTotal(total);

	List resultRows = searchContainer.getResultRows();

	int i = 0;

	for (Map.Entry<ShoppingCartItem, Integer> entry : items.entrySet()) {
		ShoppingCartItem cartItem = entry.getKey();
		Integer count = entry.getValue();

		ShoppingItem item = cartItem.getItem();
		String[] fieldsArray = cartItem.getFieldsArray();

		ShoppingItemField[] itemFields = (ShoppingItemField[])ShoppingItemFieldLocalServiceUtil.getItemFields(item.getItemId()).toArray(new ShoppingItemField[0]);

		for (int j = 0; j < count.intValue(); j++) {
			itemIds.append(cartItem.getCartItemId());
			itemIds.append(",");
		}

		ResultRow row = new ResultRow(item, item.getItemId(), i);

		PortletURL rowURL = renderResponse.createRenderURL();

		rowURL.setParameter("struts_action", "/shopping/view_item");
		rowURL.setParameter("itemId", String.valueOf(item.getItemId()));

		// SKU

		row.addText(HtmlUtil.escape(item.getSku()), rowURL);

		// Description

		if (item.isFields()) {
			StringBundler sb = new StringBundler(4);

			sb.append(HtmlUtil.escape(item.getName()));
			sb.append(" (");
			sb.append(HtmlUtil.escape(StringUtil.replace(StringUtil.merge(cartItem.getFieldsArray(), ", "), "=", ": ")));
			sb.append(StringPool.CLOSE_PARENTHESIS);

			row.addText(sb.toString(), rowURL);
		}
		else {
			row.addText(HtmlUtil.escape(item.getName()), rowURL);
		}

		// Availability

		if (ShoppingUtil.isInStock(item, itemFields, fieldsArray, count)) {
			row.addText("<div class=\"alert alert-success\">".concat(LanguageUtil.get(pageContext, "in-stock")).concat("</div>"), rowURL);
		}
		else {
			row.addText("<div class=\"alert alert-error\">".concat(LanguageUtil.get(pageContext, "out-of-stock")).concat("</div>"), rowURL);
		}

		// Quantity

		row.addText(count.toString(), rowURL);

		// Price

		row.addText(currencyFormat.format(ShoppingUtil.calculateActualPrice(item, count.intValue()) / count.intValue()), rowURL);

		// Total

		row.addText(currencyFormat.format(ShoppingUtil.calculateActualPrice(item, count.intValue())), rowURL);

		// Add result row

		resultRows.add(row);

		i++;
	}
	%>

	<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />

	<aui:input name="itemIds" type="hidden" value="<%= itemIds %>" />
	<aui:input name="couponCodes" type="hidden" value="<%= cart.getCouponCodes() %>" />

	<div class="well">
		<table class="lfr-table">
		<tr>
			<th class="text-left">
				<liferay-ui:message key="subtotal" />:
			</th>
			<td>
				<%= currencyFormat.format(ShoppingUtil.calculateActualSubtotal(items)) %>
			</td>
		</tr>
		<tr>
			<th class="text-left">
				<liferay-ui:message key="tax" />:
			</th>
			<td>
				<%= currencyFormat.format(ShoppingUtil.calculateTax(items, order.getBillingState())) %>
			</td>
		</tr>
		<tr>
			<th class="text-left">
				<liferay-ui:message key="shipping" /> <%= Validator.isNotNull(altShippingName) ? "(" + altShippingName + ")" : StringPool.BLANK %>:
			</th>
			<td>
				<%= currencyFormat.format(ShoppingUtil.calculateAlternativeShipping(items, altShipping)) %>
			</td>
		</tr>

		<%
		double insurance = ShoppingUtil.calculateInsurance(items);
		%>

		<c:if test="<%= cart.isInsure() && (insurance > 0) %>">
			<tr>
				<th class="text-left">
					<liferay-ui:message key="insurance" />:
				</th>
				<td>
					<%= currencyFormat.format(insurance) %>
				</td>
			</tr>
		</c:if>

		<c:if test="<%= coupon != null %>">
			<tr>
				<th class="text-left">
					<liferay-ui:message key="coupon-discount" />:
				</th>
				<td>
					<%= currencyFormat.format(ShoppingUtil.calculateCouponDiscount(items, order.getBillingState(), coupon)) %>

					<portlet:renderURL var="viewCouponURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
						<portlet:param name="struts_action" value="/shopping/view_coupon" />
						<portlet:param name="couponId" value="<%= String.valueOf(coupon.getCouponId()) %>" />
					</portlet:renderURL>

					<%
					String taglibOpenCouponWindow = "javascript:viewCouponWindow = window.open('" + viewCouponURL + "', 'viewCoupon', 'directories=no,height=200,location=no,menubar=no,resizable=no,scrollbars=yes,status=no,toolbar=no,width=280'); void(''); viewCouponWindow.focus();";
					%>

					<aui:a href="<%= taglibOpenCouponWindow %>" label='<%= "(" + coupon.getCouponId() + ")" %>' />
				</td>
			</tr>
		</c:if>

		<tr>
			<th class="text-left">
				<liferay-ui:message key="total" />:
			</th>
			<td>
			<%= currencyFormat.format(ShoppingUtil.calculateTotal(items, order.getBillingState(), coupon, altShipping, cart.isInsure())) %>
			</td>
		</tr>
		</table>
	</div>

	<aui:button-row>
		<aui:button type="submit" value='<%= shoppingPrefs.usePayPal() ? "continue" : "finished" %>' />
	</aui:button-row>
</aui:form>