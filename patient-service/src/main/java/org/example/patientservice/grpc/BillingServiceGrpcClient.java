package org.example.patientservice.grpc;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BillingServiceGrpcClient {
    private static final Logger log = LoggerFactory.getLogger(BillingServiceGrpcClient.class);
    private final BillingServiceGrpc.BillingServiceBlockingStub blockingStub;

 //localhost:9001/BillingService/CreatePatientAccount
 //aws.grpc:123123/BillingService/CreatePatientAccount
 public BillingServiceGrpcClient( @Value("${billing.service.address:localhost}") String serveraddress,
                                  @Value("${billing.service.grpc.port:9001}") int serverPort) {
   log.info("Connecting to grpc server on address {} and port {}", serveraddress, serverPort);
   ManagedChannel channel = ManagedChannelBuilder.forAddress(serveraddress, serverPort).usePlaintext().build();
   blockingStub  = BillingServiceGrpc.newBlockingStub(channel);

 }

 public BillingResponse createBillingAcount(String patientId,String name,String email){
     BillingRequest request = BillingRequest.newBuilder().setPatientId(patientId).setName(name).setEmail(email).build();
     BillingResponse response = blockingStub.createBillingAccount(request);
     log.info("Recieved response from billing service via grpc" + response.toString());
     return response;
 }
}
