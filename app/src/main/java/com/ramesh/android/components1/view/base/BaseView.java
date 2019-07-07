package com.ramesh.android.components1.view.base;

import androidx.annotation.StringRes;

import com.ramesh.android.components1.model.data.local.model.Notes;
import com.ramesh.android.components1.view.callback.UserNotes;


public interface BaseView {

    void showLoading();

    void hideLoading();

    void showKeyBoard();

    void onError(@StringRes int resId);

    void onError(String message);

    void showMessage(String message);

    void showMessage(@StringRes int resId);

    void fullScreen();

    void showDialog(String userAction,Notes note, UserNotes userNotesCallback);

    void hideKeyboard();
//
//    void showKeyboard();
//
//    void handleApiError(ANError error);
//
//    void handleError(Throwable throwable);
}
