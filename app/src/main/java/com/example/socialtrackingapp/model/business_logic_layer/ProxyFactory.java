package com.example.socialtrackingapp.model.business_logic_layer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyFactory {

    private static ProxyFactory proxyFactory;

    private ProxyFactory() {}

    public synchronized static ProxyFactory getInstance() {
        if(proxyFactory == null) {
            proxyFactory = new ProxyFactory();
        }
        return proxyFactory;
    }

    public Object createNewProxyInstance(
            Object obj, Class<?>[] interfaces,
            InvocationHandler invocationHandler
                                        ) {
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                                      interfaces, invocationHandler
                                     );
    }
}
