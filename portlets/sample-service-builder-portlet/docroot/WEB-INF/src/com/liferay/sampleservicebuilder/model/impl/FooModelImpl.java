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

package com.liferay.sampleservicebuilder.model.impl;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import com.liferay.sampleservicebuilder.model.Foo;
import com.liferay.sampleservicebuilder.model.FooSoap;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="FooModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class FooModelImpl extends BaseModelImpl {
	public static final String TABLE_NAME = "SSB_Foo";
	public static final Object[][] TABLE_COLUMNS = {
			{ "fooId", new Integer(Types.BIGINT) },
			

			{ "field1", new Integer(Types.VARCHAR) },
			

			{ "field2", new Integer(Types.BOOLEAN) },
			

			{ "field3", new Integer(Types.INTEGER) },
			

			{ "field4", new Integer(Types.TIMESTAMP) },
			

			{ "field5", new Integer(Types.VARCHAR) }
		};
	public static final String TABLE_SQL_CREATE = "create table SSB_Foo (fooId LONG not null primary key,field1 VARCHAR(75) null,field2 BOOLEAN,field3 INTEGER,field4 DATE null,field5 VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table SSB_Foo";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.sampleservicebuilder.model.Foo"),
			true);

	public static Foo toModel(FooSoap soapModel) {
		Foo model = new FooImpl();

		model.setFooId(soapModel.getFooId());
		model.setField1(soapModel.getField1());
		model.setField2(soapModel.getField2());
		model.setField3(soapModel.getField3());
		model.setField4(soapModel.getField4());
		model.setField5(soapModel.getField5());

		return model;
	}

	public static List<Foo> toModels(FooSoap[] soapModels) {
		List<Foo> models = new ArrayList<Foo>(soapModels.length);

		for (FooSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.sampleservicebuilder.model.Foo"));

	public FooModelImpl() {
	}

	public long getPrimaryKey() {
		return _fooId;
	}

	public void setPrimaryKey(long pk) {
		setFooId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_fooId);
	}

	public long getFooId() {
		return _fooId;
	}

	public void setFooId(long fooId) {
		if (fooId != _fooId) {
			_fooId = fooId;
		}
	}

	public String getField1() {
		return GetterUtil.getString(_field1);
	}

	public void setField1(String field1) {
		if (((field1 == null) && (_field1 != null)) ||
				((field1 != null) && (_field1 == null)) ||
				((field1 != null) && (_field1 != null) &&
				!field1.equals(_field1))) {
			_field1 = field1;
		}
	}

	public boolean getField2() {
		return _field2;
	}

	public boolean isField2() {
		return _field2;
	}

	public void setField2(boolean field2) {
		if (field2 != _field2) {
			_field2 = field2;
		}
	}

	public int getField3() {
		return _field3;
	}

	public void setField3(int field3) {
		if (field3 != _field3) {
			_field3 = field3;
		}
	}

	public Date getField4() {
		return _field4;
	}

	public void setField4(Date field4) {
		if (((field4 == null) && (_field4 != null)) ||
				((field4 != null) && (_field4 == null)) ||
				((field4 != null) && (_field4 != null) &&
				!field4.equals(_field4))) {
			_field4 = field4;
		}
	}

	public String getField5() {
		return GetterUtil.getString(_field5);
	}

	public void setField5(String field5) {
		if (((field5 == null) && (_field5 != null)) ||
				((field5 != null) && (_field5 == null)) ||
				((field5 != null) && (_field5 != null) &&
				!field5.equals(_field5))) {
			_field5 = field5;
		}
	}

	public Foo toEscapedModel() {
		if (isEscapedModel()) {
			return (Foo)this;
		}
		else {
			Foo model = new FooImpl();

			model.setEscapedModel(true);

			model.setFooId(getFooId());
			model.setField1(HtmlUtil.escape(getField1()));
			model.setField2(getField2());
			model.setField3(getField3());
			model.setField4(getField4());
			model.setField5(HtmlUtil.escape(getField5()));

			model = (Foo)Proxy.newProxyInstance(Foo.class.getClassLoader(),
					new Class[] { Foo.class }, new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public Object clone() {
		FooImpl clone = new FooImpl();

		clone.setFooId(getFooId());
		clone.setField1(getField1());
		clone.setField2(getField2());
		clone.setField3(getField3());
		clone.setField4(getField4());
		clone.setField5(getField5());

		return clone;
	}

	public int compareTo(Object obj) {
		if (obj == null) {
			return -1;
		}

		FooImpl foo = (FooImpl)obj;

		int value = 0;

		value = getField1().compareTo(foo.getField1());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		FooImpl foo = null;

		try {
			foo = (FooImpl)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = foo.getPrimaryKey();

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

	private long _fooId;
	private String _field1;
	private boolean _field2;
	private int _field3;
	private Date _field4;
	private String _field5;
}