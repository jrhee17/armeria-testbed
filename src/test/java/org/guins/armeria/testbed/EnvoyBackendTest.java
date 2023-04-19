package org.guins.armeria.testbed;

import com.google.rpc.Status;

import com.linecorp.armeria.common.SessionProtocol;
import com.linecorp.armeria.server.Server;
import com.linecorp.armeria.server.grpc.GrpcService;

import helloworld.GreeterGrpc.GreeterImplBase;
import helloworld.Helloworld.HelloReply;
import helloworld.Helloworld.HelloRequest;
import io.grpc.protobuf.StatusProto;
import io.grpc.stub.StreamObserver;

class EnvoyBackendTest {

    public static void main(String[] args) {
        final Server server = Server.builder()
                                    .port(8080, SessionProtocol.HTTP)
                                    .service(GrpcService.builder()
                                                        .addService(new CustomGreeter())
                                                        .build())
                                    .build();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            server.stop().join();
        }));
        server.start().join();
    }

    private static class CustomGreeter extends GreeterImplBase {
        @Override
        public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
            final Status status = Status.newBuilder().setMessage("sample message")
                                        .setCode(6)
                                        .build();
            responseObserver.onError(StatusProto.toStatusException(status));
        }
    }
}
