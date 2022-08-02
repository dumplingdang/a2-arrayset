package ca.bcit.comp2526.a2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for ArraySet
 * @author BCIT
 * @version 3.0
 */
public class ArraySetTest {
    
    /**
     * Many (anything more tha  n this takes too long).
     */
    public static final int LARGE = 100_000;
    
    /**
     * Some (seems like a good number).
     */
    public static final int MEDIUM = 1_000;
    
    /**
     * Few (seems like a good number).
     */
    public static final int SMALL = 10;
    
    /**
     * Test object.
     */
    protected ArraySet<Integer> testArraySet;
    
    /**
     * @throws Exception
     */
    @BeforeEach
    public void setUp() throws Exception {
        testArraySet = new ArraySet<Integer>();
    }
    
    /**
     * @throws Exception
     */
    @AfterEach
    public void tearDown() throws Exception {
        testArraySet = null;
    }
    
    
    @Test
    public void testArraySet() {
        assertTrue(testArraySet instanceof ArraySet);
    }
    
    @Test
    public void testAddOneToEmptySetReturnsTrue() {
        int     numberToAdd = 1;
        boolean result      = testArraySet.add(numberToAdd);
        assertTrue(result);
        assertTrue(testArraySet.size() == 1);
    }
    
    @Test
    public void testAddManyToEmptySetNoDuplicatesReturnsTrue() {
        boolean result = true;
        for (int i = 0; i < LARGE; ++i) {
            result = result && testArraySet.add(i);
        }
        assertTrue(result);
        assertTrue(testArraySet.size() == LARGE);
        
    }
    
    @Test
    public void testAddOneDuplicateToNoneEmptySetReturnFalse() {
        for (int i = 0; i < SMALL; i++) {
            testArraySet.add(i);
        }
//        assertThrows(IllegalArgumentException.class, () -> testArraySet.add(0));
        assertFalse(testArraySet.add(0));
        assertTrue(testArraySet.size() == SMALL);
    }
    
    @Test
    public void testAddNullToEmptySetReturnsException() {
        assertThrows(NullPointerException.class, () -> testArraySet.add(null));
        assertTrue(testArraySet.size() == 0);
        
    }
    
    @Test
    public void testAddNullToNonemptySetReturnsFalse() {
        for (int i = 0; i < MEDIUM; ++i) {
            testArraySet.add(i);
        }
        assertThrows(NullPointerException.class, () -> testArraySet.add(null));
        assertTrue(testArraySet.size() == MEDIUM);
        
    }
    
    @Test
    public void testContainsReturnsTrueSetOfOne() {
        int     numberToAdd = 1;
        boolean result      = testArraySet.add(numberToAdd);
        assertTrue(testArraySet.contains(numberToAdd));
        assertTrue(testArraySet.size() == 1);
        
    }
    
    @Test
    public void testContainsReturnsTrueLargeSet() {
        boolean result = true;
        for (int i = 0; i < LARGE; ++i) {
            testArraySet.add(i);
        }
        for (int i = 0; i < LARGE; ++i) {
            result = result && testArraySet.contains(i);
        }
        assertTrue(result);
        assertTrue(testArraySet.size() == LARGE);
    }
    
    @Test
    public void testContainsNoNullsEmptySet() {
        assertThrows(NullPointerException.class, () -> testArraySet.contains(null));
    }
    
    @Test
    public void testContainsReturnsFalseForEmptySet() {
        assertFalse(testArraySet.contains(1));
    }
    
    @Test
    public void testContainsReturnsFalseForSomethingNotInLargeSet() {
        for (int i = 0; i < LARGE; ++i) {
            testArraySet.add(i);
        }
        assertTrue(testArraySet.size() == LARGE);
        assertFalse(testArraySet.contains(LARGE));
    }
    
    @Test
    public void testContainsNoNullsNonemptySet() {
        for (int i = 0; i < MEDIUM; ++i) {
            testArraySet.add(i);
        }
        assertTrue(testArraySet.size() == MEDIUM);
        assertThrows(NullPointerException.class, () -> testArraySet.add(null));
        assertThrows(NullPointerException.class, () -> testArraySet.contains(null));
    }
    
    @Test
    public void testSizeEmptySet() {
        
        assertEquals(testArraySet.size(), 0);
    }
    
    @Test
    public void testSizeSmallSet() {
        
        for (int i = 0; i < SMALL; ++i) {
            testArraySet.add(i);
        }
        assertEquals(testArraySet.size(), SMALL);
    }
    
