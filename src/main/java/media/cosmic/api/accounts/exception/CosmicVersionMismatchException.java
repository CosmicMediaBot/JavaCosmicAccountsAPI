package media.cosmic.api.accounts.exception;

/**
 * Exception to be thrown when the API version returned by the server does not
 * match the API version this library was designed for
 * @author termer
 * @since v2.0.0
 */
public class CosmicVersionMismatchException extends CosmicException {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates a new CosmicVersionMismatchException with the specified message
	 * @param msg the exception's message
	 * @since v2.0.0
	 */
	public CosmicVersionMismatchException(String msg) {
		super(msg);
	}
}
