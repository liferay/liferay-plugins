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

/**
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at http://www.sun.com/cddl/cddl.html and
 * legal/CDDLv1.0.txt. See the License for the specific language governing
 * permission and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL Header Notice in each file
 * and include the License file at legal/CDDLv1.0.txt.
 *
 * If applicable, add the following below the CDDL Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Copyright 2009 Sun Microsystems Inc. All rights reserved.
 */

package com.liferay.wsrp.consumer.admin;

import com.liferay.counter.service.CounterServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.PortletInfo;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.wsrp.model.WSRPPortlet;
import com.liferay.wsrp.service.WSRPPortletLocalServiceUtil;

import com.sun.portal.wsrp.consumer.common.WSRPConsumerException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * <a href="WSRPPersistenceHelper.java.html"><b><i>View Source</i></b></a>
 *
 * @author Rajesh Thiagarajan
 *
 */
public class WSRPPersistenceHelper {

	public static synchronized WSRPPersistenceHelper getInstance() {
		return _instance;
	}

	public void addWSRPPortlet(Portlet portlet) throws WSRPConsumerException {
		try {
			WSRPPortlet wsrpPortlet =
				WSRPPortletLocalServiceUtil.createWSRPPortlet(
					CounterServiceUtil.increment());

			PortletInfo portletInfo = portlet.getPortletInfo();

			wsrpPortlet.setChannelName(portlet.getPortletId());
			wsrpPortlet.setConsumerId(portlet.getRemoteConsumerId());
			wsrpPortlet.setDisplayName(portlet.getDisplayName());
			wsrpPortlet.setKeywords(portletInfo.getKeywords());
			wsrpPortlet.setPortletHandle(portlet.getRemotePortletHandle());
			wsrpPortlet.setName(portlet.getPortletId());
			wsrpPortlet.setProducerEntityId(
				portlet.getRemoteProducerEntityId());
			wsrpPortlet.setShortTitle(portletInfo.getShortTitle());
			wsrpPortlet.setStatus(portlet.isActive() ? 1 : 0);
			wsrpPortlet.setTitle(portletInfo.getTitle());

			Map<String, Set<String>> portletModes = portlet.getPortletModes();

			Set<String> mimeTypes = portletModes.keySet();

			MimeTypes mimeHolder = new MimeTypes();

			for (String mimeType : mimeTypes) {
				Set<String> mimeTypeModes = portletModes.get(mimeType);

				MimeType mimeTypeModel = new MimeType();

				mimeTypeModel.setMime(mimeType);
				mimeTypeModel.getModes().addAll(mimeTypeModes);

				mimeHolder.getMimeType().add(mimeTypeModel);
			}

			wsrpPortlet.setMimeTypes(_marshalMimeTypes(mimeHolder));

			WSRPPortletLocalServiceUtil.updateWSRPPortlet(wsrpPortlet);
		}
		catch (Exception e) {
			throw new WSRPConsumerException(e.getMessage(), e);
		}
	}

	public void deleteWSRPPortlet(Portlet portlet)
		throws WSRPConsumerException {

		try {
			WSRPPortlet wsrpPortlet = WSRPPortletLocalServiceUtil.getPortlet(
				portlet.getPortletId());

			WSRPPortletLocalServiceUtil.deleteWSRPPortlet(wsrpPortlet);
		}
		catch (Exception e) {
			throw new WSRPConsumerException(e.getMessage(), e);
		}
	}

	public List<Portlet> getWSRPPortlets() throws WSRPConsumerException {
		try {
			List<WSRPPortlet> wsrpPortlets =
				WSRPPortletLocalServiceUtil.getPortlets();

			if ((wsrpPortlets == null) || (wsrpPortlets.size() == 0)) {
				return Collections.EMPTY_LIST;
			}

			List<Portlet> portlets = new ArrayList<Portlet>();

			for (WSRPPortlet wsrpPortlet : wsrpPortlets) {
				String portletId = PortalUtil.getJsSafePortletId(
					wsrpPortlet.getName());

				Portlet portlet = PortletLocalServiceUtil.newPortlet(
					CompanyConstants.SYSTEM, portletId);

				portlet.setPortletId(portletId);
				portlet.setTimestamp(System.currentTimeMillis());
				portlet.setPortletName(portletId);
				portlet.setActive(true);
				portlet.setRemote(true);
				portlet.setRemoteConsumerId(wsrpPortlet.getConsumerId());
				portlet.setRemoteProducerEntityId(
					wsrpPortlet.getProducerEntityId());

				portlet.setRemotePortletHandle(wsrpPortlet.getPortletHandle());
				portlet.setRemotePortletId(wsrpPortlet.getPortletHandle());

				MimeTypes mimeTypes = _unmarshallMimeTypes(
					wsrpPortlet.getMimeTypes());

				List<MimeType> mimes = mimeTypes.getMimeType();

				Map<String, Set<String>> portletModes =
					new HashMap<String, Set<String>>();

				for (MimeType mimeType : mimes) {
					Set<String> modes = new HashSet<String>(
						mimeType.getModes());

					portletModes.put(mimeType.getMime(), modes);
				}

				portlet.setPortletModes(portletModes);

				PortletInfo portletInfo = new PortletInfo(
					wsrpPortlet.getTitle(), wsrpPortlet.getShortTitle(),
					wsrpPortlet.getKeywords());

				portlet.setPortletInfo(portletInfo);

				portlets.add(portlet);
			}

			return portlets;
		}
		catch (Exception e) {
			throw new WSRPConsumerException(e.getMessage(), e);
		}
	}

