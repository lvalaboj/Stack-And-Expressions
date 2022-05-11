public class MyStack<Item> {


    /**
     * Pointer to top of the stack
     */
    private StackNode<Item> top;
    /**
     * Keeps track of the size of the stack
     */
    private int size;

    /**
     * Default constructor of the class initializes all
     * parameters to default values
     */
    public MyStack() {
        this.top = null;
        this.size = 0;
    }

    /**
     * @return if the stack is empty or not
     */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /**
     * Pushes a new a new value into the stack
     * Remember to update size
     *
     * @param value the value to be pushed
     */
    public void push(Item value) {
        StackNode<Item> new_val = new StackNode<>();
        new_val.value = value;
        new_val.next = top;
        top = new_val;
        size++;
    }

    /**
     * Peeks the top element of the stack
     *
     * @return the value at the top of the stack
     * @throws EmptyStackException if the stack is empty.
     *                             You can throw an exception by “throw new EmptyStackException();”
     */
    public Item peek() throws EmptyStackException {
        if (size > 0 ) {
            return top.value;
        }
        throw new EmptyStackException();
    }

    /**
     * Pops the top element of the stack
     * Remember to update size
     *
     * @return the popped element
     * @throws EmptyStackException if the stack is empty
     *                             You can throw an exception by “throw new EmptyStackException();”
     */
    public Item pop() throws EmptyStackException {
        if (size > 0) {
            StackNode<Item> popped_val = new StackNode<>();
            popped_val = top;  
            size--;
            return popped_val.value;
        }
        throw new EmptyStackException();
    }

    /**
     * @return the size of the stack
     */
    public int getSize() {
        return size;
    }

}
