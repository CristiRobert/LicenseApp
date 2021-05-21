package com.example.socialtrackingapp.model.business_logic_layer;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {

    private static final String TAG = "Cristian";
    private final Object obj;

    public MyInvocationHandler( Object obj ) {
        this.obj = obj;
    }

    @Override
    public Object invoke( Object proxy, Method method, Object[] args ) throws Throwable {
        Object returnedObject = method.invoke(obj, args);
        if(obj.getClass().isAnnotationPresent(Logged.class) || method.isAnnotationPresent(Logged.class)) {
            Log.d(TAG, method.getName() + " method was executed");
            Log.d(TAG, "The object is " + obj.toString());
            Log.d(TAG, "The type of the object is " + obj.getClass());
            Log.d(TAG, "The values of parameters are ");
            for(int index = 0; index < args.length; ++index) {
                Log.d(TAG, args[index].toString() + " ");
            }
            Log.d(TAG, "The result value is " + returnedObject);
            Log.d(TAG, "");
        }
        return returnedObject;
    }
}
