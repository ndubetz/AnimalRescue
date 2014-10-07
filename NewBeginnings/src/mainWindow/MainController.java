package mainWindow;

class MainController implements java.awt.event.ActionListener{
	MainView view;
	MainModel model;
	
	public MainController() {
		System.out.println("Controller");
	}

	@Override
	public void actionPerformed(java.awt.event.ActionEvent e) {
		model.newAnimal();	
	}

	public void addModel(MainModel model){
		System.out.println("adding model");
		this.model = model;
	}
	
	public void addView(MainView view){
		System.out.println("adding view");
		this.view = view;
	}
	
}
