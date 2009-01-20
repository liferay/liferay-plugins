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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="WSRPPortletSoap.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class WSRPPortletSoap implements Serializable {
	public static WSRPPortletSoap toSoapModel(WSRPPortlet model) {
		WSRPPortletSoap soapModel = new WSRPPortletSoap();

		soapModel.setPortletId(model.getPortletId());
		soapModel.setName(model.getName());
		soapModel.setChannelName(model.getChannelName());
		soapModel.setTitle(model.getTitle());
		soapModel.setShortTitle(model.getShortTitle());
		soapModel.setDisplayName(model.getDisplayName());
		soapModel.setKeywords(model.getKeywords());
		soapModel.setStatus(model.getStatus());
		soapModel.setProducerEntityId(model.getProducerEntityId());
		soapModel.setConsumerId(model.getConsumerId());
		soapModel.setPortletHandle(model.getPortletHandle());
		soapModel.setMimeTypes(model.getMimeTypes());

		return soapModel;
	}

	public static WSRPPortletSoap[] toSoapModels(List<WSRPPortlet> models) {
		List<WSRPPortletSoap> soapModels = new ArrayList<WSRPPortletSoap>(models.size());

		for (WSRPPortlet model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new WSRPPortletSoap[soapModels.size()]);
	}

	public WSRPPortletSoap() {
	}

	public long getPrimaryKey() {
		return _portletId;
	}

	public void setPrimaryKey(long pk) {
		setPortletId(pk);
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