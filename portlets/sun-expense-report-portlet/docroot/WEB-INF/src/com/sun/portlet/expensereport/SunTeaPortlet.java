package com.sun.portlet.expensereport;

import com.sun.saw.WorkflowException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

/**
 * SunTeaPortlet Portlet Class
 */
public class SunTeaPortlet extends GenericPortlet {

    public static final String VIEW_PAGE = "VIEW_PAGE";
    private static final String IS_WF_ENABLED = "WFEnabled";
    public ActionHandler handler = new ActionHandler();

    public void processAction(ActionRequest request, ActionResponse response) throws PortletException, IOException {


        try {
            getPortletContext().log("processAction called");
            String action = request.getParameter("action");
            String reportNumber = request.getParameter("reportNumber");
            String isWFEnabled = request.getPreferences().getValue(IS_WF_ENABLED, "false");
            boolean bnIsWFEnabled = "true".equals(isWFEnabled);
            
            if ("createReport".equals(action)) {
                try {
                    response.setRenderParameter(VIEW_PAGE, "/WEB-INF/jsp/expense-report/ReportResult.jsp");
                    String reportId = handler.createReport(request);
                    if (bnIsWFEnabled && "Pending".equals(request.getParameter("status"))) {
                        handler.submitReportToJCAPS(request, reportId);
                    }
                    if (reportId != null) {
                        response.setRenderParameter("ReportID", reportId);
                        response.setRenderParameter("result", "success");
                    } else {
                        response.setRenderParameter("result", "failure");
                    }
                    getPortletContext().log("Report ID " + reportId);
                } catch (DBException ex) {
                    throw new PortletException(ex.getMessage());
                }
            } else if ("submitReport".equals(action)) {
                System.out.println("submitReport");
                try {
                    handler.changeReportStatus("Pending", reportNumber);
                    if(bnIsWFEnabled) {
                        handler.submitReportToJCAPS(request, reportNumber);
                    }
                    response.setRenderParameter(VIEW_PAGE, "/WEB-INF/jsp/expense-report/ViewSummary.jsp");
                } catch (DBException ex) {
                    throw new PortletException(ex.getMessage());
                }

            } else if ("approveReport".equals(action)) {
                try {
                    handler.changeReportStatus("Approved", reportNumber);
                    if(bnIsWFEnabled) {
                        handler.approveOrRejectTask(reportNumber, request, true);
                    }
                    response.setRenderParameter(VIEW_PAGE, "/WEB-INF/jsp/expense-report/ViewSummary.jsp");
                } catch (DBException ex) {
                    throw new PortletException(ex.getMessage());
                } catch (WorkflowException ex) {
                    throw new PortletException(ex.getMessage());
                }
            } else if ("rejectReport".equals(action)) {
                try {
                    handler.changeReportStatus("Rejected", reportNumber);
                    response.setRenderParameter(VIEW_PAGE, "/WEB-INF/jsp/expense-report/ViewSummary.jsp");
                    if (bnIsWFEnabled) {
                        handler.approveOrRejectTask(reportNumber, request, true);
                    }
                } catch (DBException ex) {
                    throw new PortletException(ex.getMessage());
                } catch (WorkflowException ex) {
                    throw new PortletException(ex.getMessage());
                }
            } else if ("setWFPreference".equals(action)) {

                String WFEnabled = request.getParameter("WFEnabled");
             /*   String dbType = request.getParameter("DB_TYPE");
                String mySQLURL = request.getParameter("mySQLURL");
                String mySQLUsername = request.getParameter("mySQLUsername");
                String mySQLPwd = request.getParameter("mySQLPwd");*/
                System.out.print("WFEnabled from request......."+WFEnabled);
                request.getPreferences().setValue(IS_WF_ENABLED, WFEnabled);
                request.getPreferences().store();
                
                response.setPortletMode(PortletMode.VIEW);

            }
        } catch (Exception e) {

            e.printStackTrace();
            response.setRenderParameter(VIEW_PAGE, "/WEB-INF/jsp/expense-report/ErrorPage.jsp");
        }
    }

