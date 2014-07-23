package com.crassirostris.fromjade.tohandlebars.core;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * User: Coupang
 * Date: 14. 7. 7
 * Time: 오후 4:23
 * To change this template use File | Settings | File Templates.
 */
@Data
@RequiredArgsConstructor(staticName = "create")
public class PipeLine extends AbstractHandlebarsLine{
    @NonNull private String body;

    @Override
    public String getHbsStartString() {
        return getSpace() + body;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getHbsEndString() {
        return "";  //To change body of implemented methods use File | Settings | File Templates.
    }
}
