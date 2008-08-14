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

package sample.icefacesipc.bean.backing;

import com.icesoft.faces.component.ext.RowSelectorEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sample.icefacesipc.bean.model.CustomerListModel;

import sample.icefacesipc.transfer.Customer;

/**
 * <a href="CustomerList.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * This class is the backing managed-bean behind the customerList.xhtml view.
 * As a backing bean, this class participates in supporting UI actions in the
 * "view" layer of the MVC design pattern, and delegates data operations to
 * the injected CustomerListModel (model bean) object.
 * </p>
 *
 * @author Neil Griffin
 *
 */
public class CustomerList {

	public CustomerListModel getCustomerListModel() {
		return _customerListModel;
	}

	public void setCustomerListModel(CustomerListModel customerListModel) {
		_customerListModel = customerListModel;
	}

	public void selectionListener(RowSelectorEvent rowSelectorEvent) {

		CustomerListModel customerListModel = getCustomerListModel();

		int rowIndex = rowSelectorEvent.getRow();
		Customer customer = customerListModel.getAllCustomers().get(rowIndex);
		customerListModel.setSelected(customer);

		if (_log.isInfoEnabled()) {
			_log.info(
				"rowIndex=" + rowSelectorEvent.getRow() + " customerId=" +
				customer.getCustomerId() + " firstName=" +
				customer.getFirstName() + " lastName=" +
				customer.getLastName());
		}
	}

	private static final Log _log = LogFactory.getLog(CustomerList.class);

	private CustomerListModel _customerListModel;
}
