/**
 * 
 */
package jabara.general;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;

/**
 * @author jabaraster
 */
public class SortRuleTest {

    /**
     * 
     */
    @SuppressWarnings("static-method")
    @Test
    public void _getLabel_ASC() {
        assertThat(SortRule.ASC.getLabel(), is("昇順")); //$NON-NLS-1$
    }

    /**
     * 
     */
    @SuppressWarnings("static-method")
    @Test
    public void _getLabel_DESC() {
        assertThat(SortRule.DESC.getLabel(), is("降順")); //$NON-NLS-1$
    }

    /**
     * 
     */
    @SuppressWarnings("static-method")
    @Test
    public void _getLabel_en_ASC() {
        assertThat(SortRule.ASC.getLabel(Locale.ENGLISH), is("ASC")); //$NON-NLS-1$ 
    }

    /**
     * 
     */
    @SuppressWarnings("static-method")
    @Test
    public void _getLabel_en_DESC() {
        System.out.println(Locale.FRENCH);
        assertThat(SortRule.DESC.getLabel(Locale.ENGLISH), is("DESC")); //$NON-NLS-1$ 
    }

    /**
     * 
     */
    @SuppressWarnings("static-method")
    @Test
    public void _getLabel_fr_ASC() {
        final ResourceBundle resources = ResourceBundle.getBundle(SortRule.class.getName(), Locale.getDefault());
        assertThat(SortRule.ASC.getLabel(Locale.FRENCH), is(resources.getString(SortRule.ASC.toString())));
    }
}
