package media.cosmic.api.accounts.exception;

/**
 * Exception to be thrown when there is an authentication problem
 * @author termer
 * @since v2.0.0
 */
public class CosmicAuthenticationException extends CosmicException {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates a new CosmicAuthenticationException with the specified message
	 * @param msg the exception's message
	 * @since v2.0.0
	 */
	public CosmicAuthenticationException(String msg) {
		super(msg);
	}
}
