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

package com.liferay.portal.workflow.kaleo.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link KaleoDefinitionService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoDefinitionService
 * @generated
 */
public class KaleoDefinitionServiceWrapper implements KaleoDefinitionService,
	ServiceWrapper<KaleoDefinitionService> {
	public KaleoDefinitionServiceWrapper(
		KaleoDefinitionService kaleoDefinitionService) {
		_kaleoDefinitionService = kaleoDefinitionService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _kaleoDefinitionService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_kaleoDefinitionService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _kaleoDefinitionService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoDefinition> getKaleoDefinitions(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDefinitionService.getKaleoDefinitions(start, end);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoDefinition> getKaleoDefinitions(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDefinitionService.getKaleoDefinitions(companyId, start, end);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public KaleoDefinitionService getWrappedKaleoDefinitionService() {
		return _kaleoDefinitionService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedKaleoDefinitionService(
		KaleoDefinitionService kaleoDefinitionService) {
		_kaleoDefinitionService = kaleoDefinitionService;
	}

	public KaleoDefinitionService getWrappedService() {
		return _kaleoDefinitionService;
	}

	public void setWrappedService(KaleoDefinitionService kaleoDefinitionService) {
		_kaleoDefinitionService = kaleoDefinitionService;
	}

	private KaleoDefinitionService _kaleoDefinitionService;
}