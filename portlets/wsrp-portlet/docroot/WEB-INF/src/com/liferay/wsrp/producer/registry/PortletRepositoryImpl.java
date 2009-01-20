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

package com.liferay.wsrp.producer.registry;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.PortletApp;
import com.liferay.portal.portletcontainer.WindowInvokerUtil;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.wsrp.producer.impl.ProfileMapManagerImpl;

import com.sun.portal.container.EntityID;
import com.sun.portal.container.service.EventHolder;
import com.sun.portal.container.service.PortletDescriptorHolder;
import com.sun.portal.container.service.PortletDescriptorHolderFactory;
import com.sun.portal.container.service.PublicRenderParameterHolder;
import com.sun.portal.wsrp.common.WSRPUtility;
import com.sun.portal.wsrp.common.stubs.v2.ItemDescription;
import com.sun.portal.wsrp.common.stubs.v2.LocalizedString;
import com.sun.portal.wsrp.common.stubs.v2.MarkupType;
import com.sun.portal.wsrp.common.stubs.v2.ParameterDescription;
import com.sun.portal.wsrp.common.stubs.v2.PropertyList;
import com.sun.portal.wsrp.common.stubs.v2.UserContext;
import com.sun.portal.wsrp.producer.Producer;
import com.sun.portal.wsrp.producer.ProducerException;
import com.sun.portal.wsrp.producer.ProfileMapManager;
import com.sun.portal.wsrp.producer.driver.PortletRegistry;
import com.sun.portal.wsrp.producer.driver.ResourceName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.portlet.PortletPreferences;

import javax.xml.namespace.QName;

/**
 * <a href="PortletRepositoryImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Manish Gupta
 * @author Nithya Subramanian
 * @author Brian Wing Shun Chan
 *
 */
public class PortletRepositoryImpl implements PortletRegistry, ResourceName {

	public PortletRepositoryImpl(Producer producer) throws ProducerException {
		if (_portlets == null) {
			_portlets = PortletLocalServiceUtil.getPortlets();
		}

		try {
			_portletDescriptionHolder =
				PortletDescriptorHolderFactory.getPortletDescriptorHolder();
		}
		catch (Exception e) {
			throw new ProducerException(e);
		}
	}

	public PortletRepositoryImpl(
			Producer producer, String portalID, String namespace)
		throws ProducerException {

		this(producer);
	}

	public void cloneChannel(
		UserContext userContext, String newName, String existingChannel,
		String registrationHandle) {
	}

	public Set<String> getAvailablePortlets() {
		if (_portlets == null) {
			_portlets = PortletLocalServiceUtil.getPortlets();
		}

		Set<String> portletIds = new HashSet<String>();

		for (Portlet portlet : _portlets) {
			String portletId = portlet.getPortletId();

			if (portlet.getPortletApp().isWARFile()) {
				portletIds.add(portletId);
			}
		}

		return portletIds;
	}

	public List<ItemDescription> getCustomModeDescriptions() {
		return Collections.EMPTY_LIST;
	}

	public List<ItemDescription> getCustomWindowStateDescriptions() {
		return Collections.EMPTY_LIST;
	}

	public Locale getDefaultLocale() {
		return Locale.getDefault();
	}

	public Boolean getDefaultSecureMarkup(String portletName) {
		return Boolean.FALSE;
	}

	public List<LocalizedString> getDescription(
		String portletName, Set<String> locales) {

		return Collections.EMPTY_LIST;
	}

	public List<LocalizedString> getDisplayName(
			String portletName, Set<String> locales)
		throws ProducerException {

		List<LocalizedString> displayNames = new ArrayList<LocalizedString>();

		String displayName = null;

		try {
			Portlet portlet = _getPortlet(portletName);

			displayName = portlet.getDisplayName();
		}
		catch (SystemException se) {
			throw new ProducerException(se);
		}

		if (displayName != null) {
			String resourceNamePrefix =
				portletName + SEPARATOR + DISPLAY_NAME + SEPARATOR;

			LocalizedString localizedString = new LocalizedString();

			localizedString.setLang(
				WSRPUtility.toXMLLang(getDefaultLocale().toString()));
			localizedString.setResourceName(resourceNamePrefix + displayName);
			localizedString.setValue(displayName);

			displayNames.add(localizedString);
		}

		return displayNames;
	}

	public List<EventHolder> getHandledEvents(String portletName, String locale)
		throws ProducerException {

		EntityID entityID = getPortletEntityID(portletName);

		List<EventHolder> eventHolders =
			_portletDescriptionHolder.getSupportedProcessingEventHolders(
				entityID);

		return eventHolders;
	}

