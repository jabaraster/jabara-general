/**
 * 
 */
package jabara.general;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author jabaraster
 */
public class MethodKey implements Serializable {
    private static final long serialVersionUID = -4439218066322581557L;

    private final boolean     exist;
    private final String      name;
    private final Class<?>[]  parameterTypes;

    /**
     * @param pMethod -
     */
    public MethodKey(final Method pMethod) {
        if (pMethod == null) {
            this.exist = false;
            this.name = null;
            this.parameterTypes = new Class<?>[0];
        } else {
            this.exist = true;
            this.name = pMethod.getName();
            this.parameterTypes = pMethod.getParameterTypes();
        }
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
        final MethodKey other = (MethodKey) obj;
        if (this.exist != other.exist) {
            return false;
        }
        if (this.name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!this.name.equals(other.name)) {
            return false;
        }
        if (!Arrays.equals(this.parameterTypes, other.parameterTypes)) {
            return false;
        }
        return true;
    }

    /**
     * @param pType -
     * @return -
     * @throws NotFound 該当メソッドが定義されていない場合.
     */
    public Method get(final Class<?> pType) throws NotFound {
        ArgUtil.checkNull(pType, "pType"); //$NON-NLS-1$

        if (!this.exist) {
            throw NotFound.GLOBAL;
        }
        try {
            return pType.getMethod(this.name, this.parameterTypes);
        } catch (final NoSuchMethodException e) {
            throw ExceptionUtil.rethrow(e);
        }
    }

    /**
     * @return parameterTypesを返す.
     */
    public Class<?>[] getParameterTypes() {
        return this.parameterTypes.clone();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (this.exist ? 1231 : 1237);
        result = prime * result + (this.name == null ? 0 : this.name.hashCode());
        result = prime * result + Arrays.hashCode(this.parameterTypes);
        return result;
    }

    /**
     * @return existを返す.
     */
    public boolean isExist() {
        return this.exist;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @SuppressWarnings("nls")
    @Override
    public String toString() {
        return "MethodKey [exist=" + this.exist + ", name=" + this.name + ", parameterTypes=" + Arrays.toString(this.parameterTypes) + "]";
    }
}
