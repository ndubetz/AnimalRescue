package model;

/**
 * Specifies the data being described
 * by the search string
 */
public enum SearchFilterType
{
	Name,
	Color;
	
	public String toString()
	{
		switch(this)
		{
		case Name:
			return "Name";
		case Color:
			return "Color";
		default:
			return "";
		}
	}
}