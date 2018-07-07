package media.cosmic.api.accounts.exception;

/**
 * Exception that occurs when interacting with a CosmicMedia API
 * @author termer
 * @since v2.0.0
 */
public class CosmicException extends Exception {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates a new CosmicException with the specified message
	 * @param msg the exception's message
	 * @since v2.0.0
	 */
	public CosmicException(String msg) {
		super(msg);
	}
}