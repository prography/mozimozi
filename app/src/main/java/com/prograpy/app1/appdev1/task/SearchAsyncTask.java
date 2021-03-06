package com.prograpy.app1.appdev1.task;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.prograpy.app1.appdev1.network.HttpRequest;
import com.prograpy.app1.appdev1.network.response.SearchResult;

import java.util.HashMap;
import java.util.Map;
import com.prograpy.app1.appdev1.utils.D;

/**
 * Created by Note on 2018-06-30.
 */

public class SearchAsyncTask extends AsyncTask<String, Integer, SearchResult> {

    private SearchHandler handler;


    public interface SearchHandler {
        public void onSuccessAppAsyncTask(SearchResult result);
        public void onFailAppAsysncask();
        public void onCancelAppAsyncTask();
    }


    public SearchAsyncTask(SearchAsyncTask.SearchHandler handler) {
        this.handler = handler;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected SearchResult doInBackground(String... strings) {

        String path = strings[0];
        String searchname = strings[1];


        SearchResult result = null;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("searchname", searchname);

        HttpRequest request = new HttpRequest();

        try {
            String str = request.callRequestServer(path, "POST", params);

            D.log("http", "str > " + str);


            Gson gson = new GsonBuilder().create();
            result = gson.fromJson(str, SearchResult.class);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return result;
    }

    @Override
    protected void onPostExecute(SearchResult AppAsyncTaskResult) {
        super.onPostExecute(AppAsyncTaskResult);

        if (AppAsyncTaskResult != null) {
            handler.onSuccessAppAsyncTask(AppAsyncTaskResult);

        } else {
            handler.onFailAppAsysncask();
        }

    }
}
