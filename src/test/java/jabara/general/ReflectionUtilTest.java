/**
 * 
 */
package jabara.general;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

/**
 * @author jabaraster
 */
@RunWith(Enclosed.class)
public class ReflectionUtilTest {

    /**
     * @author jabaraster
     */
    @SuppressWarnings({ "boxing", "static-method" })
    public static class getConcreteParameterType {

        /**
         * @throws NotFound テストOKならスローされないはず.
         */
        @Test
        public void _ArrayListを継承して型パラメータを確定させている場合はその型が取得できる() throws NotFound {
            final Class<?> act = ReflectionUtil.getConcreteParameterType(ZExList.class);
            assertThat(act.equals(String.class), is(true));
        }

        /**
         * @throws NotFound テストOKならスローされないはず.
         */
        @Test
        public void _ArrayListを継承して型パラメータを確定させている場合はその型が取得できる_継承が深い場合() throws NotFound {
            final Class<?> act = ReflectionUtil.getConcreteParameterType(ZExExList.class);
            assertThat(act.equals(String.class), is(true));
        }

        /**
         * @throws NotFound -
         */
        @Test(expected = NotFound.class)
        public void _Objectを渡すしたらNotFound() throws NotFound {
            ReflectionUtil.getConcreteParameterType(Object.class);
        }

        /**
         * @throws NotFound -
         */
        @Test(expected = NotFound.class)
        public void _具体的な型が決定していない型を渡すとNotFound() throws NotFound {
            ReflectionUtil.getConcreteParameterType(ArrayList.class);
        }

        /**
         * @throws NotFound -
         */
        @Test(expected = NotFound.class)
        public void _型パラメータが２つある型の場合はNotFound() throws NotFound {
            ReflectionUtil.getConcreteParameterType(ZExMap.class);
        }
    }

    static class ZExExList extends ZExList {
        private static final long serialVersionUID = 9190116794363050531L;
    }

    static class ZExList extends ArrayList<String> {
        private static final long serialVersionUID = -7832197925041007293L;
    }

    static class ZExMap extends HashMap<String, Integer> {
        private static final long serialVersionUID = 5205433214501347928L;
    }
}
