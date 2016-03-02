package com.bitdubai.fermat_dap_android_sub_app_redeem_point_community_bitdubai.fragments;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bitdubai.fermat_android_api.layer.definition.wallet.AbstractFermatFragment;
import com.bitdubai.fermat_android_api.layer.definition.wallet.utils.ImagesUtils;
import com.bitdubai.fermat_android_api.layer.definition.wallet.views.FermatTextView;
import com.bitdubai.fermat_android_api.ui.interfaces.FermatWorkerCallBack;
import com.bitdubai.fermat_android_api.ui.util.FermatWorker;
import com.bitdubai.fermat_dap_android_sub_app_redeem_point_community_bitdubai.R;
import com.bitdubai.fermat_dap_android_sub_app_redeem_point_community_bitdubai.models.Actor;
import com.bitdubai.fermat_dap_android_sub_app_redeem_point_community_bitdubai.popup.AcceptDialog;
import com.bitdubai.fermat_dap_android_sub_app_redeem_point_community_bitdubai.popup.ConnectDialog;
import com.bitdubai.fermat_dap_android_sub_app_redeem_point_community_bitdubai.popup.DisconnectDialog;
import com.bitdubai.fermat_dap_android_sub_app_redeem_point_community_bitdubai.sessions.AssetRedeemPointCommunitySubAppSession;
import com.bitdubai.fermat_dap_api.layer.all_definition.DAPConstants;
import com.bitdubai.fermat_dap_api.layer.all_definition.enums.DAPConnectionState;
import com.bitdubai.fermat_dap_api.layer.all_definition.util.DAPStandardFormats;
import com.bitdubai.fermat_dap_api.layer.dap_actor.redeem_point.RedeemPointActorRecord;
import com.bitdubai.fermat_dap_api.layer.dap_actor.redeem_point.exceptions.CantAssetRedeemPointActorNotFoundException;
import com.bitdubai.fermat_dap_api.layer.dap_actor.redeem_point.exceptions.CantGetAssetRedeemPointActorsException;
import com.bitdubai.fermat_dap_api.layer.dap_actor.redeem_point.interfaces.ActorAssetRedeemPoint;
import com.bitdubai.fermat_dap_api.layer.dap_sub_app_module.redeem_point_community.interfaces.RedeemPointCommunitySubAppModuleManager;
import com.bitdubai.fermat_pip_api.layer.platform_service.error_manager.interfaces.ErrorManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Creado por Jinmy Bohorquez on 11/02/16.
 */
@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class RedeemPointCommunityConnectionOtherProfileFragment extends AbstractFermatFragment
        implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    public static final String REDEEM_POINT_SELECTED = "redeemPoint";
    private String TAG = "ConnectionOtherProfileFragment";
    private Resources res;
    private View rootView;
    private AssetRedeemPointCommunitySubAppSession assetUserCommunitySubAppSession;
    private ImageView userProfileAvatar;
    private FermatTextView redeemName;
    private FermatTextView redeemCryptoAddres;
    private FermatTextView redeemCryptoCurrency;
    private FermatTextView redeemBlockchainNetworkType;
    private FermatTextView redeemRegistrationDate;
    private FermatTextView redeemLastConnectionDate;
    private static RedeemPointCommunitySubAppModuleManager manager;
    private ErrorManager errorManager;

    private Actor actorRedeem;
    private List<Actor> actors;

    private Button connect;
    private Button disconnect;
    private int MAX = 1;
    private int OFFSET = 0;
    //private FermatTextView userStatus;
    private Button connectionRequestSend;
    private Button connectionRequestRejected;
    private Button accept;
    private DAPConnectionState connectionState;
    private android.support.v7.widget.Toolbar toolbar;

    /**
     * Create a new instance of this fragment
     *
     * @return InstalledFragment instance object
     */
    public static RedeemPointCommunityConnectionOtherProfileFragment newInstance() {
        return new RedeemPointCommunityConnectionOtherProfileFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        // setting up  module
        assetUserCommunitySubAppSession = ((AssetRedeemPointCommunitySubAppSession) appSession);
        actorRedeem = (Actor) appSession.getData(REDEEM_POINT_SELECTED);
        manager = assetUserCommunitySubAppSession.getModuleManager();
        errorManager = appSession.getErrorManager();

    }

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.dap_redeem_point_community_fragment_connections_other_profile, container, false);
        toolbar = getToolbar();
