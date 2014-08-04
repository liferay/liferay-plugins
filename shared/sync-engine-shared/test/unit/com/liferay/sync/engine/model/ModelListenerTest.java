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

package com.liferay.sync.engine.model;

import com.liferay.sync.engine.BaseTestCase;
import com.liferay.sync.engine.service.SyncFileService;
import com.liferay.sync.engine.util.FileUtil;
import com.liferay.sync.engine.util.SyncFileTestUtil;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Shinn Lok
 */
public class ModelListenerTest extends BaseTestCase {

	@Test
	public void testUpdateFilePathNameSyncFile() throws Exception {
		SyncFileService.registerModelListener(new SyncFileModelListener());

		SyncFile syncFile = SyncFileTestUtil.addFileSyncFile(
			FileUtil.getFilePathName(filePathName, "test"), 0,
			syncAccount.getSyncAccountId());

		Assert.assertTrue(_onCreateCalled);

		syncFile.setFilePathName(
			FileUtil.getFilePathName(filePathName, "test2"));
		syncFile.setSize(256);

		SyncFileService.update(syncFile);

		Assert.assertEquals(
			FileUtil.getFilePathName(filePathName, "test"),
			_originalFieldValues.get("filePathName"));
		Assert.assertEquals(3, _originalFieldValues.size());

		SyncFileService.deleteSyncFile(syncFile);

		Assert.assertTrue(_onRemoveCalled);
	}

	@Test
	public void testUpdateSyncFileIdSyncFile() throws Exception {
		SyncFileService.registerModelListener(new SyncFileModelListener());

		SyncFile syncFile = SyncFileTestUtil.addFileSyncFile(
			FileUtil.getFilePathName(filePathName, "test"), 0,
			syncAccount.getSyncAccountId());

		syncFile.setSyncFileId(12345);

		SyncFileService.update(syncFile);

		Assert.assertEquals(1, _originalFieldValues.size());
		Assert.assertFalse(_onRemoveCalled);
	}

	private boolean _onCreateCalled;
	private boolean _onRemoveCalled;
	private Map<String, Object> _originalFieldValues =
		new HashMap<String, Object>();

	private class SyncFileModelListener implements ModelListener<SyncFile> {

		@Override
		public void onCreate(SyncFile model) {
			_onCreateCalled = true;
		}

		@Override
		public void onRemove(SyncFile model) {
			_onRemoveCalled = true;
		}

		@Override
		public void onUpdate(
			SyncFile syncFile, Map<String, Object> originalValues) {

			_originalFieldValues = originalValues;
		}

	}

}