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

package com.liferay.kb.knowledgebase.model;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;

/**
 * <a href="KBArticleClp.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public class KBArticleClp extends BaseModelImpl implements KBArticle {
	public KBArticleClp() {
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

	public String getHtmlTitle() {
		return _htmlTitle;
	}

	public void setHtmlTitle(String htmlTitle) {
		_htmlTitle = htmlTitle;
	}

	public double getVersion() {
		return _version;
	}

	public void setVersion(double version) {
		_version = version;
	}

	public boolean getMinorEdit() {
		return _minorEdit;
	}

	public boolean isMinorEdit() {
		return _minorEdit;
	}

	public void setMinorEdit(boolean minorEdit) {
		_minorEdit = minorEdit;
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

	public boolean getTemplate() {
		return _template;
	}

	public boolean isTemplate() {
		return _template;
	}

	public void setTemplate(boolean template) {
		_template = template;
	}

	public boolean getDraft() {
		return _draft;
	}

	public boolean isDraft() {
		return _draft;
	}

	public void setDraft(boolean draft) {
		_draft = draft;
	}

	public long getParentResourcePrimKey() {
		return _parentResourcePrimKey;
	}

	public void setParentResourcePrimKey(long parentResourcePrimKey) {
		_parentResourcePrimKey = parentResourcePrimKey;
	}

	public java.lang.String getUserUuid() {
		throw new UnsupportedOperationException();
	}

	public void setUserUuid(java.lang.String userUuid) {
		throw new UnsupportedOperationException();
	}

	public com.liferay.portal.model.Group getGroup() {
		throw new UnsupportedOperationException();
	}

	public java.lang.String getAttachmentsDir() {
		throw new UnsupportedOperationException();
	}

	public void setAttachmentsDir(java.lang.String attachmentsDir) {
		throw new UnsupportedOperationException();
	}

	public java.lang.String[] getAttachmentsFiles() {
		throw new UnsupportedOperationException();
	}

	public com.liferay.kb.knowledgebase.model.KBArticle getParentArticle(
		long userId) {
		throw new UnsupportedOperationException();
	}

	public java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getParentArticles(
		long userId) {
		throw new UnsupportedOperationException();
	}

	public java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getChildArticles() {
		throw new UnsupportedOperationException();
	}

	public boolean hasParent() {
		throw new UnsupportedOperationException();
	}

	public KBArticle toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			KBArticle model = new KBArticleClp();

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
			model.setHtmlTitle(HtmlUtil.escape(getHtmlTitle()));
			model.setVersion(getVersion());
			model.setMinorEdit(getMinorEdit());
			model.setContent(HtmlUtil.escape(getContent()));
			model.setDescription(HtmlUtil.escape(getDescription()));
			model.setHead(getHead());
			model.setTemplate(getTemplate());
			model.setDraft(getDraft());
			model.setParentResourcePrimKey(getParentResourcePrimKey());

			model = (KBArticle)Proxy.newProxyInstance(KBArticle.class.getClassLoader(),
					new Class[] { KBArticle.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public Object clone() {
		KBArticleClp clone = new KBArticleClp();

		clone.setUuid(getUuid());
		clone.setArticleId(getArticleId());
		clone.setGroupId(getGroupId());
		clone.setResourcePrimKey(getResourcePrimKey());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setModifiedDate(getModifiedDate());
		clone.setTitle(getTitle());
		clone.setHtmlTitle(getHtmlTitle());
		clone.setVersion(getVersion());
		clone.setMinorEdit(getMinorEdit());
		clone.setContent(getContent());
		clone.setDescription(getDescription());
		clone.setHead(getHead());
		clone.setTemplate(getTemplate());
		clone.setDraft(getDraft());
		clone.setParentResourcePrimKey(getParentResourcePrimKey());

		return clone;
	}

	public int compareTo(Object obj) {
		if (obj == null) {
			return -1;
		}

		KBArticleClp kbArticle = (KBArticleClp)obj;

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

		KBArticleClp kbArticle = null;

		try {
			kbArticle = (KBArticleClp)obj;
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
	private String _htmlTitle;
	private double _version;
	private boolean _minorEdit;
	private String _content;
	private String _description;
	private boolean _head;
	private boolean _template;
	private boolean _draft;
	private long _parentResourcePrimKey;
}