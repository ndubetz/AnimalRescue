package hostView;

public class CatHistoryViewController {

	private final AnimalInfoViewController animalInfoViewController;
	private CatHistoryView catHistoryView = null;

	public CatHistoryViewController(
			AnimalInfoViewController animalInfoViewController) {
		this.animalInfoViewController = animalInfoViewController;
	}

	public CatHistoryView buildView() {
		this.catHistoryView = new CatHistoryView(null);
		return this.catHistoryView;
	}

}
