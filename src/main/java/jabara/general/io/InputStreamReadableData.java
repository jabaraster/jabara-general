/**
 * 
 */
package jabara.general.io;

import jabara.general.ArgUtil;

import java.io.InputStream;

/**
 * @author jabaraster
 */
public class InputStreamReadableData implements IReadableData {

    private final String                contentType;
    private final String                name;
    private transient final InputStream in;
    private final long                  size;

    /**
     * @param pName -
     * @param pContentType -
     * @param pSize -
     * @param pIn -
     */
    public InputStreamReadableData(final String pName, final String pContentType, final long pSize, final InputStream pIn) {
        ArgUtil.checkNull(pIn, "pIn"); //$NON-NLS-1$

        this.contentType = pContentType;
        this.in = pIn;
        this.name = pName;
        this.size = pSize;
    }

    /**
     * @see jabara.general.io.IReadableData#getContentType()
     */
    @Override
    public String getContentType() {
        return this.contentType;
    }

    /**
     * コンストラクタで渡された{@link InputStream}オブジェクトそのものを返します. <br>
     * 
     * @see jabara.general.io.IReadableData#getInputStream()
     */
    @Override
    public InputStream getInputStream() {
        return this.in;
    }

    /**
     * @see jabara.general.io.IReadableData#getName()
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * @see jabara.general.io.IReadableData#getSize()
     */
    @Override
    public long getSize() {
        return this.size;
    }
}
