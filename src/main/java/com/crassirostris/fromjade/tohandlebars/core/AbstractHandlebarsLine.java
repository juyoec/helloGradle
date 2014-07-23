package com.crassirostris.fromjade.tohandlebars.core;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Coupang
 * Date: 14. 7. 7
 * Time: 오전 11:34
 * To change this template use File | Settings | File Templates.
 */
@Data
public abstract class AbstractHandlebarsLine {
    private int indent = 0;
    public abstract String getHbsStartString();
    public abstract String getHbsEndString();
    public boolean isNeedStack(){
        return !"".equals(getHbsEndString());
    }
    public String getSpace() {
        List<String> indentList = Lists.newArrayList();
        for (int i = 0; i < indent; i++) {
            indentList.add(" ");
        }
        return Joiner.on("").join(indentList);
    }
    public boolean isSkipEqualIndent() {
        return false;
    }
}
