package com.example.android.socialmedia.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.socialmedia.Activity.Home;
import com.example.android.socialmedia.Adapter.DataAdapter;
import com.example.android.socialmedia.R;
import com.example.android.socialmedia.classes.Data;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class DataFragment extends android.support.v4.app.Fragment implements DataAdapter.recyclerListener {
    ArrayList<Data> arrayList;
    RecyclerView recyclerView;
    String myDataFromActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Home activity = (Home) getActivity();

        myDataFromActivity = activity.fi;

        return inflater.inflate(R.layout.data_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        arrayList = new ArrayList<>();

        recyclerView = view.findViewById(R.id.recycle_data);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        DataAdapter dataAdapter = new DataAdapter(getActivity());


        switch (myDataFromActivity) {
            case "android":
                recyclerView.setAdapter(dataAdapter);
                dataAdapter.setArrayListAndroid(arrayList);
                dataAdapter.setOnRecyclerListener(this);
                break;
            case "Architecture":
                recyclerView.setAdapter(dataAdapter);
                dataAdapter.setArrayList‎Architecture(arrayList);
                dataAdapter.setOnRecyclerListener(this);
                break;
            case "Biology":
                recyclerView.setAdapter(dataAdapter);
                dataAdapter.setArrayListBiology(arrayList);
                dataAdapter.setOnRecyclerListener(this);
                break;
            case "Physics":
                recyclerView.setAdapter(dataAdapter);
                dataAdapter.setArrayListPhiscal(arrayList);
                dataAdapter.setOnRecyclerListener(this);
                break;
            case "English":
                recyclerView.setAdapter(dataAdapter);
                dataAdapter.setArrayListEnglish(arrayList);
                dataAdapter.setOnRecyclerListener(this);
                break;
            case "Design":
                recyclerView.setAdapter(dataAdapter);
                dataAdapter.setArrayListDesign(arrayList);
                dataAdapter.setOnRecyclerListener(this);
                break;
        }
    }


    @Override
    public void onNamClicked(int pos) {
        Data data = arrayList.get(pos);
        String uri = data.getUrl();
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uri)));
    }
}