//        if (toolbar != null)
//            toolbar.setTitle(actorRedeem.getName());
        userProfileAvatar = (ImageView) rootView.findViewById(R.id.img_user_avatar);
        // userStatus = (FermatTextView) rootView.findViewById(R.id.userPhrase);
        redeemName = (FermatTextView) rootView.findViewById(R.id.username);
        //userEmail = (FermatTextView) rootView.findViewById(R.id.email);
        redeemCryptoAddres = (FermatTextView) rootView.findViewById(R.id.cryptoAddress);
        redeemCryptoCurrency = (FermatTextView) rootView.findViewById(R.id.cryptoCurrency);
        redeemBlockchainNetworkType = (FermatTextView) rootView.findViewById(R.id.blockchainNetworkType);
        //redeemRegistrationDate = (FermatTextView) rootView.findViewById(R.id.redeemRegistrationDate);
        redeemLastConnectionDate = (FermatTextView) rootView.findViewById(R.id.redeemLastConnectionDate);
        connectionRequestSend = (Button) rootView.findViewById(R.id.btn_connection_request_send);
        connectionRequestRejected = (Button) rootView.findViewById(R.id.btn_connection_request_reject);
        connect = (Button) rootView.findViewById(R.id.btn_conect);
        accept = (Button) rootView.findViewById(R.id.btn_connection_accept);
        disconnect = (Button) rootView.findViewById(R.id.btn_disconect);
        connectionRequestSend.setVisibility(View.GONE);
        connectionRequestRejected.setVisibility(View.GONE);
        connect.setVisibility(View.GONE);
        disconnect.setVisibility(View.GONE);
        connectionRequestRejected.setOnClickListener(this);
        connectionRequestSend.setOnClickListener(this);
        connect.setOnClickListener(this);
        disconnect.setOnClickListener(this);

        updateButton();

        try {
            redeemName.setText(actorRedeem.getName());
            //userStatus.setText(actorRedeem.getPhrase());
            //userStatus.setText(actorRedeem.getName());
            //userStatus.setTextColor(Color.parseColor("#292929"));

            if (actorRedeem.getCryptoAddress() != null) {
                redeemCryptoAddres.setText(actorRedeem.getCryptoAddress().getAddress());
                redeemCryptoCurrency.setText(actorRedeem.getCryptoAddress().getCryptoCurrency().getFriendlyName());
            } else {
                redeemCryptoAddres.setText("No");
                redeemCryptoCurrency.setText("None");
            }

            if (actorRedeem.getBlockchainNetworkType() != null) {
                redeemBlockchainNetworkType.setText(actorRedeem.getBlockchainNetworkType().getCode());
            } else {
                redeemBlockchainNetworkType.setText("None");
            }

            //redeemRegistrationDate.setText(DAPStandardFormats.DATE_FORMAT.format(new Date(actorRedeem.getRegistrationDate())));
            redeemLastConnectionDate.setText(DAPStandardFormats.DATE_FORMAT.format(new Date(actorRedeem.getLastConnectionDate())));

            if (actorRedeem.getProfileImage() != null) {
                Bitmap bitmap;
                if (actorRedeem.getProfileImage().length > 0) {
                    bitmap = BitmapFactory.decodeByteArray(actorRedeem.getProfileImage(), 0, actorRedeem.getProfileImage().length);
                } else {
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profile_image);
                }
                bitmap = Bitmap.createScaledBitmap(bitmap, 480, 480, true);
                userProfileAvatar.setImageDrawable(ImagesUtils.getRoundedBitmap(getResources(), bitmap));
            } else {
                Bitmap bitmap;
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profile_image);
                bitmap = Bitmap.createScaledBitmap(bitmap, 480, 480, true);
                userProfileAvatar.setImageDrawable(ImagesUtils.getRoundedBitmap(getResources(), bitmap));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(getActivity().getApplicationContext(), R.string.dap_other_profile_system_error_toast, Toast.LENGTH_SHORT).show();
        }
        return rootView;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_conect) {
            //CommonLogger.info(TAG, "User connection state " + actorRedeem.getConnectionState());
            ConnectDialog connectDialog;
//            try {
            connectDialog = new ConnectDialog(getActivity(),
                    (AssetRedeemPointCommunitySubAppSession) appSession,
                    null,
                    actorRedeem,
                    null);
//                        manager.getActiveAssetRedeemPointIdentity());
            connectDialog.setTitle("Connection Request");
            connectDialog.setDescription("Do you want to send ");
            connectDialog.setUsername(actorRedeem.getName());
            connectDialog.setSecondDescription("a connection request");
            connectDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    updateButton();
                }
            });
            connectDialog.show();
