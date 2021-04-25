import java.util.ArrayList;
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

    private AVLNode rootNode;
    private final AVLNode virtualNode = new AVLNode(-1,false);

    public static void main(String [] args){
        AVLTree t1 = new AVLTree();
//        System.out.println(t1.getRoot());
        t1.insert(5,false);
        t1.insert(3,false);
        t1.insert(6,false);
//        AVLTree.print(t1.getRoot());
    }




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
        if(x == null){
            insertFirst(k,i);
            return 0;
        }


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
//            System.out.println(y.getLeft().getKey());
//            int height = z.getHeight();
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

    private void insertFirst(int k,boolean i){
        this.rootNode = new AVLNode(k,i);
    }

    private int getBF(AVLNode node){
        return node.getLeft().getHeight() - node.getRight().getHeight();
    }

    private void doRotation(AVLNode node){
        int bf = getBF(node);
        AVLNode parent = node.getParent();
        boolean isRight = parent.getRight().getKey() == node.getKey();
        if (bf == -2){
            if(getBF(node.getRight()) == 1){
            } else {
                AVLNode child = node.getRight();
                if(this.rootNode.getKey()==node.getKey()){
                    this.rootNode = child;
                }
                node.setRight(child.getLeft());
                child.setLeft(node);
                node.setParent(child);
                if(isRight){
                    parent.setRight(child);
                } else{
                    parent.setLeft(child);
                }
            }
        }
        if (bf == 2){
            if(getBF(node.getLeft()) == 1){

            } else {

            }
        }

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

        public String getText(){
            if (!isRealNode()) return "null";
            return (this.info) ? "true : "+Integer.toString(this.key) : "false : "+Integer.toString(this.key);}


        public AVLNode(int key,boolean info){
            this.key = key;
            this.info = info;
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


