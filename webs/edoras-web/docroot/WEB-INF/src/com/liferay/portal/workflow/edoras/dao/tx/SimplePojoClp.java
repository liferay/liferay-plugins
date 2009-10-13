/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.workflow.edoras.dao.tx;

import com.liferay.portal.SystemException;

import org.springframework.beans.BeanUtils;

/**
 * <a href="SimplePojoClp.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * TODO: add Class-Description here ...
 * </p>
 *
 * @author Micha Kiener
 *
 */
public class SimplePojoClp<T> {

	public SimplePojoClp(
		ClassLoader remoteClassLoader, Class<T> interfaceClass,
		Class<? extends T> localImplementationClass,
		String remoteImplementationClassName)
		throws SystemException {
		_remoteClassLoader = remoteClassLoader;
		_interfaceClass = interfaceClass;
		_localImplementationClass = localImplementationClass;

		try {
			_remoteImplementationClass =
				_remoteClassLoader.loadClass(remoteImplementationClassName);
		}
		catch (ClassNotFoundException e) {
			throw new SystemException("The remote implementation [" +
				remoteImplementationClassName + "] could not be found.", e);
		}
	}

	public T createLocalObject(Object remoteValue) {
		try {
			T localValue = _localImplementationClass.newInstance();
			BeanUtils.copyProperties(
				remoteValue, localValue, _localImplementationClass);
			return localValue;
		}
		catch (Exception e) {
			throw new IllegalArgumentException("Could not serialize object", e);
		}
	}

	public Object createRemoteObject(T value) {
		try {
			Object remoteInstance = _remoteImplementationClass.newInstance();
			BeanUtils.copyProperties(
				value, remoteInstance, _remoteImplementationClass);
			return remoteInstance;
		}
		catch (Exception e) {
			throw new IllegalArgumentException("Could not serialize object", e);
		}
	}

	private Class<T> _interfaceClass;
	private Class<? extends T> _localImplementationClass;
	private ClassLoader _remoteClassLoader;
	private Class<?> _remoteImplementationClass;
}