/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.knowledgebase.model;

/**
 * <p>
 * This class is a wrapper for {@link Article}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Article
 * @generated
 */
public class ArticleWrapper implements Article {
	public ArticleWrapper(Article article) {
		_article = article;
	}

	/**
	* Gets the primary key of this article.
	*
	* @return the primary key of this article
	*/
	public long getPrimaryKey() {
		return _article.getPrimaryKey();
	}

	/**
	* Sets the primary key of this article
	*
	* @param pk the primary key of this article
	*/
	public void setPrimaryKey(long pk) {
		_article.setPrimaryKey(pk);
	}

	/**
	* Gets the uuid of this article.
	*
	* @return the uuid of this article
	*/
	public java.lang.String getUuid() {
		return _article.getUuid();
	}

	/**
	* Sets the uuid of this article.
	*
	* @param uuid the uuid of this article
	*/
	public void setUuid(java.lang.String uuid) {
		_article.setUuid(uuid);
	}

	/**
	* Gets the article ID of this article.
	*
	* @return the article ID of this article
	*/
	public long getArticleId() {
		return _article.getArticleId();
	}

	/**
	* Sets the article ID of this article.
	*
	* @param articleId the article ID of this article
	*/
	public void setArticleId(long articleId) {
		_article.setArticleId(articleId);
	}

	/**
	* Gets the resource prim key of this article.
	*
	* @return the resource prim key of this article
	*/
	public long getResourcePrimKey() {
		return _article.getResourcePrimKey();
	}

	/**
	* Sets the resource prim key of this article.
	*
	* @param resourcePrimKey the resource prim key of this article
	*/
	public void setResourcePrimKey(long resourcePrimKey) {
		_article.setResourcePrimKey(resourcePrimKey);
	}

	/**
	* Gets the group ID of this article.
	*
	* @return the group ID of this article
	*/
	public long getGroupId() {
		return _article.getGroupId();
	}

	/**
	* Sets the group ID of this article.
	*
	* @param groupId the group ID of this article
	*/
	public void setGroupId(long groupId) {
		_article.setGroupId(groupId);
	}

	/**
	* Gets the company ID of this article.
	*
	* @return the company ID of this article
	*/
	public long getCompanyId() {
		return _article.getCompanyId();
	}

	/**
	* Sets the company ID of this article.
	*
	* @param companyId the company ID of this article
	*/
	public void setCompanyId(long companyId) {
		_article.setCompanyId(companyId);
	}

	/**
	* Gets the user ID of this article.
	*
	* @return the user ID of this article
	*/
	public long getUserId() {
		return _article.getUserId();
	}

	/**
	* Sets the user ID of this article.
	*
	* @param userId the user ID of this article
	*/
	public void setUserId(long userId) {
		_article.setUserId(userId);
	}

	/**
	* Gets the user uuid of this article.
	*
	* @return the user uuid of this article
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _article.getUserUuid();
	}

	/**
	* Sets the user uuid of this article.
	*
	* @param userUuid the user uuid of this article
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_article.setUserUuid(userUuid);
	}

	/**
	* Gets the user name of this article.
	*
	* @return the user name of this article
	*/
	public java.lang.String getUserName() {
		return _article.getUserName();
	}

	/**
	* Sets the user name of this article.
	*
	* @param userName the user name of this article
	*/
	public void setUserName(java.lang.String userName) {
		_article.setUserName(userName);
	}

	/**
	* Gets the create date of this article.
	*
	* @return the create date of this article
	*/
	public java.util.Date getCreateDate() {
		return _article.getCreateDate();
	}

	/**
	* Sets the create date of this article.
	*
	* @param createDate the create date of this article
	*/
	public void setCreateDate(java.util.Date createDate) {
		_article.setCreateDate(createDate);
	}

	/**
	* Gets the modified date of this article.
	*
	* @return the modified date of this article
	*/
	public java.util.Date getModifiedDate() {
		return _article.getModifiedDate();
	}

	/**
	* Sets the modified date of this article.
	*
	* @param modifiedDate the modified date of this article
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_article.setModifiedDate(modifiedDate);
	}

	/**
	* Gets the parent resource prim key of this article.
	*
	* @return the parent resource prim key of this article
	*/
	public long getParentResourcePrimKey() {
		return _article.getParentResourcePrimKey();
	}

	/**
	* Sets the parent resource prim key of this article.
	*
	* @param parentResourcePrimKey the parent resource prim key of this article
	*/
	public void setParentResourcePrimKey(long parentResourcePrimKey) {
		_article.setParentResourcePrimKey(parentResourcePrimKey);
	}

	/**
	* Gets the version of this article.
	*
	* @return the version of this article
	*/
	public int getVersion() {
		return _article.getVersion();
	}

	/**
	* Sets the version of this article.
	*
	* @param version the version of this article
	*/
	public void setVersion(int version) {
		_article.setVersion(version);
	}

	/**
	* Gets the title of this article.
	*
	* @return the title of this article
	*/
	public java.lang.String getTitle() {
		return _article.getTitle();
	}

	/**
	* Sets the title of this article.
	*
	* @param title the title of this article
	*/
	public void setTitle(java.lang.String title) {
		_article.setTitle(title);
	}

