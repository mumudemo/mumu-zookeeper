package com.lovecws.mumu.zookeeper.zkclient;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;
import org.apache.zookeeper.server.ByteBufferOutputStream;

import java.io.*;

public class ZKClientConnect {

    public ZkClient zkClient(){
        ZkClient zkClient=new ZkClient(ZKClientConfiguration.address);
        return zkClient;
    }

    public ZkClient zkClientWithSerializer(){
        ZkClient zkClient=new ZkClient(ZKClientConfiguration.address, ZKClientConfiguration.sessionTimeOut, 2000, new ZkSerializer() {
            @Override
            public byte[] serialize(Object o) throws ZkMarshallingError {
                ByteArrayOutputStream bs=null;
                ObjectOutputStream os=null;
                try {
                    bs = new ByteArrayOutputStream();
                    os=new ObjectOutputStream(bs);
                    os.writeObject(o);
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        if(os!=null){
                            os.close();
                        }
                        if(bs!=null){
                            bs.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return bs.toByteArray();
            }

            @Override
            public Object deserialize(byte[] bytes) throws ZkMarshallingError {
                ByteArrayInputStream bs=null;
                ObjectInputStream os=null;
                try {
                    bs=new ByteArrayInputStream(bytes);
                    os=new ObjectInputStream(bs);
                    return os.readObject();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        if(os!=null){
                            os.close();
                        }
                        if(bs!=null){
                            bs.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        });
        return zkClient;
    }
}
