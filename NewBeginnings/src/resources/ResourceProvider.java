package resources;

import java.io.InputStream;

/*
 * Provides access to project resources.Defining this allows for cross-
 * package and cross-project access to resources saved in the resources 
 * package of the NewBeginnings project.
 */
public class ResourceProvider 
{
	/**
	 * Gets the specified resource
	 * 
	 * @param resourcePath the path to the resource
	 * @return the resource input stream
	 */
	public InputStream getResourceStream(String resourcePath)
	{
		return getClass().getResourceAsStream(resourcePath);
	}
}
