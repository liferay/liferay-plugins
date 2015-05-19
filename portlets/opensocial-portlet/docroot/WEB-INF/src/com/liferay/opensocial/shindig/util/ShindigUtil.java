/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

import com.liferay.opensocial.GadgetURLException;
import com.liferay.opensocial.model.impl.GadgetImpl;
import com.liferay.opensocial.service.GadgetLocalServiceUtil;
import com.liferay.opensocial.service.OAuthConsumerLocalServiceUtil;
import com.liferay.opensocial.util.PortletPropsValues;
import com.liferay.portal.kernel.concurrent.ConcurrentHashSet;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.AutoResetThreadLocal;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.service.DLAppServiceUtil;

import java.io.File;

import java.util.Map;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;

import org.apache.shindig.auth.BasicSecurityToken;
import org.apache.shindig.auth.BasicSecurityTokenCodec;
import org.apache.shindig.auth.BlobCrypterSecurityToken;
import org.apache.shindig.common.crypto.BasicBlobCrypter;
import org.apache.shindig.common.crypto.BlobCrypter;
import org.apache.shindig.config.ContainerConfig;
import org.apache.shindig.gadgets.Gadget;
import org.apache.shindig.gadgets.process.ProcessingException;
import org.apache.shindig.gadgets.process.Processor;
import org.apache.shindig.gadgets.servlet.JsonRpcGadgetContext;
import org.apache.shindig.gadgets.spec.GadgetSpec;
import org.apache.shindig.gadgets.spec.ModulePrefs;
import org.apache.shindig.gadgets.spec.OAuthService;
import org.apache.shindig.gadgets.spec.OAuthSpec;
import org.apache.shindig.gadgets.spec.UserPref;

import org.json.JSONObject;

/**
 * @author Michael Young
 * @author Dennis Ju
 */
public class ShindigUtil {

