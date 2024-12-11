public class MyNewThread extends Thread{
        int[] array;
        String name;

        MyNewThread (String name,int array[]){
            this.name =name;
            this.array = array;
        }
        public void run(){
            for (int i:array){
                System.out.println("Thread name is "+this.getName()+" Square of i: "+Math.pow(i,2));
                try{
                    Thread.sleep(1000);
                }catch (Exception e){
                    System.out.println(e);
                }

            }
        }

}
