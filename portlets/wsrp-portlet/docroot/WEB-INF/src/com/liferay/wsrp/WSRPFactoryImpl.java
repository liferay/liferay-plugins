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

package com.liferay.wsrp;

import com.liferay.portal.kernel.bean.ContextClassLoaderBeanHandler;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.wsrp.ProfileMapManager;
import com.liferay.portal.wsrp.WSRPFactory;
import com.liferay.wsrp.consumer.invoker.WSRPWindowChannelURLFactory;
import com.liferay.wsrp.consumer.invoker.WSRPWindowRequestReader;
import com.liferay.wsrp.producer.impl.LiferayProfileMapManagerImpl;

import com.sun.portal.container.ChannelMode;
import com.sun.portal.container.ChannelState;
import com.sun.portal.container.ChannelURLFactory;
import com.sun.portal.container.Container;
import com.sun.portal.container.WindowRequestReader;
import com.sun.portal.wsrp.consumer.markup.WSRPContainerFactory;

import java.lang.reflect.Proxy;

import javax.servlet.http.HttpServletRequest;

/**
 * <a href="WSRPFactoryImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 * @author Manish Gupta
 *
 */
public class WSRPFactoryImpl implements WSRPFactory {

	public WSRPFactoryImpl() {
		_container = WSRPContainerFactory.getInstance();
		_container = (Container)newProxyInstance(Container.class, _container);
	}

	public Container getContainer() {
		return _container;
	}

	public ProfileMapManager getProfileMapManager() {
		ProfileMapManager profileMapManager =
			new LiferayProfileMapManagerImpl();

		return (ProfileMapManager)newProxyInstance(
			ProfileMapManager.class, profileMapManager);
	}

	public ChannelURLFactory getWindowChannelURLFactory(
		HttpServletRequest request, Portlet portlet, ChannelState windowState,
		ChannelMode portletMode, long plid) {

		ChannelURLFactory windowChannelURLFactory =
			new WSRPWindowChannelURLFactory(
				request, portlet, windowState, portletMode, plid);

		return (ChannelURLFactory)newProxyInstance(
			ChannelURLFactory.class, windowChannelURLFactory);
	}

	public WindowRequestReader getWindowRequestReader() {
		WindowRequestReader windowRequestReader = new WSRPWindowRequestReader();

		return (WindowRequestReader)newProxyInstance(
			WindowRequestReader.class, windowRequestReader);
	}

	protected Object newProxyInstance(Class clazz, Object object) {
		ClassLoader classLoader = WSRPFactoryImpl.class.getClassLoader();

		return Proxy.newProxyInstance(
			classLoader, new Class[] {clazz},
			new ContextClassLoaderBeanHandler(object, classLoader));
	}

	private Container _container;

}