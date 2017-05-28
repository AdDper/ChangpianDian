package com.zilong.demo.changpiandian.asynctask;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zilong.demo.changpiandian.activity.MainActivity;
import com.zilong.demo.changpiandian.util.OkHttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import static android.content.Context.MODE_PRIVATE;

/**
 *
 * 这里是注册账号用的。因为注册成功后注册失败的返回的数据的类型不一样。注册成功返回的data类型为list
 * 注册失败返回的是String类型。所以注册时的判断逻辑是先获取到errorcode，如果不为OK，那么就是注册失败，直接
 * 获取message的信息。返回给用户。
 */

public class RegisterAsyncTask extends AsyncTask<String, Object, byte[]> {
    private Context context;
    protected byte[] bytes;
    private TextView textView;
    private JSONObject json_data;
    private String phone;

    public RegisterAsyncTask(Context context, TextView textView) {
        this.context = context;
        this.textView = textView;
    }

    public RegisterAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected byte[] doInBackground(String... params) {
        try {
            bytes = OkHttpUtils.getByteFromUrl(params[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    @Override
    protected void onPostExecute(byte[] bytes) {
        super.onPostExecute(bytes);
        if (bytes!=null){
            String jsonString = new String(bytes);
            try {
                JSONObject jsonObject = new JSONObject(jsonString);
                String message = jsonObject.getString("message");
                textView.setText(message);
                if (textView == null){
                    Log.i("LOL", "onPostExecute: 你大爷的");
                }
                Log.i("LOL", "onPostExecute: "+message);
                String errorcode = jsonObject.getString("errorcode");
                if (jsonObject.getString("data")==null) {
                    textView.setText(message);
                }else {
                    String data = jsonObject.getString("data");
                }

                if (jsonObject.getJSONArray("data")!=null){
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    json_data = jsonArray.getJSONObject(0);
                    String userName = json_data.getString("UserName");
                    Log.i("TAG", "onPostExecute: "+userName);
                    String accountTime = json_data.getString("RegisterAccountNumber");
                    String userNum = json_data.getString("UserNum");
                    String headPortrait = json_data.getString("HeadPortrait");
                    String registerType = json_data.getString("RegisterType");
                    String createDate = json_data.getString("CreateDate");


                    if (json_data.getString("RegisterAccountNumber")!=null){
                        String registerAccountNumber = json_data.getString("RegisterAccountNumber");

                    }
                    if (json_data.getString("Phone")!=null){
                        phone = json_data.getString("Phone");
                    }


                    /**
                     * 将得到的数据都存进sp中。在打开抽屉栏的时候将sp
                     * 中的值取出来赋给相关控件。
                     */
                    SharedPreferences sharedPreferences = context.getSharedPreferences("data_user", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("HeadPortrait",headPortrait);
                    editor.putString("UserName",userName);
                    editor.putString("RegisterAccountNumber",accountTime);
                    editor.putString("UserNum",userNum);
                    editor.putString("RegisterType",registerType);
                    editor.putString("CreateDate",createDate);
                    editor.putString("Phone",phone);
                    editor.commit();
                }

                if (errorcode.equals("OK")){
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                }
                if (!errorcode.equals("OK")){
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    textView.setText(message);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
