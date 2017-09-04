package com.lovecws.mumu.zookeeper.simple;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;

import java.util.ArrayList;
import java.util.List;

public class ZookeeperTransaction {

    /**
     * 一次提交多个事务 要么全部成功 要么全部失败
     * @return
     */
    public List<OpResult> multiCreate(){
        ZookeeperConnect zookeeperConnect=new ZookeeperConnect();
        ZooKeeper zooKeeper = zookeeperConnect.connect();

        List<Op> ops = new ArrayList<Op>();

        Op op3 = Op.delete("/op", -1);
        ops.add(op3);

        Op op4 = Op.delete("/op2", -1);
        ops.add(op4);

        Op op = Op.create("/op", null, ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        ops.add(op);

        Op op2 = Op.create("/op2", null, ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        ops.add(op2);

        try {
            List<OpResult> opResults = zooKeeper.multi(ops);
            return opResults;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
        return null;
    }
}
