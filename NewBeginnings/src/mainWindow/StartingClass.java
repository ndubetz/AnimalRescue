package mainWindow;

public class StartingClass {
	public StartingClass() {
		MainModel model = new MainModel();
		MainView view = new MainView();

		MainController controller = new MainController();
		controller.addModel(model);
		controller.addView(view);

		view.addController(controller);
	}
}
