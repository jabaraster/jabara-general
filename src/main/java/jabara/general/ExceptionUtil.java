/**
 * 
 */
package jabara.general;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutionException;

/**
 * @author jabaraster
 */
public class ExceptionUtil {

    /**
     * @param pCause -
     * @return 必ず例外がスローされるため、実際に戻り値を返すことはありません.
     */
    public static RuntimeException rethrow(final Throwable pCause) {
        if (pCause instanceof RuntimeException) {
            throw (RuntimeException) pCause;
        }
        if (pCause instanceof Error) {
            throw (Error) pCause;
        }
        if (InvocationTargetException.class.equals(pCause)) {
            return rethrow(((InvocationTargetException) pCause).getTargetException());
        }
        if (ExecutionException.class.equals(pCause)) {
            return rethrow(((ExecutionException) pCause).getCause());
        }
        throw new IllegalStateException(pCause);
    }

}
