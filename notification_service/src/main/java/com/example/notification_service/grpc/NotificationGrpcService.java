package com.example.notification_service.grpc;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import notification.notificationServiceGrpc.notificationServiceImplBase;

@GrpcService
public class NotificationGrpcService extends notificationServiceImplBase{
    @Override
    public void sendNotification(notification.notificationRequest notificationRequest , StreamObserver<notification.notificationResponse> notificationResponseStreamObserver){
        notification.notificationResponse notificationResponse = notification.notificationResponse.newBuilder()
                .setSuccess(true)
                .setResponse("The notification has sent to that particular user")
                .build();


        System.out.println(notificationRequest.getReceiverEmail());
        System.out.println(notificationRequest.getMessage());


        notificationResponseStreamObserver.onNext(notificationResponse);
        notificationResponseStreamObserver.onCompleted();
    }
}
