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

package com.liferay.portal.workflow.edoras.dao;

import com.liferay.portal.SystemException;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.workflow.edoras.NoSuchWorkflowDefinitionException;
import com.liferay.portal.workflow.edoras.model.WorkflowDefinition;
import com.liferay.portal.workflow.edoras.service.persistence.WorkflowDefinitionUtil;

import java.util.List;

import org.edorasframework.process.api.ex.ProcessException;
import org.edorasframework.process.api.service.ProcessModelDefinition;
import org.edorasframework.process.api.service.ProcessServiceDao;

/**
 * <a href="WorkflowDefinitionDao.java.html"><b><i>View Source</i></b></a>
 *
 * @author Micha Kiener
 */
public class WorkflowDefinitionDao implements ProcessServiceDao {

	public void clearCache() {

	}

	public <T> void delete(T entity) {
		long id = 0;
		try {
			id = ((WorkflowDefinition) entity).getWorkflowDefinitionId();
			WorkflowDefinitionUtil.remove(id);
		}
		catch (NoSuchWorkflowDefinitionException e) {
			throw new ProcessException(
				"Could not delete workflow definition with id [" + id +
					"]", e);
		}
		catch (SystemException e) {
			throw new ProcessException(
				"Could not delete workflow definition with id [" + id +
					"]", e);
		}
	}

	public <T> T find(Class<T> clazz, Object identity) {
		long id = (Long)identity;
		try {
			return (T) WorkflowDefinitionUtil.findByPrimaryKey(id);
		}
		catch (NoSuchWorkflowDefinitionException e) {
			throw new ProcessException(
				"Could not load workflow definition with id [" + id + "]", e);
		}
		catch (SystemException e) {
			throw new ProcessException(
				"Could not load workflow definition with id [" + id + "]", e);
		}
	}

	public <T> T find(T entity, Object identity) {
		return (T) find(WorkflowDefinition.class, identity);
	}

	public ProcessModelDefinition findModelDefinition(
		String modelId, int modelVersion, Long tenantId) {
		long companyId =
			(tenantId == null ? CompanyConstants.SYSTEM : tenantId.longValue());
		try {
			return (ProcessModelDefinition) WorkflowDefinitionUtil.findByC_N_V(
				companyId, modelId, modelVersion);
		} catch (SystemException e) {
			throw new ProcessException("Could not find workflow definition [" +
				modelId + " / " +
					modelVersion + "].", e);
		}
		catch (NoSuchWorkflowDefinitionException e) {
			throw new ProcessException(
				"Could not find workflow definition [" +
				modelId + " / " + modelVersion + "].", e);
		}
	}

	public List loadModelDefinitions(
		Long tenantId) {
		try {
			if (tenantId == null) {
				return WorkflowDefinitionUtil.findAll();
			}
			else {
				return WorkflowDefinitionUtil.findByC(tenantId.longValue());
			}
		}
		catch (SystemException e) {
			throw new ProcessException(
				"Could not fetch workflow definitions.", e);
		}
	}

	public <T> T merge(T entity) {
		return entity;
	}

	public <T> void refresh(T entity) {
	}

	public void reload(Object entity) {
	}

	public <T> void save(T entity) {
		try {
			WorkflowDefinitionUtil.update((WorkflowDefinition) entity);
		}
		catch (SystemException e) {
			throw new ProcessException(
				"Could not save / update workflow definition", e);
		}
	}

}