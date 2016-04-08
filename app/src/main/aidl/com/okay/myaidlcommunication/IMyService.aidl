// IMyService.aidl
package com.okay.myaidlcommunication;
import com.okay.myaidlcommunication.OKPerson;
import com.okay.myaidlcommunication.NotifyCallBack;
// Declare any non-default types here with import statements

interface IMyService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
    void savePersonInfo(in OKPerson person);
    List<OKPerson> getAllPerson();

    void callbackListen(NotifyCallBack callback);
}
