package com.bitdubai.fermat.dap_plugin.layer.digital_asset_transaction.asset_distribution.developer.bitdubai.version1.structure.asset_distribution_transaction_manager;

import com.bitdubai.fermat_api.layer.osa_android.database_system.PluginDatabaseSystem;
import com.bitdubai.fermat_api.layer.osa_android.file_system.PluginFileSystem;
import com.bitdubai.fermat_bch_api.layer.crypto_vault.asset_vault.interfaces.AssetVaultManager;
import com.bitdubai.fermat_dap_api.layer.all_definition.exceptions.CantSetObjectException;
import com.bitdubai.fermat_dap_plugin.layer.digital_asset_transaction.asset_distribution.developer.bitdubai.version_1.structure.AssetDistributionTransactionManager;
import com.bitdubai.fermat_dap_plugin.layer.digital_asset_transaction.asset_distribution.developer.bitdubai.version_1.structure.DigitalAssetDistributor;
import com.bitdubai.fermat_pip_api.layer.platform_service.error_manager.ErrorManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.UUID;

/**
 * Created by Luis Campo (campusprize@gmail.com) on 28/10/15.
 */

@RunWith(MockitoJUnitRunner.class)
public class SetPluginIdTest {

    private AssetDistributionTransactionManager mockAssetDistributionTransactionManager;

    @Mock
    private AssetVaultManager assetVaultManager;
    @Mock
    private DigitalAssetDistributor digitalAssetDistributor;
    @Mock
    private ErrorManager errorManager;
    @Mock
    private PluginDatabaseSystem pluginDatabaseSystem;
    @Mock
    private PluginFileSystem pluginFileSystem;

    @Before
    public void init() throws Exception {
        mockAssetDistributionTransactionManager = new AssetDistributionTransactionManager(assetVaultManager, errorManager, UUID.randomUUID(), pluginDatabaseSystem,
                pluginFileSystem);
    }

   /* @Test
    public void setPluginIdThrowsCantSetObjectExceptionTest() throws CantSetObjectException {
        System.out.println("Probando metodo setPluginIdThrowsCantSetObjectExceptionTest()");
        catchException(mockAssetDistributionTransactionManager).setPluginId(null);
        Exception thrown = caughtException();
        assertThat(thrown)
                .isNotNull()
                .isInstanceOf(CantSetObjectException.class);
    }*/

    @Test
    public void setPluginIdNoExceptionTest() throws CantSetObjectException{
        System.out.println("Probando metodo setPluginIdNoExceptionTest()");
        mockAssetDistributionTransactionManager.setPluginId(UUID.randomUUID());
    }
}
