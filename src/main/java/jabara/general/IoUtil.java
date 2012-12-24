/**
 * 
 */
package jabara.general;

import java.io.BufferedInputStream;
import java.io.InputStream;

/**
 * 
 * @author jabaraster
 */
public final class IoUtil {

    private IoUtil() {
        //
    }

    /**
     * @param pBase
     * @param pLocation
     * @return
     */
    @SuppressWarnings("resource")
    public static BufferedInputStream getResourceAsStream(final Class<?> pBase, final String pLocation) {
        final InputStream in = pBase.getResourceAsStream(pLocation);
        if (in == null) {
            throw new IllegalStateException("Resource not found. base -> " + pBase.getName() + ", location -> " + pLocation); //$NON-NLS-1$//$NON-NLS-2$
        }
        return (BufferedInputStream) (in instanceof BufferedInputStream ? in : new BufferedInputStream(in));
    }
}
