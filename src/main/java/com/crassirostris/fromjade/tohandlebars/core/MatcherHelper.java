package com.crassirostris.fromjade.tohandlebars.core;

import com.google.common.base.Splitter;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatcherHelper {
    public MatcherHelper() {
    }

    public static boolean extendsStart(String string) {
        return patternMatcher(string, "^extends");
    }

    public static boolean blockStart(String string) {
        return patternMatcher(string, "^block");
    }

    public static boolean doctypeStart(String string) {
        return patternMatcher(string, "^doctype");
    }
    public static boolean plusStart(String string) {
        return patternMatcher(string, "^\\+");
    }

    public static boolean pipeStart(String string) {
        return patternMatcher(string, "^(\\| )");
    }

    public static boolean includeStart(String string) {
        return patternMatcher(string, "^include");
    }

    public static boolean scriptStart(String string) {
        return patternMatcher(string, "^script[a-zA-Z()'=/]*");
    }

    public static boolean commaStart(String string) {
        return patternMatcher(string, "^\\..*");
    }

    public static boolean sharpStart(String string) {
        return patternMatcher(string, "^#[a-z]+");
    }

    public static int firstSpellingIndex(String word) {
        String pattern = "[a-z.#\\|]";
        return firstIndex(word, pattern);
    }

    public static int firstPrefixIndex(String word) {
        return firstIndex(word, "[.#(\\p{Space}]");
    }

    public static int firstIndex(String text, String regax) {
        Pattern p = Pattern.compile(regax);
        Matcher m = p.matcher(text);
        return m.find() ? m.start() : 0;
    }

    public static String extractWord(String text, String regax) {
        Pattern p = Pattern.compile(regax);
        Matcher m = p.matcher(text);
        return m.find() ? m.group(1) : new String("");
    }

    public static Iterator<String> spliter(String text, String delim) {
        Iterable<String> split = Splitter.on(delim).trimResults().omitEmptyStrings().split(text);
        return split.iterator();
    }

    public static Iterator<String> spliterComma(String text) {
        return spliter(text, ".");
    }

    public static Iterator<String> spliterSharp(String text) {
        return spliter(text, "#");
    }

    public static boolean patternMatcher(String text, String regax) {
        Pattern compile = Pattern.compile(regax);
        Matcher matcher = compile.matcher(text);
        return matcher.find();
    }

    public static Matcher createMatcher(String string, String regax) {
        Pattern compile = Pattern.compile(regax);
        return compile.matcher(string);
    }

    public static boolean contextStart(String string) {
        return patternMatcher(string, "^each\\p{Space}|if\\p{Space}|else\\p{Space}*");
    }

    public static String firstWord(String jadeString) {
        int firstSpelling = MatcherHelper.firstPrefixIndex(jadeString);
        String context = jadeString.substring(0, firstSpelling);
        if (firstSpelling == 0) {
            context = jadeString;
        }
        return context;
    }
}