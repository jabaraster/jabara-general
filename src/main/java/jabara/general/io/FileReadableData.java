/**
 * 
 */
package jabara.general.io;

import jabara.general.ArgUtil;
import jabara.general.ExceptionUtil;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;

/**
 * @author jabaraster
 */
public class FileReadableData implements IReadableData, Serializable {
    private static final long serialVersionUID = -65889989147600502L;

    private final String      name;
    private final String      contentType;
    private final File        file;

    /**
     * @param pContentType -
     * @param pFile -
     */
    public FileReadableData(final String pContentType, final File pFile) {
        ArgUtil.checkNull(pFile, "pFile"); //$NON-NLS-1$
        this.name = pFile.getName();
        this.contentType = pContentType;
        this.file = pFile;
    }

    /**
     * @param pName -
     * @param pContentType -
     * @param pFile -
     */
    public FileReadableData(final String pName, final String pContentType, final File pFile) {
        ArgUtil.checkNull(pFile, "pFile"); //$NON-NLS-1$
        this.name = pName;
        this.contentType = pContentType;
        this.file = pFile;
    }

    /**
     * 
     */
    public void deleteFile() {
        this.file.delete();
    }

    /**
     * @see jabara.general.io.IReadableData#getContentType()
     */
    @Override
    public String getContentType() {
        return this.contentType;
    }

    /**
     * @see jabara.general.io.IReadableData#getInputStream()
     */
    @Override
    public InputStream getInputStream() {
        try {
            final InputStream in = new FileInputStream(this.file);
            return new BufferedInputStream(in);

        } catch (final FileNotFoundException e) {
            throw ExceptionUtil.rethrow(e);
        }
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
        return this.file.length();
    }
}