//            } catch (CantGetIdentityRedeemPointException e) {
//                e.printStackTrace();
//            }
        }
        if (i == R.id.btn_disconect) {
            //CommonLogger.info(TAG, "User connection state " + actorRedeem.getConnectionState());
            final DisconnectDialog disconnectDialog;
//            try {
            disconnectDialog = new DisconnectDialog(getActivity(),
                    (AssetRedeemPointCommunitySubAppSession) appSession,
                    null,
                    actorRedeem,
                    null);
//                        manager.getActiveAssetRedeemPointIdentity());
            disconnectDialog.setTitle("Disconnect");
            disconnectDialog.setDescription("Want to disconnect from");
            disconnectDialog.setUsername(actorRedeem.getName());
            disconnectDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    //connectRequest();
                    updateButton();
                }
            });
            disconnectDialog.show();
//            } catch (CantGetIdentityRedeemPointException e) {
//                e.printStackTrace();
//            }
        }
        if (i == R.id.btn_connection_accept) {
//            try {
            AcceptDialog notificationAcceptDialog = new AcceptDialog(getActivity(),
                    (AssetRedeemPointCommunitySubAppSession) appSession,
                    null,
                    actorRedeem,
                    null);
//                        manager.getActiveAssetRedeemPointIdentity());
            notificationAcceptDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    updateButton();
                }
            });
            notificationAcceptDialog.show();