	public void updateWSRPPortlet(Portlet portlet)
		throws WSRPConsumerException {

		try {
			WSRPPortlet wsrpPortlet = WSRPPortletLocalServiceUtil.getPortlet(
				portlet.getPortletId());

			PortletInfo portletInfo = portlet.getPortletInfo();

			wsrpPortlet.setChannelName(portlet.getPortletId());
			wsrpPortlet.setConsumerId(portlet.getRemoteConsumerId());
			wsrpPortlet.setDisplayName(portlet.getDisplayName());
			wsrpPortlet.setKeywords(portletInfo.getKeywords());
			wsrpPortlet.setPortletHandle(portlet.getRemotePortletHandle());
			wsrpPortlet.setName(portlet.getPortletId());
			wsrpPortlet.setProducerEntityId(
				portlet.getRemoteProducerEntityId());
			wsrpPortlet.setShortTitle(portletInfo.getShortTitle());
			wsrpPortlet.setStatus(portlet.isActive() ? 1 : 0);
			wsrpPortlet.setTitle(portletInfo.getTitle());

			Map<String, Set<String>> portletModes = portlet.getPortletModes();

			Set<String> mimeTypes = portletModes.keySet();

			MimeTypes mimeHolder = new MimeTypes();

			for (String mimeType : mimeTypes) {
				Set<String> mimeTypeModes = portletModes.get(mimeType);

				MimeType mimeTypeModel = new MimeType();

				mimeTypeModel.setMime(mimeType);
				mimeTypeModel.getModes().addAll(mimeTypeModes);

				mimeHolder.getMimeType().add(mimeTypeModel);
			}

			wsrpPortlet.setMimeTypes(_marshalMimeTypes(mimeHolder));

			WSRPPortletLocalServiceUtil.updateWSRPPortlet(wsrpPortlet);
		}
		catch (Exception e) {
			throw new WSRPConsumerException(e.getMessage(), e);
		}
	}

	private WSRPPersistenceHelper() {
		try {
			ClassLoader classLoader = getClass().getClassLoader();

			_jaxbContext = JAXBContext.newInstance(
				"com.liferay.wsrp.consumer.admin", classLoader);

			_marshaller = _jaxbContext.createMarshaller();

			_marshaller.setProperty(
				Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			_unmarshaller = _jaxbContext.createUnmarshaller();

			_objectFactory = new ObjectFactory();
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private String _marshalMimeTypes(MimeTypes mimeTypes)
		throws WSRPConsumerException {

		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			JAXBElement<MimeTypes> element = _objectFactory.createMimeTypes(
				mimeTypes);

			_marshaller.marshal(element, baos);

			return new String(baos.toByteArray());
		}
		catch (Exception e) {
			throw new WSRPConsumerException(e.getMessage(), e);
		}
	}

	private MimeTypes _unmarshallMimeTypes(String mimeString)
		throws WSRPConsumerException {

		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(
				mimeString.getBytes());

			JAXBElement<MimeTypes> element =
				(JAXBElement<MimeTypes>)_unmarshaller.unmarshal(bais);

			return element.getValue();
		}
		catch (Exception e) {
			throw new WSRPConsumerException(e.getMessage(), e);
		}
	}

	private static Log _log =
		 LogFactoryUtil.getLog(WSRPPersistenceHelper.class);

	private static WSRPPersistenceHelper _instance =
		new WSRPPersistenceHelper();

	private JAXBContext _jaxbContext;
	private Marshaller _marshaller;
	private Unmarshaller _unmarshaller;
	private ObjectFactory _objectFactory;

}