# zookeeper分布式协调服务
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/babymm/mumu-zookeeper/blob/master/LICENSE) [![Maven Central](https://img.shields.io/maven-central/v/com.weibo/motan.svg?label=Maven%20Central)](https://github.com/babymm/mumu-zookeeper) 
[![Build Status](https://travis-ci.org/mumudemo/mumu-zookeeper.svg?branch=master)](https://travis-ci.org/mumudemo/mumu-zookeeper) [![OpenTracing-1.0 Badge](https://img.shields.io/badge/OpenTracing--1.0-enabled-blue.svg)](http://opentracing.io)


## zookeeper描述
>zookeeper是一个分布式协调服务器，可以灵活的处理集群管理、负载均衡、配置管理、分布式锁、leader选举等功能。zookeeper被管饭运用在[hadoop](http://hadoop.apache.org/)、[hbase](http://hbase.apache.org/)、[dubbo](https://github.com/alibaba/dubbo)、[motan](https://github.com/weibocom/motan)、[kafka](http://kafka.apache.org/)等。

## 分布式系统理论
### CAP理论
一个分布式系统不可能同时满足分布式一致性（consistency）、可利用性（avaiability）、分区容错性(partition tolerance)。这三个基本要求，只可能同时满足其中两个。这就是非常出名的CAP理论。 
-  分布式一致性说的是数据在多个副本之间能否保存数据一致性。当有一个副本的数据改变之后，其他的副本能够立即进行改变。满足以上要求，就说明该系统符合分布式一致性。
-  可利用性说的是服务一直处于可利用的状态，不会因为宕机、网络问题而中断服务，不能提供服务。例如经常工地施工挖断了网线，导致一些软件而无法使用，或者被病毒攻击等。服务可利用性是标志一个公司稳定的基础，基本打工都是99.9999%可利用性。
-  分区容错性是在分布式系统中遇到任何网络故障的时候任然需要对外提供数据一致性和网络可利用性，除非整个网络出现故障。为了实现这个问题就需要将服务分别布式部署到不同的地方，但是当多个服务部署在不同的地方的时候，就又牵扯到分布式一致性的。所以才有人提出了CAP理论。

![CAP理论](http://note.youdao.com/yws/api/personal/file/CB811BB7E5B44EF4A849F3CBB4A309B7?method=download&shareKey=d710b54a97915508e16859bec4040436)

### BASE理论
BASE是基本可用、软状态、最终一致性三个短语的缩写。BASE理论是基于CAP理论来的，其核心思想是既然无法做到强一致性，但是每个应用可以根据自己的业务逻辑来实现最终一致性。
-  基本可用指的是系统出现未知的故障的时候，允许损失部分性能，如响应时间的损失，功能上的损失等。
-  软状态指的是允许系统出现中间状态，而且中间状态不影响系统的整体可用性，即允许系统在各个副本之间数据存在延迟。
-  最终一致性指的是系统中的各个副本在一定的延迟之后，最终达到一致性。最终一致性保证了数据的最终会到达一个一致的状态，而不需要保证数据的强一致性。


## 分布式一致性协议
### 2PC两阶段提交协议

### 3PC三阶段提交协议

### paxos算法


## 联系方式
**以上观点纯属个人看法，如有不同，欢迎指正。  
email:<babymm@aliyun.com>  
github:[https://github.com/babymm](https://github.com/babymm)** 
