public class Demo07 {
    public static void main(String[] args) {
        /*
        最小公倍数：
         */
        int num = 24;
        int num1 = 36;

        // num 永远是较大的数， num1 是较小的数
        if (num < num1){
            // 换位置
            num = num ^ num1;
            num1 = num ^ num1;
            num = num ^ num1;
        }

        // 方法一：暴力解法
        for (int i = 2; i <= num; i ++){
            int result = num1 * i;
            if (result % num == 0){
                System.out.println("最小公倍数：" + result);
                break;
            }
        }

        // 方法二：(a * b) / 最大公约数
        int a = num, b = num1;
        while(num1 != 0){
            int temp = num; // 大
            num = num1;
            num1 = temp % num1;
        }

        int result = (a * b) / num;
        System.out.println(result);

    }
}
