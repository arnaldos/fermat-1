package test.com.bitdubai.fermat_dmp_plugin.layer.middleware.wallet_contacts.developer.bitdubai.version_1.structure.WalletContactsMiddlewareDatabaseConstants;

import com.bitdubai.fermat_dmp_plugin.layer.middleware.wallet_contacts.developer.bitdubai.version_1.structure.WalletContactsMiddlewareDatabaseConstants;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Nerio on 24/07/15.
 */
public class GetWalletContactsMiddlewareDatabaseTest {

    WalletContactsMiddlewareDatabaseConstants contactsMiddlewareDatabaseConstants = new WalletContactsMiddlewareDatabaseConstants();

    @Test
    public void constructorTest (){
        Assert.assertNotEquals(contactsMiddlewareDatabaseConstants.CRYPTO_WALLET_CONTACTS_ADDRESS_BOOK_FIRST_KEY_COLUMN, WalletContactsMiddlewareDatabaseConstants.CRYPTO_WALLET_CONTACTS_ADDRESS_BOOK_TABLE_NAME);
        Assert.assertNotEquals(contactsMiddlewareDatabaseConstants.CRYPTO_WALLET_CONTACTS_ADDRESS_BOOK_TABLE_RECEIVED_ADDRESS_CRYPTO_CURRENCY_COLUMN_NAME, WalletContactsMiddlewareDatabaseConstants.CRYPTO_WALLET_CONTACTS_ADDRESS_BOOK_TABLE_CONTACT_ID_COLUMN_NAME);
        Assert.assertNotEquals(contactsMiddlewareDatabaseConstants.CRYPTO_WALLET_CONTACTS_ADDRESS_BOOK_TABLE_RECEIVED_CRYPTO_ADDRESS_COLUMN_NAME, WalletContactsMiddlewareDatabaseConstants.CRYPTO_WALLET_CONTACTS_ADDRESS_BOOK_TABLE_WALLET_ID_COLUMN_NAME);
        Assert.assertNotEquals(contactsMiddlewareDatabaseConstants.CRYPTO_WALLET_CONTACTS_ADDRESS_BOOK_TABLE_ACTOR_TYPE_COLUMN_NAME, WalletContactsMiddlewareDatabaseConstants.CRYPTO_WALLET_CONTACTS_ADDRESS_BOOK_TABLE_ACTOR_ID_COLUMN_NAME);
        Assert.assertEquals(contactsMiddlewareDatabaseConstants.CRYPTO_WALLET_CONTACTS_ADDRESS_BOOK_TABLE_ACTOR_NAME_COLUMN_NAME, WalletContactsMiddlewareDatabaseConstants.CRYPTO_WALLET_CONTACTS_ADDRESS_BOOK_TABLE_ACTOR_NAME_COLUMN_NAME);
        Assert.assertNotEquals(contactsMiddlewareDatabaseConstants.CRYPTO_WALLET_CONTACTS_ADDRESS_BOOK_TABLE_ACTOR_ID_COLUMN_NAME, WalletContactsMiddlewareDatabaseConstants.CRYPTO_WALLET_CONTACTS_ADDRESS_BOOK_TABLE_ACTOR_TYPE_COLUMN_NAME);
        Assert.assertNotEquals(contactsMiddlewareDatabaseConstants.CRYPTO_WALLET_CONTACTS_ADDRESS_BOOK_TABLE_WALLET_ID_COLUMN_NAME, WalletContactsMiddlewareDatabaseConstants.CRYPTO_WALLET_CONTACTS_ADDRESS_BOOK_TABLE_RECEIVED_CRYPTO_ADDRESS_COLUMN_NAME);
        Assert.assertNotEquals(contactsMiddlewareDatabaseConstants.CRYPTO_WALLET_CONTACTS_ADDRESS_BOOK_TABLE_CONTACT_ID_COLUMN_NAME, WalletContactsMiddlewareDatabaseConstants.CRYPTO_WALLET_CONTACTS_ADDRESS_BOOK_TABLE_RECEIVED_ADDRESS_CRYPTO_CURRENCY_COLUMN_NAME);
        Assert.assertNotEquals(contactsMiddlewareDatabaseConstants.CRYPTO_WALLET_CONTACTS_ADDRESS_BOOK_TABLE_NAME, WalletContactsMiddlewareDatabaseConstants.CRYPTO_WALLET_CONTACTS_ADDRESS_BOOK_FIRST_KEY_COLUMN);

/*        contactsMiddlewareDatabaseConstants = new WalletContactsMiddlewareDatabaseConstants();
        Assert.assertNotNull(contactsMiddlewareDatabaseConstants.toString());*/

    }
}