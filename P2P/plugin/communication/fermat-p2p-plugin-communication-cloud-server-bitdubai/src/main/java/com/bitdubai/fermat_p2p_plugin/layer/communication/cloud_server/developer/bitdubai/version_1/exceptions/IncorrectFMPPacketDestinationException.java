package com.bitdubai.fermat_p2p_plugin.layer.communication.cloud_server.developer.bitdubai.version_1.exceptions;

import com.bitdubai.fermat_p2p_api.layer.p2p_communication.fmp.FMPException;

@SuppressWarnings("serial")
public class IncorrectFMPPacketDestinationException extends FMPException {
	
	private static final String EXCEPTION_PREFIX = "Incorrect FMPPacket Destination";
	
	public IncorrectFMPPacketDestinationException(){
		super(EXCEPTION_PREFIX);	
	}
	
	public IncorrectFMPPacketDestinationException(final String message){
		super(EXCEPTION_PREFIX + ": " + message);
	}

}
