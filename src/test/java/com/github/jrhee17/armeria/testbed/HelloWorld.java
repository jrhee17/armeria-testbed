package com.github.jrhee17.armeria.testbed;

import org.junit.jupiter.api.Test;

import com.linecorp.armeria.common.Flags;

public class HelloWorld {

    @Test
    void testChannelUtil() {
        System.out.println(Flags.requestContextStorageProvider());
    }
}
