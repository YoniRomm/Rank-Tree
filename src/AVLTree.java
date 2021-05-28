import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    private AVLNode rootNode; //root of the tree
    private final AVLNode virtualNode = new AVLNode(-1,false); //virtual node,
    // parent of root and child of every node that dont have real left or right child
    private int size = 0; //the amount of nodes in the tree

    private static int indexforKeysToArray=0; //global counter for recursive calls

    /** O(1)
     * This constructor creates an empty AVLTree.
     */
    public AVLTree(){
        this.virtualNode.setHeight(-1);
    }

    /** O(1)
     * public boolean empty()
     * <p>
     * returns true if and only if the tree is empty
     */
    public boolean empty() {
        return this.rootNode == null;
    }

    /** O(logn)
     *
     * return the node with key = k or null
     */
    public AVLNode find(int k){
        AVLNode x = this.rootNode;
        while(x.isRealNode()){
            if (k == x.key){
                return x;
            }
            if(k < x.key) {
                x = x.getLeft();
            }
            else {
                x = x.getRight();
            }
        }
        return null;
    }

    /** O(logn)
     * public boolean search(int k)
     * <p>
     * returns the info of an item with key k if it exists in the tree
     * otherwise, returns null
     */
    public Boolean search(int k) {
        AVLNode node = this.find(k);
        if(node != null){
            return node.info;
        }
        return null;
    }

    /** O(logn)
     * public int insert(int k, boolean i)
     * <p>
     * inserts an item with key k and info i to the AVL tree.
     * the tree must remain valid (keep its invariants).
	 * returns the number of nodes which require rebalancing operations (i.e. promotions or rotations).
	 * This always includes the newly-created node.
     * returns -1 if an item with key k already exists in the tree.
     */
    public int insert(int k, boolean i) {
        if(k<0){
            return 0;
        }
        AVLNode x = this.rootNode;
        if(x == null){
            insertFirst(k,i);
            return 0;
        }
        if(search(k) != null){ //if x already exists
            return -1;
        }
        setSize(this.size + 1);
        AVLNode y = null;
        AVLNode z = new AVLNode(k,i);

        while(x.isRealNode()){
            y = x;
            if(k<x.key) {
                x = x.getLeft();
            }
            else if(k > x.key){
                x=x.getRight();
            }
            else {
                return -1;
            }
        }
        z.setParent(y);
        if (z.key<y.key){
            y.setLeft(z);
        } else{
            y.setRight(z);
        }
        update_Xor_to_root(z);
        int counter=0;
        boolean isHeightChanged;
        AVLNode node_to_rotate = virtualNode;
        while(y.isRealNode()){
            int left_height = y.getLeft().getHeight();
            int right_height = y.getRight().getHeight();

            isHeightChanged=false;
            int curr_real_height = Math.max(left_height,right_height)+1;
            if (curr_real_height!=y.getHeight()){
                y.setHeight(curr_real_height);
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
                break;
            }
        }
        if(node_to_rotate.isRealNode()){
            doRotation(node_to_rotate);
        }
        return counter;
    }

    /** O(logn)
     *
     * update the Xor of all the nodes in the path from the node to the root
     */
    private void update_Xor_to_root(AVLNode node){
        update_Xor(node);
        AVLNode parent = node.getParent();
        while (parent.isRealNode()){
            update_Xor(parent);
            parent = parent.getParent();
        }
    }

    /** O(1)
     *
     * update the Xor of the given node by his childes
     */
    private void update_Xor(AVLNode node){
        node.Xor = node.info ^ node.getLeft().Xor ^ node.getRight().Xor;
    }

    /** O(1)
     *
     * insert node if the tree empty
     */
    private void insertFirst(int k,boolean i){
        this.rootNode = new AVLNode(k,i);
        setSize(this.size + 1);
    }

    /** O(1)
     *
     * return the BF of a given node.
     */
    private int getBF(AVLNode node){
        return node.getLeft().getHeight() - node.getRight().getHeight();
    }

    /** O(1)
     *
     * make a left rotation by the description we saw in class
     */
    private void rotateLeft(AVLNode node){
        AVLNode parent = node.getParent();
        AVLNode child = node.getRight();
        boolean isRoot = this.rootNode.getKey() == node.getKey();
        boolean isRight = false;
        if(!isRoot){
            isRight = parent.getRight().getKey() == node.getKey();
        }
        if(isRoot){
            this.rootNode = child;
        }
        node.setRight(child.getLeft());
        child.getLeft().setParent(node);
        child.setLeft(node);
        node.setParent(child);
        if(isRight){
            parent.setRight(child);
        } else if(!isRoot){
            parent.setLeft(child);
        }
        child.setParent(parent);
        node.height = Math.max(node.getLeft().getHeight(),node.getRight().getHeight())+1;
        parent = node.getParent();
        parent.setHeight(Math.max(parent.getLeft().getHeight(),parent.getRight().getHeight())+1);
        update_Xor(node);
        update_Xor(child);

    }

    /** O(1)
     *
     * make a right rotation by the description we saw in class
     */
    private void rotateRight(AVLNode node){
        AVLNode parent = node.getParent();
        AVLNode child = node.getLeft();
        boolean isRoot = this.rootNode.getKey() == node.getKey();
        boolean isLeft = false;
        if(!isRoot){
            isLeft = parent.getLeft().getKey() == node.getKey();
        }
        if(isRoot){
            this.rootNode = child;
        }
        node.setLeft(child.getRight());
        child.getRight().setParent(node);
        child.setRight(node);
        node.setParent(child);
        if(isLeft){
            parent.setLeft(child);
        } else if(!isRoot){
            parent.setRight(child);
        }
        child.setParent(parent);
        node.setHeight(Math.max(node.getLeft().getHeight(),node.getRight().getHeight())+1);
        parent = node.getParent();
        parent.setHeight(Math.max(parent.getLeft().getHeight(),parent.getRight().getHeight())+1);
        update_Xor(node);
        update_Xor(child);

    }

    /** O(1)
     *
     * analyze the type of criminal node (what BF) and call the rotations functions
     */
    private void doRotation(AVLNode node){
        if (getBF(node) == -2){
            if(getBF(node.getRight()) == 1){
                rotateRight(node.getRight());
            }
            rotateLeft(node);
        }
        if (getBF(node) == 2){
            if (getBF(node.getLeft()) == -1) {
                rotateLeft(node.getLeft());
            }
            rotateRight(node);
        }
    }

    /** O(1)
     *
     * make a left rotation by the description we saw in class
     */
    private boolean removeFromTreeCase1and2(AVLNode node){
        if(node.getLeft().isRealNode() && node.getRight().isRealNode()){
            return false;
        }
        if(node.getKey() == this.rootNode.getKey()){
            this.rootNode = null;
            return true;
        }
        AVLNode z = node.getParent();
        if(!node.getLeft().isRealNode() && !node.getRight().isRealNode()){ //x is a leaf
            if(z.getLeft().getKey() == node.getKey()){
                z.setLeft(virtualNode);
            } else{
                z.setRight(virtualNode);
            }
        }
        else if(node.getLeft().isRealNode() && !node.getRight().isRealNode()){ // x has only left child
            if(z.getLeft().getKey() == node.getKey()){
                z.setLeft(node.getLeft());
            } else{
                z.setRight(node.getLeft());
            }
            node.getLeft().setParent(z);
        }
        else if(!node.getLeft().isRealNode() && node.getRight().isRealNode()){ // x has only right child
            if(z.getLeft().getKey() == node.getKey()){
                z.setLeft(node.getRight());
            } else{
                z.setRight(node.getRight());
            }
            node.getRight().setParent(z);
        }
        return true;
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
        AVLNode x = find(k);
        if(x == null){
            return -1;
        }
        setSize(this.size - 1);
        AVLNode y = null;
        AVLNode node_to_rotate = x.getParent();
        boolean b = removeFromTreeCase1and2(x);
        if(!b){ // x has 2 children
            y = this.successor(x); // we need to delete y, he has no left child
            node_to_rotate = y.getParent();
            removeFromTreeCase1and2(y);
            x.getRight().setParent(y);
            x.getLeft().setParent(y);
            y.setLeft(x.getLeft());
            y.setRight(x.getRight());
            y.setParent(x.getParent());
            if(!(this.rootNode.getKey() == x.getKey())){
                if(x.getParent().getLeft().getKey() == x.getKey()){ //x is a left child
                    x.getParent().setLeft(y);
                } else{
                    x.getParent().setRight(y);
                }
            }
        }
        update_Xor_to_root(node_to_rotate);
        boolean isHeightChanged = false;
        int counter=0;
        y = node_to_rotate;
        while(y.isRealNode()){
            int left_height = y.getLeft().getHeight();
            int right_height = y.getRight().getHeight();

            isHeightChanged=false;
            int curr_real_height = Math.max(left_height,right_height)+1;
            if (curr_real_height!=y.getHeight()){
                y.setHeight(curr_real_height);
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
                doRotation(y);
                counter++;
                y = y.getParent();
            }
        }
        return counter;
    }

    /**
     * public Boolean min()
     * <p>
     * Returns the info of the item with the smallest key in the tree,
     * or null if the tree is empty
     */
    public Boolean min() {
        AVLNode node = findMin();
        if(node == null){
            return null;
        }
        return node.getValue();
    }

    /**
     * public Boolean max()
     * <p>
     * Returns the info of the item with the largest key in the tree,
     * or null if the tree is empty
     */
    public Boolean max() {
        if (this.rootNode == null){
            return null;
        }
        AVLNode node = this.rootNode;
        AVLNode parent = node;
        while(node.getKey() != -1){
            parent = node;
            node = node.getRight();
        }
        return parent.getValue();
    }

    private AVLNode findMin(){
        if (this.rootNode == null){
            return null;
        }
        AVLNode node = this.rootNode;
        AVLNode parent = node;
        while(node.getKey() != -1){
            parent = node;
            node = node.getLeft();
        }
        return parent;
    }



    /**
     * public int[] keysToArray()
     * <p>
     * Returns a sorted array which contains all keys in the tree,
     * or an empty array if the tree is empty.
     */

    public int[] keysToArray() {
        indexforKeysToArray=0;
        int[] arr = new int[size()]; // to be replaced by student code
        if(this.getRoot() == null){
            return arr;
        }
        req_keysToArray(arr,this.getRoot());
        return arr;              // to be replaced by student code
    }

    private void req_keysToArray(int[] arr,AVLNode node){
        while(node.getKey() != -1){
            req_keysToArray(arr,node.getLeft());
            arr[indexforKeysToArray++] = node.getKey();
            req_keysToArray(arr,node.getRight());
            break;
        }
    }

    /**
     * public boolean[] infoToArray()
     * <p>
     * Returns an array which contains all info in the tree,
     * sorted by their respective keys,
     * or an empty array if the tree is empty.
     */
    public boolean[] infoToArray() {
        indexforKeysToArray=0;
        boolean[] arr = new boolean[size()]; // to be replaced by student code
        if(this.getRoot() == null){
            return arr;
        }
        req_InfoToArray(arr,this.getRoot());
        return arr;              // to be replaced by student code
    }

    private void req_InfoToArray(boolean[] arr,AVLNode node){
        while(node.getKey() != -1){
            req_InfoToArray(arr,node.getLeft());
            arr[indexforKeysToArray++] = node.getValue();
            req_InfoToArray(arr,node.getRight());
            break;
        }
    }

    /**
     * public int size()
     * <p>
     * Returns the number of nodes in the tree.
     */
    public int size() {
        return this.size;
    }

    private void setSize(int size){
        this.size = size;
    }

    /**
     * public int getRoot()
     * <p>
     * Returns the root AVL node, or null if the tree is empty
     */
    public AVLNode getRoot() {
        return this.rootNode;
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
        AVLNode node = find(k);
        boolean b = node.Xor ^ node.getRight().Xor;
        AVLNode parent = node.getParent();
        while(parent.isRealNode()){
            if(parent.getRight().getKey() == node.getKey() && parent.getKey() <= k){
                b = b ^ parent.getLeft().Xor ^ parent.info;
            }
            parent = parent.getParent();
            node=node.getParent();
        }
        return b;
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
        AVLNode y = virtualNode;
        if(node.getRight().isRealNode()){
            AVLNode x = node.getRight();
            while(x.isRealNode()){
                y=x;
                x=x.getLeft();
            }
        } else{
            AVLNode x = node;
            y = node.getParent();
            while(y.isRealNode() && x == y.getRight()){
                x=y;
                y=x.parent;
            }
        }
        if(y.isRealNode()){
            return y;
        }
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
        int num_of_True = 0;
        AVLNode node = findMin();
        while(node.getKey() != k){
            num_of_True = node.info ? num_of_True+1 : num_of_True;
            node = successor(node);
        }
        num_of_True = node.info ? num_of_True+1 : num_of_True; //check for node k itself
        return num_of_True % 2 != 0;
    }

    public static void print(AVLNode root) {
        List<List<String>> lines = new ArrayList<List<String>>();

        List<AVLNode> level = new ArrayList<AVLNode>();
        List<AVLNode> next = new ArrayList<AVLNode>();

        level.add(root);
        int nn = 1;

        int widest = 0;

        while (nn != 0) {
            List<String> line = new ArrayList<String>();

            nn = 0;

            for (AVLNode n : level) {
                if (n==null || !n.isRealNode()) {
                    line.add(null);

                    next.add(null);
                    next.add(null);
                } else {
                    String aa = n.getText();
                    line.add(aa);
                    if (aa.length() > widest) widest = aa.length();

                    next.add(n.getLeft());
                    next.add(n.getRight());

                    if (n.getLeft().isRealNode()) nn++;
                    if (n.getRight().isRealNode()) nn++;
                }
            }

            if (widest % 2 == 1) widest++;

            lines.add(line);

            List<AVLNode> tmp = level;
            level = next;
            next = tmp;
            next.clear();
        }

        int perpiece = lines.get(lines.size() - 1).size() * (widest + 4);
        for (int i = 0; i < lines.size(); i++) {
            List<String> line = lines.get(i);
            int hpw = (int) Math.floor(perpiece / 2f) - 1;

            if (i > 0) {
                for (int j = 0; j < line.size(); j++) {

                    // split node
                    char c = ' ';
                    if (j % 2 == 1) {
                        if (line.get(j - 1) != null) {
                            c = (line.get(j) != null) ? '┴' : '┘';
                        } else {
                            if (j < line.size() && line.get(j) != null) c = '└';
                        }
                    }
                    System.out.print(c);

                    // lines and spaces
                    if (line.get(j) == null) {
                        for (int k = 0; k < perpiece - 1; k++) {
                            System.out.print(" ");
                        }
                    } else {

                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? " " : "─");
                        }
                        System.out.print(j % 2 == 0 ? "┌" : "┐");
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? "─" : " ");
                        }
                    }
                }
                System.out.println();
            }

            // print line of numbers
            for (int j = 0; j < line.size(); j++) {

                String f = line.get(j);
                if (f == null) f = "";
                int gap1 = (int) Math.ceil(perpiece / 2f - f.length() / 2f);
                int gap2 = (int) Math.floor(perpiece / 2f - f.length() / 2f);

                // a number
                for (int k = 0; k < gap1; k++) {
                    System.out.print(" ");
                }
                System.out.print(f);
                for (int k = 0; k < gap2; k++) {
                    System.out.print(" ");
                }
            }
            System.out.println();

            perpiece /= 2;
        }
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
        private AVLNode parent = virtualNode;
        private int height = 0;
        private boolean Xor;

        public String getText() {
            if (!this.isRealNode()) return "null";
            return this.info + ":" + Integer.toString(this.key) + " " + "h = " + this.height + " Xor = " + this.Xor;
        }

        public AVLNode(int key,boolean info){
            this.key = key;
            this.info = info;
            this.Xor = info;
        }

        //returns node's key (for virtual node return -1)
        public int getKey() {
            return this.key;
        }

        //returns node's value [info] (for virtual node return null)
        public Boolean getValue() {
            if (this.key == -1){
                return null;
            }
            return this.info;
        }

        //sets left child
        public void setLeft(AVLNode node) {
            this.leftChild = node;
        }

        //returns left child (if there is no left child return null)
        public AVLNode getLeft() {
            return this.leftChild;
        }

        //sets right child
        public void setRight(AVLNode node) {
            this.rightChild = node; // to be replaced by student code
        }

        //returns right child (if there is no right child return null)
        public AVLNode getRight() {
            return this.rightChild; // to be replaced by student code
        }

        //sets parent
        public void setParent(AVLNode node) {
            this.parent = node; // to be replaced by student code
        }

        //returns the parent (if there is no parent return null)
        public AVLNode getParent() {
            return this.parent; // to be replaced by student code
        }

        // Returns True if this is a non-virtual AVL node
        public boolean isRealNode() {
            return this.key != -1;
        }
        // sets the height of the node
        public void setHeight(int height) {
            this.height = height;
        }

        // Returns the height of the node (-1 for virtual nodes)
        public int getHeight() {
            return this.height; // to be replaced by student code
        }
    }

}


