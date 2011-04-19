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

package com.liferay.knowledgebase.hook.events;

import com.liferay.knowledgebase.model.impl.KBFreeMarkerTemplateParserImpl;
import com.liferay.knowledgebase.model.impl.KBVelocityTemplateParserImpl;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.util.InstancePool;

/**
 * @author Peter Shin
 */
public class StartupAction extends SimpleAction {

	public void run(String[] ids) throws ActionException {
		try {
			clearCache();
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
	}

	protected void clearCache() throws Exception {
		Class<?>[] classes = {
			KBFreeMarkerTemplateParserImpl.class,
			KBVelocityTemplateParserImpl.class
		};

		for (Class<?> clazz : classes) {
			InstancePool.put(clazz.getName(), clazz.newInstance());
		}
	}

}