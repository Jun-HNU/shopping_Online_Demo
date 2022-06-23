package com.hnu.链表;

public class 删除链表的倒数第n个节点19 {



    /*
     a,b,c,d,e

     n=2 ,

     */

    class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {

            int lead = 0;
            //常规操作，保存头节点的引用
            ListNode h = head;

            ListNode f = null;
            ListNode targetNode;
            while (head != null) {
                //当head节点移动了n个单位时，f节点开始移动


                head = head.next;
                if (f != null && head != null) f = f.next;
                lead++;
                if (lead == n) f = h;


            }

            if(lead==n)
            {
                ListNode l=h.next;
                h.next=null;
                return l;
            }
            //当h移动到尾端时，f移动到了倒数第n+1个单位的位置
            //倒数第n个节点为f.next,
            if (f.next != null) {
                targetNode = f.next;//将要移除的节点记录下来
                //这里江f.next的值进行覆盖，为f.next.next,此时倒数
                if (f.next.next != null)
                    f.next = f.next.next;
                else
                {
                    f.next=null;
                }
                //此时target的下一个节点仍然有节点，需要将其赋值为空，断掉它与链表的联系
                targetNode.next = null;
            }
            //返回链表的头节点
            return h;

        }

      //方法二，递归，
            int i=0;
            public  ListNode removeNthFromEnd2(ListNode head, int n) {
                if(head.next!=null)
                    head.next=removeNthFromEnd2(head.next,n);
                i++;
                if(i==n)
                    return head.next;
                else return head;
            }
        }

}
