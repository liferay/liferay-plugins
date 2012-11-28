/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.antisamy.hook.sanitizer;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.sanitizer.Sanitizer;
import com.liferay.portal.kernel.sanitizer.SanitizerException;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.Map;

import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.Policy;
import org.owasp.validator.html.PolicyException;

/**
 * @author Zsolt Balogh
 * @author Brian Wing Shun Chan
 */
public class AntiSamySanitizerImpl implements Sanitizer {

	public byte[] sanitize(
		long companyId, long groupId, long userId, String className,
		long classPK, String contentType, String[] modes, byte[] bytes,
		Map<String, Object> options) {

		if (_log.isDebugEnabled()) {
			_log.debug("Sanitizing " + className + "#" + classPK);
		}

		return bytes;
	}

	public void sanitize(
			long companyId, long groupId, long userId, String className,
			long classPK, String contentType, String[] modes,
			InputStream inputStream, OutputStream outputStream,
			Map<String, Object> options)
		throws SanitizerException {

		if (_log.isDebugEnabled()) {
			_log.debug("Sanitizing " + className + "#" + classPK);
		}

		try {
			StreamUtil.transfer(inputStream, outputStream);
		}
		catch (IOException ioe) {
			throw new SanitizerException(ioe);
		}
	}

	public String sanitize(
		long companyId, long groupId, long userId, String className,
		long classPK, String contentType, String[] modes, String s,
		Map<String, Object> options) {

		if (_log.isDebugEnabled()) {
			_log.debug("Sanitizing " + className + "#" + classPK);
		}

		if (!_initialized) {
			_init();
		}

		if (_disabled) {
			return s;
		}

		if (Validator.isNull(contentType) ||
			!contentType.equals(ContentTypes.TEXT_HTML)) {

			return s;
		}

		try {
			AntiSamy antiSamy = new AntiSamy();

			CleanResults cleanResults = antiSamy.scan(s, _policy);

			return cleanResults.getCleanHTML();
		}
		catch (Exception e) {
			_log.error("Unable to sanitize input", e);

			return StringPool.BLANK;
		}
	}

	private void _init() {
		ClassLoader classLoader = getClass().getClassLoader();

		InputStream inputStream = classLoader.getResourceAsStream(
			"sanitizer-configuration.xml");

		if (inputStream == null) {
			_log.error("Configuration could not be found");

			_disabled = true;

			return;
		}

		try {
			_policy = Policy.getInstance(inputStream);
		}
		catch (PolicyException pe) {
			_log.error("Policy could not be initialized", pe);

			_disabled = true;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		AntiSamySanitizerImpl.class);

	private boolean _disabled;
	private boolean _initialized;
	private Policy _policy;

}