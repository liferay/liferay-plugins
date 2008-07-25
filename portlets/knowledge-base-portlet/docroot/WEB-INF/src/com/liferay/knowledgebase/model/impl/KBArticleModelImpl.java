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

package com.liferay.knowledgebase.model.impl;

import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.model.KBArticleSoap;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="KBArticleModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public class KBArticleModelImpl extends BaseModelImpl {
	public static final String TABLE_NAME = "KB_KBArticle";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", new Integer(Types.VARCHAR) },
			

			{ "articleId", new Integer(Types.BIGINT) },
			

			{ "groupId", new Integer(Types.BIGINT) },
			

			{ "resourcePrimKey", new Integer(Types.BIGINT) },
			

			{ "companyId", new Integer(Types.BIGINT) },
			

			{ "userId", new Integer(Types.BIGINT) },
			

			{ "userName", new Integer(Types.VARCHAR) },
			

			{ "modifiedDate", new Integer(Types.TIMESTAMP) },
			

			{ "title", new Integer(Types.VARCHAR) },
			

			{ "version", new Integer(Types.DOUBLE) },
			

			{ "content", new Integer(Types.CLOB) },
			

			{ "description", new Integer(Types.VARCHAR) },
			

			{ "head", new Integer(Types.BOOLEAN) },
			

			{ "template", new Integer(Types.BOOLEAN) },
			

			{ "parentResourcePrimKey", new Integer(Types.BIGINT) }
		};
	public static final String TABLE_SQL_CREATE = "create table KB_KBArticle (uuid_ VARCHAR(75) null,articleId LONG not null primary key,groupId LONG,resourcePrimKey LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,modifiedDate DATE null,title VARCHAR(100) null,version DOUBLE,content TEXT null,description STRING null,head BOOLEAN,template BOOLEAN,parentResourcePrimKey LONG)";
	public static final String TABLE_SQL_DROP = "drop table KB_KBArticle";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.knowledgebase.model.KBArticle"),
			true);

	public static KBArticle toModel(KBArticleSoap soapModel) {
		KBArticle model = new KBArticleImpl();

		model.setUuid(soapModel.getUuid());
		model.setArticleId(soapModel.getArticleId());
		model.setGroupId(soapModel.getGroupId());
		model.setResourcePrimKey(soapModel.getResourcePrimKey());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setTitle(soapModel.getTitle());
		model.setVersion(soapModel.getVersion());
		model.setContent(soapModel.getContent());
		model.setDescription(soapModel.getDescription());
		model.setHead(soapModel.getHead());
		model.setTemplate(soapModel.getTemplate());
		model.setParentResourcePrimKey(soapModel.getParentResourcePrimKey());

		return model;
	}

	public static List<KBArticle> toModels(KBArticleSoap[] soapModels) {
		List<KBArticle> models = new ArrayList<KBArticle>(soapModels.length);

		for (KBArticleSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.knowledgebase.model.KBArticle"));

	public KBArticleModelImpl() {
	}

	public long getPrimaryKey() {
		return _articleId;
	}

	public void setPrimaryKey(long pk) {
		setArticleId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_articleId);
	}

	public String getUuid() {
		return GetterUtil.getString(_uuid);
	}

	public void setUuid(String uuid) {
		if ((uuid != null) && (uuid != _uuid)) {
			_uuid = uuid;
		}
	}

	public long getArticleId() {
		return _articleId;
	}

	public void setArticleId(long articleId) {
		if (articleId != _articleId) {
			_articleId = articleId;
		}
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		if (groupId != _groupId) {
			_groupId = groupId;
		}
	}

	public long getResourcePrimKey() {
		return _resourcePrimKey;
	}

	public void setResourcePrimKey(long resourcePrimKey) {
		if (resourcePrimKey != _resourcePrimKey) {
			_resourcePrimKey = resourcePrimKey;
		}
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		if (companyId != _companyId) {
			_companyId = companyId;
		}
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		if (userId != _userId) {
			_userId = userId;
		}
	}

	public String getUserName() {
		return GetterUtil.getString(_userName);
	}

	public void setUserName(String userName) {
		if (((userName == null) && (_userName != null)) ||
				((userName != null) && (_userName == null)) ||
				((userName != null) && (_userName != null) &&
				!userName.equals(_userName))) {
			_userName = userName;
		}
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		if (((modifiedDate == null) && (_modifiedDate != null)) ||
				((modifiedDate != null) && (_modifiedDate == null)) ||
				((modifiedDate != null) && (_modifiedDate != null) &&
				!modifiedDate.equals(_modifiedDate))) {
			_modifiedDate = modifiedDate;
		}
	}

	public String getTitle() {
		return GetterUtil.getString(_title);
	}

	public void setTitle(String title) {
		if (((title == null) && (_title != null)) ||
				((title != null) && (_title == null)) ||
				((title != null) && (_title != null) && !title.equals(_title))) {
			_title = title;
		}
	}

	public double getVersion() {
		return _version;
	}

	public void setVersion(double version) {
		if (version != _version) {
			_version = version;
		}
	}

	public String getContent() {
		return GetterUtil.getString(_content);
	}

	public void setContent(String content) {
		if (((content == null) && (_content != null)) ||
				((content != null) && (_content == null)) ||
				((content != null) && (_content != null) &&
				!content.equals(_content))) {
			_content = content;
		}
	}

	public String getDescription() {
		return GetterUtil.getString(_description);
	}

	public void setDescription(String description) {
		if (((description == null) && (_description != null)) ||
				((description != null) && (_description == null)) ||
				((description != null) && (_description != null) &&
				!description.equals(_description))) {
			_description = description;
		}
	}

	public boolean getHead() {
		return _head;
	}

	public boolean isHead() {
		return _head;
	}

	public void setHead(boolean head) {
		if (head != _head) {
			_head = head;
		}
	}

	public boolean getTemplate() {
		return _template;
	}

	public boolean isTemplate() {
		return _template;
	}

	public void setTemplate(boolean template) {
		if (template != _template) {
			_template = template;
		}
	}

	public long getParentResourcePrimKey() {
		return _parentResourcePrimKey;
	}

	public void setParentResourcePrimKey(long parentResourcePrimKey) {
		if (parentResourcePrimKey != _parentResourcePrimKey) {
			_parentResourcePrimKey = parentResourcePrimKey;
		}
	}

	public KBArticle toEscapedModel() {
		if (isEscapedModel()) {
			return (KBArticle)this;
		}
		else {
			KBArticle model = new KBArticleImpl();

			model.setEscapedModel(true);

			model.setUuid(HtmlUtil.escape(getUuid()));
			model.setArticleId(getArticleId());
			model.setGroupId(getGroupId());
			model.setResourcePrimKey(getResourcePrimKey());
			model.setCompanyId(getCompanyId());
			model.setUserId(getUserId());
			model.setUserName(HtmlUtil.escape(getUserName()));
			model.setModifiedDate(getModifiedDate());
			model.setTitle(HtmlUtil.escape(getTitle()));
			model.setVersion(getVersion());
			model.setContent(HtmlUtil.escape(getContent()));
			model.setDescription(HtmlUtil.escape(getDescription()));
			model.setHead(getHead());
			model.setTemplate(getTemplate());
			model.setParentResourcePrimKey(getParentResourcePrimKey());

			model = (KBArticle)Proxy.newProxyInstance(KBArticle.class.getClassLoader(),
					new Class[] { KBArticle.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public Object clone() {
		KBArticleImpl clone = new KBArticleImpl();

		clone.setUuid(getUuid());
		clone.setArticleId(getArticleId());
		clone.setGroupId(getGroupId());
		clone.setResourcePrimKey(getResourcePrimKey());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setModifiedDate(getModifiedDate());
		clone.setTitle(getTitle());
		clone.setVersion(getVersion());
		clone.setContent(getContent());
		clone.setDescription(getDescription());
		clone.setHead(getHead());
		clone.setTemplate(getTemplate());
		clone.setParentResourcePrimKey(getParentResourcePrimKey());

		return clone;
	}

	public int compareTo(Object obj) {
		if (obj == null) {
			return -1;
		}

		KBArticleImpl kbArticle = (KBArticleImpl)obj;

		int value = 0;

		value = getTitle().toLowerCase()
					.compareTo(kbArticle.getTitle().toLowerCase());

		if (value != 0) {
			return value;
		}

		if (getVersion() < kbArticle.getVersion()) {
			value = -1;
		}
		else if (getVersion() > kbArticle.getVersion()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		KBArticleImpl kbArticle = null;

		try {
			kbArticle = (KBArticleImpl)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = kbArticle.getPrimaryKey();

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
	private boolean _template;
	private long _parentResourcePrimKey;
}