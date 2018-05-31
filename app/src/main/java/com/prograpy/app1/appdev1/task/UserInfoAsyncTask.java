package com.prograpy.app1.appdev1.task;


import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.prograpy.app1.appdev1.network.HttpRequest;
import com.prograpy.app1.appdev1.network.response.UserInfoResult;

import java.util.HashMap;
import java.util.Map;

public class UserInfoAsyncTask extends AsyncTask<String, Integer, UserInfoResult> {
    private UserInfoResultHandler handler;


    public interface UserInfoResultHandler{
        public void onSuccessAppAsyncTask(UserInfoResult result);
        public void onFailAppAsysncask();
        public void onCancelAppAsyncTask();
    }

    public UserInfoAsyncTask(UserInfoResultHandler handler){
        this.handler = handler;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected UserInfoResult doInBackground(String... strings) {

        String path = strings[0];
        String userid = strings[1];
        String password = strings[2];
        String username = strings[3];
        String usermail = strings[4];

        UserInfoResult result  = null;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userid", userid);
        params.put("password", password);
        params.put("username", username);
        params.put("usermail", usermail);

        HttpRequest request = new HttpRequest();

        try {
            String str = request.callRequestServer(path,  "POST", params);

            Log.d("http", "str > " + str);


            Gson gson = new GsonBuilder().create();
            result = gson.fromJson(str, UserInfoResult.class);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return result;
    }

    @Override
    protected void onPostExecute(UserInfoResult AppAsyncTaskResult) {
        super.onPostExecute(AppAsyncTaskResult);

        if(AppAsyncTaskResult != null){
            handler.onSuccessAppAsyncTask(AppAsyncTaskResult);

        }else{
            handler.onFailAppAsysncask();
        }
    }
}

//c+r