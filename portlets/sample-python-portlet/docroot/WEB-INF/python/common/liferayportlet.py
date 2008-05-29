from com.liferay.portal.kernel.util.Validator import *;

from javax.portlet.PortletConfig import *;
from javax.portlet.PortletURL import *;
from javax.portlet.RenderResponse import *;



def actionLink(self, res, text, params):
    return self.link(actionUrl(res, params), text);

def actionUrl(self, res, params):
    portletUrl = res.createActionURL();

    return self.url(portletUrl, params);

def link(self, url, text):
    return "<a href='" + url + "'>" + text + "</a>";

def renderLink(self, res, text, target, params):
    return self.link(renderUrl(res, target, params), text);

def renderUrl(self, res, target, params):
    portletUrl = res.createRenderURL();

    if (Validator.isNotNull(target)):
        portletUrl.setParameter("pythonFile", target);

    return url(portletUrl, params);

def showPortletDetails(self, portletConfig, req):
    return """
        <table class='liferay-table'>
        <tr>
            <td>Portlet Name:</td>
            <td> """ + portletConfig.getPortletName() + """</td>
        </tr>
        <tr>
            <td>Preferences:</td>
            <td>""" + req.getPreferences().getMap() + """</td>
        </tr>
        <tr>
            <td>Parameters:</td>
            <td>""" + req.getParameterMap() + """</td>
        </tr>
        </table>
    """;

def showUserDetails(self, userInfo):
    return """
        <table class='liferay-table'>
        <tr>
            <td>User ID:</td>
            <td>""" + userInfo['liferay.user.id'] + """</td>
        </tr>
        <tr>
            <td>First Name:</td>
            <td>""" + userInfo['user.name.given'] + """</td>
        </tr>
        <tr>
            <td>Last Name:</td>
            <td>""" + userInfo['user.name.family'] + """</td>
        </tr>
        <tr>
            <td>Email Address:</td>
            <td>""" + userInfo['user.home-info.online.email'] + """</td>
        </tr>
        </table>
    """;

def url(self, portletUrl, params):
    for key, value in params.iteritems():
        portletUrl.setParameter(key, value);

    return portletUrl.toString();
