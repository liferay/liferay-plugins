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

package com.liferay.wsrp.consumer.service;

/**
 * <a href="ProducerServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class ProducerServiceUtil {
	public static com.liferay.wsrp.consumer.model.Producer addProducer(
		long plid, java.lang.String name, java.lang.String wsdlURL)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		ProducerService producerService = ProducerServiceFactory.getService();

		return producerService.addProducer(plid, name, wsdlURL);
	}

	public static void deleteProducer(long producerId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		ProducerService producerService = ProducerServiceFactory.getService();

		producerService.deleteProducer(producerId);
	}

	public static com.liferay.wsrp.consumer.model.Producer getProducer(
		long producerId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		ProducerService producerService = ProducerServiceFactory.getService();

		return producerService.getProducer(producerId);
	}

	public static void updateProducer(long producerId, java.lang.String name,
		java.lang.String wsdlURL)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		ProducerService producerService = ProducerServiceFactory.getService();

		producerService.updateProducer(producerId, name, wsdlURL);
	}
}