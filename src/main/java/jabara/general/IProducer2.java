/**
 * 
 */
package jabara.general;

/**
 * @param <A> 引数の型.
 * @param <R> 供給する値の型.
 * @author jabaraster
 */
public interface IProducer2<A, R> {

    /**
     * @param pArgument -
     * @return -
     */
    R produce(A pArgument);
}
