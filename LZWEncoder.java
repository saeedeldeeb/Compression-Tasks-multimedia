/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Compression;

import java.io.IOException;
import java.util.*;

public class LZWEncoder {

    private static double MAX_TABLE_SIZE; //Max Table size is based on the bit length input.

    /**
     * Compress a string to a list of output symbols and then pass it for
     * compress file creation.
     *
     * @param Bit_Length //Provided as user input.
     * @param input_string //Filename that is used for encoding.
     * @return
     * @throws IOException
     */
    static Map<String, Integer> TABLE;

    public static List<Integer> Encode_string(String input_string, double Bit_Length) throws IOException {

        MAX_TABLE_SIZE = Math.pow(2, Bit_Length);

        double table_Size = 256;

        TABLE = new HashMap<>();

        for (int i = 0; i < 255; i++) {
            TABLE.put("" + (char) i, i);
        }

        String initString = "";

        List<Integer> encoded_values = new ArrayList<>();

        for (char symbol : input_string.toCharArray()) {
            String Str_Symbol = initString + symbol;
            if (TABLE.containsKey(Str_Symbol)) {
                initString = Str_Symbol;
            } else {
                encoded_values.add(TABLE.get(initString));

                if (table_Size < MAX_TABLE_SIZE) {
                    TABLE.put(Str_Symbol, (int) table_Size++);
                }
                initString = "" + symbol;
            }
        }

        if (!initString.equals("")) {
            encoded_values.add(TABLE.get(initString));
        }

        for (int i = 0; i < encoded_values.size(); i++) {
            System.out.println(encoded_values.get(i) + "" + getKeysFromValue(TABLE, encoded_values.get(i)));
        }
        return encoded_values;
    }

    public static List<Object> getKeysFromValue(Map<?, ?> hm, Object value) {
        List<Object> list = new ArrayList<>();
        for (Object o : hm.keySet()) {
            if (hm.get(o).equals(value)) {
                list.add(o);
            }
        }
        return list;
    }

    public static List<Integer> neededArray() {
        List<Integer> ran = new ArrayList<>();
        for (int i = 256; i < 276; i++) {
            ran.add(i);

        }
        return ran;
    }

    public static void main(String[] args) throws IOException {

        Encode_string("BABAABAAA", 9);

    }
}
