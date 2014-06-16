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

package com.liferay.knowledgebase.hook.listeners;

import com.liferay.knowledgebase.service.KBArticleLocalServiceUtil;
import com.liferay.knowledgebase.service.KBTemplateLocalServiceUtil;
import com.liferay.portal.ModelListenerException;
import com.liferay.portal.model.BaseModelListener;
import com.liferay.portal.model.Group;

/**
 * @author Brian Wing Shun Chan
 */
public class GroupModelListener extends BaseModelListener<Group> {

	@Override
	public void onBeforeRemove(Group group) throws ModelListenerException {
		try {
			doOnBeforeRemove(group);
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	protected void doOnBeforeRemove(Group group) throws Exception {
		KBArticleLocalServiceUtil.deleteGroupKBArticles(group.getGroupId());

		KBTemplateLocalServiceUtil.deleteGroupKBTemplates(group.getGroupId());
	}

}