package com.crassirostris.fromjade.tohandlebars.core;

import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * User: Coupang
 * Date: 14. 7. 7
 * Time: 오후 4:11
 * To change this template use File | Settings | File Templates.
 */
@NoArgsConstructor(staticName = "create")
public class ScriptLine extends AbstractHandlebarsLine {
    private final String SCRIPT_START = "<script type=\"text/javascript\">";
    private final String SCRIPT_END = "</script>";

    @Override
    public String getHbsStartString() {
        return getSpace() + SCRIPT_START;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getHbsEndString() {
        return getSpace() + SCRIPT_END;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
