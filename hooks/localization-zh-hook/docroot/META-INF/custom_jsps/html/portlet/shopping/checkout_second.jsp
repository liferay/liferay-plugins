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

<%@ include file="/html/portlet/shopping/init.jsp" %>

<%
ShoppingCart cart = ShoppingUtil.getCart(renderRequest);

Map items = cart.getItems();

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

	<table class="lfr-table">
	<tr>
		<td>
			<strong><liferay-ui:message key="billing-address" /></strong>

			<br /><br />

			<%@ include file="/html/portlet/shopping/checkout_second_billing_address.jspf" %>
		</td>
		<td class="lfr-top">
			<strong><liferay-ui:message key="shipping-address" /></strong>

			<br /><br />

			<%@ include file="/html/portlet/shopping/checkout_second_shipping_address.jspf" %>
		</td>
	</tr>
	</table>

	<c:if test="<%= !shoppingPrefs.usePayPal() %>">
		<br />

		<strong><liferay-ui:message key="credit-card" /></strong>

		<br /><br />

		<table class="lfr-table">
		<tr>
			<td>
				<liferay-ui:message key="full-name" />:
			</td>
			<td>
				<%= HtmlUtil.escape(order.getCcName()) %>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="type" />:
			</td>
			<td>
				<liferay-ui:message key='<%= "cc_" + HtmlUtil.escape(order.getCcType()) %>' />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="number" />:
			</td>
			<td>
				<%= CreditCard.hide(order.getCcNumber()) %>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="expiration-date" />:
			</td>
			<td>
				<%= CalendarUtil.getMonths(locale)[order.getCcExpMonth()] %>, <%= order.getCcExpYear() %>
			</td>
		</tr>

		<c:if test="<%= Validator.isNotNull(order.getCcVerNumber()) %>">
			<tr>
				<td>
					<liferay-ui:message key="verification-number" />:
				</td>
				<td>
					<%= HtmlUtil.escape(order.getCcVerNumber()) %>
				</td>
			</tr>
		</c:if>

		</table>
	</c:if>

	<br />

	<c:if test="<%= Validator.isNotNull(order.getComments()) %>">
		<strong><liferay-ui:message key="comments" /></strong>

		<br /><br />

		<%= HtmlUtil.escape(order.getComments()) %>

		<br /><br />
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

	Set results = items.entrySet();
	int total = items.size();

	searchContainer.setTotal(total);

	List resultRows = searchContainer.getResultRows();

	Iterator itr = results.iterator();

	for (int i = 0; itr.hasNext(); i++) {
		Map.Entry entry = (Map.Entry)itr.next();

		ShoppingCartItem cartItem = (ShoppingCartItem)entry.getKey();
		Integer count = (Integer)entry.getValue();

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

		row.addText(item.getSku(), rowURL);

		// Description

		if (item.isFields()) {
			StringBundler sb = new StringBundler(4);

			sb.append(HtmlUtil.escape(item.getName()));
			sb.append(" (");
			sb.append(StringUtil.replace(StringUtil.merge(cartItem.getFieldsArray(), ", "), "=", ": "));
			sb.append(")");

			row.addText(sb.toString(), rowURL);
		}
		else {
			row.addText(HtmlUtil.escape(item.getName()), rowURL);
		}

		// Availability

		if (ShoppingUtil.isInStock(item, itemFields, fieldsArray, count)) {
			row.addText("<div class=\"portlet-msg-success\">".concat(LanguageUtil.get(pageContext, "in-stock")).concat("</div>"), rowURL);
		}
		else {
			row.addText("<div class=\"portlet-msg-error\">".concat(LanguageUtil.get(pageContext, "out-of-stock")).concat("</div>"), rowURL);
		}

		// Quantity

		row.addText(count.toString(), rowURL);

		// Price

		row.addText(currencyFormat.format(ShoppingUtil.calculateActualPrice(item, count.intValue()) / count.intValue()), rowURL);

		// Total

		row.addText(currencyFormat.format(ShoppingUtil.calculateActualPrice(item, count.intValue())), rowURL);

		// Add result row

		resultRows.add(row);
	}
	%>

	<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />

	<aui:input name="itemIds" type="hidden" value="<%= itemIds %>" />
	<aui:input name="couponCodes" type="hidden" value="<%= cart.getCouponCodes() %>" />

	<br />

	<table class="lfr-table">
	<tr>
		<td>
			<liferay-ui:message key="subtotal" />:
		</td>
		<td>
			<%= currencyFormat.format(ShoppingUtil.calculateActualSubtotal(items)) %>
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="tax" />:
		</td>
		<td>
			<%= currencyFormat.format(ShoppingUtil.calculateTax(items, order.getBillingState())) %>
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="shipping" /> <%= Validator.isNotNull(altShippingName) ? "(" + altShippingName + ")" : StringPool.BLANK %>:
		</td>
		<td>
			<%= currencyFormat.format(ShoppingUtil.calculateAlternativeShipping(items, altShipping)) %>
		</td>
	</tr>

	<%
	double insurance = ShoppingUtil.calculateInsurance(items);
	%>

	<c:if test="<%= cart.isInsure() && (insurance > 0) %>">
		<tr>
			<td>
				<liferay-ui:message key="insurance" />:
			</td>
			<td>
				<%= currencyFormat.format(insurance) %>
			</td>
		</tr>
	</c:if>

	<c:if test="<%= coupon != null %>">
		<tr>
			<td>
				<liferay-ui:message key="coupon-discount" />:
			</td>
			<td>
				<%= currencyFormat.format(ShoppingUtil.calculateCouponDiscount(items, order.getBillingState(), coupon)) %>

				<portlet:renderURL var="viewCouponURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
					<portlet:param name="struts_action" value="/shopping/view_coupon" />
					<portlet:param name="couponId" value="<%= String.valueOf(coupon.getCouponId()) %>" />
				</portlet:renderURL>

				<%
				String taglibOpenCouponWindow = "var viewCouponWindow = window.open('" + viewCouponURL + "', 'viewCoupon', 'directories=no,height=200,location=no,menubar=no,resizable=no,scrollbars=yes,status=no,toolbar=no,width=280'); void(''); viewCouponWindow.focus();";
				%>

				<aui:a href="<%= taglibOpenCouponWindow %>" label='<%= "(" + coupon.getCouponId() + ")" %>' />
			</td>
		</tr>
	</c:if>

	<tr>
		<td>
			<liferay-ui:message key="total" />:
		</td>
		<td>
		<%= currencyFormat.format(ShoppingUtil.calculateTotal(items, order.getBillingState(), coupon, altShipping, cart.isInsure())) %>
		</td>
	</tr>
	</table>

	<aui:button-row>
		<aui:button type="submit" value='<%= shoppingPrefs.usePayPal() ? "continue" : "finished" %>' />
	</aui:button-row>
</aui:form>