package com.example.exercise14;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import com.example.exercise14.databinding.ActivityMainBinding;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {

    private static final String MUSIC_PATH = Environment.getExternalStorageDirectory().getPath() + "/";

    private final MediaPlayer mediaPlayer = new MediaPlayer();
    private final List<String> musicList = new ArrayList<>();
    private ActivityMainBinding binding;
    private String selectedMusic = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);
        ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

        readFile();

        configureListView();
        configureButton();
    }

    private void readFile() {
        File[] files = new File(MUSIC_PATH).listFiles();
        System.out.println("files.length = " + files.length);
        for (File file : files) {
            String fileName = file.getName();
            if (fileName.endsWith("mp3")) {
                System.out.println("fileName = " + fileName);
                musicList.add(fileName.split("\\.")[0]);
            }
        }
    }

    private void configureListView() {
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, musicList);
        binding.mp3ListView.setAdapter(adapter);
        binding.mp3ListView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        binding.mp3ListView.setItemChecked(0, true);

        binding.mp3ListView.setOnItemClickListener((parent, view, position, id) -> {
            selectedMusic = musicList.get(position);
        });
    }

    @SuppressLint("SetTextI18n")
    private void configureButton() {
        binding.stopButton.setClickable(false);

        binding.playButton.setOnClickListener(view -> {
            try {
                if (selectedMusic.isEmpty()) {
                    return;
                }

                mediaPlayer.setDataSource(MUSIC_PATH + selectedMusic);
                mediaPlayer.prepare();
                mediaPlayer.start();

                binding.playButton.setClickable(false);
                binding.stopButton.setClickable(true);

                binding.musicTextView.setText("Playing: " + selectedMusic);
                binding.musicProgressBar.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        binding.stopButton.setOnClickListener(view -> {
            mediaPlayer.stop();
            mediaPlayer.reset();

            binding.playButton.setClickable(true);
            binding.stopButton.setClickable(false);

            binding.musicTextView.setText("Stop");
            binding.musicProgressBar.setVisibility(View.INVISIBLE);
        });
    }
}