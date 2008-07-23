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

package com.liferay.iweb.model.impl;

import com.liferay.iweb.model.SemanticsFile;
import com.liferay.iweb.model.SemanticsFileSoap;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="SemanticsFileModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class SemanticsFileModelImpl extends BaseModelImpl {
	public static final String TABLE_NAME = "IWEB_SemanticsFile";
	public static final Object[][] TABLE_COLUMNS = {
			{ "semanticsName", new Integer(Types.VARCHAR) },
			

			{ "semanticsURI", new Integer(Types.VARCHAR) },
			

			{ "createdInInterestGroup", new Integer(Types.BIGINT) }
		};
	public static final String TABLE_SQL_CREATE = "create table IWEB_SemanticsFile (semanticsName VARCHAR(75) null,semanticsURI VARCHAR(75) not null primary key,createdInInterestGroup LONG)";
	public static final String TABLE_SQL_DROP = "drop table IWEB_SemanticsFile";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.iweb.model.SemanticsFile"),
			true);

	public static SemanticsFile toModel(SemanticsFileSoap soapModel) {
		SemanticsFile model = new SemanticsFileImpl();

		model.setSemanticsName(soapModel.getSemanticsName());
		model.setSemanticsURI(soapModel.getSemanticsURI());
		model.setCreatedInInterestGroup(soapModel.getCreatedInInterestGroup());

		return model;
	}

	public static List<SemanticsFile> toModels(SemanticsFileSoap[] soapModels) {
		List<SemanticsFile> models = new ArrayList<SemanticsFile>(soapModels.length);

		for (SemanticsFileSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final boolean CACHE_ENABLED_INTERESTGROUPS_SEMANTICSFILES = com.liferay.iweb.model.impl.InterestGroupModelImpl.CACHE_ENABLED_INTERESTGROUPS_SEMANTICSFILES;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.iweb.model.SemanticsFile"));

	public SemanticsFileModelImpl() {
	}

	public String getPrimaryKey() {
		return _semanticsURI;
	}

	public void setPrimaryKey(String pk) {
		setSemanticsURI(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return _semanticsURI;
	}

	public String getSemanticsName() {
		return GetterUtil.getString(_semanticsName);
	}

	public void setSemanticsName(String semanticsName) {
		if (((semanticsName == null) && (_semanticsName != null)) ||
				((semanticsName != null) && (_semanticsName == null)) ||
				((semanticsName != null) && (_semanticsName != null) &&
				!semanticsName.equals(_semanticsName))) {
			_semanticsName = semanticsName;
		}
	}

	public String getSemanticsURI() {
		return GetterUtil.getString(_semanticsURI);
	}

	public void setSemanticsURI(String semanticsURI) {
		if (((semanticsURI == null) && (_semanticsURI != null)) ||
				((semanticsURI != null) && (_semanticsURI == null)) ||
				((semanticsURI != null) && (_semanticsURI != null) &&
				!semanticsURI.equals(_semanticsURI))) {
			_semanticsURI = semanticsURI;
		}
	}

	public long getCreatedInInterestGroup() {
		return _createdInInterestGroup;
	}

	public void setCreatedInInterestGroup(long createdInInterestGroup) {
		if (createdInInterestGroup != _createdInInterestGroup) {
			_createdInInterestGroup = createdInInterestGroup;
		}
	}

	public SemanticsFile toEscapedModel() {
		if (isEscapedModel()) {
			return (SemanticsFile)this;
		}
		else {
			SemanticsFile model = new SemanticsFileImpl();

			model.setEscapedModel(true);

			model.setSemanticsName(HtmlUtil.escape(getSemanticsName()));
			model.setSemanticsURI(HtmlUtil.escape(getSemanticsURI()));
			model.setCreatedInInterestGroup(getCreatedInInterestGroup());

			model = (SemanticsFile)Proxy.newProxyInstance(SemanticsFile.class.getClassLoader(),
					new Class[] { SemanticsFile.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public Object clone() {
		SemanticsFileImpl clone = new SemanticsFileImpl();

		clone.setSemanticsName(getSemanticsName());
		clone.setSemanticsURI(getSemanticsURI());
		clone.setCreatedInInterestGroup(getCreatedInInterestGroup());

		return clone;
	}

	public int compareTo(Object obj) {
		if (obj == null) {
			return -1;
		}

		SemanticsFileImpl semanticsFile = (SemanticsFileImpl)obj;

		String pk = semanticsFile.getPrimaryKey();

		return getPrimaryKey().compareTo(pk);
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		SemanticsFileImpl semanticsFile = null;

		try {
			semanticsFile = (SemanticsFileImpl)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		String pk = semanticsFile.getPrimaryKey();

		if (getPrimaryKey().equals(pk)) {
			return true;
		}
		else {
			return false;
		}
	}

	public int hashCode() {
		return getPrimaryKey().hashCode();
	}

	private String _semanticsName;
	private String _semanticsURI;
	private long _createdInInterestGroup;
}