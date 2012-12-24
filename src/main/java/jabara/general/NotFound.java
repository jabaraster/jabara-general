/**
 * 
 */
package jabara.general;

/**
 * 
 * @author jabaraster
 */
public class NotFound extends Exception {
    private static final long  serialVersionUID = 5937606718632906327L;

    /**
     * 
     */
    @SuppressWarnings("synthetic-access")
    public static final Global GLOBAL           = new Global();

    /**
     * 
     */
    public NotFound() {
        //
    }

    /**
     * @param pMessage
     */
    public NotFound(final String pMessage) {
        super(pMessage);
    }

    /**
     * @author jabaraster
     */
    public static final class Global extends NotFound {
        private static final long serialVersionUID = 136537340442992668L;

        /**
         * 
         */
        private Global() {
            super("This is global instance."); //$NON-NLS-1$
        }
    }
}
