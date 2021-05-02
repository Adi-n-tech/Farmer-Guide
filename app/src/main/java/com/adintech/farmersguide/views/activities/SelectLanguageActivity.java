package com.adintech.farmersguide.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.adintech.farmersguide.R;
import com.adintech.farmersguide.Util.Utility;
import com.adintech.farmersguide.Util.constant.AppConstants;
import com.adintech.farmersguide.Util.preference.AppPreferencesManager;
import com.adintech.farmersguide.databinding.ActivitySelectLanguageBinding;

public class SelectLanguageActivity extends AppCompatActivity {

    //variables
    private ActivitySelectLanguageBinding mBinding;
    private String current_code = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_select_language);

        getSupportActionBar().setTitle(R.string.change_language);

        current_code = AppPreferencesManager.getString(AppConstants.LANGUAGE_CODE_KEY, this) != null ? AppPreferencesManager.getString(AppConstants.LANGUAGE_CODE_KEY, this) : AppConstants.HINDI_LANG_CODE;

        if (current_code.equals(AppConstants.ENGLISH_LANG_CODE)) {
            mBinding.rbEnglish.setChecked(true);
        } else if (current_code.equals(AppConstants.HINDI_LANG_CODE)) {
            mBinding.rbHindi.setChecked(true);
        } else if (current_code.equals(AppConstants.MARATHI_LANG_CODE)) {
            mBinding.rbMarathi.setChecked(true);
        }

        mBinding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = "";
                if (mBinding.rbEnglish.isChecked()) {
                    code = AppConstants.ENGLISH_LANG_CODE;
                } else if (mBinding.rbHindi.isChecked()) {
                    code = AppConstants.HINDI_LANG_CODE;
                } else if (mBinding.rbMarathi.isChecked()) {
                    code = AppConstants.MARATHI_LANG_CODE;
                }

                AppPreferencesManager.putString(AppConstants.LANGUAGE_CODE_KEY, code, SelectLanguageActivity.this);

                Utility.showToast(SelectLanguageActivity.this, "Language Save Successfully");
                Utility.setLocale(SelectLanguageActivity.this, code);

                Intent it = new Intent(SelectLanguageActivity.this, DashboardActivity.class);
                startActivity(it);

            }
        });
    }
}