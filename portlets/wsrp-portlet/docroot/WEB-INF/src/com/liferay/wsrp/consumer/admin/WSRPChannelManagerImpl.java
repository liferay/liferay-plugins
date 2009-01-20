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

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.PortletInfo;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletInstanceFactoryUtil;

import com.sun.portal.wsrp.common.WSRPConfig;
import com.sun.portal.wsrp.common.WSRPMBeanException;
import com.sun.portal.wsrp.common.stubs.v2.LocalizedString;
import com.sun.portal.wsrp.common.stubs.v2.MarkupType;
import com.sun.portal.wsrp.common.stubs.v2.PortletDescription;
import com.sun.portal.wsrp.consumer.admin.mbeans.WSRPChannelManagerMBean;
import com.sun.portal.wsrp.consumer.common.WSRPConsumerException;
import com.sun.portal.wsrp.consumer.producermanager.ProducerEntity;
import com.sun.portal.wsrp.consumer.producermanager.ProducerEntityManager;
import com.sun.portal.wsrp.consumer.producermanager.ProducerEntityManagerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <a href="WSRPChannelManagerImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Rajesh Thiagarajan
 * @author Manish Gupta
 *
 */
public class WSRPChannelManagerImpl implements WSRPChannelManagerMBean {

	public void createWSRPChannel(
			String channelName, String consumerId, String producerEntityId,
			String portletHandle)
		throws WSRPMBeanException {

		try {
			String portletId = PortalUtil.getJsSafePortletId(channelName);

			validatePortletId(portletId);

			Portlet portlet = PortletLocalServiceUtil.newPortlet(
				CompanyConstants.SYSTEM, portletId);

			portlet.setPortletId(portletId);
			portlet.setTimestamp(System.currentTimeMillis());
			portlet.setPortletName(portletId);
			portlet.setActive(true);
			portlet.setRemote(true);
			portlet.setRemoteConsumerId(consumerId);
			portlet.setRemoteProducerEntityId(producerEntityId);
			portlet.setRemotePortletHandle(portletHandle);
			portlet.setRemotePortletId(channelName);

			addPortletInfo(portlet, producerEntityId, portletHandle);

			persistRemotePortlet(portlet);

			PortletLocalServiceUtil.deployRemotePortlet(portlet);

		}
		catch (WSRPConsumerException wsrpce) {
			throw new WSRPMBeanException(wsrpce.getMessage());
		}
	}

	public List<String> listWSRPChannels() {
		List<String> remotePortlets = new ArrayList<String>();

		List<Portlet> portlets = PortletLocalServiceUtil.getPortlets();

		for (Portlet portlet : portlets) {
			if (portlet.isRemote()) {
				remotePortlets.add(portlet.getPortletId());
			}
		}

		return remotePortlets;
	}

	public void removeWSRPChannel(String channelName) {
		try {
			Portlet portlet = PortletLocalServiceUtil.getPortletById(
				CompanyConstants.SYSTEM, channelName);

			if (!portlet.isRemote()) {
				return;
			}

			PortletInstanceFactoryUtil.destroy(portlet);

			WSRPPersistenceHelper persistenceHelper =
				WSRPPersistenceHelper.getInstance();

			persistenceHelper.deleteWSRPPortlet(portlet);
		}
		catch (SystemException se) {
			_log.error(se, se);
		}
		catch (WSRPConsumerException wsrpce) {
			_log.error(wsrpce, wsrpce);
		}
	}

	public void removeWSRPChannels(List<String> channelNames) {
		for (String channelName : channelNames) {
			removeWSRPChannel(channelName);
		}
	}

