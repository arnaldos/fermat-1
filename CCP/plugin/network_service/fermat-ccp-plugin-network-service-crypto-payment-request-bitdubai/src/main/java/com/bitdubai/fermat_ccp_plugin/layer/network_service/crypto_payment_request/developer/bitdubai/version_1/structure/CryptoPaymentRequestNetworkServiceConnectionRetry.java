package com.bitdubai.fermat_ccp_plugin.layer.network_service.crypto_payment_request.developer.bitdubai.version_1.structure;

import com.bitdubai.fermat_api.layer.all_definition.components.interfaces.PlatformComponentProfile;

/**
 * The class <code>com.bitdubai.fermat_ccp_plugin.layer.network_service.crypto_payment_request.developer.bitdubai.version_1.structure.CryptoPaymentRequestNetworkServiceConnectionRetry</code>
 * haves the specific data to retry the connection for an specific platform component profile.
 * <p>
 * Created by Leon Acosta - (laion.cj91@gmail.com) on 13/10/2015.
 */
public class CryptoPaymentRequestNetworkServiceConnectionRetry {

    private static final long WAIT_TIME_DEFAULT = 100000;
    private static final long WAIT_TIME_GAP     =  10000;
    private static final long WAIT_TIME_MAX     = 500000;
    private static final long WAIT_TIME_MIN     =  30000;

    private final PlatformComponentProfile platformComponentProfile;
    private       long                     waitTime                ;
    private       int                      counter                 ;

    public CryptoPaymentRequestNetworkServiceConnectionRetry(final PlatformComponentProfile platformComponentProfile,
                                                             long waitTime) {

        this.platformComponentProfile = platformComponentProfile;
        this.waitTime                 = waitTime                ;
        this.counter                  = 0                       ;
    }

    public CryptoPaymentRequestNetworkServiceConnectionRetry(final PlatformComponentProfile platformComponentProfile) {

        this.platformComponentProfile = platformComponentProfile;
        this.waitTime                 = WAIT_TIME_DEFAULT       ;
        this.counter                  = 0                       ;
    }

    public PlatformComponentProfile getPlatformComponentProfile() {
        return platformComponentProfile;
    }

    public long getWaitTime() {
        return waitTime;
    }

    public int getCounter() {
        return counter;
    }

    public void plusTime() {
        if (this.waitTime > WAIT_TIME_MIN)
            this.waitTime = waitTime - WAIT_TIME_GAP;
    }

    public void minusTime() {
        if (this.waitTime < WAIT_TIME_MAX)
            this.waitTime = waitTime + WAIT_TIME_GAP;
    }

    public void plusCount() {
        counter++;
    }
}
