package media.cosmic.api.accounts.calls;

import java.io.IOException;

import org.jsoup.Jsoup;

import media.cosmic.api.accounts.CosmicAccounts;
import media.cosmic.api.accounts.exception.CosmicAPIErrorException;
import media.cosmic.api.accounts.exception.CosmicVersionMismatchException;
import media.cosmic.api.accounts.models.CheckTokenValidationModel;
import media.cosmic.api.accounts.models.CosmicAccountModel;
import media.cosmic.api.accounts.models.FetchUserInfoModel;
import media.cosmic.api.accounts.models.StartAuthSessionModel;
import media.cosmic.api.accounts.utils.StringFilter;

/**
 * Utility to class to define, retrieve, and make API calls
 * @author termer
 * @since v2.0.0
 */
public class CosmicAPICall {
	// Available API calls
	
	/**
	 * API call that initiates a session.
	 * Requires two parameters:<br>
	 *  - Private Key<br>
	 *  - Callback URL<br>
	 *  Uses StartAuthSessionModel model for responses.
	 *  @since v2.0.0
	 */
	public static CosmicAPICall START_AUTH_SESSION = new CosmicAPICall(
			"v%v/start_auth_session?private_key=%p1&callback_url=%p2",
			new StartAuthSessionModel());
	
	/**
	 * API call that returns the info for a user
	 * Requires two parameters:<br>
	 *  - Private Key<br>
	 *  - Token<br>
	 *  Uses FetchUserInfoModel model for responses.
	 *  @since v2.0.0
	 */
	public static CosmicAPICall FETCH_USER_INFO = new CosmicAPICall(
			"v%v/fetch_user_info?private_key=%p1&token=%p2",
			new FetchUserInfoModel());
	
	/**
	 * API call that checks whether the user has authenticated and
	 * accepted permissions for the token.
	 * Requires one parameter:<br>
	 *  - Token<br>
	 *  Uses CheckTokenValidationModel model for responses.
	 *  @since v2.0.0
	 */
	public static CosmicAPICall CHECK_TOKEN_VALIDATION = new CosmicAPICall(
			"v%v/check_token_validation?token=%p1",
			new CheckTokenValidationModel());
	
	
	// Private variables for individual CosmicAPICall instances
	private String _PATH_ = null;
	private CosmicAccountModel _MODEL_ = null;
	
	/**
	 * Creates a new CosmicAPICall object
	 * @param path the API path
	 * @param model the response model
	 * @since v2.0.0
	 */
	public CosmicAPICall(String path, CosmicAccountModel model) {
		_PATH_ = path;
		_MODEL_ = model;
	}
	
	/**
	 * Executes the specified API call and returns the response
	 * @param call the call to execute
	 * @param params the parameters to provide
	 * @return the call's response
	 * @throws IOException if connecting to the server fails
	 * @throws CosmicVersionMismatchException if the API version is diffent from the internal API version
	 * @throws CosmicAPIErrorException if the API response returns an error
	 * @since v2.0.0
	 */
	public static CosmicAccountModel execute(CosmicAPICall call, String[] params) throws CosmicVersionMismatchException, IOException, CosmicAPIErrorException {
		CosmicAccountModel response = null;
		
		String tmpPath = call.getAPIPath();
		
		// Replace parameter flags
		boolean available = true;
		int index = 1;
		while(available) {
			if(tmpPath.contains("%p"+Integer.toString(index))) {
				// Get parameter to replace
				String repl = "null";
				if(params.length >= index) {
					repl = params[index-1];
					if(repl==null) {
						repl = "null";
					} else {
						repl = StringFilter.encodeURIComponent(repl);
					}
				}
				
				// Replace parameter flag with parameter
				tmpPath = tmpPath.replace("%p"+Integer.toString(index), repl);
				index++;
			} else {
				available = false;
			}
		}
		
		// Replace version flag
		tmpPath = tmpPath.replace("%v", CosmicAccounts.getAPIVersionString());
		
		tmpPath = CosmicAccounts.getBaseURL()+tmpPath;
		
		String json = Jsoup.connect(tmpPath).ignoreContentType(true).execute().body();
		
		response = CosmicAccounts.getGson().fromJson(json, call.getModel().getClass());
		
		// Check for error
		if(response != null) {
			if(StringFilter.same(response.status, "error")) {
				throw new CosmicAPIErrorException("API returned error: "+response.error);
			}
		}
		
		return response;
	}
	
	/**
	 * Returns the response model for this call
	 * @return this call's response model
	 * @since v2.0.0
	 */
	public CosmicAccountModel getModel() {
		return _MODEL_;
	}
	
	/**
	 * Returns the API path of this call.
	 * Paths contain flags such as %p1, %p2, ect which are replaced
	 * with provided parameters when executed, as well as %v which
	 * is replaced with the current API version.
	 * @return the API path of this call
	 * @since v2.0.0
	 */
	public String getAPIPath() {
		return _PATH_;
	}
	
	/**
	 * Executes this CosmicAPICall and returns the response model object
	 * @param params the parameters to pass it
	 * @return the response as a response model object
	 * @throws IOException if connecting to the server fails
	 * @throws CosmicVersionMismatchException  if the API version does not match the internal API version
	 * @throws CosmicAPIErrorException if the API response returns an error
	 * @since v2.0.0
	 */
	public CosmicAccountModel execute(String[] params) throws CosmicVersionMismatchException, IOException, CosmicAPIErrorException {
		return CosmicAPICall.execute(this, params);
	}
}
