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

package com.liferay.opensocial.shindig.util;

import com.google.inject.Inject;

import com.liferay.opensocial.gadget.portlet.GadgetPortlet;
import com.liferay.opensocial.service.GadgetLocalServiceUtil;
import com.liferay.opensocial.util.PortletPropsValues;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import java.io.File;

import java.util.Map;

import javax.portlet.PortletRequest;

import org.apache.shindig.auth.BasicSecurityToken;
import org.apache.shindig.auth.BasicSecurityTokenCodec;
import org.apache.shindig.auth.BlobCrypterSecurityToken;
import org.apache.shindig.common.crypto.BasicBlobCrypter;
import org.apache.shindig.common.crypto.BlobCrypter;
import org.apache.shindig.config.ContainerConfig;
import org.apache.shindig.gadgets.Gadget;
import org.apache.shindig.gadgets.process.Processor;
import org.apache.shindig.gadgets.servlet.JsonRpcGadgetContext;
import org.apache.shindig.gadgets.spec.GadgetSpec;
import org.apache.shindig.gadgets.spec.ModulePrefs;
import org.apache.shindig.gadgets.spec.OAuthService;
import org.apache.shindig.gadgets.spec.OAuthSpec;

import org.json.JSONObject;

/**
 * @author Michael Young
 * @author Dennis Ju
 */
public class ShindigUtil {

	public static String convertFromOpenSsl(String key) {
		key = key.replaceAll(_OPEN_SSL_A_Z, StringPool.BLANK);
		key = key.replace(StringPool.NEW_LINE, StringPool.BLANK);

		return key;
	}

	public static String createSecurityToken(
			String ownerId, long viewerId, String appId, String domain,
			String appUrl, long moduleId, String activeUrl)
		throws Exception {

		String securityToken = StringPool.BLANK;

		String securityTokenType = _containerConfig.getString(
			ContainerConfig.DEFAULT_CONTAINER, "gadgets.securityTokenType");

		if (securityTokenType.equals("secure")) {
			String securityTokenKeyPath = _containerConfig.getString(
				ContainerConfig.DEFAULT_CONTAINER,
				"gadgets.securityTokenKeyFile");

			File securityTokenKeyFile = new File(securityTokenKeyPath);

			BlobCrypter blobCrypter = new BasicBlobCrypter(
				securityTokenKeyFile);

			BlobCrypterSecurityToken blobCrypterSecurityToken =
				new BlobCrypterSecurityToken(
					blobCrypter, ContainerConfig.DEFAULT_CONTAINER, domain);

			blobCrypterSecurityToken.setAppUrl(appUrl);
			blobCrypterSecurityToken.setModuleId(moduleId);
			blobCrypterSecurityToken.setOwnerId(ownerId);
			blobCrypterSecurityToken.setViewerId(String.valueOf(viewerId));

			securityToken = blobCrypterSecurityToken.encrypt();
		}
		else if (securityTokenType.equals("insecure")) {
			BasicSecurityToken basicSecurityToken = new BasicSecurityToken(
				ownerId, String.valueOf(viewerId), appId, domain, appUrl,
				String.valueOf(moduleId), ContainerConfig.DEFAULT_CONTAINER,
				activeUrl, null);

			securityToken = _basicSecurityTokenCodec.encodeToken(
				basicSecurityToken);
		}

		securityToken = HttpUtil.encodeURL(securityToken);

		return securityToken;
	}

	public static String getColumnUserPrefs(String namespace) {
		return _COLUMN_USER_PREFS.concat(namespace);
	}

	public static com.liferay.opensocial.model.Gadget getGadget(
			String portletName)
		throws Exception {

		int pos = portletName.indexOf(
			StringPool.UNDERLINE, GadgetPortlet.PORTLET_NAME_PREFIX.length());

		String uuid = GetterUtil.getString(portletName.substring(pos + 1));

		uuid = PortalUUIDUtil.fromJsSafeUuid(uuid);

		com.liferay.opensocial.model.Gadget gadget =
			GadgetLocalServiceUtil.getGadget(uuid);

		return gadget;
	}

	public static GadgetSpec getGadgetSpec(String url) throws Exception {
		JSONObject gadgetContextJSONObject = new JSONObject();

		gadgetContextJSONObject.put(
			"debug",
			GetterUtil.getBoolean(PortletPropsValues.SHINDIG_JS_DEBUG));
		gadgetContextJSONObject.put(
			"ignoreCache",
			GetterUtil.getBoolean(PortletPropsValues.SHINDIG_NO_CACHE));

		JSONObject gadgetRequestJSONObject = new JSONObject();

		gadgetRequestJSONObject.put("url", url);

		JsonRpcGadgetContext jsonRpcGadgetContext = new JsonRpcGadgetContext(
			gadgetContextJSONObject, gadgetRequestJSONObject);

		Gadget gadget = _processor.process(jsonRpcGadgetContext);

		return gadget.getSpec();
	}

	public static long getModuleId(String namespace) {
		return namespace.hashCode();
	}

	public static Map<String, OAuthService> getOAuthServices(
		GadgetSpec gadgetSpec) {

		ModulePrefs modulePrefs = gadgetSpec.getModulePrefs();

		if (modulePrefs == null) {
			return null;
		}

		OAuthSpec oauthSpec = modulePrefs.getOAuthSpec();

		if (oauthSpec == null) {
			return null;
		}

		return oauthSpec.getServices();
	}

	public static String getOwnerId(Layout layout)
		throws PortalException, SystemException {

		Group group = layout.getGroup();

		long classPK = group.getClassPK();

		String ownerId = "G-" + classPK;

		if (group.isUser()) {
			ownerId = String.valueOf(classPK);
		}

		return ownerId;
	}

	public static String getPortletResourceNamespace(
			PortletRequest portletRequest, ThemeDisplay themeDisplay)
		throws Exception {

		String portletId = ParamUtil.getString(
			portletRequest, "portletResource");

		Portlet portlet = PortletLocalServiceUtil.getPortletById(
			themeDisplay.getCompanyId(), portletId);

		return PortalUtil.getPortletNamespace(portlet.getPortletName());
	}

	public static String getTableOpenSocial() {
		return _TABLE_OPEN_SOCIAL;
	}

	private static final String _COLUMN_USER_PREFS = "USER_PREFS_";

	private static final String _OPEN_SSL_A_Z = "-----[A-Z ]*-----";

	private static final String _TABLE_OPEN_SOCIAL = "OPEN_SOCIAL_DATA_";

	@Inject
	private static BasicSecurityTokenCodec _basicSecurityTokenCodec;

	@Inject
	private static ContainerConfig _containerConfig;

	@Inject
	private static Processor _processor;

}