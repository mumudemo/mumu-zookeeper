package com.lovecws.mumu.zookeeper.curator.recipes;

import com.lovecws.mumu.zookeeper.curator.recipes.CuratorLeader;
import org.junit.Test;

public class CuratorLeaderDemo {
    @Test
    public void leader(){
        CuratorLeader leader=new CuratorLeader();
        leader.leader("/curator/leader");
    }
}