    @Test
    public void testSizeMediumSet() {
        
        for (int i = 0; i < MEDIUM; ++i) {
            testArraySet.add(i);
        }
        assertEquals(testArraySet.size(), MEDIUM);
    }
    
    @Test
    public void testSizeLargeSet() {
        
        for (int i = 0; i < LARGE; ++i) {
            testArraySet.add(i);
        }
        assertEquals(testArraySet.size(), LARGE);
    }
    
    @Test
    public void testClearEmptySet() {
        
        testArraySet.clear();
        assertEquals(testArraySet.size(), 0);
    }
    
    @Test
    public void testClearLargeSet() {
        
        for (int i = 0; i < LARGE; ++i) {
            testArraySet.add(i);
        }
        testArraySet.clear();
        assertEquals(testArraySet.size(), 0);
    }
    
    @Test
    public void testAddOneDuplicateSequentiallySmallSet() {
        assertTrue(testArraySet.add(1));
//        assertThrows(IllegalArgumentException.class, () -> testArraySet.add(1));
        assertFalse(testArraySet.add(1));
        assertEquals(testArraySet.size(), 1);
    }
    
    @Test
    public void testAddOneDuplicateNonsequentuallySmallSet() {
        
        assertTrue(testArraySet.add(1));
        assertTrue(testArraySet.add(2));
        assertFalse(testArraySet.add(1));
//        assertThrows(IllegalArgumentException.class, () -> testArraySet.add(1));
        assertEquals(testArraySet.size(), 2);
    }
    
    @Test
    public void testAddOneDuplicateSequentiallyLargeSet() {
        
        for (int i = 0; i < LARGE; ++i) {
            testArraySet.add(i);
        }
        for (int i = 0; i < LARGE; ++i) {
            assertFalse(testArraySet.add(i));
//            int value = i;
//            assertThrows(IllegalArgumentException.class, () -> testArraySet.add(value));
        }
        assertEquals(testArraySet.size(), LARGE);
        
    }
    
    @Test
    public void testAddManyDuplicatesNonSequentiallyLargeSet() {
        
        Random random = new Random();
        for (int i = 0; i < LARGE; ++i) {
            testArraySet.add(random.nextInt(SMALL));
        }
        assertEquals(SMALL, testArraySet.size());
        
    }
    
    @Test
    public void testToArraySmall() {
        for (int i = 0; i < SMALL; ++i) {
            testArraySet.add(i);
        }
        Object[] arr = testArraySet.toArray();
        assertEquals(arr.length, SMALL);
        for (int i = 0; i < SMALL; ++i) {
            assertNotNull(arr[i]);
            assertTrue(testArraySet.contains((Integer) arr[i]));
        }
    }
    
    @Test
    public void testToArraySmallCleared() {
        for (int i = 0; i < SMALL; ++i) {
            testArraySet.add(i);
        }
        testArraySet.clear();
        Object[] arr = testArraySet.toArray();
        assertEquals(0, arr.length);
    }
    
    @Test
    public void testToArrayMediumSomeRemoved() {
        
        Random random = new Random();
        
        for (int i = 0; i < MEDIUM; ++i) {
            testArraySet.add(i);
        }
        
        int removed = 0;
        
        for (int i = 0; i < SMALL; ++i) {
            if (testArraySet.remove(random.nextInt(MEDIUM))) {
                removed++;
            }
        }
        Object[] arr = testArraySet.toArray();
        assertEquals(arr.length, (MEDIUM - removed));
    }
    
    @Test
    public void testIteratorIsNotNull() {
        
        Iterator<Integer> itr = testArraySet.iterator();
        assertNotNull(itr);
    }
    
    @Test
    public void testIteratorOverEmptySet() {
        
        Iterator<Integer> itr   = testArraySet.iterator();
        int               count = 0;
        if (itr.hasNext()) {
            count++;
        }
        assertEquals(count, 0);
    }
    
    @Test
    public void testIteratorOverMediumSet() {
        
        for (int i = 0; i < MEDIUM; ++i) {
            testArraySet.add(i);
        }
        Iterator<Integer> itr   = testArraySet.iterator();
        int               count = 0;
        while (itr.hasNext()) {
            int i = itr.next();
            count++;
        }
        assertEquals(count, MEDIUM);
    }
    
    @Test
    public void testRemoveNullFromEmptySet() {
//        assertFalse(testArraySet.remove(null));
        assertThrows(NullPointerException.class, () -> testArraySet.remove(null));
        assertTrue(testArraySet.size() == 0);
        
    }
    
