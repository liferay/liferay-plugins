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

package com.liferay.socialnetworking.service;

/**
 * <a href="WallEntryLocalServiceWrapper.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Brian Wing Shun Chan
 */
public class WallEntryLocalServiceWrapper implements WallEntryLocalService {
	public WallEntryLocalServiceWrapper(
		WallEntryLocalService wallEntryLocalService) {
		_wallEntryLocalService = wallEntryLocalService;
	}

	public com.liferay.socialnetworking.model.WallEntry addWallEntry(
		com.liferay.socialnetworking.model.WallEntry wallEntry)
		throws com.liferay.portal.SystemException {
		return _wallEntryLocalService.addWallEntry(wallEntry);
	}

	public com.liferay.socialnetworking.model.WallEntry createWallEntry(
		long wallEntryId) {
		return _wallEntryLocalService.createWallEntry(wallEntryId);
	}

	public void deleteWallEntry(long wallEntryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		_wallEntryLocalService.deleteWallEntry(wallEntryId);
	}

	public void deleteWallEntry(
		com.liferay.socialnetworking.model.WallEntry wallEntry)
		throws com.liferay.portal.SystemException {
		_wallEntryLocalService.deleteWallEntry(wallEntry);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		return _wallEntryLocalService.dynamicQuery(dynamicQuery);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		return _wallEntryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	public com.liferay.socialnetworking.model.WallEntry getWallEntry(
		long wallEntryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _wallEntryLocalService.getWallEntry(wallEntryId);
	}

	public java.util.List<com.liferay.socialnetworking.model.WallEntry> getWallEntries(
		int start, int end) throws com.liferay.portal.SystemException {
		return _wallEntryLocalService.getWallEntries(start, end);
	}

	public int getWallEntriesCount() throws com.liferay.portal.SystemException {
		return _wallEntryLocalService.getWallEntriesCount();
	}

	public com.liferay.socialnetworking.model.WallEntry updateWallEntry(
		com.liferay.socialnetworking.model.WallEntry wallEntry)
		throws com.liferay.portal.SystemException {
		return _wallEntryLocalService.updateWallEntry(wallEntry);
	}

	public com.liferay.socialnetworking.model.WallEntry updateWallEntry(
		com.liferay.socialnetworking.model.WallEntry wallEntry, boolean merge)
		throws com.liferay.portal.SystemException {
		return _wallEntryLocalService.updateWallEntry(wallEntry, merge);
	}

	public com.liferay.socialnetworking.model.WallEntry addWallEntry(
		long groupId, long userId, java.lang.String comments,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _wallEntryLocalService.addWallEntry(groupId, userId, comments,
			themeDisplay);
	}

	public void deleteWallEntries(long groupId)
		throws com.liferay.portal.SystemException {
		_wallEntryLocalService.deleteWallEntries(groupId);
	}

	public java.util.List<com.liferay.socialnetworking.model.WallEntry> getWallEntries(
		long groupId, int start, int end)
		throws com.liferay.portal.SystemException {
		return _wallEntryLocalService.getWallEntries(groupId, start, end);
	}

	public int getWallEntriesCount(long groupId)
		throws com.liferay.portal.SystemException {
		return _wallEntryLocalService.getWallEntriesCount(groupId);
	}

	public java.util.List<com.liferay.socialnetworking.model.WallEntry> getWallToWallEntries(
		long groupId1, long groupId2, long userId1, long userId2, int start,
		int end) throws com.liferay.portal.SystemException {
		return _wallEntryLocalService.getWallToWallEntries(groupId1, groupId2,
			userId1, userId2, start, end);
	}

	public int getWallToWallEntriesCount(long groupId1, long groupId2,
		long userId1, long userId2) throws com.liferay.portal.SystemException {
		return _wallEntryLocalService.getWallToWallEntriesCount(groupId1,
			groupId2, userId1, userId2);
	}

	public com.liferay.socialnetworking.model.WallEntry updateWallEntry(
		long wallEntryId, java.lang.String comments)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _wallEntryLocalService.updateWallEntry(wallEntryId, comments);
	}

	private WallEntryLocalService _wallEntryLocalService;
}