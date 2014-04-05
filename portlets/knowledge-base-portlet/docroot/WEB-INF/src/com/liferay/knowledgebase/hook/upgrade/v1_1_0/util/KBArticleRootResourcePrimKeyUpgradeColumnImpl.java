/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.knowledgebase.hook.upgrade.v1_1_0.util;

import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.service.KBArticleLocalServiceUtil;
import com.liferay.portal.kernel.upgrade.util.BaseUpgradeColumnImpl;
import com.liferay.portal.kernel.upgrade.util.UpgradeColumn;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

/**
 * @author Peter Shin
 */
public class KBArticleRootResourcePrimKeyUpgradeColumnImpl
	extends BaseUpgradeColumnImpl {

	public KBArticleRootResourcePrimKeyUpgradeColumnImpl(
		UpgradeColumn resourcePrimKeyColumn) {

		super("rootResourcePrimKey");

		_resourcePrimKeyColumn = resourcePrimKeyColumn;
	}

	@Override
	public Object getNewValue(Object oldValue) throws Exception {
		Long resourcePrimKey = (Long)_resourcePrimKeyColumn.getOldValue();

		KBArticle kbArticle = KBArticleLocalServiceUtil.getLatestKBArticle(
			resourcePrimKey, WorkflowConstants.STATUS_ANY);

		while (!kbArticle.isRoot()) {
			kbArticle = KBArticleLocalServiceUtil.getLatestKBArticle(
				kbArticle.getParentResourcePrimKey(),
				WorkflowConstants.STATUS_ANY);
		}

		return new Long(kbArticle.getResourcePrimKey());
	}

	private UpgradeColumn _resourcePrimKeyColumn;

}