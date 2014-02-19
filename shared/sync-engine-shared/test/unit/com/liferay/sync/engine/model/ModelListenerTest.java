/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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
		SyncFile syncFile = SyncFileTestUtil.addFileSyncFile(
			"/home/liferay/test", 0, syncAccount.getSyncAccountId());

		SyncFileService.registerListener(new SyncFileModelListener());

		syncFile.setFilePathName("/home/liferay/test2");
		syncFile.setSize(256);

		SyncFileService.update(syncFile);

		Assert.assertEquals(
			"/home/liferay/test", _originalFieldValues.get("filePathName"));
		Assert.assertEquals(2, _originalFieldValues.size());
	}

	@Test
	public void testUpdateSyncFileIdSyncFile() throws Exception {
		SyncFile syncFile = SyncFileTestUtil.addFileSyncFile(
			"/home/liferay/test", 0, syncAccount.getSyncAccountId());

		SyncFileService.registerListener(new SyncFileModelListener());

		syncFile.setSyncFileId(12345);

		SyncFileService.update(syncFile);

		Assert.assertTrue(_originalFieldValues.isEmpty());
	}

	private Map<String, Object> _originalFieldValues =
		new HashMap<String, Object>();

	private class SyncFileModelListener implements ModelListener<SyncFile> {

		@Override
		public void onUpdate(
			SyncFile syncFile, Map<String, Object> originalFieldValues) {

			_originalFieldValues = originalFieldValues;
		}

	}

}