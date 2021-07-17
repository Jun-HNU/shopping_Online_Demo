



PIDS=`ps -ef|grep "daemon.nimbus "|grep -v grep`
if [ "$PIDS" != "" ]; then
echo "nimbus master is live,pid: "
ps -ef|grep daemon.nimbus | awk '{print $2}'|head -1
fi

PIDS=`ps -ef|grep "storm.ui.cor "|grep -v grep`
if [ "$PIDS" != "" ]; then
echo "nimbus  UI is live ,pid:"
ps -ef|grep storm.ui.core | awk '{print $2}'|head -1

fi


PIDS=`ps -ef|grep "daemon.supervisor "|grep -v grep`
if [ "$PIDS" != "" ]; then
echo "nimbus supervisor"
ps -ef|grep daemon.supervisor | awk '{print $2}'|head -1
fi


PIDS=`ps -ef|grep "daemon.logviewer "|grep -v grep`
if [ "$PIDS" != "" ]; then
echo "nimbus logviewer"
ps -ef|grep daemon.logviewer | awk '{print $2}'|head -1
fi