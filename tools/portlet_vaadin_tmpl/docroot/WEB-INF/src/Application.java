package @portlet.java.package.name@;

import com.vaadin.Application;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

public class @portlet.java.class.name@Application extends Application {

	public void init() {
		Window window = new Window();

		setMainWindow(window);

		window.addComponent(new Label("Hello @portlet.java.class.name@!"));
	}

}