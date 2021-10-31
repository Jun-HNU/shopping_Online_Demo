1.kafka中的offset概念

在 Kafka 中无论是 producer 往 topic 中写数据, 还是 consumer 从 topic 中读数据, 都避免不了和 offset 打交道, 关于 offset 主要有以下几个概念。


Last Committed Offset：consumer group 最新一次 commit 的 offset，表示这个 group 已经把 Last Committed Offset 之前的数据都消费成功了。
Current Position：consumer group 当前消费数据的 offset，也就是说，Last Committed Offset 到 Current Position 之间的数据已经拉取成功，可能正在处理，但是还未 commit。
Log End Offset(LEO)：记录底层日志 (log) 中的下一条消息的 offset。, 对 producer 来说，就是即将插入下一条消息的 offset。
High Watermark(HW)：已经成功备份到其他 replicas 中的最新一条数据的 offset，也就是说 Log End Offset 与 High Watermark 之间的数据已经写入到该 partition 的 leader 中，但是还未完全备份到其他的 replicas 中，consumer 是无法消费这部分消息 (未提交消息)。

每个 Kafka 副本对象都有两个重要的属性：LEO 和 HW。注意是所有的副本，而不只是 leader 副本。关于这两者更详细解释，建议参考这篇文章。

对于消费者而言，异步模式下 committed offset 是落后于 current position 的。如果 consumer 挂掉了, 那么下一次消费数据又只会从 committed offset 的位置拉取数据，就会导致数据被重复消费。

2.kafka中的position概念

kafka顺序读写磁盘
Partition中的分段存储Segment，对应log和index文件。（0.8版本之前的kafka没有timeindex文件）, 是kafka的具体时间日志
主要分成数据文件xxx.log,和索引文件xxx.index, 两个文件相对应的， 前缀相同。
segment文件命名规则：partition全局的第一个segment从0开始，后续每个segment文件名为上一个segment文件最后一条消息的offset值。数值最大为64位long大小，19位数字字符长度，没有数字用0填充。
以及.timeindex文件。
offset-position.png以索引文件中的3，497为例，依次在数据文件中表示第3个message（在全局partition表示第368772个message）、
以及该消息的物理偏移地址为497。把index文件(index file)的（3,497）与log文件（data file) 的（Message368772，497）相对应 ，
两个数字又分别代表消息的偏移量与对应的物理地址偏移量。
索引文件中的position 不是消息的offset, 而是message在segment中的物理位置。
先由文件名找到segment的起始偏移量（partition下的第多少条消息），再由offset找到实际偏移量，与消息体中的偏移量和物理地址对应。

3.kafka的同一个topic（逻辑概念）对应了num.partition个分区，每个分区对应一个文件夹，以topic名作为前缀，分区数作为后缀。
每个分区下有leader-epoch-checkpoint和.snapshot文件以及一个或者多个segment,每个segment对应.log和.index、.timeindex三个文件。
partition下都有以下5类文件
# 其中数字123456只是举个栗子
00000000000000123456.index
00000000000000123456.log
00000000000000123456.snapshot
00000000000000123456.timeindex
leader-epoch-checkpoint

3.1其中index、log、timeindex文件是二进制文本，可以使用如下kafka工具查看内容。
log.segment.bytes	单个日志文件的最大大小	int	1073741824
bin/kafka-run-class.sh kafka.tools.DumpLogSegments --files 00000000000000000000.log

segment文件的00000000000000000000.log快要满log.segment.bytes的时候就开始创建了新的log文件
并且.log和.index、.timeindex文件是一起出现的; 并且名称是以文件第一个offset命名的

.log存储消息文件
.index存储消息的索引
.timeIndex，时间索引文件，通过时间戳做索引

3.2leader-epoch-checkpoint 中保存了每一任leader开始写入消息时的offset;
会定时更新follower被选为leader时会根据这个确定哪些消息可用，checkpoint文件可以直接查看
如：
0 #版本号
1 #下面的记录行数
29 2485991681 #leader epoch ，可以看到有两位值（epoch，offset）。
## epoch表示leader的版本号，从0开始，当leader变更过1次时epoch就会+1
## offset则对应于该epoch版本的leader写入第一条消息的位移。可以理解为用户可以消费到的最早数据位移。与kafka.tools.GetOffsetShell --time -2获取到的值是一致的。


3.3snapshot:是kafka对幂等型或者事务型producer所生成的快照文件。




