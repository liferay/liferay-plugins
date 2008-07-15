<%--
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
--%>

<%@ taglib prefix="a" uri="http://jmaki/v1.0/jsp" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>


<%@ page import="javax.portlet.*"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>

<portlet:defineObjects />
<portlet:resourceURL var="reportURL" escapeXml="false"/>

<portlet:renderURL var="createURL">
    <portlet:param name="action" value="createReport"/>
</portlet:renderURL>

<portlet:actionURL var="submitURL">

</portlet:actionURL>

    
<script type="text/javascript">

    function viewReport(reportNumber){
          var reportUrl = '<%=reportURL.toString()%>'+"&reportNumber="+reportNumber+"&action=viewReport";
		// alert(reportUrl);
  		   jmaki.doAjax({ url :reportUrl ,
                     method : 'GET',
                   	 callback: function(_req) { 
						//alert(_req.responseXML);
						viewReportDetails(_req);
                                                
					 }
    });
           
	}




  	function appReport(approve) {
	//	alert("appreport called"+ approve);
		if("true" == approve) {
					document.getElementById('action').value = "approveReport";
					document.actionForm.submit();
		}
		else {
				    document.getElementById('action').value = "rejectReport";
					document.actionForm.submit();

		}

    }
    function subReport() {

					document.getElementById('action').value = "submitReport";
					document.actionForm.submit();

    }
        function viewItemDetails(_req)
        {
         if (_req.readyState == 4 && _req.status == 200) {
         document.getElementById('jsItems').innerHTML = _req.responseText;
         }
        
        }
  function viewReportDetails(_req) {
 //alert("viewReportDetails 1 called ");
     if (_req.readyState == 4 && _req.status == 200) {
                        document.getElementById('reportDetails').style.display="inline";
                        var xml = _req.responseXML;
			//	alert(xml);
				if(xml == null || xml.getElementsByTagName("ReportId")[0] == null) {
							xml = (new DOMParser()).parseFromString( _req.responseText,
"application/xml" ); 
				}
						//	alert("........."+xml);
					    document.getElementById('jsReportId').innerHTML=xml.getElementsByTagName("ReportId")[0].firstChild.nodeValue;
					    document.getElementById('reportNumberHidden').value=xml.getElementsByTagName("ReportId")[0].firstChild.nodeValue;
   					    document.getElementById('jsEmployeeId').innerHTML=xml.getElementsByTagName("EmployeeId")[0].firstChild.nodeValue;
					    document.getElementById('jsEmployeeName').innerHTML=xml.getElementsByTagName("EmployeeName")[0].firstChild.nodeValue;
					    document.getElementById('jsManagerName').innerHTML=xml.getElementsByTagName("ManagerName")[0].firstChild.nodeValue;
						var noOfItems = xml.getElementsByTagName("Item").length;
			//	alert("Items" +noOfItems);

						if(noOfItems > 0) {
						document.getElementById("itemDetails").style.display="inline";
						  var tbody = document.getElementById('itemTable').getElementsByTagName("TBODY")[0];
						  
						}
						var noOfRows = document.getElementById("noOfRows").value;
						var intNoOfRows = eval(noOfRows);
						//Remove Existing Rows
						for(var j = intNoOfRows;j>0;j--)
						{
							var tbody = document.getElementById('itemTable').getElementsByTagName("TBODY")[0];
							tbody.deleteRow(j);
						}

						document.getElementById("noOfRows").value = noOfItems;
						
						<%String userName =  (String)portletSession.getAttribute("userName");%>
    
					   var jsUserName = '<%=userName%>';
                                           var isManagerForReport = (jsUserName == xml.getElementsByTagName("ManagerName")[0].firstChild.nodeValue)?true:false;
				//	 alert("ismanager"+isManagerForReport);
				// alert(xml.getElementsByTagName("Status")[0].firstChild.nodeValue);
					 
						if(isManagerForReport & xml.getElementsByTagName("Status")[0].firstChild.nodeValue == "Pending" ) {
								document.getElementById("approveReport").style.display="inline";
								document.getElementById("rejectReport").style.display="inline";
						}
						else {
								document.getElementById("approveReport").style.display="none";
								document.getElementById("rejectReport").style.display="none";

						}
						if(xml.getElementsByTagName("Status")[0].firstChild.nodeValue == "Saved") {
							document.getElementById("submitReport").style.display="inline";
						}
						else {
							document.getElementById("submitReport").style.display="none";
						}

						for(var i = 0;i<noOfItems;i++){
						    var row = document.createElement("TR");
							var cell1 = document.createElement("TD");
						//	alert(xml.getElementsByTagName("ItemPurpose")[i].firstChild);
						//	alert(xml.getElementsByTagName("StartDate")[i].firstChild);
													//	alert(xml.getElementsByTagName("ItemAmount")[i].firstChild);
																					
							if(xml.getElementsByTagName("ItemPurpose")[i].firstChild != null) {
								
								cell1.innerHTML=xml.getElementsByTagName("ItemPurpose")[i].firstChild.nodeValue;
							}else {
							cell1.innerHTML = "";
							}

							
						
						var cell2 = document.createElement("TD");
						//	alert("SD"+xml.getElementsByTagName("StartDate")[i].firstChild);
							if(xml.getElementsByTagName("StartDate")[i].firstChild != null) {
								cell2.innerHTML=xml.getElementsByTagName("StartDate")[i].firstChild.nodeValue;
							}else {
								cell2.innerHTML = "";
							}
				

				/*			var cell3 = document.createElement("TD");
							if(xml.getElementsByTagName("EndDate")[i].firstChild != null) {
								cell3.innerHTML=xml.getElementsByTagName("EndDate")[i].firstChild.nodeValue;
							}else {
								cell3.innerHTML = "";
							}*/
							
							
						
							var cell4 = document.createElement("TD");
							if(xml.getElementsByTagName("ItemAmount")[i].firstChild != null) {
								cell4.innerHTML=xml.getElementsByTagName("ItemAmount")[i].firstChild.nodeValue;
							}else {
								cell4.innerHTML = "";
							}

						
				
							row.appendChild(cell1);
							row.appendChild(cell2);
							//row.appendChild(cell3);
							row.appendChild(cell4);
							tbody.appendChild(row);
						}
			


        }
    }
	


	function hideDetails() {
		document.getElementById('reportDetails').style.display="none";
	
	}



