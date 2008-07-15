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
     
     