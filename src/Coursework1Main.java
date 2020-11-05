import java.util.Objects;

/**
 * Driver class for the Warehouse class of Coursework 1
 * in the Software and Programming II module at BBK in 2019/0.
 *
 * @author Carsten Fuhs
 */
public class Coursework1Main {

    /* The following is a tiny "home-grown" testing framework.
     * We will see a more advanced framework, JUnit, later in the module.
     */

    /** Index value for the next test. */
    private static int testNo = 1;

    /** Number of passed tests. */
    private static int passes = 0;

    /** Number of failed tests. */
    private static int fails = 0;

    /** Output for successful test. */
    private static final String YEA = "OK    "; //"PASSED";

    /** Output for unsuccessful test. */
    private static final String NAY = "FAILED";

    /**
     * Acceptable distance from expected value for double values,
     * should be slightly above 0.
     */
    private static final double DELTA = 1e-9;

    /**
     * Tests two int values for equality.
     *
     * Side effects: screen output of test result and increment of static
     * counter variables according to result.
     *
     * @param description  to be used as part of the screen output
     * @param expected  the expected value
     * @param actual  the actual value
     */
    public static void testIntEqual(String description, int expected, int actual) {
        sideEffectsForTest(description, expected + "", actual + "",
                expected == actual);
    }

    /**
     * Tests two long values for equality.
     *
     * Side effects: screen output of test result and increment of static
     * counter variables according to result.
     *
     * @param description  to be used as part of the screen output
     * @param expected  the expected value
     * @param actual  the actual value
     */
    public static void testLongEqual(String description, long expected, long actual) {
        sideEffectsForTest(description, expected + "", actual + "",
                expected == actual);
    }

    /**
     * Tests two double values for equality (up to a small "delta").
     *
     * Side effects: screen output of test result and increment of static
     * counter variables according to result.
     *
     * @param description  to be used as part of the screen output
     * @param expected  the expected value
     * @param actual  the actual value
     */
    public static void testDoubleEqual(String description, double expected, double actual) {
        sideEffectsForTest(description, expected + "", actual + "",
                expected - DELTA <= actual && actual <= expected + DELTA);
        // small rounding errors are ok
    }

    /**
     * Tests two Objects for equality. Works for Object and all its subclasses
     * (String, Article, ...).
     *
     * Side effects: screen output of test result and increment of static
     * counter variables according to result.
     *
     * @param description  to be used as part of the screen output
     * @param expected  the expected value
     * @param actual  the actual value
     */
    public static void testObjectEqual(String description, Object expected, Object actual) {
        sideEffectsForTest(description, expected + "", actual + "",
                Objects.equals(expected, actual));
        // Objects.equals is graceful on null
    }

    /**
     * Helper method for the side effects of the tests for different data types
     * (here already converted to Strings): screen output and increment of
     * static counter variables.
     *
     * @param description  description of the test to be printed
     * @param expected  String representation of the expected value
     * @param actual  String representation of the actual value
     * @param result  true: test has passed; false: test has failed
     */
    private static void sideEffectsForTest(String description, String expected, String actual, boolean result) {
        String output;
        if (result) {
            passes++;
            output = YEA;
        } else {
            fails++;
            output = NAY;
        }
        //System.out.println("Test " + testNo + ": " + description
        //    + ", expected: " + expected + ", actual: " + actual
        //    + " ===> " + output);
        System.out.println(output + " - Test " + testNo + ": " + description
                + ", expected: " + expected + ", actual: " + actual);
        testNo++;
    }

    /* The code to test our Warehouse in particular starts here. */

    /**
     * Constants for use in the tests.
     */
    private static final Article ARTICLE1 = new Article("Pen", 750);
    private static final Article ARTICLE2 = new Article("Stamp", 80);
    private static final Article ARTICLE3 = new Article("Car", 2000000);
    private static final Article ARTICLE4 = new Article("Soda", 200);
    private static final Article ARTICLE5 = new Article("Towel", 800);
    private static final Article ARTICLE6 = new Article("Lemonade", 200);
    private static final Article ARTICLE7 = new Article("Soda", 200);
    private static final Article ARTICLE8 = new Article("Book", 990);

    /* Methods to create suitably constructed and modified Warehouse objects. */

    /**
     * @return an empty Warehouse
     */
    private static Warehouse makeEmptyWarehouse() {
        return new Warehouse();
    }

