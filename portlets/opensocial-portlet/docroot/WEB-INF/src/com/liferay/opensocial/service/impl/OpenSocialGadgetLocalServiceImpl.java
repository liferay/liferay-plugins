/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.opensocial.service.impl;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.opensocial.OpenSocialGadgetNameException;
import com.liferay.opensocial.model.OpenSocialGadget;
import com.liferay.opensocial.service.base.OpenSocialGadgetLocalServiceBaseImpl;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;

/**
 * <a href="OpenSocialGadgetLocalServiceImpl.java.html"><b><i>View Source</i>
 * </b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class OpenSocialGadgetLocalServiceImpl
	extends OpenSocialGadgetLocalServiceBaseImpl {

	public OpenSocialGadget addOpenSocialGadget(
			long companyId, String name, String url, String xml)
		throws PortalException, SystemException {

		Date now = new Date();

		validate(name);

		long openSocialGadgetId = CounterLocalServiceUtil.increment();

		OpenSocialGadget openSocialGadget = openSocialGadgetPersistence.create(
			openSocialGadgetId);

		openSocialGadget.setCompanyId(companyId);
		openSocialGadget.setCreateDate(now);
		openSocialGadget.setModifiedDate(now);
		openSocialGadget.setName(name);
		openSocialGadget.setUrl(url);
		openSocialGadget.setXml(xml);

		openSocialGadgetPersistence.update(openSocialGadget, false);

		return openSocialGadget;
	}

	public void deleteWSRPConsumer(long openSocialGadgetId)
		throws PortalException, SystemException {

		OpenSocialGadget openSocialGadget =
			openSocialGadgetPersistence.findByPrimaryKey(openSocialGadgetId);

		openSocialGadgetPersistence.remove(openSocialGadget);
	}

	public List<OpenSocialGadget> getOpenSocialGadgets(
			long companyId, int start, int end)
		throws PortalException, SystemException {

		return openSocialGadgetPersistence.findByCompanyId(
			companyId, start, end);
	}

	public int getOpenSocialGadgetsCount(long companyId)
		throws PortalException, SystemException {

		return openSocialGadgetPersistence.countByCompanyId(companyId);
	}

	public OpenSocialGadget updateOpenSocialGadget(
			long openSocialGadgetId, String name, String xml)
		throws PortalException, SystemException {

		Date now = new Date();

		validate(name);

		OpenSocialGadget openSocialGadget = openSocialGadgetPersistence.create(
			openSocialGadgetId);

		openSocialGadget.setModifiedDate(now);
		openSocialGadget.setName(name);
		openSocialGadget.setXml(xml);

		openSocialGadgetPersistence.update(openSocialGadget, false);

		return openSocialGadget;
	}

	protected void validate(String name) throws PortalException {
		if (Validator.isNull(name)) {
			throw new OpenSocialGadgetNameException();
		}
	}

}