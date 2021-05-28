public class TimeTester {

    public static void main(String [] args){
        AVLTree t2 = new AVLTree();
        for(int i=0;i<100;i++){
            t2.insert(i,true);
        }

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
}
