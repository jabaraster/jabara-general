/*
 * 作成日： 2006/03/16
 */

package jabara.general;

import java.io.Serializable;

/**
 * ソート条件. <br>
 * 昇順ソート条件は{@link #asc(String)}、降順ソート条件は{@link #desc(String)} によって取得する. <br>
 * <br>
 * このクラスはサブクラスを作成することが出来ない. <br>
 * 
 * @author 河野
 */
public abstract class Sort implements Serializable {

    private static final long serialVersionUID = -594331134048001098L;

    private final String      columnName;
    private final String      orderString;
    private final SortRule    sortRule;

    private final String      tostring;
    private final int         hash;

    /**
     * @param pColumnName -
     * @param pSortRule -
     */
    @SuppressWarnings("nls")
    public Sort(final String pColumnName, final SortRule pSortRule) {
        this.columnName = ArgUtil.checkNullOrEmpty(pColumnName, "pColumnName");
        this.sortRule = ArgUtil.checkNull(pSortRule, "pSortRule");

        this.tostring = this.getClass().getName() + //
                "[columnName=" + this.columnName + //
                ",sortRule=" + this.sortRule + //
                "]";
        this.hash = this.columnName.hashCode() + this.sortRule.hashCode();
        this.orderString = pColumnName + " " + pSortRule.name();
    }

    /**
     * 列名とソート条件（昇順、降順）が一致するときに等しいオブジェクトと判断する. <br>
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public final boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Sort)) {
            return false;
        }
        final Sort target = (Sort) obj;
        return this.columnName.equals(target.columnName) && this.sortRule.equals(target.sortRule);
    }

    /**
     * @return コンストラクタで指定された列名.
     */
    public final String getColumnName() {
        return this.columnName;
    }

    /**
     * @return SQL 文のORDER BY 句に記述する文字列. <br>
     *         例えば<code> HOGE DESC </code>のような文字列.
     */
    public final String getOrderString() {
        return this.orderString;
    }

    /**
     * @return 昇順、降順のいずれか.
     */
    public final SortRule getSortRule() {
        return this.sortRule;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public final int hashCode() {
        return this.hash;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return this.tostring;
    }

    /**
     * 昇順ソート条件を取得する.
     * 
     * @param pColumnName 列名. null及び空文字は禁止.
     * @return 昇順ソート条件
     */
    @SuppressWarnings("synthetic-access")
    public static Sort asc(final String pColumnName) {
        return new Asc(pColumnName);
    }

    /**
     * pSort の各要素の{@link #getOrderString()} が返す文字列を連結し、SQL 文のORDER BY 句で使える文字列を返す. <br>
     * <code>ORDER BY</code> は付かないので注意.
     * 
     * @param pSort ソート条件. null禁止. 配列の各要素もnull禁止.
     * @return ORDER BY 句に書ける文字列. pSort の長さが０の場合は空文字
     */
    public static String buildSortString(final Sort... pSort) {
        if (pSort == null || pSort.length == 0) {
            throw new IllegalArgumentException("pSortにnullあるいは長さ0の配列は渡せません."); //$NON-NLS-1$
        }

        final String SEPARATOR = ", "; //$NON-NLS-1$
        final StringBuilder sb = new StringBuilder();
        for (final Sort sort : pSort) {
            sb.append(sort.getOrderString()).append(SEPARATOR);
        }
        sb.delete(sb.length() - SEPARATOR.length(), sb.length());
        return new String(sb);
    }

    /**
     * 降順ソート条件を取得する.
     * 
     * @param pColumnName 列名. null及び空文字は禁止.
     * @return 降順ソート条件
     */
    @SuppressWarnings("synthetic-access")
    public static Sort desc(final String pColumnName) {
        return new Desc(pColumnName);
    }

    /**
     * 昇順ソート用クラス.
     * 
     * @author 河野
     */
    private static final class Asc extends Sort {
        private static final long serialVersionUID = 7964390806366978088L;

        /**
         * @param pColumnName 列名
         */
        private Asc(final String pColumnName) {
            super(pColumnName, SortRule.ASC);
        }
    }

    /**
     * 降順ソート用クラス.
     * 
     * @author 河野
     */
    private static final class Desc extends Sort {
        private static final long serialVersionUID = 362556712630181239L;

        /**
         * @param pColumnName 列名
         */
        private Desc(final String pColumnName) {
            super(pColumnName, SortRule.DESC);
        }
    }
}
