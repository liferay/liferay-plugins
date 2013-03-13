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

package com.liferay.polls.model;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the PollsVote service. Represents a row in the &quot;Polls_PollsVote&quot; database table, with each column mapped to a property of this class.
 *
 * @author Juan Fern√°ndez
 * @see PollsVoteModel
 * @see com.liferay.polls.model.impl.PollsVoteImpl
 * @see com.liferay.polls.model.impl.PollsVoteModelImpl
 * @generated
 */
public interface PollsVote extends PollsVoteModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.polls.model.impl.PollsVoteImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public com.liferay.polls.model.PollsChoice getPollsChoice()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;
}