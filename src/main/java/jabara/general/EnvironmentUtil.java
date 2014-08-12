/**
 * 
 */
package jabara.general;

/**
 * @author jabaraster
 * 
 */
public final class EnvironmentUtil {

    private EnvironmentUtil() {
        // 処理なし
    }

    /**
     * 環境変数、あるいは-Dオプションにて指定するJVM引数から指定の文字列を取得します. <br>
     * 環境変数にもJVM引数にも指定されていない場合、pDefaultValueを返します. <br>
     * 
     * @param pParameterName -
     * @param pDefaultValue -
     * @return -
     * @throws NumberFormatException intに変換出来ない値が指定されたいた場合にスローします.
     */
    public static int getInt(final String pParameterName, final int pDefaultValue) {
        ArgUtil.checkNullOrEmpty(pParameterName, "pParameterName"); //$NON-NLS-1$
        return Integer.parseInt(getString(pParameterName, String.valueOf(pDefaultValue)));
    }

    /**
     * 環境変数、あるいは-Dオプションにて指定するJVM引数から指定の文字列を取得し、intに変換して返します. <br>
     * 環境変数にもJVM引数にも指定されていない場合、{@link IllegalStateException}をスローします. <br>
     * 
     * @param pParameterName -
     * @return -
     * @throws IllegalStateException 環境変数と-Dオプションの両方に指定のパラメータが見付からない場合にスローします.
     * @throws NumberFormatException intに変換出来ない値が指定されたいた場合にスローします.
     */
    public static int getIntUnsafe(final String pParameterName) throws IllegalStateException {
        ArgUtil.checkNullOrEmpty(pParameterName, "pParameterName"); //$NON-NLS-1$
        return Integer.parseInt(getStringUnsafe(pParameterName));
    }

    /**
     * 環境変数、あるいは-Dオプションにて指定するJVM引数から指定の文字列を取得します. <br>
     * 環境変数にもJVM引数にも指定されていない場合、pDefaultValueを返します. <br>
     * 
     * @param pParameterName -
     * @param pDefaultValue -
     * @return -
     */
    public static String getString(final String pParameterName, final Object pDefaultValue) {
        ArgUtil.checkNullOrEmpty(pParameterName, "pParameterName"); //$NON-NLS-1$

        String value = System.getenv(pParameterName);
        if (value != null) {
            return value;
        }
        value = System.getProperty(pParameterName);
        if (value != null) {
            return value;
        }
        return pDefaultValue == null ? null : pDefaultValue.toString();
    }

    /**
     * 環境変数、あるいは-Dオプションにて指定するJVM引数から指定の文字列を取得します. <br>
     * 環境変数にもJVM引数にも指定されていない場合、{@link IllegalStateException}をスローします. <br>
     * 
     * @param pParameterName -
     * @return -
     * @throws IllegalStateException 環境変数と-Dオプションの両方に指定のパラメータが見付からない場合にスローします.
     */
    public static String getStringUnsafe(final String pParameterName) throws IllegalStateException {
        ArgUtil.checkNullOrEmpty(pParameterName, "pParameterName"); //$NON-NLS-1$

        String value = System.getenv(pParameterName);
        if (value != null) {
            return value;
        }
        value = System.getProperty(pParameterName);
        if (value != null) {
            return value;
        }
        throw new IllegalStateException("パラメータ " + pParameterName + " が環境変数にも-Dオプションにも見付かりませんでした."); //$NON-NLS-1$//$NON-NLS-2$
    }
}
