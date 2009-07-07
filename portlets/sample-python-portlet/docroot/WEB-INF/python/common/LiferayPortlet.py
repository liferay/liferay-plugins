from com.liferay.portal.kernel.util import Validator;

class LiferayPortlet:
    
    def __init__(self, renderRequest, renderResponse):
        self.renderRequest = renderRequest;
        self.renderResponse = renderResponse;
    
    def actionLink(self, text, params):
        return self.link(self.actionUrl(params), text);
    
    def actionUrl(self, params):
        portletUrl = self.renderResponse.createActionURL();
    
        return self.url(portletUrl, params);
    
    def link(self, url, text):
        return "<a href='" + url + "'>" + text + "</a>";
    
    def renderLink(self, text, target, params):
        return self.link(self.renderUrl(target, params), text);
    
    def renderUrl(self, target, params):
        portletUrl = self.renderResponse.createRenderURL();
    
        if (Validator.isNotNull(target)):
            portletUrl.setParameter("pythonFile", target);
    
        return self.url(portletUrl, params);
    
    def showPortletDetails(self, portletConfig):
        return """
            <table class='liferay-table'>
            <tr>
                <td>Portlet Name:</td>
                <td> """ + portletConfig.getPortletName() + """</td>
            </tr>
            <tr>
                <td>Preferences:</td>
                <td>""" + str(self.renderRequest.getPreferences().getMap()) + """</td>
            </tr>
            <tr>
                <td>Parameters:</td>
                <td>""" + str(self.renderRequest.getParameterMap()) + """</td>
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