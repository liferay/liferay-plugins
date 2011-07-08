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

package com.liferay.mail.vaadin;

import com.liferay.mail.model.Account;
import com.liferay.mail.model.Folder;
import com.liferay.mail.service.AccountLocalServiceUtil;
import com.liferay.mail.service.FolderLocalServiceUtil;
import com.liferay.mail.service.MessageLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import com.vaadin.data.Container.Hierarchical;
import com.vaadin.data.Container.ItemSetChangeNotifier;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.MethodProperty;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.terminal.Resource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Henri Sara
 */
public class FolderContainer implements Hierarchical, ItemSetChangeNotifier {

	private static Log _log = LogFactoryUtil.getLog(FolderContainer.class);

	private static final String CAPTION = "caption";

	private static final String ICON = "icon";

	private final Resource ICON_INBOX = new PortletResource("images/vaadin/icons/home.png");

	private final Resource ICON_GENERIC_FOLDER =
		new PortletResource("images/vaadin/icons/folder.png");

	private final Resource ICON_DRAFT_FOLDER =
		new PortletResource("images/vaadin/icons/draft.png");

	private final Resource ICON_TRASH_FOLDER =
		new PortletResource("images/vaadin/icons/trash.png");

	private final Resource ICON_SENT_FOLDER =
		new PortletResource("images/vaadin/icons/sentfolder.png");

	private final Resource ICON_ACCOUNT =
		new PortletResource("images/vaadin/icons/account.png");

	List<ItemId> rootIds = new ArrayList<ItemId>();

	private Map<Long, List<ItemId>> accountFolders =
		new HashMap<Long, List<ItemId>>();
	private Map<Long, Account> accountIdToAccount =
		new HashMap<Long, Account>();

	private static List<String> propertyIds;

	private List<ItemSetChangeListener> itemSetChangeListeners;

	static {
		propertyIds = new ArrayList<String>();
		propertyIds.add(CAPTION);
	}

	private class ItemId {

		private Account account;
		private Folder folder;

		public ItemId(Account account) {

			this.account = account;
		}

		public Account getAccount() {

			return account;
		}

		public Folder getFolder() {

			return folder;
		}

		public ItemId(Folder folder) {

			this.folder = folder;
		}

		public boolean isAccount() {

			return (account != null);
		}
	}

	public FolderContainer(List<Account> accounts) {

		for (Account account : accounts) {
			rootIds.add(new ItemId(account));
			accountIdToAccount.put(account.getAccountId(), account);
		}
	}

	public Collection<ItemId> rootItemIds() {

		return rootIds;
	}

	public boolean hasChildren(Object itemId) {

		if (rootIds.contains(itemId)) {
			return true;
		}

		return false;
	}

	public Collection<ItemId> getChildren(Object itemId) {

		ItemId id = (ItemId) itemId;
		if (id.isAccount()) {
			return getFolders(id.getAccount().getAccountId());
		}
		else {
			return null;
		}
	}

	private List<ItemId> getFolders(Long accountId) {

		List<ItemId> itemIds = accountFolders.get(accountId);
		if (itemIds == null) {
			itemIds = new ArrayList<ItemId>();
			try {
				List<Folder> folders =
					FolderLocalServiceUtil.getFolders(accountId);
				for (Folder folder : folders) {
					itemIds.add(new ItemId(folder));
				}
			}
			catch (SystemException e) {
				_log.debug(e);
			}

			accountFolders.put(accountId, itemIds);
		}

		return itemIds;
	}

	public Object getParent(Object itemId) {

		if (itemId == null || rootIds.contains(itemId)) {
			return null;
		}
		else {
			return ((ItemId) itemId).getFolder().getAccountId();
		}
	}

	public boolean areChildrenAllowed(Object itemId) {

		boolean rootItem = isRoot(itemId);
		return rootItem;
	}

	public boolean isRoot(Object itemId) {

		return rootIds.contains(itemId);
	}

	public boolean setChildrenAllowed(Object itemId, boolean areChildrenAllowed)
		throws UnsupportedOperationException {

		throw new UnsupportedOperationException();
	}

	public boolean setParent(Object itemId, Object newParentId)
		throws UnsupportedOperationException {

		throw new UnsupportedOperationException();
	}

	public Property getContainerProperty(Object itemId, Object propertyId) {

		ItemId id = (ItemId) itemId;
		if (CAPTION.equals(propertyId)) {
			if (id.isAccount()) {
				return new MethodProperty(id.getAccount(), "address");
			}
			else {
				Folder f = id.getFolder();
				if (accountIdToAccount.get(f.getAccountId()).getInboxFolderId() == f.getFolderId()) {
					String text = id.getFolder().getDisplayName();
					try {
						long unread = MessageLocalServiceUtil
								.getFolderUnreadMessagesCount(f.getFolderId());
						text += " (" + unread + ")";
					}
					catch (SystemException e) {
						text += " (?)";
						_log.debug(e);
					}

					return new ObjectProperty(text);
				}
				else {
					return new MethodProperty(id.getFolder(), "displayName");
				}
			}
		}
		else if (ICON.equals(propertyId)) {
			Resource icon = getIconForItem(id);
			if (icon == null) {
				return null;
			}
			else {
				return new ObjectProperty(icon);
			}

		}
		else {
			return null;
		}
	}

