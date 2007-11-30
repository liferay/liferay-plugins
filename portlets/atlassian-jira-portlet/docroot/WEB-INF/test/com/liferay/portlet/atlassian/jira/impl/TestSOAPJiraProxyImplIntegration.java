package com.liferay.portlet.atlassian.jira.impl;

import _soapclient.JiraSoapService;
import _soapclient.JiraSoapServiceServiceLocator;
import com.atlassian.jira.rpc.soap.beans.RemoteProject;
import com.liferay.portlet.atlassian.jira.SystemException;
import com.liferay.portlet.atlassian.jira.model.Assignee;
import com.liferay.portlet.atlassian.jira.model.Component;
import com.liferay.portlet.atlassian.jira.model.Issue;
import com.liferay.portlet.atlassian.jira.model.IssueType;
import com.liferay.portlet.atlassian.jira.model.Priority;
import com.liferay.portlet.atlassian.jira.model.Project;
import com.liferay.portlet.atlassian.jira.model.Version;
import junit.framework.TestCase;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author Mahong Wei
 * @version $Revision$
 */
public class TestSOAPJiraProxyImplIntegration extends TestCase {

    public void testSetServiceURL() {
        try {
            _proxy = new SOAPJiraProxyImpl();
            _proxy.setServiceURL("http://192.168.1.114:8090/");
        }
        catch (Exception e) {
            fail("Unable to set the service URL");
            e.printStackTrace();
        }

    }

    public void testGetComponents() {
        try {
            String token = _service.login(USER_NAME, PASSWORD);
            String projectKey = "QA";
            Collection components = _proxy.getComponents(token, projectKey);
            System.out.println("===================================");
            System.out.println("this is testGetComponents"
                + "Components token+QA");
            Iterator listProject = components.iterator();
            while (listProject.hasNext()) {
                Component componentTest = (Component) listProject.next();
                System.out.println("Components;" + componentTest.getName());

            }
            System.out.println("===================================");
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

    }

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

    }

    public void testGetVersions() {
        try {

            String token = _service.login(USER_NAME, PASSWORD);
            Collection versions = _proxy.getVersions(token, "QA");
            System.out.println("===================================");
            System.out.println("this is testGetVersions"
                + "getVersions token+QA");
            Iterator listProject = versions.iterator();
            while (listProject.hasNext()) {
                Version version = listProject.next();
                System.out.println("getVersionName :"
                    + version.getVersionName());
                System.out.println("getVersionId :" + version.getVersionId());

            }
            System.out.println("===================================");
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
    }

    public void testGetPriorities() {
        try {
            String token = _service.login(USER_NAME, PASSWORD);
            Collection projects = _proxy.getPriorities(token);
            System.out.println("===================================");
            System.out.println("this is testGetPriorities"
                + "P:testGetPriorities token");
            Iterator listProject = projects.iterator();
            while (listProject.hasNext()) {
                Priority priority = listProject.next();

                System.out.println("testGetPrioritiesName: "
                    + priority.getPriorityName());
                System.out.println("testGetPrioritiesID:  "
                    + priority.getPriorityId());
            }
            System.out.println("===================================");
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
    }

    public void testGetIssueTypes() {
        try {
            String token = _service.login(USER_NAME, PASSWORD);
            Collection projects = _proxy.getIssueTypes(token);
            System.out.println("===================================");
            System.out.println("this is testGetIssueTypes"
                + "P:testGetIssueTypes token");
            Iterator listIssueTypes = projects.iterator();
            while (listIssueTypes.hasNext()) {
                IssueType issueType = listIssueTypes.next();
                System.out.println("getIssueTyepName:  "
                    + issueType.getIssueTypeName());
                System.out.println("getIssueTypeId:  "
                    + issueType.getIssueTypeID());

            }
            System.out.println("===================================");
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
    }

    public void testGetProjects() {
        try {
            String token = _service.login(USER_NAME, PASSWORD);
            Collection projects = _proxy.getProjects(token);
            System.out.println("===================================");
            System.out.println("this is testGetProjects"
                + "P:testGetPriorities token");
            Iterator listProject = projects.iterator();
            while (listProject.hasNext()) {
                Project priority = listProject.next();
                System.out.println("Get ProjectName:" + priority.getName());
                System.out.println("Get ProjectKey:" + priority.getKey());
                System.out.println("Get ProjectID:" + priority.getId());

            }
            System.out.println("===================================");

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
    }

    public void testGetAssignees() {
        try {
            String token = _proxy.login(USER_NAME, PASSWORD);
            RemoteProject[] projects = _service.getProjects(token);
            if (projects.length == 0) {
                fail("No projects found.  Bad integration configuration");
            }
            Collection assignees = _proxy.getAssignees(token,
                                                                 projects[0].getKey());
            System.out.println("===================================");
            for (Assignee assignee : assignees) {
                System.out.println("GetAssignees is:" + assignee);
            }
            System.out.println("===================================");

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

    }

    public void testValidLogin() {
        try {
            _proxy.login(USER_NAME, PASSWORD);
        }
        catch (SystemException e) {
            fail("Caught system exception");
            e.printStackTrace();
        }
        catch (Exception e) {
            fail("Caught security exception");
            e.printStackTrace();
        }
    }

    /*
      * public void testInvalidLogin() { try { _proxy.login("foo", "my4406395");
      * fail("This"); } catch (SystemException e) { fail("Caught system
      * exception"); e.printStackTrace(); } catch (IssueTrackerSecurityException
      * e) { // this should occur } }
      */
    protected void setUp() throws Exception {
        super.setUp();
        JiraSoapServiceServiceLocator jiraSoapServiceGetter = new JiraSoapServiceServiceLocator();
        jiraSoapServiceGetter.setEndpointAddress("JirasoapserviceV2",
                                                 "http://192.168.1.114:8090/rpc/soap/jirasoapservice-v2");
        _service = jiraSoapServiceGetter.getJirasoapserviceV2();
        _proxy = new SOAPJiraProxyImpl();
        _proxy.setServiceURL("http://192.168.1.114:8090/");

    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    private JiraSoapService _service;
    private SOAPJiraProxyImpl _proxy;
    private static final String PASSWORD = "my4406395";
    private static final String USER_NAME = "saint";

    // Constants for issue creation
    private static final String PROJECT_KEY = "QA";
    private static final String ISSUE_TYPE_ID = "1";
    private static final String SUMMARY_NAME = "123 testversion cctv4 "
        + new Date();
    private static final String PRIORITY_ID = "4";
    private static final String COMPONENT_ID = "10000";

}