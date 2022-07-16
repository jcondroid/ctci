import java.util.HashSet;
public class ch2_linked_list {
    public static boolean debug = false;

    /**
     * Node Class
     */
    public static class Node {
        Node next = null;
        int data;

        public Node(int d){
            data = d;
        }

        void add(int d){
            Node end = new Node(d);
            Node n = this;
            while(n.next != null){
                n = n.next;
            }
            n.next = end;
        }

        void addNode(Node end){
            Node n = this;
            while(n.next != null){
                n = n.next;
            }
            n.next = end;
        }

        Node deleteNode(Node head, int d){
            Node n = head;
            if(n.data == d){
                return head.next; // moved head
            }
            while(n.next != null){
                if(n.next.data == d){
                    n.next = n.next.next;
                    return head; // head didn't change
                }
                n = n.next;
            }
            return head;
        }
    }
    public static void main(String[] args){
        // System.out.println(isUniqueWithDataStructure("abbcd"));
        // System.out.println(isUniqueWithoutDataStructure("abccd"));
        // System.out.println(removeDups("abca", "ba"));
        Node n = new Node(0);
        n.add(1);
        n.add(1);
        n.add(2);
        Node middle_node = new Node(7);
        n.addNode(middle_node);
        n.add(3);
        n.add(5);
        n.add(5);
        // readNode(n);
        
        /* 2.1
        System.out.println("Running removingDups...");
        removeDups(n);
        readNode(n);
        System.out.println("Adding dups 1 and 5...");
        n.add(1); n.add(5);
        readNode(n);
        System.out.println("Running removeDupsWithoutBuffer...");
        removeDupsWithoutBuffer(n);
        readNode(n);
        */

        // 2.2
        // System.out.println("k to last: "+kToLast(n, 7).data);

        // 2.3 
        // deleteMiddleNode(middle_node);
        // readNode(n);

        // 2.4 
        // 3 -> 5 -> 8 -> 5 -> 10 -> 2 -> 1 [partition= 5]
        /*
        Node p = new Node(3); p.add(5); p.add(8); p.add(5);
        p.add(10); p.add(2); p.add(1);
        readNode(p);
        System.out.println("Partitioning previously defined linked list...");
        Node partitioned_node = partition(p, 5);
        readNode(partitioned_node);
        */
        // Answer: 3 -> 1 -> 2 -> 10 -> 5 -> 5 -> 8

        // 2.5
        // (7-> 1 -> 6) + (5 -> 9 -> 2)
        // Node n1 = new Node(7); n1.add(1); n1.add(6);
        // Node n2 = new Node(5); n2.add(9); n2.add(2);
        // Node sum = reverseSumLists(n1, n2); readNode(sum);
        // n1 = new Node(1); n1.add(2); n1.add(3);
        // n2 = new Node(6); n2.add(7);
        // sum = reverseSumLists(n1, n2); readNode(sum);
        // Follow up: (6 -> 1 -> 7) + (2 -> 9 -> 5) = 912
        // Node n1 = new Node(6); n1.add(1); n1.add(7);
        // Node n2 = new Node(2); n2.add(9); n2.add(5);
        // int sum = sumLists(n1, n2); System.out.println(sum);
        // n1 = new Node(1); n1.add(2); n1.add(3);
        // n2 = new Node(6); n2.add(7);
        // sum = sumLists(n1, n2); System.out.println(sum);

        // 2.6 Palindrome ex: 1>2>3>0>3>2>1
        // Node pal = new Node(1); pal.add(2); pal.add(3); pal.add(0); 
        // pal.add(3); pal.add(2); pal.add(1);
        // System.out.println("Is 1>2>3>0>3>2>1 a palindrome?");
        // System.out.println(palindrome(pal));
        // Node not_pal = new Node(1); not_pal.add(2); not_pal.add(3); not_pal.add(4);
        // System.out.println("Is 1>2>3>4 a palindrome?");
        // System.out.println(palindrome(not_pal));

        // 2.7 Intersecting Node
        // let o be have the same node "middle_node" so there is an interesting node
        // let q be another ll without any intersecting node
        /**
        Node o = new Node(8);
        o.add(6);
        o.addNode(middle_node); // intersecting node
        o.add(5);
        // o.add(3);
        // o.add(0);
        // o.add(9);
        // readNode(n);
        System.out.println("Do nodes n and o intersect?");
        // readNode(o);
        System.out.println(intersection(n, o));
        Node q = new Node(4); q.add(4); q.add(4); q.add(2);
        System.out.println("Do nodes n and o intersect?");
        System.out.println(intersection(n, q));
        */

        // 2.8 - loopDetection
        Node c = new Node(0);
        c.add(9);
        c.add(3);
        c.add(c.data);
        Node runner = c;
        while(runner != null){
            runner = runner.next;
            if(runner.next == null){
                runner.next = c; // Here runner is a circular linked list
                break;
            }
        }
        // readNode(runner);
        System.out.println("Is runner a circular ll? (Hint: yes it is)");
        System.out.println(loopDetection(runner));

        System.out.println("Is n a circular ll? (Hint: no it is not)");
        System.out.println(loopDetection(n));
    }
    /**
     * Node helper functions
     */
    private static void readNode(Node n){
        Node runner = n;
        while(runner != null){
            System.out.println(runner.data);
            runner = runner.next;
        }
    }
    /**
     * 2.1 Remove Dups: 
     *  Write code to remove duplicates from an unsorted linked list.
     * `FOLLOW UP
     * `How would you solve this problem if a temporary buffer is not allowed?
     */
    // Using an addition data structure (Set)
    public static void removeDups(Node n){
        Node runner = n;
        HashSet<Integer> node_set = new HashSet<Integer>();
        while(runner != null){
            if(!node_set.add(runner.data)){
                n.deleteNode(n, runner.data);
            }
            // System.out.println(runner.data);
            runner = runner.next;
        }
    }
    // Without using a buffer
    public static void removeDupsWithoutBuffer(Node n){
        Node runner = n;
        while(runner != null){
            Node runner2 = runner.next; // why n? should be runner.next
            while(runner2 != null){
                if(runner2.data == runner.data){ //delete runner and break
                    n.deleteNode(n, runner.data);
                    break;
                }
                runner2 = runner2.next;
            }
            runner = runner.next;
        }
    }

