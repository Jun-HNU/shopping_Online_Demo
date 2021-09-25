 kafka中的offset概念

在 Kafka 中无论是 producer 往 topic 中写数据, 还是 consumer 从 topic 中读数据, 都避免不了和 offset 打交道, 关于 offset 主要有以下几个概念。


Last Committed Offset：consumer group 最新一次 commit 的 offset，表示这个 group 已经把 Last Committed Offset 之前的数据都消费成功了。
Current Position：consumer group 当前消费数据的 offset，也就是说，Last Committed Offset 到 Current Position 之间的数据已经拉取成功，可能正在处理，但是还未 commit。
Log End Offset(LEO)：记录底层日志 (log) 中的下一条消息的 offset。, 对 producer 来说，就是即将插入下一条消息的 offset。
High Watermark(HW)：已经成功备份到其他 replicas 中的最新一条数据的 offset，也就是说 Log End Offset 与 High Watermark 之间的数据已经写入到该 partition 的 leader 中，但是还未完全备份到其他的 replicas 中，consumer 是无法消费这部分消息 (未提交消息)。

每个 Kafka 副本对象都有两个重要的属性：LEO 和 HW。注意是所有的副本，而不只是 leader 副本。关于这两者更详细解释，建议参考这篇文章。

对于消费者而言，异步模式下 committed offset 是落后于 current position 的。如果 consumer 挂掉了, 那么下一次消费数据又只会从 committed offset 的位置拉取数据，就会导致数据被重复消费。