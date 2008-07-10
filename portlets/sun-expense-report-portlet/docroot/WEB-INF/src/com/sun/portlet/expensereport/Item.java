package com.sun.portlet.expensereport;

import java.io.Serializable;
import org.json.JSONArray;

public class Item implements Serializable {

    private String itemId;
    private String reportId;
    private String itemPurpose;
    private String startDate;
    private String endDate;
    private String itemAmount;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getItemPurpose() {
        return itemPurpose;
    }

    public void setItemPurpose(String itemPurpose) {
        this.itemPurpose = itemPurpose;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(String itemAmount) {
        this.itemAmount = itemAmount;
    }

    public JSONArray toJSON() {
        JSONArray thisJSON = new JSONArray();

        thisJSON.put(this.getItemPurpose());
        thisJSON.put(this.getStartDate());
        thisJSON.put(this.getEndDate());
        thisJSON.put(this.getItemAmount());
        return thisJSON;
    }
}
