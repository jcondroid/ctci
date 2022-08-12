class Stack{
    private int arr[];
    private int top;
    private int capacity;

    Stack(int size){
        capacity = size;
        top = -1;
        arr = new int[size];
    }

    // push
    public void push(int x){
        if(top == capacity - 1){
            System.out.println("Stack is full. Terminating...\n");
            System.exit(-1);
        }
        System.out.println("Pushing new item to Stack...");
        arr[++top] = x;
    }
    // pop
    public int pop(Stack item){
        if(isEmpty()){
            System.out.println("Stack is empty. Terminating...\n");
            System.exit(-1);
        }
        return arr[top--];
    }
    // peek
    public int peek(){
        if(!isEmpty()){
            return arr[top];
        }else{
            System.out.println("Stack is empty. Terminating...\n");
            System.exit(-1);
        }
        return top;
    }
    // isEmpty
    public boolean isEmpty(){
        return top == -1;
    }
}

class Main{
    public static void main(String[] args) {
        Stack myStack = new Stack(5);
        myStack.isEmpty();
        // myStack.peek();
        myStack.push(9);
        System.out.println(myStack.peek());
        System.out.println(myStack.isEmpty());
    }
}