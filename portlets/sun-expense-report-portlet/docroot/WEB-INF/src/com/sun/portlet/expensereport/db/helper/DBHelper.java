/** Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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
/**
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at http://www.sun.com/cddl/cddl.html and
 * legal/CDDLv1.0.txt. See the License for the specific language governing
 * permission and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL Header Notice in each file
 * and include the License file at legal/CDDLv1.0.txt.
 *
 * If applicable, add the following below the CDDL Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Copyright 2008 Sun Microsystems Inc. All rights reserved.
 */

package com.sun.portlet.expensereport.db.helper;

import com.sun.portlet.expensereport.exception.DBException;
import com.sun.portlet.expensereport.bean.Item;
import com.sun.portlet.expensereport.bean.Employee;
import com.sun.portlet.expensereport.bean.Report;
import com.sun.portlet.expensereport.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * <a href="HSQLDBHelper.java.html"><b><i>View Source</i></b></a>
 *
 * @author Nithya Subramanian
 *
 */
public class DBHelper {

    public static Connection connection = null;
    public static final String MYSQL_GET_EMPLOYEE_DETAILS_QUERY =
        "SELECT e.emp_sun_id, e.emp_name," +
        "(select e1.emp_name from lportal.employee e1 where" +
        " e.manager_Id = e1.emp_id) as manager_name FROM" +
        " lportal.employee e where e.emp_id = ?";
    public static final String MYSQL_INSERT_REPORT_QUERY =
        "insert into lportal.report_details (emp_id, amount," +
        " rep_status, submission_date,rep_desc) " +
        "values (?,?,?,?,?)";
    public static final String MYSQL_INSERT_ITEM_QUERY =
        "insert into lportal.item (item_purpose, start_date, " +
        "end_date, report_id, item_amount) " +
        "values (?,?,?,?,?)";
    public static final String MYSQL_GET_REPORT_ID_QUERY =
        "SELECT max(report_id) as report_id from lportal.report_details";
    public static final String MYSQL_GET_PENDING_REPORTS_FOR_EMP =
        "SELECT * FROM lportal.report_details r where r.emp_id = ?" +
        " and r.rep_status in ('Pending','Saved')";
    public static final String MYSQL_GET_REPORT_DETAILS_FOR_REPORT =
        "select * from lportal.report_details r where r.report_id = ?";
    public static final String MYSQL_GET_ITEM_DETAILS_FOR_REPORT =
        "SELECT * FROM item i where i.report_id= ?";
    public static final String MYSQL_GET_PENDING_REPORTS_FOR_MGR =
        "select * from report_details r where r.emp_id in" +
        " (select e.emp_id from lportal.employee e where " +
        "e.manager_id = ?) and r.rep_status='Pending'";
    public static final String MYSQL_GET_REPORTS_FOR_APPOVAL_BY_MGR =
        "select r.report_id, r.amount, r.rep_status, r.rep_desc, " +
        "r.submission_date, (select e1.emp_name from employee e1" +
        " where e1.emp_id = r.emp_id )as emp_name from report_details r " +
        "where r.emp_id in (select emp_id from employee e where" +
        " e.manager_id = ?)and r.rep_status='Pending'";
    public static final String MYSQL_SET_REPORT_STATUS =
        "update report_details set rep_status=? where report_id = ?";
    public static final String MYSQL_GET_MANAGER_FOR_EMP =
        "SELECT manager_id FROM employee e where e.emp_id=?";
    public static final String MYSQL_GET_EMP_NAME_FROM_ID =
        "SELECT emp_name FROM employee e where e.emp_id=?";
    private static final String CONNECTION_PROPERTIES =
        "DBConnection.properties";
    private static final String MYSQL_CONNECTION_URL = "MySQL.ConnectionURL";
    private static final String MYSQL_DRIVER_CLASS = "MySQL.DriverClass";
    private static final String MYSQL_USERNAME = "MySQL.UserName";
    private static final String MYSQL_PASSWORD = "MySQL.Password";

    public void createItemsForReport(List<Item> itemList, String reportId) 
        throws DBException {
        try {
            if (connection == null) {
                connection = getConnection();
            }
            for (Item item : itemList) {
                PreparedStatement preparedStn = connection.prepareStatement(
                    MYSQL_INSERT_ITEM_QUERY);

                preparedStn.setString(1, item.getItemPurpose());
                preparedStn.setString(2, item.getStartDate());
                preparedStn.setString(3, "");
                preparedStn.setString(4, reportId);
                preparedStn.setString(5, item.getItemAmount());
                preparedStn.executeUpdate();
            }

        } catch (SQLException e) {

            throw new DBException(e.getMessage());
        } catch (ClassNotFoundException e) {

            throw new DBException(e.getMessage());
        } catch (IOException e) {

            throw new DBException(e.getMessage());
        }
    }

