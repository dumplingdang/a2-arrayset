# a2-arrayset
## Implementation requirements
1. Implement the **Set** by using an array whose elements are of type E.

2. Your implementation of the **ArraySet** class must support initially holding 10 items, i.e., it must
have a capacity of 10. If there is an attempt to add one more item than the current size allows,
i.e., the current size has increased to be equal to the capacity, the size of the set (its capacity) must
double.

3. When an item is added to the **ArraySet**, add the item to the end of the array.

4. Your class must be a generic type like the **ArrayList**.

5. You will need to create another helper class that implements the **Iterator** interface in order
to satisfy the **iterator( )** method.

6. Since **ArraySet** provides an iterator, add **Iterable<E>** to the list of interfaces **ArraySet** implements. As long as you have an **iterator( )** method, this interface is happy and we have
established another is-a relationship.

7. Add **Collection<E>** to the list of interfaces your **ArraySet** implements, and provide
an implementation of the **removeIf** method. No other method required. Prove it works with an
appropriate set of unit tests.
