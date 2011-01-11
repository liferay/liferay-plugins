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

package com.liferay.portal.workflow.kaleo.definition;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Marcellus Tavares
 */
public class Recipients extends DefinitionNode {

	public void configureParent(DefinitionNode parentNode) {
		Notification notification = (Notification)parentNode;

		for (Recipient recipient : _recipients) {
			notification.addRecipient(recipient);
		}
	}

	public void addRecipient(Recipient recipient) {
		_recipients.add(recipient);
	}

	public Set<Recipient> _recipients = new HashSet<Recipient>();

}