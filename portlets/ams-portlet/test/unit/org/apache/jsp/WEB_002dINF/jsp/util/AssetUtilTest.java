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

package org.apache.jsp.WEB_002dINF.jsp.util;

import org.apache.jsp.WEB_002dINF.jsp.util.init_jsp.AssetUtil;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Wesley Gong
 */
public class AssetUtilTest {

	@Test
	public void testIsValidSerialNumberInvalid() throws Exception {
		Assert.assertEquals(AssetUtil.isValidSerialNumber("123#abc"), false);
	}

	@Test
	public void testIsValidSerialNumberValid() throws Exception {
		Assert.assertEquals(AssetUtil.isValidSerialNumber("123abc"), true);
	}

}