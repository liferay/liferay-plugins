/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.polls.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PollsVoteService}.
 * </p>
 *
 * @author    Juan Fernï¿½ndez
 * @see       PollsVoteService
 * @generated
 */
public class PollsVoteServiceWrapper implements PollsVoteService,
	ServiceWrapper<PollsVoteService> {
	public PollsVoteServiceWrapper(PollsVoteService pollsVoteService) {
		_pollsVoteService = pollsVoteService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _pollsVoteService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_pollsVoteService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _pollsVoteService.invokeMethod(name, parameterTypes, arguments);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public PollsVoteService getWrappedPollsVoteService() {
		return _pollsVoteService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedPollsVoteService(PollsVoteService pollsVoteService) {
		_pollsVoteService = pollsVoteService;
	}

	public PollsVoteService getWrappedService() {
		return _pollsVoteService;
	}

	public void setWrappedService(PollsVoteService pollsVoteService) {
		_pollsVoteService = pollsVoteService;
	}

	private PollsVoteService _pollsVoteService;
}