    @Test
    public void testRemoveFromEmptySet() {

//        assertFalse(testArraySet.remove(SMALL));
        assertThrows(NullPointerException.class, () -> testArraySet.remove(SMALL));
        assertTrue(testArraySet.size() == 0);
        
    }
    
    @Test
    public void testRemoveNullFromNonemptySet() {
        
        for (int i = 0; i < MEDIUM; ++i) {
            testArraySet.add(i);
        }
        assertThrows(NullPointerException.class, () -> testArraySet.remove(null));
        assertTrue(testArraySet.size() == MEDIUM);
        
    }
    
    @Test
    public void testRemoveFirstElementFromNonemptySet() {
        
        for (int i = 0; i < MEDIUM; ++i) {
            testArraySet.add(i);
        }
        assertTrue(testArraySet.remove(0));
        assertTrue(testArraySet.size() == (MEDIUM - 1));
        assertFalse(testArraySet.contains(0));
    }
    
    @Test
    public void testRemoveLastElementFromNonemptySet() {
        
        for (int i = 0; i < MEDIUM; ++i) {
            testArraySet.add(i);
        }
        assertTrue(testArraySet.remove((MEDIUM - 1)));
        assertTrue(testArraySet.size() == (MEDIUM - 1));
        assertFalse(testArraySet.contains((MEDIUM - 1)));
        
    }
    
    @Test
    public void testRemoveMiddleElementFromNonemptySet() {
        
        for (int i = 0; i < MEDIUM; ++i) {
            testArraySet.add(i);
        }
        assertTrue(testArraySet.remove((MEDIUM / 2)));
        assertTrue(testArraySet.size() == (MEDIUM - 1));
        assertFalse(testArraySet.contains((MEDIUM / 2)));
        
    }
    
    @Test
    public void testRemoveAllElementsFromNonemptySet() {
        for (int i = 0; i < MEDIUM; ++i) {
            testArraySet.add(i);
        }
        for (int i = 0; i < MEDIUM; ++i) {
            testArraySet.remove(i);
        }
        assertTrue(testArraySet.size() == 0);
        boolean contains = false;
        for (int i = 0; i < MEDIUM; ++i) {
            contains = contains || testArraySet.contains(i);
        }
        assertFalse(contains);
    }
    
    @Test
    public void testToArrayHasEqualLength() {
        for (int i = 0; i < MEDIUM; ++i) {
            testArraySet.add(i);
        }
        Object[] objects = testArraySet.toArray();
        int      length  = objects.length;
        assertEquals(MEDIUM, length);
    }
    
    @Test
    public void testToArrayContainsSameElementsReturnsTrue() {
        for (int i = 0; i < MEDIUM; ++i) {
            testArraySet.add(i);
        }
        Object[] objects = testArraySet.toArray();
        for (int i = 0; i < objects.length; i++) {
            assertEquals(i, objects[i]);
        }
    }
    
    @Test
    public void testEmptyArraySetToEmptyArray() {
        Object[] objects = testArraySet.toArray();
        assertTrue(objects.length == 0);
    }
    
    @Test
    public void testToArrayWithParamHasSameLength() {
        for (int i = 0; i < MEDIUM; ++i) {
            testArraySet.add(i);
        }
        Integer[] param    = new Integer[testArraySet.size()];
        Integer[] integers = testArraySet.toArray(param);
        assertEquals(MEDIUM, integers.length);
    }
    
    @Test
    public void testToArrayWithParamContainsSameElements() {
        for (int i = 0; i < MEDIUM; ++i) {
            testArraySet.add(i);
        }
        Integer[] param    = new Integer[testArraySet.size()];
        Integer[] integers = testArraySet.toArray(param);
        for (int i = 0; i < integers.length; i++) {
            assertEquals(i, integers[i]);
        }
    }
    
    @Test
    public void testToArrayWithParamHasZeroLengthWhenArraySetIsEmtpy() {
        Integer[] integers = testArraySet.toArray(new Integer[testArraySet.size()]);
        assertEquals(0, integers.length);
    }
    
    @Test
    public void testToArrayWithParamThrowsExceptionWhenParamLengthNotEqualArraySetLargeSize() {
        for (int i = 0; i < LARGE; i++) {
            testArraySet.add(i);
        }
        assertThrows(NullPointerException.class, () -> testArraySet.toArray(new Integer[SMALL]));
    }
    
