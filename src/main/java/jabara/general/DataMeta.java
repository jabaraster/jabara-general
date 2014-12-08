/**
 * 
 */
package jabara.general;

import java.io.Serializable;

/**
 * @author jabaraster
 */
public class DataMeta implements Serializable {
    private static final long serialVersionUID = -7763590752317643481L;

    private final String      hash;
    private final long        length;

    private final int         hashCode;
    private final String      toString;

    /**
     * @param pHash -
     * @param pLength -
     */
    public DataMeta(final String pHash, final long pLength) {
        this.hash = pHash;
        this.length = pLength;

        this.hashCode = computeHash();
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
        final DataMeta other = (DataMeta) obj;
        if (this.hash == null) {
            if (other.hash != null) {
                return false;
            }
        } else if (!this.hash.equals(other.hash)) {
            return false;
        }
        if (this.length != other.length) {
            return false;
        }
        return true;
    }

    /**
     * @return the hash
     */
    public String getHash() {
        return this.hash;
    }

    /**
     * @return the length
     */
    public long getLength() {
        return this.length;
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

    @SuppressWarnings("nls")
    private String buildToString() {
        return "DataMeta [hash=" + this.hash + ", length=" + this.length + "]";
    }

    private int computeHash() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (this.hash == null ? 0 : this.hash.hashCode());
        result = prime * result + (int) (this.length ^ this.length >>> 32);
        return result;
    }
}
