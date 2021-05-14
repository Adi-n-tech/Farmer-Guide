package com.adintech.farmersguide.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.adintech.farmersguide.Models.SharesPreferences;
import com.adintech.farmersguide.R;
import com.adintech.farmersguide.Util.Utility;
import com.adintech.farmersguide.Util.constant.AppConstants;
import com.adintech.farmersguide.Util.preference.AppPreferencesManager;
import com.adintech.farmersguide.databinding.ActivitySelectLanguageBinding;

public class SelectLanguageActivity extends AppCompatActivity implements View.OnClickListener {

    //variables
    private ActivitySelectLanguageBinding mBinding;
    private String current_code = "";
    private String selectedLangCode = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_select_language);

        mBinding.rbEnglish.setOnClickListener(this);
        mBinding.rbMarathi.setOnClickListener(this);
        mBinding.rbHindi.setOnClickListener(this);
        mBinding.btnSave.setOnClickListener(this);

        current_code = AppPreferencesManager.getString(AppConstants.LANGUAGE_CODE_KEY, this) != null ? AppPreferencesManager.getString(AppConstants.LANGUAGE_CODE_KEY, this) : AppConstants.HINDI_LANG_CODE;
        if (current_code.equals(AppConstants.ENGLISH_LANG_CODE)) {
            setSelectedOptionHighlight(View.VISIBLE, View.GONE, View.GONE);
        } else if (current_code.equals(AppConstants.HINDI_LANG_CODE)) {
            setSelectedOptionHighlight(View.GONE, View.VISIBLE, View.GONE);
        } else if (current_code.equals(AppConstants.MARATHI_LANG_CODE)) {
            setSelectedOptionHighlight(View.GONE, View.GONE, View.VISIBLE);
        }
      /*  mBinding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = "";
                if (mBinding.rbEnglish.isClickable()) {
                    code = AppConstants.ENGLISH_LANG_CODE;
                    mBinding.englishClick.setVisibility(View.VISIBLE);
                } else if (mBinding.rbHindi.isClickable()) {
                    mBinding.hindiClick.setVisibility(View.VISIBLE);
                    code = AppConstants.HINDI_LANG_CODE;
                } else if (mBinding.rbMarathi.isClickable()) {
                    code = AppConstants.MARATHI_LANG_CODE;
                    mBinding.marathidClick.setVisibility(View.VISIBLE);
                }

                AppPreferencesManager.putString(AppConstants.LANGUAGE_CODE_KEY, code, SelectLanguageActivity.this);

                Utility.showToast(SelectLanguageActivity.this, "Language Save Successfully");
                Utility.setLocale(SelectLanguageActivity.this, code);

                Intent it = new Intent(SelectLanguageActivity.this, DashboardActivity.class);

                startActivity(it);

            }
        });*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rbEnglish:
                selectedLangCode = AppConstants.ENGLISH_LANG_CODE;
                setSelectedOptionHighlight(View.VISIBLE, View.GONE, View.GONE);
                break;
            case R.id.rbMarathi:
                selectedLangCode = AppConstants.MARATHI_LANG_CODE;
                setSelectedOptionHighlight(View.GONE, View.GONE, View.VISIBLE);
                break;
            case R.id.rbHindi:
                selectedLangCode = AppConstants.HINDI_LANG_CODE;
                setSelectedOptionHighlight(View.GONE, View.VISIBLE, View.GONE);
                break;
            case R.id.btnSave:
                AppPreferencesManager.putString(AppConstants.LANGUAGE_CODE_KEY, selectedLangCode, SelectLanguageActivity.this);

                Utility.showToast(SelectLanguageActivity.this, "Language Save Successfully");
                Utility.setLocale(SelectLanguageActivity.this, selectedLangCode);

                SharesPreferences sharesPreferences = new SharesPreferences(SelectLanguageActivity.this);
                Intent it;
                if (sharesPreferences.checkLogin()) {
                    it = new Intent(SelectLanguageActivity.this, DashboardActivity.class);
                } else {
                    it = new Intent(SelectLanguageActivity.this, LoginActivity.class);
                }
                startActivity(it);
                break;
        }
    }

    private void setSelectedOptionHighlight(int english, int hindi, int marathi) {
        mBinding.englishClick.setVisibility(english);
        mBinding.hindiClick.setVisibility(hindi);
        mBinding.marathidClick.setVisibility(marathi);
    }
}