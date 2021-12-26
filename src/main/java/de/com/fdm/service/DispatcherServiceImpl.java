package de.com.fdm.service;

import de.com.fdm.grpc.dispatcher.lib.DispatcherGrpc;
import de.com.fdm.grpc.dispatcher.lib.Empty;
import de.com.fdm.grpc.dispatcher.lib.OutboundMessage;
import de.com.fdm.tmi.SenderManager;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class DispatcherServiceImpl extends DispatcherGrpc.DispatcherImplBase {

    @Autowired
    private SenderManager senderManager;

    @Override
    public void send(OutboundMessage request, StreamObserver<Empty> responseObserver) {
        senderManager.send(request);

        Empty response = Empty.newBuilder().build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
