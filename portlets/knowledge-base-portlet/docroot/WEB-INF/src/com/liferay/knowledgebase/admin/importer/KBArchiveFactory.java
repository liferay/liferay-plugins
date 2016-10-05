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

package com.liferay.knowledgebase.admin.importer;

import com.liferay.knowledgebase.KBArticleImportException;
import com.liferay.knowledgebase.admin.importer.util.PathUtil;
import com.liferay.knowledgebase.util.PortletPropsValues;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.zip.ZipReader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Adolfo Pérez
 */
public class KBArchiveFactory {

	public KBArchive createKBArchive(ZipReader zipReader)
		throws PortalException {

		List<String> entries = zipReader.getEntries();

		if (entries == null) {
			throw new KBArticleImportException(
				"The uploaded file is not a ZIP archive or it is corrupted");
		}

		Collections.sort(entries);

		KBArchiveState kbArchiveState = new KBArchiveState(zipReader);

		for (String entry : entries) {
			boolean validArticleExtension = false;

			for (String articleExtension :
					PortletPropsValues.MARKDOWN_IMPORTER_ARTICLE_EXTENSIONS) {

				if (entry.endsWith(articleExtension)) {
					validArticleExtension = true;

					break;
				}
			}

			if (!validArticleExtension) {
				continue;
			}

			String entryParentPath = PathUtil.getParent(entry);

			kbArchiveState.setCurrentFolder(entryParentPath);

			String entryFileNamePath = PathUtil.getFileName(entry);

			String markdownImporterArticleIntro =
				PortletPropsValues.MARKDOWN_IMPORTER_ARTICLE_INTRO;

			String entryFileName = entryFileNamePath.toString();

			if (entryFileName.endsWith(markdownImporterArticleIntro)) {
				kbArchiveState.setCurrentFolderIntroFile(entry);
			}
			else {
				kbArchiveState.addCurrentFolderFile(entry);
			}
		}

		return new KBArchiveImpl(kbArchiveState.getFolders());
	}

	private static final class FileImpl implements KBArchive.File {

		public FileImpl(String name, ZipReader zipReader) {
			_name = name;
			_zipReader = zipReader;
		}

		@Override
		public String getContent() {
			return _zipReader.getEntryAsString(getName());
		}

		@Override
		public String getName() {
			return _name;
		}

		private final String _name;
		private final ZipReader _zipReader;

	}

	private static final class FolderImpl implements KBArchive.Folder {

		public FolderImpl(
			String name, KBArchive.Folder parentFolder,
			KBArchive.File introFile, Collection<KBArchive.File> files) {

			_name = name;
			_parentFolder = parentFolder;
			_introFile = introFile;
			_files = files;
		}

		@Override
		public Collection<KBArchive.File> getFiles() {
			return _files;
		}

		@Override
		public KBArchive.File getIntroFile() {
			return _introFile;
		}

		@Override
		public String getName() {
			return _name;
		}

		@Override
		public KBArchive.File getParentFolderIntroFile() {
			if (_parentFolder == null) {
				return null;
			}

			return _parentFolder.getIntroFile();
		}

		private final Collection<KBArchive.File> _files;
		private final KBArchive.File _introFile;
		private final String _name;
		private final KBArchive.Folder _parentFolder;

	}

	private static final class KBArchiveImpl implements KBArchive {

		public KBArchiveImpl(Collection<KBArchive.Folder> folders) {
			_folders = folders;
		}

		@Override
		public Collection<KBArchive.Folder> getFolders() {
			return _folders;
		}

		private final Collection<KBArchive.Folder> _folders;

	}

	private static final class KBArchiveState {

		public KBArchiveState(ZipReader zipReader) {
			_zipReader = zipReader;
		}

		public void addCurrentFolderFile(String path) {
			_currentFolderFiles.add(new FileImpl(path, _zipReader));
		}

		public Collection<KBArchive.Folder> getFolders() {
			_saveCurrentFolderState();

			return _folders.values();
		}

		public void setCurrentFolder(String folderPath) {
			if (folderPath == null) {
				folderPath = _ROOT_FOLDER_PATH;
			}

			if (folderPath.equals(_currentFolderPath)) {
				return;
			}

			_saveCurrentFolderState();
			_restoreFolderState(folderPath);
		}

		public void setCurrentFolderIntroFile(String path) {
			_currentFolderIntroFile = new FileImpl(path, _zipReader);
		}

		private void _restoreFolderState(String folderPath) {
			_currentFolderPath = folderPath;

			KBArchive.Folder folder = _folders.get(folderPath);

			if (folder != null) {
				_currentFolderIntroFile = folder.getIntroFile();
				_currentFolderFiles = folder.getFiles();
			}
			else {
				_currentFolderIntroFile = null;
				_currentFolderFiles = new ArrayList<KBArchive.File>();
			}
		}

		private void _saveCurrentFolderState() {
			if ((_currentFolderPath == null) ||
				((_currentFolderIntroFile == null) &&
				 _currentFolderFiles.isEmpty())) {

				return;
			}

			String currentFolderParentPath = PathUtil.getParent(
				_currentFolderPath);

			KBArchive.Folder parentFolder = null;

			if (currentFolderParentPath != null) {
				parentFolder = _folders.get(currentFolderParentPath);
			}

			KBArchive.Folder folder = new FolderImpl(
				_currentFolderPath, parentFolder, _currentFolderIntroFile,
				_currentFolderFiles);

			_folders.put(_currentFolderPath, folder);
		}

		private static final String _ROOT_FOLDER_PATH = StringPool.SLASH;

		private Collection<KBArchive.File> _currentFolderFiles;
		private KBArchive.File _currentFolderIntroFile;
		private String _currentFolderPath;
		private final Map<String, KBArchive.Folder> _folders =
			new TreeMap<String, KBArchive.Folder>();
		private final ZipReader _zipReader;

	}

}