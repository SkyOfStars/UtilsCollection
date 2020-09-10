package com.cy.utils.view.Preference;

import android.content.Context;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.cy.utils.R;

/**
 * created by cy on 2020/8/20 0020.
 */
public class PreferenceActivityTest extends PreferenceActivity {
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.checkbox);
        mContext = this;

        //CheckBoxPreference组件
        CheckBoxPreference mCheckbox0 = (CheckBoxPreference) findPreference("checkbox_0");
        mCheckbox0.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

            @Override
            public boolean onPreferenceClick(Preference preference) {
                //这里可以监听到这个CheckBox 的点击事件
                return true;
            }
        });

        mCheckbox0.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

            @Override
            public boolean onPreferenceChange(Preference arg0, Object newValue) {
                //这里可以监听到checkBox中值是否改变了
                //并且可以拿到新改变的值
                Toast.makeText(mContext, "checkBox_0改变的值为" + (Boolean) newValue, Toast.LENGTH_LONG).show();
                return true;
            }
        });

        CheckBoxPreference mCheckbox1 = (CheckBoxPreference) findPreference("checkbox_1");
        mCheckbox1.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

            @Override
            public boolean onPreferenceClick(Preference preference) {
                //这里可以监听到这个CheckBox 的点击事件
                return true;
            }
        });

        mCheckbox1.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

            @Override
            public boolean onPreferenceChange(Preference arg0, Object newValue) {
                //这里可以监听到checkBox中值是否改变了
                //并且可以拿到新改变的值
                Toast.makeText(mContext, "checkBox_1改变的值为" + (Boolean) newValue, Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }
}
