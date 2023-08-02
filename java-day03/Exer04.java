import java.util.Scanner;

public class Demo {

    public static void main(String[] args) {
        /*
            编写一个程序，根据输入的年份和月份来输出该月份的天数。要考虑闰年的情况。
         */

        Scanner in = new Scanner(System.in);

        System.out.println("请输入年份：");
        int year = in.nextInt();

        System.out.println("请输入月份：");
        int month = in.nextInt();

        // 取天数
        int days = switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 2 -> {
                // 判断是否闰年
                if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                    yield 28;
                } else {
                    yield 29;
                }
            }
            case 4, 6, 9, 11 -> 30;
            default -> 0;
        };


        System.out.println("天数是：" + days);

    }
}