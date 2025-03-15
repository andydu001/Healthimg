
/*

Andy D

 */
package org.example;

import software.amazon.awssdk.auth.credentials.AwsCredentials;

import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;

import software.amazon.awssdk.core.SdkPlugin;

import software.amazon.awssdk.core.SdkServiceClientConfiguration;

import software.amazon.awssdk.http.async.AsyncExecuteRequest;

import software.amazon.awssdk.http.async.SdkAsyncHttpClient;

import software.amazon.awssdk.regions.Region;

import software.amazon.awssdk.services.medicalimaging.MedicalImagingAsyncClient;

import software.amazon.awssdk.services.medicalimaging.model.*;

import java.util.concurrent.CompletableFuture;

import java.util.concurrent.ExecutionException;

import java.util.function.Consumer;

public class FFAbase3{

    FFAbase3() throws ExecutionException, InterruptedException {

        MedicalImagingAsyncClient meds = MedicalImagingAsyncClient.builder().addPlugin(new SdkPlugin() {
            @Override
            public void configureClient(SdkServiceClientConfiguration.Builder config) {


            }
        }).httpClient(new SdkAsyncHttpClient() {
            @Override
            public CompletableFuture<Void> execute(AsyncExecuteRequest request) {
                //implementation needed
                return null;
            }

            @Override
            public void close() {

            }
        }).region(Region.AWS_GLOBAL).credentialsProvider(new AwsCredentialsProvider() {
            @Override
            public AwsCredentials resolveCredentials() {
                return new MMBase2();
            }
        }).build();

        //*******************************************************************
        System.out.println(meds.serviceClientConfiguration().region().id());

        //Confirm the schemes
        System.out.println(meds.serviceClientConfiguration().authSchemes().values());

        // System.out.println(meds.serviceClientConfiguration().credentialsProvider().resolveIdentity());
        System.out.println(meds.serviceName());


        // Create a new datastore
        CompletableFuture<CreateDatastoreResponse>  completableFuture =  meds.createDatastore(new Consumer<CreateDatastoreRequest.Builder>() {
            @Override
            public void accept(CreateDatastoreRequest.Builder builder) {
                builder.clientToken("TZTS").datastoreName("IMAGE_FILE_001").build();

            }
        });

       System.out.println(completableFuture.get().datastoreId());

        meds.listDatastores(new Consumer<ListDatastoresRequest.Builder>() {
            @Override
            public void accept(ListDatastoresRequest.Builder builder) {
                System.out.println();
            }
        });


        meds.getDatastore(new Consumer<GetDatastoreRequest.Builder>() {
            @Override
            public void accept(GetDatastoreRequest.Builder builder) {
                System.out.println(builder.build());
            }
        });


        StartDicomImportJobRequest jobRequest = StartDicomImportJobRequest
                .builder().jobName("PRINT@").datastoreId("job").build();


        //jobRequest.toBuilder().datastoreId("ALL").jobName("PRINT@").inputS3Uri("A").build();
        meds.startDICOMImportJob(jobRequest);




        System.out.println(jobRequest.datastoreId());
        System.out.println(jobRequest.jobName());


        meds.getDICOMImportJob(new Consumer<GetDicomImportJobRequest.Builder>() {
            @Override
            public void accept(GetDicomImportJobRequest.Builder builder) {
                // System.out.println(builder.build().datastoreId());
            }
        });



        meds.searchImageSets(new Consumer<SearchImageSetsRequest.Builder>() {
            @Override
            public void accept(SearchImageSetsRequest.Builder builder) {
                builder.searchCriteria(SearchCriteria.builder().filters(new SearchFilter[20]).build()).build();
            }
        }) ;


        meds.searchImageSetsPaginator(SearchImageSetsRequest.builder().build());

    }

}
