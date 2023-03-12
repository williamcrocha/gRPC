package br.com.wcr.demogrpc.server;

import br.com.wcr.demogrpc.PersonAddServiceGrpc;
import br.com.wcr.demogrpc.PersonRequest;
import br.com.wcr.demogrpc.PersonResponse;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PersonAddServiceImpl extends PersonAddServiceGrpc.PersonAddServiceImplBase {

    @Override
    public void add(PersonRequest request, StreamObserver<PersonResponse> responseObserver){
        log.info("Request received:\n{}",request);
        responseObserver.onNext(
                PersonResponse.newBuilder().setMessage("Success!").build()
        );
        responseObserver.onCompleted();
    }
}
