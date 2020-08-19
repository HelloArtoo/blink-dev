package com.gobue.blink.algo.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {

        HashMap<String, List> kv = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            int[] arr = new int[26];
            for(int j = 0; j < strs[i].length(); j++) {
                arr[strs[i].charAt(j) - 'a']++;
            }

            StringBuilder sb = new StringBuilder();
            for(int k = 0; k < arr.length; k++) {
                sb.append(arr[k]).append("#");
            }

            String key = sb.toString();
            if(kv.get(key) == null) {
                kv.put(key, new ArrayList<>());
            }

            kv.get(key).add(strs[i]);
        }
        return new ArrayList(kv.values());
    }

    public static void main(String[] args) {
        String[] strs = {"ray", "cod", "abe", "ned", "arc", "jar", "owl", "pop", "paw", "sky", "yup", "fed", "jul", "woo", "ado", "why", "ben", "mys", "den", "dem", "fat", "you", "eon", "sui", "oct", "asp", "ago", "lea", "sow", "hus", "fee", "yup", "eve", "red", "flo", "ids", "tic", "pup", "hag", "ito", "zoo"};
        List<List<String>> lists = new GroupAnagrams().groupAnagrams(strs);
        for (List<String> lst : lists) {
            System.out.println(lst);
        }
    }
}
