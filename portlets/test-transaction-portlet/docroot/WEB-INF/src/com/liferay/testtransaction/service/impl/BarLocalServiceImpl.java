/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.model.ClassName;
import com.liferay.portal.service.PortalServiceUtil;
import com.liferay.testtransaction.model.Bar;
import com.liferay.testtransaction.service.base.BarLocalServiceBaseImpl;

/**
 * @author Brian Wing Shun Chan
 */
public class BarLocalServiceImpl extends BarLocalServiceBaseImpl {

	public Bar addBar(String text) throws SystemException {
		long barId = counterLocalService.increment();

		Bar bar = barPersistence.create(barId);

		bar.setText(text);

		barPersistence.update(bar, false);

		PortalServiceUtil.testClassName(BarLocalServiceImpl.class.getName());

		return bar;
	}

	public void addBarPortalRollback(String text) throws SystemException {
		long barId = counterLocalService.increment();

		Bar bar = barPersistence.create(barId);

		bar.setText(text);

		barPersistence.update(bar, false);

		PortalServiceUtil.testClassNameRollback(
			BarLocalServiceImpl.class.getName());
	}

	public void addBarPortletRollback(String text) throws SystemException {
		long barId = counterLocalService.increment();

		Bar bar = barPersistence.create(barId);

		bar.setText(text);

		barPersistence.update(bar, false);

		PortalServiceUtil.testClassName(BarLocalServiceImpl.class.getName());

		throw new SystemException();
	}

	public void cleanUp(Bar bar) throws PortalException, SystemException {
		barPersistence.remove(bar);

		ClassName className = classNamePersistence.findByValue(
			BarLocalServiceImpl.class.getName());

		classNamePersistence.remove(className);
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

}