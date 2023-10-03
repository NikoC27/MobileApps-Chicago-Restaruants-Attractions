package com.nikocastellana.broadcastreceiver;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class MyWebView extends Fragment {
    private WebView myWebView = null;
    private int currIdx = -1;
    private int urlArrayLen;
    private ListViewModel model;


    // Called to create the content view for this Fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout defined in quote_fragment.xml
        // The last parameter is false because the returned view does not need to be attached to the container ViewGroup
        return inflater.inflate(R.layout.web_item, container, false);
    }

    //replaces onActivityCreated
    @Override
    public void onViewCreated (View view, Bundle savedInstanceState){

        super.onViewCreated(view,savedInstanceState);

        model = new ViewModelProvider(requireActivity()).get(ListViewModel.class);
        urlArrayLen = AttractionActivity.webArray.length;

        // retains last quote shown on config change
        model.getSelectedItem().observe(getViewLifecycleOwner(), item -> {
            // Update the UI.
            if (item < 0 || item >= urlArrayLen)
                return;
            currIdx = item;

            myWebView = getView().findViewById(R.id.web_frag);
            myWebView.setWebViewClient(new WebViewClient());
            myWebView.loadUrl(AttractionActivity.webArray[currIdx]);
        });
//
//
    }
}
