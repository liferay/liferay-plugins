/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.polls.model.impl;

import com.liferay.polls.model.PollsChoice;
import com.liferay.polls.service.PollsChoiceLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 */
public class PollsVoteImpl extends PollsVoteBaseImpl {

	public PollsVoteImpl() {
	}

	public PollsChoice getPollsChoice()
		throws PortalException, SystemException {

		return PollsChoiceLocalServiceUtil.getPollsChoice(getPollsChoiceId());
	}

}