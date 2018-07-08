package media.cosmic.api.accounts;

import media.cosmic.api.accounts.exception.CosmicAPIErrorException;

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
	
	protected CosmicAccountSession(String privKey, String callback) throws CosmicAPIErrorException {
		if(privKey==null || callback==null) {
			throw new IllegalArgumentException("Private key or callback is null");
		} else {
			_PRIVATE_KEY_ = privKey;
			_CALLBACK_ = callback;
		}
		
		// Retrieve token
		
	}
}