/*
 *
 * Copyright (c) 2000-2007 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
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
 *
 */

package com.liferay.portlet.atlassian.jira.impl;

import _soapclient.JiraSoapService;
import com.atlassian.jira.rpc.exception.RemoteAuthenticationException;
import com.atlassian.jira.rpc.exception.RemotePermissionException;
import com.atlassian.jira.rpc.soap.beans.RemoteComponent;
import com.atlassian.jira.rpc.soap.beans.RemoteIssueType;
import com.atlassian.jira.rpc.soap.beans.RemotePriority;
import com.atlassian.jira.rpc.soap.beans.RemoteProject;
import com.atlassian.jira.rpc.soap.beans.RemoteVersion;
import com.liferay.portlet.atlassian.jira.IssueTrackerSecurityException;
import com.liferay.portlet.atlassian.jira.SystemException;
import com.liferay.portlet.atlassian.jira.model.Component;
import com.liferay.portlet.atlassian.jira.model.IssueType;
import com.liferay.portlet.atlassian.jira.model.Priority;
import com.liferay.portlet.atlassian.jira.model.Project;
import com.liferay.portlet.atlassian.jira.model.Version;
import junit.framework.Assert;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collection;


/**
 * @author Michael C. Han
 * @version $Revision$
 */
@RunWith(JMock.class)
public class TestSOAPJiraProxyImplMock {
    private JUnit4Mockery _testContext = new JUnit4Mockery();
    private SOAPJiraProxyImpl _proxy;
    private JiraSoapService _mockService;

    /*
    public void testCreateIssue() {
        try {
            String token = _service.login(USER_NAME, PASSWORD);
            Priority priority = new Priority(PRIORITY_ID, PRIORITY_ID);
            IssueType issuetype = new IssueType(ISSUE_TYPE_ID, ISSUE_TYPE_ID);
            Project project = new Project("Liferay", "Liferay", "Liferay");
            Issue issue =
                new Issue(project, issuetype, priority, SUMMARY_NAME,
                          USER_NAME);
            Component component = new Component(COMPONENT_ID, COMPONENT_ID);
            List list = new ArrayList();
            list.add(component);
            issue.setComponents(list);

            List list2 = new ArrayList();
            Version fixversion = new Version("10072", "10072");

            Version fixversion2 = new Version("10021", "10021");
            list2.add(fixversion);
            list2.add(fixversion2);
            issue.setFixedVersions(list2);

            Version version = new Version("10031", "10031");
            Version version2 = new Version("10030", "10030");
            List testVersion = new ArrayList();
            testVersion.add(version);
            testVersion.add(version2);
            issue.setVersions(testVersion);
            issue.setAssigneeName(USER_NAME);
            _proxy.createIssue(token, issue);

        }
        catch (RemoteException e) {
            fail("Caught remote error");
            e.printStackTrace();
        }
        catch (SystemException e) {
            fail("Caught system error");
            e.printStackTrace();
        }
        catch (Exception e) {
            fail("Caught security error");
            e.printStackTrace();
        }

    }*/

    @Test
    public void testGetIssueTypes() throws Exception {
        final String token = "token";
        final RemoteIssueType remoteIssueType = new RemoteIssueType();
        remoteIssueType.setId("priorityId");
        remoteIssueType.setName("priorityName");
        _testContext.checking(new Expectations() {
            {
                one(_mockService).getIssueTypes(token);
                RemoteIssueType[] issueTypes =
                    new RemoteIssueType[]{remoteIssueType};
                will(returnValue(issueTypes));
            }

        });
        Collection issueTypes = _proxy.getIssueTypes(token);
        _testContext.assertIsSatisfied();
        Assert.assertEquals(issueTypes.size(), 1);
        IssueType issueType = (IssueType) issueTypes.iterator().next();
        Assert.assertEquals(issueType.getIssueTypeID(), remoteIssueType.getId());
        Assert.assertEquals(issueType.getIssueTypeName(), remoteIssueType.getName());
    }

