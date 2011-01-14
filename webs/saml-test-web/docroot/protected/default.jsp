<%@ page import="com.liferay.saml2.WebKeys" %>
<%@ page import="com.liferay.saml2.session.SPSession" %>
<%@ page import="com.liferay.saml2.util.PropsValues" %>

<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<%@ page import="org.opensaml.xml.XMLObject" %>
<%
	SPSession spSession = (SPSession)request.getAttribute(WebKeys.SP_SESSION);
%>
<p>
Authenticated user: <%= spSession.getNameID() %><br/>
Session valid until: <%= spSession.getNotOnOrAfter() %>
</p>
<p>Attributes:</p>
<%
	for (String key : spSession.getAttributeKeys()) {
		%><%= key %>: <%
		for (XMLObject value : spSession.getAttribute(key)) {
			%> <%= value.getDOM().getTextContent() %> <%
		}
		%><br/><%
	}
%>
<p>
<a href="<%= request.getContextPath() %>">Back to Home</a> <a href="<%= request.getContextPath() %>/Logout">Logout from Application</a>
</p>
<script type="text/javascript" src="<%= PropsValues.SAML_IDP_KEEPALIVE_URL %>?entityId=<%= URLEncoder.encode(PropsValues.SAML_SP_ENTITY_ID, "UTF-8") %>"></script>