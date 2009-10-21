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

package com.liferay.portal.workflow.edoras.dao.identity;

import com.liferay.portal.service.persistence.ModelIdentity;
import com.liferay.portal.service.persistence.ModelIdentityImpl;

import java.io.Serializable;

import org.edorasframework.process.core.dao.jpa.DefaultObjectIdentity;
import org.edorasframework.xmlpersister.XmlPersistedAttribute;


/**
 * <a href="ServiceBuilderObjectIdentity.java.html"><b><i>View Source</i></b></a>
 *
 * @author Micha Kiener
 */
public abstract class AbstractServiceBuilderObjectIdentity
	extends DefaultObjectIdentity {

	public AbstractServiceBuilderObjectIdentity() {

	}
	
	public AbstractServiceBuilderObjectIdentity(
		ModelIdentity modelIdentity, String attributeName, Object instance) {
		
		this(modelIdentity.getContextName(), attributeName,
			modelIdentity.getPrimaryKey(), modelIdentity.getModelClassName(),
			instance);
	}

	public AbstractServiceBuilderObjectIdentity(
		String contextName, String attibuteName, Serializable primaryKey,
		String typeName, Object instance) {

		setAttributeName(attibuteName);
		setTypeName(typeName);
		setAttribute(instance);
		setPrimaryKey(getStringFromPrimaryKey(primaryKey));
		
		_contextName = contextName;
	}
	
	public String getContextName() {
		return _contextName;
	}
	
	public ModelIdentity getModelIdentity() {
		ModelIdentityImpl modelIdentity = new ModelIdentityImpl();

		modelIdentity.setContextName(getContextName());
		modelIdentity.setModelClassName(getTypeName());
		modelIdentity.setPrimaryKey(getPrimaryKeyFromString(getPrimaryKey()));

		return modelIdentity;
	}

	protected abstract String getStringFromPrimaryKey(Serializable primaryKey);

	protected abstract Serializable getPrimaryKeyFromString(String primaryKey);
	
    @XmlPersistedAttribute("ctx")
	private String _contextName;
}
