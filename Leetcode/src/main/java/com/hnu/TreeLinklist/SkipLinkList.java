package com.hnu.TreeLinklist;
//https://zhuanlan.zhihu.com/p/339750543
import java.util.Random;
import java.util.Stack;

class SkipLinkNode<T>
{

    int ListIndex;//二次hash计算得到的
    T value;
    SkipLinkNode right,down;//左右上下四个方向的指针
    public SkipLinkNode (int ListIndex,T value) {
        this.ListIndex=ListIndex;
        this.value=value;

    }

}

public class SkipLinkList <T> {

    SkipLinkNode headNode;//头节点，入口
    int highLevel;//层数
    Random random;// 用于投掷硬币
    final int MAX_LEVEL = 32;//最大的层
    SkipLinkList(){
        random=new Random();
        headNode=new SkipLinkNode(Integer.MIN_VALUE,null);
        highLevel=0;
    }
    public SkipLinkNode search(int ListIndex) {
        SkipLinkNode team=headNode;
        while (team!=null) {
            if(team.ListIndex==ListIndex)
            {
                return  team;
            }
            else if(team.right==null)//右侧没有了，只能下降
            {
                team=team.down;
            }
            else if(team.right.ListIndex>ListIndex)//需要下降去寻找
            {
                team=team.down;
            }
            else //右侧比较小向右
            {
                team=team.right;
            }
        }
        return null;
    }

    public void delete(int ListIndex)//删除不需要考虑层数
    {
        SkipLinkNode team=headNode;
        while (team!=null) {
            if (team.right == null) {//右侧没有了，说明这一层找到，没有只能下降
                team=team.down;
            }
            else if(team.right.ListIndex==ListIndex)//找到节点，右侧即为待删除节点
            {
                team.right=team.right.right;//删除右侧节点
                team=team.down;//向下继续查找删除
            }
            else if(team.right.ListIndex>ListIndex)//右侧已经不可能了，向下
            {
                team=team.down;
            }
            else { //节点还在右侧
                team=team.right;
            }
        }
    }
    public void add(SkipLinkNode node)
    {

        int ListIndex=node.ListIndex;
        SkipLinkNode findNode=search(ListIndex);
        if(findNode!=null)//如果存在这个ListIndex的节点
        {
            findNode.value=node.value;
            return;
        }

        Stack<SkipLinkNode>stack=new Stack<SkipLinkNode>();//存储向下的节点，这些节点可能在右侧插入节点
        SkipLinkNode team=headNode;//查找待插入的节点   找到最底层的哪个节点。
        while (team!=null) {//进行查找操作
            if(team.right==null)//右侧没有了，只能下降
            {
                stack.add(team);//将曾经向下的节点记录一下
                team=team.down;
            }
            else if(team.right.ListIndex>ListIndex)//需要下降去寻找
            {
                stack.add(team);//将曾经向下的节点记录一下
                team=team.down;
            }
            else //向右
            {
                team=team.right;
            }
        }

        int level=1;//当前层数，从第一层添加(第一层必须添加，先添加再判断)
        SkipLinkNode downNode=null;//保持前驱节点(即down的指向，初始为null)
        while (!stack.isEmpty()) {
            //在该层插入node
            team=stack.pop();//抛出待插入的左侧节点
            SkipLinkNode nodeTeam=new SkipLinkNode(node.ListIndex, node.value);//节点需要重新创建
            nodeTeam.down=downNode;//处理竖方向
            downNode=nodeTeam;//标记新的节点下次使用
            if(team.right==null) {//右侧为null 说明插入在末尾
                team.right=nodeTeam;
            }
            //水平方向处理
            else {//右侧还有节点，插入在两者之间
                nodeTeam.right=team.right;
                team.right=nodeTeam;
            }
            //考虑是否需要向上
            if(level>MAX_LEVEL)//已经到达最高级的节点啦
                break;
            double num=random.nextDouble();//[0-1]随机数
            if(num>0.5)//运气不好结束
                break;
            level++;
            if(level>highLevel)//比当前最大高度要高但是依然在允许范围内 需要改变head节点
            {
                highLevel=level;
                //需要创建一个新的节点
                SkipLinkNode highHeadNode=new SkipLinkNode(Integer.MIN_VALUE, null);
                highHeadNode.down=headNode;
                headNode=highHeadNode;//改变head
                stack.add(headNode);//下次抛出head
            }
        }

    }
    public void printList() {
        SkipLinkNode teamNode=headNode;
        int ListIndex=1;
        SkipLinkNode last=teamNode;
        while (last.down!=null){
            last=last.down;
        }
        while (teamNode!=null) {
            SkipLinkNode enumNode=teamNode.right;
            SkipLinkNode enumLast=last.right;
            System.out.printf("%-8s","head->");
            while (enumLast!=null&&enumNode!=null) {
                if(enumLast.ListIndex==enumNode.ListIndex)
                {
                    System.out.printf("%-5s",enumLast.ListIndex+"->");
                    enumLast=enumLast.right;
                    enumNode=enumNode.right;
                }
                else{
                    enumLast=enumLast.right;
                    System.out.printf("%-5s","");
                }

            }
            teamNode=teamNode.down;
            ListIndex++;
            System.out.println();
        }
    }
    public static void main(String[] args) {
        SkipLinkList<Integer>list=new SkipLinkList<Integer>();
        for(int i=1;i<20;i++)
        {
            list.add(new SkipLinkNode(i,666));
        }
        list.printList();
        list.delete(4);
        list.delete(8);
        list.printList();
    }
}