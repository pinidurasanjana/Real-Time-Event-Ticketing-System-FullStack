public class MyThread extends Thread{
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());

        MyNewThread myNewThread = new MyNewThread("MyThread",new int[]{1,2,3});
        myNewThread.run();
        MyNewThread myNewThread1 = new MyNewThread("MyNewThread1", new int[]{4,5,6,7});
        myNewThread1.run();

        int[] anotherThread = {10,11,12};
        for (int i:anotherThread){
            System.out.println("Thread name is: "+Thread.currentThread().getName()+"Square of: "+Math.pow(i,2));
        }
    }


}