	/**
	* Gets the content of this article.
	*
	* @return the content of this article
	*/
	public java.lang.String getContent() {
		return _article.getContent();
	}

	/**
	* Sets the content of this article.
	*
	* @param content the content of this article
	*/
	public void setContent(java.lang.String content) {
		_article.setContent(content);
	}

	/**
	* Gets the description of this article.
	*
	* @return the description of this article
	*/
	public java.lang.String getDescription() {
		return _article.getDescription();
	}

	/**
	* Sets the description of this article.
	*
	* @param description the description of this article
	*/
	public void setDescription(java.lang.String description) {
		_article.setDescription(description);
	}

	/**
	* Gets the priority of this article.
	*
	* @return the priority of this article
	*/
	public int getPriority() {
		return _article.getPriority();
	}

	/**
	* Sets the priority of this article.
	*
	* @param priority the priority of this article
	*/
	public void setPriority(int priority) {
		_article.setPriority(priority);
	}

	/**
	* Gets the latest of this article.
	*
	* @return the latest of this article
	*/
	public int getLatest() {
		return _article.getLatest();
	}

	/**
	* Sets the latest of this article.
	*
	* @param latest the latest of this article
	*/
	public void setLatest(int latest) {
		_article.setLatest(latest);
	}

	/**
	* Gets the status of this article.
	*
	* @return the status of this article
	*/
	public int getStatus() {
		return _article.getStatus();
	}

	/**
	* Sets the status of this article.
	*
	* @param status the status of this article
	*/
	public void setStatus(int status) {
		_article.setStatus(status);
	}

	/**
	* Gets the status by user ID of this article.
	*
	* @return the status by user ID of this article
	*/
	public long getStatusByUserId() {
		return _article.getStatusByUserId();
	}

	/**
	* Sets the status by user ID of this article.
	*
	* @param statusByUserId the status by user ID of this article
	*/
	public void setStatusByUserId(long statusByUserId) {
		_article.setStatusByUserId(statusByUserId);
	}

	/**
	* Gets the status by user uuid of this article.
	*
	* @return the status by user uuid of this article
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getStatusByUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _article.getStatusByUserUuid();
	}

	/**
	* Sets the status by user uuid of this article.
	*
	* @param statusByUserUuid the status by user uuid of this article
	*/
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_article.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Gets the status by user name of this article.
	*
	* @return the status by user name of this article
	*/
	public java.lang.String getStatusByUserName() {
		return _article.getStatusByUserName();
	}

	/**
	* Sets the status by user name of this article.
	*
	* @param statusByUserName the status by user name of this article
	*/
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_article.setStatusByUserName(statusByUserName);
	}

	/**
	* Gets the status date of this article.
	*
	* @return the status date of this article
	*/
	public java.util.Date getStatusDate() {
		return _article.getStatusDate();
	}

	/**
	* Sets the status date of this article.
	*
	* @param statusDate the status date of this article
	*/
	public void setStatusDate(java.util.Date statusDate) {
		_article.setStatusDate(statusDate);
	}

	/**
	* @deprecated {@link #isApproved}
	*/
	public boolean getApproved() {
		return _article.getApproved();
	}

	/**
	* Determines if this article is approved.
	*
	* @return <code>true</code> if this article is approved; <code>false</code> otherwise
	*/
	public boolean isApproved() {
		return _article.isApproved();
	}

	/**
	* Determines if this article is a draft.
	*
	* @return <code>true</code> if this article is a draft; <code>false</code> otherwise
	*/
	public boolean isDraft() {
		return _article.isDraft();
	}

	/**
	* Determines if this article is expired.
	*
	* @return <code>true</code> if this article is expired; <code>false</code> otherwise
	*/
	public boolean isExpired() {
		return _article.isExpired();
	}

	/**
	* Determines if this article is pending.
	*
	* @return <code>true</code> if this article is pending; <code>false</code> otherwise
	*/
	public boolean isPending() {
		return _article.isPending();
	}

	public boolean isNew() {
		return _article.isNew();
	}

	public void setNew(boolean n) {
		_article.setNew(n);
	}

	public boolean isCachedModel() {
		return _article.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_article.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _article.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_article.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _article.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _article.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_article.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return new ArticleWrapper((Article)_article.clone());
	}

	public int compareTo(com.liferay.knowledgebase.model.Article article) {
		return _article.compareTo(article);
	}

	public int hashCode() {
		return _article.hashCode();
	}

	public com.liferay.knowledgebase.model.Article toEscapedModel() {
		return new ArticleWrapper(_article.toEscapedModel());
	}

	public java.lang.String toString() {
		return _article.toString();
	}

	public java.lang.String toXmlString() {
		return _article.toXmlString();
	}

	public java.lang.String getAttachmentsDirName() {
		return _article.getAttachmentsDirName();
	}

	public java.lang.String[] getAttachmentsFileNames()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _article.getAttachmentsFileNames();
	}

	public long getClassPK() {
		return _article.getClassPK();
	}

	public Article getWrappedArticle() {
		return _article;
	}

	private Article _article;
}