    /**
     * 2.2 Return Kth to Last: 
     *  Implement an algorithm to find the kth to last element of a singly linked list
     *  Assumptions: k > 0
     */
    public static Node kToLast(Node head, int k){
        Node runner = head;
        Node runner_plus_k = runner;
        for(int i = 0; i < k; i++){
            runner_plus_k = runner_plus_k.next;
        }
        while(runner_plus_k != null){
            //0,1,1,2,3,5,5
            runner = runner.next;
            runner_plus_k = runner_plus_k.next;
        }
        return runner;
    }

    /**
     * 2.3 Delete Middle Node: 
     *  Implement an algorithm to delete a node in the middle (i.e., any node but
     *  the first and last node, not necessarily the exact middle) of a singly 
     *  linked list, given only access to that node.
     *  EXAMPLE
     *  Input:the node c from the linked list: a->b->c->d->e->f
     *  Result: nothing is returned, but the new linked list looks like a->b->d->e->f 
     * My Assumption: The user cannot use the head as a param
     */
    public static void deleteMiddleNode(Node middle_node){
        if(middle_node != null && middle_node.next != null){
            Node next_node = middle_node.next;
            middle_node.data = next_node.data;
            middle_node.next = next_node.next;
        }
    }

    /**
     * 2.4 Partition: 
     *  Write code to partition a linked list around a value x, such that all nodes 
     *  less than x come before all nodes greater than or equal to x. 
     *  If x is contained within the list, the values of x only need to be after the 
     *  elements less than x (see below). The partition element x can appear anywhere 
     *  in the "right partition"; 
     *  it does not need to appear between the left and right partitions.
     *  EXAMPLE
     *  Input:  3 -> 5 -> 8 -> 5 -> 10 -> 2 -> 1 [partition= 5]
     *  Output: 3 -> 1 -> 2 -> 10 -> 5 -> 5 -> 8
     */
    public static Node partition(Node n, int partition){
        Node runner = n;
        Node left_partitioned_node = new Node(0);
        Node right_partitioned_node = new Node(0);
        while(runner != null){
            if(runner.data < partition){
                left_partitioned_node.add(runner.data); 
            }else{
                right_partitioned_node.add(runner.data);
            }
            runner = runner.next;
        }
        left_partitioned_node = left_partitioned_node.next;
        runner = left_partitioned_node;
        while(left_partitioned_node != null){
            if(left_partitioned_node.next == null){
                left_partitioned_node.next = right_partitioned_node.next;
                break;
            }
            left_partitioned_node = left_partitioned_node.next;
        }
        return runner;
    }

