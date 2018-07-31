package com.suzanelsamahy.vidviewer;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.suzanelsamahy.vidviewer.base.BaseAppCompatActivity;
import com.suzanelsamahy.vidviewer.util.ConnectivityReciever;
import com.suzanelsamahy.vidviewer.util.SharedPreferencesManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EnterChannelIdActivity extends BaseAppCompatActivity implements ConnectivityReciever.ConnectivityReceiverListener {

    @BindView(R.id.enter_id_et)
    EditText idEditText;

    @BindView(R.id.next_btn)
    Button nextBtn;


    // UC-fT6wIb2N5-fZexR_eBRy
    // UCP4bf6IHJJQehibu6ai__cg 24

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_channel_id);
        ButterKnife.bind(this);
        idEditText.setText(R.string.channel_id_example);
    }


    @OnClick(R.id.next_btn)
    protected void onNextBtnClicked() {
        String id = idEditText.getText().toString();
        if(validateChannelId(id)){
            SharedPreferencesManager.getInstance(getApplicationContext()).saveStringPref(SharedPreferencesManager.CHANNEL_ID,id);
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra(getString(R.string.channel_intent_str),id);
            startActivity(intent);
            finish();
            checkConnection();
        }
    }


    private boolean validateChannelId(String id) {

        if(checkConnection()){
           return true;
        } else if(id.length()<24){
            showToastMessage(getString(R.string.enter_valid_id_str));
            return false;
        }
        return true;
    }





    private boolean checkConnection() {
        boolean isConnected = ConnectivityReciever.isConnected();
        showSnack(isConnected);
        return isConnected;
    }

    // Showing the status in Snackbar
    private void showSnack(boolean isConnected) {
        String message;
        int color;
        if (isConnected) {
            message = getString(R.string.connected_to_internet_str);
            color = Color.RED;
        } else {
            message = getString(R.string.not_connected_to_internet_str);
            color = Color.RED;
            Snackbar snackbar = Snackbar
                    .make(findViewById(R.id.next_btn), message, Snackbar.LENGTH_LONG);

            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(color);
            sbView.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
            snackbar.show();
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        // register connection status listener
        ChannelApplication.getInstance().setConnectivityListener(this);
    }

    /**
     * Callback will be triggered when there is change in
     * network connection
     */
    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);
    }

}
