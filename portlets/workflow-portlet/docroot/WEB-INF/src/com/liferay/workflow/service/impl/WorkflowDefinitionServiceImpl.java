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

package com.liferay.workflow.service.impl;

import com.liferay.documentlibrary.DuplicateDirectoryException;
import com.liferay.documentlibrary.NoSuchFileException;
import com.liferay.documentlibrary.service.DLServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ResourceLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.workflow.NoSuchDefinitionException;
import com.liferay.workflow.model.WorkflowDefinition;
import com.liferay.workflow.service.base.WorkflowDefinitionServiceBaseImpl;

import java.util.Date;

/**
 * <a href="WorkflowDefinitionServiceImpl.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class WorkflowDefinitionServiceImpl
	extends WorkflowDefinitionServiceBaseImpl {

	public WorkflowDefinition addDefinition(
			String xml, boolean addCommunityPermissions,
			boolean addGuestPermissions)
		throws PortalException, SystemException {

		return addDefinition(
			xml, Boolean.valueOf(addCommunityPermissions),
			Boolean.valueOf(addGuestPermissions), null, null);
	}

	public WorkflowDefinition addDefinition(
			String xml, String[] communityPermissions,
			String[] guestPermissions)
		throws PortalException, SystemException {

		return addDefinition(
			xml, null, null, communityPermissions, guestPermissions);
	}

	public WorkflowDefinition addDefinition(
			String xml, Boolean addCommunityPermissions,
			Boolean addGuestPermissions, String[] communityPermissions,
			String[] guestPermissions)
		throws PortalException, SystemException {

		// Deploy xml

		User user = getUser();

		long definitionId = GetterUtil.getLong(
			workflowComponentService.deploy(xml));

		// File

		long companyId = user.getCompanyId();
		String portletId = CompanyConstants.SYSTEM_STRING;
		long groupId = GroupConstants.DEFAULT_PARENT_GROUP_ID;
		long repositoryId = CompanyConstants.SYSTEM;
		String dirName = "workflow/definitions";
		String fileName = dirName  + "/" + definitionId + ".xml";
		long fileEntryId = 0;
		String properties = StringPool.BLANK;
		Date modifiedDate = new Date();
		ServiceContext serviceContext = new ServiceContext();
		byte[] bytes = xml.getBytes();

		try {
			DLServiceUtil.addDirectory(companyId, repositoryId, dirName);
		}
		catch (DuplicateDirectoryException dde) {
		}

		DLServiceUtil.addFile(
			companyId, portletId, groupId, repositoryId, fileName, fileEntryId,
			properties, modifiedDate, serviceContext, bytes);

		// Resources

		if ((addCommunityPermissions != null) &&
			(addGuestPermissions != null)) {

			addDefinitionResources(
				user, definitionId, addCommunityPermissions.booleanValue(),
				addGuestPermissions.booleanValue());
		}
		else {
			addDefinitionResources(
				user, definitionId, communityPermissions, guestPermissions);
		}

		return getDefinition(definitionId);
	}

	public void addDefinitionResources(
			User user, long definitionId, boolean addCommunityPermissions,
			boolean addGuestPermissions)
		throws PortalException, SystemException {

		ResourceLocalServiceUtil.addResources(
			user.getCompanyId(), 0, user.getUserId(),
			WorkflowDefinition.class.getName(), definitionId, false,
			addCommunityPermissions, addGuestPermissions);
	}

	public void addDefinitionResources(
			User user, long definitionId, String[] communityPermissions,
			String[] guestPermissions)
		throws PortalException, SystemException {

		ResourceLocalServiceUtil.addModelResources(
			user.getCompanyId(), 0, user.getUserId(),
			WorkflowDefinition.class.getName(), definitionId,
			communityPermissions, guestPermissions);
	}

	public WorkflowDefinition getDefinition(long definitionId)
		throws PortalException, SystemException {

		try {
			long companyId = getUser().getCompanyId();
			long repositoryId = CompanyConstants.SYSTEM;
			String dirName = "workflow/definitions";
			String fileName = dirName  + "/" + definitionId + ".xml";

			String xml = new String(
				DLServiceUtil.getFile(companyId, repositoryId, fileName));

			WorkflowDefinition definition =
				(WorkflowDefinition)workflowComponentService.getDefinition(
					definitionId);

			definition.setXml(xml);

			return definition;
		}
		catch (NoSuchFileException nsfe) {
			throw new NoSuchDefinitionException();
		}
	}

}