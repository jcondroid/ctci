import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.Queue;

public class ch3_stacks_n_queues {
    public static boolean debug = false;

    /**
     * NOTES
     * 1. Stacks can be useful in certain recursive algorithms. Sometimes you need to push
     *  temporary data onto a stack as you recurse, but then remove them as you backtrack 
     *  (for example, because the recursive check failed). 
     *  A stack offers an intuitive way to do this. A stack can also be used to implement 
     *  a recursive algorithm iteratively. (This is a good exercise! Take a simple recursive 
     *  algorithm and implement it iteratively.)
     * 2. It is especially easy to mess up the updating of the first and last nodes in a queue. 
     *  Be sure to double check this.
     * 3. One place where queues are often used is in breadth-first search or in implementing 
     *  a cache.
     * 4. In breadth-first search, for example, we used a queue to store a list of the nodes 
     *  that we need to process. Each time we process a node, we add its adjacent nodes to the 
     *  back of the queue. This allows us to process nodes in the order in which they are viewed. 
     */

    /**
     * STACK
     *  Keep track of Top
     */
/** Copied from Book. To to familiarize and memorize in case I need to implement one from scratch
    public class Stack<T> {
        private class Node<T> {
            private T data;
            private Node<T> next;
            
            public Node(T data) {
                this.data = data;
            }
        }
        
        private Node<T> top;
        
        // Remove the top item from the stack and return what was popped
        public T pop() {
            if (top == null) throw new EmptyStackException();
            T item = top.data; 
            top = top.next;
            return item;
        }

        // Add an item to the top of the stack
        public void push(T item) {
            Node<T> t = new Node<T>(item);
            t.next = top;
            top = t;
        }

        //  Return the top of the stack
        public T peek() {
            if (top == null) throw new EmptyStackException();
            return top.data;
        }

        // Return true if and only if the stack is empty
        public boolean isEmpty() {
            return top== null;
        }

        // Return min
    }
*/
    /**
     * QUEUE
     *  Keep track of First and Last
     */
    public class Queue<T> {
        private class Node<T> {
            private T data;
            private Node<T> next;
            
            public Node(T data) {
                this.data = data;
            }
        }
        
        private Node<T> first;
        private Node<T> last;
        
        // Add an item to the end of the list
        public void add(T item) {
            Node<T> t = new Node<T>(item);
            if (last != null) {
                last.next= t;
            }
            last = t;
            if (first == null) {
                first = last;
            }
        }
        // Remove the first item in the list and return that item
        public T remove() {
            if (first == null) throw new NoSuchElementException();
            T data = first.data;
            first = first.next;
            if (first == null) {
                last = null;
            }
            return data;
        }
        // Return the top of the queue
        public T peek() {
            if (first == null) throw new NoSuchElementException();
            return first.data;
        }
        // Return true if and only if the queue is empty
        public boolean isEmpty() {
            return first == null;
        }
    }
        

