public class Array {
    public static void main(String[] args) {
        int[] array = new int[]{1, 5, 6, 8, 9, 10};
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
        calculateSum(array);
    }
        public static int calculateSum(int[] array) {
            int sum = 0;
            for (int num:array){
                sum += num;
            }
            return sum;
        }
}
