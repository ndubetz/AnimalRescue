package model;
import java.util.List;

/**
 * Interface into however we choose to
 * store the cat data or any fake
 * cats
 */
public interface IAnimalDatabase
{
	/**
	 * Sets the filter type 
	 *
	 * @param filterType the type of filter
	 */
	public void setSearchFilterType(SearchFilterType filterType);

	/**
	 * Sets the filter string
	 * 
	 * @param filter the filter string
	 */
	public void setSearchFiler(String filter);
	
	/**
	 * Retrieves the list of cats that match
	 * the current search parameters from
	 * the database
	 * 
	 * @return the list of cats
	 */
	public List<Cat> getFilteredCats();
	
	/**
	 * Gets the single cat from the database
	 * with the given cat ID
	 * 
	 * @param catID
	 * @return
	 */
	public Cat getSingleCat(String catID);
	
	/**
	 * Adds a new cat to the database
	 * 
	 * @param cat the new cat
	 */
	public void addNewCat(Cat cat);
	
	/**
	 * Update a cat already in the database
	 * 
	 * @param cat the cat to update
	 */
	public void updateCat(Cat cat);
}