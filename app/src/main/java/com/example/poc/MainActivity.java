package com.example.poc;

import androidx.appcompat.app.AppCompatActivity;

import com.liveperson.api.LivePersonCallback;
import com.liveperson.infra.ConversationViewParams;
import com.liveperson.infra.InitLivePersonProperties;
import com.liveperson.infra.auth.LPAuthenticationParams;
import com.liveperson.infra.auth.LPAuthenticationType;
import com.liveperson.infra.callbacks.InitLivePersonCallBack;
import com.liveperson.messaging.TaskType;
import com.liveperson.messaging.model.AgentData;
import com.liveperson.messaging.sdk.api.LivePerson;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private static final String APP_ID = "com.example.poc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startCodeFlow(View v) {
        String brandID = "62219232";
        final String authCode = "k16336";
        LivePerson.initialize(this, new InitLivePersonProperties(brandID, APP_ID, new InitLivePersonCallBack() {
            @Override
            public void onInitSucceed() {
                LPAuthenticationParams lpAuthenticationParams = new LPAuthenticationParams(LPAuthenticationType.AUTH);
                lpAuthenticationParams.setAuthKey(authCode);
                LivePerson.showConversation(MainActivity.this, lpAuthenticationParams, new ConversationViewParams());
            }

            @Override
            public void onInitFailed(Exception e) {
                Log.e("Error authenticating", Objects.requireNonNull(e.getMessage()));
            }
        }));
    }
}
