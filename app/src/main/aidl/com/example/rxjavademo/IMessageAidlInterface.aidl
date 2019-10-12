// IMessageAidlInterface.aidl
package com.example.rxjavademo;

// Declare any non-default types here with import statements

import com.example.rxjavademo.Bean;
interface IMessageAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    Bean get();

}
