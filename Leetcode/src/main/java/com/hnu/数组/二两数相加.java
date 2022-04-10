package com.hnu.数组;
class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }
public class 二两数相加 {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode cur=pre ;
        int puls=0;
        while(l1!=null||l2!=null) {
            int val1=0,val2=0;
            if(l1!=null)
            {
                val1=l1.val;
            }
            if(l2!=null)
            {
                val2=l2.val;
            }
            int sum = val1 + val2 + puls;
            puls = sum / 10;
            int val = sum % 10;

            cur.next=new ListNode(val);
            cur=cur.next;
            if(l1!=null)
            {
                l1=l1.next;
            }
            else{
                l1=null;
            }


            if(l2!=null)
            {
                l2=l2.next;
            }else
            {
                l2=null;
            }
        }
        if (puls!=0)
            cur.next=new ListNode(puls);
        //cur=cur.next;
        return pre.next;

    }
    public static void main(String[] args) {

       
    }
}
