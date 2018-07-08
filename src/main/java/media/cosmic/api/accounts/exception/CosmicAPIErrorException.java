package media.cosmic.api.accounts.exception;

/**
 * Exception to be thrown when an API response has an error status
 * @author termer
 * @since v2.0.0
 */
public class CosmicAPIErrorException extends CosmicException {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates a new CosmicAPIErrorException with the specified message
	 * @param msg the exception's message
	 * @since v2.0.0
	 */
	public CosmicAPIErrorException(String msg) {
		super(msg);
	}
}
