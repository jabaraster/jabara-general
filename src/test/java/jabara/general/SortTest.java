/**
 * 
 */
package jabara.general;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * 
 * @author jabaraster
 */
public class SortTest {

    /**
     * 
     */
    @SuppressWarnings({ "nls", "static-method" })
    @Test
    public void _getOrderString() {
        assertThat("hoge ASC, piyo DESC", is(Sort.buildSortString(Sort.asc("hoge"), Sort.desc("piyo"))));
    }
}
