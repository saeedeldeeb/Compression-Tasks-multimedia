/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Compression;

/**
 *
 * @author computer market
 */
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LZWDecoder {
     static Map<Integer, String> dictionary ;
    private String decompressData(List<Integer> compressed) {
        // Build the dictionary.
        int dictSize = 256;
       dictionary = new HashMap<>();
        for (int i = 0; i < 256; i++)
            dictionary.put(i, "" + (char) i);

        String w = "" + (char) (int) compressed.remove(0);
        String result = w;
        for (int k : compressed) {
            String entry;
            if (dictionary.containsKey(k))
                entry = dictionary.get(k);
            else if (k == dictSize)
                entry = w + w.charAt(0);
            else
                throw new IllegalArgumentException("Bad compressed k: " + k);

            result += ","+entry;

            // Add w+entry[0] to the dictionary.
            dictionary.put(dictSize++, w + entry.charAt(0));

            w = entry;
        }
        System.out.println(result);
        return result;
    }

    private byte[] getCharsAsBytes(String decompressed) {
        int length = decompressed.length();
        ByteBuffer buffer = ByteBuffer.allocate(length);
        for (int i = 0; i < length; i++) {
            buffer.put((byte) decompressed.codePointAt(i));
        }
        return buffer.array();
    }
    
    public static List<String> get(String let){
        String[] split = new LZWDecoder().decompress(let).split(",");
        
        return Arrays.asList(split);
    }

    public String decompress(String data) {
        try {
            String[] intsAsString = data.split(",");
            ArrayList<Integer> integers = new ArrayList<>();
            for (String anIntsAsString : intsAsString) {
                integers.add(Integer.parseInt(anIntsAsString));
            }

            String decompressed = decompressData(integers);
            return new String(getCharsAsBytes(decompressed), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
public static void main(String[] args)  {

new LZWDecoder().decompress("66,65,256,257");
    System.out.println(dictionary);
    }
}

    

