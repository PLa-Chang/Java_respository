import java.util.Scanner;

public class Demo02 {
    public static void main(String[] args) {
        /*
        编写一个程序，根据用户输入的温度单位("C"表示摄氏度，"F"表示华氏度)和温度，将其转换为另一种单位后输出。

        注: 摄氏度转换为华氏度的公式(摄氏度 * 9  / 5)+32，以及华氏度转换为摄氏度的公式(华氏度-32) *  5 / 9
         */
        Scanner in = new Scanner(System.in);

        System.out.println("请输入温度: ");
        double temperature = in.nextDouble();

        System.out.println("请输入单位 (c/f)：");
        String c = in.next();

        switch (c) {
            case "c", "C" -> {
                double result = temperature * 9 / 5 + 32;
                System.out.printf("华氏度温度为：%f F \n", result);
            }
            case "f", "F" -> {
                double result = (temperature - 32 ) / 9 * 5;
                System.out.printf("摄氏度温度为：%f C \n", result);
            }
            default -> System.out.println("输入错误");
        }
    }
}