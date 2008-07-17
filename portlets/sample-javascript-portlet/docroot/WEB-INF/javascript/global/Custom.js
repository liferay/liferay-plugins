function Custom() {}

Custom.navigation = function(res) { 

	var user_info = new Object(); user_info['type'] = 'user';
	var portlet_info = new Object(); portlet_info['type'] = 'portlet';
	
	infoFilePath = '/WEB-INF/javascript/info.js';
	
	return "\n\
		<div class='separator'></div>\n\n\
		<div>\n\
			Navigation:\n\n" + 
			LiferayPortlet.renderLink(res, "Home", "/WEB-INF/javascript/view.js", new Object())  + " - " +
			LiferayPortlet.renderLink(res, "User Info", infoFilePath, user_info) + " - " +
			LiferayPortlet.renderLink(res, "Portlet Info", infoFilePath, portlet_info) + " - " +
			LiferayPortlet.renderLink(res, "Invoke Java", "/WEB-INF/javascript/java.js", new Object()) + "\n\
		</div>\n";
}

Custom.sayHello = function() {
	return "\n\
		<div>\n\
			<b>Hello Javascript Portlet users!</b>\n\
		</div>\n";
}

Custom.showNumber = function(req, res) {
	var prefs = req.getPreferences();

	var num = prefs.getValue("number", "0");

	return "\n\
		<div>\n\
			Number: " + num + " (" + LiferayPortlet.actionLink(res, "Increment", new Object()) + ")\n\
		</div>\n";
}