    /**
     * @return a Warehouse to which ARTICLE1 has been added
     */
    private static Warehouse makeAddOneArticleWarehouse() {
        Warehouse k = makeEmptyWarehouse();
        k.add(ARTICLE1);
        return k;
    }

    /**
     * @return a Warehouse to which ARTICLE8 has been added twice
     */
    private static Warehouse makeAddTwoArticleSameWarehouse() {
        Warehouse k = makeEmptyWarehouse();
        k.add(ARTICLE8);
        k.add(ARTICLE8);
        return k;
    }

    /**
     * @return a Warehouse to which ARTICLE8, null, ARTICLE8 have been added
     */
    private static Warehouse makeAddTwoArticleSameAndNullWarehouse() {
        Warehouse k = makeEmptyWarehouse();
        k.add(ARTICLE8);
        k.add(null);
        k.add(ARTICLE8);
        return k;
    }

    /**
     * @return a Warehouse to which ARTICLE1, null, ARTICLE2 have been added
     */
    private static Warehouse makeAddTwoArticleAndNullWarehouse() {
        Warehouse k = makeEmptyWarehouse();
        k.add(ARTICLE1);
        k.add(null);
        k.add(ARTICLE2);
        return k;
    }

    /**
     * @return a Warehouse on which addAll was invoked with ARTICLE1, null, ARTICLE2
     */
    private static Warehouse makeAddAllTwoArticleAndNullWarehouse() {
        Warehouse k = makeEmptyWarehouse();
        Article[] items = { ARTICLE1, null, ARTICLE2 };
        k.addAll(items);
        return k;
    }

    /**
     * @return a Warehouse constructed with ARTICLE1, null, ARTICLE2 in the argument
     *  array
     */
    private static Warehouse makeConstructorTwoArticleAndNullWarehouse() {
        Article[] items = { ARTICLE1, null, ARTICLE2 };
        Warehouse k = new Warehouse(items);
        return k;
    }

    /**
     * @return a Warehouse with both constructor arguments and a call to add()
     */
    private static Warehouse makeConstructorAddTwoArticleAndNullWarehouse() {
        Article[] items = { ARTICLE1, null };
        Warehouse k = new Warehouse(items);
        k.add(ARTICLE2);
        return k;
    }

    /**
     * @return a Warehouse with one article passed in the constructor where the
     *  array passed to the constructor is later modified (which should not
     *  affect the Warehouse object)
     */
    private static Warehouse makeConstructorOneArticleArraySideEffectWarehouse() {
        Article[] items = { ARTICLE1 };
        Warehouse k = new Warehouse(items);
        items[0] = null; // the Warehouse object should still contain ARTICLE1!
        return k;
    }

    /**
     * @return a Warehouse on which reset() was called right before returning
     */
    private static Warehouse makeResetWarehouse() {
        Article[] items = { ARTICLE1, null };
        Warehouse k = new Warehouse(items);
        k.add(ARTICLE2);
        k.reset();
        return k;
    }

    /**
     * @return a Warehouse on which the keepOnlyArticlesWith() mutator has been
     *  called to remove some items
     */
    private static Warehouse makeKeepWarehouse() {
        Article[] items = { ARTICLE3, null, ARTICLE4, ARTICLE5, ARTICLE6, ARTICLE7, ARTICLE8, null, null, ARTICLE8  };
        Warehouse k = new Warehouse(items);
        k.add(ARTICLE2);
        k.keepOnlyArticlesWith(400);
        return k;
    }

    /**
     * @return an array with two Warehouses: the first one has ARTICLE1, ARTICLE2,
     *  and the second one has ARTICLE3
     */
    private static Warehouse[] makeTwoWarehouses() {
        return new Warehouse[] { new Warehouse(new Article[] { ARTICLE1, ARTICLE2 }),
                new Warehouse(new Article[] { ARTICLE3 })};
    }