    public static void main(String[] args){
        // Stack<Integer> s = new Stack<Integer>();
        
        // 3.1
        /*
        threeInOne myStacks = new threeInOne(3, 3);
        // System.out.println(myStacks.isEmpty(1));System.out.println(myStacks.isEmpty(2));System.out.println(myStacks.isEmpty(3));
        System.out.println(myStacks.peek(1));
        myStacks.push(8, 1);
        System.out.println(myStacks.peek(1));
        myStacks.push(6, 1);
        System.out.println(myStacks.peek(1));
        myStacks.push(7, 1);
        myStacks.readStacks();
        // System.out.println("Peek 1: "+myStacks.peek(1));
        // System.out.println("Peek 2: "+myStacks.peek(2));
        // System.out.println("Peek 3: "+myStacks.peek(3));
        myStacks.pop(1);
        System.out.println("Peek 1: "+myStacks.peek(1));
        myStacks.push(10,2);
        myStacks.readStacks();
        System.out.println(myStacks.peek(2));
        myStacks.push(10,2);
        System.out.println(myStacks.peek(2));
        myStacks.push(2,2);
        System.out.println(myStacks.peek(2));
        myStacks.readStacks();
        */
        // 3.2
        // stackMin();
        // 3.3 
        // stackOfPlates();
        // 3.4 
        // queueViaStacks();
        // 3.5
        Stack<Integer> stackToSort = new Stack<Integer>();
        stackToSort.push(5);stackToSort.push(4);stackToSort.push(6);stackToSort.push(2);
        stackToSort.push(3);stackToSort.push(9);
        // sortStack(stackToSort);
        // 3.6 
        animalShelter();

    }
    /**
     * 3.1 Three in One: 
     *  Describe how you could use a single array to implement three stacks.
     */
    // Fixed size
    public static class threeInOne{
        private int stacksArr[];
        private int sizePerStack = 0;
        private int firstTop = -1; private int secondTop = 2; private int thirdTop = 5;
        private int firstCapacity = 0; private int secondCapacity = 0; private int thirdCapacity = 0;
        private int stacksCapacity = 0;
        public threeInOne(int stackSize, int numberOfStacks){
            sizePerStack = stackSize;
            stacksCapacity = sizePerStack * numberOfStacks;
            firstCapacity = stackSize;
            secondCapacity = firstCapacity + stackSize;
            thirdCapacity = secondCapacity + stackSize;
            // System.out.println("first Cap = "+firstCapacity);
            // System.out.println("2nd Cap = "+secondCapacity);
            // System.out.println("3rd Cap = "+thirdCapacity);
            stacksArr = new int[stacksCapacity];
        }
        public void readStacks(){
            String stackString = "";
            for(int stack : stacksArr){
                stackString += stack + ", ";
            }
            System.out.println(stackString);
        }
        // push
        public void push(int data, int stack){
            switch(stack){
                case 1:
                    if(firstTop == firstCapacity - 1){
                        System.out.println("This Stack is full. Terminating...\n");
                        System.exit(-1);
                    }
                    System.out.println("Pushing new item to Stack...");
                    stacksArr[++firstTop] = data;
                    break;
                case 2:
                    if(secondTop == secondCapacity - 1){
                        System.out.println("This Stack is full. Terminating...\n");
                        System.exit(-1);
                    }
                    System.out.println("Pushing new item to Stack...");
                    stacksArr[++secondTop] = data;
                    break;
                case 3:
                    if(thirdTop == thirdCapacity - 1){
                        System.out.println("This Stack is full. Terminating...\n");
                        System.exit(-1);
                    }
                    System.out.println("Pushing new item to Stack...");
                    stacksArr[++thirdTop] = data;
                    break;
                default:
                    System.out.println("Invalid stack selected. Terminating...");
                    System.exit(-1);
                    break;
            }
        }
        // pop
        public int pop(int stack){
            if(isEmpty(stack)){
                System.out.println("This stack is empty");
                // System.exit(-1);
                return -1;
            }else{
                switch(stack){
                    case 1:
                        return stacksArr[firstTop--];
                    case 2:
                        return stacksArr[secondTop--];
                    case 3:
                        return stacksArr[thirdTop--];
                    default:
                        System.out.println("Invalid stack selected. Terminating...");
                        System.exit(-1);
                        return 0;
                }
            }
        }
        // peek
        public int peek(int stack){
            if(!isEmpty(stack)){
                switch(stack){
                    case 1:
                        return stacksArr[firstTop];
                    case 2:
                        return stacksArr[secondTop];
                    case 3:
                        return stacksArr[thirdTop];
                    default:
                        System.out.println("Invalid stack selected. Terminating...");
                        System.exit(-1);
                        return 0;
                }
            }else{
                System.out.println("This stack is empty");
                return -1;
            }
        }
        // isEmpty
        public boolean isEmpty(int stack){
            switch(stack){
                case 1:
                    return firstTop == -1;
                case 2:
                    return secondTop == -1;
                case 3:
                    return thirdTop == -1;
                default:
                    System.out.println("Invalid stack selected. Terminating...");
                    System.exit(-1);
                    return false;
            }
        }
    }

    // Solution from the book (good to understand)
    class FixedMultiStack{
        private int numberOfStacks = 3;
        private int stackCapacity;
        private int[] values;
        private int[] sizes;
        public FixedMultiStack(int stackSize) {
            stackCapacity = stackSize;
            values = new int[stackSize * numberOfStacks];
            sizes = new int[numberOfStacks];
        }
        /* Push value onto stack */
        public void push(int stackNum, int value) throws Exception{ //throws FullStackException{
            /* Check that we have space for the next element */
            if (isFull(stackNum)) {
                // throw new FullStackException(); 
                throw new Exception();
            }
            /* Increment stack pointer and then update top value. */
            sizes[stackNum]++;
            values[indexOfTop(stackNum)] = value;
        }
        /* Pop item from top of stack */
        public int pop(int stackNum) {
            if (isEmpty(stackNum)) {
                throw new EmptyStackException();
            }
            int topIndex = indexOfTop(stackNum);
            int value = values[topIndex]; // Get top
            values[topIndex] = 0; // Clear
            sizes[stackNum]--; // Shrink
            return value; 
        }
        /* Return top element. */
        public int peek(int stackNum) {
            if (isEmpty(stackNum)) {
                throw new EmptyStackException();
            }
            return values[indexOfTop(stackNum)];
        }

