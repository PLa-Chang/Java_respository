public class Demo03 {

    public static void main(String[] args) {
        /*
            分解质因数
            15 = 3 * 5
            20 = 2 * 2 * 5
            30 = 2 * 3 * 5
            60 = 2 * 2 * 3 * 5  ==>  2 * 30 ==> 2 * 2 * 15
            80 = 2 * 2 * 2 * 2 * 5

            假设我们分解 num 这个数：
            1. 先要遍历所有小于等于num的数 (for), 目的是找到质因数
            2. 在这些数中找到质数 (for)
            3. 判断这个质数是不是质因数，不是就找下一个质因数。是则输出质因数并改变 num 重新分解(1. 2. 3.), 直到 num 为 1 分解完成(while)

         */
        int num = 30;

        while (num > 1) {
            System.out.println("现在分解 " + num);
            // 进行分解质因数
            out:
            for (int i = 2; i <= num; i++) {
                // 判断 i 是否是质数
                for (int j = 2; j < i; j++) {
                    if (i % j == 0) {
                        // 不是质数, 去判断下一个数是否是质数
                        continue out;
                    }
                }
                // i 是质数
                // 判断 i 是否是 num 的因数
                if (num % i == 0) {
                    // 找到了一个质因数 i
                    System.out.println(i);

                    num /= i;

                    // 找改变后的 num 的质因数
                    break;
                }

                // i不是 num 质因数，进行下一次 for 循环(朝夕下一个质因数)
            }
        }


//        int num = 3;
//
//        while(num > 1){
//            // 列出小于等于 num 的所有质数
//            // 循环比 num 小的数
//            findNum:for (int i = 2; i <= num; i ++){
//                // 找这些数中的质数
//                for (int j = 1; j <= i >> 1; j ++) {
//                    if (i % j == 0 && j != 1){
//                        continue findNum;
//                    }
//                }
//                // 是质数
//                if (num % i == 0){ // 质因数
//                    System.out.println(i);
//                    num /= i;
//                    break;
//                }
//            }
//        }


    }
}