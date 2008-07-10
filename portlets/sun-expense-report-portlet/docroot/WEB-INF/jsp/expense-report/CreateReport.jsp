<%@ taglib prefix="a" uri="http://jmaki/v1.0/jsp" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>


<%@ page import="javax.portlet.*"%>
<%@ page import="com.sun.portlet.expensereport.Employee"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>



<portlet:defineObjects />
<SCRIPT LANGUAGE="JavaScript" SRC="<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/js/calendar.js")%>">

</SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/js/calendar-setup.js")%>">

</SCRIPT>

<portlet:actionURL var="submitURL">
<portlet:param name="action" value="createReport"/>
</portlet:actionURL>

<portlet:renderURL var="returnURL"/>

<script type = "text/javascript">
function saveReport() {
	//alert("save report called");
	if(checkAmount() ){
document.getElementById("status").value = "Saved";
document.reportForm.submit();
	}
}

function addRow()
{
//	alert("addrow called");
var noOfRows = document.getElementById("noOfRows").value;
//alert(noOfRows);
var rowIndex = eval(noOfRows) + 1;
var tbody = document.getElementById('myTable').getElementsByTagName("TBODY")[0];
var row = document.createElement("TR");

var cell1 = document.createElement("TD");

var selectElement = document.createElement("select");
selectElement.setAttribute("name","itemPurpose");
var optionElement1 = document.createElement("option");
optionElement1.setAttribute("value","Entertainment  Meals/Meeting with Customer");
optionElement1.innerHTML = "Entertainment  Meals/Meeting with Customer";
selectElement.appendChild(optionElement1);

var optionElement2 = document.createElement("option");
optionElement2.setAttribute("value","Office Supplies - Stationary");
optionElement2.innerHTML = "Office Supplies - Stationary";
selectElement.appendChild(optionElement2);

var optionElement3 = document.createElement("option");
optionElement3.setAttribute("value","Internal Offsite Meetings");
optionElement3.innerHTML = "Internal Offsite Meetings";
selectElement.appendChild(optionElement3);

var optionElement4 = document.createElement("option");
optionElement4.setAttribute("value","Postage - Courier");
optionElement4innerHTML = "Postage - Courier";
selectElement.appendChild(optionElement4);

var optionElement5 = document.createElement("option");
optionElement5.setAttribute("value","Customer Seminars");
optionElement5.innerHTML = "Customer Seminars";
selectElement.appendChild(optionElement5);

var optionElement6 = document.createElement("option");
optionElement6.setAttribute("value","Employee Incentives");
optionElement6.innerHTML = "Employee Incentives";
selectElement.appendChild(optionElement6);

var optionElement7 = document.createElement("option");
optionElement7.setAttribute("value","Home Phone/Internet Charges");
optionElement7.innerHTML = "Home Phone/Internet Charges";
selectElement.appendChild(optionElement7);

var optionElement8 = document.createElement("option");
optionElement8.setAttribute("value","Local Means- Sun Staff only");
optionElement8.innerHTML = "Local Means- Sun Staff only";
selectElement.appendChild(optionElement8);

var optionElement10 = document.createElement("option");
optionElement10.setAttribute("value","Mobile Phone Charges");
optionElement10.innerHTML = "Mobile Phone Charges";
selectElement.appendChild(optionElement10);

var optionElement11 = document.createElement("option");
optionElement11.setAttribute("value","Trip- Auto, Rental, Parking");
optionElement11.innerHTML = "Trip- Auto, Rental, Parking";
selectElement.appendChild(optionElement11);

var optionElement12 = document.createElement("option");
optionElement12.setAttribute("value","Trip - Hotel Room Charges");
optionElement12.innerHTML = "Trip - Hotel Room Charges";
selectElement.appendChild(optionElement12);

var optionElement13 = document.createElement("option");
optionElement13.setAttribute("value","Relocation");
optionElement13.innerHTML = "Relocation";
selectElement.appendChild(optionElement13);

var optionElement14 = document.createElement("option");
optionElement14.setAttribute("value","Tuition Reimursement");
optionElement14.innerHTML = "Tuition Reimursement";
selectElement.appendChild(optionElement14);

var optionElement15 = document.createElement("option");
optionElement15.setAttribute("value","Notice Period Pay");
optionElement15.innerHTML = "Notice Period Pay";
selectElement.appendChild(optionElement15);


var optionElement16 = document.createElement("option");
optionElement16.setAttribute("value","Governmnet Related Expenses");
optionElement16.innerHTML = "Governmnet Related Expenses";
selectElement.appendChild(optionElement16);

cell1.appendChild(selectElement);

var cell2= document.createElement("TD");
var itemElement = document.createElement("input");
itemElement.setAttribute("type","text");
itemElement.setAttribute("name","itemAmount");
var itemId = "itemAmount"+rowIndex;
//alert(itemId);
itemElement.setAttribute("id",itemId);
cell2.appendChild(itemElement);
//cell2.innerHTML='<input type="text" name="itemAmount" id="itemAmount"/>';

var cell3 = document.createElement("TD");
cell3.innerHTML='<input type="text" name="startDate" />';

//var cell4 = document.createElement("TD");
//cell4.innerHTML='<input type="text" name="endDate" />';

row.appendChild(cell1);
row.appendChild(cell2);
row.appendChild(cell3);
//row.appendChild(cell4);
tbody.appendChild(row);
document.getElementById("noOfRows").value = rowIndex;

}

