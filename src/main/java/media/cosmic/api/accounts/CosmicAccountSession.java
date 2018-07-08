package media.cosmic.api.accounts;

import java.io.IOException;

import media.cosmic.api.accounts.calls.CosmicAPICall;
import media.cosmic.api.accounts.exception.CosmicAPIErrorException;
import media.cosmic.api.accounts.exception.CosmicVersionMismatchException;
import media.cosmic.api.accounts.models.StartAuthSessionModel;

/**
 * Connection class for CosmicMedia accounts API
 * @author termer
 * @since v2.0.0
 */
public class CosmicAccountSession {
	private String _PRIVATE_KEY_ = null;
	private String _CALLBACK_ = null;
	
	private String _TOKEN_ = null;
	
	private boolean _ACCEPTED_ = false;
	
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
	 * Returns the authorization URL a user must visit to authorize this session
	 * @return the authorization URL
	 * @since v2.0.0
	 */
	public String getAuthorizationURL() {
		return CosmicAccounts.getBaseURL().replace("api/", "")+"auth?q="+_TOKEN_;
	}
}