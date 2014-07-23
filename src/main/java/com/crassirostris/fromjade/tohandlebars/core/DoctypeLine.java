package com.crassirostris.fromjade.tohandlebars.core;

import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * User: Coupang
 * Date: 14. 7. 7
 * Time: 오후 4:25
 * To change this template use File | Settings | File Templates.
 */
@NoArgsConstructor(staticName = "create")
public class DoctypeLine extends AbstractHandlebarsLine {
    @Override
    public String getHbsStartString() {
        return "<!DOCTYPE html>";
    }

    @Override
    public String getHbsEndString() {
        return "";
    }
}
