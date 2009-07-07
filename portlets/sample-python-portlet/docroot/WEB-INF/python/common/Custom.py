from javax.portlet.PortletPreferences import *;
from javax.portlet.RenderRequest import *;
from javax.portlet.RenderResponse import *;

class Custom:
    def __init__(self, renderRequest, renderResponse):
        self.renderRequest = renderRequest;
        self.renderResponse = renderResponse;
        self.lp = LiferayPortlet(renderRequest, renderResponse);

    def sayHello(self):
        return """
            <div>
                <b>Hello Python Portlet users!</b>
            </div>
        """;
    
    def navigation(self):
        nav = '\n';
        nav += '<div class="separator"></div>\n\n';
        nav += '<div>\n';
        nav += 'Navigation:\n';
        nav += '\n' + self.lp.renderLink("Home", "/WEB-INF/python/view.py", {}) + " - ";
        nav += self.lp.renderLink("User Info", "/WEB-INF/python/info.py", {'type' : 'user'}) + " - ";
        nav += self.lp.renderLink("Portlet Info", "/WEB-INF/python/info.py", {'type' : 'portlet'}) + " - ";
        nav += self.lp.renderLink("Invoke Java", "/WEB-INF/python/java.py", {}) + '\n';
        nav += '</div>\n';

        return nav;
    
    def showNumber(self):
        preferences = self.renderRequest.getPreferences();
    
        num = preferences.getValue("number", "0");
        actionLink = self.lp.actionLink("Increment", {});
    
        return '<div>#Number: ' + num + ' (' + actionLink + ')</div>';