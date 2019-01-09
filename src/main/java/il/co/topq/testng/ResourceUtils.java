package il.co.topq.testng;

import java.io.IOException;
import java.util.Scanner;

/**
 * Utility class for reading files from the classpath
 * 
 * @author Itai Agmon
 *
 */
public class ResourceUtils {

	private ResourceUtils() {
		// Utility class
	}

	/**
	 * Get resource from the class path as string
	 * 
	 * @param resourceName The name of the resource file
	 * @return resource as string
	 * @throws IOException
	 */
	static String resourceToString(final String resourceName) throws IOException {
		try (Scanner s = new Scanner(
				new ResourceUtils().getClass().getClassLoader().getResourceAsStream(resourceName))) {
			s.useDelimiter("\\A");
			return s.hasNext() ? s.next() : "";
		}
	}

}
