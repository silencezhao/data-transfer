package com.datatransfer.ioutil.test;

import com.datatransfer.ioutil.AutoCloseableDemo;
import org.junit.Test;

public class IOUtilsTest {


    @Test
    public void closeableObj(){

        try(AutoCloseableDemo autoCloseableDemo = new AutoCloseableDemo()){

        }catch (Exception ex){

        }
    }
}