4.lastCaughtUpTimeMs：
怎么样判定一个分区是否有副本是处于同步失效状态的呢？从Kafka 0.9.x版本开始通过唯一的一个参数
replica.lag.time.max.ms（默认大小为10,000）来控制，当ISR中的一个follower副本滞后leader副本
的时间超过参数replica.lag.time.max.ms指定的值时即判定为副本失效，需要将此follower副本剔出除
ISR之外。具体实现原理很简单，当follower副本将leader副本的LEO（Log End Offset，每个分区最后
一条消息的位置）之前的日志全部同步时，则认为该follower副本已经追赶上leader副本，此时更新该
副本的lastCaughtUpTimeMs标识。Kafka的副本管理器（ReplicaManager）启动时会启动一个副本过
期检测的定时任务，而这个定时任务会定时检查当前时间与副本的lastCaughtUpTimeMs差值是否大于
参数replica.lag.time.max.ms指定的值。千万不要错误的认为follower副本只要拉取leader副本的数据
就会更新lastCaughtUpTimeMs，试想当leader副本的消息流入速度大于follower副本的拉取速度时，
follower副本一直不断的拉取leader副本的消息也不能与leader副本同步，如果还将此follower副本置
于ISR中，那么当leader副本失效，而选取此follower副本为新的leader副本，那么就会有严重的消息丢
失。

5.kafka副本
offsets.topic.replication.factor决定了分本的个数
副本Kafka分区下有可能有很多个副本(replica)用于实现冗余，从而进一步实现高可用。副本根据角色的不同可分为3类：
leader副本：响应clients端读写请求的副本
follower副本：被动地备份leader副本中的数据，不能响应clients端读写请求。
ISR副本：包含了leader副本和所有与leader副本保持同步的follower副本

6.kafka 缓冲池的内存回收与GC。
https://www.cnblogs.com/rwxwsblog/p/14754810.html
在新版的 Kafka Producer 中，设计了一个消息缓冲池，在创建 Producer 时会默认创建一个大小为 32M 的缓冲池，也可以通过 buffer.memory 参数指定缓冲池的大小，
同时缓冲池被切分成多个内存块，内存块的大小就是我们创建 Producer 时传的 batch.size 大小，默认大小 16384，而每个 Batch 都会包含一个 batch.size 大小的内存块，消息就是存放在内存块当中。
客户端将消息追加到对应主题分区的某个 Batch 中，如果 Batch 已经满了，则会新建一个 Batch，同时向缓冲池（RecordAccumulator）申请一块大小为 batch.size 的内存块用于存储消息。

当 Batch 的消息被发到了 Broker 后，Kafka Producer 就会移除该 Batch，既然 Batch 持有某个内存块，那必然就会涉及到 GC 问题。
频繁的申请内存，用完后就丢弃，必然导致频繁的 GC，造成严重的性能问题。那么，Kafka 是怎么做到避免频繁 GC 的呢？

前面说过了，缓冲池在设计逻辑上面被切分成一个个大小相等的内存块，当消息发送完毕，归还给缓冲池不就可以避免被回收了吗？

缓冲池的内存持有类是 BufferPool。
从 BufferPool 的成员可看出，缓冲池实际上由一个个 ByteBuffer 组成的，BufferPool 持有这些内存块，并保存在成员 free 中，free 的总大小由 totalMemory 作限制，
而 nonPooledAvailableMemory 则表示还剩下缓冲池还剩下多少内存还未被分配。

当 Batch 的消息发送完毕后，就会将它持有的内存块归还到 free 中，以便后面的 Batch 申请内存块时不再创建新的 ByteBuffer，从 free 中取就可以了，从而避免了内存块被 JVM 回收的问题。
如果你的消息大小比 batchSize 还要大，则不会从 free 中循环获取已分配好的内存块，而是重新创建一个新的 ByteBuffer，并且该 ByteBuffer 不会被归还到缓冲池中（JVM GC 回收），
如果此时 nonPooledAvailableMemory 比消息体还要小，
还会将 free 中空闲的内存块销毁（JVM GC 回收），以便缓冲池中有足够的内存空间提供给用户申请，这些动作都会导致频繁 GC 的问题出现。
7.kafka工作流程中消息快大小。
https://www.cnblogs.com/rwxwsblog/p/14754810.html

bufferpool
生产者
batch.size：
  默认值是16KB
 当有多个消息要被发送到同一个分区时，生产者会把它们放在同一个批次里。该参数指定了一个批次可
 以使用的内存大小，按照字节数计算，而不是消息个数。当批次被填满，批次里的所有消息会被发送出
 去。不过生产者并不一定都会等到批次被填满才发送，半满的批次，甚至只包含一个消息的批次也可能
 被发送。所以就算把 batch.size 设置的很大，也不会造成延迟，只会占用更多的内存而已，如果设置
 的太小，生产者会因为频繁发送消息而增加一些额外的开销。
 那个线程将producerRecord放入RecordAcumultor(bufferpoll,存放batch的缓存池)，是谁将
 其取出。batch何时被发送。
 主线程放入，sender线程取出，batch.size或者linger.ms其一条件达到时，sender线程被唤醒
 ，发送。
 
 linger.ms”决定了你的消息一旦写入一个Batch，最多等待这么多时间，他一定会跟着Batch一起发送出去。
 避免一个Batch迟迟凑不满，导致消息一直积压在内存里发送不出去的情况。这是一个很关键的参数。
 sender线程何时被唤醒，