    public void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {

        try {
            getPortletContext().log("doView called");
            PortletRequestDispatcher dispatcher = null;
            response.setContentType("text/html");

            String user = (String) request.getPortletSession().getAttribute(ActionHandler.USER_ID);
            
            if (user == null) {

                String userId = request.getRemoteUser();
                
                System.out.println("user id:::::::::::::" + userId);
                String jobTitle = "";

                if (userId != null) {
                try {
                        com.liferay.portal.model.User userObj = com.liferay.portal.service.UserServiceUtil.getUserById(Long.parseLong(userId));
                       System.out.println("job title....."+userObj.getContact().getJobTitle());
                      jobTitle = userObj.getContact().getJobTitle();
                      if (userObj != null) {
                          user = userObj.getScreenName();
                          System.out.println("user . screenName = " + user);

                       }
                       
                    } catch (RemoteException ex) {
                        Logger.getLogger(SunTeaPortlet.class.getName()).log(Level.SEVERE, null, ex);
                        throw new PortletException(ex.getMessage());
                    }
        
                    request.getPortletSession().setAttribute(ActionHandler.USER_ID, user);
                    request.getPortletSession().setAttribute(ActionHandler.JOB_TITLE, jobTitle);
                    
                }
            }

            Employee empBean = (Employee) request.getPortletSession().getAttribute(ActionHandler.EMP_DETAILS_BEAN);
            boolean anonymousUser = false;
            if (empBean == null && user != null) {
                try {
                    empBean = handler.getEmployeeDetails(user);
                } catch (DBException ex) {
                    throw new PortletException(ex.getMessage());
                }
                //For Users Not configured in DB
                if (empBean == null) {
                    anonymousUser = true;
                }
                request.getPortletSession().setAttribute(ActionHandler.EMP_DETAILS_BEAN, empBean);
            } else if (user == null) {
                anonymousUser = true;
            }

            String userName = (String) request.getPortletSession().getAttribute(ActionHandler.USER_NAME);
            if (userName == null) {
                userName = (empBean != null) ? empBean.getName() : null;
                request.getPortletSession().setAttribute(ActionHandler.USER_NAME, userName);
            }
            String viewPage = request.getParameter(VIEW_PAGE);
            if (viewPage == null) {

                //View page not set in process Action method
                if (anonymousUser) {
                    viewPage = "/WEB-INF/jsp/expense-report/AnonymousUser.jsp";
                } else {
                    if ("createReport".equals(request.getParameter("action"))) {
                        //Link to create report - get elementary employee details
                        getPortletContext().log("create report");
                        viewPage = "/WEB-INF/jsp/expense-report/CreateReport.jsp";
                    } else {
                        //If  WFEnabled is present in the preferences, redirect to Summary page
                        String isWFEnabled = request.getPreferences().getValue(IS_WF_ENABLED, null);
                        if (isWFEnabled != null) {
                            getPortletContext().log("view summary");
                            viewPage = "/WEB-INF/jsp/expense-report/ViewSummary.jsp";
                            String jobTitle = (String)request.getPortletSession().getAttribute(ActionHandler.JOB_TITLE);
                            if (jobTitle.equalsIgnoreCase("Manager")) {
                               
                                request.getPortletSession().setAttribute("Role", "Manager");
                            } else {
                                request.getPortletSession().setAttribute("Role", "Employee");
                            }
                        } else {
                        //WFEnabled is not present in the preferences, redirect to Edit page
                            viewPage = "/WEB-INF/jsp/expense-report/Welcome.jsp";
                        }
                    }
                }
            } else if ("/WEB-INF/jsp/expense-report/ReportResult.jsp".equals(viewPage)) {

                request.setAttribute("ReportID", request.getParameter("ReportID"));
                request.setAttribute("result", request.getParameter("result"));
            }
            dispatcher = getPortletContext().getRequestDispatcher(viewPage);
            dispatcher.include(request, response);
        } catch (Exception e) {
            PortletRequestDispatcher dispatcher = getPortletContext().getRequestDispatcher("/WEB-INF/jsp/expense-report/ErrorPage.jsp");
            //getPortletContext().log(e.getMessage());
            e.printStackTrace();
            dispatcher.include(request, response);

        }
    }

    public void doHelp(RenderRequest request, RenderResponse response) throws PortletException, IOException {


        response.setContentType("text/html");
        PortletRequestDispatcher dispatcher = getPortletContext().getRequestDispatcher("/WEB-INF/jsp/expense-report/Help.jsp");
        dispatcher.include(request, response);
    }

    public void doEdit(RenderRequest request, RenderResponse response) throws PortletException, IOException {


        response.setContentType("text/html");
        PortletRequestDispatcher dispatcher = getPortletContext().getRequestDispatcher("/WEB-INF/jsp/expense-report/Welcome.jsp");
        dispatcher.include(request, response);
    }

    public void serveResource(ResourceRequest request, ResourceResponse response) throws PortletException, IOException {
        System.out.println("serveresource called - changed");
        String reportNumber = request.getParameter("reportNumber");

        String action = request.getParameter("action");
        System.out.println("Action in serveResource " + action);
        if ("viewReport".equals(action)) {
            String responseStr;
            try {

                responseStr = handler.getReportDetailsForReportId(reportNumber, request);
            } catch (DBException ex) {
                throw new PortletException(ex.getMessage());
            }
            response.setContentType("application/xml");
            response.getWriter().write(responseStr);



        }

    }
}