    /**
     * Main method that drives the tests.
     *
     * @param args ignored.
     */
    public static void main(String[] args) {
        Warehouse store;
        store = makeEmptyWarehouse();
        testObjectEqual("toString", "[]", store.toString()); // 1
        store = makeEmptyWarehouse();
        testObjectEqual("mostExpensiveArticle", null, store.mostExpensiveArticle()); // 2
        store = makeEmptyWarehouse();
        testIntEqual("numberOfArticles", 0, store.numberOfArticles()); // 3
        store = makeEmptyWarehouse();
        testIntEqual("makeNewWarehouseWith", 0, store.makeNewWarehouseWith(120).numberOfArticles()); // 4
        store = makeEmptyWarehouse();
        testLongEqual("totalPriceInPence", 0, store.totalPriceInPence()); // 5
        store = makeEmptyWarehouse();
        testDoubleEqual("averagePriceInPence", -1.0, store.averagePriceInPence()); // 6

        store = makeAddOneArticleWarehouse();
        testObjectEqual("toString", "[" + ARTICLE1 + "]", store.toString()); // 7
        store = makeAddOneArticleWarehouse();
        testObjectEqual("mostExpensiveArticle", ARTICLE1, store.mostExpensiveArticle()); // 8
        store = makeAddOneArticleWarehouse();
        testIntEqual("numberOfArticles", 1, store.numberOfArticles()); // 9
        store = makeAddOneArticleWarehouse();
        testIntEqual("makeNewWarehouseWith", 1, store.makeNewWarehouseWith(120).numberOfArticles()); // 10
        store = makeAddOneArticleWarehouse();
        testIntEqual("makeNewWarehouseWith", 0, store.makeNewWarehouseWith(1000).numberOfArticles()); // 11
        store = makeAddOneArticleWarehouse();
        testLongEqual("totalPriceInPence", 750, store.totalPriceInPence()); // 12
        store = makeAddOneArticleWarehouse();
        testDoubleEqual("averagePriceInPence", 750, store.averagePriceInPence()); // 13

        store = makeAddTwoArticleSameWarehouse();
        testObjectEqual("toString", "[" + ARTICLE8 + ", " + ARTICLE8 + "]", store.toString()); // 14
        store = makeAddTwoArticleSameWarehouse();
        testObjectEqual("mostExpensiveArticle", ARTICLE8, store.mostExpensiveArticle()); // 15
        store = makeAddTwoArticleSameWarehouse();
        testIntEqual("numberOfArticles", 2, store.numberOfArticles()); // 16
        store = makeAddTwoArticleSameWarehouse();
        testIntEqual("makeNewWarehouseWith", 2, store.makeNewWarehouseWith(120).numberOfArticles()); // 17
        store = makeAddTwoArticleSameWarehouse();
        testIntEqual("makeNewWarehouseWith", 0, store.makeNewWarehouseWith(13000).numberOfArticles()); // 18
        store = makeAddTwoArticleSameWarehouse();
        testLongEqual("totalPriceInPence", 1980, store.totalPriceInPence()); // 19
        store = makeAddTwoArticleSameWarehouse();
        testDoubleEqual("averagePriceInPence", 990, store.averagePriceInPence()); // 20

        store = makeAddTwoArticleSameAndNullWarehouse();
        testObjectEqual("toString", "[" + ARTICLE8 + ", " + ARTICLE8 + "]", store.toString()); // 21

        store = makeAddTwoArticleAndNullWarehouse();
        testObjectEqual("mostExpensiveArticle", ARTICLE1, store.mostExpensiveArticle()); // 22
        store = makeAddTwoArticleAndNullWarehouse();
        testIntEqual("numberOfArticles", 2, store.numberOfArticles()); // 23
        store = makeAddTwoArticleAndNullWarehouse();
        testIntEqual("makeNewWarehouseWith", 2, store.makeNewWarehouseWith(20).numberOfArticles()); // 24
        store = makeAddTwoArticleAndNullWarehouse();
        testIntEqual("makeNewWarehouseWith", 1, store.makeNewWarehouseWith(100).numberOfArticles()); // 25
        store = makeAddTwoArticleAndNullWarehouse();
        testIntEqual("makeNewWarehouseWith", 1, store.makeNewWarehouseWith(750).numberOfArticles()); // 26
        store = makeAddTwoArticleAndNullWarehouse();
        testIntEqual("makeNewWarehouseWith", 0, store.makeNewWarehouseWith(3200).numberOfArticles()); // 27
        store = makeAddTwoArticleAndNullWarehouse();
        testLongEqual("totalPriceInPence", 830, store.totalPriceInPence()); // 28
        store = makeAddTwoArticleAndNullWarehouse();
        testDoubleEqual("averagePriceInPence", 415, store.averagePriceInPence()); // 29

        store = makeAddAllTwoArticleAndNullWarehouse();
        testObjectEqual("mostExpensiveArticle", ARTICLE1, store.mostExpensiveArticle()); // 30
        store = makeAddAllTwoArticleAndNullWarehouse();
        testIntEqual("numberOfArticles", 2, store.numberOfArticles()); // 31
        store = makeAddAllTwoArticleAndNullWarehouse();
        testIntEqual("makeNewWarehouseWith", 2, store.makeNewWarehouseWith(20).numberOfArticles()); // 32
        store = makeAddAllTwoArticleAndNullWarehouse();
        testIntEqual("makeNewWarehouseWith", 1, store.makeNewWarehouseWith(100).numberOfArticles()); // 33
        store = makeAddAllTwoArticleAndNullWarehouse();
        testIntEqual("makeNewWarehouseWith", 1, store.makeNewWarehouseWith(750).numberOfArticles()); // 34
        store = makeAddAllTwoArticleAndNullWarehouse();
        testIntEqual("makeNewWarehouseWith", 0, store.makeNewWarehouseWith(3200).numberOfArticles()); // 35
        store = makeAddAllTwoArticleAndNullWarehouse();
        testLongEqual("totalPriceInPence", 830, store.totalPriceInPence()); // 36
        store = makeAddAllTwoArticleAndNullWarehouse();
        testDoubleEqual("averagePriceInPence", 415, store.averagePriceInPence()); // 37

        store = makeConstructorTwoArticleAndNullWarehouse();
        testObjectEqual("mostExpensiveArticle", ARTICLE1, store.mostExpensiveArticle()); // 38
        store = makeConstructorTwoArticleAndNullWarehouse();
        testIntEqual("numberOfArticles", 2, store.numberOfArticles()); // 39
        store = makeConstructorTwoArticleAndNullWarehouse();
        testIntEqual("makeNewWarehouseWith", 2, store.makeNewWarehouseWith(20).numberOfArticles()); // 40
        store = makeConstructorTwoArticleAndNullWarehouse();
        testIntEqual("makeNewWarehouseWith", 1, store.makeNewWarehouseWith(100).numberOfArticles()); // 41
        store = makeConstructorTwoArticleAndNullWarehouse();
        testIntEqual("makeNewWarehouseWith", 1, store.makeNewWarehouseWith(750).numberOfArticles()); // 42
        store = makeConstructorTwoArticleAndNullWarehouse();
        testIntEqual("makeNewWarehouseWith", 0, store.makeNewWarehouseWith(3200).numberOfArticles()); // 43
        store = makeConstructorTwoArticleAndNullWarehouse();
        testLongEqual("totalPriceInPence", 830, store.totalPriceInPence()); // 44
        store = makeConstructorTwoArticleAndNullWarehouse();
        testDoubleEqual("averagePriceInPence", 415, store.averagePriceInPence()); // 45

        store = makeConstructorAddTwoArticleAndNullWarehouse();
        testObjectEqual("mostExpensiveArticle", ARTICLE1, store.mostExpensiveArticle()); // 46
        store = makeConstructorAddTwoArticleAndNullWarehouse();
        testIntEqual("numberOfArticles", 2, store.numberOfArticles()); // 47
        store = makeConstructorAddTwoArticleAndNullWarehouse();
        testIntEqual("makeNewWarehouseWith", 2, store.makeNewWarehouseWith(20).numberOfArticles()); // 48
        store = makeConstructorAddTwoArticleAndNullWarehouse();
        testIntEqual("makeNewWarehouseWith", 1, store.makeNewWarehouseWith(100).numberOfArticles()); // 49
        store = makeConstructorAddTwoArticleAndNullWarehouse();
        testIntEqual("makeNewWarehouseWith", 1, store.makeNewWarehouseWith(750).numberOfArticles()); // 50
        store = makeConstructorAddTwoArticleAndNullWarehouse();
        testIntEqual("makeNewWarehouseWith", 0, store.makeNewWarehouseWith(3200).numberOfArticles()); // 51
        store = makeConstructorAddTwoArticleAndNullWarehouse();
        testIntEqual("totalPriceInPence", 830, store.totalPriceInPence()); // 52
        store = makeConstructorAddTwoArticleAndNullWarehouse();
        testDoubleEqual("averagePriceInPence", 415, store.averagePriceInPence()); // 53

        store = makeConstructorOneArticleArraySideEffectWarehouse();
        testObjectEqual("toString", "[" + ARTICLE1 + "]", store.toString()); // 54
        store = makeConstructorOneArticleArraySideEffectWarehouse();
        testObjectEqual("mostExpensiveArticle", ARTICLE1, store.mostExpensiveArticle()); // 55
        store = makeConstructorOneArticleArraySideEffectWarehouse();
        testIntEqual("numberOfArticles", 1, store.numberOfArticles()); // 56
        store = makeConstructorOneArticleArraySideEffectWarehouse();
        testIntEqual("makeNewWarehouseWith", 1, store.makeNewWarehouseWith(120).numberOfArticles()); // 57
        store = makeConstructorOneArticleArraySideEffectWarehouse();
        testIntEqual("makeNewWarehouseWith", 0, store.makeNewWarehouseWith(1000).numberOfArticles()); // 58
        store = makeConstructorOneArticleArraySideEffectWarehouse();
        testLongEqual("totalPriceInPence", 750, store.totalPriceInPence()); // 59
        store = makeConstructorOneArticleArraySideEffectWarehouse();
        testDoubleEqual("averagePriceInPence", 750, store.averagePriceInPence()); // 60

        store = makeResetWarehouse();
        testObjectEqual("toString", "[]", store.toString()); // 61
        store = makeResetWarehouse();
        testObjectEqual("mostExpensiveArticle", null, store.mostExpensiveArticle()); // 62
        store = makeResetWarehouse();
        testIntEqual("numberOfArticles", 0, store.numberOfArticles()); // 63
        store = makeResetWarehouse();
        testIntEqual("makeNewWarehouseWith", 0, store.makeNewWarehouseWith(120).numberOfArticles()); // 64
        store = makeResetWarehouse();
        testLongEqual("totalPriceInPence", 0, store.totalPriceInPence()); // 65
        store = makeResetWarehouse();
        testDoubleEqual("averagePriceInPence", -1.0, store.averagePriceInPence()); // 66

        store = makeKeepWarehouse();
        testLongEqual("mostExpensiveArticle", 2000000, store.mostExpensiveArticle().getPriceInPence()); // 67
        store = makeKeepWarehouse();
        testIntEqual("numberOfArticles", 4, store.numberOfArticles()); // 68
        store = makeKeepWarehouse();
        testIntEqual("makeNewWarehouseWith", 3, store.makeNewWarehouseWith(940).numberOfArticles()); // 69
        store = makeKeepWarehouse();
        testLongEqual("totalPriceInPence", 2002780, store.totalPriceInPence()); // 70
        store = makeKeepWarehouse();
        testDoubleEqual("averagePriceInPence", 500695, store.averagePriceInPence()); // 71

        testObjectEqual("mostValuedWarehouse", null, Warehouse.mostValuedWarehouse(new Warehouse[0])); // 72

        Warehouse[] stores = makeTwoWarehouses();
        testIntEqual("mostValuedWarehouse", 2000000, Warehouse.mostValuedWarehouse(stores).totalPriceInPence()); // 73

        System.out.println();
        System.out.println(YEA + ": " + passes);
        System.out.println(NAY + ": " + fails);
    }