	public List<LocalizedString> getKeywords(
			String portletName, Set<String> locales)
		throws ProducerException {

		String keywords = null;

		try {
			Portlet portlet = _getPortlet(portletName);

			keywords = portlet.getPortletInfo().getKeywords();
		}
		catch (Exception e) {
			throw new ProducerException(e);
		}

		if (keywords == null) {
			return Collections.EMPTY_LIST;
		}

		List<LocalizedString> result = new ArrayList<LocalizedString>();

		String resourceName =
			portletName + SEPARATOR + KEYWORDS + SEPARATOR +  keywords;

		LocalizedString localizedString = new LocalizedString();

		localizedString.setLang(
			WSRPUtility.toXMLLang(getDefaultLocale().toString()));
		localizedString.setResourceName(resourceName);
		localizedString.setValue(keywords);

		result.add(localizedString);

		return result;
	}

	public List<MarkupType> getMarkupTypesSet(String portletName)
		throws ProducerException {

		Map<String, Set<String>> portletModesMap = null;

		try {
			Portlet portlet = _getPortlet(portletName);

			portletModesMap = portlet.getPortletModes();
		}
		catch (Exception e) {
			throw new ProducerException(e);
		}

		List<MarkupType> markupTypes = new ArrayList<MarkupType>();

		for (String mimeType : portletModesMap.keySet()) {
			MarkupType markupType = new MarkupType();

			markupType.getWindowStates().addAll(_WSRP_STATES);

			Set<String> modes = portletModesMap.get(mimeType);

			for (String mode : modes) {
				if (mode.equalsIgnoreCase("edit")) {
					markupType.getModes().add("wsrp:edit");
				}
				else if (mode.equalsIgnoreCase("help")) {
					markupType.getModes().add("wsrp:help");
				}
				else if (mode.equalsIgnoreCase("view")) {
					markupType.getModes().add("wsrp:view");
				}
			}

			markupType.setMimeType(mimeType);

			markupTypes.add(markupType);
		}

		return markupTypes;
	}

	public List<ParameterDescription> getNavigationalPublicParameters(
		String portletName, String locale) throws ProducerException {

		List<ParameterDescription> parameterDescriptions =
			new ArrayList<ParameterDescription>();

		EntityID entityID = getPortletEntityID(portletName);

		List<PublicRenderParameterHolder> publicRenderParameterHolders =
			_portletDescriptionHolder.getSupportedPublicRenderParameterHolders(
				entityID, null);

		for (PublicRenderParameterHolder publicRenderParameterHolder :
				publicRenderParameterHolders) {

			ParameterDescription parameterDescription =
				new ParameterDescription();

			parameterDescription.setIdentifier(
				publicRenderParameterHolder.getIdentifier());

			List<QName> names = parameterDescription.getNames();

			names.add(publicRenderParameterHolder.getQName());
			names.addAll(publicRenderParameterHolder.getAliases());

			parameterDescriptions.add(parameterDescription);
		}

		return parameterDescriptions;
	}

	public EntityID getPortletEntityID(String portletName)
		throws ProducerException {

		try {
			Portlet portlet = _getPortlet(portletName);

			return WindowInvokerUtil.getEntityID(portlet);
		}
		catch (Exception e) {
			throw new ProducerException(e);
		}
	}

	public PortletPreferences getPortletPreferences(String portletName) {
		return null;
	}

	public Map<String, ResourceBundle> getPortletResourceMap(
		String portletName) {

		return null;
	}

	public List<EventHolder> getPublishedEvents(
			String portletName, String locale)
		throws ProducerException {

		EntityID entityID = getPortletEntityID(portletName);

		List<EventHolder> publishedEvents =
			_portletDescriptionHolder.getSupportedPublishingEventHolders(
				entityID);

		return publishedEvents;
	}

	public List<LocalizedString> getShortTitle(
			String portletName, Set<String> locales)
		throws ProducerException {

		String shortTitle = null;

		try {
			Portlet portlet = _getPortlet(portletName);

			shortTitle = portlet.getPortletInfo().getShortTitle();
		}
		catch (SystemException se) {
			throw new ProducerException(se);
		}

		List<LocalizedString> shortTitles = new ArrayList<LocalizedString>();

		String resourceNamePrefix =
			portletName + SEPARATOR + SHORT_TITLE + SEPARATOR;

		LocalizedString localizedString = new LocalizedString();

		localizedString.setLang(
			WSRPUtility.toXMLLang(getDefaultLocale().toString()));
		localizedString.setResourceName(resourceNamePrefix + shortTitle);
		localizedString.setValue(shortTitle);

		shortTitles.add(localizedString);

		return shortTitles;
	}

