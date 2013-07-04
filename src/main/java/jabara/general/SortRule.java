package jabara.general;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author 河野
 */
public enum SortRule implements ILabelable {

    /**
     * 昇順.
     */
    ASC,

    /**
     * 降順.
     */
    DESC, ;

    /**
     * @see jabara.general.ILabelable#getLabel()
     */
    @Override
    public String getLabel() {
        return this.getLabel(Locale.getDefault());
    }

    /**
     * @see jabara.general.ILabelable#getLabel(java.util.Locale)
     */
    @Override
    public String getLabel(final Locale pLocale) {
        ArgUtil.checkNull(pLocale, "pLocale"); //$NON-NLS-1$
        return ResourceBundle.getBundle(SortRule.class.getName(), pLocale).getString(toString());
    }

}
