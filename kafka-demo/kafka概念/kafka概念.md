1.kafka中的offset概念

在 Kafka 中无论是 producer 往 topic 中写数据, 还是 consumer 从 topic 中读数据, 都避免不了和 offset 打交道, 关于 offset 主要有以下几个概念。


Last Committed Offset：consumer group 最新一次 commit 的 offset，表示这个 group 已经把 Last Committed Offset 之前的数据都消费成功了。
Current Position：consumer group 当前消费数据的 offset，也就是说，Last Committed Offset 到 Current Position 之间的数据已经拉取成功，可能正在处理，但是还未 commit。
Log End Offset(LEO)：记录底层日志 (log) 中的下一条消息的 offset。, 对 producer 来说，就是即将插入下一条消息的 offset。
High Watermark(HW)：已经成功备份到其他 replicas 中的最新一条数据的 offset，也就是说 Log End Offset 与 High Watermark 之间的数据已经写入到该 partition 的 leader 中，但是还未完全备份到其他的 replicas 中，consumer 是无法消费这部分消息 (未提交消息)。

每个 Kafka 副本对象都有两个重要的属性：LEO 和 HW。注意是所有的副本，而不只是 leader 副本。关于这两者更详细解释，建议参考这篇文章。

对于消费者而言，异步模式下 committed offset 是落后于 current position 的。如果 consumer 挂掉了, 那么下一次消费数据又只会从 committed offset 的位置拉取数据，就会导致数据被重复消费。

2.kafka中的position概念


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

6.kafka 缓冲池

