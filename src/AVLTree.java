/**
 * public class AVLNode
 * <p>
 * This class represents an AVLTree with integer keys and boolean values.
 * <p>
 * IMPORTANT: do not change the signatures of any function (i.e. access modifiers, return type, function name and
 * arguments. Changing these would break the automatic tester, and would result in worse grade.
 * <p>
 * However, you are allowed (and required) to implement the given functions, and can add functions of your own
 * according to your needs.
 */

public class AVLTree {

    private AVLNode rootNode;
    private AVLNode virtualNode = new AVLNode(-1,false);


    /**
     * This constructor creates an empty AVLTree.
     */
    public AVLTree(){

    }

    /**
     * public boolean empty()
     * <p>
     * returns true if and only if the tree is empty
     */
    public boolean empty() {
        return this.rootNode == null;
    }

    /**
     * public boolean search(int k)
     * <p>
     * returns the info of an item with key k if it exists in the tree
     * otherwise, returns null
     */
    public Boolean search(int k) {
        AVLNode node = rootNode;
        while(node!=null){
            if(node.getKey()==k){
                return node.getValue();
            }
            if(node.getKey()>k){
                node = node.getLeft();
            }
            else{
                node = node.getRight();
            }
        }
        return null;
    }

    /**
     * public int insert(int k, boolean i)
     * <p>
     * inserts an item with key k and info i to the AVL tree.
     * the tree must remain valid (keep its invariants).
	 * returns the number of nodes which require rebalancing operations (i.e. promotions or rotations).
	 * This always includes the newly-created node.
     * returns -1 if an item with key k already exists in the tree.
     */
    public int insert(int k, boolean i) {
        AVLNode y = null;
        AVLNode x = this.rootNode;
        AVLNode z = new AVLNode(k,i);


        while(x!= null){
            y = x;
            if(k<x.key) {
                x = x.getLeft();
            }
            if(k > x.key){
                x=x.getRight();
            }
            else {
                return -1;
            }
        }
        z.setParent(y);
        if(y == null){
            this.rootNode = z;
        }
        boolean isRight = false;
        boolean isHeightChanged = false;
        if (z.key<y.key){
            y.setLeft(z);
        } else{
            y.setRight(z);
            isRight = true;
        }
        int counter=0;
        AVLNode node_to_rotate = virtualNode;
        while(y != null){
            int left_height = y.getLeft().getHeight();
            int right_height = y.getRight().getHeight();
            isHeightChanged=false;
            if (Math.max(left_height,right_height)+1!=y.getHeight()){
                y.setHeight(y.getHeight()+1);
                isHeightChanged=true;
            }
            int BF = getBF(y);
            if(Math.abs(BF) < 2 && !isHeightChanged){
                return counter;
            }
            else if(Math.abs(BF) < 2 && isHeightChanged){
                y = y.getParent();
            }
            else {
                if(node_to_rotate.getKey()==-1){
                    node_to_rotate=y;
                }
                counter++;
            }
        }
        doRotation(node_to_rotate);
        return counter;
    }

    private int getBF(AVLNode node){
        return node.getLeft().getHeight() - node.getRight().getHeight();
    }

    private void doRotation(AVLNode node){
        int bf = getBF(node);

    }

    /**
     * public int delete(int k)
     * <p>
     * deletes an item with key k from the binary tree, if it is there;
     * the tree must remain valid (keep its invariants).
     * returns the number of nodes which required rebalancing operations (i.e. demotions or rotations).
     * returns -1 if an item with key k was not found in the tree.
     */
    public int delete(int k) {
        return 42;    // to be replaced by student code
    }

    /**
     * public Boolean min()
     * <p>
     * Returns the info of the item with the smallest key in the tree,
     * or null if the tree is empty
     */
    public Boolean min() {
        return null; // to be replaced by student code
    }

    /**
     * public Boolean max()
     * <p>
     * Returns the info of the item with the largest key in the tree,
     * or null if the tree is empty
     */
    public Boolean max() {
        return null; // to be replaced by student code
    }

