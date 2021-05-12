public class Mymain {

    public static void main(String [] args){
        AVLTree t1 = new AVLTree();
        AVLTree t2 = new AVLTree();

        t2.insert(10,false);
        t2.insert(9,false);
        t2.insert(8,false);



        t1.insert(18,false);
        t1.insert(20,true);
        t1.insert(19,false);
        t1.insert(16,false);
        t1.insert(17,false);
        t1.delete(20);
//        t1.insert(15,true);
//        t1.insert(14,true);
        AVLTree.print(t1.getRoot());
//        System.out.println(t1.search(21));


//        int [] arr = t2.keysToArray();
//        boolean [] arr2 = t2.infoToArray();

//        System.out.println(Arrays.toString(arr));
//        System.out.println(Arrays.toString(arr2));
//
//        AVLNode node_18 = t1.getRoot().getLeft().getRight();
//        AVLNode node_20 = t1.getRoot().getRight();
//
//        System.out.println(t1.successor(node_18).getKey());
//        System.out.println(t1.successor(node_20));

//        t1.insert(9,false);
//        t1.insert(7,false);




    }
}
