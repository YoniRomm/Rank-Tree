import java.sql.Array;
import java.util.Random;

public class TimeTester {

    public static void fakeRun(){
        AVLTree t2 = new AVLTree();
        for(int i=0;i<10000;i++){
            t2.insert(i,true);
        }
    }

    public static void testTime1(){
        fakeRun();

        for(int i=1;i<=5;i++){
            long sum1=0;
            long sum2=0;
            for(int k=0;k<10;k++){
                AVLTree t1 = new AVLTree();
                for(int j=1;j<500*i;j++){
                    t1.insert(j,false);
                }
                long start = System.nanoTime();
                for(int j=1;j<100;j++){
                    t1.prefixXor(j);
                }
                long end = System.nanoTime();
                long average = (end-start)/(500*i);
                long start2 = System.nanoTime();
                for(int j=1;j<100;j++){
                    t1.succPrefixXor(j);
                }
                long end2 = System.nanoTime();
                long average2 = (end2-start2)/(500*i);
                sum1 += average;
                sum2 += average2;
            }
            System.out.println("average prefixXor with i = " + i + " " +  sum1/10);
            System.out.println("average succPrefixXor with i = " + i + " " +  sum2/10);


        }
    }

    public static void testTime2ArithmeticSequence(){
        fakeRun();

        for(int i=1;i<=5;i++) {
            BST bst1 = new BST();
            AVLTree t1 = new AVLTree();
            long start = System.nanoTime();
            for (int j = 1; j <= 1000 * i; j++) {
                bst1.insert(j,false);
            }
            long end = System.nanoTime();
            long average = (end-start)/(1000*i);
            System.out.println("average time with i = " + " " + i + " is " + average );
        }

    }

    public static void testTime2BalancedSequence(){
        fakeRun();
        for(int i=1;i<=5;i++) {
            BST bst1 = new BST();
            AVLTree t1 = new AVLTree();
            int k =1000;
            int [] arr = new int[k*i];
            for(int j=0;j<k*i;j++){
                arr[j] = j;
            }
            long start = System.nanoTime();
            recInsertToBST(arr,bst1,0,k*i-1);
            long end = System.nanoTime();
            long average = (end-start)/(1000*i);
            System.out.println("average time with i = " + " " + i + " is " + average );
        }
//        BST.print(bst1.getRoot());
//        AVLTree.print(t1.getRoot());

    }

    private static void recInsertToBST(int [] arr, AVLTree t1,int start,int end){
        if (start>end){
            return;
        }
        int middle = (int) Math.floor((start+end)/2);
        t1.insert(middle,false);
        recInsertToBST(arr,t1,start,middle-1);
        recInsertToBST(arr,t1,middle+1,end);
    }

    private static void recInsertToBST(int [] arr, BST t1,int start,int end){
        if (start>end){
            return;
        }
        int middle = (int) Math.floor((start+end)/2);
        t1.insert(middle,false);
        recInsertToBST(arr,t1,start,middle-1);
        recInsertToBST(arr,t1,middle+1,end);
    }

    public static void testTime2RandomSequence(){
        fakeRun();
        Random random = new Random();
        for(int i=1;i<=5;i++) {
            BST bst1 = new BST();
            AVLTree t1 = new AVLTree();
            long start = System.nanoTime();
            for (int j = 1; j <= 1000 * i; j++) {
                bst1.insert(random.nextInt(1000*i),false);
            }
            long end = System.nanoTime();
            long average = (end-start)/(1000*i);
            System.out.println("average time with i = " + " " + i + " is " + average );
        }

    }

    public static void main(String [] args){

        testTime2RandomSequence();

    }
}
