package org.guins.armeria.testbed;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import com.linecorp.armeria.server.ServerBuilder;
import com.linecorp.armeria.server.annotation.Get;
import com.linecorp.armeria.server.docs.DocService;
import com.linecorp.armeria.testing.junit5.server.ServerExtension;

class DocServiceTest {

    @RegisterExtension
    static ServerExtension server = new ServerExtension() {
        @Override
        protected void configure(ServerBuilder sb) throws Exception {
            sb.annotatedService("/test", new Object() {
                @Get("/")
                public void get() {
                }
            });
            sb.serviceUnder("/docs", DocService.builder().build());
        }
    };


    @Test
    void testDocService() throws Exception {
        Thread.sleep(Long.MAX_VALUE);
    }
}
