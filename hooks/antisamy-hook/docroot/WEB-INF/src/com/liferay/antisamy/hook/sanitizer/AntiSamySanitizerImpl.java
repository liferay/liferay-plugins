/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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
 * <a href="AntiSamySanitizerImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Zsolt Balogh
 * @author Brian Wing Shun Chan
 */
public class AntiSamySanitizerImpl implements Sanitizer {

	public byte[] sanitize(
		long companyId, long groupId, long userId, String className,
		long classPK, String contentType, String[] modes, byte[] byteArray,
		Map<String, Object> options) {

		if (_log.isDebugEnabled()) {
			_log.debug("Sanitizing " + className + "#" + classPK);
		}

		return byteArray;
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

		if (_disabled) {
			return s;
		}

		if (Validator.isNull(contentType) ||
			!contentType.equals(ContentTypes.TEXT_HTML)) {

			return s;
		}

		try {
			if (_policy == null) {
				_init();

				if (_disabled) {
					return s;
				}
			}

			AntiSamy antiSamy = new AntiSamy();
			CleanResults results = antiSamy.scan(s, _policy);
			s = results.getCleanHTML();

			return s;
		} catch (Exception e) {
			_log.error(
				"Error with sanitizing input, returning blank string", e);

			return StringPool.BLANK;
		}
	}

	private void _init() {
		InputStream policyInputStream =
			getClass().getClassLoader().getResourceAsStream(
				"sanitizer-configuration.xml");

		if (policyInputStream == null) {
			_log.error(
				"Couldn't find the policy file sanitizer-configuration.xml, " +
					"AntiSamy filter is disabled");

			_disabled = true;
			return;
		}

		try {
			_policy = Policy.getInstance(policyInputStream);
		}
		catch (PolicyException e) {
			_log.error(
				"AntiSamy policy file had some problems, the filter is " +
					"disabled.", e);

			_disabled = true;
		}
	}

	private static boolean _disabled;
	private static Policy _policy;

	private static Log _log = LogFactoryUtil.getLog(
		AntiSamySanitizerImpl.class);

}