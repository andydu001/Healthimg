package org.example;

import software.amazon.awssdk.auth.credentials.AwsCredentials;

public class MMBase2 implements AwsCredentials {

    @Override
    public String accessKeyId() {
        return "adu001";
    }

    @Override
    public String secretAccessKey() {
        return "slekkekdkdmenii";
    }
}
