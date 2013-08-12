/**
 * 
 */
package jabara.general;

import java.io.Serializable;

/**
 * @param <T> 供給する値の型.
 * @author jabaraster
 */
public interface IProducer<T> extends Serializable {

    /**
     * @return -
     */
    T produce();
}
