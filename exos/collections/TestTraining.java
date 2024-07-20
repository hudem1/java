
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class TestTraining {
  public static void main(String[] args) {
    System.out.println("début du main de TestTraining");
    collections_test();
  }

  public static void sliceStrings() {
    String mystr = new String("blabla");
  }

  public static void collections_test() {
    // arraylists
    System.out.println("************* ArrayLists *************");
    List<String> list = new ArrayList<String>() {{
      add("first");
      add("second");
      add("third");
    }};
    int size = list.size();
    list.set(1, "new second");
    int third_index = list.indexOf("third");
    boolean contained = list.contains("blabla");
    list.add("hihi");
    list.remove("haha");
    // list.clear();
    // list.isEmpty();

    for (int i = 0; i < list.size(); i++) {
      //
    }

    for (String item: list) {
      //
    }

    list.forEach(item -> {
      System.out.println(item);
    });

    int[] intArray = {1, 2, 3, 4};
    // intArray[4] = 2; // gives error (array is of fixed size)
    int len = intArray.length;
    String[] stringArray = {"apple", "banana", "cherry"};

    // sets
    System.out.println("************* Sets *************");
    Set<String> set = new HashSet<>() {{
      add("Apple");
      add("Banana");
      add("Cherry");
    }};

    // works also to initialize set at creation
    // Set<Integer> test2 = new HashSet<>(Set.of(1, 2, 3, 4));

    set.size();
    set.contains("mystring");
    set.add("ahah");
    // set.clear();
    // set.isEmpty();
    set.remove("string to remove");
    set.forEach(item -> System.out.println(item));
    System.out.println(set.toArray()[0]);

    System.out.println("----- avant iterator ----");
    Iterator<String> iterator = set.iterator();

    // Iterate over the Set using an iterator
    while (iterator.hasNext()) {
        String name = iterator.next();
        System.out.println(name);
    }

    System.out.println("************* Maps *************");
    Map<Integer, String> map1 = new HashMap<Integer, String>() {{
      put(0, "zero");
      put(1, "un");
      put(2, "deux");
    }};

    Map<Integer, String> map2 = Map.ofEntries(
      Map.entry(0, "lala"),
      Map.entry(1, "lolo")
    );

    Map<Integer, String> map3 = Map.of(
      0, "hihi",
      1, "hoho"
    );

    map1.get(0);
    map1.size();
    Set<Integer> keySet = map1.keySet();
    Collection<String> collectionOfStrings = map1.values();
    Set<Entry<Integer, String>> entrySet = map1.entrySet();
    map1.containsKey(1);
    map1.containsValue("my val");
    map1.isEmpty();
    // map1.clear();
    map1.remove(3);
    map1.remove(1, "et ben non, mauvaise string");

    Iterator<Entry<Integer, String>> it = map1.entrySet().iterator();

    System.out.println("************* ? *************");

    LinkedList<String> linkedList = new LinkedList<>();
  }
}