    @Test
    public void testToArrayWithParamThrowsExceptionWhenParamLengthNotEqualArraySetMediumSize() {
        for (int i = 0; i < MEDIUM; i++) {
            testArraySet.add(i);
        }
        assertThrows(NullPointerException.class, () -> testArraySet.toArray(new Integer[LARGE]));
    }
    
    @Test
    public void testContainsAllWhenArraySetContainsAllElementsInSpecifiedCollection() {
        Collection c = new ArraySet();
        for (int i = 0; i < MEDIUM; ++i) {
            testArraySet.add(i);
            c.add(i);
        }
        assertTrue(testArraySet.containsAll(c));
    }
    
    @Test
    public void testContainsAllWhenArraySetContainsSomeElementsOfSpecifiedCollection() {
        Collection c = new ArraySet();
        for (int i = 0; i < SMALL; i++) {
            testArraySet.add(i);
        }
        for (int i = 0; i < MEDIUM; i++) {
            c.add(i);
        }
        assertFalse(testArraySet.containsAll(c));
    }
    
    @Test
    public void testContainsAllWhenSpecifiedCollectionSizeLessThanArraySetSize() {
        Collection c = new ArraySet();
        for (int i = 0; i < MEDIUM; i++) {
            testArraySet.add(i);
        }
        for (int i = 0; i < SMALL; i++) {
            c.add(i);
        }
        assertTrue(testArraySet.containsAll(c));
    }
    
    @Test
    public void testContainsAllThrowsExceptionWhenSpecifiedCollectionIsEmpty() {
        Collection<Integer> c = new ArraySet<>();
        for (int i = 0; i < SMALL; i++) {
            testArraySet.add(i);
        }
        assertThrows(NullPointerException.class, () -> testArraySet.containsAll(c));
    }
    
    @Test
    public void testContainsAllThrowsExceptionWhenArraySetIsEmpty() {
        Collection<Integer> c = new ArraySet<>();
        for (int i = 0; i < SMALL; i++) {
            c.add(i);
        }
        assertThrows(NullPointerException.class, () -> testArraySet.containsAll(c));
    }
    
    @Test
    public void testContainsAllWhenSpecifiedCollectionIsNonArraySet() {
        Collection<Integer> c = new ArrayList<>();
        for (int i = 0; i < MEDIUM; i++) {
            testArraySet.add(i);
        }
        for (int i = 0; i < MEDIUM; i++) {
            c.add(i);
        }
        assertTrue(testArraySet.containsAll(c));
    }
    
    @Test
    public void testAddAllThrowsExceptionWhenAllElementsInSpecifiedCollectionAlreadyInArraySet() {
        ArraySet<Number>    testArraySet = new ArraySet<>();
        Collection<Integer> collection   = new ArraySet<>();
        for (int i = 0; i < MEDIUM; i++) {
            testArraySet.add(i);
            collection.add(i);
        }
        assertThrows(IllegalArgumentException.class, () -> testArraySet.addAll(collection));
    }
    
    @Test
    public void testAddAllThrowsExceptionWhenSomeElementsInSpecifiedCollectionAlreadyInSet() {
        ArraySet<Number>    testArraySet = new ArraySet<>();
        Collection<Integer> collection   = new ArraySet<>();
        for (int i = 0; i < SMALL; i++) {
            testArraySet.add(i);
        }
        for (int i = 0; i < MEDIUM; i++) {
            collection.add(i);
        }
        assertThrows(IllegalArgumentException.class, () -> testArraySet.addAll(collection));
    }
    
    @Test
    public void testAddAllReturnsTrueWhenNoElementsInSpecifiedCollectionAlreadyInSet() {
        ArraySet<Number>    testArraySet = new ArraySet<>();
        Collection<Integer> collection   = new ArraySet<>();
        for (int i = 0; i < SMALL; i++) {
            testArraySet.add(i);
        }
        for (int i = SMALL; i < MEDIUM; i++) {
            collection.add(i);
        }
        assertTrue(testArraySet.addAll(collection));
    }
    
    @Test
    public void testAddAllThrowsExceptionWhenSpecifiedCollectionIsNull() {
        ArraySet<Number>    testArraySet = new ArraySet<>();
        Collection<Integer> collection   = null;
        for (int i = 0; i < SMALL; i++) {
            testArraySet.add(i);
        }
        assertThrows(NullPointerException.class, () -> testArraySet.addAll(collection));
    }
    
    @Test
    public void testAddAllThrowsExceptionWhenSpecifiedCollectionIsEmpty() {
        ArraySet<Number>    testArraySet = new ArraySet<>();
        Collection<Integer> collection   = new ArraySet<>();
        for (int i = 0; i < SMALL; i++) {
            testArraySet.add(i);
        }
        assertThrows(NullPointerException.class, () -> testArraySet.addAll(collection));
    }
    