	public List<LocalizedString> getTitle(
			String portletName, Set<String> locales)
		throws ProducerException {

		String title = null;

		try {
			Portlet portlet = _getPortlet(portletName);

			title = portlet.getPortletInfo().getTitle();
		}
		catch (SystemException se) {
			throw new ProducerException(se);
		}

		List<LocalizedString> titles = new ArrayList<LocalizedString>();

		String resourceNamePrefix = portletName + SEPARATOR + TITLE + SEPARATOR;

		LocalizedString localizedString = new LocalizedString();

		localizedString.setLang(
			WSRPUtility.toXMLLang(getDefaultLocale().toString()));
		localizedString.setResourceName(resourceNamePrefix + title);
		localizedString.setValue(title);

		titles.add(localizedString);

		return titles;
	}

	public List<ItemDescription> getUserCategories(
			String portletName, Set<String> locales)
		throws ProducerException {

		Map<String,String> roles = null;

		try {
			Portlet portlet = _getPortlet(portletName);

			roles = portlet.getRoleMappers();
		}
		catch (Exception e) {
			throw new ProducerException(e);
		}

		if ((roles == null) || roles.isEmpty()) {
			return Collections.EMPTY_LIST;
		}

		List<ItemDescription> userCategories = new ArrayList<ItemDescription>();

		String resourcePrefix =
			portletName + SEPARATOR + ROLE_DESCRIPTION + SEPARATOR;

		int i = 0;

		for (String key : roles.keySet()) {
			LocalizedString localizedString = new LocalizedString();

			localizedString.setLang(
				WSRPUtility.toXMLLang(getDefaultLocale().toString()));
			localizedString.setResourceName(
				resourcePrefix + key + SEPARATOR + i);
			localizedString.setValue(roles.get(key));

			ItemDescription itemDescription = new ItemDescription();

			itemDescription.setDescription(localizedString);
			itemDescription.setItemName(key);

			userCategories.add(itemDescription);

			i++;
		}

		return userCategories;
	}

	public List<ItemDescription> getUserProfileItems(
		String portletName, Set<String> locales) {

		List<ItemDescription> userProfiles = new ArrayList<ItemDescription>();

		try {
			Portlet portlet = _getPortlet(portletName);

			PortletApp portletApp = portlet.getPortletApp();

			Set<String> userAttributes = portletApp.getUserAttributes();

			ProfileMapManager profileMapManager = new ProfileMapManagerImpl();

			Map<String, String> wsrpDefaultUserInfoMap =
				profileMapManager.getPortletMap();

			String resourcePrefix =
				portletName + SEPARATOR + USERINFO_DESCRIPTION + SEPARATOR;

			int i = 0;

			Iterator<String> itr = userAttributes.iterator();

			while (itr.hasNext()) {
				String userAttribute = itr.next();

				String wsrpUserAttribute = wsrpDefaultUserInfoMap.get(
					userAttribute);

				if (wsrpUserAttribute == null ) {
					continue;
				}

				LocalizedString localizedString = new LocalizedString();

				localizedString.setLang(
					WSRPUtility.toXMLLang(getDefaultLocale().toString()));
				localizedString.setResourceName(
					resourcePrefix + userAttribute + SEPARATOR + i);
				localizedString.setValue(wsrpUserAttribute);

				ItemDescription itemDescription = new ItemDescription();

				itemDescription.setDescription(localizedString);
				itemDescription.setItemName(wsrpUserAttribute);

				userProfiles.add(itemDescription);

				i++;
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return userProfiles;
	}

	public boolean isCloneSupported() {
		return false;
	}

	public void removeChannel(String regHandle, String portletName) {
	}

	public void setPortletProperties(
		UserContext userContext, PropertyList propertyList,
		String portletHandle, String registrationHandle) {

		throw new UnsupportedOperationException();
	}

	private Portlet _getPortlet(String portletName) throws SystemException{
		return PortletLocalServiceUtil.getPortletById(
			CompanyConstants.SYSTEM, portletName);
	}

	private static final List<String> _WSRP_MODES =
		Arrays.asList(new String[] {"wsrp:view", "wsrp:help", "wsrp:edit"});

	private static final List<String> _WSRP_STATES =
		Arrays.asList(
			new String[] {"wsrp:maximized", "wsrp:minimized", "wsrp:normal"});

	private static Log _log =
		 LogFactoryUtil.getLog(PortletRepositoryImpl.class);

	private List<Portlet> _portlets = null;
	private PortletDescriptorHolder _portletDescriptionHolder = null;

}