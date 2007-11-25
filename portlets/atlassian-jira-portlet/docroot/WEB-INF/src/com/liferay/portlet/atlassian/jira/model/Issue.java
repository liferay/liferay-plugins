package com.liferay.portlet.atlassian.jira.model;

import java.util.Calendar;
import java.util.Collection;
import java.io.Serializable;

/**
 * @author Mahong Wei
 * @version $Revision$
 */
public class Issue implements Serializable {
    public Issue() {
        
    }
    public Issue(Project project, IssueType issueType, Priority priority,
                 String summary, String reporterName) {
        _project = project;
        _issueType = issueType;
        _priority = priority;
        _summary = summary;
        _reporterName = reporterName;
    }

    public Project getProject() {
        return _project;
    }

    public void setProject(Project project) {
        _project = project;
    }

    public IssueType getIssueType() {
        return _issueType;
    }

    public void setIssueType(IssueType issueType) {
        _issueType = issueType;
    }

    public Priority getPriority() {
        return _priority;
    }

    public void setPriority(Priority priority) {
        _priority = priority;
    }

    public Calendar getDueDate() {
        return _dueDate;
    }

    public void setDueDate(Calendar dueDate) {
        _dueDate = dueDate;
    }

    public Collection<Component> getComponents() {
        return _components;
    }

    public void setComponents(Collection<Component> components) {
        _components = components;
    }

    public Collection<Version> getVersions() {
        return _versions;
    }

    public void setVersions(Collection<Version> versions) {
        _versions = versions;
    }

    public Collection<Version> getFixedVersions() {
        return _fixedVersions;
    }

    public void setFixedVersions(Collection<Version> fixedVersions) {
        _fixedVersions = fixedVersions;
    }

    public String getSummary() {
        return _summary;
    }

    public void setSummary(String summary) {
        _summary = summary;
    }

    public String getAssigneeName() {
        return _assigneeName;
    }

    public void setAssigneeName(String assigneeName) {
        _assigneeName = assigneeName;
    }

    public String getReporterName() {
        return _reporterName;
    }

    public void setReporterName(String reporterName) {
        _reporterName = reporterName;
    }

    public String getEnvironment() {
        return _environment;
    }

    public void setEnvironment(String environment) {
        _environment = environment;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }


    public String toString() {
        return "Issue{" +
            "_project='" + _project+ '\'' +
            ", _issueType=" + _issueType +
            ", _priority=" + _priority +
            ", _dueDate=" + _dueDate +
            ", _components=" + _components +
            ", _versions=" + _versions +
            ", _fixedVersions=" + _fixedVersions +
            ", _summary='" + _summary + '\'' +
            ", _assigneeName='" + _assigneeName + '\'' +
            ", _reporterName='" + _reporterName + '\'' +
            ", _environment='" + _environment + '\'' +
            ", _description='" + _description + '\'' +
            '}';
    }

    private Project _project;
    private IssueType _issueType;
    private Priority _priority;
    private Calendar _dueDate;
    private Collection<Component> _components;
    private Collection<Version> _versions;
    private Collection<Version> _fixedVersions;
    private String _summary;
    private String _assigneeName;
    private String _reporterName;
    private String _environment;
    private String _description;
}