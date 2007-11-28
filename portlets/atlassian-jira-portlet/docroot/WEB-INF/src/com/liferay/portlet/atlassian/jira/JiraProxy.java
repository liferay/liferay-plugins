/**
 * Copyright (c) 2000-2007 Liferay, Inc. All rights reserved.
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

package com.liferay.portlet.atlassian.jira;


import com.liferay.portlet.atlassian.jira.model.Assignee;
import com.liferay.portlet.atlassian.jira.model.Component;
import com.liferay.portlet.atlassian.jira.model.Issue;
import com.liferay.portlet.atlassian.jira.model.IssueType;
import com.liferay.portlet.atlassian.jira.model.Priority;
import com.liferay.portlet.atlassian.jira.model.Project;
import com.liferay.portlet.atlassian.jira.model.Version;

import java.util.Collection;

/**
 * Proxy Interface to interact with the Jira bug tracking system.
 *
 * @author Ma Hong Wei
 * @version $Revision$
 */
public interface JiraProxy {

    JiraProxy newInstance();

    /**
     * Set the location of the Jira web service
     *
     * @param url
     * @throws SystemException
     */
    void setServiceURL(String url)
        throws SystemException;

    /**
     * Obtain the components for a given project.
     *
     * @param token
     * @param projectKey
     * @return collection of components
     * @throws IssueTrackerSecurityException
     * @throws SystemException
     */
    Collection<Component> getComponents(String token, String projectKey)
        throws IssueTrackerSecurityException, SystemException;

    /**
     * Log into Jira and retrieve the security token.
     *
     * @param userName
     * @param passwd
     * @return
     */
    String login(String userName, String passwd)
        throws IssueTrackerSecurityException, SystemException;

    /**
     * Obtain all projects authorized by this security token
     *
     * @param securityToken
     * @return
     * @throws IssueTrackerSecurityException
     * @throws SystemException
     */
    Collection<Project> getProjects(String securityToken)
        throws IssueTrackerSecurityException, SystemException;

    /**
     * Obtain all issue types authorized by this security token
     *
     * @param securityToken
     * @return
     * @throws IssueTrackerSecurityException
     * @throws SystemException
     */
    Collection<IssueType> getIssueTypes(String securityToken)
        throws IssueTrackerSecurityException, SystemException;


    /**
     * Obtain all priorities authorized by this security token
     *
     * @param securityToken
     * @return
     * @throws IssueTrackerSecurityException
     * @throws SystemException
     */
    Collection<Priority> getPriorities(String securityToken)
        throws IssueTrackerSecurityException, SystemException;

    /**
     * Obtain all versions for a given project authorized by this security token
     *
     * @param securityToken
     * @param projectKey
     * @return
     * @throws IssueTrackerSecurityException
     * @throws SystemException
     */
    Collection<Version> getVersions(String securityToken, String projectKey)
        throws IssueTrackerSecurityException, SystemException;


    /**
     * TBD Need to determine how to retrieve assignee from Jira
     *
     * @param securityToken
     * @param projectKey
     * @return
     * @throws IssueTrackerSecurityException
     * @throws SystemException
     */
    Collection<Assignee> getAssignees(String securityToken, String projectKey)
        throws IssueTrackerSecurityException, SystemException;

    /**
     * Create a new issue using the security token
     *
     * @param securityToken
     * @param issue
     * @return issue id provided by Jira.
     * @throws IssueTrackerSecurityException
     * @throws SystemException
     */
    String createIssue(String securityToken, Issue issue)
        throws IssueTrackerSecurityException, SystemException;
	
}