</script>
    

<jsp:useBean id="handler" scope="session" class="com.sun.portlet.expensereport.handler.ActionHandler"/>

<jsp:setProperty name="handler" property="user" value='<%=(String)portletSession.getAttribute("userId")%>'/>
<%!boolean bnIsWFEnabled;%>
<%
String strIsWFEnabled = renderRequest.getPreferences().getValue("WFEnabled", "");
bnIsWFEnabled = strIsWFEnabled.equals("true");
%>
<jsp:setProperty name="handler" property="isWFEnabled" value='<%=bnIsWFEnabled%>'/>

  <a href='<%=createURL.toString()%>'><b><u>Add a New Expense Report</b></u></a><br>

<h3><b><u>List of Saved/Submitted Expense Reports Raised by you</b></u></h3><br>
<h5>Click on the report to view details or to Submit Saved Report</h5>
     <div id="raisedReportsTable">
    <a:widget name="yahoo.dataTable"  subscribe="/table/topic"
       value="{columns : [
    
     { label : 'Report No.', id : 'reportId'},
     { label : 'Description', id : 'reportDesc'},
     { label : 'Submission Date(MM-DD-YY)', id : 'submissionDate'},
     { label :'Amount (USD)', id : 'amount'},
     { label : 'Status', id : 'status'}
     ],
    rows : ${handler.raisedReportsJSON}}" />
	</div>
    <%!boolean isManager;%>

    <%String role =  (String)portletSession.getAttribute("Role");
    System.out.println("Role "+role);
    isManager = (role.equals("Manager"))?true:false;
  
    
    %>
    
    <%if(isManager){%>
<h3><b><u>List of Pending Expense Reports Raised by Reportees</b></u></h3><br>
<h5>Click on the report to view details and to approve/reject</h5>
<div id="approvalReportsTable">
     <a:widget name="yahoo.dataTable"
      subscribe="/table/topic"
     value="{columns : [

     { label : 'Report No.', id : 'reportId'},
     { label : 'Description', id : 'reportDesc'},
     { label : 'Employee Name', id : 'employeeName'},
     { label : 'Submission Date(MM-DD-YY)', id : 'submissionDate'},
     { label :'Amount (USD)', id : 'amount'},
     { label : 'Status', id : 'status'}
     ],
    rows : ${handler.reportsToApproveJSON}}" />
    
    <%}%>
	</div>
	<br>
    <div id="reportDetails" style="display:none">
	<a href='javascript:hideDetails();'>Hide Details</a>
	<br>
	<b>Details for Report No. <div style="display:inline"  id="jsReportId"></div></b> <br>
	Submitter:&nbsp; <b><div style="display:inline" id="jsEmployeeName"> </div>(<div style="display:inline" id="jsEmployeeId"></div>)</b>
	&nbsp;&nbsp;&nbsp;&nbsp;
	Approver:&nbsp;<b> <div style="display:inline" id="jsManagerName"></div></b>
	<br>
 <br>
<div id = "itemDetails" style="display:none" >
<b>Item Details:</b><br>
<table id="itemTable" border = "1" cellspacing="8" >
<tbody>
<tr>
<th>Item Purpose</th>
<th>Date</th>
<!--<th>End Date</th>-->
<th>Amount(USD)</th>
</tr>
</tbody>
</table>


</div>




<form name="actionForm" id='actionForm' method="POST" action='<%=submitURL.toString()%>'>
<INPUT TYPE="hidden" NAME="noOfRows" value="0" id="noOfRows"/>
	<INPUT TYPE="hidden" NAME="reportNumber" id = "reportNumberHidden"/>
	<INPUT TYPE="hidden" NAME="action"  id = "action" value="submitReport"/>
	<input type="button" name="submitReport"  id="submitReport" style="display:none" value="Submit Report" onClick="javascript:subReport();" />
	<input type="button" name="approveReport"  id="approveReport"  style="display:none" value="Approve Report" onClick="javascript:appReport('true');"/>
     <input type="button" name="rejectReport"  id="rejectReport" style="display:none" value="Reject Report" onClick="javascript:appReport('false');"/>
</form>
    </div>
    
    <div id="jsItems" style="display:inline"></div>

    



		