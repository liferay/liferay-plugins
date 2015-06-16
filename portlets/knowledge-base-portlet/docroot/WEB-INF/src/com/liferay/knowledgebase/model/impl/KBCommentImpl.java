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

package com.liferay.knowledgebase.model.impl;

import com.liferay.knowledgebase.model.KBComment;
import com.liferay.portlet.exportimport.lar.StagedModelType;

/**
 * @author Peter Shin
 * @author Daniel Kocsis
 */
public class KBCommentImpl extends KBCommentBaseImpl {

	public KBCommentImpl() {
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(KBComment.class);
	}

}