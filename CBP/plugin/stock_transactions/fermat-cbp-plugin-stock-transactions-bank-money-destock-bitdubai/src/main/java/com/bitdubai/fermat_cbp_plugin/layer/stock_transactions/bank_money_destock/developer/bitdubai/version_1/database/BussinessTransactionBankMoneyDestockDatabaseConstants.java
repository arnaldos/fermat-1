package com.bitdubai.fermat_cbp_plugin.layer.stock_transactions.bank_money_destock.developer.bitdubai.version_1.database;
/**
 * The Class <code>BussinessTransactionBankMoneyDestockDatabaseConstants
 * keeps constants the column names of the database.<p/>
 * <p/>
 *
 * Created by Franklin Marcano - (franklinmarcano970@gmail.com) on 16/11/15.
 *
 * @version 1.0
 * @since Java JDK 1.7
 */

public class BussinessTransactionBankMoneyDestockDatabaseConstants {
    /**
     * Bank Money Stock database table definition.
     */
    public static final String BANK_MONEY_DESTOCK_DATABASE_NAME                = "bank_money_destock_DB";
    static final String BANK_MONEY_DESTOCK_TABLE_NAME                          = "bank_money_destock";

    static final String BANK_MONEY_DESTOCK_TRANSACTION_ID_COLUMN_NAME        = "transaction_id";
    static final String BANK_MONEY_DESTOCK_PUBLIC_KEY_ACTOR_COLUMN_NAME      = "public_key_actor";
    static final String BANK_MONEY_DESTOCK_FIAT_CURRENCY_COLUMN_NAME         = "fiat_currency";
    static final String BANK_MONEY_DESTOCK_CBP_WALLET_PUBLIC_KEY_COLUMN_NAME = "cbp_wallet_public_key";
    static final String BANK_MONEY_DESTOCK_BNK_WALLET_PUBLIC_KEY_COLUMN_NAME = "bnk_wallet_public_key";
    static final String BANK_MONEY_DESTOCK_BANK_ACCOUNT_COLUMN_NAME          = "bank_account";
    static final String BANK_MONEY_DESTOCK_CONCEPT_COLUMN_NAME               = "concept";
    static final String BANK_MONEY_DESTOCK_MEMO_COLUMN_NAME                  = "memo";
    static final String BANK_MONEY_DESTOCK_AMOUNT_COLUMN_NAME                = "amount";
    static final String BANK_MONEY_DESTOCK_TIMESTAMP_COLUMN_NAME             = "timestamp";
    static final String BANK_MONEY_DESTOCK_TRANSACTION_STATUS_COLUMN_NAME    = "transaction_status";

    static final String BANK_MONEY_DESTOCK_FIRST_KEY_COLUMN                    = "transaction_id";
}
