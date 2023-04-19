package org.guins.armeria.testbed;

import java.util.concurrent.CompletableFuture;

import javax.annotation.Nullable;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;

import com.linecorp.armeria.client.retrofit2.ArmeriaRetrofit;
import com.linecorp.armeria.common.HttpResponse;
import com.linecorp.armeria.common.HttpStatus;
import com.linecorp.armeria.common.MediaType;
import com.linecorp.armeria.server.ServerBuilder;
import com.linecorp.armeria.testing.junit5.server.ServerExtension;

import retrofit2.Converter;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.GET;

class RetrofitTest {

    @RegisterExtension
    static ServerExtension server = new ServerExtension() {
        @Override
        protected void configure(ServerBuilder sb) throws Exception {
            sb.service("/", (req, ctx) -> {
                return HttpResponse.of(HttpStatus.OK, MediaType.JSON_UTF_8,
                                       "{\"name\":\"Cony\", \"age\":26}");
            });
        }
    };

    public static class Pojo {

        @Nullable
        @JsonProperty("name")
        String name;
        @JsonProperty("age")
        int age;

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                              .add("name", name)
                              .add("age", age)
                              .toString();
        }
    }

    public interface SimpleService {

        @GET("/")
        CompletableFuture<Pojo> get();
    }

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Converter.Factory converterFactory = JacksonConverterFactory.create(objectMapper);

    @Test
    void testRetrofit() {
        final SimpleService simpleService = ArmeriaRetrofit.builder(server.httpUri())
                                                           .addConverterFactory(converterFactory)
                                                           .build().create(SimpleService.class);
        System.out.println(simpleService.get().join());
    }
}
