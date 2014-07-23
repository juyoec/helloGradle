package com.crassirostris.example.string;

import org.junit.Test;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Coupang
 * Date: 14. 7. 7
 * Time: 오전 11:57
 * To change this template use File | Settings | File Templates.
 */
public class RegaxStringTest {
    @Test
    public void testRegax() {
        String pattern = "[a-z.]";
        String target = "          title Coupang Content Management System";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(target);
        System.out.println(m.find());
        System.out.println(m.start());
    }

    @Test
    public void testRegax2() {
        String pattern = "^script[a-zA-Z()'=/]*";
        String target = "script(type='text/javascript')";

        System.out.println(Pattern.matches(pattern, target));

    }

    @Test
    public void testRegax3() {
        String pattern = ".*(\\(.*\\)).*";
        String target = "select.select-bundle-list(size=\"1\",name=\"bundles\")";

        System.out.println(Pattern.matches(pattern, target));
        Pattern compile = Pattern.compile(pattern);


        Matcher matcher = compile.matcher(target);
        if (!matcher.find()) {
            System.out.println("dont find");
            return;
        }
        String group = matcher.group(1);
        System.out.println(group);
    }

    @Test
    public void testRegax4() {
        String pattern = "([a-z\\-]+)=[\"\']{1}([a-zA-Z\\p{Punct}0-9]+)[\"']{1},*";

        String target = "tabindex=\"-1\",role=\"dialog\",aria-labelledby=\'myModalLabel\',aria-hidden=\"true\",data-info='{\"assetId\":{{assetId}},\"assetKey\":\"{{assetKey}}\",\"assetValue\":\"{{assetValue}}\"}'";

        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(target);
        while (matcher.find()) {
            System.out.println(" jfind");
            String string = matcher.group();
            System.out.println(string);
        }


    }

    @Test
    public void testRegax6() {
        String pattern = "[a-z\\-]+=[\"\']{1}[a-zA-Z\\p{Punct}0-9]+[\"\']{1},*";

        String target = "value='#{bundle.bundleId}', selected='selected', data-description='#{bundle.description}', data-available='#{bundle.available}'";

        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(target);
        while (matcher.find()) {
            System.out.println(" jfind");
            String string = matcher.group();
            System.out.println(string);
        }


    }

    @Test
    public void testRegax5() {
        String pattern = "^\\|\\p{Space}";

        String target = "| CMS(Contents Management System) Admin";

        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(target);
        System.out.println(Pattern.matches(pattern, target));
        System.out.println(matcher.find());
        System.out.println(matcher.group() + "\"");

        while (matcher.find()) {
            String string = matcher.group();
            System.out.println(target.replaceAll(pattern, "") + string.replaceAll(" ", ""));
        }
    }
}
