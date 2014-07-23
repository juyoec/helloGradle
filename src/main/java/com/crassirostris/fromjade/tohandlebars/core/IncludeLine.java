package com.crassirostris.fromjade.tohandlebars.core;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * User: Coupang
 * Date: 14. 7. 7
 * Time: 오후 4:14
 * To change this template use File | Settings | File Templates.
 */
@Data
@RequiredArgsConstructor(staticName = "create")
public class IncludeLine extends AbstractHandlebarsLine {
    @NonNull private String path;

    @Override
    public String getHbsStartString() {
        return getSpace() + String.format("{{> %s}}", path);
    }

    @Override
    public String getHbsEndString() {
        return "";
    }
}