    @Test(expected = IssueTrackerSecurityException.class)
    public void testGetIssueTypesBadPermissions() throws Exception {
        final String token = "token";
        _testContext.checking(new Expectations() {
            {
                one(_mockService).getIssueTypes(token);
                will(throwException(new RemotePermissionException()));
            }

        });
        _proxy.getIssueTypes(token);
        _testContext.assertIsSatisfied();
    }

    @Test(expected = IssueTrackerSecurityException.class)
    public void testGetIssueTypesBadAuthentication() throws Exception {
        final String token = "token";
        _testContext.checking(new Expectations() {
            {
                one(_mockService).getIssueTypes(token);
                will(throwException(new RemoteAuthenticationException()));
            }

        });
        _proxy.getIssueTypes(token);
        _testContext.assertIsSatisfied();
    }

    @Test(expected = SystemException.class)
    public void testGetIssueTypesSystemFailure() throws Exception {
        final String token = "token";
        _testContext.checking(new Expectations() {
            {
                one(_mockService).getIssueTypes(token);
                will(throwException(new IllegalArgumentException()));
            }

        });
        _proxy.getIssueTypes(token);
        _testContext.assertIsSatisfied();
    }

    
    @Test
    public void testGetPriorities() throws Exception {
        final String token = "token";
        final RemotePriority remotePriority = new RemotePriority();
        remotePriority.setId("priorityId");
        remotePriority.setName("priorityName");
        _testContext.checking(new Expectations() {
            {
                one(_mockService).getPriorities(token);
                RemotePriority[] priorties =
                    new RemotePriority[]{remotePriority};
                will(returnValue(priorties));
            }

        });
        Collection priorities = _proxy.getPriorities(token);
        _testContext.assertIsSatisfied();
        Assert.assertEquals(priorities.size(), 1);
        Priority priority = (Priority) priorities.iterator().next();
        Assert.assertEquals(priority.getPriorityId(), remotePriority.getId());
        Assert.assertEquals(priority.getPriorityName(), remotePriority.getName());
    }

    @Test(expected = IssueTrackerSecurityException.class)
    public void testGetPrioritiesBadPermissions() throws Exception {
        final String token = "token";
        _testContext.checking(new Expectations() {
            {
                one(_mockService).getPriorities(token);
                will(throwException(new RemotePermissionException()));
            }

        });
        _proxy.getPriorities(token);
        _testContext.assertIsSatisfied();
    }

    @Test(expected = IssueTrackerSecurityException.class)
    public void testGetPrioritiesBadAuthentication() throws Exception {
        final String token = "token";
        _testContext.checking(new Expectations() {
            {
                one(_mockService).getPriorities(token);
                will(throwException(new RemoteAuthenticationException()));
            }

        });
        _proxy.getPriorities(token);
        _testContext.assertIsSatisfied();
    }

    @Test(expected = SystemException.class)
    public void testGetPrioritiesSystemFailure() throws Exception {
        final String token = "token";
        _testContext.checking(new Expectations() {
            {
                one(_mockService).getPriorities(token);
                will(throwException(new IllegalArgumentException()));
            }

        });
        _proxy.getPriorities(token);
        _testContext.assertIsSatisfied();
    }

    
    @Test
    public void testGetComponents() throws Exception {
        final String token = "token";
        final String projectKey = "projectKey";
        final RemoteComponent remoteComponent = new RemoteComponent();
        remoteComponent.setId("componentId");
        remoteComponent.setName("componentName");
        _testContext.checking(new Expectations() {
            {
                one(_mockService).getComponents(token, projectKey);
                RemoteComponent[] components =
                    new RemoteComponent[]{remoteComponent};
                will(returnValue(components));
            }

        });
        Collection components = _proxy.getComponents(token, projectKey);
        _testContext.assertIsSatisfied();
        Assert.assertEquals(components.size(), 1);
        Component component = (Component) components.iterator().next();
        Assert.assertEquals(component.getId(), remoteComponent.getId());
        Assert.assertEquals(component.getName(), remoteComponent.getName());
    }

