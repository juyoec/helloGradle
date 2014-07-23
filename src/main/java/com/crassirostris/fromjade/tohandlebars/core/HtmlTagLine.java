package com.crassirostris.fromjade.tohandlebars.core;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Joiner;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import com.google.common.base.Preconditions;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

/**
 * Created with IntelliJ IDEA.
 * User: Coupang
 * Date: 14. 7. 7
 * Time: 오전 11:11
 * To change this template use File | Settings | File Templates.
 */
@Data
@RequiredArgsConstructor(staticName = "create")
public class HtmlTagLine extends AbstractHandlebarsLine {
    @NonNull private String name;
    private String body = "";
    private Multimap<String, String> attribute = ArrayListMultimap.create();


    public void generateAttribute(String trimedJadeString) {
        Preconditions.checkArgument(!"".equals(name));
        if (trimedJadeString.indexOf(name) == 0) {
            trimedJadeString = trimedJadeString.replaceFirst(name, "");
        }

        // body 추출
        if (hasBody(trimedJadeString)) {
            int spaceIndex = extractSpaceIndex(trimedJadeString);
            String extractString = trimedJadeString.substring(spaceIndex);
            body = extractString;
            trimedJadeString = trimedJadeString.replace(extractString, "").trim();
        }

        // property 추출
        if (trimedJadeString.indexOf("(") >= 0) {
            String extractString = MatcherHelper.extractWord(trimedJadeString, ".*(\\(.*\\)).*").replace("(", "").replace(")", "");
            Iterator<String> spliter = MatcherHelper.spliter(extractString, "=");
            String key = spliter.next();
            while ( spliter.hasNext()) {
                String spliterString = spliter.next();
                if (spliterString.indexOf(",")> 0) {
                    String value = spliterString;
                    if (spliterString.indexOf(":")<0 || spliterString.indexOf("javascript:;") >= 0) {
                        value = spliterString.substring(0, spliterString.indexOf(",")).trim().replaceAll("\"", "").replaceAll("'", "");
                    }
                    attribute.put(key, value);
                    key =spliterString.substring(spliterString.indexOf(",") +1).trim();
                } else {
                    attribute.put(key, spliterString.replaceAll("\"", "").replaceAll("'", ""));
                }
            }
            trimedJadeString = trimedJadeString.substring(0, trimedJadeString.indexOf("("));
        }

        if (MatcherHelper.commaStart(trimedJadeString)) {
            Iterator<String> commaIterator = MatcherHelper.spliterComma(trimedJadeString);
            while(commaIterator.hasNext()) {
                String next = commaIterator.next();
                if (next.indexOf("#") > 0) {        // a#abc
                    Iterator<String> sharpIterator  = MatcherHelper.spliterSharp(next);
                    attribute.put("class", sharpIterator.next());
                    attribute.put("id", sharpIterator.next());
                } else { // id 가 없을 때
                    attribute.put("class", next);
                }
            }
        } else if (MatcherHelper.sharpStart(trimedJadeString)) {
            String idString = trimedJadeString.replace("#", "");
            if (idString.indexOf(".") > 0) { // #id.class 의 케이스
                Iterator<String> commaIterator = MatcherHelper.spliterComma(idString);
                attribute.put("id", commaIterator.next());
                while(commaIterator.hasNext()) {
                    attribute.put("class", commaIterator.next());
                }

            } else {
                attribute.put("id", idString); // id만있을때
            }
        }
    }

    private int extractSpaceIndex(String trimedJadeString) {
        int blockIndex = trimedJadeString.indexOf(")");
        int spaceIndex = trimedJadeString.indexOf(" ");

        if (blockIndex == -1 && spaceIndex > 0) {
            return spaceIndex;
        }
        return blockIndex + 1;
    }

    private boolean hasBody(String trimedJadeString) {
        int blockIndex = trimedJadeString.indexOf(")");
        int spaceIndex = trimedJadeString.lastIndexOf(" ");
        if (blockIndex == -1 && spaceIndex > 0) {
            return true;
        }
        return blockIndex < spaceIndex;
    }

    private boolean isDataAttribute(String next) {
        return next.startsWith("data-");
    }

    private boolean isSingleProperty(String next) {
        return next.contains("disabled")||next.contains("readonly");
    }

    @Override
    public String getHbsStartString() {
        StringBuffer sb = new StringBuffer("");
        Set<String> keys = attribute.keySet();
        for (String key: keys) {
            Collection<String> strings = attribute.get(key);
            if ("class".equals(key)) {
                sb.append(String.format(" class=\"%s\"", Joiner.on(" ").join(strings)));
            } else {
                String formattingString = " %s=\"%s\"";
                if (strings.toString().contains("\"")) {
                    formattingString = " %s=\'%s\'";
                }
                sb.append(String.format(formattingString, key, strings.toString().replace("[", "").replace("]", "")));
            }
        }
        return getSpace() + String.format("<%s%s>%s", name, sb.toString(),  body);
    }

    @Override
    public String getHbsEndString() {
        return getSpace() + String.format("</%s>", name);
    }
}
