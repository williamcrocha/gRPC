package br.com.wcr.demogrpc.client;

import br.com.wcr.demogrpc.AddressRequest;
import br.com.wcr.demogrpc.PersonAddServiceGrpc;
import br.com.wcr.demogrpc.PersonRequest;
import br.com.wcr.demogrpc.PersonResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ClientMain {

    public static void main(String[] args) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost",8080)
                .usePlaintext()
                .build();

        PersonAddServiceGrpc.PersonAddServiceBlockingStub stub = PersonAddServiceGrpc.newBlockingStub(channel);

        PersonRequest request = PersonRequest.newBuilder()
                .setFirstName("William")
                .setLastName("Rocha")
                .addAddress(
                        AddressRequest.newBuilder()
                                .setAddress("Some Street 1")
                                .setCity("Any City 1")
                                .setZipCode(123456)
                                .build()
                )
                .addAddress(
                AddressRequest.newBuilder()
                        .setAddress("Some Street 2")
                        .setCity("Any City 2")
                        .setZipCode(654321)
                        .build()
                )
                .build();
        PersonResponse response = stub.add(request);
        log.info("Response: {}",response.getMessage());
        channel.shutdown();
        channel.awaitTermination(1,TimeUnit.SECONDS); // Wait for ack
        log.info("Finished");
    }
}
