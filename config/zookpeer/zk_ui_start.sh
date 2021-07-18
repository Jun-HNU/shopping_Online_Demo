
#!/bin/bash
echo "zk ui server..."
source /etc/profile
#前面已将export LINUX_DEPLOY_DIR=/mnt/hgfs/Vmshare-haddop3/linux-deploy-dir添加 为环境变量
nohup  java -jar $LINUX_DEPLOY_DIR/zkui-2.0-SNAPSHOT-jar-with-dependencies.jar >/home/zookeeper/zk_ui/zkui.log 2>&1 &