    /*

OK     - Test 1: toString, expected: [], actual: []
OK     - Test 2: mostExpensiveArticle, expected: null, actual: null
OK     - Test 3: numberOfArticles, expected: 0, actual: 0
OK     - Test 4: makeNewWarehouseWith, expected: 0, actual: 0
OK     - Test 5: totalPriceInPence, expected: 0, actual: 0
OK     - Test 6: averagePriceInPence, expected: -1.0, actual: -1.0
OK     - Test 7: toString, expected: [(Pen, 750)], actual: [(Pen, 750)]
OK     - Test 8: mostExpensiveArticle, expected: (Pen, 750), actual: (Pen, 750)
OK     - Test 9: numberOfArticles, expected: 1, actual: 1
OK     - Test 10: makeNewWarehouseWith, expected: 1, actual: 1
OK     - Test 11: makeNewWarehouseWith, expected: 0, actual: 0
OK     - Test 12: totalPriceInPence, expected: 750, actual: 750
OK     - Test 13: averagePriceInPence, expected: 750.0, actual: 750.0
OK     - Test 14: toString, expected: [(Book, 990), (Book, 990)], actual: [(Book, 990), (Book, 990)]
OK     - Test 15: mostExpensiveArticle, expected: (Book, 990), actual: (Book, 990)
OK     - Test 16: numberOfArticles, expected: 2, actual: 2
OK     - Test 17: makeNewWarehouseWith, expected: 2, actual: 2
OK     - Test 18: makeNewWarehouseWith, expected: 0, actual: 0
OK     - Test 19: totalPriceInPence, expected: 1980, actual: 1980
OK     - Test 20: averagePriceInPence, expected: 990.0, actual: 990.0
OK     - Test 21: toString, expected: [(Book, 990), (Book, 990)], actual: [(Book, 990), (Book, 990)]
OK     - Test 22: mostExpensiveArticle, expected: (Pen, 750), actual: (Pen, 750)
OK     - Test 23: numberOfArticles, expected: 2, actual: 2
OK     - Test 24: makeNewWarehouseWith, expected: 2, actual: 2
OK     - Test 25: makeNewWarehouseWith, expected: 1, actual: 1
OK     - Test 26: makeNewWarehouseWith, expected: 1, actual: 1
OK     - Test 27: makeNewWarehouseWith, expected: 0, actual: 0
OK     - Test 28: totalPriceInPence, expected: 830, actual: 830
OK     - Test 29: averagePriceInPence, expected: 415.0, actual: 415.0
OK     - Test 30: mostExpensiveArticle, expected: (Pen, 750), actual: (Pen, 750)
OK     - Test 31: numberOfArticles, expected: 2, actual: 2
OK     - Test 32: makeNewWarehouseWith, expected: 2, actual: 2
OK     - Test 33: makeNewWarehouseWith, expected: 1, actual: 1
OK     - Test 34: makeNewWarehouseWith, expected: 1, actual: 1
OK     - Test 35: makeNewWarehouseWith, expected: 0, actual: 0
OK     - Test 36: totalPriceInPence, expected: 830, actual: 830
OK     - Test 37: averagePriceInPence, expected: 415.0, actual: 415.0
OK     - Test 38: mostExpensiveArticle, expected: (Pen, 750), actual: (Pen, 750)
OK     - Test 39: numberOfArticles, expected: 2, actual: 2
OK     - Test 40: makeNewWarehouseWith, expected: 2, actual: 2
OK     - Test 41: makeNewWarehouseWith, expected: 1, actual: 1
OK     - Test 42: makeNewWarehouseWith, expected: 1, actual: 1
OK     - Test 43: makeNewWarehouseWith, expected: 0, actual: 0
OK     - Test 44: totalPriceInPence, expected: 830, actual: 830
OK     - Test 45: averagePriceInPence, expected: 415.0, actual: 415.0
OK     - Test 46: mostExpensiveArticle, expected: (Pen, 750), actual: (Pen, 750)
OK     - Test 47: numberOfArticles, expected: 2, actual: 2
OK     - Test 48: makeNewWarehouseWith, expected: 2, actual: 2
OK     - Test 49: makeNewWarehouseWith, expected: 1, actual: 1
OK     - Test 50: makeNewWarehouseWith, expected: 1, actual: 1
OK     - Test 51: makeNewWarehouseWith, expected: 0, actual: 0
OK     - Test 52: totalPriceInPence, expected: 830, actual: 830
OK     - Test 53: averagePriceInPence, expected: 415.0, actual: 415.0
OK     - Test 54: toString, expected: [(Pen, 750)], actual: [(Pen, 750)]
OK     - Test 55: mostExpensiveArticle, expected: (Pen, 750), actual: (Pen, 750)
OK     - Test 56: numberOfArticles, expected: 1, actual: 1
OK     - Test 57: makeNewWarehouseWith, expected: 1, actual: 1
OK     - Test 58: makeNewWarehouseWith, expected: 0, actual: 0
OK     - Test 59: totalPriceInPence, expected: 750, actual: 750
OK     - Test 60: averagePriceInPence, expected: 750.0, actual: 750.0
OK     - Test 61: toString, expected: [], actual: []
OK     - Test 62: mostExpensiveArticle, expected: null, actual: null
OK     - Test 63: numberOfArticles, expected: 0, actual: 0
OK     - Test 64: makeNewWarehouseWith, expected: 0, actual: 0
OK     - Test 65: totalPriceInPence, expected: 0, actual: 0
OK     - Test 66: averagePriceInPence, expected: -1.0, actual: -1.0
OK     - Test 67: mostExpensiveArticle, expected: 2000000, actual: 2000000
OK     - Test 68: numberOfArticles, expected: 4, actual: 4
OK     - Test 69: makeNewWarehouseWith, expected: 3, actual: 3
OK     - Test 70: totalPriceInPence, expected: 2002780, actual: 2002780
OK     - Test 71: averagePriceInPence, expected: 500695.0, actual: 500695.0
OK     - Test 72: mostValuedWarehouse, expected: null, actual: null
OK     - Test 73: mostValuedWarehouse, expected: 2000000, actual: 2000000

OK    : 73
FAILED: 0

     */
}
