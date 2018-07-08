package media.cosmic.api.accounts;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import media.cosmic.api.accounts.exception.CosmicAPIErrorException;
import media.cosmic.api.accounts.exception.CosmicException;
import media.cosmic.api.accounts.exception.CosmicVersionMismatchException;
import media.cosmic.api.accounts.models.VersionModel;

/**
 * Main CosmicMedia accounts API class.
 * @author termer
 * @since v2.0.0
 */
public class CosmicAccounts {
	// The API version this library was designed for
	private static double _INTERNAL_VERSION_ = 2;
	
	// The base API url
	private static String _API_URL_ = "https://accounts.cosmic.media/api/";
	// The API returned from the server
	private static double _API_VERSION_ = -1;
	
	// The Gson object
	private static Gson _GSON_ = new GsonBuilder().create();
	
	/**
	 * Creates a new CosmicAccountConnection
	 * @param privKey the API private key
	 * @param callback the callback URL
	 * @return a new CosmicAccountConnection
	 * @throws IOException if connecting to the server fails
	 * @throws CosmicAPIErrorException if the API response returns an error
	 * @since v2.0.0
	 */
	public static CosmicAccountConnection createConnection(String privKey, String callback) throws IOException, CosmicAPIErrorException {
		return new CosmicAccountConnection(privKey, callback);
	}
	
	/**
	 * Returns the base API URL
	 * @return the base API URL
	 * @since v2.0.0
	 */
	public static String getBaseURL() {
		return _API_URL_;
	}
	
	/**
	 * Sets the base API URL, for example:<br>
	 * <code>https://accounts.cosmic.media/api/</code>
	 * @param url the new base URL
	 * @throws MalformedURLException if the provided URL is invalid
	 * @since v2.0.0
	 */
	public static void setBaseURL(String url) throws MalformedURLException {
		// Throws an exception if the URL is malformed
		new URL(url);
		
		// If no exception is throw, set URL
		_API_URL_ = url;
	}
	
	/**
	 * Returns the Gson object used by JavaCosmicAccountsAPI
	 * @return the shared Gson object
	 * @since v2.0.0
	 */
	public static Gson getGson() {
		return _GSON_;
	}
	
	/**
	 * Returns the current API version, and if not present, retrieves it from the API server
	 * @return the current API version
	 * @throws IOException if retrieving the current version from the server fails
	 * @throws CosmicVersionMismatchException if the current API version does not match the version this library was built for
	 * @since v2.0.0
	 */
	public static double getAPIVersion() throws IOException, CosmicVersionMismatchException {
		if(_API_VERSION_ == -1) {
			// Fetch API version from server
			String json = Jsoup.connect(_API_URL_+"version")
					.ignoreContentType(true)
					.execute()
					.body();
			
			VersionModel verModel = getGson().fromJson(json, VersionModel.class);
			_API_VERSION_ = verModel.version;
		}
		if(_API_VERSION_ != _INTERNAL_VERSION_) {
			// Throw exception for version mismatch
			throw new CosmicVersionMismatchException(
					"This library is made for version "+Double.toString(_INTERNAL_VERSION_)+
					", server returned version "+Double.toString(_API_VERSION_)
					);
		}
		
		return _API_VERSION_;
	}
	
	/**
	 * Returns the current API version as a String.
	 * Should be used in API URLs, as it does not include the
	 * trailing ".0" on non-double API versions.
	 * @return the current API version as a String
	 * @throws CosmicVersionMismatchException if the current API version does not match the version this library was built for
	 * @throws IOException if retrieving the current version from the server fails
	 * @since v2.0.0
	 */
	public static String getAPIVersionString() throws CosmicVersionMismatchException, IOException {
		String ver = Double.toString(getAPIVersion());
		
		// Removes the trailing .0 on integer API versions
		if(ver.endsWith("0")) {
			ver = ver.substring(0, ver.indexOf('.'));
		}
		
		return ver;
	}
	
	/**
	 * Returns the API version this library was built for
	 * @return the API version this library was built for
	 * @since v2.0.0
	 */
	public static double getInternalAPIVersion() {
		return _INTERNAL_VERSION_;
	}
}