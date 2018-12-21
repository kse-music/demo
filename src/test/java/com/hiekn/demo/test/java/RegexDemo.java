package com.hiekn.demo.test.java;

import com.hiekn.demo.test.TestBase;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式定义了一个字符串搜索模式。
 * 正则表达式可以用于搜索，编辑和处理文本。
 * 由正则表达式定义的模式可在任何匹配一次或多次，或不为给定的字符串。
 *
 *
 * 在Java中的正则表达式中的特殊字符   \.[{(*+?^$|,这些字符如果要正常输出需要用 \ 转义
 *
 * @author DingHao
 * @date 2018/12/20 11:26
 */
public class RegexDemo extends TestBase {

    public static final String TEXT = "This is my text";

    @Test
    public void stringRegex() {

        String s1 = "a";
        System.out.println("s1=" + s1);

        // Check the entire s1
        // Match any character
        // Rule .
        // ==> true
        boolean match = s1.matches(".");
        System.out.println("-Match . " + match);

        s1 = "abc";
        System.out.println("s1=" + s1);

        // Check the entire s1
        // Match any character
        // Rule .
        // ==> false (Because s1 has three characters)
        match = s1.matches(".");
        System.out.println("-Match . " + match);

        // Check the entire s1
        // Match with any character 0 or more times
        // Combine the rules . and *
        // ==> true
        match = s1.matches(".*");
        System.out.println("-Match .* " + match);

        String s2 = "m";
        System.out.println("s2=" + s2);

        // Check the entire s2
        // Start by m
        // Rule ^
        // ==> true
        match = s2.matches("^m");
        System.out.println("-Match ^m " + match);

        s2 = "mnp";
        System.out.println("s2=" + s2);

        // Check the entire s2
        // Start by m
        // Rule ^
        // ==> false (Because s2 has three characters)
        match = s2.matches("^m");
        System.out.println("-Match ^m " + match);

        // Start by m
        // Next any character, appearing one or more times.
        // Rule ^ and. and +
        // ==> true
        match = s2.matches("^m.+");
        System.out.println("-Match ^m.+ " + match);

        String s3 = "p";
        System.out.println("s3=" + s3);

        // Check s3 ending with p
        // Rule $
        // ==> true
        match = s3.matches("p$");
        System.out.println("-Match p$ " + match);

        s3 = "2nnp";
        System.out.println("s3=" + s3);

        // Check the entire s3
        // End of p
        // ==> false (Because s3 has 4 characters)
        match = s3.matches("p$");
        System.out.println("-Match p$ " + match);

        // Check out the entire s3
        // Any character appearing once.
        // Followed by n, appear one or up to three times.
        // End by p: p $
        // Combine the rules: . , {X, y}, $
        // ==> true

        match = s3.matches(".n{1,3}p$");
        System.out.println("-Match .n{1,3}p$ " + match);

        String s4 = "2ybcd";
        System.out.println("s4=" + s4);

        // Start by 2
        // Next x or y or z
        // Followed by any one or more times.
        // Combine the rules: [abc]. , +
        // ==> true
        match = s4.matches("2[xyz].+");

        System.out.println("-Match 2[xyz].+ " + match);

        String s5 = "2bkbv";

        // Start any one or more times
        // Followed by a or b, or c: [abc]
        // Next z or v: [zv]
        // Followed by any
        // ==> true
        match = s5.matches(".+[abc][zv].*");

        System.out.println("-Match .+[abc][zv].* " + match);

    }

    @Test
    public void splitRegex() {
        System.out.println("TEXT=" + TEXT);

        // White space appears one or more times.
        // The whitespace characters: \t \n \x0b \r \f
        // Combining rules: \ s and +
        String regex = "\\s+";
        String[] splitString = TEXT.split(regex);
        // 4
        System.out.println(splitString.length);

        for (String string : splitString) {
            System.out.println(string);
        }
        // Replace all whitespace with tabs
        String newText = TEXT.replaceAll("\\s+", "\t");
        System.out.println("New text=" + newText);
    }

    @Test
    public void eitherOrCheck() {
        String s = "The film Tom and Jerry!";

        // Check the whole s
        // Begin by any characters appear 0 or more times
        // Next Tom or Jerry
        // End with any characters appear 0 or more times
        // Combine the rules:., *, X | Z
        // ==> true
        boolean match = s.matches(".*(Tom|Jerry).*");
        System.out.println("s=" + s);
        System.out.println("-Match .*(Tom|Jerry).* " + match);

        s = "The cat";
        // ==> false
        match = s.matches(".*(Tom|Jerry).*");
        System.out.println("s=" + s);
        System.out.println("-Match .*(Tom|Jerry).* " + match);

        s = "The Tom cat";
        // ==> true
        match = s.matches(".*(Tom|Jerry).*");
        System.out.println("s=" + s);
        System.out.println("-Match .*(Tom|Jerry).* " + match);
    }

    @Test
    public void matcherFind(){

        final String TEXT = "This \t is a \t\t\t String";

        // Spaces appears one or more time.
        String regex = "\\s+";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(TEXT);

        int i = 0;
        while (matcher.find()) {
            System.out.print("start" + i + " = " + matcher.start());
            System.out.print(" end" + i + " = " + matcher.end());
            System.out.println(" group" + i + " = " + matcher.group());
            i++;
        }

    }

    @Test
    public void matcherLookingAt() {
        String country1 = "iran";
        String country2 = "Iraq";

        // Start by I followed by any character.
        // Following is the letter a or e.
        String regex = "^I.[ae]";

        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

        Matcher matcher = pattern.matcher(country1);

        // lookingAt () searches that match the first part.
        System.out.println("lookingAt = " + matcher.lookingAt());

        // matches() must be matching the entire
        System.out.println("matches = " + matcher.matches());

        // Reset matcher with new text: country2
        matcher.reset(country2);

        System.out.println("lookingAt = " + matcher.lookingAt());
        System.out.println("matches = " + matcher.matches());
    }

    @Test
    public void namedGroup(){

        final String TEXT = " int a = 100;float b= 130;float c= 110 ; ";

        // Use (?<groupName>pattern) to define a group named: groupName
        // Defined group named declare: using (?<declare>...)
        // And a group named value: use: (?<value>..)
        String regex = "(?<declare>\\s*(int|float)\\s+[a-z]\\s*)=(?<value>\\s*\\d+\\s*);";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(TEXT);

        while (matcher.find()) {
            String group = matcher.group();
            System.out.println(group);
            System.out.println("declare: " + matcher.group("declare"));
            System.out.println("value: " + matcher.group("value"));
            System.out.println("------------------------------");
        }
    }

    @Test
    public void namedGroup2(){

        String TEXT = "<a href='http://HOST/file/FILE1'>File 1</a>"
                + "<a href='http://HOST/file/FILE2'>File 2</a>";

        // Java >= 7.
        // Define group named fileName.
        // *? ==> ? after a quantifier makes it a reluctant quantifier.
        // It tries to find the smallest match.
        String regex = "/file/(?<fileName>.*?)'>";//*? 将找到的最小的匹配

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(TEXT);

        while (matcher.find()) {
            System.out.println(matcher.start());
            System.out.println(matcher.end());
            System.out.println(matcher.group());
            System.out.println("File Name = " + matcher.group("fileName"));
        }
    }

}
