public class Demo04 {
    public static void main(String[] args) {
        /*
            两个数的最大公约数

            24 和 36   12
         */
        int num = 24;
        int num1 = 36;

        // 找到两个数之间较小的数
        int min = num > num1 ? num1 : num;
        // 一暴力解法：
        for (int i = min; i >= 1; i --){
            if (num % i == 0 && num1 % i == 0){
                System.out.printf("%d 和 %d 的最大公约数是：%d \n", num, num1, i);
                break;
            }
        }

        // 二辗转法
        // num 永远是较大的数， num1 是较小的数
        if (num < num1){
            // 换位置
            num = num ^ num1;
            num1 = num ^ num1;
            num = num ^ num1;
        }
         while(num1 != 0){
            int temp = num; // 大
            num = num1;
            num1 = temp % num1;
        }

        System.out.println(num);
//        do {
//            if (num % num1 == 0){
//                System.out.println("最大公约数是：" + num1);
//                break;
//            }
//            int temp = num; // 大
//            num = num1;
//            num1 = temp % num1;
//
//        } while(true);

    }
}
