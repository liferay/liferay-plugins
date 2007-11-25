package com.liferay.portlet.atlassian.jira.impl;

import _soapclient.JiraSoapService;
import _soapclient.JiraSoapServiceServiceLocator;
import com.atlassian.jira.rpc.exception.RemoteAuthenticationException;
import com.atlassian.jira.rpc.exception.RemotePermissionException;
import com.atlassian.jira.rpc.soap.beans.RemoteComponent;
import com.atlassian.jira.rpc.soap.beans.RemoteIssue;
import com.atlassian.jira.rpc.soap.beans.RemoteIssueType;
import com.atlassian.jira.rpc.soap.beans.RemotePriority;
import com.atlassian.jira.rpc.soap.beans.RemoteProject;
import com.atlassian.jira.rpc.soap.beans.RemoteProjectRole;
import com.atlassian.jira.rpc.soap.beans.RemoteProjectRoleActors;
import com.atlassian.jira.rpc.soap.beans.RemoteUser;
import com.atlassian.jira.rpc.soap.beans.RemoteVersion;
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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Implementation of the JiraProxy that calls the Jira SOAP interfaces.
 *
 * @author Mahong Wei
 * @version $Revision$
 */
public class SOAPJiraProxyImpl implements JiraProxy {
    private static final Log log = LogFactory.getLog(SOAPJiraProxyImpl.class);

    public SOAPJiraProxyImpl() {
    }

    public JiraProxy newInstance() {
        return new SOAPJiraProxyImpl();
    }

    public SOAPJiraProxyImpl(String url) throws SystemException {
        setServiceURL(url);
    }

    public void setSoapService(JiraSoapService service) {
        _service = service;
    }

    public void setServiceURL(final String url) throws SystemException {
        final JiraSoapServiceServiceLocator jiraSoapServiceGetter =
            new JiraSoapServiceServiceLocator();
        try {
            jiraSoapServiceGetter.setEndpointAddress(_ADDRESS_KEY,
                                                     url + _ADDRESS_POST_FIX);
            _service = jiraSoapServiceGetter.getJirasoapserviceV2();
        }
        catch (ServiceException e) {
            log.error("Unable to connect to service", e);
            throw new SystemException("Unable to connect to web service: "
                + url, e);
        }
    }

    /**
     * Obtain the components for a given project.
     *
     * @param token
     * @param projectKey
     * @return collection of components
     * @throws com.liferay.portlet.atlassian.jira.IssueTrackerSecurityException
     *
     * @throws com.liferay.portlet.atlassian.jira.SystemException
     *
     */
    public Collection<Component> getComponents(String token, String projectKey)
        throws IssueTrackerSecurityException, SystemException {
        try {
            final RemoteComponent[] remotes =
                _service.getComponents(token,
                                       projectKey);
            final int len = remotes.length;
            final List<Component> components = new ArrayList<Component>(len);
            for (int i = 0; i < len; i++) {

                components.add(new Component(remotes[i].getName(), remotes[i]
                    .getId()));
                // convert RemoteComponent to Component
            }

            return components;
        }
        catch (RemotePermissionException e) {
            throw new IssueTrackerSecurityException(
                "Invalid permissions for: ");
        }
        catch (RemoteAuthenticationException e) {
            throw new IssueTrackerSecurityException(
                "Invalid credentials for: ");
        }
        catch (Exception e) {
            throw new SystemException("System Errors", e);
        }
    }

    /**
     * obtain the Assigness for given project
     *
     * @param securityToken&projectKey
     * @return Collection of Assignee
     * @throws com.liferay.portlet.atlassian.jira.IssueTrackerSecurityException
     *
     * @throws com.liferay.portlet.atlassian.jira.SystemException
     *
     */

