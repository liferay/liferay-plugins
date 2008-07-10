/*
 * ReportBean.java
 *
 * Created on February 17, 2008, 4:45 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.sun.portlet.expensereport;

import java.io.Serializable;
import java.util.List;
import org.json.JSONArray;

public class Report implements Serializable {

    private String reportId;
    private String id;
    private String employeeId;
    private String employeeName;
    private String amount;
    private String reportDesc;
    private String status;
    private String submissionDate;
    private List<Item> items;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    /** Creates a new instance of ReportBean */
    public Report() {
    }

    public String getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(String date) {
        this.submissionDate = date;
    }
    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public JSONArray toJSON(String role) {
        JSONArray thisJSON = new JSONArray();
     
        thisJSON.put(this.getReportId());

        thisJSON.put(this.getReportDesc());
       if ("manager".equals(role)) {
            thisJSON.put(this.getEmployeeName());
        }
        thisJSON.put(this.getSubmissionDate());
        thisJSON.put(this.getAmount());
        thisJSON.put(this.getStatus());
        //thisJSON.put(this.getId());
        return thisJSON;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReportDesc() {
        return reportDesc;
    }

    public void setReportDesc(String reportDesc) {
        this.reportDesc = reportDesc;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

}
