/**
 * 
 */
package jabara.general;

import java.io.Serializable;

/**
 * 名前の付いた値.
 * 
 * @param <V> 値の型.
 * @author jabaraster
 */
public class NameValue<V> implements Serializable {
    private static final long serialVersionUID = 8520371456248683948L;

    private final String      name;
    private final V           value;

    private final int         hashCode;
    private final String      toString;

    /**
     * @param pName -
     * @param pValue -
     */
    public NameValue(final String pName, final V pValue) {
        this.name = pName;
        this.value = pValue;
        this.hashCode = buildHashCode();
        this.toString = buildToString();
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NameValue<?> other = (NameValue<?>) obj;
        if (this.name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!this.name.equals(other.name)) {
            return false;
        }
        if (this.value == null) {
            if (other.value != null) {
                return false;
            }
        } else if (!this.value.equals(other.value)) {
            return false;
        }
        return true;
    }

    /**
     * @return nameを返す.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return valueを返す.
     */
    public V getValue() {
        return this.value;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return this.hashCode;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return this.toString;
    }

    private int buildHashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (this.name == null ? 0 : this.name.hashCode());
        result = prime * result + (this.value == null ? 0 : this.value.hashCode());
        return result;
    }

    @SuppressWarnings("nls")
    private String buildToString() {
        return "NameValue [name=" + this.name + ", value=" + this.value + "]";
    }
}
