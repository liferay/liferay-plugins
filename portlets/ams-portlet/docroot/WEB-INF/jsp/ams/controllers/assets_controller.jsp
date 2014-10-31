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
public class AlloyControllerImpl extends BaseAlloyControllerImpl {

	public AlloyControllerImpl() {
		setPermissioned(true);
	}

	public void delete() throws Exception {
		long assetId = ParamUtil.getLong(request, "id");

		AssetLocalServiceUtil.deleteAsset(assetId);

		addSuccessMessage();

		PortletURL redirectURL = liferayPortletResponse.createRenderURL();

		redirectTo(redirectURL);
	}

	public void index() throws Exception {
		AlloySearchResult alloySearchResult = search(null);

		renderRequest.setAttribute("alloySearchResult", alloySearchResult);

		if (Validator.isNotNull(format) && format.equals("json")) {
			setJSONResponseContent(alloySearchResult);
		}
	}

	public void save() throws Exception {
		Asset asset = AssetLocalServiceUtil.createAsset(0);

		String serialNumber = ParamUtil.getString(request, "serialNumber");

		Pattern pattern = Pattern.compile(_SERIAL_NUMBER_REGEX);

		Matcher matcher = pattern.matcher(serialNumber);

		if (!matcher.find()) {
			throw new AlloyException("invalid-serial-number");
		}

		updateModel(asset);

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	public void view() throws Exception {
		long assetId = ParamUtil.getLong(request, "id");

		Asset asset = AssetLocalServiceUtil.getAsset(assetId);

		renderRequest.setAttribute("asset", asset);

		if (Validator.isNotNull(format) && format.equals("json")) {
			setJSONResponseContent(asset);
		}
	}

	@Override
	protected Indexer buildIndexer() {
		return AssetIndexer.getInstance();
	}

	private static final String _SERIAL_NUMBER_REGEX = "^[a-zA-Z0-9]+$";

}
%>