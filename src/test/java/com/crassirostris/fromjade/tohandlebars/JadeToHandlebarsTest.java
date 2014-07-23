package com.crassirostris.fromjade.tohandlebars;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Coupang
 * Date: 14. 7. 7
 * Time: 오후 5:28
 * To change this template use File | Settings | File Templates.
 */
public class JadeToHandlebarsTest {
    @Test
    public void testPrecess() throws Exception {
        ConverterManager converterManager = ConverterManager.create();
        String filename[] = {"C:\\Users\\Coupang\\IdeaProjects\\display\\display-interfaces\\src\\main\\webapp\\WEB-INF\\views\\displaycomponent"
                ,
                "C:\\Users\\Coupang\\IdeaProjects\\display\\display-interfaces\\src\\main\\webapp\\WEB-INF\\views\\displaycomponent-attribute"
                ,
        "C:\\Users\\Coupang\\IdeaProjects\\display\\display-interfaces\\src\\main\\webapp\\WEB-INF\\views\\displaycomponent-category",
        "C:\\Users\\Coupang\\IdeaProjects\\display\\display-interfaces\\src\\main\\webapp\\WEB-INF\\views\\displayitemcategory"
        };

        for (String name : filename) {

            converterManager.run(name);
        }
    }
}
