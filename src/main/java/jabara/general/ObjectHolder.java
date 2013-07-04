package jabara.general;

/**
 * オブジェクトを単純に保持します.
 * 
 * @param <V> 内包する値.
 * @author jabaraster
 */
public class ObjectHolder<V> implements IProducer<V>, IProducer2<Object, V> {

    private V value;

    /**
     * 
     */
    public ObjectHolder() {
        // 処理なし
    }

    /**
     * @param pValue 内包する値.
     */
    public ObjectHolder(final V pValue) {
        this.value = pValue;
    }

    /**
     * @return 内包する値.
     */
    public V getValue() {
        return this.value;
    }

    /**
     * @see jabara.general.IProducer#produce()
     */
    @Override
    public V produce() {
        return this.value;
    }

    /**
     * pArgumentに依らず、常に内包する値を返します.
     * 
     * @see jabara.general.IProducer2#produce(java.lang.Object)
     */
    @Override
    public V produce(@SuppressWarnings("unused") final Object pArgument) {
        return this.value;
    }

    /**
     * @param pValue 内包する値.
     */
    public void setValue(final V pValue) {
        this.value = pValue;
    }

}