	protected void addPortletInfo(
			Portlet portlet, String producerEntityId, String portletHandle)
		throws WSRPConsumerException {

		ProducerEntityManagerFactory producerEntityManagerFactory =
			ProducerEntityManagerFactory.getInstance();

		String portalId = WSRPConfig.getPortalId();

		ProducerEntityManager producerEntityManager =
			producerEntityManagerFactory.getProducerEntityManager(
				portalId, null);

		ProducerEntity producerEntity = producerEntityManager.getProducerEntity(
			producerEntityId);

		PortletDescription portletDescription =
			producerEntity.getPortletDescription(portletHandle);

		Map<String, Set<String>> portletModes =
			new HashMap<String, Set<String>>();

		List<MarkupType> markupTypes = portletDescription.getMarkupTypes();

		for (MarkupType markupType : markupTypes) {
			Set<String> mimeModes = getPortalModes(markupType.getModes());

			portletModes.put(markupType.getMimeType(), mimeModes);
		}

		portlet.setPortletModes(portletModes);

		LocalizedString localizedDisplayName =
			portletDescription.getDisplayName();

		if (localizedDisplayName != null) {
			String displayName = localizedDisplayName.getValue();

			if (displayName == null) {
				portlet.setDisplayName(portletHandle);
			}
			else {
				portlet.setDisplayName(displayName);
			}
		}

		String title = null;

		LocalizedString localizedTitle = portletDescription.getTitle();

		if (localizedTitle != null) {
			title = localizedTitle.getValue();
		}
		else {
			title = portletHandle;
		}

		String shortTitle = null;

		LocalizedString localizedShortTitle =
			portletDescription.getShortTitle();

		if (localizedShortTitle != null) {
			shortTitle = localizedShortTitle.getValue();
		}
		else {
			shortTitle = portletHandle;
		}

		String keyword = null;

		List<LocalizedString> keywords = portletDescription.getKeywords();

		if ((keywords != null) && (keywords.size() >= 1)) {
			LocalizedString element = keywords.get(0);

			if (element != null) {
				keyword = element.getValue();
			}
		}

		if (keyword == null) {
			keyword = _DEFAULT_KEYWORD;
		}

		PortletInfo portletInfo = new PortletInfo(title, shortTitle, keyword);

		portlet.setPortletInfo(portletInfo);
	}

	protected Set<String> getPortalModes(List<String> modes) {
		Set<String> portalModes = new HashSet<String>(modes.size());

		portalModes.add(_MODE_VIEW);

		for (String mode : modes) {
			if (mode.equals(_WSRP_EDIT)) {
				portalModes.add(_MODE_EDIT);
			}
			else if (mode.equals(_WSRP_HELP)) {
				portalModes.add(_MODE_HELP);
			}
			else if (mode.equals(_WSRP_VIEW)) {
				portalModes.add(_MODE_VIEW);
			}
		}

		return portalModes;
	}

	protected void persistRemotePortlet(Portlet remotePortlet)
		throws WSRPConsumerException {

		WSRPPersistenceHelper persistenceHelper =
			WSRPPersistenceHelper.getInstance();

		persistenceHelper.addWSRPPortlet(remotePortlet);
	}

	protected void validatePortletId(String portletId)
		throws WSRPConsumerException {

		try {
			Portlet portlet = PortletLocalServiceUtil.getPortletById(
				CompanyConstants.SYSTEM, portletId);

			if (portlet.isRemote()) {
				throw new WSRPConsumerException("DUPLICATE_PORTLET_NAME");
			}
			else if (portlet.isUndeployedPortlet()) {
				PortletInstanceFactoryUtil.clear(portlet);
			}
		}
		catch (SystemException se) {
			throw new WSRPConsumerException("SYSTEM_FAILURE");
		}
	}

	private static final String _DEFAULT_KEYWORD = "WSRP";

	private static final String _MODE_EDIT = "edit";

	private static final String _MODE_HELP = "help";

	private static final String _MODE_VIEW = "view";

	private static final String _WSRP_EDIT = "wsrp:edit";

	private static final String _WSRP_HELP = "wsrp:help";

	private static final String _WSRP_VIEW = "wsrp:view";

	private static Log _log =
		 LogFactoryUtil.getLog(WSRPChannelManagerImpl.class);

}