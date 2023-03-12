package br.com.wcr.demogrpc.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class ServerMain {

    public static void main(String[] args) throws IOException, InterruptedException {
        log.info("Server is starting...");
        Server server = ServerBuilder
                .forPort(8080)
                .addService(new PersonAddServiceImpl())
                .build();
        server.start();
        log.info("Server is running.");
        server.awaitTermination();
    }
}
