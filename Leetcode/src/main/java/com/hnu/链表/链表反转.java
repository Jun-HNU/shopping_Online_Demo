package com.hnu.链表;



public class 链表反转 {



    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {


        public ListNode reverseList(ListNode head)
        {//

            if(head==null)  return null;//当链表为空时，直接返回

            if(head!=null&&head.next!=null)//只有当节点个数不小于2时，才会进入这里。
            //假设嵌套和函数栈的最李里面的一层，
            //当前head为倒数第二个节点
            {
                ListNode end =reverseList(head.next);
                //此时end即为head.next 所对应的节点为，
                // 将其作为新链表的头节点，
                head.next.next=head;//head反转为新链表的第二个节点
                head.next=null;//第三个节点为空
                return end;//返回头节点
            }
            return head;//当链表只有一个元素，直接返回，或者递归到最后一个元素时，直接返回

        }

        public ListNode reverseList2(ListNode head) {
            ListNode cur=head;
            ListNode   last=null;
            if(cur==null||cur.next==null)
            {
                return cur;
            }

            if (null!=cur){

                last=reverseList(cur.next);
                cur.next.next=cur;
                cur.next=null;
                return last;


            }
            return last;
        }
    }
}
