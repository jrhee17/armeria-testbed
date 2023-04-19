package org.guins.armeria.testbed;

import org.junit.jupiter.api.Test;

import com.linecorp.armeria.common.Flags;

public class HelloWorld {

    @Test
    void testChannelUtil() {
        System.out.println(Flags.requestContextStorageProvider());
    }
}
