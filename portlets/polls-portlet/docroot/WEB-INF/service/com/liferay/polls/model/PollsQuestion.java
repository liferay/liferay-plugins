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
 * The extended model interface for the PollsQuestion service. Represents a row in the &quot;Polls_PollsQuestion&quot; database table, with each column mapped to a property of this class.
 *
 * @author Juan Fern√°ndez
 * @see PollsQuestionModel
 * @see com.liferay.polls.model.impl.PollsQuestionImpl
 * @see com.liferay.polls.model.impl.PollsQuestionModelImpl
 * @generated
 */
public interface PollsQuestion extends PollsQuestionModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.polls.model.impl.PollsQuestionImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public java.util.List<com.liferay.polls.model.PollsChoice> getPollsChoices()
		throws com.liferay.portal.kernel.exception.SystemException;

	public int getPollsVotesCount()
		throws com.liferay.portal.kernel.exception.SystemException;

	public boolean isExpired();

	public boolean isExpired(
		com.liferay.portal.service.ServiceContext serviceContext,
		java.util.Date defaultCreateDate);
}