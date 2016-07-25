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
		setAlloyServiceInvokerClass(SAMTodoList.class);
		setPermissioned(true);
	}

	public void add() throws Exception {
		_validateAdd();

		SAMTodoList samTodoList = SAMTodoListLocalServiceUtil.createSAMTodoList(0);

		updateModel(samTodoList);

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	public void create() throws Exception {
		SAMTodoList samTodoList = SAMTodoListLocalServiceUtil.createSAMTodoList(0);

		renderRequest.setAttribute("samTodoList", samTodoList);
	}

	public void delete() throws Exception {
		SAMTodoList samTodoList = _fetchSAMTodoList();

		_validateDelete(samTodoList);

		SAMTodoListLocalServiceUtil.deleteSAMTodoList(samTodoList);

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	public void edit() throws Exception {
		SAMTodoList samTodoList = _fetchSAMTodoList();

		_validateEdit(samTodoList);

		renderRequest.setAttribute("samTodoList", samTodoList);
	}

	public void index() throws Exception {
		renderRequest.setAttribute("portletURL", portletURL);

		SearchContainer<SAMTodoList> searchContainer = new SearchContainer<SAMTodoList>(portletRequest, portletURL, null, null);

		String orderByCol = ParamUtil.getString(request, "orderByCol", "name");

		renderRequest.setAttribute("orderByCol", orderByCol);

		String orderByType = ParamUtil.getString(request, "orderByType", "asc");

		renderRequest.setAttribute("orderByType", orderByType);

		OrderByComparator obc = OrderByComparatorFactoryUtil.create(SAMTodoListModelImpl.TABLE_NAME, orderByCol, orderByType.equals("asc"));

		List<SAMTodoList> samTodoLists = alloyServiceInvoker.executeDynamicQuery(new Object[] {"userId", themeDisplay.getUserId()}, searchContainer.getStart(), searchContainer.getEnd(), obc);

		renderRequest.setAttribute("samTodoLists", samTodoLists);
	}

	public void update() throws Exception {
		SAMTodoList samTodoList = _fetchSAMTodoList();

		_validateUpdate(samTodoList);

		updateModel(samTodoList);

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	public void view() throws Exception {
		SAMTodoList samTodoList = _fetchSAMTodoList();

		_validateView(samTodoList);

		renderRequest.setAttribute("samTodoList", samTodoList);

		portletURL.setParameter("samTodoListId", String.valueOf(samTodoList.getSamTodoListId()));

		renderRequest.setAttribute("portletURL", portletURL);

		renderRequest.setAttribute("SAMTodoItemConstantsMethods", new SAMTodoItemConstants());

		SearchContainer<SAMTodoItem> samTodoItemSearchContainer = new SearchContainer<SAMTodoItem>(portletRequest, portletURL, null, null);

		String samTodoItemsOrderByCol = ParamUtil.getString(request, "samTodoItemsOrderByCol", "priority");

		renderRequest.setAttribute("samTodoItemsOrderByCol", samTodoItemsOrderByCol);

		String samTodoItemsOrderByType = ParamUtil.getString(request, "samTodoItemsOrderByType", "asc");

		renderRequest.setAttribute("samTodoItemsOrderByType", samTodoItemsOrderByType);

		OrderByComparator samTodoItemsOBC = OrderByComparatorFactoryUtil.create(SAMTodoListModelImpl.TABLE_NAME, samTodoItemsOrderByCol, samTodoItemsOrderByType.equals("asc"));

		AlloyServiceInvoker samTodoItemAlloyServiceInvoker = new AlloyServiceInvoker(SAMTodoItem.class.getName());

		List<SAMTodoItem> samTodoItems = samTodoItemAlloyServiceInvoker.executeDynamicQuery(new Object[] {"samTodoListId", samTodoList.getSamTodoListId()}, samTodoItemSearchContainer.getStart(), samTodoItemSearchContainer.getEnd(), samTodoItemsOBC);

		renderRequest.setAttribute("samTodoItems", samTodoItems);
	}

	private SAMTodoList _fetchSAMTodoList() throws Exception {
		long samTodoListId = ParamUtil.getLong(request, "id");

		return SAMTodoListLocalServiceUtil.fetchSAMTodoList(samTodoListId);
	}

	private void _validateAdd() throws Exception {
		_validateName();
	}

	private void _validateDelete(SAMTodoList samTodoList) throws Exception {
		_validateSAMTodoList(samTodoList);

		AlloyServiceInvoker samTodoItemAlloyServiceInvoker = new AlloyServiceInvoker(SAMTodoItem.class.getName());

		List<SAMTodoItem> samTodoItems = samTodoItemAlloyServiceInvoker.executeDynamicQuery(new Object[] {"samTodoListId", samTodoList.getSamTodoListId()});

		if (!samTodoItems.isEmpty()) {
			throw new AlloyException("the-todo-list-is-not-empty", false);
		}
	}

	private void _validateEdit(SAMTodoList samTodoList) throws Exception {
		_validateSAMTodoList(samTodoList);
	}

	private void _validateName() throws Exception {
		String name = ParamUtil.getString(request, "name");

		if (Validator.isNull(name)) {
			throw new AlloyException("the-todo-list-name-is-invalid", false);
		}

		List<SAMTodoList> samTodoLists = alloyServiceInvoker.executeDynamicQuery(new Object[] {"userId", themeDisplay.getUserId(), "name", name});

		if (!samTodoLists.isEmpty()) {
			long samTodoListId = ParamUtil.getLong(request, "id");

			SAMTodoList samTodoList = samTodoLists.get(0);

			if (samTodoList.getSamTodoListId() != samTodoListId) {
				throw new AlloyException("the-todo-list-already-exists", false);
			}
		}
	}

	private void _validateSAMTodoList(SAMTodoList samTodoList) throws Exception {
		if ((samTodoList == null) || samTodoList.isNew()) {
			long samTodoListId = ParamUtil.getLong(request, "id");

			throw new AlloyException("the-todo-list-with-id-x-does-not-exist", new Object[] {samTodoListId}, false);
		}
	}

	private void _validateUpdate(SAMTodoList samTodoList) throws Exception {
		_validateSAMTodoList(samTodoList);

		_validateName();
	}

	private void _validateView(SAMTodoList samTodoList) throws Exception {
		_validateSAMTodoList(samTodoList);
	}

}
%>