/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

package sample.icefacesipc.bean.model;

import java.util.List;

import javax.faces.event.ValueChangeEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sample.icefacesipc.service.CustomerService;
import sample.icefacesipc.transfer.Customer;
import sample.icefacesipc.util.PortletSessionUtil;

import com.icesoft.faces.async.render.RenderManager;
import com.icesoft.faces.async.render.Renderable;
import com.icesoft.faces.context.DisposableBean;
import com.icesoft.faces.webapp.xmlhttp.PersistentFacesState;
import com.icesoft.faces.webapp.xmlhttp.RenderingException;

/**
 * <a href="CustomerListModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * This class is a model managed-bean for the customerList.xhtml view
 * (Customers Portlet) and bookingList.xhtml view (Bookings Portlet).
 * As a model bean, this class handles data-related concerns in the
 * "model" layer of the MVC design pattern.
 * </p>
 * <p>
 * This class also participates in ICEfaces Ajax Push. Whenever the
 * selected customer is changed in the customerList.xhtml view, the
 * setSelected() method will trigger an ICEfaces Ajax Push operation.
 * Since the bookingList.xhtml view is referencing this model bean
 * as well, the Ajax Push operation will cause the bookingList.xhtml
 * view to re-render itself according to the selected customer.
 * Similarly, when the user edits the first or last name in the
 * bookingList.xhtml view, then the valueChangeListener will trigger
 * another Ajax Push operation in order to ensure that the first/last
 * name columns in the customerList.xhtml view are are re-rendered.
 * </p>
 *
 * @author Neil Griffin
 *
 */
public class CustomerListModel implements DisposableBean, Renderable {

	public CustomerListModel() {
		_persistentFacesState = PersistentFacesState.getInstance();
	}

	@SuppressWarnings("unchecked")
	public List<Customer> getAllCustomers() {

		List<Customer> allCustomers = (List<Customer>)PortletSessionUtil
			.getSharedSessionAttribute(PortletSessionUtil.ATTR_CUSTOMER_LIST);

		if (allCustomers == null) {
			allCustomers = getCustomerService().getAllCustomers();
			PortletSessionUtil.setSharedSessionAttribute(
				PortletSessionUtil.ATTR_CUSTOMER_LIST, allCustomers);
		}

		return allCustomers;
	}

	public void valueChangeListener(ValueChangeEvent valueChangeEvent) {
		getRenderManager().getOnDemandRenderer(RENDER_GROUP).requestRender();
	}
	
	public CustomerService getCustomerService() {
		return _customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		_customerService = customerService;
	}

	public void dispose() throws Exception {

		// http://jira.icefaces.org/browse/ICE-2896
		// http://jira.icefaces.org/browse/ICE-2904
		getRenderManager().getOnDemandRenderer(RENDER_GROUP).remove(this);
	}

	public void renderingException(RenderingException e) {
		_log.error(e, e);
	}

	public RenderManager getRenderManager() {
		return _renderManager;
	}

	public void setRenderManager(RenderManager renderManager) {
		_renderManager = renderManager;
		_renderManager.getOnDemandRenderer(RENDER_GROUP).add(this);
	}

	public Customer getSelected() {
		return (Customer)PortletSessionUtil.getSharedSessionAttribute(
				PortletSessionUtil.ATTR_SELECTED_CUSTOMER);
	}

	public void setSelected(Customer customer) {

		PortletSessionUtil.setSharedSessionAttribute(
			PortletSessionUtil.ATTR_SELECTED_CUSTOMER, customer);

		// Use ICEfaces Ajax-Push to force the Bookings portlet to re-render
		// the bookings according to the newly selected user.
		getRenderManager().getOnDemandRenderer(RENDER_GROUP).requestRender();
	}

	public PersistentFacesState getState() {
		return _persistentFacesState;
	}

	private static final Log _log = LogFactory.getLog(CustomerListModel.class);
	private static final String RENDER_GROUP = "CUSTOMER_RENDER_GROUP";

	private CustomerService _customerService;
	private PersistentFacesState _persistentFacesState;
	private RenderManager _renderManager;
}
