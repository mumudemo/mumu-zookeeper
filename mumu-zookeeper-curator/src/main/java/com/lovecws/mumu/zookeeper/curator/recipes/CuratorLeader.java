package com.lovecws.mumu.zookeeper.curator.recipes;

import com.lovecws.mumu.zookeeper.curator.framework.CuratorConnect;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.state.ConnectionState;

import java.util.concurrent.TimeUnit;

public class CuratorLeader {

    /**
     * leader选举
     * @return
     */
    public String leader(String path){
        for (int i = 0; i <3 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    CuratorFramework curatorFramework = new CuratorConnect().connect();
                    LeaderSelector leaderSelector=new LeaderSelector(curatorFramework, path, new LeaderSelectorListener() {
                        @Override
                        public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
                            System.out.println("成为master:"+Thread.currentThread().getName());
                            TimeUnit.SECONDS.sleep(10);
                        }

                        @Override
                        public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {

                        }
                    });
                    leaderSelector.autoRequeue();
                    leaderSelector.start();
                }
            }).start();
        }
        try {
            TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
