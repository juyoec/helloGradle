package com.crassirostris.fromjade.tohandlebars.core;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * User: Coupang
 * Date: 14. 7. 7
 * Time: 오후 9:17
 * To change this template use File | Settings | File Templates.
 */
@Data
@RequiredArgsConstructor(staticName = "create")
public class ExtendsLine extends AbstractHandlebarsLine {
    @NonNull
    private String name;

    @Override
    public String getHbsStartString() {
        return "";
    }

    @Override
    public String getHbsEndString() {
        return getSpace() + String.format("{{> %s}}", name);
    }
}
