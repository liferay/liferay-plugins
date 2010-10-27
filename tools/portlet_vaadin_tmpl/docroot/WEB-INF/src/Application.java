package @portlet.name@;

import com.vaadin.Application;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

public class @portlet.display.java.name@Application extends Application {

	public void init() {
		Window w = new Window();
		setMainWindow(w);
		w.addComponent(new Label("Hello @portlet.display.java.name@!"));		
	}

}

