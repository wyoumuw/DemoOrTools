package com.youmu.maven.test;

import com.youmu.maven.utils.NetUtils;
import com.youmu.maven.utils.model.NetResult;
import org.junit.Test;

/**
 * Created by youmu on 2017/6/1.
 */
public class NetTest {
    @Test
    public void test1()throws  Throwable{
        NetResult result=NetUtils.get("https://api.weixin.qq.com/cgi-bin/media/get/jssdk?access_token=cGQrlgC9DSe2nntlINKteKmvu4_fhhhpzmmBddyrruc8VPrWXRROtklHGMkDYG8AlvbZ2wJYm1b9x8_mOa4E48mXLu3yS0zxfO9EUnefuA9_o_E3YJbpGZ2Pdt3KmbMfCYXaAEAXTS&media_id=9GNqiWk8bj48xruh8PHC7YojYRO241eq5lRg26pWfFvt5IxzubIw00v-72AiqCRI",null);

    }
}
