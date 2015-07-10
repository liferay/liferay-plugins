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

package com.liferay.tika.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.File;
import com.liferay.portal.kernel.util.StringPool;

import java.io.InputStream;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.apache.commons.compress.archivers.zip.UnsupportedZipFeatureException;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.pdfbox.exceptions.CryptographyException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;

/**
 * @author Jonathan McCann
 */
public class TikaFileInvocationHandler implements InvocationHandler {

	public TikaFileInvocationHandler(File file) {
		_file = file;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
		throws Throwable {

		String methodName = method.getName();

		if (methodName.equals("extractText")) {
			if (args.length == 2) {
				return extractText((InputStream)args[0], (String)args[1]);
			}
			else {
				return extractText(
					(InputStream)args[0], (String)args[1], (Integer)args[2]);
			}
		}

		return method.invoke(_file, args);
	}

	protected String extractText(InputStream is, String fileName) {
		return extractText(is, fileName, -1);
	}

	protected String extractText(
		InputStream is, String fileName, int maxStringLength) {

		String text = null;

		try {
			Tika tika = new Tika();

			tika.setMaxStringLength(maxStringLength);

			text = tika.parseToString(is);
		}
		catch (Exception e) {
			Throwable throwable = ExceptionUtils.getRootCause(e);

			if ((throwable instanceof CryptographyException) ||
				(throwable instanceof EncryptedDocumentException) ||
				(throwable instanceof UnsupportedZipFeatureException)) {

				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to extract text from an encrypted file " +
							fileName);
				}
			}
			else if (e instanceof TikaException) {
				if (_log.isWarnEnabled()) {
					_log.warn("Unable to extract text from " + fileName);
				}
			}
			else {
				_log.error(e, e);
			}
		}

		if (_log.isInfoEnabled()) {
			if (text == null) {
				_log.info("Text extraction failed for " + fileName);
			}
			else {
				_log.info("Text was extracted for " + fileName);
			}
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Extractor returned text:\n\n" + text);
		}

		if (text == null) {
			text = StringPool.BLANK;
		}

		return text;
	}

	private static Log _log = LogFactoryUtil.getLog(
		TikaFileInvocationHandler.class);

	private File _file;

}