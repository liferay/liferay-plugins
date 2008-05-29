from javax.portlet.PortletPreferences import *;
from javax.portlet.RenderRequest import *;
from javax.portlet.RenderResponse import *;

class Custom:
    def __init__(self, req, res):
        self.req = req;
        self.res = res;
        self.lp = LiferayPortlet(req, res);

    def sayHello(self):
        return """
            <div>
                <b>Hello Python Portlet users!</b>
            </div>
        """;
    
    def navigation(self):
        return '\n\
            <div class="separator"></div>\n\n\
            <div>\n\
                Navigation:\n\
                \n' + self.lp.renderLink("Home", "/WEB-INF/python/view.py", {}) + " - " + \
                self.lp.renderLink("User Info", "/WEB-INF/python/info.py", {'type' : 'user'}) + " - " + \
                self.lp.renderLink("Portlet Info", "/WEB-INF/python/info.py", {'type' : 'portlet'}) + " - " + \
                self.lp.renderLink("Invoke Java", "/WEB-INF/python/java.py", {}) + '\n\
            </div>\n\
        ';
    
    def showNumber(self):
        prefs = self.req.getPreferences();
    
        num = prefs.getValue("number", "0");
        actionLink = self.lp.actionLink("Increment", {});
    
        return '<div>\n\
                #Number: ' + num + ' (' + actionLink + ')\n\
            </div>\n\
        ';