package com.lovecws.mumu.zookeeper.simple;

import com.lovecws.mumu.benchmark.client.AbstractBenchmarkClient;
import com.lovecws.mumu.benchmark.client.AbstractBenchmarkClientRunnable;
import com.lovecws.mumu.benchmark.client.ClientRunnable;
import com.lovecws.mumu.benchmark.service.BenchmarkService;
import org.apache.zookeeper.CreateMode;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class ZookeeperBenchmarkClient extends AbstractBenchmarkClient {

    private static BenchmarkService benchmarkService;
    private static boolean isMultiClient;

    public static void main(String[] args) {
        int concurrents = 30;
        int runtime = 100;//运行时间必须要大于预热时间 默认预热时间为30秒
        if(runtime<AbstractBenchmarkClient.WARMUPTIME){
            throw new IllegalArgumentException("总运行时间不能小于程序预热时间【30s】");
        }

        String classname = ZookeeperBenchmarkClientRunnable.class.getName();
        String params = null;
        isMultiClient = false;

        benchmarkService=new ZookeeperBenchmarkServiceImpl(new ZookeeperCreat());

        new ZookeeperBenchmarkClient().start(concurrents, runtime, classname, params);
    }


    @Override
    public ClientRunnable getClientRunnable(String classname, String params, CyclicBarrier barrier, CountDownLatch latch, long startTime, long endTime) {
        BenchmarkService service=null;
        if (isMultiClient) {
            benchmarkService=new ZookeeperBenchmarkServiceImpl(new ZookeeperCreat());
        } else {
            service = benchmarkService;
        }

        Class[] parameterTypes = new Class[]{BenchmarkService.class, String.class, CyclicBarrier.class,
                CountDownLatch.class, long.class, long.class};
        Object[] parameters = new Object[]{service, params, barrier, latch, startTime, endTime};

        ClientRunnable clientRunnable = null;
        try {
            clientRunnable = (ClientRunnable) (Class.forName(classname).getConstructor(parameterTypes).newInstance(parameters));
        } catch (InstantiationException | NoSuchMethodException | ClassNotFoundException | IllegalAccessException |InvocationTargetException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return clientRunnable;
    }

    /**
     * zookeeper客户端线程
     */
    public static class ZookeeperBenchmarkClientRunnable extends AbstractBenchmarkClientRunnable{

        public ZookeeperBenchmarkClientRunnable(BenchmarkService benchmarkService,String params, CyclicBarrier barrier, CountDownLatch latch, long startTime, long endTime) {
            super(benchmarkService, barrier, latch, startTime, endTime);
        }

        @Override
        protected Object call(BenchmarkService benchmarkService) {
            return benchmarkService.execute(null,null,null);
        }
    }

    /**
     * zookeeper 服务实现
     */
    public static class ZookeeperBenchmarkServiceImpl implements BenchmarkService{
        private ZookeeperChildren zookeeperChildren;
        private ZookeeperCreat zookeeperCreat;

        public ZookeeperBenchmarkServiceImpl(ZookeeperCreat zookeeperCreat) {
            this.zookeeperCreat = zookeeperCreat;
        }

        public ZookeeperChildren getZookeeperChildren() {
            return zookeeperChildren;
        }

        public void setZookeeperChildren(ZookeeperChildren zookeeperChildren) {
            this.zookeeperChildren = zookeeperChildren;
        }

        public ZookeeperCreat getZookeeperCreat() {
            return zookeeperCreat;
        }

        public void setZookeeperCreat(ZookeeperCreat zookeeperCreat) {
            this.zookeeperCreat = zookeeperCreat;
        }

        @Override
        public Object execute(String name, Class[] parameterTypes, Object[] parameters) {
            return zookeeperCreat.create("/zookeeper1223/webbench",null, CreateMode.PERSISTENT_SEQUENTIAL);
            //return zookeeperChildren.getChildren("/zookeeper",false);
        }
    }
}
