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

package com.liferay.wsrp.consumer.service.impl;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.permission.PortletPermissionUtil;
import com.liferay.wsrp.consumer.model.Producer;
import com.liferay.wsrp.consumer.permission.ProducerPermission;
import com.liferay.wsrp.consumer.service.base.ProducerServiceBaseImpl;

/**
 * <a href="ProducerServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael Young
 *
 */
public class ProducerServiceImpl extends ProducerServiceBaseImpl {
	public Producer addProducer(long plid, String name, String wsdlURL)
		throws PortalException, SystemException {

		PermissionChecker permissionChecker = getPermissionChecker();

		PortletPermissionUtil.check(
			permissionChecker, plid, "1",
			"ADD_PRODUCER");
		
		return producerLocalService.addProducer(
			getUserId(), plid, name, wsdlURL);
	}
	
	public void deleteProducer(long producerId) 
		throws PortalException, SystemException {

		PermissionChecker permissionChecker = getPermissionChecker();

		ProducerPermission.check(permissionChecker, producerId,
				ActionKeys.DELETE);

		producerLocalService.deleteProducer(producerId);
	}
	
	public Producer getProducer(long producerId)
		throws PortalException, SystemException {

		PermissionChecker permissionChecker = getPermissionChecker();

		ProducerPermission.check(
			permissionChecker, producerId, ActionKeys.VIEW);
		
		return producerLocalService.getProducer(producerId);
	}

	public void updateProducer(long producerId, String name, String wsdlURL) 
		throws PortalException, SystemException {

		PermissionChecker permissionChecker = getPermissionChecker();
	
		ProducerPermission.check(permissionChecker, producerId,
				ActionKeys.UPDATE);
	
		producerLocalService.updateProducer(producerId, name, wsdlURL);
	}
	
}