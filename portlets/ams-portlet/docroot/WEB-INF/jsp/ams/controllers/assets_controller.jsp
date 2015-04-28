<%--
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
--%>

<%@ include file="/WEB-INF/jsp/ams/controllers/init.jspf" %>
<%@ include file="/WEB-INF/jsp/util/asset_indexer.jspf" %>

<%!
public static class AlloyControllerImpl extends BaseAlloyControllerImpl {

	public AlloyControllerImpl() {
		setPermissioned(true);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"id"}, parameterTypes = {Long.class})
	public void delete() throws Exception {
		long assetId = ParamUtil.getLong(request, "id");

		AssetLocalServiceUtil.deleteAsset(assetId);

		if (isRespondingTo()) {
			respondWith(translate("your-request-completed-successfully"));

			return;
		}

		addSuccessMessage();

		PortletURL redirectURL = liferayPortletResponse.createRenderURL();

		redirectTo(redirectURL);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.RENDER_PHASE, parameterNames = {"cur", "delta"}, parameterTypes = {Integer.class, Integer.class})
	public void index() throws Exception {
		AlloySearchResult alloySearchResult = search(null);

		renderRequest.setAttribute("alloySearchResult", alloySearchResult);

		respondWith(alloySearchResult);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"id", "serialNumber"}, parameterTypes = {Long.class, String.class})
	public void save() throws Exception {
		Asset asset = AssetLocalServiceUtil.createAsset(0);

		_validateSave();

		updateModel(asset);

		if (isRespondingTo()) {
			respondWith(asset);

			return;
		}

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.RENDER_PHASE, parameterNames = {"id"}, parameterTypes = {Long.class})
	public void view() throws Exception {
		long assetId = ParamUtil.getLong(request, "id");

		Asset asset = AssetLocalServiceUtil.getAsset(assetId);

		renderRequest.setAttribute("asset", asset);

		respondWith(indexer.getDocument(asset));
	}

	@Override
	protected Indexer buildIndexer() {
		return AssetIndexer.getInstance();
	}

	private void _validateSave() throws Exception {
		String serialNumber = ParamUtil.getString(request, "serialNumber");

		Pattern pattern = Pattern.compile(_SERIAL_NUMBER_REGEX);

		Matcher matcher = pattern.matcher(serialNumber);

		if (!matcher.find()) {
			throw new AlloyException("the-serial-number-is-invalid");
		}
	}

	private static final String _SERIAL_NUMBER_REGEX = "^[a-zA-Z0-9]+$";

}
%>