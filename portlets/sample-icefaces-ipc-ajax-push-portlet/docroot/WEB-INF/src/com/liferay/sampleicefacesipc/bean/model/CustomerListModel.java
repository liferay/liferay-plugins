/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.sampleicefacesipc.bean.model;

import com.icesoft.faces.async.render.OnDemandRenderer;
import com.icesoft.faces.async.render.RenderManager;
import com.icesoft.faces.async.render.Renderable;
import com.icesoft.faces.context.DisposableBean;
import com.icesoft.faces.webapp.xmlhttp.PersistentFacesState;
import com.icesoft.faces.webapp.xmlhttp.RenderingException;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.sampleicefacesipc.service.CustomerService;
import com.liferay.sampleicefacesipc.transfer.Customer;
import com.liferay.sampleicefacesipc.util.PortletSessionUtil;

import java.util.List;

import javax.faces.event.ValueChangeEvent;

/**
 * @author Neil Griffin
 */
public class CustomerListModel implements DisposableBean, Renderable {

	public CustomerListModel() {
		_persistentFacesState = PersistentFacesState.getInstance();
	}

	public List<Customer> getAllCustomers() {
		List<Customer> allCustomers =
			(List<Customer>)PortletSessionUtil.getSharedSessionAttribute(
				PortletSessionUtil.CUSTOMER_LIST);

		if (allCustomers == null) {
			allCustomers = _customerService.getAllCustomers();

			PortletSessionUtil.setSharedSessionAttribute(
				PortletSessionUtil.CUSTOMER_LIST, allCustomers);
		}

		return allCustomers;
	}

	public CustomerService getCustomerService() {
		return _customerService;
	}

	public RenderManager getRenderManager() {
		return _renderManager;
	}

	public Customer getSelected() {
		return (Customer)PortletSessionUtil.getSharedSessionAttribute(
			PortletSessionUtil.SELECTED_CUSTOMER);
	}

	public PersistentFacesState getState() {
		return _persistentFacesState;
	}

	public void dispose() throws Exception {

		// http://jira.icefaces.org/browse/ICE-2896
		// http://jira.icefaces.org/browse/ICE-2904

		OnDemandRenderer onDemandRenderer =
			getRenderManager().getOnDemandRenderer(CUSTOMER_RENDER_GROUP);

		onDemandRenderer.remove(this);
	}

	public void renderingException(RenderingException e) {
		_log.error(e, e);
	}

	public void setCustomerService(CustomerService customerService) {
		_customerService = customerService;
	}

	public void setRenderManager(RenderManager renderManager) {
		_renderManager = renderManager;

		OnDemandRenderer onDemandRenderer =
			getRenderManager().getOnDemandRenderer(CUSTOMER_RENDER_GROUP);

		onDemandRenderer.add(this);
	}

	public void setSelected(Customer customer) {
		PortletSessionUtil.setSharedSessionAttribute(
			PortletSessionUtil.SELECTED_CUSTOMER, customer);

		OnDemandRenderer onDemandRenderer =
			getRenderManager().getOnDemandRenderer(CUSTOMER_RENDER_GROUP);

		onDemandRenderer.requestRender();
	}

	public void valueChangeListener(ValueChangeEvent valueChangeEvent) {
		OnDemandRenderer onDemandRenderer =
			getRenderManager().getOnDemandRenderer(CUSTOMER_RENDER_GROUP);

		onDemandRenderer.requestRender();
	}

	private static final String CUSTOMER_RENDER_GROUP = "CUSTOMER_RENDER_GROUP";

	private static final Log _log = LogFactoryUtil.getLog(
		CustomerListModel.class);

	private CustomerService _customerService;
	private PersistentFacesState _persistentFacesState;
	private RenderManager _renderManager;

}