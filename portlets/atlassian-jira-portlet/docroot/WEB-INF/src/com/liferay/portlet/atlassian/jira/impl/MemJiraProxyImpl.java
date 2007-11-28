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

package com.liferay.portlet.atlassian.jira.impl;

import com.liferay.portlet.atlassian.jira.IssueTrackerSecurityException;
import com.liferay.portlet.atlassian.jira.JiraProxy;
import com.liferay.portlet.atlassian.jira.SystemException;
import com.liferay.portlet.atlassian.jira.model.Assignee;
import com.liferay.portlet.atlassian.jira.model.Component;
import com.liferay.portlet.atlassian.jira.model.Issue;
import com.liferay.portlet.atlassian.jira.model.IssueType;
import com.liferay.portlet.atlassian.jira.model.Priority;
import com.liferay.portlet.atlassian.jira.model.Project;
import com.liferay.portlet.atlassian.jira.model.Version;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This is a dummy implementation used for development of the portlet. This should
 * not be used except when there's no real Jira available.
 *
 * @author Michael C. Han
 * @version $Revision$
 */
public class MemJiraProxyImpl implements JiraProxy {
    private String _url;

    public JiraProxy newInstance() {
        return new MemJiraProxyImpl();
    }

    public void setServiceURL(final String url) throws SystemException {
        _url = url;
    }

    public Collection<Component> getComponents(final String token,
                                               final String projectKey)
        throws IssueTrackerSecurityException, SystemException {
        Collection<Component> components = new ArrayList<Component>();
        components.add(new Component("Liferay Plugins", "Liferay Plugins"));
        components.add(new Component("Liferay Portal", "Liferay Portal"));
        components.add(new Component("Liferay IDE", "Liferay IDE"));
        return components;
    }

    public String login(final String userName, final String passwd)
        throws IssueTrackerSecurityException, SystemException {
        return "I_AM_LOGGED_IN";
    }

    public Collection<Project> getProjects(final String securityToken)
        throws IssueTrackerSecurityException, SystemException {
        Collection<Project> projects = new ArrayList<Project>();
        projects.add(new Project("Liferay Portal", "Liferay Portal", "10000"));
        projects.add(new Project("Liferay IDE", "Liferay IDE", "10001"));
        return projects;
    }

    public Collection<IssueType> getIssueTypes(final String securityToken)
        throws IssueTrackerSecurityException, SystemException {
        Collection<IssueType> issueTypes = new ArrayList<IssueType>();
        issueTypes.add(new IssueType("Bug", "Bug"));
        issueTypes.add(new IssueType("New Feature", "New Feature"));
        return issueTypes;
    }

    public Collection<Priority> getPriorities(final String securityToken)
        throws IssueTrackerSecurityException, SystemException {
        Collection<Priority> priorities = new ArrayList<Priority>();
        priorities.add(new Priority("Critical", "Critical"));
        priorities.add(new Priority("Blocker", "Blocker"));
        priorities.add(new Priority("Major", "Major"));
        priorities.add(new Priority("Minor", "Minor"));
        return priorities;
    }

    public Collection<Version> getVersions(final String securityToken,
                                           final String projectKey)
        throws IssueTrackerSecurityException, SystemException {
        Collection<Version> versions = new ArrayList<Version>();
        versions.add(new Version("4.3.0", "4.3.0"));
        versions.add(new Version("4.3.1", "4.3.1"));
        versions.add(new Version("4.3.2", "4.3.2"));
        return versions;
    }

    public Collection<Assignee> getAssignees(final String securityToken,
                                             final String projectKey)
        throws IssueTrackerSecurityException, SystemException {
        Collection<Assignee> assignees = new ArrayList<Assignee>();
        assignees.add(new Assignee("mhan", "mhan@liferay.com",
                                   "Michael C. Han"));
        assignees.add(new Assignee("jcao", "jcao@liferay.com", "Jian Cao"));
        assignees.add(new Assignee("hma", "hma@liferay.com", "Hongwei Ma"));
        return assignees;
    }

    public String createIssue(final String securityToken, final Issue issue)
        throws IssueTrackerSecurityException, SystemException {
        return "Issue102";
    }
}
