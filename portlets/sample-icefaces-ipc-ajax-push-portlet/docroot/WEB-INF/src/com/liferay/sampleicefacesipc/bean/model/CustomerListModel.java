/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.sampleicefacesipc.bean.model;

import java.util.List;

import javax.faces.event.ValueChangeEvent;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import com.liferay.sampleicefacesipc.service.CustomerService;
import com.liferay.sampleicefacesipc.transfer.Customer;
import com.liferay.sampleicefacesipc.util.PortletSessionUtil;
import com.icesoft.faces.async.render.OnDemandRenderer;

import com.icesoft.faces.async.render.RenderManager;
import com.icesoft.faces.async.render.Renderable;
import com.icesoft.faces.context.DisposableBean;
import com.icesoft.faces.webapp.xmlhttp.PersistentFacesState;
import com.icesoft.faces.webapp.xmlhttp.RenderingException;

/**
 * <a href="CustomerListModel.java.html"><b><i>View Source</i></b></a>
 *
 * @author Neil Griffin
 *
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

	private static final Log _log = LogFactoryUtil.getLog(CustomerListModel.class);

	private CustomerService _customerService;
	private PersistentFacesState _persistentFacesState;
	private RenderManager _renderManager;

}