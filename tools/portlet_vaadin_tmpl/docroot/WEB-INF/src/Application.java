package @portlet.java.package.name@;

public class @portlet.java.class.name@Application extends Application {

	public void init() {
		Window window = new Window();

		setMainWindow(window);

		Label label = new Label("Hello @portlet.java.class.name@!");

		window.addComponent(label);
	}

}