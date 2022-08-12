import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class treeTraversal {
    public static void main(String args[]){
        BinaryTreeNode tree = constructTree();
        // System.out.println("PreOrder Traversal: ");
        // preOrder(tree);
        // System.out.println(preorderTraversal(tree).toString());
        // System.out.println("InOrder Traversal: ");
        // inOrder(tree);
        // System.out.println(inorderTraversal(tree).toString());
        // System.out.println("PostOrder Traversal: ");
        // postOrder(tree);
        // System.out.println(postorderTraversal(tree).toString());
        // levelOrderTraversal(tree);
        // problem 1
        // System.out.println(maxNodeBinaryTree(tree));
        // p2
        // System.out.println(maxNode(tree));
        // p3
        // System.out.println(doesElementExistInTree(tree, 8));
    }
    private static BinaryTreeNode constructTree(){
        /**
         *      1
         *     / \
         *    2   3
         *   / \ / \
         *  4  5 6  7
         */
        BinaryTreeNode tree = new BinaryTreeNode(1);
        BinaryTreeNode treeL = new BinaryTreeNode(2);
        BinaryTreeNode treeR = new BinaryTreeNode(3);
        BinaryTreeNode tree4 = new BinaryTreeNode(4);
        BinaryTreeNode tree5 = new BinaryTreeNode(5);
        BinaryTreeNode tree6 = new BinaryTreeNode(6);
        BinaryTreeNode tree7 = new BinaryTreeNode(7);
        tree.setLeft(treeL);
        tree.setRight(treeR);
        treeL.setLeft(tree4);
        treeL.setRight(tree5);
        treeR.setLeft(tree6);
        treeR.setRight(tree7);
        return tree;
    }
    /**
     * Recursion
     */
    private static void preOrder(BinaryTreeNode root){
        if(root != null){
            System.out.println(root.data);
            preOrder(root.left);
            preOrder(root.right);
        }
    }
    private static void inOrder(BinaryTreeNode root){
        if(root != null){
            inOrder(root.left);
            System.out.println(root.data);
            inOrder(root.right);
        }
    }
    private static void postOrder(BinaryTreeNode root){
        if(root != null){
            postOrder(root.left);
            postOrder(root.right);
            System.out.println(root.data);
        }
    }
    /**
     * Iterative
     */
    // DLR
    private static ArrayList<Integer> preorderTraversal(BinaryTreeNode root){
        ArrayList<Integer> res = new ArrayList<Integer>();
        // if(res == null) return res;
        Stack<BinaryTreeNode> s = new Stack<BinaryTreeNode>();
        s.push(root);
        while(!s.isEmpty()){
            BinaryTreeNode tmp = s.pop();
            res.add(tmp.data);
            // pay more attention to this sequence
            if(tmp.right != null){
                s.push(tmp.right);
            }
            if(tmp.left != null){
                s.push(tmp.left);
            }
        }
        return res;
    }
    // LDR
    private static ArrayList<Integer> inorderTraversal(BinaryTreeNode root){
        ArrayList<Integer> res = new ArrayList<Integer>();
        Stack<BinaryTreeNode> s = new Stack<BinaryTreeNode>();
        BinaryTreeNode currentNode = root;
        boolean done = false;
        while(!done){
            if(currentNode != null){
                s.push(currentNode);
                currentNode = currentNode.left;
            }else{
                if(s.isEmpty()){
                    done = true;
                }else{
                    currentNode = s.pop();
                    res.add(currentNode.data);
                    currentNode = currentNode.right;
                }
            }
        }
        return res;
    }
    // LRD
    private static ArrayList<Integer> postorderTraversal(BinaryTreeNode root){
        ArrayList<Integer> res = new ArrayList<Integer>();
        Stack<BinaryTreeNode> s = new Stack<BinaryTreeNode>();
        s.push(root);
        BinaryTreeNode prev = null;
        while(!s.isEmpty()){
            BinaryTreeNode current = s.peek();
            if(prev == null || prev.left == current || prev.right == current){
                // traverse from top to bottom, and if current has left child or right child,
                // push onto the stack; otherwise pop out
                if(current.left != null){
                    s.push(current.left);
                }else if (current.right != null){
                    s.push(current.right);
                }
            }else if(current.left == prev){
                if(current.right != null){
                    s.push(current.right);
                }
            }else{
                res.add(current.data);
                s.pop();
            }
            prev = current;
        }
        return res;
    }
    /**
     * Level Order Traversal
     * 1. Visit root.
     * 2. While traversing level 1, keep all the elements at level 1+1 in queue.
     * 3. Go to the next level and visit all the nodes at that level
     * 4. Repeat this until all levels are completed.
     * @param root
     * @return
     */
    private static ArrayList<ArrayList<Integer>> levelOrderTraversal(BinaryTreeNode root){
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(root == null) return res;
        Queue<BinaryTreeNode> q = new LinkedList<BinaryTreeNode>();
        q.offer(root);
        q.offer(null);
        ArrayList<Integer> curr = new ArrayList<Integer>();
        while(!q.isEmpty()){
            BinaryTreeNode tmp = q.poll();
            if(tmp.left != null){
                q.offer(tmp.left);
            }
            if(tmp.right != null){
                q.offer(tmp.right);
            } else{
                ArrayList<Integer> c_curr = new ArrayList<Integer>(curr);
                res.add(c_curr);
                curr.clear(); // Java will clear the reference, so we have to new a new ARrayList
                // completion of a level
                if(!q.isEmpty()){
                    q.offer(null);
                }
            }
        }
        return res;
    }
    // problem 1 - find the maximum element in a binary tree
    // idea is to traverse left side and get the maximum and then right side and get maximum and then compare
    // time: O(n) space: O(n)
    private static int maxNodeBinaryTree(BinaryTreeNode root){
        int max = Integer.MIN_VALUE;
        if(root != null){
            int maxLeft = maxNodeBinaryTree(root.left);
            int maxRight = maxNodeBinaryTree(root.right);
            if(maxLeft > maxRight){
                max = maxLeft;
            }else{
                max = maxRight;
            }
            if(root.data > max) max = root.data;
        }
        return max;
    }
    // p2
    private static Integer maxNode(BinaryTreeNode root){
        ArrayList<Integer> res = new ArrayList<Integer>();
        Stack<BinaryTreeNode> s = new Stack<BinaryTreeNode>();
        int maxNode = Integer.MIN_VALUE;
        s.push(root);
        while(!s.isEmpty()){
            BinaryTreeNode tmp = s.pop();
            res.add(tmp.data);
            if(tmp.data > maxNode) maxNode = tmp.data;
            if(tmp.right != null){
                s.push(tmp.right);
            }
            if(tmp.left != null){
                s.push(tmp.left);
            }
        }
        return maxNode;
    }
    // give an algorithm for searching an element in binary tree
    private static boolean doesElementExistInTree(BinaryTreeNode root, int element){
        if(root == null){
            return false;
        }
        if(root.data == element){
            return true;
        }
        return doesElementExistInTree(root.left, element) || doesElementExistInTree(root.right, element);
    }
    // do same thing without recursion
    
}
