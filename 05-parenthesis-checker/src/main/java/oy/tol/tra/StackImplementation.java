package oy.tol.tra;

/**
 * An implementation of the StackInterface.
 * <p>
 * 
 * Note that you need to implement construtor(s) for your concrete StackImplementation, which
 * allocates the internal Object array for the Stack:
 * - a default constructor, calling the StackImplementation(int size) with value of 10.
 * - StackImplementation(int size), which allocates an array of Object's with size.
 *  -- remember to maintain the capacity and/or currentIndex when the stack is manipulated.
 */
public class StackImplementation<E> implements StackInterface<E> {

   private Object [] itemArray;
   private int capacity;
   private int currentIndex = -1;
   private static final int DEFAULT_STACK_SIZE = 10;

   /**
    * Allocates a stack with a default capacity.
    * @throws StackAllocationException
    */
   public StackImplementation() throws StackAllocationException {
      this.capacity=DEFAULT_STACK_SIZE;
      itemArray=new Object[DEFAULT_STACK_SIZE];
      
   }

   /**
    * - if the size is less than 2, throw StackAllocationException
    * - if the allocation of the array throws with Java exception,
    *   throw StackAllocationException.
    * @param capacity The capacity of the stack.
    * @throws StackAllocationException If cannot allocate room for the internal array.
    */
   public StackImplementation(int capacity) throws StackAllocationException {
      if(capacity<2){
         throw new StackAllocationException("Capacity is too small!");
      }
      this.capacity=capacity;
      itemArray=new Object[capacity];
   }

   @Override
   public int capacity() {
      return this.capacity;
   }

   @Override
   public void push(E element) throws StackAllocationException, NullPointerException {
      if (element == null) {
         throw new NullPointerException("Cannot push null element onto stack!");
      }
      if (currentIndex >= capacity - 1) {
         // Stack is full, need to resize
         resizeStack();
      }
      currentIndex++;
      itemArray[currentIndex] = element;

   }

   @SuppressWarnings("unchecked")
   @Override
   public E pop() throws StackIsEmptyException {
      if (isEmpty()) {
         throw new StackIsEmptyException("Stack is empty!");
      }
      E element = (E) itemArray[currentIndex];
      itemArray[currentIndex] = null;
      currentIndex--;
      return element;
   }

   @SuppressWarnings("unchecked")
   @Override
   public E peek() throws StackIsEmptyException {
      if (isEmpty()) {
         throw new StackIsEmptyException("Stack is empty!");
      }
      return (E) itemArray[currentIndex];
   }

   @Override
   public int size() {
      return currentIndex+1;
   }

   @Override
   public void clear() {
      currentIndex=-1;
   }

   @Override
   public boolean isEmpty() {
      if(size()==0){
         return true;
      }
      return false;
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder("[");
      for (var index = 0; index <= currentIndex; index++) {
         builder.append(itemArray[index].toString());
         if (index < currentIndex) {
            builder.append(", ");
         }
      }
      builder.append("]");
      return builder.toString();
   }

   /**
    * Doubles the capacity of the stack by creating a new array and copying elements.
    */
   private void resizeStack() throws StackAllocationException {
      int newCapacity = capacity * 2;
      Object[] newArray = new Object[newCapacity];
      // Copy elements from old array to new array
      for (int i = 0; i < capacity; i++) {
         newArray[i] = itemArray[i];
      }
      itemArray = newArray;
      capacity = newCapacity;
   }
}
