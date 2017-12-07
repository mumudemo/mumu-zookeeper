package com.lovecws.mumu.zookeeper.curator.recipes;

import com.lovecws.mumu.zookeeper.curator.recipes.CuratorAtomic;
import org.junit.Test;

public class CuratorAtomicDemo {

    @Test
    public void atomic(){
        CuratorAtomic curatorAtomic=new CuratorAtomic();
        curatorAtomic.atomic("/curator/atomic",100);
    }
}
