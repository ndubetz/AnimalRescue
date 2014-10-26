package database;
import java.util.List;

import model.Cat;

/**
 * Interface into however we choose to
 * store the cat data or any fake
 * cats
 */
public interface IAnimalDatabase
{
	/**
	 * Retrieves the list of cats that match
	 * the current search parameters from
	 * the database
	 * 
	 * @return the list of cats
	 */
	public List<Cat> getFilteredCats(SearchFilterType filterType, String filter);
	
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