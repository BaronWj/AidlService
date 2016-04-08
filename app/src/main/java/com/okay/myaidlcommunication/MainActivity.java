package com.okay.myaidlcommunication;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private final String TAG= "LOG^^^^:";
    private IMyService mRemoteService;
    private boolean flag;
    private TextView text;

    ClientCallBack callBack = new ClientCallBack();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bindService();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        text = (TextView)findViewById(R.id.text11);

        Button button = (Button) findViewById(R.id.clickButton);
        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          OKPerson okPerson = new OKPerson();
                                          okPerson.setName("小红");
                                          okPerson.setTelNumber("1831083022");
                                          okPerson.setAge(12);
                                          try {
                                              mRemoteService.callbackListen(callBack);
                                              mRemoteService.savePersonInfo(okPerson);

                                          } catch (RemoteException e) {
                                              e.printStackTrace();
                                          }
                                      }
                                  }
        );
    }

    //启动service 方式2
    //
    private void bindService(){
        Intent intent = new Intent(MainActivity.this,RemoteService.class);
        Log.i(TAG, "bindService()");
        Boolean aa= bindService(intent, mRemoteConnection, Context.BIND_AUTO_CREATE);
        Log.i(TAG, "bindService()-----"+aa);

    }

    private void unBindService(){
        Log.i(TAG, "unBindService() start....");
        if(flag == true){
            Log.i(TAG, "unBindService() flag");
            unbindService(mRemoteConnection);
            flag = false;
        }
    }

    private ServiceConnection mRemoteConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mRemoteService = IMyService.Stub.asInterface(service);
            Log.i(TAG, "mRemoteService flag");
            flag = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mRemoteService = null;

        }
    };


    class ClientCallBack extends NotifyCallBack.Stub{
        @Override
        public void callback(String name, boolean joinOrLeave) throws RemoteException {
            Log.v(TAG,"name:"+name);
            text.setText(name);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