    public Collection<Assignee> getAssignees(String securityToken,
                                             String projectKey)
        throws IssueTrackerSecurityException, SystemException {
        try {
            final Set<Assignee> assignees = new HashSet<Assignee>();
            final RemoteProject project = _service.getProjectByKey(
                securityToken, projectKey);
            RemoteProjectRole[] allAvailableRoles = _service
                .getProjectRoles(securityToken);
            final int numRoles = allAvailableRoles.length;
            for (int i = 0; i < numRoles; i++) {
                RemoteProjectRoleActors actors = _service.getProjectRoleActors(
                    securityToken, allAvailableRoles[i], project);
                RemoteUser[] users = actors.getUsers();
                final int numUsers = users.length;
                for (int j = 0; j < numUsers; j++) {
                    final RemoteUser user = users[i];
                    final Assignee assignee = new Assignee(user.getName(), user
                        .getEmail(), user.getFullname());
                    assignees.add(assignee);
                }
            }
            return assignees;

        }
        catch (RemotePermissionException e) {
            throw new IssueTrackerSecurityException("Invalid permissions for: "
                + securityToken);
        }
        catch (RemoteAuthenticationException e) {
            throw new IssueTrackerSecurityException("Invalid credentials for: "
                + securityToken);
        }
        catch (Exception e) {
            throw new SystemException("System Errors", e);
        }

    }

    /**
     * obtain the IssueTypes for given project
     *
     * @param securityToken
     * @return Collection of IssueType
     * @throws com.liferay.portlet.atlassian.jira.IssueTrackerSecurityException
     *
     * @throws com.liferay.portlet.atlassian.jira.SystemException
     *
     */
    public Collection<IssueType> getIssueTypes(String securityToken)
        throws IssueTrackerSecurityException, SystemException {
        try {

            RemoteIssueType[] projectn = _service.getIssueTypes(securityToken);

            final int length = projectn.length;
            final List<IssueType> issueTypes = new ArrayList<IssueType>(length);
            for (int i = 0; i < length; i++) {

                IssueType pr = new IssueType(projectn[i].getName(), projectn[i]
                    .getId());
                issueTypes.add(pr);
            }
            return issueTypes;
        }
        catch (RemotePermissionException e) {
            throw new IssueTrackerSecurityException("Invalid permissions for: "
                + securityToken);
        }
        catch (RemoteAuthenticationException e) {
            throw new IssueTrackerSecurityException("Invalid credentials for: "
                + securityToken);
        }
        catch (Exception e) {
            throw new SystemException("System Errors", e);
        }
        // TODO Auto-generated method stub

    }

    /**
     * obtain the Priorities for given project
     *
     * @param securityToken
     * @return Collection of Priority
     * @throws com.liferay.portlet.atlassian.jira.IssueTrackerSecurityException
     *
     * @throws com.liferay.portlet.atlassian.jira.SystemException
     *
     */
    public Collection<Priority> getPriorities(String securityToken)
        throws IssueTrackerSecurityException, SystemException {
        try {

            RemotePriority[] remotepriority = _service
                .getPriorities(securityToken);
            final int length = remotepriority.length;
            final List<Priority> prioritys = new ArrayList<Priority>(length);
            for (int i = 0; i < length; i++) {
                Priority pr = new Priority(remotepriority[i].getName(),
                                           remotepriority[i].getId());
                prioritys.add(pr);
            }
            return prioritys;
        }
        catch (RemotePermissionException e) {
            throw new IssueTrackerSecurityException("Invalid permissions for: "
                + securityToken);
        }
        catch (RemoteAuthenticationException e) {
            throw new IssueTrackerSecurityException("Invalid credentials for: "
                + securityToken);
        }
        catch (Exception e) {
            throw new SystemException("System Errors", e);
        }

    }

