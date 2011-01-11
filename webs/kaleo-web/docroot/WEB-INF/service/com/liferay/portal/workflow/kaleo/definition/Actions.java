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
public class Actions extends DefinitionNode {

	public void addAction(Action action) {
		_actions.add(action);
	}

	public void addNotification(Notification notification) {
		_notifications.add(notification);
	}

	public void configureParent(DefinitionNode parentNode) {
		Node node = (Node)parentNode;

		node.setActions(_actions);
		node.setNotifications(_notifications);
	}

	private Set<Action> _actions = new HashSet<Action>();
	private Set<Notification> _notifications = new HashSet<Notification>();

}