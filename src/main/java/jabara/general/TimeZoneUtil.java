/**
 * 
 */
package jabara.general;

import java.util.Date;
import java.util.TimeZone;

/**
 * @author jabaraster
 */
public final class TimeZoneUtil {

    /**
     * 
     */
    public static final TimeZone UTC = TimeZone.getTimeZone("UTC"); //$NON-NLS-1$

    private TimeZoneUtil() {
        // nop
    }

    /**
     * @param pSource -
     * @param pSourceTimeZone -
     * @param pDestTimeZone -
     * @return -
     */
    public static Date convertTimeZone(final Date pSource, final TimeZone pSourceTimeZone, final TimeZone pDestTimeZone) {
        ArgUtil.checkNull(pSource, "pSource"); //$NON-NLS-1$
        ArgUtil.checkNull(pSourceTimeZone, "pSourceTimeZone"); //$NON-NLS-1$
        ArgUtil.checkNull(pDestTimeZone, "pDestTimeZone"); //$NON-NLS-1$

        return new Date(pSource.getTime() - pSourceTimeZone.getRawOffset() + pDestTimeZone.getRawOffset());
    }

    /**
     * @return -
     */
    public static Date getUtcCurrentTime() {
        return convertTimeZone(new Date(), TimeZone.getDefault(), UTC);
    }
}