    /**
     * obtain the Projects for given project
     *
     * @param securityToken
     * @return Collection of Project
     * @throws com.liferay.portlet.atlassian.jira.IssueTrackerSecurityException
     *
     * @throws com.liferay.portlet.atlassian.jira.SystemException
     *
     */
    public Collection<Project> getProjects(String securityToken)
        throws IssueTrackerSecurityException, SystemException {

        try {
            RemoteProject[] remoteProjects =
                _service.getProjects(securityToken);
            final int numProjects = remoteProjects.length;
            final List<Project> projects = new ArrayList<Project>(numProjects);
            for (int i = 0; i < numProjects; i++) {
                RemoteProject remoteProj = remoteProjects[i];
                Project pr = new Project(remoteProj.getKey(), remoteProj
                    .getName(), remoteProj.getId());
                projects.add(pr);
            }
            return projects;
        }
        catch (RemotePermissionException e) {
            throw new IssueTrackerSecurityException("Invalid permissions for: "
                + securityToken);
        }
        catch (RemoteAuthenticationException e) {
            throw new IssueTrackerSecurityException("Invalid credentials for: "
                + securityToken);
        }
        catch (Exception e) {
            throw new SystemException("System Errors", e);
        }
    }

    /**
     * obtain the Projects for getVersions
     *
     * @param securityToken &projectKey
     * @return Collection of Version
     * @throws com.liferay.portlet.atlassian.jira.IssueTrackerSecurityException
     *
     * @throws com.liferay.portlet.atlassian.jira.SystemException
     *
     */
    public Collection<Version> getVersions(String securityToken,
                                           String projectKey)
        throws IssueTrackerSecurityException,
               SystemException {
        try {
            List<Version> con = new ArrayList<Version>();
            RemoteVersion[] versions = _service.getVersions(securityToken,
                                                            projectKey);

            final int length = versions.length;
            for (int i = 0; i < length; i++) {
                Version pr = new Version(versions[i].getId(), versions[i]
                    .getName());
                con.add(pr);
            }
            return con;
        }
        catch (RemotePermissionException e) {
            throw new IssueTrackerSecurityException("Invalid permissions for: "
                + securityToken);
        }
        catch (RemoteAuthenticationException e) {
            throw new IssueTrackerSecurityException("Invalid credentials for: "
                + securityToken);
        }
        catch (Exception e) {
            throw new SystemException("System Errors", e);
        }
    }

    /**
     * obtain the Projects for login
     *
     * @param userName
     * @param passwd
     * @return String of Project
     * @throws com.liferay.portlet.atlassian.jira.IssueTrackerSecurityException
     *
     * @throws com.liferay.portlet.atlassian.jira.SystemException
     *
     */
    public String login(String userName, String passwd)
        throws IssueTrackerSecurityException, SystemException {
        try {
            return _service.login(userName, passwd);
        }
        catch (RemotePermissionException e) {
            throw new IssueTrackerSecurityException("Invalid permissions for: "
                + userName);
        }
        catch (RemoteAuthenticationException e) {
            throw new IssueTrackerSecurityException("Invalid credentials for: "
                + userName);
        }
        catch (Exception e) {
            throw new SystemException("System Errors", e);
        }
    }

