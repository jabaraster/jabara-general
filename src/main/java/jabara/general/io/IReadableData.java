/**
 * 
 */
package jabara.general.io;

import java.io.InputStream;

/**
 * @author jabaraster
 */
public interface IReadableData {

    /**
     * @return -
     */
    String getContentType();

    /**
     * @return -
     */
    InputStream getInputStream();

    /**
     * @return -
     */
    String getName();

    /**
     * @return -
     */
    long getSize();
}
