package oy.tol.tra;


public class QueueImplementation<E> implements QueueInterface<E> {

	private Object[] queueArray;
	private int capacity;
	private int front;
	private int rear;
	private int Size;
	private static final int DEFAULT_QUEUE_SIZE = 10;

	/**
	 * Allocates a queue with a default capacity.
	 * @throws QueueAllocationException If the allocation of the array throws with Java exception.
	 */
	public QueueImplementation() throws QueueAllocationException {
		this(DEFAULT_QUEUE_SIZE);
	}

	/**
	 * Allocates a queue with the specified capacity.
	 * @param capacity The capacity of the queue.
	 * @throws QueueAllocationException If the allocation of the array throws with Java exception.
	 */
	public QueueImplementation(int capacity) throws QueueAllocationException {
		if (capacity < 1) {
			throw new QueueAllocationException("Capacity must be at least 1.");
		}
		this.capacity = capacity;
		queueArray = new Object[capacity];
		front = 0;
		rear = -1;
	}

	@Override
	public int capacity() {
		return this.capacity;
	}

	@Override
	public void enqueue(E element) throws QueueAllocationException, NullPointerException {
		if (element == null) {
			throw new NullPointerException("Cannot enqueue null element.");
		}
		if (size() == capacity()) {
			reallocate();
		}
		rear = (rear + 1) % capacity;
		queueArray[rear] = element;
		Size++;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E dequeue() throws QueueIsEmptyException {
		if (isEmpty()) {
			throw new QueueIsEmptyException("Queue is empty.");
		}
		E element = (E) queueArray[front];
		queueArray[front] = null;
		front = (front + 1) % capacity;
		Size--;
		return element;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E element() throws QueueIsEmptyException {
		if (isEmpty()) {
			throw new QueueIsEmptyException("Queue is empty.");
		}
		return (E) queueArray[front];
	}

	@Override
	public int size() {
		return Size;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public void clear() {
		for (int i = 0; i < capacity; i++) {
			queueArray[i] = null;
		}
		front = 0;
		rear = -1;
		Size = 0;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("[");
		for (int i = 0; i < Size; i++) {
			builder.append(queueArray[(front + i) % capacity]);
			if (i < Size - 1) {
				builder.append(", ");
			}
		}
		builder.append("]");
		return builder.toString();
	}

	private void reallocate() throws QueueAllocationException {
		int newCapacity = capacity * 2; // Double the capacity
		Object[] newQueueArray = new Object[newCapacity];
		int index = 0;
		while (!isEmpty()) {
			newQueueArray[index++] = dequeue();
		}
		Size=capacity;
		queueArray = newQueueArray;
		capacity = newCapacity;
		front = 0;
		rear = index - 1;
	}
}