    /**
     * 2.5 Sum Lists: 
     *  You have two numbers represented by a linked list, where each node 
     *  contains a single digit. The digits are stored in reverse order, 
     *  such that the 1 's digit is at the head of the list. Write a function 
     *  that adds the two numbers and returns the sum as a linked list.
     *  EXAMPLE
     *  Input: (7-> 1 -> 6) + (5 -> 9 -> 2).That is,617 + 295.
     *  Output: 2 -> 1 -> 9. That is, 912.
     *  FOLLOW UP
     *  Suppose the digits are stored in forward order. Repeat the above problem.
     *  EXAMPLE
     *  Input:(6 -> 1 -> 7) + (2 -> 9 -> 5).That is,617 + 295.
     *  Output: 9 -> 1 -> 2. That is, 912.
     */
    // (7-> 1 -> 6) + (5 -> 9 -> 2)
    public static Node reverseSumLists(Node list1, Node list2){
        int sum = 0; Node sum_node = new Node(0); int num1 = 0; int num2 = 0; 
        int remainder = 0; int carry_over = 0;
        while(list1 != null || list2 != null){
            sum = 0;
            num1 = (list1 != null) ? list1.data : 0;
            num2 = (list2 != null) ? list2.data : 0;
            sum = num1 + num2 + carry_over;
            remainder = sum % 10;
            sum_node.add(remainder);
            if(sum > 10){
                carry_over = 1;
            } else {
                carry_over = 0;
            }
            if(list1 != null) list1 = list1.next;
            if(list2 != null) list2 = list2.next;
        }
        // readNode(sum_node.next);
        // sum = 0;
        sum_node = sum_node.next;
        // while(sum_node != null){
        //     sum = sum * 10 + sum_node.data;
        //     sum_node = sum_node.next;
        // }
        // System.out.println(num2);
        // num1 = list1.data + list2.data;

        return sum_node;
    }
    public static int sumLists(Node list1, Node list2){
        int num1 = 0; int num2 = 0;
        while(list1 != null){
            num1 = num1 * 10 + list1.data;
            list1 = list1.next;
        }
        while(list2 != null){
            num2 = num2 * 10 + list2.data;
            list2 = list2.next;
        }
        System.out.println(num1);
        System.out.println(num2);
        // num1 = list1.data + list2.data;

        return num1+num2;
    }

    /**
     * 2.6 Palindrome: 
     *  Implement a function to check if a linked list is a palindrome
     *  Palindrome is the same forwards and backwards, ex: 1>2>3>0>3>2>1
     */
    public static boolean palindrome(Node head){
        HashSet<Integer> node_set = new HashSet<Integer>(); int palindrome_counter = 0;
        Node runner = head;
        while(runner != null){
            if(node_set.add(runner.data)){
                palindrome_counter += 1;
            }else{
                palindrome_counter -= 1;
            }
            runner = runner.next;
        }
        return palindrome_counter == 0 || palindrome_counter == 1;
    }

    /**
     * 2.7 Intersection: 
     *  Given two (singly) linked lists, determine if the two lists intersect. 
     *  Return the intersecting node. Note that the intersection is defined based 
     *  on reference, not value. That is, if the kth node of the first linked list is 
     *  the exact same node (by reference) as the jth node of the second linked list, 
     *  then they are intersecting. 
     */
    public static boolean intersection(Node n1, Node n2){
        Node runner1 = n1; Node runner2 = n2;
        while(runner1 != null){
            while(runner2 != null){
                if(runner2 == runner1){
                    return true;
                }
                runner2 = runner2.next;
            }
            runner2 = n2;
            runner1 = runner1.next;
        }

        return false;
    }

    /**
     * 2.8 Loop Detection: 
     *  Given a circular linked list, implement an algorithm that returns the node at the
     *  beginning of the loop.
     *  DEFINITION
     *  Circular linked list: A (corrupt) linked list in which a node's next pointer 
     *  points to an earlier node, so as to make a loop in the linked list.
     *  EXAMPLE
     *  Input: A -> B -> C -> D -> E -> C [the same C as earlier]
     *  Output: C
     */
    public static boolean loopDetection(Node head){
        Node slowRunner = head; Node fastRunner = head.next;
        while(fastRunner != null){
            if(fastRunner.next == null || fastRunner.next.next == null){
                return false;
            }
            if(fastRunner == slowRunner){
                return true;
            }
            slowRunner = slowRunner.next;
            fastRunner = fastRunner.next.next;
        }
        return false;
    }
}
