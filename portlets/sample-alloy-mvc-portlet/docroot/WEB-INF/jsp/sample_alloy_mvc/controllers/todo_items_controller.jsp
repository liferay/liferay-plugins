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

<%@ include file="/WEB-INF/jsp/sample_alloy_mvc/controllers/init.jspf" %>

<%!
public static class AlloyControllerImpl extends BaseAlloyControllerImpl {

	public AlloyControllerImpl() {
		setAlloyServiceInvokerClass(SAMTodoItem.class);
		setPermissioned(true);
	}

	public void add() throws Exception {
		_validateAdd();

		SAMTodoItem samTodoItem = SAMTodoItemLocalServiceUtil.createSAMTodoItem(0);

		updateModel(samTodoItem, "status", 0);

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	public void create() throws Exception {
		renderRequest.setAttribute("SAMTodoItemConstants", getConstantsBean(SAMTodoItemConstants.class));

		SAMTodoItem samTodoItem = SAMTodoItemLocalServiceUtil.createSAMTodoItem(0);

		renderRequest.setAttribute("samTodoItem", samTodoItem);

		long samTodoListId = ParamUtil.getLong(request, "samTodoListId");

		renderRequest.setAttribute("samTodoListId", samTodoListId);
	}

	public void delete() throws Exception {
		SAMTodoItem samTodoItem = _fetchSAMTodoItem();

		_validateDelete(samTodoItem);

		SAMTodoItemLocalServiceUtil.deleteSAMTodoItem(samTodoItem);

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	public void edit() throws Exception {
		SAMTodoItem samTodoItem = _fetchSAMTodoItem();

		_validateEdit(samTodoItem);

		renderRequest.setAttribute("SAMTodoItemConstants", getConstantsBean(SAMTodoItemConstants.class));

		renderRequest.setAttribute("samTodoItem", samTodoItem);

		renderRequest.setAttribute("samTodoListId", samTodoItem.getSamTodoListId());

		AlloyServiceInvoker samTodoListAlloyServiceInvoker = new AlloyServiceInvoker(SAMTodoList.class.getName());

		List<SAMTodoList> samTodoLists = samTodoListAlloyServiceInvoker.executeDynamicQuery(new Object[] {"userId", themeDisplay.getUserId()});

		renderRequest.setAttribute("samTodoLists", samTodoLists);
	}

	public void update() throws Exception {
		SAMTodoItem samTodoItem = _fetchSAMTodoItem();

		_validateUpdate(samTodoItem);

		updateModel(samTodoItem);

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	public void view() throws Exception {
		SAMTodoItem samTodoItem = _fetchSAMTodoItem();

		_validateView(samTodoItem);

		renderRequest.setAttribute("SAMTodoItemConstants", getConstantsBean(SAMTodoItemConstants.class));
		renderRequest.setAttribute("SAMTodoItemConstantsMethods", new SAMTodoItemConstants());

		renderRequest.setAttribute("samTodoItem", samTodoItem);

		SAMTodoList samTodoList = SAMTodoListLocalServiceUtil.fetchSAMTodoList(samTodoItem.getSamTodoListId());

		renderRequest.setAttribute("samTodoList", samTodoList);
	}

	private SAMTodoItem _fetchSAMTodoItem() throws Exception {
		long samTodoItemId = ParamUtil.getLong(request, "id");

		return SAMTodoItemLocalServiceUtil.fetchSAMTodoItem(samTodoItemId);
	}

	private void _validateAdd() throws Exception {
		_validateDescription();
	}

	private void _validateDelete(SAMTodoItem samTodoItem) throws Exception {
		_validateSAMTodoItem(samTodoItem);
	}

	private void _validateDescription() throws Exception {
		String description = ParamUtil.getString(request, "description");

		if (Validator.isNull(description)) {
			throw new AlloyException("the-todo-item-description-is-invalid", false);
		}

		List<SAMTodoItem> samTodoItems = alloyServiceInvoker.executeDynamicQuery(new Object[] {"userId", themeDisplay.getUserId(), "description", description});

		if (!samTodoItems.isEmpty()) {
			SAMTodoItem samTodoItem = samTodoItems.get(0);

			long samTodoItemId = ParamUtil.getLong(request, "id");

			if (samTodoItem.getSamTodoItemId() != samTodoItemId) {
				throw new AlloyException("the-todo-item-already-exists", false);
			}
		}
	}

	private void _validateEdit(SAMTodoItem samTodoItem) throws Exception {
		_validateSAMTodoItem(samTodoItem);
	}

	private void _validateSAMTodoItem(SAMTodoItem samTodoItem) throws Exception {
		if ((samTodoItem == null) || samTodoItem.isNew()) {
			long samTodoItemId = ParamUtil.getLong(request, "id");

			throw new AlloyException("the-todo-item-with-id-x-does-not-exist", new Object[] {samTodoItemId}, false);
		}
	}

	private void _validateUpdate(SAMTodoItem samTodoItem) throws Exception {
		_validateSAMTodoItem(samTodoItem);

		_validateDescription();
	}

	private void _validateView(SAMTodoItem samTodoItem) throws Exception {
		_validateSAMTodoItem(samTodoItem);
	}

}
%>