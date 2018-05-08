package com.anhhn.weatherdemoapp.ui.home;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.anhhn.weatherdemoapp.R;

/**
 * Created by Andy Ha on 5/8/18.
 */
public class ButtonFragment extends Fragment {

    private Button btnRefresh;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_button, container, false);
        btnRefresh = view.findViewById(R.id.btnRefresh);
        btnRefresh.setOnClickListener(v -> {
            ((HomeActivity)getActivity()).refreshList();
        });

        return view;
    }
}
