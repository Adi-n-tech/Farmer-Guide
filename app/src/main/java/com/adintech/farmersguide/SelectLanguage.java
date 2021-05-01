package com.adintech.farmersguide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.adintech.farmersguide.constant.AppConstants;
import com.adintech.farmersguide.preference.AppPreferencesManager;

public class SelectLanguage extends AppCompatActivity {
    private RadioButton rbEnglish, rbHindi, rbMarathi;
    private Button btnSave;
    private String current_code = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_language);

        getSupportActionBar().setTitle(R.string.change_language);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rbEnglish = findViewById(R.id.rbEnglish);
        rbHindi = findViewById(R.id.rbHindi);
        rbMarathi = findViewById(R.id.rbMarathi);
        btnSave = findViewById(R.id.btnSave);

        current_code = AppPreferencesManager.getString(AppConstants.LANGUAGE_CODE_KEY, this) != null ? AppPreferencesManager.getString(AppConstants.LANGUAGE_CODE_KEY, this) : AppConstants.HINDI_LANG_CODE;

        if (current_code.equals(AppConstants.ENGLISH_LANG_CODE)) {
            rbEnglish.setChecked(true);
        } else if (current_code.equals(AppConstants.HINDI_LANG_CODE)) {
            rbHindi.setChecked(true);
        } else if (current_code.equals(AppConstants.MARATHI_LANG_CODE)) {
            rbMarathi.setChecked(true);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = "";
                if (rbEnglish.isChecked()) {
                    code = AppConstants.ENGLISH_LANG_CODE;
                } else if (rbHindi.isChecked()) {
                    code = AppConstants.HINDI_LANG_CODE;
                } else if (rbMarathi.isChecked()) {
                    code = AppConstants.MARATHI_LANG_CODE;
                }

                AppPreferencesManager.putString(AppConstants.LANGUAGE_CODE_KEY, code, SelectLanguage.this);

                Utility.showToast(SelectLanguage.this, "Language Save Successfully");
                Utility.setLocale(SelectLanguage.this, code);

                Intent it = new Intent(SelectLanguage.this, MainActivity.class);
                startActivity(it);

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}