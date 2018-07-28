package com.suzanelsamahy.vidviewer.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;


/**
 * Created by yassermabrouk on 10/27/16.
 */

public class BaseFragment extends Fragment {


    private BaseViewBridge baseViewBridge;
    private static final int REQUEST_CALL_PHONE = 1245;

    protected void showProgressDialog() {
        baseViewBridge.showProgressDialog();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseViewBridge = new BaseViewBridge(getActivity());

    }

    protected void hideKeyboard(){
        baseViewBridge.hideKeyboard();
    }
    protected void hideProgressDialog() {
      baseViewBridge.hideProgressDialog();
    }

    protected void showToastMessage(String message){
        baseViewBridge.showToastMessage(message);
    }

    protected void showDialogMessage(String message){
//        baseViewBridge.showDialogMessage(message);
    }

    @Override
    public void onStop() {
        super.onStop();
        baseViewBridge.hideProgressDialog();
    }

    public boolean isConnectedToInternet(){
        return baseViewBridge.isConnectedToInternet();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }



    }


