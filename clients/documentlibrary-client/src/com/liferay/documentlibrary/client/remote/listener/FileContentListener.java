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

package com.liferay.documentlibrary.client.remote.listener;

import com.liferay.documentlibrary.client.data.FileData;
import com.liferay.documentlibrary.client.data.FileSystemManagerUtil;
import com.liferay.documentlibrary.client.event.FileSystemEventManagerUtil;
import com.liferay.documentlibrary.client.local.ChangeType;
import com.liferay.documentlibrary.client.util.AppPropsValues;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;

import com.ning.http.client.resumable.ResumableListener;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import java.nio.ByteBuffer;

/**
 * @author Gail Hernandez
 */
public class FileContentListener implements ResumableListener {

	public FileContentListener(FileData fileData) {
		_fileData = fileData;

		StringBundler sb = new StringBundler(3);

		sb.append(AppPropsValues.ROOT_FOLDER);
		sb.append(File.separator);
		sb.append(FileSystemManagerUtil.getFullFileName(fileData.getId()));

		String filename = sb.toString();

		File file = new File(filename);

		if (_log.isInfoEnabled()) {
			_log.info("Creating file " + filename);
		}

		try {
			if (!file.exists()) {
				FileSystemEventManagerUtil.addLocalFileEvent(
					fileData.getId(), ChangeType.ADDED);

				file.createNewFile();
			}

			_randomAccessFile = new RandomAccessFile(file, "rw");
		}
		catch(Exception e) {
			_log.error(e, e);
			_randomAccessFile = null;
		}
	}

	public long length() {
		try {
			return _randomAccessFile.length();
		}
		catch (IOException e) {
			_log.error(e, e);
		}

		return 0;
	}

	public void onAllBytesReceived() {
		if (_log.isInfoEnabled()) {
			_log.info("Completed file " + _fileData.getTitle());
		}

		FileSystemEventManagerUtil.addLocalFileEvent(
			_fileData.getId(), ChangeType.MODIFIED);

		try {
			_randomAccessFile.close();
		}
		catch (IOException e) {
			_log.error(e, e);
		}

		_fileData.setDownloadCompleted(true);
	}

	public void onBytesReceived(ByteBuffer byteBuffer) throws IOException {
		_randomAccessFile.seek(length());
		_randomAccessFile.write(byteBuffer.array());
	}

	private static Log _log = LogFactoryUtil.getLog(
		FileContentListener.class.getName());

	private FileData _fileData;

	private RandomAccessFile _randomAccessFile;

}