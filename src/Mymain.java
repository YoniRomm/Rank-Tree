import java.util.Arrays;
import java.util.Random;

public class Mymain {

    public static void main(String [] args){
        AVLTree t1 = new AVLTree();
        AVLTree t2 = new AVLTree();
        AVLTree t3 = new AVLTree();

        t1.insert(1,true);
        t1.insert(2,true);
        t1.delete(1);
        AVLTree.print(t1.getRoot());






//        int j =0;
//        while(j<1000){
//            for(int i=0;i<=100;i++){
//                Random random = new Random();
//                boolean rand = random.nextBoolean();
//                t1.insert(i,rand);
//                if(i%5==0){
//                    t1.delete(i-1);
//                }
//            }
//            Random random = new Random();
//            int rand_num = random.nextInt(100);
//            while((rand_num+1) % 5 == 0){
//                rand_num = random.nextInt(100);
//            }
//            try{
//                if (t1.prefixXor(rand_num)!=t1.succPrefixXor(rand_num)){
//                    AVLTree.print(t1.getRoot());
//                    System.out.println("prefixXor = " + t1.prefixXor(rand_num) + " num " + rand_num);
//                    System.out.println("succPrefixXor = " + t1.succPrefixXor(rand_num) + " num " + rand_num);
//                    t1.prefixXor(rand_num);
//                    break;
//                }
//            }catch (Exception exception){
//                System.out.println(rand_num);
//                AVLTree.print(t1.getRoot());
//                break;
//            }
//
//            j++;
//
//        }
        System.out.println("finished");
//        AVLTree.print(t1.getRoot());







//        if(!emptyTester()){
//            System.out.println("empty problem");
//        }
//
//
//
//
//
//        t2.insert(10,false);
//        t2.insert(9,false);
//        t2.insert(8,false);
//
//
//
//
//
//        t1.insert(10,false);
//        t1.insert(15,true);
//        t1.insert(20,false);
//        t1.insert(26,false);
//        t1.insert(17,false);
//

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


    public static boolean emptyTester(){
        AVLTree t3 = new AVLTree();
        if(!t3.empty()){
            return false;
        }
        t3.insert(10,false);
        if(t3.empty()){
            return false;
        }
        t3.delete(10);
        return t3.empty();
    }


}