max.request.size
默认他是1MB，该参数用于控制生产者发送的请求大小，它可以指定能发送的单个消息的最大值，也可以指单个请求里
所有消息的总大小。它也就限制了每次发送给Kafka服务器请求的最大大小，同时也会限制你一条消息的最大
大小也不能超过这个参数设置的值broker 对可接收的消息最大值也有自己的限制（ message.max.size ），所以两
边的配置最好匹配，避免生产者发送的消息被 broker 拒绝.

producer.request.timeout.ms
retries”和“retries.backoff.ms”决定了重试机制，也就是如果一个请求失败了可以重试几次，每次重试的间隔是多少毫秒。

broker

message.max.bytes
 (默认:1000000) – broker能接收消息的最大字节数，这个值应该比消费端的
fetch.message.max.bytes更小才对，否则broker就会因为消费端无法使用这个消息而挂起。

replica.fetch.max.bytes：
 (默认: 1MB) – broker可复制的消息的最大字节数。这个值应该比message.max.bytes大，否
 则broker会接收此消息，但无法将此消息复制出去，从而造成数据丢失。

消费者：
fetch.message.max.bytes

 (默认 1MB) – 消费者能读取的最大消息。这个值应该大于或等于message.max.bytes。
 
 
 
 8.kafkaProducer构造函数分析
 KafkaProducer在进行构造器初始化的时候会初始化Partitioner、Serializer、拦截器、元数据、消息收集器RecordAccumulator、NetworkClient、Sender等：
 https://blog.csdn.net/noaman_wgs/article/details/105646288
org.apache.kafka.clients.producer.KafkaProducer.KafkaProducer(ProducerConfig config, Serializer<K> keySerializer, org.apache.kafka.common
.serialization.Serializer<V> valueSerializer, Metadata metadata, KafkaClient kafkaClient);



Kafka消息发送的步骤：

    初始化：Kafka初始化，加载默认配置以及设置的配置参数，开启网络线程；
    执行拦截器逻辑，预处理消息（如果未实现拦截器，就跳过此步骤）；
    获取集群元数据metadata；（这里可能时sender线程获取元数据）
    调用Serializer.serialize()方法序列化消息的key/value；
    调用partition()选择合适的分区策略，为消息进行分区；
    将消息缓存到RecordAccumulator中；
    唤醒Sender线程，将待发送的数据按 【Broker Id <=> List<ProducerBatch>】的数据进行归类
    与服务端不同的Broker建立网络连接，将对应Broker待发送的消息List<ProducerBatch>发送出去。

在KafkaProducer发送消息过程中，有一些常见类需要讲解，以便更好理解源码的实现过程。
1). Cluster

Kafka Cluster保存节点、topic、分区信息，比如当前 Kafka 集群共有多少主题、多少 Broker 等：

    broker.id 与 node对应关系
    topic与Partition对应关系
    node与Partition对应关系
2).Metadata保存了所有topic相关的部分数据，会被所有client线程和后台sender线程共享。当请求一个topic的metadata不存在时，会触发metadata的更新过程。
  Metadata的主要数据结构为：Cluster对象和其他熟悉，Cluster记录了topic与集群的信息，具体参数如下。
3)Metrics

Metrics是用来统计某些指标的度量。Sensor能够统计某段时间内关联的多个Metric的指标功能，如平均值、最大值、最小值。·
4)RecordAccumulator

RecordAccumulator可以理解为固定大小的队列，用来将消息内容保存到内容当中。

5).封装消息发送的处理逻辑。

整个生产者客户端主要有两个线程，主线程以及Sender线程。Producer在主线程中产生消息，然后通过拦截器，序列化器，
分区器之后缓存到消息累加器RecordAccumulator中。Sender线程从RecordAccumulator中获取消息并发送到kafka中。
RecordAccumulator主要用来缓存消息，这样发送的时候进行批量发送以便减少相应的网络传输。RecordAccumulator
缓存的大小可以通过配置参数buffer.memory配置，默认是32M。如果创建消息的速度过快，超过sender发送给kafka服务
器的速度，会导致缓存空间不足，这个时候sender线程可能会阻塞或者抛出异常，max.block.ms配置决定阻塞的最大时间。

