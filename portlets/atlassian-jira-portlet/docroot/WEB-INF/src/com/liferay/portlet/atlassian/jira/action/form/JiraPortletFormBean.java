package com.liferay.portlet.atlassian.jira.action.form;

public class JiraPortletFormBean {

    private String userName;

    private String password;

    private String issueType;

    private String project;

    private String summary;

    private String priority;

    private String dueDate;

    private String[] component;

    private String[] affectVersions;

    private String[] fixVersions;

    private String assignee;

    private String reporter;

    private String environment;

    private String description;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String[] getComponent() {
        return component;
    }

    public void setComponent(String[] component) {
        this.component = component;
    }

    public String[] getAffectVersions() {
        return affectVersions;
    }

    public void setAffectVersions(String[] affectVersions) {
        this.affectVersions = affectVersions;
    }

    public String[] getFixVersions() {
        return fixVersions;
    }

    public void setFixVersions(String[] fixVersions) {
        this.fixVersions = fixVersions;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
