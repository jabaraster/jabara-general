/**
 * 
 */
package jabara.general.io;

import java.io.OutputStream;

/**
 * @author jabaraster
 */
public class BlackHole extends OutputStream {
    /**
     * 
     */
    public static BlackHole INSTANCE = new BlackHole();

    /**
     * @see java.io.OutputStream#write(int)
     */
    @Override
    public void write(@SuppressWarnings("unused") final int pB) {
        // nop
    }
}