    @Test(expected = IssueTrackerSecurityException.class)
    public void testGetComponentsBadPermissions() throws Exception {
        final String token = "token";
        final String projectKey = "projectKey";
        _testContext.checking(new Expectations() {
            {
                one(_mockService).getComponents(token, projectKey);
                will(throwException(new RemotePermissionException()));
            }

        });
        _proxy.getComponents(token, projectKey);
        _testContext.assertIsSatisfied();
    }

    @Test(expected = IssueTrackerSecurityException.class)
    public void testGetComponentsBadAuthentication() throws Exception {
        final String token = "token";
        final String projectKey = "projectKey";
        _testContext.checking(new Expectations() {
            {
                one(_mockService).getComponents(token, projectKey);
                will(throwException(new RemoteAuthenticationException()));
            }

        });
        _proxy.getComponents(token, projectKey);
        _testContext.assertIsSatisfied();
    }

    @Test(expected = SystemException.class)
    public void testGetComponentsSystemFailure() throws Exception {
        final String token = "token";
        final String projectKey = "projectKey";
        _testContext.checking(new Expectations() {
            {
                one(_mockService).getComponents(token, projectKey);
                will(throwException(new IllegalArgumentException()));
            }

        });
        _proxy.getComponents(token, projectKey);
        _testContext.assertIsSatisfied();
    }

    
    @Test
    public void testGetVersions() throws Exception {
        final String token = "token";
        final String projectKey = "projectKey";
        final RemoteVersion remoteVersion = new RemoteVersion();
        remoteVersion.setId("versionId");
        remoteVersion.setName("versionName");
        _testContext.checking(new Expectations() {
            {
                one(_mockService).getVersions(token, projectKey);
                RemoteVersion[] versions = new RemoteVersion[]{remoteVersion};
                will(returnValue(versions));
            }

        });
        Collection versions = _proxy.getVersions(token, projectKey);
        _testContext.assertIsSatisfied();
        Assert.assertEquals(versions.size(), 1);
        Version version = (Version) versions.iterator().next();
        Assert.assertEquals(version.getVersionId(), remoteVersion.getId());
        Assert.assertEquals(version.getVersionName(), remoteVersion.getName());
    }

    @Test(expected = IssueTrackerSecurityException.class)
    public void testGetVersionsBadPermissions() throws Exception {
        final String token = "token";
        final String projectKey = "projectKey";
        _testContext.checking(new Expectations() {
            {
                one(_mockService).getVersions(token, projectKey);
                will(throwException(new RemotePermissionException()));
            }

        });
        _proxy.getVersions(token, projectKey);
        _testContext.assertIsSatisfied();
    }

    @Test(expected = IssueTrackerSecurityException.class)
    public void testGetVersionsBadAuthentication() throws Exception {
        final String token = "token";
        final String projectKey = "projectKey";
        _testContext.checking(new Expectations() {
            {
                one(_mockService).getVersions(token, projectKey);
                will(throwException(new RemoteAuthenticationException()));
            }

        });
        _proxy.getVersions(token, projectKey);
        _testContext.assertIsSatisfied();
    }

    @Test(expected = SystemException.class)
    public void testGetVersionsSystemFailure() throws Exception {
        final String token = "token";
        final String projectKey = "projectKey";
        _testContext.checking(new Expectations() {
            {
                one(_mockService).getVersions(token, projectKey);
                will(throwException(new IllegalArgumentException()));
            }

        });
        _proxy.getVersions(token, projectKey);
        _testContext.assertIsSatisfied();
    }
    
