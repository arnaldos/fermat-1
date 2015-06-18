package com.bitdubai.fermat_cry_plugin.layer.crypto_router.incoming_crypto.developer.bitdubai.version_1.util;

import com.bitdubai.fermat_api.layer.all_definition.money.CryptoAddress;
import com.bitdubai.fermat_api.layer.all_definition.transaction_transference_protocol.Specialist;
import com.bitdubai.fermat_api.layer.all_definition.transaction_transference_protocol.crypto_transactions.CryptoTransaction;
import com.bitdubai.fermat_cry_api.layer.crypto_module.actor_address_book.ActorAddressBookManager;
import com.bitdubai.fermat_cry_api.layer.crypto_module.actor_address_book.ActorAddressBook;
import com.bitdubai.fermat_cry_api.layer.crypto_module.actor_address_book.DealsWithActorAddressBook;
import com.bitdubai.fermat_cry_api.layer.crypto_module.actor_address_book.exceptions.CantGetActorAddressBook;
import com.bitdubai.fermat_cry_plugin.layer.crypto_router.incoming_crypto.developer.bitdubai.version_1.exceptions.CantSelectSpecialistException;

/**
 * Created by eze on 12/06/15.
 */

/*
 * The purpose of this class is to indicate the correct
 * destination for a given transaction
 */
public class SpecialistSelector implements DealsWithActorAddressBook {

    /*
     * DealsWithActorAddressBook Interface member variables
     */
    private ActorAddressBookManager actorAddressBook;

    /*
     * DealsWithActorAddressBook Interface method implementation
     */
    @Override
    public void setUserAddressBookManager(ActorAddressBookManager actorAddressBook) {
        this.actorAddressBook = actorAddressBook;
    }

    public Specialist getSpecialist(CryptoTransaction cryptoTransaction) throws CantSelectSpecialistException {

        CryptoAddress cryptoAddress = new CryptoAddress();
        cryptoAddress.setAddress(cryptoTransaction.getAddressFrom().getAddress());
        cryptoAddress.setCryptoCurrency(cryptoTransaction.getCryptoCurrency());

        ActorAddressBook user = null;
        try {
            user = this.actorAddressBook.getActorAddressBookByCryptoAddress(cryptoAddress);
        } catch (CantGetActorAddressBook cantGetActorCryptoAddress) {
            cantGetActorCryptoAddress.printStackTrace();
        }

        if (user != null) {
            switch (user.getActorType()) {
                case DEVICE_USER:
                    return Specialist.DEVICE_USER_SPECIALIST;
                case INTRA_USER:
                    return Specialist.INTRA_USER_SPECIALIST;
                case EXTRA_USER:
                    return Specialist.EXTRA_USER_SPECIALIST;
            }
        }
        // Here we have a serious problem
        throw new CantSelectSpecialistException();

    }
}
