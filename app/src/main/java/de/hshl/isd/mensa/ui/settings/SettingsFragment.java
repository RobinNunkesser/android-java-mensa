package de.hshl.isd.mensa.ui.settings;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.preference.PreferenceFragmentCompat;
import de.hshl.isd.mensa.R;

public class SettingsFragment extends PreferenceFragmentCompat {


    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState,
            @Nullable String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }
}