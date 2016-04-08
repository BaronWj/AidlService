// NotifyCallBack.aidl
package com.okay.myaidlcommunication;

// Declare any non-default types here with import statements

interface NotifyCallBack {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
     void callback(String name,boolean joinOrLeave);

}
