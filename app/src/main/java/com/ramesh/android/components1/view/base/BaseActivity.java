package com.ramesh.android.components1.view.base;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.ramesh.android.components1.App;
import com.ramesh.android.components1.R;
import com.ramesh.android.components1.di.component.ActivityComponent;
import com.ramesh.android.components1.di.component.DaggerActivityComponent;
import com.ramesh.android.components1.di.module.ActivityModule;
import com.ramesh.android.components1.model.data.local.model.Notes;
import com.ramesh.android.components1.utils.DialogUtils;
import com.ramesh.android.components1.view.callback.UserNotes;

import butterknife.Unbinder;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.ramesh.android.components1.utils.AppConstant.ACTION_CREATE;
import static com.ramesh.android.components1.utils.AppConstant.ACTION_EDIT;


public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    private static final String TAG = "BaseActivity";

    private ProgressDialog mProgressDialog;
    private ActivityComponent mActivityComponent;
    private Unbinder mUnBinder;
    UserNotes userNotesCallback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((App) getApplication()).getComponent())
                .build();
    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }


    private void showSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = sbView.findViewById(com.google.android.material.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, R.color.white));
        snackbar.show();
    }

    @Override
    public void onError(String message) {
        if (message != null) {
            showSnackBar(message);
        } else {
            showSnackBar(getString(R.string.some_error));
        }
    }

    @Override
    public void onError(@StringRes int resId) {
        onError(getString(resId));
    }

    @Override
    public void showMessage(String message) {
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.some_error), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showMessage(@StringRes int resId) {
        showMessage(getString(resId));
    }


    @Override
    public void fullScreen() {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }


    public void showLoading() {
        hideLoading();
        mProgressDialog = DialogUtils.progressDialog(this);
    }

    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    @Override
    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    @Override
    public void showKeyBoard() {

    }

    // Permission check
    public boolean isPermissionGranted(String permission) {
        return PackageManager.PERMISSION_DENIED == ContextCompat.checkSelfPermission(this, permission);
    }

    public boolean is21Above() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void showDialog(String userAction,Notes notes, UserNotes userNotesCallback) {


        MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(this, R.style.AppCompatAlertDialogStyle);
        alertDialogBuilder.setTitle(getResources().getString(R.string.tittle));
        this.userNotesCallback = userNotesCallback;

        final EditText etxtNotes = new EditText(this);
        etxtNotes.setInputType(InputType.TYPE_CLASS_TEXT);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(50, 50, 50, 50);
        etxtNotes.setLayoutParams(layoutParams);

        alertDialogBuilder.setIcon(R.drawable.ic_notes_blue);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setView(etxtNotes);
        if(userAction.equalsIgnoreCase(ACTION_EDIT))
            etxtNotes.setText(notes.getNotes());
        alertDialogBuilder.setPositiveButton(getResources().getString(R.string.save), (dialog, which) -> {
                notes.setNotes(etxtNotes.getText().toString().trim());
            userNotesCallback.notes(userAction,notes);
        });
        alertDialogBuilder.setNegativeButton(getResources().getString(R.string.cancel), (dialog, which) -> dialog.cancel());

        alertDialogBuilder.show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
    }


    public abstract void viewModelInitialization();


}