	private Resource getIconForItem(ItemId id) {

		if (id.isAccount()) {
			return ICON_ACCOUNT;
		}
		else {
			Folder f = id.getFolder();
			Account account =
				accountIdToAccount.get(f.getAccountId());
			if (account.getInboxFolderId() == f.getFolderId()) {
				return ICON_INBOX;
			}
			else if (account.getTrashFolderId() == f.getFolderId()) {
				return ICON_TRASH_FOLDER;
			}
			else if (account.getDraftFolderId() == f.getFolderId()) {
				return ICON_DRAFT_FOLDER;
			}
			else if (account.getSentFolderId() == f.getFolderId()) {
				return ICON_SENT_FOLDER;
			}
			else {
				return ICON_GENERIC_FOLDER;
			}
		}

	}

	public boolean isModifiable(Object itemId) {
		ItemId id = (ItemId) itemId;
		if (id.isAccount()) {
			return false;
		} else {
			Folder f = id.getFolder();
			Account account =
				accountIdToAccount.get(f.getAccountId());
			if (account.getInboxFolderId() == f.getFolderId()) {
				return false;
			}
			else if (account.getTrashFolderId() == f.getFolderId()) {
				return false;
			}
			else if (account.getDraftFolderId() == f.getFolderId()) {
				return false;
			}
			else if (account.getSentFolderId() == f.getFolderId()) {
				return false;
			}

			return true;
		}
	}

	public static Object getCaptionPropertyId() {

		return CAPTION;
	}

	public static Object getIconPropertyId() {

		return ICON;
	}

	public Folder getFolder(Object itemId) {

		ItemId id = (ItemId) itemId;
		if (id == null || id.isAccount()) {
			return null;
		}

		return id.getFolder();
	}

	public boolean addContainerProperty(
		Object propertyId, Class<?> type, Object defaultValue)
		throws UnsupportedOperationException {

		throw new UnsupportedOperationException();
	}

	public Object addItem()
		throws UnsupportedOperationException {

		throw new UnsupportedOperationException();
	}

	public Item addItem(Object itemId)
		throws UnsupportedOperationException {

		throw new UnsupportedOperationException();
	}

	public boolean containsId(Object itemId) {

		if (itemId instanceof ItemId) {
			return true;
		}

		return false;
	}

	public Collection<String> getContainerPropertyIds() {

		return propertyIds;
	}

	public Item getItem(Object itemId) {

		return null;
	}

	public Collection<ItemId> getItemIds() {

		return rootItemIds();
	}

	public Class<?> getType(Object propertyId) {

		if (ICON.equals(propertyId)) {
			return Resource.class;
		}
		return String.class;
	}

	public boolean removeAllItems()
		throws UnsupportedOperationException {

		throw new UnsupportedOperationException();
	}

	public boolean removeContainerProperty(Object propertyId)
		throws UnsupportedOperationException {

		throw new UnsupportedOperationException();
	}

	public boolean removeItem(Object itemId)
		throws UnsupportedOperationException {

		throw new UnsupportedOperationException();
	}

	public int size() {

		return 0;
	}

	public Account getAccount(Object value) {

		if (value == null || !(value instanceof ItemId)) {
			return null;
		}
		ItemId id = (ItemId) value;
		if (id.isAccount()) {
			return id.getAccount();
		}
		else {
			return accountIdToAccount.get(id.getFolder().getAccountId());
		}
	}

	public Object getAccountItemId(Long accountId) {

		if (accountId == null) {
			return null;
		}

		for (ItemId itemId : rootItemIds()) {
			if (itemId.getAccount().getAccountId() == accountId) {
				return itemId;
			}
		}

		return null;
	}

	public Object getFolderItemId(Long accountId, Long folderId) {

		if (accountId == null || folderId == null) {
			return null;
		}

		List<ItemId> folders = getFolders(accountId);
		if (folders == null) {
			return null;
		}

		for (ItemId folderItemId : folders) {
			if (folderItemId.getFolder().getFolderId() == folderId) {
				return folderItemId;
			}
		}

		return null;
	}

	public void refresh() {
		rootIds.clear();
		accountFolders = new HashMap<Long, List<ItemId>>();
		Collection<Account> oldAccounts = accountIdToAccount.values();
		accountIdToAccount = new HashMap<Long, Account>();

		for (Account account : oldAccounts) {
			try {
				account = AccountLocalServiceUtil.getAccount(account.getAccountId());
				// any errors cause skipping that account
				rootIds.add(new ItemId(account));
				accountIdToAccount.put(account.getAccountId(), account);
			} catch (PortalException e) {
				_log.info("The account "+account.getAccountId()+" does not exist anymore", e);
			} catch (SystemException e) {
				_log.info("The account "+account.getAccountId()+" does not exist anymore", e);
			}
		}

		fireItemSetChange();
	}

	public void addListener(ItemSetChangeListener listener) {
		if (itemSetChangeListeners == null) {
			itemSetChangeListeners = new LinkedList<ItemSetChangeListener>();
		}
		itemSetChangeListeners.add(listener);
	}

	public void removeListener(ItemSetChangeListener listener) {
		if (itemSetChangeListeners != null) {
			itemSetChangeListeners.remove(listener);
		}
	}

	private void fireItemSetChange() {
		if (itemSetChangeListeners != null) {
			final Container.ItemSetChangeEvent event = new Container.ItemSetChangeEvent() {
				public Container getContainer() {
					return FolderContainer.this;
				}
			};
			for (ItemSetChangeListener listener : itemSetChangeListeners) {
				listener.containerItemSetChange(event);
			}
		}
	}

}