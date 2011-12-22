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

package com.liferay.testtransaction.messaging;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.testtransaction.service.BarLocalServiceUtil;

/**
 * @author Shuyang Zhou
 */
public class TestTransactionMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		boolean rollback = message.getBoolean("rollback");
		String text = message.getString("text");

		if (rollback) {
			BarLocalServiceUtil.addBar_Rollback(text);
		}
		else {
			BarLocalServiceUtil.addBar_Success(text);
		}
	}

}