<%@ taglib prefix="a" uri="http://jmaki/v1.0/jsp" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>


<%@ page import="javax.portlet.*"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>

<portlet:defineObjects />
<portlet:actionURL var="submitURL"/>
<script language="javascript">
function toggleMySQL() {
	alert("called");
	if(document.getElementById("mysqlradio").checked) {
	document.getElementById("mysqlproperties").style.display="inline";
	} else if(document.getElementById("hsqlradio").checked) {
		document.getElementById("mysqlproperties").style.display="none";
	}

}
</script>
    
    Do you wish to enable Workflow Implementation using JCAPS and SAW for this application? <br><br>
    <form name="form1" method="POST" action='<%=submitURL.toString()%>'>
     <INPUT TYPE="radio" NAME="WFEnabled" value="true" /><b>Enable Workflow Engine(JCAPS)</b> <br>
     <INPUT TYPE="radio" NAME="WFEnabled" value="false"/><b>Disable Workflow Engine(JCAPS)</b>
	 <br><br>
	<!--	Type of Database to be used:<br><br>
     <INPUT TYPE="radio" NAME="DB_TYPE" value="MySQL"  id="mysqlradio" onclick="javascript:toggleMySQL();"/><b>MySQL</b> <br>
	 <div id ="mysqlproperties" style="display:none">
	 &nbsp;&nbsp;<b>MySQL Connection URL:</b> <input type="text" id="mySQLURL" name="mySQLURL" value="" /><br>
	 (eg) jdbc:mysql://localhost:3306/lportal<br>
	 	 &nbsp;&nbsp;<b>MySQL Username:&nbsp;&nbsp;</b><input type="text" id="mySQLUsername" name="mySQLUsername" value="" /><br>
		&nbsp;&nbsp;<b>MySQL Password:&nbsp;&nbsp;</b><input type="text" id="mySQLPwd" name="mySQLPwd" value=""/><br>
		</div>
     <INPUT TYPE="radio" NAME="DB_TYPE"  id="hsqlradio" value="HSQL" onclick="javascript:toggleMySQL();"/><b>Embedded HSQL</b>-->
     <input type="hidden" name="action" value="setWFPreference"/><br><br>
     <input type="submit" name="gotosummary" value="Set Preference"/>
 </form>
     
     