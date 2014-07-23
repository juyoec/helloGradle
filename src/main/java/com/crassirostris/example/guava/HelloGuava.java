package com.crassirostris.example.guava;

import com.crassirostris.example.guava.model.Fruit;

import com.google.common.base.*;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.*;
import com.google.common.io.Files;
import com.sun.istack.internal.Nullable;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static com.google.common.base.Preconditions.*;

/**
 * Created with IntelliJ IDEA.
 * User: Coupang
 * Date: 14. 7. 3
 * Time: 오후 2:59
 * To change this template use File | Settings | File Templates.
 */

/**
 * Base Function
 * (Objectutils, ClassUtils, ArrayUtils, BooleanUtils
 * 문재열 조작
 * (StringUtils, StringEscapeUtils, RandomStringUtils, Tokenizer, WordUtils
 * Character 핸들링
 * (CharSetUtils, CharSet, CharRange, CharUtils
 * JVM Interaction
 * (SystemUtils, CharEncoding
 * Serialization
 * (SerializationUtils, SerializationException
 */
public class HelloGuava {
    public void collectionGuava() {
        Map<String, String> lookup = Maps.newHashMap();
        lookup.put("apple", "red");
        lookup.put("apple", "red");
        lookup.put("apple", "red");

        System.out.println(lookup);
        // {apple=red}

        List<String> fruitList = Lists.newArrayList("apple", "melon", "cherry", null, "water melon");
        System.out.println(fruitList);
        //[apple, melon, cherry, null, water melon]

        //Collections2.filter(fruitList, Collections.notNull());

        Multimap<String, String> multimap = ArrayListMultimap.create();
        System.out.println(multimap.size());
        //0

        multimap.put("Java", "바나나");
        multimap.put("Java", "사과");
        multimap.put("Java", "수박");
        multimap.put("Google", "오이");
        multimap.put("Google", "당근");
        System.out.println(multimap.size());
        // 5

        Collection<String> java = multimap.get("Java");
        System.out.println(java);
        //  [바나나, 사과, 수박]

        multimap.remove("Java", "오이");
        System.out.println(java);
        // [바나나, 사과, 수박]
        System.out.println(multimap);
        // {Google=[오이, 당근], Java=[바나나, 사과, 수박]}

        multimap.remove("Java", "사과");
        System.out.println(java);
        // [바나나, 수박]

        System.out.println(multimap);
        // {Google=[오이, 당근], Java=[바나나, 수박]}

        multimap.removeAll("Java");
        System.out.println(multimap);
        //{Google=[오이, 당근]}
        System.out.println(multimap.size());
        // 2


        HashBiMap<String, String> languageCode = HashBiMap.create();
        languageCode.put("en", "English");
        languageCode.put("fr", "French");
        languageCode.put("zh", "Chinese");
        System.out.println(languageCode.get("en"));
        // English
        System.out.println(languageCode.inverse().get("French"));
        // fr

        Predicate<String> isTwoWord = new Predicate<String>() {
            @Override
            public boolean apply(@Nullable java.lang.String input) {
                return input.length() <= 2;  //To change body of implemented methods use File | Settings | File Templates.
            }
        };
        ArrayList<String> strings = Lists.newArrayList("바나나", "사과", "수박", null);
        Collection<String> filterStrings = Collections2
                .filter(strings, Predicates.notNull());
        System.out.println(Collections2.filter(filterStrings, isTwoWord));
    }

    public void objectGuava() {
        Fruit fruit = Fruit.create("apple", "red");
        System.out.println(fruit.toString());
        // Fruit{apple, red}

    }

    public void stringGuava() {
        System.out.println(Strings.nullToEmpty("            java    "));
        // java

        System.out.println(Strings.nullToEmpty(""));
        // ""

        System.out.println(Strings.nullToEmpty(null));
        // ""

        System.out.println(Strings.emptyToNull("java"));
        // java

        System.out.println(Strings.emptyToNull(""));
        // null

        System.out.println(Strings.emptyToNull(null));
        // null

        String text = " foo,  , bar,  quux";

        System.out.println(Splitter.on(",").trimResults().omitEmptyStrings().split(text));
    }

    public void preconditionGuava() {
        checkArgument(true, "this false argument");
        // pass
        //Preconditions.checkArgument(false, "this false argument");
        // throw IllegalArgumentException

        String name = checkNotNull("melon");
        System.out.println(name);
        // melon
        name = checkNotNull("");
        System.out.println(name);
        // ""
        /*name = checkNotNull(null);
        System.out.println(name);*/
        // throw NullPointerException
    }

    public void orderingGuava() {
        List<Integer> nums = Lists.newArrayList(3,4,2,1,6,null, 9);

        Collections.sort(nums, Ordering.natural().nullsFirst());
        System.out.println(nums);
        // [null, 1, 2, 3, 4, 6, 9]

        Collections.sort(nums, Ordering.natural().nullsLast());
        System.out.println(nums);
        // [1, 2, 3, 4, 6, 9, null]

        Collections.sort(nums, Ordering.natural().reverse().nullsLast());
        System.out.println(nums);
        // [9, 6, 4, 3, 2, 1, null]

        System.out.println(Joiner.on(",").skipNulls().join(nums));


    }