function checkAmount() {
//	alert("cjeck amt called");
var returnVal = true;
var noOfItems = document.getElementById("noOfRows").value;
//alert(noOfItems);
var intItems = eval(noOfItems);
//alert(intItems);
for(var i = 1;i <= intItems;i++) 
{
	var itemId = "itemAmount"+i;
	//alert(itemId);
	var itemAmtVal =  document.getElementById(itemId).value;
	//alert(itemAmtVal);
	if(itemAmtVal == "" || isNaN(itemAmtVal)) {
		alert("Please enter a valid amount for item no."+i);
		returnVal = false;
		break;
	}
	
}
return returnVal;
}


</script>
<form name="reportForm" method="POST" action='<%=submitURL.toString()%>' onsubmit="javascript: return checkAmount();">
<% Employee empBean = (Employee)portletSession.getAttribute("EmployeeDetails");
%>

<INPUT TYPE="hidden" NAME="noOfRows" id = "noOfRows" value="1"/>
<h4>New Expense Report for <%=empBean.getName()%> (<%=empBean.getSunId()%>)</h4>
<br>
Approver:&nbsp;&nbsp;&nbsp;&nbsp;<b><%=empBean.getManagerId()%> </b><br>
Description : <input type="text" name="reportDesc" size="40"/> <br>

Items:<br>

<table id="myTable" border="1">
<tbody>
<tr>
<th>Item Description</th>
<th>Amount (USD)</th>
<th>Date (MM/DD/YY)</th>
<!-- <th>End Date</th> -->
</tr>
<tr>

<td><SELECT NAME="itemPurpose">
	<OPTION VALUE="Entertainment  Meals/Meeting with Customer" >Entertainment  Meals/Meeting with Customer</option>
	<OPTION VALUE="Office Supplies - Stationary">Office Supplies - Stationary</option>
	<OPTION VALUE="Internal Offsite Meetings">Internal Offsite Meetings</option>
	<OPTION VALUE="Postage - Courier">Postage - Courier</option>
	<OPTION VALUE="Customer Seminars">Customer Seminars</option>
	<OPTION VALUE="Employee Incentives">Employee Incentives</option>
	<OPTION VALUE="Home Phone/Internet Charges">Home Phone/Internet Charges</option>
	<OPTION VALUE="Local Means- Sun Staff only">Local Means- Sun Staff only</option>
	<OPTION VALUE="Local Taxi">Local Taxi</option>
	<OPTION VALUE="Mobile Phone Charges">Mobile Phone Charges</option>
	<OPTION VALUE="Trip- Auto, Rental, Parking">Trip- Auto, Rental, Parking</option>
	<OPTION VALUE="Trip - Hotel Meals">Trip - Hotel Meals</option>
	<OPTION VALUE="Trip - Hotel Room Charges">Trip - Hotel Room Charges</option>
	<OPTION VALUE="Trips- Airfare">Trips- Airfare</option>
	<OPTION VALUE="Relocation">Relocation</option>
	<OPTION VALUE="Tuition Reimursement">Tuition Reimursement</option>
	<OPTION VALUE="Notice Period Pay">Notice Period Pay</option>
	<OPTION VALUE="Governmnet Related Expenses">Governmnet Related Expenses</option>
</SELECT>
</td>
<td><input type="text" name="itemAmount" id="itemAmount1"/></td>
<td><input type="text" name="startDate" /></td>
<!-- <td><input type="text" name="endDate"/></td> -->

</tr>
</tbody>
<input type="button" name="addRowBtn" onClick="javascript:addRow();" value="Add Item"/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

</table> 
<br>
 <input id="status" type="hidden" name="status" value="Pending"/>
<input type="button" value="Save Report" onclick="javascript:saveReport();"/>
<input type="submit" value="Submit Report"/>
</form>

<a href='<%=returnURL.toString()%>'>Cancel</a>