9.KafkaProducer Sender 线程详解
https://www.cnblogs.com/dingwpmz/p/12168351.html
https://segmentfault.com/a/1190000040449414?utm_source=sf-similar-article


10. kafka吞吐量高的原因
   
   （1）顺序读写：基于磁盘的随机读写确实很慢，但磁盘的顺序读写性能却很高，一些情况下磁盘顺序读写性能甚至要高于内存随机读写。（Kafka的message是不断追加到本地磁盘文件末尾的，而不是随机的写入，这使得Kafka写入吞吐量得到了显著提升 。）
   
   （2）Page Cache:为了优化读写性能，kafka利用了操作系统本身的page cache,就是利用操作系统自身的内存而不是JVM空间内存，这样做的好处是：
   
            1：避免Object消耗：如果是使用java堆，java对象的内存消耗比较大，通常是所存储数据的两倍甚至更多。
   
            2：避免GC问题：随着JVM中数据不断增多，垃圾回收将会变得复杂与缓慢，使用系统缓存就不会存在GC问题。
   
   通过操作系统的page cache,kafka的读写操作基本上是基于内存的，读写速度得到了极大的提升。
   
   （3）零拷贝:（不使用的时候，数据在内核空间和用户空间之间穿梭了两次），使用零拷贝技术后避免了这种拷贝。通过这种 “零拷贝” 的机制，Page Cache 结合 sendfile 方法，Kafka消费端的性能也大幅提升。这也是为什么有时候消费端在不断消费数据时，我们并没有看到磁盘io比较高，此刻正是操作系统缓存在提供数据。
   
   （4）分区分段+索引：topic 中的数据是按照一个一个的partition即分区存储到不同broker节点的，每个partition对应了操作系统上的一个文件夹，partition实际上又是按照segment分段存储的，这也非常符合分布式系统分区分桶的设计思想。kafka的message消息实际上是分布式存储在一个一个segment中的，每次文件操作也是直接操作的segment。为了进一步的查询优化，kafka又默认为分段后的数据文件建立了索引文件，就是文件系统上的.index文件.这种分区分段+索引的设计，不仅提升了数据读取的效率，同时也提高了数据处理的并行度。
   
   （5）批量读写：Kafka数据读写也是批量的而不是单条的。在向Kafka写入数据时，可以启用批次写入，这样可以避免在网络上频繁传输单个消息带来的延迟和带宽开销。假设网络带宽为10MB/S，一次性传输10MB的消息比传输1KB的消息10000万次显然要快得多。
   
   （6）批量压缩：
   
   在很多情况下，系统的瓶颈不是CPU或磁盘，而是网络IO，对于需要在广域网上的数据中心之间发送消息的数据流水线尤其如此。进行数据压缩会消耗少量的CPU资源,不过对于kafka而言,网络IO更应该需要考虑。
   
       如果每个消息都压缩，但是压缩率相对很低，所以Kafka使用了批量压缩，即将多个消息一起压缩而不是单个消息压缩
       Kafka允许使用递归的消息集合，批量的消息可以通过压缩的形式传输并且在日志中也可以保持压缩格式，直到被消费者解压缩
       Kafka支持多种压缩协议，包括Gzip和Snappy压缩协议
   
        Kafka速度的秘诀在于，它把所有的消息都变成一个批量的文件，并且进行合理的批量压缩，减少网络IO损耗，通过mmap提高I/O速度，写入数据的时候由于单个Partion是末尾添加所以速度最优；读取数据的时候配合sendfile直接暴力输出。
   
    
11.kafka的消费流程
https://www.bilibili.com/video/BV1Vf4y177cg?p=10
kafak消费者消费记录后会将offset信息生产到一个cumsumer_offset的topic当中。

12.kafka 如何保证可靠性
数据重复生产或消费
数据遗漏生产或消费
kafak宕机后的重连。


消费者群主，群组协调器，再均衡监听器
生产者是线程安全的
消费者是线程不安全的，所以必须为每个线程创建一个消费者实例。
如果一个消费者，用多个线程来跑，会有线程安全问题。

分区在均衡时，发生重复消费和数据丢失。
例如：自动提交时，offset是每个5s提交一次，在第三秒时发生再均衡，数据被消费了，但offset没来的及提交。
此时在均衡后接着上次最新提交的位置开始消费，就会导致数据被重复消费。
同步提交时，如果提交发生阻塞，后来逻辑无法进行，程序会一直阻塞在哪里，异步提交就可以解决这类问题。

kafka
batch.num.messages

默认值：200，每次批量消息的数量，只对 asyc 起作用