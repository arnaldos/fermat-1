package unit.com.bitdubai.fermat_dmp_plugin.layer.basic_wallet.bitcoin_wallet.developer.bitdubai.version_1.structure.BitcoinWalletBasicWalletAvailableBalance;

import com.bitdubai.fermat_api.layer.dmp_basic_wallet.bitcoin_wallet.exceptions.CantRegisterCreditException;
import com.bitdubai.fermat_api.layer.dmp_basic_wallet.bitcoin_wallet.interfaces.BitcoinWalletTransactionRecord;
import com.bitdubai.fermat_api.layer.osa_android.database_system.Database;
import com.bitdubai.fermat_api.layer.osa_android.database_system.DatabaseTable;
import com.bitdubai.fermat_api.layer.osa_android.database_system.DatabaseTableRecord;
import com.bitdubai.fermat_api.layer.osa_android.database_system.DatabaseTransaction;
import com.bitdubai.fermat_api.layer.osa_android.database_system.PluginDatabaseSystem;
import com.bitdubai.fermat_api.layer.osa_android.database_system.exceptions.CantLoadTableToMemoryException;
import com.bitdubai.fermat_api.layer.osa_android.database_system.exceptions.CantOpenDatabaseException;
import com.bitdubai.fermat_api.layer.osa_android.database_system.exceptions.DatabaseNotFoundException;
import com.bitdubai.fermat_pip_api.layer.pip_platform_service.error_manager.ErrorManager;
import com.bitdubai.fermat_dmp_plugin.layer.basic_wallet.bitcoin_wallet.developer.bitdubai.version_1.structure.BitcoinWalletBasicWalletAvailableBalance;
import com.bitdubai.fermat_dmp_plugin.layer.basic_wallet.bitcoin_wallet.developer.bitdubai.version_1.structure.BitcoinWalletDatabaseConstants;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import unit.com.bitdubai.fermat_dmp_plugin.layer.basic_wallet.bitcoin_wallet.developer.bitdubai.version_1.structure.mocks.MockBitcoinWalletTransactionRecord;
import unit.com.bitdubai.fermat_dmp_plugin.layer.basic_wallet.bitcoin_wallet.developer.bitdubai.version_1.structure.mocks.MockDatabaseTableRecord;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.fest.assertions.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * Created by jorgegonzalez on 2015.07.14..
 */
@RunWith(MockitoJUnitRunner.class)
public class CreditTest {

    @Mock
    private Database mockDatabase;
    @Mock
    private DatabaseTable mockWalletTable;
    @Mock
    private DatabaseTable mockBalanceTable;
    @Mock
    private DatabaseTransaction mockTransaction;

    private List<DatabaseTableRecord> mockRecords;

    private DatabaseTableRecord mockBalanceRecord;

    private DatabaseTableRecord mockWalletRecord;

    private BitcoinWalletTransactionRecord mockTransactionRecord;

    private BitcoinWalletBasicWalletAvailableBalance testBalance;

    @Before
    public void setUpMocks(){
        mockTransactionRecord = new MockBitcoinWalletTransactionRecord();
        mockBalanceRecord = new MockDatabaseTableRecord();
        mockWalletRecord = new MockDatabaseTableRecord();
        mockRecords = new ArrayList<>();
        mockRecords.add(mockBalanceRecord);
        setUpMockitoRules();
    }

    public void setUpMockitoRules(){
        when(mockDatabase.getTable(BitcoinWalletDatabaseConstants.BITCOIN_WALLET_TABLE_NAME)).thenReturn(mockWalletTable);
        when(mockDatabase.getTable(BitcoinWalletDatabaseConstants.BITCOIN_WALLET_BALANCE_TABLE_NAME)).thenReturn(mockBalanceTable);
        when(mockBalanceTable.getRecords()).thenReturn(mockRecords);
        when(mockWalletTable.getEmptyRecord()).thenReturn(mockWalletRecord);
        when(mockDatabase.newTransaction()).thenReturn(mockTransaction);
    }

    @Before
    public void setUpAvailableBalance(){
        testBalance = new BitcoinWalletBasicWalletAvailableBalance(mockDatabase);
    }

    @Test
    public void Credit_SuccesfullyInvoked_ReturnsAvailableBalance() throws Exception{
        catchException(testBalance).credit(mockTransactionRecord);
        assertThat(caughtException()).isNull();
    }

    @Test
    public void Credit_OpenDatabaseCantOpenDatabase_ThrowsCantRegisterCreditException() throws Exception{
        doThrow(new CantOpenDatabaseException("MOCK", null, null, null)).when(mockDatabase).openDatabase();

        catchException(testBalance).credit(mockTransactionRecord);
        assertThat(caughtException())
                .isNotNull()
                .isInstanceOf(CantRegisterCreditException.class);
    }

    @Test
    public void Credit_OpenDatabaseDatabaseNotFound_ThrowsCantRegisterCreditException() throws Exception{
        doThrow(new DatabaseNotFoundException("MOCK", null, null, null)).when(mockDatabase).openDatabase();

        catchException(testBalance).credit(mockTransactionRecord);
        assertThat(caughtException())
                .isNotNull()
                .isInstanceOf(CantRegisterCreditException.class);
    }

    @Test
    public void Credit_DaoCantCalculateBalanceException_ThrowsCantRegisterCreditException() throws Exception{
        doThrow(new CantLoadTableToMemoryException("MOCK", null, null, null)).when(mockWalletTable).loadToMemory();

        catchException(testBalance).credit(mockTransactionRecord);
        assertThat(caughtException())
                .isNotNull()
                .isInstanceOf(CantRegisterCreditException.class);
    }

    @Test
    public void Credit_GeneralException_ThrowsCantRegisterCreditException() throws Exception{
        when(mockBalanceTable.getRecords()).thenReturn(null);

        catchException(testBalance).credit(mockTransactionRecord);
        assertThat(caughtException())
                .isNotNull()
                .isInstanceOf(CantRegisterCreditException.class);
    }

}
