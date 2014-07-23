package com.crassirostris.fromjade.tohandlebars.core;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * User: Coupang
 * Date: 14. 7. 8
 * Time: 오후 2:26
 * To change this template use File | Settings | File Templates.
 */
@Data
@RequiredArgsConstructor(staticName = "create")
public class ContextLine extends AbstractHandlebarsLine{
    @NonNull private String body;
    private String var;

    public void selectVariable(String jadeString) {
        switch (body) {
            case "each":
                var = jadeString.substring(jadeString.indexOf("in ") + 3, jadeString.length());
                break;
            case "if":
                var = jadeString.replaceFirst("if ", "");
                break;
            case "else":
                var = "";
                break;
        }
    }

    @Override
    public String getHbsStartString() {
        if ("else".equals(body)) {
            return "{{else}}";
        }
        return String.format("{{#%s %s}}", body, var);
    }

    @Override
    public String getHbsEndString() {
        if ("else".equals(body)) {
            return "";
        }
        return String.format("{{/%s}}", body);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isSkipEqualIndent() {
        return true;
    }
}
