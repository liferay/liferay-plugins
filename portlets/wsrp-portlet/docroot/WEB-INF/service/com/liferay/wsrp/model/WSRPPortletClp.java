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

package com.liferay.wsrp.model;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

/**
 * <a href="WSRPPortletClp.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class WSRPPortletClp extends BaseModelImpl<WSRPPortlet>
	implements WSRPPortlet {
	public WSRPPortletClp() {
	}

	public long getPrimaryKey() {
		return _portletId;
	}

	public void setPrimaryKey(long pk) {
		setPortletId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_portletId);
	}

	public long getPortletId() {
		return _portletId;
	}

	public void setPortletId(long portletId) {
		_portletId = portletId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getChannelName() {
		return _channelName;
	}

	public void setChannelName(String channelName) {
		_channelName = channelName;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getShortTitle() {
		return _shortTitle;
	}

	public void setShortTitle(String shortTitle) {
		_shortTitle = shortTitle;
	}

	public String getDisplayName() {
		return _displayName;
	}

	public void setDisplayName(String displayName) {
		_displayName = displayName;
	}

	public String getKeywords() {
		return _keywords;
	}

	public void setKeywords(String keywords) {
		_keywords = keywords;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public String getProducerEntityId() {
		return _producerEntityId;
	}

	public void setProducerEntityId(String producerEntityId) {
		_producerEntityId = producerEntityId;
	}

	public String getConsumerId() {
		return _consumerId;
	}

	public void setConsumerId(String consumerId) {
		_consumerId = consumerId;
	}

	public String getPortletHandle() {
		return _portletHandle;
	}

	public void setPortletHandle(String portletHandle) {
		_portletHandle = portletHandle;
	}

	public String getMimeTypes() {
		return _mimeTypes;
	}

	public void setMimeTypes(String mimeTypes) {
		_mimeTypes = mimeTypes;
	}

	public WSRPPortlet toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			WSRPPortlet model = new WSRPPortletClp();

			model.setEscapedModel(true);

			model.setPortletId(getPortletId());
			model.setName(HtmlUtil.escape(getName()));
			model.setChannelName(HtmlUtil.escape(getChannelName()));
			model.setTitle(HtmlUtil.escape(getTitle()));
			model.setShortTitle(HtmlUtil.escape(getShortTitle()));
			model.setDisplayName(HtmlUtil.escape(getDisplayName()));
			model.setKeywords(HtmlUtil.escape(getKeywords()));
			model.setStatus(getStatus());
			model.setProducerEntityId(HtmlUtil.escape(getProducerEntityId()));
			model.setConsumerId(HtmlUtil.escape(getConsumerId()));
			model.setPortletHandle(HtmlUtil.escape(getPortletHandle()));
			model.setMimeTypes(HtmlUtil.escape(getMimeTypes()));

			model = (WSRPPortlet)Proxy.newProxyInstance(WSRPPortlet.class.getClassLoader(),
					new Class[] { WSRPPortlet.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public Object clone() {
		WSRPPortletClp clone = new WSRPPortletClp();

		clone.setPortletId(getPortletId());
		clone.setName(getName());
		clone.setChannelName(getChannelName());
		clone.setTitle(getTitle());
		clone.setShortTitle(getShortTitle());
		clone.setDisplayName(getDisplayName());
		clone.setKeywords(getKeywords());
		clone.setStatus(getStatus());
		clone.setProducerEntityId(getProducerEntityId());
		clone.setConsumerId(getConsumerId());
		clone.setPortletHandle(getPortletHandle());
		clone.setMimeTypes(getMimeTypes());

		return clone;
	}

	public int compareTo(WSRPPortlet wsrpPortlet) {
		long pk = wsrpPortlet.getPrimaryKey();

		if (getPrimaryKey() < pk) {
			return -1;
		}
		else if (getPrimaryKey() > pk) {
			return 1;
		}
		else {
			return 0;
		}
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		WSRPPortletClp wsrpPortlet = null;

		try {
			wsrpPortlet = (WSRPPortletClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = wsrpPortlet.getPrimaryKey();

		if (getPrimaryKey() == pk) {
			return true;
		}
		else {
			return false;
		}
	}

	public int hashCode() {
		return (int)getPrimaryKey();
	}

	private long _portletId;
	private String _name;
	private String _channelName;
	private String _title;
	private String _shortTitle;
	private String _displayName;
	private String _keywords;
	private int _status;
	private String _producerEntityId;
	private String _consumerId;
	private String _portletHandle;
	private String _mimeTypes;
}