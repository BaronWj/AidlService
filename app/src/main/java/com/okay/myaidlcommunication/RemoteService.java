package com.okay.myaidlcommunication;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by panweijie on 16/4/7.
 */
public class RemoteService extends Service {
    private ArrayList<OKPerson> personList = new ArrayList<OKPerson>();
    private final String TAG = "RemmoteService^^^^:";
    private MyBinder myBinder = new MyBinder();
    ArrayList<NotifyCallBack> notifyCallBackslist = new ArrayList<>();

    private IMyService mRemoteService;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "BindService-->onBind()");

        return myService;
    }

    public class MyBinder extends Binder {

        public RemoteService getService1() {
            return RemoteService.this;
        }
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "BindService-->onCreate()");
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.i(TAG, "BindService-->onStart()");
        super.onStart(intent, startId);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "BindService-->onDestroy()");
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "BindService-->onUnbind()");
        return super.onUnbind(intent);
    }


    private final IMyService.Stub myService;

    {
        myService = new IMyService.Stub() {
            @Override
            public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
                Log.v("basicTypes:", ":" + anInt + aLong);

            }

            @Override
            public void savePersonInfo(OKPerson person) throws RemoteException {
                if (person != null) {
                    personList.add(person);
                }
                Log.v("basicTypes:", ":" + person.getName() + person.getTelNumber());
                for (NotifyCallBack aa : notifyCallBackslist){
                    aa.callback(person.getName(),true);
                }
            }

            @Override
            public List<OKPerson> getAllPerson() throws RemoteException {
                return personList;
            }

            @Override
            public void callbackListen(NotifyCallBack callback) throws RemoteException {
                notifyCallBackslist.add(callback);
            }


        };
    }


}
