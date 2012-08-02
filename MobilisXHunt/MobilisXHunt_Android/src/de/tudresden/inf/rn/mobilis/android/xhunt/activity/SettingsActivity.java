/*******************************************************************************
 * Copyright (C) 2010 Technische Universität Dresden
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * 	http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * Dresden, University of Technology, Faculty of Computer Science
 * Computer Networks Group: http://www.rn.inf.tu-dresden.de
 * mobilis project: http://mobilisplatform.sourceforge.net
 ******************************************************************************/
package de.tudresden.inf.rn.mobilis.android.xhunt.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import de.tudresden.inf.rn.mobilis.android.xhunt.R;
import de.tudresden.inf.rn.mobilis.android.xhunt.service.ServiceConnector;
import de.tudresden.inf.rn.mobilis.mxa.ConstMXA;

/**
 * The Class SettingsActivity.
 */
public class SettingsActivity extends PreferenceActivity 
	/*implements OnSharedPreferenceChangeListener*/ {
	
	/** The Constant TAG for logging. */
	private static final String TAG = "SettingsActivity";
	
	/** The EditText for the servers jid. */
	private EditTextPreference mEditServerJID;
	
	/** The EditText for the nickname. */
	private EditTextPreference mEditNickname;	
	
	/** The CheckBox for switching between Static Mode/GPS Mode. */
	private CheckBoxPreference mSetStaticMode;
	
	/** The Button to start the MXA preferences. */
	private Button btnMxaSettings;
	
	/** The ServiceConnector to connect to the XHuntService. */
	private ServiceConnector mServiceConnector;
	
	
    /** The Handler which will be called if the XHuntService was 
     * successfully bound. */
    private Handler mXHuntServiceBoundHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			addPreferencesFromResource(R.xml.layout_settings);
			setContentView(R.layout.activity_settings);
			
			initComponents();
		}
	};
	
	
	/**
	 * Bind XHuntService.
	 */
	private void bindXHuntService(){
    	mServiceConnector = new ServiceConnector(this);
    	mServiceConnector.doBindXHuntService(mXHuntServiceBoundHandler);
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#finish()
	 */
	@Override
	public void finish() {
		mServiceConnector.doUnbindXHuntService();
		super.finish();
	}
	
	/**
	 * Gets the shared preference key for the nickname.
	 *
	 * @return the key of the nickname
	 */
	private String getKeyNickname(){
		return getResources().getString(R.string.bundle_key_settings_username);
	}
	
	/**
	 * Gets the shared preference key for the server jid.
	 *
	 * @return the key of the server jid
	 */
	private String getKeyServerJid(){
		return getResources().getString(R.string.bundle_key_settings_serverjid);
	}
	
	/**
	 * Gets the shared preference key for the static mode.
	 * 
	 * @return the key of the static mode
	 */
	private String getKeyStaticMode() {
		return getResources().getString(R.string.bundle_key_settings_staticmode);
	}

	/**
	 * Gets the shared preference value of a key.
	 *
	 * @param key the key related to a shared preference
	 * @return the shared preference value
	 */
	private String getSharedPrefValue(String key){
		return mServiceConnector.getXHuntService().getSharedPrefHelper()
			.getValue(key);
	}
	
	
	/**
	 * Inits the components.
	 */
	private void initComponents(){
		mEditNickname = (EditTextPreference)getPreferenceScreen().findPreference(
				getKeyNickname());
		
		mEditServerJID = (EditTextPreference)getPreferenceScreen().findPreference(
				getKeyServerJid());
		
		
		mSetStaticMode = (CheckBoxPreference)getPreferenceScreen().findPreference(getKeyStaticMode());
		mSetStaticMode.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				boolean staticMode = (Boolean)newValue;
				Log.v(TAG, "User set StaticMode to " + staticMode);	
				if(staticMode) {
					mServiceConnector.getXHuntService().getMXAProxy().setStaticMode(true);
					mServiceConnector.getXHuntService().getGPSProxy().setLocation(51033880, 13783272);
				} else {
					mServiceConnector.getXHuntService().getMXAProxy().setStaticMode(false);
				}
				return true;
			}
		});
			
		
		btnMxaSettings = (Button)findViewById(R.id.settings_btn_mxa_preferences);
		btnMxaSettings.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(ConstMXA.INTENT_PREFERENCES);
				i.addCategory(Intent.CATEGORY_PREFERENCE);
				startActivity(i);
			}
		});
		
		updateSummaries();
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		bindXHuntService();
    }

	/* (non-Javadoc)
	 * @see android.app.Activity#onWindowFocusChanged(boolean)
	 */
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		updateSummaries();
		super.onWindowFocusChanged(hasFocus);
	}
	
	/**
	 * Update summaries of all preference entries.
	 * A summary displays the current value of a preference.
	 */
	private void updateSummaries(){
		mEditNickname.setSummary(getSharedPrefValue(getKeyNickname()));
		mEditServerJID.setSummary(getSharedPrefValue(getKeyServerJid()));
	}

}
