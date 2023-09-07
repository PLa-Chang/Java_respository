
package com.map;
import java.util.*;
public class Exer02 {
    /**
     * 输入一个字符串，统计字符串中每个字符出现的次数并输出
     */
    public static void main(String[] args) {
        String s = "asadfasdasfs";
        Map<Character, Integer> map2 = new HashMap<>();
        // 遍历字符串
        for (int i = 0; i < s.length(); i++) {
            // 调用map集合的getOrDefault方法，判断集合中的key是否有当前的字符
            Integer count = map2.getOrDefault(s.charAt(i), 0);
            // 如果没有，则添加，如果有，则key对应的value + 1；
            map2.put(s.charAt(i),count + 1);
        }

        Set<Map.Entry<Character, Integer>> entries = map2.entrySet();
        Iterator<Map.Entry<Character, Integer>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Character, Integer> next = iterator.next();
            Character key = next.getKey();
            Integer value = next.getValue();
            System.out.println(key + " 出现了 "+ value + " 次 ");
        }
    }
}

二、为什么
public class Exer04 {
    public static void main(String[] args) {
        Map<Short, String> map = new HashMap<>();
        for (short i = 0; i < 100; i++) {
            map.put(i, String.valueOf(i));
            // 问题出现在这里：short类型的 - 1，得到int 类型的
            map.remove(i - 1);
        }
        System.out.println(map.size());     // 100
    }
}

三、
public class Exer01 {
    /**
     * 创建一个Map，完成以下操作：
     * - 将我国省份和其简称存到 Map 集合中
     * - 将省份名称中包含"江"的省份从集合中删除
     * - 遍历输出集合元素
     */
// 方法一：
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("北京", "京");
        map.put("上海", "沪");
        map.put("天津", "津");
        map.put("重庆", "渝");
        map.put("广东", "粤");
        map.put("浙江", "浙");

        Set<Map.Entry<String,String>> mapSet = map.entrySet();
        Iterator<Map.Entry<String, String>> mapIt = mapSet.iterator();
        while (mapIt.hasNext()) {
            Map.Entry<String, String> entry = mapIt.next();
            String key = entry.getKey();
            if (key.contains("江")){
                mapIt.remove();
            }
        }


        for (Map.Entry<String, String> maps : map.entrySet()) {
            System.out.println(maps.getKey()+" 的简称是："+maps.getValue());
        }
    }
// 方法二：
public static void main(String[] args) {
        String text = "asdasfdgfdgdsg, !";

        // 将字符串分割，返回一个字符串数组
        String[] strings = text.split("");
        // 将字符串数组转换成集合
        List<String> stringList = Arrays.asList(strings);
        // 因为这个List集合是不可变的，所以将其添加到ArrayList集合中：
        List<String> list = new ArrayList<>(stringList);
        HashSet<String> set = new HashSet<>(list);

        // 遍历set 集合
        for (String str : set) {
            // 统计每个字符出现的次数
            int count = 0;
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).equals(str)) {
                    count++;
                }
            }
            System.out.println(str + "出现了" + count + "次");
        }      
    }
// 方法三：
public static void main(String[] args) {
        String text = "asdasfdgfdgdsg, !";

        // 将字符串分割，返回一个字符串数组
        String[] strings = text.split("");

        // 如果不用集合，直接使用字符串操作就是下面的代码：
        while(text.length() > 0){
            // 取字符串第一个字符
            String c = text.substring(0, 1);
            // 第一个字符全部删掉
            String s = text.replaceAll(c, "");
            // 计算出现的次数
            int count = text.length() - s.length();
            System.out.println(c + " 出现了 " + count + " 次");
            text = s;
        }
    }
}