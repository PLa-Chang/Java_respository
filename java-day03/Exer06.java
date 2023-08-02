public void demo3() {
    /*
     * 编写一个程序，根据用户输入的月份和日期，输出该日期所在的星座。以下是一个简单的星座日期范围参考：
      * 水瓶座（1月20日到2月18日）
      * 双鱼座（2月19日到3月20日）
      * 白羊座（3月21日到4月19日）
      * 金牛座（4月20日到5月20日）
      * 双子座（5月21日到6月20日）
      * 巨蟹座（6月21日到7月22日）
      * 狮子座（7月23日到8月22日）
      * 处女座（8月23日到9月22日）
      * 天秤座（9月23日到10月22日）
      * 天蝎座（10月23日到11月21日）
      * 射手座（11月22日到12月21日）
      * 魔羯座（12月22日到1月19日）
     * */
        Scanner in = new Scanner(System.in);
        System.out.println("请输入月份");
        int month = in.nextInt();
        System.out.println("请输入日");
        int day = in.nextInt();
        String res = "";
        if (month == 1 && day >= 20 || month == 2 && day <= 18) {
            res = "水瓶座";
        }else if (month == 2 && day >= 19 || month == 3 && day <= 20) {
            res = "双鱼座";
        }else if (month == 3 && day >= 21 || month == 4 && day <= 19) {
            res = "白羊座";
        }else if (month == 4 && day >= 20 || month == 5 && day <= 20) {
            res = "金牛座";
        }else if (month == 5 && day >= 21 || month == 6 && day <= 20) {
            res = "双子座";
        }else if (month == 6 && day >= 21 || month == 7 && day <= 22) {
            res = "巨蟹座";
        }else if (month == 7 && day >= 23 || month == 8 && day <= 22) {
            res = "狮子座";
        }else if (month == 8 && day >= 23 || month == 9 && day <= 22) {
            res = "处女座";
        }else if (month == 9 && day >= 23 || month == 10 && day <= 22) {
            res = "天秤座";
        }else if (month == 10 && day >= 23 || month == 11 && day <= 21) {
            res = "天蝎座";
        }else if (month == 11 && day >= 22 || month == 12 && day <= 21) {
            res = "射手座";
        }else if (month == 12 && day >= 22 || month == 1 && day <= 19) {
            res = "摩羯座";
        }
        System.out.println(res);

}