	public static void clearGadgetSpecCache(String url) {
		_ignoreGadgetSpecCache.add(url);
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

	public static String getColumnUserPrefs(
		String namespace, ThemeDisplay themeDisplay) {

		StringBundler sb = new StringBundler(3);

		sb.append(_COLUMN_USER_PREFS);
		sb.append(namespace);

		Layout layout = themeDisplay.getLayout();

		sb.append(layout.getPlid());

		return sb.toString();
	}

	public static String getFileEntryURL(String portalURL, long fileEntryId)
		throws PortalException {

		FileEntry fileEntry = DLAppServiceUtil.getFileEntry(fileEntryId);

		StringBundler sb = new StringBundler(6);

		sb.append(portalURL);
		sb.append(PortalUtil.getPathContext());
		sb.append("/documents/");
		sb.append(fileEntry.getRepositoryId());
		sb.append(StringPool.SLASH);
		sb.append(fileEntry.getUuid());

		return sb.toString();
	}

	public static com.liferay.opensocial.model.Gadget getGadget(
			PortletPreferences portletPreferences)
		throws Exception {

		String url = portletPreferences.getValue("url", StringPool.BLANK);

		if (Validator.isNull(url)) {
			return null;
		}

		com.liferay.opensocial.model.Gadget gadget = new GadgetImpl();

		GadgetSpec gadgetSpec = null;

		try {
			gadgetSpec = ShindigUtil.getGadgetSpec(url);
		}
		catch (Exception e) {
			throw new GadgetURLException(e);
		}

		ModulePrefs modulePrefs = gadgetSpec.getModulePrefs();

		gadget.setName(modulePrefs.getTitle());
		gadget.setUrl(url);

		return gadget;
	}

	public static com.liferay.opensocial.model.Gadget getGadget(
			String portletName, long companyId)
		throws Exception {

		int pos = portletName.indexOf(StringPool.UNDERLINE);

		String uuid = GetterUtil.getString(portletName.substring(pos + 1));

		uuid = PortalUUIDUtil.fromJsSafeUuid(uuid);

		com.liferay.opensocial.model.Gadget gadget =
			GadgetLocalServiceUtil.getGadget(uuid, companyId);

		return gadget;
	}

	public static Folder getGadgetEditorRootFolder(long repositoryId)
		throws Exception {

		Folder folder = null;

		try {
			folder = DLAppServiceUtil.getFolder(
				repositoryId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
				_GADGET_EDITOR_ROOT_FOLDER_NAME);
		}
		catch (Exception e) {
		}

		if (folder == null) {
			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setGroupPermissions(
				new String[] {
					ActionKeys.ADD_DOCUMENT, ActionKeys.DELETE,
					ActionKeys.UPDATE, ActionKeys.VIEW
				});
			serviceContext.setGuestPermissions(new String[] {ActionKeys.VIEW});
			serviceContext.setScopeGroupId(repositoryId);

			folder = DLAppServiceUtil.addFolder(
				repositoryId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
				_GADGET_EDITOR_ROOT_FOLDER_NAME, StringPool.BLANK,
				serviceContext);
		}

		return folder;
	}

	public static GadgetSpec getGadgetSpec(String url) throws Exception {
		return getGadgetSpec(
			url, PortletPropsValues.SHINDIG_JS_DEBUG,
			PortletPropsValues.SHINDIG_NO_CACHE);
	}

	public static GadgetSpec getGadgetSpec(
			String url, boolean debug, boolean ignoreCache)
		throws Exception {

		if (Validator.isNull(url)) {
			throw new GadgetURLException();
		}

		JSONObject gadgetContextJSONObject = new JSONObject();

		gadgetContextJSONObject.put("debug", debug);

		if (!ignoreCache && _ignoreGadgetSpecCache.contains(url)) {
			ignoreCache = true;
		}

		gadgetContextJSONObject.put("ignoreCache", ignoreCache);

		JSONObject gadgetRequestJSONObject = new JSONObject();

		gadgetRequestJSONObject.put("url", url);

		JsonRpcGadgetContext jsonRpcGadgetContext = new JsonRpcGadgetContext(
			gadgetContextJSONObject, gadgetRequestJSONObject);

		Gadget gadget = null;

		try {
			gadget = _processor.process(jsonRpcGadgetContext);

			_ignoreGadgetSpecCache.remove(url);
		}
		catch (ProcessingException pe) {
			_ignoreGadgetSpecCache.add(url);

			throw new GadgetURLException(pe);
		}

		return gadget.getSpec();
	}

	public static String getHost() {
		return _host.get();
	}

	public static long getModuleId(String namespace) {
		return namespace.hashCode();
	}

	public static Map<String, OAuthService> getOAuthServices(String url)
		throws Exception {

		GadgetSpec gadgetSpec = getGadgetSpec(url);

		ModulePrefs modulePrefs = gadgetSpec.getModulePrefs();

		if (modulePrefs == null) {
			return null;
		}

		OAuthSpec oAuthSpec = modulePrefs.getOAuthSpec();

		if (oAuthSpec == null) {
			return null;
		}

		return oAuthSpec.getServices();
	}

	public static String getOwnerId(Layout layout) throws PortalException {
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

		return PortalUtil.getPortletNamespace(portletId);
	}

	public static String getScheme() {
		return _scheme.get();
	}

	public static String getTableOpenSocial() {
		return _TABLE_OPEN_SOCIAL;
	}

	public static boolean hasUserPrefs(GadgetSpec gadgetSpec) throws Exception {
		if (gadgetSpec == null) {
			return false;
		}

		return hasUserPrefs(gadgetSpec.getUserPrefs());
	}

	public static boolean hasUserPrefs(Map<String, UserPref> userPrefs)
		throws Exception {

		if (userPrefs == null) {
			return false;
		}

		for (UserPref userPref : userPrefs.values()) {
			if (userPref.getDataType() != UserPref.DataType.HIDDEN) {
				return true;
			}
		}

		return false;
	}

	public static boolean isContentValid(String content) {
		try {
			new GadgetSpec(null, content);

			return true;
		}
		catch (Exception e) {
		}

		return false;
	}

	public static boolean isValidUser(User user) {
		if (user.isDefaultUser()) {
			return false;
		}
		else {
			return true;
		}
	}

	public static void setHost(String host) {
		_host.set(host);
	}

	public static void setScheme(String scheme) {
		_scheme.set(scheme);
	}

	public static String transformURL(String url) {
		return StringUtil.replace(
			url, new String[] {"%host%", "%scheme%"},
			new String[] {getHost(), getScheme()});
	}

	public static void updateOAuthConsumers(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long[] oAuthConsumerIds = ParamUtil.getLongValues(
			actionRequest, "oAuthConsumerId");

		String gadgetKey = ParamUtil.getString(actionRequest, "gadgetKey");
		String[] serviceNames = ParamUtil.getParameterValues(
			actionRequest, "serviceName");
		String[] consumerKeys = ParamUtil.getParameterValues(
			actionRequest, "consumerKey");
		String[] consumerSecrets = ParamUtil.getParameterValues(
			actionRequest, "consumerSecret");
		String[] keyTypes = ParamUtil.getParameterValues(
			actionRequest, "keyType");

		if ((serviceNames.length == 0) && (keyTypes.length != 0)) {
			serviceNames = new String[] {StringPool.BLANK};
		}

		for (int i = 0; i < serviceNames.length; i++) {
			String consumerKey = (String)ArrayUtil.getValue(consumerKeys, i);

			String consumerSecret = (String)ArrayUtil.getValue(
				consumerSecrets, i);

			if (Validator.isNull(consumerKey)) {
				consumerKey = StringPool.BLANK;
			}

			if (Validator.isNull(consumerSecret)) {
				consumerSecret = StringPool.BLANK;
			}

			if (oAuthConsumerIds[i] <= 0) {
				OAuthConsumerLocalServiceUtil.addOAuthConsumer(
					themeDisplay.getCompanyId(), gadgetKey, serviceNames[i],
					consumerKey, consumerSecret, keyTypes[i]);
			}
			else {
				OAuthConsumerLocalServiceUtil.updateOAuthConsumer(
					oAuthConsumerIds[i], consumerKey, consumerSecret,
					keyTypes[i], StringPool.BLANK, StringPool.BLANK);
			}
		}
	}

	private static final String _COLUMN_USER_PREFS = "USER_PREFS_";

	private static final String _GADGET_EDITOR_ROOT_FOLDER_NAME =
		"OpenSocial Gadgets";

	private static final String _TABLE_OPEN_SOCIAL = "OPEN_SOCIAL_DATA_";

	@Inject
	private static BasicSecurityTokenCodec _basicSecurityTokenCodec;

	@Inject
	private static ContainerConfig _containerConfig;

	private static AutoResetThreadLocal<String> _host =
		new AutoResetThreadLocal<>(
			ShindigUtil.class + "._host", StringPool.BLANK);
	private static Set<String> _ignoreGadgetSpecCache =
		new ConcurrentHashSet<>();

	@Inject
	private static Processor _processor;

	private static AutoResetThreadLocal<String> _scheme =
		new AutoResetThreadLocal<>(
			ShindigUtil.class + "._scheme", StringPool.BLANK);

}