    @Test
    public void testRetainAllThrowsExceptionWhenSpecifiedColletionIsNull() {
        Collection<Integer> collection = null;
        for (int i = 0; i < MEDIUM; i++) {
            testArraySet.add(i);
        }
        assertThrows(NullPointerException.class, () -> testArraySet.retainAll(collection));
    }
    
    @Test
    public void testRetainAllThrowsExceptionWhenSpecifiedCollectionIsEmpty() {
        Collection<Integer> collection = new ArraySet<>();
        for (int i = 0; i < MEDIUM; i++) {
            testArraySet.add(i);
        }
        assertThrows(NullPointerException.class, () -> testArraySet.retainAll(collection));
    }
    
    @Test
    public void testRetainAllThrowsExceptionWhenArraySetIsEmpty() {
        Collection<Integer> collection = new ArraySet<>();
        for (int i = 0; i < MEDIUM; i++) {
            collection.add(i);
        }
        assertThrows(NullPointerException.class, () -> testArraySet.retainAll(collection));
    }
    
    @Test
    public void testRetainAllReturnsFalseWhenArraySetNotChanged() {
        Collection<Integer> collection = new ArraySet<>();
        for (int i = 0; i < MEDIUM; i++) {
            testArraySet.add(i);
            collection.add(i);
        }
        assertFalse(testArraySet.retainAll(collection));
    }
    
    @Test
    public void testRetainAllReturnTrueWhenArraySetContainsSomeElementsInSpecifiedCollection() {
        Collection<Integer> collection = new ArraySet<>();
        for (int i = 0; i < LARGE; i++) {
            testArraySet.add(i);
        }
        for (int i = 0; i < MEDIUM; i++) {
            collection.add(i);
        }
        assertTrue(testArraySet.retainAll(collection));
        assertTrue(testArraySet.size() == MEDIUM);
    }
    
    @Test
    public void testRetainAllReturnTrueWhenArraySetContainsNoneInSpecifiedCollection() {
        Collection<Integer> collection = new ArraySet<>();
        for (int i = 0; i < SMALL; i++) {
            collection.add(i);
        }
        for (int i = SMALL; i < MEDIUM; i++) {
            testArraySet.add(i);
        }
        assertTrue(testArraySet.retainAll(collection));
        assertTrue(testArraySet.size() == 0);
    }
    
    @Test
    public void testRemoveAllReturnsFalseWhenArraySetNotChanged() {
        Collection<Integer> collection = new ArraySet<>();
        for (int i = 0; i < MEDIUM; i++) {
            testArraySet.add(i);
        }
        for (int i = MEDIUM; i < LARGE; i++) {
            collection.add(i);
        }
        assertFalse(testArraySet.removeAll(collection));
        assertTrue(testArraySet.size() == MEDIUM);
    }
    
    @Test
    public void testRemoveAllReturnTrueWhenArraySetContainsAllElementsInSpecifiedCollection() {
        Collection<Integer> collection = new ArraySet<>();
        for (int i = 0; i < MEDIUM; i++) {
            testArraySet.add(i);
            collection.add(i);
        }
        assertTrue(testArraySet.removeAll(collection));
        assertTrue(testArraySet.size() == 0);
    }
    
    @Test
    public void testRemoveAllReturnsTrueWhenArraySetContainsSomeElementsInSpecifiedCollection() {
        Collection<Integer> collection = new ArraySet<>();
        for (int i = 0; i < LARGE; i++) {
            testArraySet.add(i);
        }
        for (int i = 0; i < MEDIUM; i++) {
            collection.add(i);
        }
        assertTrue(testArraySet.removeAll(collection));
        assertTrue(testArraySet.size() == (LARGE - MEDIUM));
    }
    
    @Test
    public void testRemoveIfElementsValueEqualGreaterThanMedium() {
        for (int i = 0; i < LARGE; i++) {
            testArraySet.add(i);
        }
        assertTrue(testArraySet.removeIf(e -> e >= MEDIUM));
        assertEquals(1000, testArraySet.size());
    }
    
    @Test
    public void testRemoveIfElementsValueLessThanMedium() {
        for (int i = 0; i < LARGE; i++) {
            testArraySet.add(i);
        }
        assertTrue(testArraySet.removeIf(e -> e < MEDIUM));
        assertEquals(99000, testArraySet.size());
    }
}
