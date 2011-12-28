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

package com.liferay.sampleicefacesipc.bean.backing;

import com.icesoft.faces.component.ext.RowSelectorEvent;

import com.liferay.sampleicefacesipc.bean.model.CustomerListModel;
import com.liferay.sampleicefacesipc.transfer.Customer;

/**
 * @author Neil Griffin
 */
public class CustomerList {

	public CustomerListModel getCustomerListModel() {
		return _customerListModel;
	}

	public void selectionListener(RowSelectorEvent rowSelectorEvent) {
		Customer customer = _customerListModel.getAllCustomers().get(
			rowSelectorEvent.getRow());

		_customerListModel.setSelected(customer);
	}

	public void setCustomerListModel(CustomerListModel customerListModel) {
		_customerListModel = customerListModel;
	}

	private CustomerListModel _customerListModel;

}