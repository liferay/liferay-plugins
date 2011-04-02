
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;
import com.liferay.testtransaction.service.BarLocalServiceUtil;

/**
 * @author Shuyang Zhou
 */
public class ServiceInvokeMessageListener implements MessageListener {

	public void receive(Message message) throws MessageListenerException {
		String barText = (String)message.getPayload();
		boolean rollback = message.getBoolean("ROLLBACK");

		try {
			if (rollback) {
				BarLocalServiceUtil.addBarWithoutClassNameRollback(barText);
			}
			else {
				BarLocalServiceUtil.addBarWithoutClassName(barText);
			}
		}
		catch (SystemException se) {
			throw new RuntimeException(se);
		}
	}

}