    /**
     * obtain the Projects for createIssue
     *
     * @param securityToken &issue
     * @throws com.liferay.portlet.atlassian.jira.IssueTrackerSecurityException
     *
     * @throws com.liferay.portlet.atlassian.jira.SystemException
     *
     */
    public String createIssue(String securityToken, Issue issue)
        throws IssueTrackerSecurityException, SystemException {

        try {
            RemoteIssue remoteIssue = new RemoteIssue();
            final String projectKey = issue.getProject().getKey();

            remoteIssue.setProject(projectKey);
            remoteIssue.setType(issue.getIssueType().getIssueTypeID());
            remoteIssue.setSummary(issue.getSummary());
            remoteIssue.setPriority(issue.getPriority().getPriorityId());
            remoteIssue.setDuedate(issue.getDueDate());
            remoteIssue.setAssignee(issue.getAssigneeName());
            remoteIssue.setDescription(issue.getDescription());
            remoteIssue.setEnvironment(issue.getEnvironment());
            remoteIssue.setReporter(issue.getReporterName());
            if (null != issue.getComponents()) {
                final Collection<RemoteComponent> remoteComponents = _convertComponents(
                    securityToken, projectKey, issue.getComponents());
                remoteIssue.setComponents(remoteComponents
                    .toArray(new RemoteComponent[remoteComponents.size()]));
            }
            if (null != issue.getFixedVersions()) {

                final Collection<RemoteVersion> converVersion = _convertVersion(
                    securityToken, projectKey, issue.getFixedVersions());
                RemoteVersion[] remoteVersions = converVersion
                    .toArray(new RemoteVersion[converVersion.size()]);
                remoteIssue.setFixVersions(remoteVersions);
            }
            if (null != issue.getVersions()) {
                final Collection<RemoteVersion> converAffectsVersion = _convertVersion(
                    securityToken, projectKey, issue.getVersions());
                RemoteVersion[] remoteVersions = converAffectsVersion
                    .toArray(new RemoteVersion[converAffectsVersion.size()]);
                remoteIssue.setAffectsVersions(remoteVersions);
            }

            RemoteIssue returnedIssue = _service.createIssue(securityToken,
                                                             remoteIssue);
            String issueKey = returnedIssue.getKey();
            if (log.isDebugEnabled()) {

                log.debug("Successfully created issue " + issueKey);
                log.debug(returnedIssue.getAssignee());
            }
            return issueKey;

        }
        catch (RemotePermissionException e) {
            throw new SecurityException("Invalid permissions for: ");

        }
        catch (RemoteAuthenticationException e) {
            throw new SecurityException("Invalid credentials for: ");

        }
        catch (Exception e) {
            throw new SystemException("System Errors", e);

        }

    }

    /**
     * obtain the Util for _convertComponents
     *
     * @param securityToken &projectKey
     * @return String of Project
     * @throws java.rmi.RemoteException
     */
    private Collection<RemoteComponent> _convertComponents(
        final String securityToken, final String projectKey,
        final Collection<Component> components)
        throws RemoteException {
        final RemoteComponent[] remotes = _service.getComponents(securityToken,
                                                                 projectKey);
        final Map<String, RemoteComponent> remoteMap = new HashMap<String, RemoteComponent>();
        final int len = remotes.length;
        for (int i = 0; i < len; i++) {
            RemoteComponent remoteComp = remotes[i];
            remoteMap.put(remoteComp.getId(), remoteComp);
        }
        final List<RemoteComponent> remoteCompToSet = new ArrayList<RemoteComponent>(
            components.size());

        for (Component component : components) {
            remoteCompToSet.add(remoteMap.get(component.getId()));
        }
        return remoteCompToSet;
    }

    /**
     * obtain the Util for _converVersion
     *
     * @param securityToken for authentication
     * @param projectKey    of project to convert versions for
     * @return String of RemoteVersion
     * @throws java.rmi.RemoteException
     */
    private Collection<RemoteVersion> _convertVersion(
        final String securityToken,
        final String projectKey,
        final Collection<Version> versions)
        throws RemoteException {
        RemoteVersion[] version = _service.getVersions(securityToken,
                                                       projectKey);
        final Map<String, RemoteVersion> remoteMap = new HashMap<String, RemoteVersion>();
        final int len = version.length;
        for (int i = 0; i < len; i++) {
            RemoteVersion remoteComp = version[i];
            remoteMap.put(remoteComp.getId(), remoteComp);
        }
        final List<RemoteVersion> remoteCompToSet = new ArrayList<RemoteVersion>(
            versions.size());

        for (Version versionc : versions) {
            remoteCompToSet.add(remoteMap.get(versionc.getVersionId()));
        }
        return remoteCompToSet;
    }

    private JiraSoapService _service;
    private static final String _ADDRESS_KEY = "JirasoapserviceV2";

    private static final String _ADDRESS_POST_FIX = "rpc/soap/jirasoapservice-v2";

}
