package hostView;

public class CatHistoryViewController {

	@SuppressWarnings("unused")
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
