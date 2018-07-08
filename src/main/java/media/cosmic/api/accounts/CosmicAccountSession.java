package media.cosmic.api.accounts;

import java.io.IOException;

import media.cosmic.api.accounts.calls.CosmicAPICall;
import media.cosmic.api.accounts.exception.CosmicAPIErrorException;
import media.cosmic.api.accounts.exception.CosmicAuthenticationException;
import media.cosmic.api.accounts.exception.CosmicVersionMismatchException;
import media.cosmic.api.accounts.models.FetchUserInfoModel;
import media.cosmic.api.accounts.models.CheckTokenValidationModel;
import media.cosmic.api.accounts.models.StartAuthSessionModel;
import media.cosmic.api.accounts.utils.CosmicUserInfo;

/**
 * Connection class for CosmicMedia accounts API
 * @author termer
 * @since v2.0.0
 */
public class CosmicAccountSession {
	// Private key
	private String _PRIVATE_KEY_ = null;
	
	// Callback URL
	private String _CALLBACK_ = null;
	
	// Session token
	private String _TOKEN_ = null;
	
	// Whether this session is authorized
	private boolean _AUTHORIZED_ = false;
	
	protected CosmicAccountSession(String privKey, String callback) throws CosmicAPIErrorException, CosmicVersionMismatchException, IOException {
		if(privKey==null || callback==null) {
			throw new IllegalArgumentException("Private key or callback is null");
		} else {
			_PRIVATE_KEY_ = privKey;
			_CALLBACK_ = callback;
		}
		
		// Retrieve token
		_TOKEN_ = ((StartAuthSessionModel)CosmicAPICall.START_AUTH_SESSION.execute(new String[] {privKey, callback})).key;
	}
	
	/**
	 * Returns this session's token
	 * @return this session's token
	 * @since v2.0.0
	 */
	public String getToken() {
		return _TOKEN_;
	}
	
	/**
	 * Returns this session's private key
	 * @return this session's private key
	 * @since v2.0.0
	 */
	public String getPrivateKey() {
		return _PRIVATE_KEY_;
	}
	
	/**
	 * Returns this session's callback URL
	 * @return this session's callback URL
	 * @since v2.0.0
	 */
	public String getCallbackURL() {
		return _CALLBACK_;
	}
	
	/**
	 * Returns the authorization URL a user must visit to authorize this session
	 * @return the authorization URL
	 * @since v2.0.0
	 */
	public String getAuthorizationURL() {
		return CosmicAccounts.getBaseURL().replace("api/", "")+"auth?q="+_TOKEN_;
	}
	
	/**
	 * Returns whether this session has been authorized
	 * @return whether this session has been authorized
	 * @throws CosmicVersionMismatchException if the API version is different from the internal API version
	 * @throws CosmicAPIErrorException if the API response returns and error
	 * @throws IOException if connecting to the server fails
	 * @since v2.0.0
	 */
	public boolean isAuthorized() throws CosmicVersionMismatchException, CosmicAPIErrorException, IOException {
		_AUTHORIZED_ = ((CheckTokenValidationModel)CosmicAPICall.CHECK_TOKEN_VALIDATION.execute(new String[] {_TOKEN_})).validated;
		return _AUTHORIZED_;
	}
	
	/**
	 * Returns a CosmicUserInfo object for this user
	 * @return this user's info
	 * @throws CosmicVersionMismatchException if the API version is different from the internal API version
	 * @throws CosmicAPIErrorException if the API response returns an error
	 * @throws IOException if connecting to the server fails
	 * @throws CosmicAuthenticationException if this session has not been authorized
	 * @since v2.0.0
	 */
	public CosmicUserInfo fetchUserInfo() throws CosmicVersionMismatchException, CosmicAPIErrorException, IOException, CosmicAuthenticationException {
		boolean authed = false;
		if(_AUTHORIZED_==false) {
			authed = isAuthorized();
		}
		
		CosmicUserInfo info = new CosmicUserInfo();
		
		if(authed) {
			FetchUserInfoModel model = ((FetchUserInfoModel)CosmicAPICall.FETCH_USER_INFO.execute(new String[] {_PRIVATE_KEY_, _TOKEN_}));
			
			// Set values
			info.setBirthday(model.birthdate);
			info.setCosmicID(model.cosmic_id);
			info.setCountry(model.country);
			info.setEmail(model.email);
			info.setFullName(model.fullname);
			info.setGender(model.gender);
			info.setPhoneNumber(model.phone);
			info.setUsername(model.username);
		} else {
			throw new CosmicAuthenticationException("Session is not authorized by user");
		}
		
		return info;
	}
}