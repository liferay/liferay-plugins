/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

package com.liferay.portlet.service;

import com.liferay.portlet.service.PropsUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.web.context.support.XmlWebApplicationContext;

/**
 * <a href="LazyClassPathApplicationContext.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * <p>
 * This web application context will first load bean definitions in the
 * contextConfigLocation parameter in web.xml. Then, the context will load bean
 * definitions specified by the property "spring.configs" in portal.properties.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @author Alexander Chow
 *
 */
public class LazyClassPathApplicationContext extends XmlWebApplicationContext {

	protected void loadBeanDefinitions(XmlBeanDefinitionReader reader)
		throws BeansException {

		try {
			super.loadBeanDefinitions(reader);
		}
		catch (Exception e) {
			_log.warn(e);
		}

		reader.setResourceLoader(new DefaultResourceLoader());

		String[] configLocations = PropsUtil.getArray(PropsUtil.SPRING_CONFIGS);

		for (int i = 0; i < configLocations.length; i++) {
			try {
				reader.loadBeanDefinitions(configLocations[i]);
			}
			catch (Exception e) {
				_log.warn(e);
			}
		}
	}

	private static Log _log =
		LogFactory.getLog(LazyClassPathApplicationContext.class);

}