package com.crassirostris.fromjade.tohandlebars.core;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * User: Coupang
 * Date: 14. 7. 7
 * Time: 오후 4:21
 * To change this template use File | Settings | File Templates.
 */
@Data
@RequiredArgsConstructor(staticName = "create")
public class BlockLine extends AbstractHandlebarsLine{
    @NonNull private String name;

    @Override
    public String getHbsStartString() {
        StringBuffer sb = new StringBuffer("");
        if (getIndent() == 0) {
            return String.format("{{# partial \"%s\" }}", name);
        } else {
            return getSpace() + String.format("{{#block \"%s\"}}{{/block}}", name);
        }
    }

    @Override
    public String getHbsEndString() {
        StringBuffer sb = new StringBuffer("");
        if (getIndent() == 0) {
            return String.format("{{/partial}}");
        } else {
            return "";
        }
    }
}
