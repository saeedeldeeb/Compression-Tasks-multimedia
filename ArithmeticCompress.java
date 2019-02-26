package Compression;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import static java.util.stream.Collectors.toMap;

public class ArithmeticCompress {

    public static HashMap<Character, Integer> Table = new HashMap<>();
    public static HashMap<String, String> Table2 = new HashMap<>();
  //  public static HashMap<String, String> Table3 = new HashMap<>();
    public static Map<Character, Integer> sorted;
    static int Total;
    static char[] charofmesg;

    public void setTableofstring(String message) {
        int counter;
        charofmesg = message.toCharArray();
        Total = charofmesg.length;
        for (int i = 0; i < charofmesg.length; i++) {
            counter = 1;
            for (int j = i + 1; j < charofmesg.length; j++) {
                if (charofmesg[i] == charofmesg[j]) {
                    counter++;

                }
            }
            if (!Table.containsKey(charofmesg[i])) {
                Table.put(charofmesg[i], counter);
            }
        }
        System.out.println("Done");
    }

    public void sortHash() {
        sorted = Table
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));
    }

    public void calculateProbability() {
        float Prob, start = 0, end, low, high, range;
        char ch;
        StringBuilder stb = new StringBuilder();
        String[] spliter;
        for (Map.Entry<Character, Integer> entry : sorted.entrySet()) {
            char key = entry.getKey();
            float val = entry.getValue();
            Prob = val / Total;
            System.out.println(key + "  " + val);
            end = start + Prob;
            Table2.put(key + "", start + "," + end);
            start = end;
        }
        for (int i = 0; i < Total; i++) {
            ch = charofmesg[i];
            stb.append(ch);
            System.out.println(stb);
            spliter = Table2.get(stb + "").split(",");
            low = Float.parseFloat(spliter[0]);
            high = Float.parseFloat(spliter[1]);
            range = high - low;
            for (Map.Entry<Character, Integer> entry : sorted.entrySet()) {
                char key = entry.getKey();
                float val = entry.getValue();
                Prob = val * range / Total;
                end = low + Prob;
                Table2.put(stb.toString()+key, low + "," + end);
                low = end;
            }
//Table2.clear();
//Table2 = Table3;
//Table3.clear();
        }
    }

    public static String start(String param) {
        new ArithmeticCompress().setTableofstring(param);
        new ArithmeticCompress().sortHash();
        new ArithmeticCompress().calculateProbability();
//        System.out.println(Table.get('c'));
//        System.out.println(Table2.get("c"));
//         System.out.println(Table2.get("aabcab"));
return Table2.get(param);
    }

}
