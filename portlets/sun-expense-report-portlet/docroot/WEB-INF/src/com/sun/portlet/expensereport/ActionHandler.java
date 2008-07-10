package com.sun.portlet.expensereport;

import com.sun.saw.WorkflowException;
import com.sun.saw.vo.TaskVO;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.ResourceRequest;
import org.json.JSONArray;
import javax.xml.namespace.QName;

public class ActionHandler {


    public static final String EMP_DETAILS_BEAN = "EmployeeDetails";
    public static final String USER_ID = "userId";
    public static final String JOB_TITLE = "jobTitle";
    public static final String USER_NAME = "userName";
    private DBHelper dbHelper = null;
    private WFHelper wfHelper = null;
    public static final String USER_ROLE_MANAGER = "Manager";
    public static final String PENDING_TASKS = "PendingTasks";
    public static final String DB_TYPE = "DB_TYPE";
    public static final String MYSQL_USERNAME = "MYSQL_USERNAME";
    public static final String MYSQL_URL = "MYSQL_URL";
    public static final String MYSQL_PWD = "MYSQL_PWD";
    public static final String WEBSERVICE_CONNECTION_PROPERTIES = "WebServiceClient.properties";
    private static Properties webserviceProperties = null;

    public ActionHandler() {
        if (dbHelper == null) {
            String db;
            try {
                db = getProperty("DB");

                if (db != null && db.equals("MySQL")) {
                    dbHelper = new DBHelper();
                } else {
                    dbHelper = new HSQLDBHelper();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if (wfHelper == null) {
            wfHelper = new WFHelper();
        }
    }
    private String user;
    private String reportNumber;
    private boolean isWFEnabled;



    private String getProperty(String propertyName) throws IOException {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("DBConnection.properties"));
        System.out.println("string///" + properties.getProperty(propertyName));
        return properties.getProperty(propertyName);
    }
    
    private void  loadProperties()throws IOException {
        webserviceProperties = new Properties();
        webserviceProperties.load(this.getClass().getClassLoader().getResourceAsStream(WEBSERVICE_CONNECTION_PROPERTIES));
    }

    public List<Report> getPendingReportsSummaryForEmployee() throws DBException {
        List<Report> reports = dbHelper.getPendingReportsSummaryForEmployee(getUser());
        for (Report report : reports) {
            String url = "javascript:viewReport(\'" + report.getReportId() + "\');";
            String reportValue = "<a href=\"" + url + "\">" + report.getReportId() + "</a>";
            report.setReportId(reportValue);
            String rowData = "id : " + report.getId();
            report.setId(rowData);
        }
        return reports;
    }

    public List<Report> getReportsToApprove() throws DBException {
        List<Report> reports = dbHelper.getReportsToApprove(getUser());
        for (Report report : reports) {
            String url = "javascript:viewReport(\'" + report.getReportId() + "\');";
            String reportValue = "<a href=\"" + url + "\">" + report.getReportId() + "</a>";
            report.setReportId(reportValue);
            String rowData = "id : " + report.getId();
            report.setId(rowData);
        }
        return reports;
    }

    public String getRaisedReportsJSON() throws DBException {
        //TODO - Saw API to make calls

        JSONArray reportsArray = new JSONArray();
        List<Report> reports = getPendingReportsSummaryForEmployee();
        for (Report report : reports) {
            JSONArray reportArray = report.toJSON("employee");
            reportsArray.put(reportArray);
        }
        // System.out.print("reports "+reportsArray);
        return reportsArray.toString();
    }

    public String getReportsToApproveJSON() throws WorkflowException, DBException {

        /*   List<TaskVO> pendingTasks = null;
        try {
        
        pendingTasks =   wfHelper.getPendingTasks(USER_ROLE_MANAGER);
        }
        catch(WorkflowException we)
        {
        System.out.println("Error while getting tasks "+we.getMessage());
        return getReportsToApproveJSONDB();
        }*/

        List<TaskVO> pendingTasks = null;
        if (getIsWFEnabled()) {

            pendingTasks = wfHelper.getPendingTasks(USER_ROLE_MANAGER);



            JSONArray reportsArray = new JSONArray();

            for (TaskVO task : pendingTasks) {
                String reportNumber = task.getFlexString6();
                System.out.println("RN from Jcaps " + reportNumber);
                Report report = dbHelper.getReportDetailsforReport(reportNumber);
                if (report != null) {
                    String empId = report.getEmployeeId();
                    String managerId = dbHelper.getManagerForEmployee(empId);
                    System.out.println("Manager for report  " + managerId);
                    System.out.println("user  " + this.getUser());
                    if (managerId.equals(this.getUser())) {
                        System.out.println("Manager for report 2 " + managerId);
                        String empName = dbHelper.getEmployeeNameFromId(empId);
                        String url = "javascript:viewReport(\'" + report.getReportId() + "\');";
                        String reportValue = "<a href=\"" + url + "\">" + report.getReportId() + "</a>";
                        report.setReportId(reportValue);
                        String rowData = "id : " + report.getId();
                        report.setId(rowData);
                        report.setEmployeeName(empName);
                        JSONArray reportArray = report.toJSON("manager");
                        reportsArray.put(reportArray);
                    }
                }
            }
            return reportsArray.toString();
        } else {
            return getReportsToApproveJSONDB();
        }
    }

    public String getItemsForReportJSON() throws DBException {
        JSONArray itemsArray = new JSONArray();
        if (reportNumber != null) {
            Report reportDetails = dbHelper.getReportDetailsforReport(this.getReportNumber());
            List<Item> itemList = reportDetails.getItems();
            for (Item item : itemList) {
                JSONArray itemArray = item.toJSON();
                itemsArray.put(itemArray);
            }
        }
        System.out.println(itemsArray.toString());
        return itemsArray.toString();
    }

    public String getItemsForReportXML(Report reportDetails) throws DBException {
        StringBuffer itemsXML = new StringBuffer("<Items>");
        if (reportDetails != null) {
            List<Item> itemList = reportDetails.getItems();
            if (itemList != null) {
                for (Item item : itemList) {
                    itemsXML.append("<Item>");

                    itemsXML.append("<ItemPurpose>");
                    itemsXML.append(item.getItemPurpose() != null ? item.getItemPurpose() : "");
                    itemsXML.append("</ItemPurpose>");

                    itemsXML.append("<StartDate>");
                    itemsXML.append(item.getStartDate() != null ? item.getStartDate() : "");
                    itemsXML.append("</StartDate>");

                    itemsXML.append("<EndDate>");
                    itemsXML.append(item.getEndDate() != null ? item.getEndDate() : "");
                    itemsXML.append("</EndDate>");

                    itemsXML.append("<ItemAmount>");
                    itemsXML.append(item.getItemAmount() != null ? item.getItemAmount() : "");
                    itemsXML.append("</ItemAmount>");

                    itemsXML.append("</Item>");
                }
            }

        }
        itemsXML.append("</Items>");
        System.out.println(itemsXML.toString());
        return itemsXML.toString();
    }

    public String getItemsForReportJSON(String reportNumber) throws DBException {
        JSONArray itemsArray = new JSONArray();
        if (reportNumber != null) {
            Report reportDetails = dbHelper.getReportDetailsforReport(reportNumber);
            List<Item> itemList = reportDetails.getItems();
            for (Item item : itemList) {
                JSONArray itemArray = item.toJSON();
                itemsArray.put(itemArray);
            }
        }
        return itemsArray.toString();

    }

    public String getReportsToApproveJSONDB() throws DBException {
        //TODO - Saw API to make calls
        JSONArray reportsArray = new JSONArray();
        List<Report> reports = getReportsToApprove();
        for (Report report : reports) {
            JSONArray reportArray = report.toJSON("manager");
            reportsArray.put(reportArray);
        }
        //  System.out.print("reports "+reportsArray);
        return reportsArray.toString();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String createReport(ActionRequest request) throws DBException {
        String reportId = null;
        Employee empBean = (Employee) request.getPortletSession().getAttribute(EMP_DETAILS_BEAN);
        String userId = empBean.getId();
        String[] itemAmounts = request.getParameterValues("itemAmount");
        String[] itemPurposes = request.getParameterValues("itemPurpose");
        int noOfItems = 0;
        if (itemPurposes != null) {
            noOfItems = itemPurposes.length;
        }
        String reportDesc = request.getParameter("reportDesc");
        String[] fromDate = request.getParameterValues("startDate");

        //String[] toDate = request.getParameterValues("endDate");
        Calendar currentTime = Calendar.getInstance();
        String submissionDate = currentTime.get(Calendar.MONTH) + "-" + currentTime.get(Calendar.DATE) + "-" + currentTime.get(Calendar.YEAR);
        String status = request.getParameter("status");
        Report reportBean = new Report();
        Double reportAmount = 0.0d;
        List<Item> itemList = new ArrayList<Item>();
        for (int i = 0; i < noOfItems; i++) {
            Item item = new Item();
            //item.setEndDate(toDate[i]);
            item.setStartDate(fromDate[i]);
            item.setItemPurpose(itemPurposes[i]);
            item.setItemAmount(itemAmounts[i]);
            if (itemAmounts[i] != null && !itemAmounts[i].equals("")) {
                double fItemAmount = Double.parseDouble(itemAmounts[i]);
                reportAmount += fItemAmount;
            }
            itemList.add(item);

        }
        reportBean.setEmployeeId(userId);
        reportBean.setAmount(String.valueOf(reportAmount));
        reportBean.setReportDesc(reportDesc);
        reportBean.setSubmissionDate(submissionDate);
        reportBean.setStatus(status);

        reportId = dbHelper.createReportRecord(reportBean);
        dbHelper.createItemsForReport(itemList, reportId);
        return reportId;
    }

    public String getReportDetailsForReportId(String reportId, ResourceRequest request) throws DBException {

        StringBuffer responseStr = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?><Report>");
        responseStr.append("<ReportId>" + reportId + "</ReportId>");

        Report reportDetails = dbHelper.getReportDetailsforReport(reportId);
        Employee empDetails = getEmployeeDetails(reportDetails.getEmployeeId());

        responseStr.append("<EmployeeName>" + empDetails.getName() + "</EmployeeName>");
        responseStr.append("<EmployeeId>" + empDetails.getSunId() + "</EmployeeId>");
        responseStr.append("<Description>" + reportDetails.getReportDesc() + "</Description>");
        responseStr.append("<ManagerName>" + empDetails.getManagerId() + "</ManagerName>");
        //  responseStr.append("<StartDate>" + reportDetails.getStartDate() + "</StartDate>");
        //   responseStr.append("<EndDate>" + reportDetails.getEndDate() + "</EndDate>");
        responseStr.append("<SubmissionDate>" + reportDetails.getSubmissionDate() + "</SubmissionDate>");
        responseStr.append("<Status>" + reportDetails.getStatus() + "</Status>");
        responseStr.append("<Amount>" + reportDetails.getAmount() + "</Amount>");
        //     responseStr.append("<Notes>" + (reportDetails.getNotes() != null ? reportDetails.getNotes() : "") + "</Notes>");
        responseStr.append(getItemsForReportXML(reportDetails));
        responseStr.append("</Report>");
        System.out.println("responseStr:::::::::::::::::;" + responseStr);
        return responseStr.toString();

    }

    public Employee getEmployeeDetails(String empId) throws DBException {
        return dbHelper.getEmployeeDetails(empId);
    }

    public void submitReportToJCAPS(PortletRequest request, String reportId) {
        try {
            
            if(webserviceProperties == null) {
                loadProperties();
            }
            String url = webserviceProperties.getProperty("WSDL_URL");
			String qName = webserviceProperties.getProperty("QNAME");
			String serviceName = webserviceProperties.getProperty("SERVICE_NAME");
		 URL wsdlLocation = new URL(url);
		
		websynergy.suntea._12000.repository.myrepository.prjsuntea.wssuntea.WsSunTeaService service =
                    new websynergy.suntea._12000.repository.myrepository.prjsuntea.wssuntea.WsSunTeaService(wsdlLocation, new QName(qName, serviceName));
            websynergy.suntea._12000.repository.myrepository.prjsuntea.wssuntea.PortType port = service.getPortTypeBndPort();
             
            

  // com.sun.wsclient.WsSunTeaService service = new com.sun.wsclient.WsSunTeaService();
   //com.sun.wsclient.PortType port = service.getPortTypeBndPort();

            Employee empBean = (Employee) request.getPortletSession().getAttribute(EMP_DETAILS_BEAN);
            String empID = empBean.getId();
            double amount = 0.0d;
            String purpose = "";
            String notes = "";
            String fromDate = "";
            String toDate = "";
            String submissionDate = "";
            String status = "Pending";
            String empName = empBean.getName();
            String reportID = reportId;
            String role = USER_ROLE_MANAGER;
            System.out.println("Before calling WS");
            port.submitReport(empName, amount, empID, purpose, fromDate, toDate, reportID, role, submissionDate, status);
            System.out.println("After calling WS");
        }catch(Exception e) {
            e.printStackTrace();
        }
   
    }

    public void changeReportStatus(String toStatus, String reportNumber) throws DBException {
        Integer intReportNimber = Integer.valueOf(reportNumber);
        dbHelper.setReportStatus(toStatus, intReportNimber);

    }

    public List getPendingTasksForManager() throws WorkflowException {
        return wfHelper.getPendingTasks(this.USER_ROLE_MANAGER);
    }

    public void approveOrRejectTask(String reportNumber, PortletRequest request, boolean approve) throws WorkflowException {
        PortletSession session = request.getPortletSession();
        List<TaskVO> pendingTasks = (List<TaskVO>) session.getAttribute(PENDING_TASKS);
        if (pendingTasks == null) {
            pendingTasks = wfHelper.getPendingTasks(USER_ROLE_MANAGER);
        }
        Iterator taskIterator = pendingTasks.iterator();
        for (; taskIterator.hasNext();) {
            TaskVO task = (TaskVO) taskIterator.next();
            if (reportNumber.equals(task.getFlexString6())) {
                wfHelper.checkOutTask(USER_ROLE_MANAGER, task);
                Map customAttrMap = new HashMap();
                String output = null;
                if (approve) {
                    customAttrMap.put("FLEX_STRING8", "Approved");
                    output = "Approved";
                } else {
                    customAttrMap.put("FLEX_STRING8", "Rejected");
                    output = "Rejected";
                }
                wfHelper.saveTask(USER_ROLE_MANAGER, task, output, customAttrMap);
                wfHelper.completeTasks(USER_ROLE_MANAGER, task);
                taskIterator.remove();

            }
        }
        session.setAttribute(PENDING_TASKS, pendingTasks);


    }

    public String getItemDataTable() {
        return "<a:widget name=\"yahoo.dataTable\"" +
                "value=\"{columns : [" +
                "{ label : 'Item Purpose', id : 'itemPurpose'}," +
                "{ label : 'Start Date', id : 'startDate'}," +
                "{ label : 'End Date', id : 'endDate'}," +
                "{ label : 'Amount(INR)', id : 'itemAmount'}" +
                "]," +
                "rows :";
    }

    public String getReportNumber() {
        return reportNumber;
    }

    public void setReportNumber(String reportNumber) {
        this.reportNumber = reportNumber;
    }

    public boolean getIsWFEnabled() {
        return isWFEnabled;
    }

    public void setIsWFEnabled(boolean isWFEnabled) {
        this.isWFEnabled = isWFEnabled;
    }
}
