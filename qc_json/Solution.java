import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    List<String> input;
    JsonParser parser;
    Map<String, JsonObject> map;

    public Solution() {
        input = new ArrayList<>();
        parser = new JsonParser();
        map = new WeakHashMap();
    }

    public void add(String data) {
        input.add(data);
        map.put(data, parser.parse(data).getAsJsonObject());
    }

    public List<String> find(String data) {
        return input.stream().filter(r -> match(r, data)).collect(Collectors.toList());
    }

    public void delete(String data) {
        input = input.stream().filter(r -> !match(r, data)).collect(Collectors.toList());
    }

    private boolean match(String a, String b) {
        return match(map.get(a), parser.parse(b).getAsJsonObject());
    }

    private boolean match(JsonObject potential, JsonObject criteria) {
        Set<Map.Entry<String, JsonElement>> criteraFields = criteria.entrySet();
        Set<String> keys = new HashSet<>();
        for (Map.Entry<String, JsonElement> e : criteraFields) {
            String key = e.getKey();
            if (!potential.has(key)) {
                return false;
            }
            keys.add(key);
        }
        for (String key : keys) {
            if (!match(potential.get(key), criteria.get(key))) {
                return false;
            }
        }
        return true;
    }

    private boolean match(JsonElement potential, JsonElement criteria) {
        if (criteria.isJsonPrimitive()) {
            if (!potential.isJsonPrimitive() ||
                    !criteria.getAsJsonPrimitive().equals(potential.getAsJsonPrimitive())) {
                return false;
            }
        } else if (criteria.isJsonObject()) {
            if (!potential.isJsonObject() ||
                    !match(potential.getAsJsonObject(), criteria.getAsJsonObject())) {
                return false;
            }
        } else if (criteria.isJsonArray()) {
            if (!potential.isJsonArray()
                    || !match(potential.getAsJsonArray(), criteria.getAsJsonArray())) {
                return false;
            }
        } else if (criteria.isJsonNull()) {
            if (!potential.isJsonNull()) {
                return false;
            }
        }
        return true;
    }

    private boolean match(JsonArray arrayP, JsonArray arrayC) {
        /*
        for (int i = 0; i < arrayC.size(); i++) {
            boolean found = false;
            for (int j = 0; j < arrayP.size() && !found; j++) {
                found = match(arrayP.get(j), arrayC.get(i));
            }
            if (!found) {
                return false;
            }
        }
        */
        /*
        int[] c = new int[arrayC.size()];
        for (int i = 0; i < arrayC.size(); i++) {
            c[i]=arrayC.get(i).hashCode();
        }
        int[] p = new int[arrayP.size()];
        for (int i = 0; i < arrayP.size(); i++) {
            p[i]=arrayP.get(i).hashCode();
        }
        */
        Set<Integer> p = new HashSet<>();
        for (int i = 0; i < arrayP.size(); i++) {
            p.add(arrayP.get(i).hashCode());
        }
        for (int i = 0; i < arrayC.size(); i++) {
            if (!p.contains(arrayC.get(i).hashCode())) {
                return false;
            }
        }


        return true;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String line = in.nextLine();
            if (line == null || line.trim().equals("")) {
                System.exit(1);
            }
            if (line.trim().startsWith("add ")) {
                s.add(line.substring(4));
            } else if (line.trim().startsWith("get ")) {
                List<String> get = s.find(line.substring(4));
                get.stream().forEach(g -> System.out.println(g));
            } else if (line.trim().startsWith("delete ")) {
                s.delete(line.substring(7));
            } else {
                System.out.println("Unexpected input " + line);
                System.exit(1);
            }
        }


    }
}