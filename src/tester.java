import java.util.Arrays;
import java.util.Random;
import java.util.function.IntPredicate;



public class tester {

    public static void main(String[] args) {
        int[] keys = {5, 10, 8, 3, 7};
        boolean[] vals = {true, true, false, false, true};
        AVLTree tree = new AVLTree();
        for (int i = 0; i < keys.length; i++) {
            tree.insert(keys[i], vals[i]);
        }
        AVLTree.print(tree.getRoot());

        if (tree.getRoot().getKey() != 8) {
            System.out.println("insert not right.1");
            if (tree.getRoot().getLeft().getKey() != 5) {
                System.out.println("insert not right.2");
            }
        }
        if (!tree.search(7)) {
            System.out.println("search not right.1");
        }
        if (tree.search(14) != null) {
            System.out.println("search not right.2");
        }
        if (tree.empty()) {
            System.out.println("empty not right");
        }
        int[] shouldbe = {3, 5, 7, 8, 10};
        int[] actual = tree.keysToArray();
        if (!Arrays.equals(actual, shouldbe)) {
            System.out.println("keystoArray not right");
        }
        boolean[] should = {false, true, true, false, true};
        boolean[] actualInfo = tree.infoToArray();
        if (!Arrays.equals(actualInfo, should)) {
            System.out.println("infotoArray not right");
        }
        if (tree.size() != 5) {
            System.out.println("size not right");
        }

        System.out.println("min "+tree.min());
        System.out.println("max "+tree.max());
        tree.delete(3);

        if (tree.search(3) != null) {
            System.out.println("delete.1");
        }
        AVLTree.print(tree.getRoot());
        System.out.println(tree.getRoot().getKey());
        tree.delete(10);
        System.out.println(tree.getRoot().getKey());
        if (tree.getRoot().getKey() != 7) {
            System.out.println("delete.2");
        }
        tree.insert(3, false);
        tree.insert(2, false);
        if (tree.getRoot().getLeft().getKey() != 3) {
            System.out.println("rotation not right");
        }
        if(tree.prefixXor(7)!=false){
            System.out.println("prefix problem");
        }
        if(tree.prefixXor(8)!=false) {
            System.out.println("prefix problem");
        }
        if(tree.prefixXor(2)!=false) {
            System.out.println("prefix problem");
        }
//        if(tree.succPrefixXor(7)!=false){
//            System.out.println("prefix problem");
//        }
//        if(tree.succPrefixXor(8)!=false) {
//            System.out.println("prefix problem");
//        }
//        if(tree.succPrefixXor(2)!=false) {
//            System.out.println("prefix problem");
//        }
//        System.out.println("min "+tree.min());
//        System.out.println("max "+tree.max());
//        System.out.println(tree.successor(tree.getRoot().getRight())==null);
//        System.out.println(tree.successor(tree.getRoot().getLeft()).getKey());
    }


    }