        /* Return if stack is empty. */
        public boolean isEmpty(int stackNum) {
            return sizes[stackNum] == 0;
        }
        /* Return if stack is full. */
        public boolean isFull(int stackNum) {
            return sizes[stackNum] == stackCapacity;
        }
        /* Returns index of the top of the stack. */
        private int indexOfTop(int stackNum) {
            int offset = stackNum * stackCapacity;
            int size = sizes[stackNum];
            return offset+ size - 1; 
        }
    }

    /**
     * 3.2 Stack Min: 
     *  How would you design a stack which, in addition to push and pop, 
     *  has a function min which returns the minimum element? Push, 
     *  pop and min should all operate in 0(1) time
     */
    // There are 2 solutions for this. 
    // 1. is to add a new attribute to Node to keep track of the min
    // 2. (optimized) is to add an additional stack which keeps track of the mins
    // 2. is optimized because if we have a large stack then we waste a lot of space by 
    // keeping track of the min for every single element
    public static class MinStack{
        private Node head;
        public void push(int x){
            if(head == null){
                head = new Node(x,x,null);
            }else{
                head = new Node(x, Math.min(x, head.min), head);
            }
        }
        public void pop(){
            head = head.next;
        }
        public int peek(){
            return head.data;
        }
        public int min(){
            return head.min;
        }
        private class Node{
            int data;
            int min;
            Node next;
            private Node(int data, int min, Node next){
                this.data = data;
                this.min = min;
                this.next = next;
            }
        }
    }
    public static void stackMin(){
        MinStack myStack = new MinStack();
        myStack.push(8); myStack.push(6); myStack.push(7);
        System.out.println("myStack.min()="+myStack.min());
        myStack.pop();
        System.out.println("Poppin...");
        System.out.println("myStack.min()="+myStack.min());
        myStack.pop();
        System.out.println("Poppin...");
        System.out.println("myStack.min()="+myStack.min());
    }

    /**
     * 3.3 Stack of Plates: 
     *  Imagine a (literal) stack of plates. If the stack gets too high, it might topple. 
     *  Therefore, in real life, we would likely start a new stack when the previous 
     *  stack exceeds some threshold. Implement a data structure SetOfStacks that mimics 
     *  this. SetOfStacks should be composed of several stacks and should create a new 
     *  stack once the previous one exceeds capacity. SetOfStacks.push() and SetOfStacks.pop() 
     *  should behave identically to a single stack (that is, pop () should return 
     *  the same values as it would if there were just a single stack).
     *  FOLLOW UP
     *  Implement a function popAt ( int index) which performs a pop operation on a 
     *  specific sub-stack.
     */
    public static class MyStack{
        private Node head;
        private int capacity = 3;
        private int size = 0;