    public String createReportRecord(Report report) throws DBException {
        try {
            if (connection == null) {
                connection = getConnection();
            }
            PreparedStatement preparedStn = connection.prepareStatement(
                MYSQL_INSERT_REPORT_QUERY);

            preparedStn.setString(1, report.getEmployeeId());
            preparedStn.setString(2, report.getAmount());
            preparedStn.setString(3, report.getStatus());
            preparedStn.setString(4, report.getSubmissionDate());
            preparedStn.setString(5, report.getReportDesc());
            String reportId = null;

            if (preparedStn.executeUpdate() == 1) {
                Statement stn = connection.createStatement();
                ResultSet result = stn.executeQuery(MYSQL_GET_REPORT_ID_QUERY);
                if (result != null && result.next()) {
                    reportId = result.getString("report_id");
                }
            }
            return reportId;
        } catch (SQLException e) {

            throw new DBException(e.getMessage());
        } catch (ClassNotFoundException e) {

            throw new DBException(e.getMessage());
        } catch (IOException e) {

            throw new DBException(e.getMessage());
        }
    }

    public Employee getEmployeeDetails(String empId) throws DBException {
        try {
            if (connection == null) {
                connection = getConnection();
            }
            PreparedStatement stn = connection.prepareStatement(
                MYSQL_GET_EMPLOYEE_DETAILS_QUERY);
            stn.setString(1, empId);
            ResultSet result = stn.executeQuery();
            Employee employeeBean = new Employee();
            if (result != null && result.next()) {
                String empSunId = result.getString("Emp_Sun_Id");
                String empName = result.getString("Emp_name");
                String managerId = result.getString("Manager_name");
                employeeBean.setId(empId);
                employeeBean.setSunId(empSunId);
                employeeBean.setName(empName);
                employeeBean.setManagerId(managerId);
                return employeeBean;
            } else {
                return null;
            }
        } catch (SQLException e) {

            throw new DBException(e.getMessage());
        } catch (ClassNotFoundException e) {

            throw new DBException(e.getMessage());
        } catch (IOException e) {

            throw new DBException(e.getMessage());
        }
    }
    
    public String getEmployeeNameFromId(String empId) throws DBException {
        String empName = "";
        try {
            if (connection == null) {
                connection = getConnection();
            }
            PreparedStatement preparedStn = connection.prepareStatement(
                MYSQL_GET_EMP_NAME_FROM_ID);
            preparedStn.setString(1, empId);
            ResultSet result = preparedStn.executeQuery();

            if (result != null && result.next()) {
                empName = result.getString(1);
            }
        } catch (SQLException e) {

            throw new DBException(e.getMessage());
        } catch (ClassNotFoundException e) {

            throw new DBException(e.getMessage());
        } catch (IOException e) {

            throw new DBException(e.getMessage());
        }
        return empName;
    }

