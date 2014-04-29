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

package com.liferay.rtl.tools;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.rtl.hook.filter.dynamiccss.RTLCSSUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Writer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.tools.ant.DirectoryScanner;

/**
 * @author Eduardo Garcia
 * @see com.liferay.portal.tools.SassToCssBuilder
 */
public class RtlCssBuilder {

	public static void main(String[] args) {
		Map<String, String> arguments = ArgumentsUtil.parseArguments(args);

		List<String> dirNames = new ArrayList<String>();

		String dirName = arguments.get("sass.dir");

		if (Validator.isNotNull(dirName)) {
			dirNames.add(dirName);
		}
		else {
			for (int i = 0;; i++ ) {
				dirName = arguments.get("sass.dir." + i);

				if (Validator.isNotNull(dirName)) {
					dirNames.add(dirName);
				}
				else {
					break;
				}
			}
		}

		String docrootDirName = arguments.get("sass.docroot.dir");

		try {
			new RtlCssBuilder(dirNames, docrootDirName);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public RtlCssBuilder(List<String> dirNames, String docrootDirName)
		throws Exception {

		Class<?> clazz = getClass();

		ClassLoader classLoader = clazz.getClassLoader();

		RTLCSSUtil.init(classLoader);

		for (String dirName : dirNames) {
			_getRtlCache(dirName, docrootDirName);
		}
	}

	private void _getRtlCache(String dirName, String docrootDirName)
		throws Exception {

		DirectoryScanner directoryScanner = new DirectoryScanner();

		String baseDir = docrootDirName.concat(dirName);

		directoryScanner.setBasedir(baseDir);

		directoryScanner.setIncludes(new String[] {"**\\.sass-cache*\\*.css"});

		directoryScanner.scan();

		String[] cacheFileNames = directoryScanner.getIncludedFiles();

		for (String cacheFileName : cacheFileNames) {
			cacheFileName = _normalizeFileName(dirName, cacheFileName);

			if (cacheFileName.contains("_rtl")) {
				continue;
			}

			try {
				long start = System.currentTimeMillis();

				_getRtlCssFileCache(docrootDirName, cacheFileName);

				long end = System.currentTimeMillis();

				System.out.println(
					"Generated RTL cache for " + cacheFileName + " in " +
						(end - start) + " ms");
			}
			catch (Exception e) {
				System.out.println(
					"Unable to generate RTL cache for " + cacheFileName);

				e.printStackTrace();
			}
		}
	}

	private void _getRtlCssFileCache(
			String docrootDirName, String cacheFileName)
		throws Exception {

		File cacheFile = new File(docrootDirName.concat(cacheFileName));

		String cacheContent = _readFile(cacheFile);

		String rtlFileName = _getRtlFileName(cacheFileName);

		File rtlCacheFile = new File (docrootDirName.concat(rtlFileName));

		String rtlCacheContent = RTLCSSUtil.getRtlCss(cacheContent);

		// Append custom CSS for RTL

		if (rtlCacheFile.exists()) {
			rtlCacheContent = rtlCacheContent + _readFile(rtlCacheFile);
		}

		_writeFile(rtlCacheFile, rtlCacheContent);

		rtlCacheFile.setLastModified(cacheFile.lastModified());
	}

	private String _getRtlFileName(String fileName) {
		int pos = fileName.lastIndexOf(StringPool.PERIOD);

		return fileName.substring(0, pos) + "_rtl" + fileName.substring(pos);
	}

	private String _normalizeFileName(String dirName, String fileName) {
		return StringUtil.replace(
			dirName + StringPool.SLASH + fileName,
			new String[] {
				StringPool.BACK_SLASH, StringPool.DOUBLE_SLASH
			},
			new String[] {
				StringPool.SLASH, StringPool.SLASH
			}
		);
	}

	/**
	 * @see com.liferay.portal.util.FileImpl#read(File file)
	 */
	private String _readFile(File file) throws IOException {
		if ((file == null) || !file.exists()) {
			return null;
		}

		RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");

		byte[] bytes = new byte[(int)randomAccessFile.length()];

		randomAccessFile.readFully(bytes);

		randomAccessFile.close();

		if (bytes == null) {
			return null;
		}

		String s = new String(bytes, StringPool.UTF8);

		return StringUtil.replace(
			s, StringPool.RETURN_NEW_LINE, StringPool.NEW_LINE);
	}

	/**
	 * @see com.liferay.portal.util.FileImpl#write(File file, String string)
	 */
	private void _writeFile(File file, String s) throws IOException {
		if (s == null) {
			return;
		}

		_mkdirsParentFile(file);

		Writer writer = new OutputStreamWriter(
			new FileOutputStream(file, false), StringPool.UTF8);

		writer.write(s);

		writer.close();
	}

	/**
	 * @see com.liferay.portal.util.FileImpl#mkdirsParentFile(File file)
	 */
	private void _mkdirsParentFile(File file) {
		File parentFile = file.getParentFile();

		if (parentFile == null) {
			return;
		}

		try {
			if (!parentFile.exists()) {
				parentFile.mkdirs();
			}
		}
		catch (SecurityException se) {

			// We may have the permission to write a specific file without
			// having the permission to check if the parent file exists

		}
	}

}