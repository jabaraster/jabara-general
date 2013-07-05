/**
 * 
 */
package jabara.general;

import java.util.Locale;

/**
 * @author jabaraster
 */
public interface ILabelable {

    /**
     * @return -
     */
    String getLabel();

    /**
     * @param pLocale -
     * @return -
     */
    String getLabel(Locale pLocale);
}