        public void push(int data){
            head = new Node(data, head);
            size++;
        }
        public int pop(){
            Node poppedNode = head;
            head = head.next;
            size--;
            return poppedNode.data;
        }
        public int peek(){
            return head.data;
        }
        public boolean isEmpty(){
            return head == null;
        }
        public boolean isFull(){
            return size == capacity;
        }
        public void readNodes(){
            
        }
        private class Node{
            int data;
            Node next;
            private Node(int data, Node next){
                this.data = data;
                this.next = next;
            }
        }
    }
    static class SetOfStacks{
        ArrayList<MyStack> stacks = new ArrayList<MyStack>();
        public void push(int value){
            // get last stack
            MyStack lastStack = getLastStack();
            if(lastStack != null && !lastStack.isFull()){
                lastStack.push(value);
            }else{
                MyStack stack = new MyStack();
                stack.push(value);
                stacks.add(stack);
            }
        }
        private MyStack getLastStack(){
            if(stacks.size() == 0) return null;
            return stacks.get(stacks.size() - 1);
        }
        private MyStack getStack(int index){
            if(stacks.size() == 0) return null;
            return stacks.get(index);
        }
        public void peek(){
            String stacksStr = "";
            for(MyStack stack : stacks){
                stacksStr += stack.head.data + ", ";
            }
            System.out.println(stacksStr);
        }
        public int pop(){
            // get last stack
            MyStack lastStack = getLastStack();
            if(lastStack == null){
                throw new EmptyStackException();
            }
            int v = lastStack.pop();
            if(lastStack.size == 0) stacks.remove(stacks.size() - 1);
            return v;
        }
        public int popAt(int index){
            MyStack indexedStack = getStack(index);
            if(indexedStack == null){
                throw new EmptyStackException();
            }
            int v = indexedStack.pop();
            if(indexedStack.size == 0) stacks.remove(index);
            return v;
        }
    }
    /**
    public static class SetOfStacks{
        private int stackCapacity;
        // private int stackSize = 3;
        private int[] stackSizes;
        private int[] values;
        public SetOfStacks(int size){
            stackCapacity = size;
            stackSizes = new int[1];
            values = new int[stackCapacity];
        }
        public void push(int value){
            if(isFull()){
                // create a new stack
                // allocate new space for stackSizes 
                int[] tmpStackSizes = new int[stackSizes.length + 1];
                tmpStackSizes = stackSizes;
                stackSizes = tmpStackSizes;
            }
            // add value to top stack
            stackSizes[stackSizes.length-1]++;
            values[indexOfTop()] = value;
        }
        public int pop(){
            if(isEmpty()){
                throw new EmptyStackException();
            }else{
                int topIndex = indexOfTop();
                int value = values[topIndex]; // Get top
                values[topIndex] = 0; // Clear
                stackSizes[stackSizes.length-1]--; // Shrink
                return value; 
            }
        }
        public boolean isFull(){
            System.out.println(stackSizes.length);
            return stackSizes[stackSizes.length - 1] == stackCapacity;
        }
        public boolean isEmpty(){
            return stackSizes[stackSizes.length-1] == 0;
        }
        public int indexOfTop(){
            int offset = stackSizes.length * stackCapacity;
            int size = stackSizes[stackSizes.length-1];
            return offset + size - 1;
        }
        public void readStacks(){
            String stackString = "";
            for(int stack : values){
                stackString += stack + ", ";
            }
            System.out.println(stackString);
        }
    }
    */
    public static void stackOfPlates(){
        SetOfStacks plates = new SetOfStacks();
        plates.push(3); 
        plates.push(2); plates.push(1);
        plates.peek();
        // plates.readStacks();
        // plates.readStacks();
        plates.push(4);plates.push(5);plates.push(6);
        plates.push(7); 
        System.out.println("Popped "+plates.pop());
        plates.peek();
        plates.pop();
        plates.peek();
        plates.push(7);// add 7 to end of 2nd stack
        plates.push(8);plates.push(8);plates.push(8);
        System.out.println("Popping 3rd stack: "+plates.popAt(2));
        plates.peek();
        System.out.println("Popping 2nd stack: "+plates.popAt(1));
        plates.peek();

    }

    /**
     * 3.4 Queue via Stacks: 
     *  Implement a MyQueue class which implements a queue using two stacks.
     */
    // Using java.util.Stack and Queue 
    static class MyQueue extends MyStack{
        Stack<Integer> normalStack;
        Stack<Integer> reversStack;
        MyQueue(){
            normalStack = new Stack<Integer>();
            reversStack = new Stack<Integer>();
        }
        public void add(int data){
            normalStack.add(data);
        }
        private void updateQueue(){
            if(reversStack.isEmpty()){
                while(!normalStack.isEmpty()){
                    reversStack.push(normalStack.pop());
                }
            }
        }
        public int remove(){
            updateQueue();
            return reversStack.pop();
        }
        public int peek(){
            updateQueue();
            return reversStack.peek();
        }
    }
    public static void queueViaStacks(){
        // I think the idea is to have on stack LIFO and then a 2nd stack that takes the top element from the 1st stack
        // and reverses it. MyQueue will require 2 stacks to implement
        
        MyQueue queue = new MyQueue();
        queue.add(8);System.out.println(queue.peek());
        queue.add(6);queue.add(7);queue.add(5);queue.add(3);queue.add(0);queue.add(9);
        System.out.println(queue.peek());System.out.println(queue.remove());System.out.println(queue.peek());
    }

