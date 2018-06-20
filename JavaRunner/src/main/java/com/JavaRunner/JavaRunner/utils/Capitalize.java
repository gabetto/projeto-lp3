package com.JavaRunner.JavaRunner.utils;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Capitalize {

    private static final String ALL_SPECIAL_UPPER_CASE = "A-ZÇÁÀÃÂÉÈẼÊÌÍĨÎÕÓÒÔŨÚÙÜÛ";
    private static final String ALL_SPECIAL_LOWER_CASE = "a-zçáàãâéèẽêìíĩîõóòôũúùüû";
    private static final String REGEX_ALL_UPPER_CASE = "[" + ALL_SPECIAL_UPPER_CASE + "]";
    private static final String REGEX_ALL_LOWER_CASE = "[" + ALL_SPECIAL_LOWER_CASE + "]";

    /**
     * GetMatcher provide a compiled regex to Slug/Camel/Sneak Case. Inspired by http://30secondsofcode.org
     *
     * @param string
     * @return
     */
    private static Matcher getMatcher(String string) {
        return Pattern.compile(REGEX_ALL_UPPER_CASE + "{2,}(?=" + REGEX_ALL_UPPER_CASE + REGEX_ALL_LOWER_CASE +
                "+[0-9]*|\\b)|" + REGEX_ALL_UPPER_CASE + "?" + REGEX_ALL_LOWER_CASE
                + "+[0-9]*|" + REGEX_ALL_UPPER_CASE + "|[0-9]+").matcher(string);
    }

    /**
     * Capitalize every first character in words. Split by spaces(" "), tabulations("\t") and line breaker("\n")
     *
     * @param words
     * @return
     */
    public static String capitalizeByWords(String words) {
        if (words.length() == 0) return words;
        StringBuilder stringBuilder = new StringBuilder();
        StringTokenizer tokens = new StringTokenizer(words, " \t\n", true);
        for (; tokens.hasMoreTokens(); ) {
            String slice = tokens.nextToken();
            stringBuilder.append(slice.substring(0, 1)
                    .toUpperCase()
                    .concat(slice.substring(1).toLowerCase()));
        }
        return stringBuilder.toString().trim();
    }

    /**
     * The same thing of capitalizeByWords(), but this replace all connectives name's in Portuguese BR
     *
     * @param words
     * @return
     */
    public static String brazilianCapitalize(String words) {
        return capitalizeByWords(words).replaceAll(" E ", " e ")
                .replaceAll(" Da ", " da ").replaceAll(" Das ", " das ")
                .replaceAll(" De ", " de ").replaceAll(" Del ", " del ")
                .replaceAll(" Do ", " do ").replaceAll(" Dos ", " dos ");
    }

    /**
     * Convert any string to camelCase. Ideal to create method names ou class
     *
     * @param string
     * @return
     */
    public static String toCamelCase(String string) {
        StringBuilder stringBuilder = new StringBuilder();
        Matcher matcher = getMatcher(string);
        while (matcher.find())
            stringBuilder.append(matcher.group(0).substring(0, 1).toUpperCase()).append(matcher.group(0).substring(1).toLowerCase());
        return String.valueOf(stringBuilder).trim();
    }

    /**
     * Convert any string to camelCase. Ideal to rename files before to save on server
     *
     * @param string
     * @return
     */
    public static String toSneakCase(String string) {
        StringBuilder stringBuilder = new StringBuilder();
        Matcher matcher = getMatcher(string);
        while (matcher.find())
            stringBuilder.append(matcher.group(0).toLowerCase().concat("_"));
        return stringBuilder.toString().replaceAll("_$", "");
    }

    /**
     * Convert any string to camelCase. Ideal to create route names dynamically
     *
     * @param string
     * @return
     */
    public static String toSlugCase(String string) {
        StringBuilder n = new StringBuilder();
        Matcher matcher = getMatcher(string);
        while (matcher.find())
            n.append(matcher.group(0).toLowerCase().concat("-"));
        return n.toString().replaceAll("-$", "");
    }

    /**
     * Convert any CamelCase string to another case, with replace string
     *
     * @param string
     * @param replace
     * @return
     */
    public static String camelCaseToAnother(String string, String replace) {
        return string.replaceAll("([" + ALL_SPECIAL_LOWER_CASE + "\\d])" +
                "(" + REGEX_ALL_UPPER_CASE + ")", "$1" + replace + "$2").replaceAll
                ("(" + REGEX_ALL_UPPER_CASE + "+)" +
                        "(" + REGEX_ALL_UPPER_CASE + "[" +
                        ALL_SPECIAL_LOWER_CASE + "\\d]+)", "$1" + replace + "$2");
    }
}
