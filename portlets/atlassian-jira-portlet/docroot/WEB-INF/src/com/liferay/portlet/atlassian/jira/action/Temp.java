package com.liferay.portlet.atlassian.jira.action;

/**
 * @author Michael C. Han
 * @version $Revision$
 */
public class Temp {
    /*private static final String _ISSUE_TYPES_KEY = "issueTypes";

    private static final String _PROJECTS_KEY = "projects";

    private static final String _USER_NAME_PARAM = "userName";

    private static final String _PROJECT_KEY = "projectKey";

    private static final String _PROJECT_NAME = "projectName";

    private static final String _ISSUE_TYPE = "issueType";

    private static final String _ISSUE_TYPE_ID = "issueTypeID";

    private static final String _PRIORITY = "priority";

    private static final String _COMPONENT = "component";

    private static final String _VERSION = "version";

    private static final String _ASSIGNEE = "assignee";

    private static final String _USER_NAME = "userName";

    private static final String _JIRA_URL = "jiraUrl";

    private static final String _SUCCESS_ID = "successId";

    // private FormProcessingStrategy _page1Processor;
    //
    // public CreateTicketPortletAction() {
    // super();
    // _page1Processor = new Step1FormProcessingStrategy();
    // }

    protected Map referenceData(PortletRequest request, Object command,
                                Errors errors, int page) throws Exception {

        JiraPortletFormBean jiraPortletFormBean = (JiraPortletFormBean) command;

        request.setAttribute("formBean", jiraPortletFormBean);

        return referenceData(request, page);

    }

    protected Map referenceData(PortletRequest request, int page)
        throws Exception {

        PortletPreferences prefs = request.getPreferences();
        String jiraUrl = prefs.getValue("jiraUrl", "");

        if (StringUtil.isEmpty(jiraUrl)) {
            throw new IllegalStateException("Please input jira Url!");
        }

        try {
            _jiraProxy.setServiceURL(jiraUrl);
        }
        catch (SystemException e) {
            // TODO Auto-generated catch block
            throw new IllegalStateException(
                "Please enter the correct jira Url!");
        }

        JiraPortletFormBean jiraPortletFormBean = (JiraPortletFormBean) request
            .getAttribute("formBean");

        Map data = null;

        if (page == 1) {

            String userName = jiraPortletFormBean.getUserName();

            String password = jiraPortletFormBean.getPassword();

            if (userName != null && password != null) {
                data = new HashMap();
                final String securityToken = _jiraProxy.login(userName,
                    password);

                request.getPortletSession().setAttribute("securityToken",
                    securityToken);

                request.getPortletSession().setAttribute(_USER_NAME_PARAM,
                    userName);

                final Collection<Project> projects = _jiraProxy
                    .getProjects(securityToken);

                final Collection<IssueType> issueTypes = _jiraProxy
                    .getIssueTypes(securityToken);

                if (issueTypes != null && issueTypes.size() > 0)
                    data.put(_ISSUE_TYPES_KEY, issueTypes);
                if (projects != null && projects.size() > 0)
                    data.put(_PROJECTS_KEY, projects);
            }

        }

        if (page == 2) {

            String securityToken = (String) request
                .getPortletSession()
                .getAttribute(
                    JiraPortletConstants.SECURITY_TOKEN_KEY);

            data = new HashMap();
            if (StringUtil.isEmpty(securityToken)) {
                throw new IllegalArgumentException("Invalid security token");
            }

            String project = jiraPortletFormBean.getProject();

            if (StringUtil.isEmpty(project)) {
                throw new IllegalArgumentException("Invalid project");
            }
            int projectIndex = project.indexOf(",");
            String projectKey = project.substring(0, projectIndex);
            String projectName = project.substring(projectIndex + 1,
                project.length());

            request.getPortletSession().setAttribute(_PROJECT_KEY,
                projectKey);
            request.getPortletSession().setAttribute(_PROJECT_NAME,
                projectName);

            Collection<Priority> _priority = _jiraProxy
                .getPriorities(securityToken);

            Collection<Component> _component = _jiraProxy.getComponents(
                securityToken, projectKey);

            Collection<Version> _version = _jiraProxy.getVersions(
                securityToken, projectKey);

            Collection<Assignee> _assignee = _jiraProxy.getAssignees(
                securityToken, projectKey);

            String issueTypeAndId = jiraPortletFormBean.getIssueType();
            if (StringUtil.isEmpty(issueTypeAndId)) {
                throw new IllegalStateException(
                    "issueTypeAndId should not be empty!");
            }
            int issueIndex = issueTypeAndId.indexOf(",");
            String issueTypeID = issueTypeAndId.substring(0, issueIndex);
            String issueType = issueTypeAndId.substring(issueIndex + 1,
                issueTypeAndId.length());

            request.getPortletSession().setAttribute(_ISSUE_TYPE_ID,
                issueTypeID);
            String userName = jiraPortletFormBean.getUserName();
            data.put(_PROJECT_NAME, projectName);
            data.put(_ISSUE_TYPE, issueType);
            data.put(_PRIORITY, _priority);
            data.put(_COMPONENT, _component);
            data.put(_VERSION, _version);
            data.put(_ASSIGNEE, _assignee);
            data.put(_USER_NAME, userName);


        }

        return data;
    }

    *//**
 * Registers a PropertyEditor with the data binder for handling Dates using
 * the format as currently specified in the PortletPreferences.
 *//*
    protected void initBinder(PortletRequest request,
                              PortletRequestDataBinder binder) throws Exception {

        binder.registerCustomEditor(Collection.class,
            new CustomCollectionEditor(Collection.class, true));

        binder.setAllowedFields(new String[]{"userName", "password",
            "issueType", "project", "summary", "priority",
            "dueDate", "component", "affectVersions",
            "fixVersions", "assignee", "reporter", "environment",
            "description"});
    }

    protected void processFinish(ActionRequest request,
                                 ActionResponse response, Object command,
                                 BindException errors) throws Exception {
    }

    protected ModelAndView renderFinish(RenderRequest request,
                                        RenderResponse response, Object command,
                                        BindException errors) throws Exception {

        JiraPortletFormBean issueFormBean = (JiraPortletFormBean) command;

        String issueTypeAndId = issueFormBean.getIssueType();
        if (StringUtil.isEmpty(issueTypeAndId)) {
            throw new IllegalStateException(
                "issueTypeAndId should not be empty!");
        }
        int issueIndex = issueTypeAndId.indexOf(",");
        String issueTypeID = issueTypeAndId.substring(0, issueIndex);

        IssueType issueType = new IssueType();

        issueType.setIssueTypeID(issueTypeID);

        String priority = issueFormBean.getPriority();

        Priority priorityModel = new Priority();
        priorityModel.set_priorityId(priority);

        String summary = issueFormBean.getSummary();

        String reporterName = ""*//* issueFormBean.getReporter() *//*;

        String securityToken = (String) request.getPortletSession()
            .getAttribute(JiraPortletConstants.SECURITY_TOKEN_KEY);

        String projectKey = (String) request.getPortletSession().getAttribute(
            _PROJECT_KEY);

        String assignee = issueFormBean.getAssignee();

        String environment = issueFormBean.getEnvironment();

        String description = issueFormBean.getDescription();

        String[] componentArray = issueFormBean.getComponent();

        String[] affectVersionArray = issueFormBean.getAffectVersions();

        String[] fixVersionsArray = issueFormBean.getFixVersions();

        String dueDate = issueFormBean.getDueDate();

        List<Component> components = new ArrayList<Component>();

        if (componentArray != null && componentArray.length != 0) {
            for (int i = 0; i < componentArray.length; i++) {
                components.add(new Component(componentArray[i]));
            }

        }

        List<Version> affectVersions = new ArrayList<Version>();
        if (!(affectVersionArray.length == 0)) {
            for (int i = 0; i < affectVersionArray.length; i++) {
                affectVersions.add(new Version(affectVersionArray[i]));
            }

        }

        List<Version> fixVersions = new ArrayList<Version>();
        if (!(fixVersionsArray.length == 0)) {
            for (int i = 0; i < fixVersionsArray.length; i++) {
                fixVersions.add(new Version(fixVersionsArray[i]));
            }

        }

        Issue issue = new Issue(projectKey, issueType, priorityModel, summary,
            reporterName);

        if (components != null && components.size() != 0) {

            issue.setComponents(components);
        }

        if (affectVersions != null && affectVersions.size() != 0) {

            issue.setVersions(affectVersions);
        }

        if (fixVersions != null && fixVersions.size() != 0) {

            issue.setFixedVersions(fixVersions);
        }

        if (reporterName != null && !reporterName.equals("")) {
            issue.setReporterName(reporterName);
        }

        if (assignee != null && !(assignee.equals(""))) {
            issue.setAssigneeName(assignee);
        }

        if (environment != null && !(environment.equals(""))) {
            issue.setEnvironment(environment);
        }

        if (description != null && !(description.equals(""))) {
            issue.setDescription(description);
        }
        Calendar cal;
        if (description != null && !(description.equals(""))) {

            cal = Calendar.getInstance();

            String[] a = dueDate.split("-");
            cal.set(new Integer(a[0]), new Integer(a[1]), new Integer(a[2]));
            issue.setDueDate(cal);
        }
        String successId = "";

        try {

            successId = _jiraProxy.createIssue(securityToken, issue);

        }
        catch (IssueTrackerSecurityException e) {
            throw new IssueTrackerSecurityException(
                "User or Password is not currect!");
        }
        System.out.println("successId" + successId);

        ModelAndView jiraSuccess = new ModelAndView("jiraSuccess");


        PortletPreferences prefs = request.getPreferences();
        String jiraUrl = prefs.getValue("jiraUrl", "");

        if (StringUtil.isEmpty(jiraUrl)) {
            throw new IllegalStateException("Please input jira Url!");
        }

        try {
            _jiraProxy.setServiceURL(jiraUrl);
        }
        catch (SystemException e) {
            // TODO Auto-generated catch block
            throw new IllegalStateException(
                "Please enter the correct jira Url!");
        }

        jiraSuccess.addObject(_JIRA_URL, jiraUrl);
        jiraSuccess.addObject(_SUCCESS_ID, successId);
        return jiraSuccess;
    }

    protected void processCancel(ActionRequest request,
                                 ActionResponse response, Object command,
                                 BindException errors) throws Exception {
        response.setRenderParameter("action", "jiraIndex");
    }

    protected void validatePage(Object command, Errors errors, int page,
                                boolean finish) {
        if (finish) {
            this.getValidator().validate(command, errors);
        }
        JiraPortletFormBean jira = (JiraPortletFormBean) command;
        CreateTicketValidator jiraPortletValidator = (CreateTicketValidator) getValidator();
        switch (page) {
            case 0:
                jiraPortletValidator.validateUserName(jira, errors);
                jiraPortletValidator.validatePassword(jira, errors);
                break;
            case 2:
                jiraPortletValidator.validateSummary(jira, errors);
                break;
        }
    }

    protected ModelAndView renderInvalidSubmit(RenderRequest request,
                                               RenderResponse response) throws Exception {
        return null;
    }

    protected void handleInvalidSubmit(ActionRequest request,
                                       ActionResponse response) throws Exception {
        response.setRenderParameter("action", "jiraIndex");
    }

    private JiraProxy _jiraProxy;

    public void setJiraProxy(JiraProxy jiraProxy) {
        _jiraProxy = jiraProxy;
    }

}
*/
}
