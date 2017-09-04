package com.lovecws.mumu.zookeeper.zkclient;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;

import java.io.*;
import java.util.List;

public class ZKClientCreate {

    public ZkClient create(){
        ZkClient zkClient=new ZkClient(ZKClientConfiguration.address);
        zkClient.subscribeChildChanges("/temp", new IZkChildListener() {
            @Override
            public void handleChildChange(String s, List<String> list) throws Exception {
                System.out.println(s);
                System.out.println(list);
            }
        });
        zkClient.createEphemeral("/temp");
        return zkClient;
    }
}