    public String getManagerForEmployee(String empId) throws DBException {
        String managerId = "";
        try {
            if (connection == null) {
                connection = getConnection();
            }
            PreparedStatement preparedStn = connection.prepareStatement(
                MYSQL_GET_MANAGER_FOR_EMP);
            preparedStn.setString(1, empId);
            ResultSet result = preparedStn.executeQuery();

            if (result != null && result.next()) {
                managerId = result.getString(1);
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new DBException(e.getMessage());
        } catch (IOException e) {
            throw new DBException(e.getMessage());
        }
        return managerId;
    }

    public List<Report> getPendingReportsSummaryForEmployee(String empId)
        throws DBException {

        try {
            if (connection == null) {
                connection = getConnection();
            }
            PreparedStatement preparedStn = connection.prepareStatement(
                MYSQL_GET_PENDING_REPORTS_FOR_EMP);
            preparedStn.setString(1, empId);
            List<Report> reportList = null;

            ResultSet result = preparedStn.executeQuery();

            if (result != null) {
                reportList = new ArrayList<Report>();
                while (result.next()) {
                    Integer reportId = result.getInt("REPORT_ID");
                    String amount = result.getString("AMOUNT");
                    String repDesc = result.getString("REP_DESC");
                    String submissionDate = result.getString("SUBMISSION_DATE");
                    String status = result.getString("REP_STATUS");


                    Report report = new Report();
                    report.setReportId(reportId != null ? 
                        reportId.toString() : null);
                    report.setId(reportId != null ? 
                        reportId.toString() : null);
                    report.setAmount(amount);
                    report.setReportDesc(repDesc);
                    report.setStatus(status);
                    report.setSubmissionDate(submissionDate);
                    reportList.add(report);
                }
            }
            return reportList;
        } catch (SQLException e) {

            throw new DBException(e.getMessage());
        } catch (ClassNotFoundException e) {

            throw new DBException(e.getMessage());
        } catch (IOException e) {

            throw new DBException(e.getMessage());
        }

    }

    public Report getReportDetailsforReport(String reportId) 
        throws DBException {
        
        try {
            if (connection == null) {
                connection = getConnection();
            }
            PreparedStatement preparedStn = connection.prepareStatement(
                MYSQL_GET_REPORT_DETAILS_FOR_REPORT);
            preparedStn.setInt(1, Integer.parseInt(reportId));

            ResultSet result = preparedStn.executeQuery();
            Report report = null;

            if (result != null && result.next()) {
                report = new Report();
                String amount = result.getString("AMOUNT");
                String reportDesc = result.getString("REP_DESC");
                String submissionDate = result.getString("SUBMISSION_DATE");
                String status = result.getString("REP_STATUS");
                String empId = result.getString("EMP_ID");

                PreparedStatement preparedStnItem = connection.prepareStatement(
                    MYSQL_GET_ITEM_DETAILS_FOR_REPORT);
                preparedStnItem.setInt(1, Integer.parseInt(reportId));
                ResultSet itemResults = preparedStnItem.executeQuery();
                List<Item> itemsList = null;

                if (itemResults != null) {
                    itemsList = new ArrayList<Item>();
                    while (itemResults.next()) {
                        Item item = new Item();
                        item.setItemId(itemResults.getString("ITEM_ID"));
                        item.setEndDate(itemResults.getString("END_DATE"));
                        item.setStartDate(itemResults.getString("START_DATE"));
                        item.setItemAmount(itemResults.getString(
                            "ITEM_AMOUNT"));
                        item.setItemPurpose(itemResults.getString(
                            "ITEM_PURPOSE"));
                        itemsList.add(item);
                    }

                }

                report.setReportId(reportId);
                report.setAmount(amount);
                report.setEmployeeId(empId);
                report.setReportDesc(reportDesc);
                report.setStatus(status);
                report.setSubmissionDate(submissionDate);
                report.setItems(itemsList);
            }
            return report;
        } catch (SQLException e) {

            throw new DBException(e.getMessage());
        } catch (ClassNotFoundException e) {

            throw new DBException(e.getMessage());
        } catch (IOException e) {

            throw new DBException(e.getMessage());
        }

    }

    public List<Report> getReportsToApprove(String managerId) 
        throws DBException {

        try {
            if (connection == null) {
                connection = getConnection();
            }
            PreparedStatement preparedStn = connection.prepareStatement(
                MYSQL_GET_REPORTS_FOR_APPOVAL_BY_MGR);
            preparedStn.setString(1, managerId);
            List<Report> reportList = null;

            ResultSet result = preparedStn.executeQuery();

            if (result != null) {
                reportList = new ArrayList<Report>();
                while (result.next()) {
                    Integer reportId = result.getInt("REPORT_ID");
                    String amount = result.getString("AMOUNT");
                    String reportDesc = result.getString("REP_DESC");
                    String submissionDate = result.getString("SUBMISSION_DATE");
                    String status = result.getString("REP_STATUS");
                    String empName = result.getString("EMP_NAME");


                    Report report = new Report();
                    report.setReportId(reportId != null ?
                        reportId.toString() : null);
                    report.setId(reportId != null ?
                        reportId.toString() : null);
                    report.setAmount(amount);
                    report.setReportDesc(reportDesc);
                    report.setStatus(status);
                    report.setSubmissionDate(submissionDate);
                    report.setEmployeeName(empName);
                    reportList.add(report);
                }
            }
            return reportList;
        } catch (SQLException e) {

            throw new DBException(e.getMessage());
        } catch (ClassNotFoundException e) {

            throw new DBException(e.getMessage());
        } catch (IOException e) {

            throw new DBException(e.getMessage());
        }

    }

    public void setReportStatus(String status, Integer reportNumber)
        throws DBException {

        try {
            if (connection == null) {
                connection = getConnection();
            }
            PreparedStatement preparedStn = connection.prepareStatement(
                MYSQL_SET_REPORT_STATUS);
            preparedStn.setString(1, status);
            preparedStn.setInt(2, reportNumber);

            preparedStn.executeUpdate();


        } catch (SQLException e) {

            throw new DBException(e.getMessage());
        } catch (ClassNotFoundException e) {

            throw new DBException(e.getMessage());
        } catch (IOException e) {

            throw new DBException(e.getMessage());
        }

    }

    private void createTables() throws SQLException {
        Statement stmt = null;
        String reportDetailsQuery = "select * from report_details";

        System.out.println("Inside createTables");


        String reportDetailsCreateQuery = "CREATE TABLE report_details (" +
            "report_id integer GENERATED BY DEFAULT AS IDENTITY(" +
            "start with 100000) PRIMARY KEY," +
            "emp_id varchar(150) not null," +
            "amount varchar not null," +
            "rep_status varchar not null," +
            "submission_date varchar not null," +
            "rep_desc varchar not null" +
            ")";
        String itemCreateQuery = "CREATE TABLE item (" +
            "item_id integer GENERATED BY DEFAULT AS IDENTITY(" +
            "start with 10000) PRIMARY KEY," +
            "item_purpose varchar(150) not null," +
            "start_date varchar NOT NULL," +
            "end_date varchar NOT NULL," +
            "report_id integer," +
            "item_amount varchar(45) NOT NULL," +
            "FOREIGN KEY (report_id) REFERENCES report_details" +
            ")";
        String employeeCreateQuery = "CREATE TABLE employee (" +
            "emp_id varchar(50) not null primary key, " +
            "emp_sun_id varchar not null," +
            "emp_name varchar(100) not null," +
            "manager_id varchar not null" +
            ")";
        String employeePopulateQuery = "insert into employee" +
            " (emp_id,emp_sun_id,emp_name,manager_id) values(?,?,?,?)";
        PreparedStatement pstmt = null;
        try {
            stmt = connection.createStatement();
            try {
                ResultSet set = stmt.executeQuery(reportDetailsQuery);
                System.out.println("tables already exists...");
                return;
            } catch (SQLException se) {
                System.out.println("Creating Tables...");
            }
            pstmt = connection.prepareStatement(employeePopulateQuery);


            System.out.println("ReportDetails table result::::" +
                stmt.executeUpdate(reportDetailsCreateQuery));

            System.out.println("Item table result::::" + 
                stmt.executeUpdate(itemCreateQuery));
            System.out.println("Employee table result::::" + 
                stmt.executeUpdate(employeeCreateQuery));
            connection.commit();

            pstmt.setString(1, "paul");
            pstmt.setString(2, "208321");
            pstmt.setString(3, "Paul Tester");
            pstmt.setString(4, "mary");
            pstmt.execute();

            pstmt.setString(1, "ed");
            pstmt.setString(2, "193451");
            pstmt.setString(3, "Ed Developer");
            pstmt.setString(4, "mary");
            pstmt.execute();

            pstmt.setString(1, "chris");
            pstmt.setString(2, "168321");
            pstmt.setString(3, "Chris Editor");
            pstmt.setString(4, "mary");
            pstmt.execute();

            pstmt.setString(1, "mary");
            pstmt.setString(2, "108321");
            pstmt.setString(3, "Mary Manager");
            pstmt.setString(4, "bill");
            pstmt.execute();

            pstmt.setString(1, "bill");
            pstmt.setString(2, "100321");
            pstmt.setString(3, "Bill Boss");
            pstmt.setString(4, "bill");
            pstmt.execute();
            connection.commit();

        } catch (SQLException se) {
            se.printStackTrace();
            throw new SQLException(se.getMessage());
        }
    }
    
    private Connection getConnection()
        throws ClassNotFoundException, SQLException, IOException {
        if (connection == null) {
            Class.forName(getProperty(MYSQL_DRIVER_CLASS));
            connection = DriverManager.getConnection(getProperty(
                MYSQL_CONNECTION_URL), getProperty(MYSQL_USERNAME),
                getProperty(MYSQL_PASSWORD));
        }
        return connection;
    }
    
    private String getProperty(String propertyName) throws IOException {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream(
            CONNECTION_PROPERTIES));
        System.out.println("string///" + properties.getProperty(propertyName));
        return properties.getProperty(propertyName);
    }

    
}
