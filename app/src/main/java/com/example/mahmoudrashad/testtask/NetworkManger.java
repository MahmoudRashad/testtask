package com.example.mahmoudrashad.testtask;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.DownloadListener;
import com.androidnetworking.interfaces.DownloadProgressListener;

/**
 * Created by mahmoudrashad on 3/11/2018.
 */

public class NetworkManger {
    Activity activity;

    private MyCustomObjectListener listener;
    public NetworkManger(Activity activity) {
        this.activity = activity;
        AndroidNetworking.initialize(activity);
    }

    public void setCustomObjectListener(MyCustomObjectListener listener) {
        this.listener = listener;
    }

    public void download (String url,String dirPath,String fileName )
    {

        AndroidNetworking.download(url,dirPath,fileName)
                .setTag("downloadTest")
                .setPriority(Priority.MEDIUM)
                .build()
                .setDownloadProgressListener(new DownloadProgressListener() {
                    @Override
                    public void onProgress(long bytesDownloaded, long totalBytes) {
                        // do anything with progress
//                        Toast.makeText(activity,"get"+bytesDownloaded +" from "+totalBytes ,Toast.LENGTH_SHORT).show();
                    }
                })
                .startDownload(new DownloadListener() {
                    @Override
                    public void onDownloadComplete() {
                        // do anything after completion
                        Toast.makeText(activity,"DownloadComplete",Toast.LENGTH_LONG).show();
                        if (listener!= null)
                            listener.oncomplate();
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Toast.makeText(activity,"onError"+error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });


    }

    public interface MyCustomObjectListener {

        void onObjectReady(String title);

        void onFailed(String title);
        void oncomplate ();


    }


}