//            } catch (CantGetIdentityRedeemPointException e) {
//                e.printStackTrace();
//            }
        }
        if (i == R.id.btn_connection_request_send) {
            //CommonLogger.info(TAG, "User connection state " + actorRedeem.getConnectionState());
            Toast.makeText(getActivity(), R.string.dap_other_profile_request_send_toast, Toast.LENGTH_SHORT).show();
        }
        if (i == R.id.btn_connection_request_reject) {
            // CommonLogger.info(TAG, "User connection state " + actorRedeem.getConnectionState());
            Toast.makeText(getActivity(), R.string.dap_other_profile_request_reject_toast, Toast.LENGTH_SHORT).show();
        }
    }

    private void updateButton() {
        try {
            connectionState = manager.getActorRedeemRegisteredDAPConnectionState(this.actorRedeem.getActorPublicKey());
        } catch (CantGetAssetRedeemPointActorsException e) {
            e.printStackTrace();
        }
        updateStateConnection(connectionState);
        onRefresh();
    }

    private void updateStateConnection(DAPConnectionState dapConnectionState) {

        switch (dapConnectionState) {
            case BLOCKED_LOCALLY:
            case BLOCKED_REMOTELY:
            case CANCELLED_LOCALLY:
            case CANCELLED_REMOTELY:
                connectionRejected();
                break;
            case CONNECTED_ONLINE:
            case CONNECTED_OFFLINE:
                disconnectRequest();
                break;
            case DISCONNECTED_LOCALLY:
            case DISCONNECTED_REMOTELY:
            case DENIED_LOCALLY:
            case DENIED_REMOTELY:
            case REGISTERED_ONLINE:
            case REGISTERED_OFFLINE:
                connectRequest();
                break;
            case PENDING_LOCALLY:
                connectionAccept();
                break;
            case PENDING_REMOTELY:
            case CONNECTING:
                connectionSend();
                break;
        }
    }

    private void connectionSend() {
        connectionRequestSend.setVisibility(View.VISIBLE);
        connect.setVisibility(View.GONE);
        disconnect.setVisibility(View.GONE);
        connectionRequestRejected.setVisibility(View.GONE);
    }

    private void connectionAccept() {
        connectionRequestSend.setVisibility(View.GONE);
        connect.setVisibility(View.GONE);
        disconnect.setVisibility(View.GONE);
        connectionRequestRejected.setVisibility(View.GONE);
        accept.setVisibility(View.VISIBLE);
    }

    private void connectRequest() {
        connectionRequestSend.setVisibility(View.GONE);
        connect.setVisibility(View.VISIBLE);
        disconnect.setVisibility(View.GONE);
        connectionRequestRejected.setVisibility(View.GONE);
    }

    private void disconnectRequest() {
        connectionRequestSend.setVisibility(View.GONE);
        connect.setVisibility(View.GONE);
        disconnect.setVisibility(View.VISIBLE);
        connectionRequestRejected.setVisibility(View.GONE);
    }

    private void connectionRejected() {
        connectionRequestSend.setVisibility(View.GONE);
        connect.setVisibility(View.GONE);
        disconnect.setVisibility(View.GONE);
        connectionRequestRejected.setVisibility(View.VISIBLE);
    }

    private Drawable getImgDrawable(byte[] customerImg) {
        if (customerImg != null && customerImg.length > 0)
            return ImagesUtils.getRoundedBitmap(res, customerImg);

        return ImagesUtils.getRoundedBitmap(res, R.drawable.profile_image);
    }

    /*private void setUpScreen(LayoutInflater layoutInflater) throws CantGetActiveLoginIdentityException {
    }*/

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }

    @Override
    public void onUpdateViewOnUIThread(String code) {
        switch (code) {
            case DAPConstants.DAP_UPDATE_VIEW_ANDROID:
                updateButton();
                break;
            default:
                super.onUpdateViewOnUIThread(code);
        }
    }

    private synchronized List<Actor> getProfileData() {
        List<RedeemPointActorRecord> tempActor = new ArrayList<>();
        ActorAssetRedeemPoint actorAssetRedeemPoint;
        actors = new ArrayList<>();

        try {
            actorAssetRedeemPoint = manager.getActorRedeemPoint(actorRedeem.getActorPublicKey());

            tempActor.add(new RedeemPointActorRecord(actorAssetRedeemPoint.getActorPublicKey(),
                    actorAssetRedeemPoint.getName(),
                    actorAssetRedeemPoint.getDapConnectionState(),
                    actorAssetRedeemPoint.getLocationLatitude(),
                    actorAssetRedeemPoint.getLocationLongitude(),
                    actorAssetRedeemPoint.getCryptoAddress(),
                    actorAssetRedeemPoint.getRegistrationDate(),
                    actorAssetRedeemPoint.getLastConnectionDate(),
                    actorAssetRedeemPoint.getType(),
                    actorAssetRedeemPoint.getBlockchainNetworkType(),
                    actorAssetRedeemPoint.getProfileImage()));

            if (tempActor.size() > 0) {
                for (RedeemPointActorRecord record : tempActor) {
                    actors.add((new Actor(record)));
                }
            }
        } catch (CantGetAssetRedeemPointActorsException e) {
            e.printStackTrace();
        } catch (CantAssetRedeemPointActorNotFoundException e) {
            e.printStackTrace();
        }
        return actors;
    }

    @Override
    public void onRefresh() {
        FermatWorker worker = new FermatWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                return getProfileData();
            }
        };
        worker.setContext(getActivity());
        worker.setCallBack(new FermatWorkerCallBack() {
            @SuppressWarnings("unchecked")
            @Override
            public void onPostExecute(Object... result) {
                actors = (ArrayList<Actor>) result[0];
                if (actors.get(0).getCryptoAddress() != null) {
                    redeemCryptoAddres.setText(actors.get(0).getCryptoAddress().getAddress());
                    redeemCryptoCurrency.setText(actors.get(0).getCryptoAddress().getCryptoCurrency().getFriendlyName());
                } else {
                    redeemCryptoAddres.setText("No");
                    redeemCryptoCurrency.setText("None");
                }
                if (actors.get(0).getBlockchainNetworkType() != null) {
                    redeemBlockchainNetworkType.setText(actors.get(0).getBlockchainNetworkType().toString().replace("_", " "));
                } else {
                    redeemBlockchainNetworkType.setText("None");
                }
            }

            @Override
            public void onErrorOccurred(Exception ex) {
                if (getActivity() != null)
                    Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_LONG).show();
                ex.printStackTrace();
            }
        });
        worker.execute();
    }
}
