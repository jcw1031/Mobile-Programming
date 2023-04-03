package com.woopaca.exercise6;

import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import com.woopaca.exercise6.databinding.Exercise63Binding;

@SuppressWarnings("deprecation")
public class Exercise63 extends TabActivity {

    private Exercise63Binding binding; //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = Exercise63Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TabHost tabHost = getTabHost();

        TabSpec tabDog1 = tabHost.newTabSpec("DOG_1").setIndicator("댕댕이 1");
        tabDog1.setContent(binding.dog1.getId());
        tabHost.addTab(tabDog1);

        TabSpec tabDog2 = tabHost.newTabSpec("DOG_2").setIndicator("댕댕이 2");
        tabDog2.setContent(binding.dog2.getId());
        tabHost.addTab(tabDog2);

        TabSpec tabDog3 = tabHost.newTabSpec("DOG_3").setIndicator("댕댕이 3");
        tabDog3.setContent(binding.dog3.getId());
        tabHost.addTab(tabDog3);

        TabSpec tabDog4 = tabHost.newTabSpec("DOG_3").setIndicator("댕댕이 4");
        tabDog4.setContent(binding.dog4.getId());
        tabHost.addTab(tabDog4);

        tabHost.setCurrentTab(0);
    }
}
