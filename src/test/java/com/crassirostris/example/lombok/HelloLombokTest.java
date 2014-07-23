package com.crassirostris.example.lombok;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Coupang
 * Date: 14. 7. 3
 * Time: 오후 2:13
 * To change this template use File | Settings | File Templates.
 */
public class HelloLombokTest {
    @Test
    public void testHello() throws Exception {
        HelloLombok target = new HelloLombok();
        target.hello();

    }
}
