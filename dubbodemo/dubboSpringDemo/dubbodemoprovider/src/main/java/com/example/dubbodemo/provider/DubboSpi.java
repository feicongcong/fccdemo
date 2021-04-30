package com.example.dubbodemo.provider;

import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.rpc.Protocol;

public class DubboSpi {
    public static void main(String[] args) {
//        ExtensionLoader<Protocol> extensionLoader = ExtensionLoader.getExtensionLoader(Protocol.class);
//        Protocol protocol = extensionLoader.getExtension("http");
//        System.out.println(protocol);

        ExtensionLoader<Person> extensionLoader1 = ExtensionLoader.getExtensionLoader(Person.class);
        Person person = extensionLoader1.getExtension("my");
        System.out.println(person.getName());
    }
}