    @Test
    public void testGetProjects() throws Exception {
        final String token = "token";
        final RemoteProject remoteProject = new RemoteProject();
        remoteProject.setKey("test");
        remoteProject.setName("TestProject");
        remoteProject.setId("testID");
        _testContext.checking(new Expectations() {
            {
                one(_mockService).getProjects(token);
                RemoteProject[] projects = new RemoteProject[]{remoteProject};
                will(returnValue(projects));

            }

        });
        Collection projects = _proxy.getProjects(token);
        _testContext.assertIsSatisfied();
        Assert.assertEquals(projects.size(), 1);
        Project project = (Project) projects.iterator().next();
        Assert.assertEquals(project.getKey(), remoteProject.getKey());
        Assert.assertEquals(project.getName(), remoteProject.getName());
        Assert.assertEquals(project.getId(), remoteProject.getId());
    }

    @Test(expected = IssueTrackerSecurityException.class)
    public void testGetProjectsBadPermissions() throws Exception {
        final String token = "token";
        final RemoteProject remoteProject = new RemoteProject();
        remoteProject.setKey("test");
        remoteProject.setName("TestProject");
        remoteProject.setId("testID");
        _testContext.checking(new Expectations() {
            {
                one(_mockService).getProjects(token);
                will(throwException(new RemotePermissionException()));
            }

        });
        _proxy.getProjects(token);
        _testContext.assertIsSatisfied();
    }

    @Test(expected = IssueTrackerSecurityException.class)
    public void testGetProjectsBadAuthentication() throws Exception {
        final String token = "token";
        final RemoteProject remoteProject = new RemoteProject();
        remoteProject.setKey("test");
        remoteProject.setName("TestProject");
        remoteProject.setId("testID");
        _testContext.checking(new Expectations() {
            {
                one(_mockService).getProjects(token);
                will(throwException(new RemoteAuthenticationException()));
            }

        });
        _proxy.getProjects(token);
        _testContext.assertIsSatisfied();
    }

    @Test(expected = SystemException.class)
    public void testGetProjectsSystemFailure() throws Exception {
        final String token = "token";
        final RemoteProject remoteProject = new RemoteProject();
        remoteProject.setKey("test");
        remoteProject.setName("TestProject");
        remoteProject.setId("testID");
        _testContext.checking(new Expectations() {
            {
                one(_mockService).getProjects(token);
                will(throwException(new IllegalArgumentException()));
            }

        });
        _proxy.getProjects(token);
        _testContext.assertIsSatisfied();
    }

    @Test
    public void testValidLogin() throws Exception {
        final String userName = "test";
        final String password = "password";
        final String token = "token";
        _testContext.checking(new Expectations() {
            {
                one(_mockService).login(userName, password);
                will(returnValue(token));
            }

        });
        _proxy.login(userName, password);
        _testContext.assertIsSatisfied();
    }

    @Test(expected = IssueTrackerSecurityException.class)
    public void testInvalidLoginBadPermissions() throws Exception {
        final String userName = "test";
        final String password = "password";
        _testContext.checking(new Expectations() {
            {
                one(_mockService).login(userName, password);
                will(throwException(new RemotePermissionException()));
            }

        });
        _proxy.login(userName, password);
        _testContext.assertIsSatisfied();

    }

    @Test(expected = IssueTrackerSecurityException.class)
    public void testInvalidLoginBadAuthentication() throws Exception {
        final String userName = "test";
        final String password = "password";
        _testContext.checking(new Expectations() {
            {
                one(_mockService).login(userName, password);
                will(throwException(new RemoteAuthenticationException()));
            }

        });
        _proxy.login(userName, password);
        _testContext.assertIsSatisfied();
    }

    @Test(expected = SystemException.class)
    public void testInvalidLoginSystemError() throws Exception {
        final String userName = "test";
        final String password = "password";
        _testContext.checking(new Expectations() {
            {
                one(_mockService).login(userName, password);
                will(throwException(new IllegalArgumentException()));
            }

        });
        _proxy.login(userName, password);
        _testContext.assertIsSatisfied();
    }

    @Before
    public void setUp() throws Exception {
        _mockService = _testContext.mock(JiraSoapService.class);
        _proxy = new SOAPJiraProxyImpl();
        _proxy.setSoapService(_mockService);
    }

    @After
    public void tearDown() throws Exception {
    }
}
