/**
 * 
 */
package jabara.general;

import java.util.Date;
import java.util.TimeZone;

import org.junit.Test;

import static org.junit.Assert.assertThat;

import static org.hamcrest.core.Is.is;

/**
 * @author jabaraster
 */
public class TimeZoneUtilTest {

    /**
     * 
     */
    @SuppressWarnings("static-method")
    @Test
    public void _convertTimeZone() {
        final Date now = TimeZoneUtil.getUtcCurrentTime();
        final Date jtc = TimeZoneUtil.convertTimeZone(now, TimeZoneUtil.UTC, TimeZone.getTimeZone("Asia/Tokyo")); //$NON-NLS-1$
        assertThat( //
                Long.valueOf(jtc.getTime() - now.getTime()) //
                , is(Long.valueOf(1000 * 60 * 60 * 9)));
    }

    /**
     * 
     */
    @SuppressWarnings("static-method")
    @Test
    public void _getUtcCurrentTime() {
        final Date utcNow = TimeZoneUtil.getUtcCurrentTime();
        final TimeZone preTz = TimeZone.getDefault();
        try {
            TimeZone.setDefault(TimeZone.getTimeZone("Asia/Tokyo")); //$NON-NLS-1$
            final Date jtc = new Date();
            assertThat( //
                    Long.valueOf(jtc.getTime() - utcNow.getTime()) //
                    , is(Long.valueOf(1000 * 60 * 60 * 9)));

        } finally {
            TimeZone.setDefault(preTz);
        }
    }

    /**
     * 
     */
    @SuppressWarnings({ "static-method", "boxing" })
    @Test
    public void _UTC() {
        assertThat(TimeZoneUtil.UTC.getRawOffset(), is(0));
    }

}
