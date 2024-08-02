package com.example.centrocultural.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.centrocultural.AudioplayService;
import com.example.centrocultural.R;

public class PaintingDetailFragment extends Fragment {

    private static final String ARG_NAME = "name";
    private static final String ARG_IMAGE_RES_ID = "imageResId";
    private static final String ARG_DESCRIPTION = "description";
    private static final String ARG_ARTIST = "artist";
    private static final String ARG_YEAR = "year";
    private static final String ARG_AUDIO_FILE_NAME = "audioFileName";
    private String audioFileName;

    public static PaintingDetailFragment newInstance(String name, int imageResId, String description, String artist, String year, String audioFileName) {
        PaintingDetailFragment fragment = new PaintingDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        args.putInt(ARG_IMAGE_RES_ID, imageResId);
        args.putString(ARG_DESCRIPTION, description);
        args.putString(ARG_ARTIST, artist);
        args.putString(ARG_YEAR, year);
        args.putString(ARG_AUDIO_FILE_NAME, audioFileName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_painting_detail, container, false);

        ImageView imageView = view.findViewById(R.id.paintingDetailImageView);
        TextView nameTextView = view.findViewById(R.id.paintingDetailTitleTextView);
        TextView descriptionTextView = view.findViewById(R.id.paintingDetailDescriptionTextView);
        TextView artistTextView = view.findViewById(R.id.paintingDetailAuthorTextView);
        TextView yearTextView = view.findViewById(R.id.paintingDetailYearTextView);

        if (getArguments() != null) {
            nameTextView.setText(getArguments().getString(ARG_NAME));
            imageView.setImageResource(getArguments().getInt(ARG_IMAGE_RES_ID));
            descriptionTextView.setText(getArguments().getString(ARG_DESCRIPTION));
            artistTextView.setText(getArguments().getString(ARG_ARTIST));
            yearTextView.setText(getArguments().getString(ARG_YEAR));
            audioFileName = getArguments().getString(ARG_AUDIO_FILE_NAME);
        }

        Button btnPlay = view.findViewById(R.id.btnPlay);
        Button btnPause = view.findViewById(R.id.btnPause);
        Button btnResume = view.findViewById(R.id.btnResume);
        Button btnStop = view.findViewById(R.id.btnStop);

        btnPlay.setOnClickListener(onClickListenerPlay());
        btnPause.setOnClickListener(onClickListenerPause());
        btnResume.setOnClickListener(onClickListenerResume());
        btnStop.setOnClickListener(onClickListenerStop());

        return view;
    }

    private View.OnClickListener onClickListenerPlay() {
        return v -> {
            Intent audioplayServiceIntent = new Intent(getContext(), AudioplayService.class);
            audioplayServiceIntent.putExtra(AudioplayService.FILENAME, audioFileName);
            audioplayServiceIntent.putExtra(AudioplayService.COMMAND, AudioplayService.PLAY);
            getContext().startService(audioplayServiceIntent);
        };
    }
   /*private View.OnClickListener onClickListenerPlay() {
        return v -> {
            startAudioPlayService(AudioplayService.PLAY, "image1.mp3");
        };
    }*/

    private View.OnClickListener onClickListenerPause() {
        return v -> {
            Intent audioplayServiceIntent = new Intent(getContext(), AudioplayService.class);
            audioplayServiceIntent.putExtra(AudioplayService.COMMAND, AudioplayService.PAUSE);
            getContext().startService(audioplayServiceIntent);
        };
    }
    /*private View.OnClickListener onClickListenerPause() {
        return v -> {
            startAudioPlayService(AudioplayService.PAUSE, null);
        };
    }*/

    private View.OnClickListener onClickListenerResume() {
        return v -> {
            Intent audioplayServiceIntent = new Intent(getContext(), AudioplayService.class);
            audioplayServiceIntent.putExtra(AudioplayService.COMMAND, AudioplayService.RESUME);
            getContext().startService(audioplayServiceIntent);
        };
    }
    /*private View.OnClickListener onClickListenerResume() {
        return v -> {
            startAudioPlayService(AudioplayService.RESUME, null);
        };
    }*/
    private View.OnClickListener onClickListenerStop() {
        return v -> {
            Intent audioplayServiceIntent = new Intent(getContext(), AudioplayService.class);
            audioplayServiceIntent.putExtra(AudioplayService.COMMAND, AudioplayService.STOP);
            getContext().startService(audioplayServiceIntent);
        };
    }
    /*private View.OnClickListener onClickListenerStop() {
        return v -> {
            startAudioPlayService(AudioplayService.STOP, null);
        };
    }*/
    private void startAudioPlayService(String command, String filename) {
        Intent audioplayServiceIntent = new Intent(getContext(), AudioplayService.class);
        audioplayServiceIntent.putExtra(AudioplayService.COMMAND, command);
        if (filename != null) {
            audioplayServiceIntent.putExtra(AudioplayService.FILENAME, filename);
        }
        getContext().startService(audioplayServiceIntent);
    }
}