package com.example.draqo.andreipetrooshinpoms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.methods.VKApiWall;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private String[] scope = new String[]{VKScope.MESSAGES, VKScope.FRIENDS, VKScope.WALL, VKScope.PHOTOS};

    public static ArrayList<String> stories = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Предостовление прав пользователя
        VKSdk.login(this, scope);


    }


    // Авторизация пользователя
    @Override
    protected void onActivityResult(final int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
// Пользователь успешно авторизовался
                Toast.makeText(getApplicationContext(), "Authorithation complited...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(VKError error) {
// Произошла ошибка авторизации (например, пользователь запретил авторизацию)
                Toast.makeText(getApplicationContext(), "ERROR ", Toast.LENGTH_SHORT).show();

            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    //Обработчик кнопки
    public void onClick(View view) {

        //Отправление запроса на вк сервер
        VKRequest vkRequest = new VKApiWall()
                .get(VKParameters.from(VKApiConst.OWNER_ID, String.valueOf(-33850608), VKApiConst.COUNT, 30, VKApiConst.FILTERS, "owner"));
        vkRequest.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                try {
                    //Чтение JSON объетка
                    JSONObject jsonObject = (JSONObject) response.json.get("response");
                    JSONArray jsonArray = (JSONArray) jsonObject.get("items");

                    //Заполнение списка историями
                    for (int i = 1; i < jsonArray.length(); i++) {
                        JSONObject jobj = (JSONObject) jsonArray.get(i);
                        stories.add("Моя история №" + i
                                + ": \n " + jobj.getString("text"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        // Переход на другую активность
        Intent intent = new Intent(MainActivity.this, ViewActivity.class);
        startActivity(intent);

    }
}
