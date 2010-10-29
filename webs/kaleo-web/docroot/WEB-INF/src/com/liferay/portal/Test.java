/*
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.portal;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <a href="Test.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public class Test {
	public static void main(String[] args) {
		try {
			String expectedCategory = null;
			boolean returnValue;
			
			Map<String, Serializable> workflowContext =
				new HashMap<String, Serializable>();

			String className = (String)workflowContext.get(
				WorkflowConstants.CONTEXT_ENTRY_CLASS_NAME);
			long classPK = GetterUtil.getLong((String)workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));

			AssetEntry entry = AssetEntryLocalServiceUtil.getEntry(className, classPK);
			Iterator categoriesIter = entry.getCategories().iterator();

			while (categoriesIter.hasNext()) {
				AssetCategory category = (AssetCategory)categoriesIter.next();
				if (category.getName().equals(expectedCategory)) {
					returnValue = true;
					return;
				}
			}

			returnValue = false;
		}
		catch (Exception e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}


	}
}
