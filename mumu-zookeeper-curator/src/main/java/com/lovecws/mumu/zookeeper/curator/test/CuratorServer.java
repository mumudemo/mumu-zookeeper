package com.lovecws.mumu.zookeeper.curator.test;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.test.TestingServer;

import java.io.File;

public class CuratorServer {

    public void testServer(){
            try {
            TestingServer server=new TestingServer(2181,new File("/"));
            server.start();

            CuratorFramework curatorFramework = CuratorFrameworkFactory.
                    builder().
                    connectString(server.getConnectString()).
                    sessionTimeoutMs(1000).
                    retryPolicy(new RetryNTimes(3, 1000)).
                    build();
            curatorFramework.start();
            System.out.println(curatorFramework.getChildren().forPath("/"));
            curatorFramework.close();
            server.stop();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
