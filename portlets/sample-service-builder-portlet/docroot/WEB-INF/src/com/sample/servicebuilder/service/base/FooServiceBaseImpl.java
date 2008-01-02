/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

package com.sample.servicebuilder.service.base;

import com.liferay.portlet.service.PrincipalBean;

import com.sample.servicebuilder.service.FooLocalService;
import com.sample.servicebuilder.service.FooLocalServiceFactory;
import com.sample.servicebuilder.service.FooService;
import com.sample.servicebuilder.service.persistence.FooPersistence;
import com.sample.servicebuilder.service.persistence.FooUtil;

import org.springframework.beans.factory.InitializingBean;

/**
 * <a href="FooServiceBaseImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public abstract class FooServiceBaseImpl extends PrincipalBean
	implements FooService, InitializingBean {
	public FooLocalService getFooLocalService() {
		return fooLocalService;
	}

	public void setFooLocalService(FooLocalService fooLocalService) {
		this.fooLocalService = fooLocalService;
	}

	public FooPersistence getFooPersistence() {
		return fooPersistence;
	}

	public void setFooPersistence(FooPersistence fooPersistence) {
		this.fooPersistence = fooPersistence;
	}

	public void afterPropertiesSet() {
		if (fooLocalService == null) {
			fooLocalService = FooLocalServiceFactory.getImpl();
		}

		if (fooPersistence == null) {
			fooPersistence = FooUtil.getPersistence();
		}
	}

	protected FooLocalService fooLocalService;
	protected FooPersistence fooPersistence;
}