    public void fileGuava() {
        String filename = "src/main/resource/text.txt";

        final File file = new File(filename);
        System.out.println(file.getAbsoluteFile());
        try {
            final List<String> lines = Files.readLines(file, Charsets.UTF_8);
            for (final String line: lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println(e.toString());
        }

        String filename2 = "src/main/resource/text2.txt";
        final File touchFile = new File(filename2);

        try {
            Files.touch(touchFile);
            touchFile.delete();
            final List<String> lines = Files.readLines(file, Charsets.UTF_8);
            Files.write(Joiner.on("\n").join(lines).getBytes(), touchFile);

        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    public void tableGuava() {
        Table<Integer, String, String> table = HashBasedTable.create();
        // one row
        table.put(1, "a", "1a");
        table.put(1, "a", "1a");
        table.put(1, "b", "1b");
        table.put(1, "b", "1b");

        // two row
        table.put(2, "a", "2a");
        table.put(2, "b", "2b");

        // three row
        table.put(3, "a", "3a");
        table.put(3, "b", "3b");
        System.out.println(table);

        Map<String, String> onerow = table.row(1);
        System.out.println(onerow);

        Map<Integer, String> bColumn = table.column("b");
        System.out.println(bColumn);

    }

    @NoArgsConstructor(staticName = "create")
    private static class Axis {

        Table<String, String, Integer> table = HashBasedTable.create();
        public Integer read(String i, String j) {
            Integer integer = table.get(i, j);
            try {
                int afterI = checkNotNull(integer);
                table.put(i,j,++afterI);
                integer = afterI;

            } catch (Exception e) {
                integer = 1;
                table.put(i,j, integer);
            }

            return integer;
        }
    }

    public void cacheGuava() throws ExecutionException {
        final Axis axis = Axis.create();
        LoadingCache<String,Integer> build = CacheBuilder.newBuilder().expireAfterAccess(1, TimeUnit.MINUTES)
                .maximumSize(9)
                .build(new CacheLoader<String, Integer>() {
                    @Override
                    public Integer load(String key) throws Exception {
                        checkArgument(key.length() >= 2);
                        String i = key.substring(0, 1);
                        String j = key.substring(1);
                        Integer read = axis.read(i, j);
                        System.out.println(key + " cache load");
                        return read;  //To change body of implemented methods use File | Settings | File Templates.
                    }
                });

        String key = "a1";
        String stringFormat = "key : %s value : %d";
        System.out.println(String.format(stringFormat, key , build.get(key)));

        for (int i =1; i< 10; i++) {
            key = "a" + i;
            System.out.println(String.format(stringFormat, key , build.get(key)));
        }
        for (int i =1; i< 10; i++) {
            key = "a" + i;
            System.out.println(String.format(stringFormat, key , build.get(key)));
        }
        for (int i =10 ; i< 15; i++) {
            key = "a" + i;
            System.out.println(String.format(stringFormat, key , build.get(key)));
        }
        for (int i =1; i< 15; i++) {
            key = "a" + i;
            System.out.println(String.format(stringFormat, key , build.get(key)));
        }
        for (int i =1; i< 15; i++) {
            key = "a" + i;
            System.out.println(String.format(stringFormat, key , build.get(key)));
        }


        /*try {
            Integer error = build.get("1");
            // Throw IllegalArgumentException
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/
        /*
        a1 cache load
key : a1 value : 1
key : a1 value : 1
a2 cache load
key : a2 value : 1
a3 cache load
key : a3 value : 1
a4 cache load
key : a4 value : 1
a5 cache load
key : a5 value : 1
a6 cache load
key : a6 value : 1
a7 cache load
key : a7 value : 1
a8 cache load
key : a8 value : 1
a9 cache load
key : a9 value : 1
key : a1 value : 1
key : a2 value : 1
key : a3 value : 1
key : a4 value : 1
key : a5 value : 1
key : a6 value : 1
key : a7 value : 1
key : a8 value : 1
key : a9 value : 1
a10 cache load
key : a10 value : 1
a11 cache load
key : a11 value : 1
a12 cache load
key : a12 value : 1
a13 cache load
key : a13 value : 1
a14 cache load
key : a14 value : 1
a1 cache load
key : a1 value : 2
a2 cache load
key : a2 value : 2
a3 cache load
key : a3 value : 2
a4 cache load
key : a4 value : 2
a5 cache load
key : a5 value : 2
a6 cache load
key : a6 value : 2
a7 cache load
key : a7 value : 2
a8 cache load
key : a8 value : 2
a9 cache load
key : a9 value : 2
a10 cache load
key : a10 value : 2
a11 cache load
key : a11 value : 2
a12 cache load
key : a12 value : 2
a13 cache load
key : a13 value : 2
a14 cache load
key : a14 value : 2
a1 cache load
key : a1 value : 3
a2 cache load
key : a2 value : 3
a3 cache load
key : a3 value : 3
a4 cache load
key : a4 value : 3
a5 cache load
key : a5 value : 3
a6 cache load
key : a6 value : 3
a7 cache load
key : a7 value : 3
a8 cache load
key : a8 value : 3
a9 cache load
key : a9 value : 3
a10 cache load
key : a10 value : 3
a11 cache load
key : a11 value : 3
a12 cache load
key : a12 value : 3
a13 cache load
key : a13 value : 3
a14 cache load
key : a14 value : 3
         */
    }

}
