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

package com.liferay.knowledgebase.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="KBArticleSoap.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public class KBArticleSoap implements Serializable {
	public static KBArticleSoap toSoapModel(KBArticle model) {
		KBArticleSoap soapModel = new KBArticleSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setArticleId(model.getArticleId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setResourcePrimKey(model.getResourcePrimKey());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setTitle(model.getTitle());
		soapModel.setVersion(model.getVersion());
		soapModel.setContent(model.getContent());
		soapModel.setDescription(model.getDescription());
		soapModel.setHead(model.getHead());
		soapModel.setParentResourcePrimKey(model.getParentResourcePrimKey());

		return soapModel;
	}

	public static KBArticleSoap[] toSoapModels(List<KBArticle> models) {
		List<KBArticleSoap> soapModels = new ArrayList<KBArticleSoap>(models.size());

		for (KBArticle model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new KBArticleSoap[soapModels.size()]);
	}

	public KBArticleSoap() {
	}

	public long getPrimaryKey() {
		return _articleId;
	}

	public void setPrimaryKey(long pk) {
		setArticleId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getArticleId() {
		return _articleId;
	}

	public void setArticleId(long articleId) {
		_articleId = articleId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getResourcePrimKey() {
		return _resourcePrimKey;
	}

	public void setResourcePrimKey(long resourcePrimKey) {
		_resourcePrimKey = resourcePrimKey;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public double getVersion() {
		return _version;
	}

	public void setVersion(double version) {
		_version = version;
	}

	public String getContent() {
		return _content;
	}

	public void setContent(String content) {
		_content = content;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public boolean getHead() {
		return _head;
	}

	public boolean isHead() {
		return _head;
	}

	public void setHead(boolean head) {
		_head = head;
	}

	public long getParentResourcePrimKey() {
		return _parentResourcePrimKey;
	}

	public void setParentResourcePrimKey(long parentResourcePrimKey) {
		_parentResourcePrimKey = parentResourcePrimKey;
	}

	private String _uuid;
	private long _articleId;
	private long _groupId;
	private long _resourcePrimKey;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _modifiedDate;
	private String _title;
	private double _version;
	private String _content;
	private String _description;
	private boolean _head;
	private long _parentResourcePrimKey;
}