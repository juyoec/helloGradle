package com.crassirostris.example.guava;

import org.junit.Test;

import java.util.concurrent.ExecutionException;

/**
 * Created with IntelliJ IDEA.
 * User: Coupang
 * Date: 14. 7. 3
 * Time: 오후 3:02
 * To change this template use File | Settings | File Templates.
 */
public class HelloGuavaTest {
    @Test
    public void testCollectionGuava() throws Exception {
        HelloGuava target = new HelloGuava();
        target.collectionGuava();
    }

    @Test
    public void testObjectGuava() {
        HelloGuava target = new HelloGuava();
        target.objectGuava();

    }

    @Test
    public void testStringGuava() {
        HelloGuava target = new HelloGuava();
        target.stringGuava();
    }

    @Test
    public void testPreconditionGuava() {
        HelloGuava target = new HelloGuava();
        target.preconditionGuava();
    }

    @Test
    public void testOrderingGuava() {
        HelloGuava target = new HelloGuava();
        target.orderingGuava();
    }

    @Test
    public void testFileGuava() {
        HelloGuava target = new HelloGuava();
        target.fileGuava();
    }

    @Test
    public void testTableGuava() {
        HelloGuava target = new HelloGuava();
        target.tableGuava();


    }

    @Test
    public void testCacheGuava() throws ExecutionException {
        HelloGuava target = new HelloGuava();
        target.cacheGuava();
    }
}