    /**
     * public int[] keysToArray()
     * <p>
     * Returns a sorted array which contains all keys in the tree,
     * or an empty array if the tree is empty.
     */
    public int[] keysToArray() {
        int[] arr = new int[42]; // to be replaced by student code
        return arr;              // to be replaced by student code
    }

    /**
     * public boolean[] infoToArray()
     * <p>
     * Returns an array which contains all info in the tree,
     * sorted by their respective keys,
     * or an empty array if the tree is empty.
     */
    public boolean[] infoToArray() {
        boolean[] arr = new boolean[42]; // to be replaced by student code
        return arr;                    // to be replaced by student code
    }

    /**
     * public int size()
     * <p>
     * Returns the number of nodes in the tree.
     */
    public int size() {
        return 42; // to be replaced by student code
    }

    /**
     * public int getRoot()
     * <p>
     * Returns the root AVL node, or null if the tree is empty
     */
    public AVLNode getRoot() {
        return null;
    }

    /**
     * public boolean prefixXor(int k)
     *
     * Given an argument k which is a key in the tree, calculate the xor of the values of nodes whose keys are
     * smaller or equal to k.
     *
     * precondition: this.search(k) != null
     *
     */
    public boolean prefixXor(int k){
        return false;
    }

    /**
     * public AVLNode successor
     *
     * given a node 'node' in the tree, return the successor of 'node' in the tree (or null if successor doesn't exist)
     *
     * @param node - the node whose successor should be returned
     * @return the successor of 'node' if exists, null otherwise
     */
    public AVLNode successor(AVLNode node){
        return null;
    }

    /**
     * public boolean succPrefixXor(int k)
     *
     * This function is identical to prefixXor(int k) in terms of input/output. However, the implementation of
     * succPrefixXor should be the following: starting from the minimum-key node, iteratively call successor until
     * you reach the node of key k. Return the xor of all visited nodes.
     *
     * precondition: this.search(k) != null
     */
    public boolean succPrefixXor(int k){
        return false;
    }


    /**
     * public class AVLNode
     * <p>
     * This class represents a node in the AVL tree.
     * <p>
     * IMPORTANT: do not change the signatures of any function (i.e. access modifiers, return type, function name and
     * arguments. Changing these would break the automatic tester, and would result in worse grade.
     * <p>
     * However, you are allowed (and required) to implement the given functions, and can add functions of your own
     * according to your needs.
     */
    public class AVLNode {

        private int key;
        private boolean info;
        private AVLNode leftChild = virtualNode;
        private AVLNode rightChild = virtualNode;
        private int height;


        public AVLNode(int key,boolean info){
            this.key = key;
            this.info = info;

        }

        //returns node's key (for virtual node return -1)
        public int getKey() {
            if (this.key == -1){
                return -1;
            }
            return this.key;
        }

        //returns node's value [info] (for virtual node return null)
        public Boolean getValue() {
            return null;
        }

        //sets left child
        public void setLeft(AVLNode node) {
            return; // to be replaced by student code
        }

        //returns left child (if there is no left child return null)
        public AVLNode getLeft() {
            return this.leftChild;
        }

        //sets right child
        public void setRight(AVLNode node) {
            return; // to be replaced by student code
        }

        //returns right child (if there is no right child return null)
        public AVLNode getRight() {
            return this.rightChild; // to be replaced by student code
        }

        //sets parent
        public void setParent(AVLNode node) {
            return; // to be replaced by student code
        }

        //returns the parent (if there is no parent return null)
        public AVLNode getParent() {
            return null; // to be replaced by student code
        }

        // Returns True if this is a non-virtual AVL node
        public boolean isRealNode() {
            return true; // to be replaced by student code
        }

        // sets the height of the node
        public void setHeight(int height) {
            return; // to be replaced by student code
        }

        // Returns the height of the node (-1 for virtual nodes)
        public int getHeight() {
            return this.height; // to be replaced by student code
        }
    }

}


