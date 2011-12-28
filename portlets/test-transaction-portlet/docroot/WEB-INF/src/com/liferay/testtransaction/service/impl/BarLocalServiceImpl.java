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

package com.liferay.testtransaction.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.PortalServiceUtil;
import com.liferay.testtransaction.NoSuchBarException;
import com.liferay.testtransaction.model.Bar;
import com.liferay.testtransaction.service.base.BarLocalServiceBaseImpl;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class BarLocalServiceImpl extends BarLocalServiceBaseImpl {

	public void addBar_Rollback(String text) throws SystemException {
		addBar(text);

		throw new SystemException();
	}

	public Bar addBar_Success(String text) throws SystemException {
		return addBar(text);
	}

	public void addBarAndClassName_PortalRollback(String text)
		throws SystemException {

		addBar(text);

		PortalServiceUtil.testAddClassName_Rollback(
			BarLocalServiceImpl.class.getName());
	}

	public void addBarAndClassName_PortletRollback(String text)
		throws SystemException {

		addBar(text);

		PortalServiceUtil.testAddClassName_Success(
			BarLocalServiceImpl.class.getName());

		throw new SystemException();
	}

	@Override
	public void deleteBar(Bar bar) throws SystemException {
		barPersistence.remove(bar);
	}

	public void deleteBarAndClassName(Bar bar)
		throws PortalException, SystemException {

		barPersistence.remove(bar);

		classNamePersistence.removeByValue(BarLocalServiceImpl.class.getName());
	}

	public Bar getBar(String text) throws PortalException, SystemException {
		List<Bar> bars = barPersistence.findByText(text);

		if (bars.isEmpty()) {
			throw new NoSuchBarException();
		}

		return bars.get(0);
	}

	public boolean hasBar(String text) throws SystemException {
		int count = barPersistence.countByText(text);

		if (count > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean hasClassName() throws SystemException {
		int count = classNamePersistence.countByValue(
			BarLocalServiceImpl.class.getName());

		if (count > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public void testAddClassNameAndBar_Success(String text)
		throws SystemException {

		addBar(text);

		PortalServiceUtil.testAddClassName_Success(
			BarLocalServiceImpl.class.getName());
	}

	protected Bar addBar(String text) throws SystemException {
		long barId = counterLocalService.increment();

		Bar bar = barPersistence.create(barId);

		bar.setText(text);

		barPersistence.update(bar, false);

		return bar;
	}

}