    /**
     * 3.5 Sort Stack: 
     *  Write a program to sort a stack such that the smallest items are on the top. 
     *  You can use an additional temporary stack, but you may not copy the elements 
     *  into any other data structure (such as an array). 
     *  The stack supports the following operations: push, pop, peek, and isEmpty. 
     */
    public static Stack<Integer> sortStack(Stack<Integer> stackToSort){
        Stack<Integer> tempStack = new Stack<Integer>();

        while(!stackToSort.isEmpty()){
            if(tempStack.empty() || stackToSort.peek() < tempStack.peek()){
                tempStack.push(stackToSort.pop());
            }else{
                int tmp = stackToSort.pop();
                while(!tempStack.isEmpty()){
                    stackToSort.push(tempStack.pop());
                }
                stackToSort.push(tmp);
            }
        }
        // To Test uncomment
        // System.out.println("Is tmpStack empty? "+tempStack.empty());
        // System.out.println("Is stackToSort empty? "+stackToSort.empty());
        // String sortedStackStr = "";
        // while(!tempStack.isEmpty()){
        //     sortedStackStr += tempStack.pop() + ",";
        // }
        // System.out.println("Sorted stack: " + sortedStackStr);
        return tempStack;
    }

    /**
     * 3.6 Animal Shelter: 
     *  An animal shelter, which holds only dogs and cats, operates on a strictly 
     *  "first in, first out" basis. People must adopt either the "oldest" 
     *  (based on arrival time) of all animals at the shelter, or they can select whether 
     *  they would prefer a dog or a cat (and will receive the oldest animal of that type). 
     *  They cannot select which specific animal they would like. Create the data structures 
     *  to maintain this system and implement operations such as enqueue, dequeueAny, 
     *  dequeueDog, and dequeueCat. You may use the built-in Linked list data structure.
     */
    abstract static class Animal{
        private int order; // instead of timestamps just do this
        protected String name;
        public Animal(String animalName){
            name = animalName;
        }
        public void setOrder(int orderInLine){
            order = orderInLine;
        }
        public int getOrder(){
            return order;
        }
        /**
         * Compare orders of animals to return the animal that is in line the longest (FIFO)
         * @param a
         * @return
         */
        public boolean isOlderThan(Animal a){
            return this.order < a.getOrder();
        }
    }
    public static class Dog extends Animal{
        public Dog(String dogName){
            super(dogName);
        }
    }
    public static class Cat extends Animal{
        public Cat(String catName){
            super(catName);
        }
    }
    static class AnimalQueue{
        LinkedList<Dog> dogs = new LinkedList<Dog>();
        LinkedList<Cat> cats = new LinkedList<Cat>();
        private int order = 0;

        public void enqueue(Animal a){
            a.setOrder(order);
            order++;
            if(a instanceof Dog){
                dogs.addLast((Dog) a);
            }else if(a instanceof Cat){
                cats.addLast((Cat) a);
            }
        }
        // Note: It is important that Dog and Cat both inherit the Animal class because dequeueAny needs to be able
        // to return both Cat and Dog objects
        public Animal dequeueAny(){
            if(dogs.size() == 0){
                return dequeueCats();
            }else if(cats.size() == 0){
                return dequeueDogs();
            }
            Dog dog = dogs.peek();
            Cat cat = cats.peek();
            if(dog.isOlderThan(cat)){
                return dequeueDogs();
            }else{
                return dequeueCats();
            }
        }
        public Cat dequeueCats(){
            return cats.poll();
        }
        public Dog dequeueDogs(){
            return dogs.poll();
        }
    }
    public static void animalShelter(){
        AnimalQueue animalShelterQueue = new AnimalQueue();
        Dog spot = new Dog("Spot"); Dog skittles = new Dog("Skittles");
        animalShelterQueue.enqueue(spot);
        System.out.println("Just added "+animalShelterQueue.dogs.getLast().name);
        animalShelterQueue.enqueue(skittles);
        System.out.println("Just added "+animalShelterQueue.dogs.getLast().name);
        Cat shadow = new Cat("Shadow");
        animalShelterQueue.enqueue(shadow);
        System.out.println("Just added "+animalShelterQueue.cats.getLast().name);
        System.out.println("First Dog in queue "+animalShelterQueue.dogs.getFirst().name);
        animalShelterQueue.dequeueAny();
        System.out.println("First Dog in queue "+animalShelterQueue.dogs.getFirst().name);
        System.out.println("First Cat in queue "+animalShelterQueue.cats.getFirst().name);
    }
}
