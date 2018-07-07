package media.cosmic.api.accounts;

import media.cosmic.api.accounts.exception.CosmicException;

/**
 * Connection class for CosmicMedia accounts API
 * @author termer
 * @since v2.0.0
 */
public class CosmicAccountConnection {
	private String _PRIVATE_KEY_ = null;
	private String _CALLBACK_ = null;
	
	private String _TOKEN_ = null;
	
	private boolean _ACCEPTED_ = false;
	
	protected CosmicAccountConnection(String privKey, String callback) throws CosmicException {
		if(privKey==null || callback==null) {
			throw new CosmicException("Private key or callback is null");
		} else {
			_PRIVATE_KEY_ = privKey;
			_CALLBACK_ = callback;
		}
		
		
	}
}