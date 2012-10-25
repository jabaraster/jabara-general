package jabara;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * デバッグ用クラス. <br>
 * 
 * @author 河野
 */
@SuppressWarnings("nls")
public final class Debug {

    /**
     * インスタンス化を禁止する.
     */
    private Debug() {
        // 処理なし
    }

    /**
     * メソッド呼び出し階層を表示する. <br>
     * 引数には、最大表示階層数を指定する. 1以上の数値を指定すること. <br>
     * 
     * @param maxLevel 表示するメソッド呼び出し階層の最大数
     * @return スタックトレース文字列
     */
    public static String printStackTrace(final int maxLevel) {
        if (1 > maxLevel) {
            throw new IllegalArgumentException("1 以上の数値でなくてはいけません！");
        }

        final StringBuffer msg = new StringBuffer();
        msg.append("[メソッド階層表示 ");
        msg.append(new SimpleDateFormat("yyyy/MM/dd HH:mm").format(new Date()));
        msg.append("]");
        msg.append(System.getProperty("line.separator"));

        final StackTraceElement[] stacks = new Throwable().getStackTrace();

        // 階層表示の開始. ループの開始インデックスが 1なのは、
        // このメソッドを呼び出したメソッドを階層表示の一番上にするため
        for (int i = 1, size = stacks.length; i < size && i < maxLevel + 1; i++) {
            if (1 != i) {
                for (int j = 1; j < i; j++) {
                    msg.append(" ");
                }
                msg.append("-");
            }
            msg.append(stacks[i].getClassName());
            msg.append("#");
            msg.append(stacks[i].getMethodName());
            msg.append(" (");
            msg.append(stacks[i].getFileName());
            msg.append(":");
            msg.append(stacks[i].getLineNumber());
            msg.append(")");
            msg.append(System.getProperty("line.separator"));
        }
        final String retStr = new String(msg);
        System.out.println(retStr);

        return retStr;
    }

    /**
     * 呼び出し元メソッド情報のみをデバッグライトとして標準出力に出力する. <br>
     * 
     * @return 出力したメッセージと同じ文字列
     */
    public static String write() {
        // スタックトレースの２番目がDebug.write()を呼んだ直接のメソッドになる
        final StackTraceElement elem = new Exception().getStackTrace()[1];

        // デバッグライトプリント
        final StringBuffer str = new StringBuffer();
        str.append(getLastToken(elem.getClassName(), ".")).append("#").append(elem.getMethodName());
        str.append("(").append(elem.getLineNumber()).append(")");

        final String ret = str.toString();
        System.out.println(ret);

        return ret;
    }

    /**
     * boolean値をデバッグ出力する. <br>
     * 
     * @param value 出力対象
     * @return 標準出力に出力される文字列と同じ文字列
     */
    public static String write(final boolean value) {
        return writeImpl(String.valueOf(value));
    }

    /**
     * byteをデバッグ出力する. <br>
     * 
     * @param value 出力対象
     * @return 標準出力に出力される文字列と同じ文字列
     */
    public static String write(final byte value) {
        return writeImpl(String.valueOf(value));
    }

    /**
     * char値をデバッグ出力する. <br>
     * 
     * @param value 出力対象
     * @return 標準出力に出力される文字列と同じ文字列
     */
    public static String write(final char value) {
        return writeImpl(String.valueOf(value));
    }

    /**
     * double値をデバッグ出力する. <br>
     * 
     * @param value 出力対象
     * @return 標準出力に出力される文字列と同じ文字列
     */
    public static String write(final double value) {
        return writeImpl(String.valueOf(value));
    }

    /**
     * float値をデバッグ出力する. <br>
     * 
     * @param value 出力対象
     * @return 標準出力に出力される文字列と同じ文字列
     */
    public static String write(final float value) {
        return writeImpl(String.valueOf(value));
    }

    /**
     * int値をデバッグ出力する. <br>
     * 
     * @param value 出力対象
     * @return 標準出力に出力される文字列と同じ文字列
     */
    public static String write(final int value) {
        return writeImpl(String.valueOf(value));
    }

    /**
     * long値をデバッグ出力する. <br>
     * 
     * @param value 出力対象
     * @return 標準出力に出力される文字列と同じ文字列
     */
    public static String write(final long value) {
        return writeImpl(String.valueOf(value));
    }

    /**
     * 指定のオブジェクトのtoString()したものをデバッグライトとして標準出力に出力する. <br>
     * 呼び出し元メソッド情報が出力される.
     * 
     * @param msg 出力するメッセージ
     * @return 出力したメッセージと同じ文字列
     */
    public static String write(final Object msg) {
        return writeImpl(msg);
    }

    /**
     * short値をデバッグ出力する. <br>
     * 
     * @param value 出力対象
     * @return 標準出力に出力される文字列と同じ文字列
     */
    public static String write(final short value) {
        return writeImpl(String.valueOf(value));
    }

    private static String getLastToken(final String baseString, final String delim) {

        if (delim.length() == 0) {
            return baseString;
        }

        return baseString.substring(baseString.lastIndexOf(delim) + delim.length());
    }

    /**
     * メッセージ出力実装メソッド. <br>
     * スタックトレース階層を計算するため、全てのwrite()メソッドは このメソッドに処理を委譲する必要がある. <br>
     * 
     * @param msg メッセージ対象オブジェクト
     * @return 出力したメッセージと同じ文字列
     */
    private static String writeImpl(final Object msg) {
        // スタックトレースの３番目がDebug.write()を呼んだ直接のメソッドになる
        final StackTraceElement elem = new Exception().getStackTrace()[2];

        // デバッグライトプリント
        final StringBuffer str = new StringBuffer();
        str.append(getLastToken(elem.getClassName(), ".")).append("#").append(elem.getMethodName());
        str.append("(").append(elem.getLineNumber()).append("):");

        if (msg != null) {
            str.append(msg.toString());
        } else {
            str.append("<null>");
        }

        final String ret = str.toString();
        System.out.println(ret);

        return ret;
    }

}