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
import com.liferay.testtransaction.TestUtil;
import com.liferay.testtransaction.model.Bar;
import com.liferay.testtransaction.service.base.BarLocalServiceBaseImpl;

/**
 * The implementation of the bar local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.testtransaction.service.BarLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.testtransaction.service.base.BarLocalServiceBaseImpl
 * @see com.liferay.testtransaction.service.BarLocalServiceUtil
 */
public class BarLocalServiceImpl extends BarLocalServiceBaseImpl {
	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.liferay.testtransaction.service.BarLocalServiceUtil} to access the bar local service.
	 */
	public void addBarPortalRollback(String text) throws SystemException {
		long barId = counterLocalService.increment();

		Bar bar = barPersistence.create(barId);

		bar.setText(text);

		barPersistence.update(bar, false);

		PortalServiceUtil.testClassNameRollback(TestUtil.class.getName());
	}

	public void addBarPortletRollback(String text) throws SystemException {
		long barId = counterLocalService.increment();

		Bar bar = barPersistence.create(barId);

		bar.setText(text);

		barPersistence.update(bar, false);

		PortalServiceUtil.testClassName(TestUtil.class.getName());

		throw new SystemException();
	}

	public Bar addBarSuccess(String text) throws SystemException {
		long barId = counterLocalService.increment();

		Bar bar = barPersistence.create(barId);

		bar.setText(text);

		barPersistence.update(bar, false);

		PortalServiceUtil.testClassName(TestUtil.class.getName());

		return bar;
	}

	public void cleanUp(Bar bar) throws PortalException, SystemException {
		barPersistence.remove(bar);
		ClassName className = classNamePersistence.findByValue(
			TestUtil.class.getName());
		classNamePersistence.remove(className);
	}

	public boolean hasBar(String text) throws SystemException {
		return !barPersistence.findByText(text).isEmpty();
	}

	public boolean hasClassName() throws SystemException {
		return classNamePersistence.countByValue(TestUtil.class.getName()) > 0;
	}

}