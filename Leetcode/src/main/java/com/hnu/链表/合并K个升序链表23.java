package com.hnu.链表;

public class 合并K个升序链表23 {


    public ListNode help(ListNode[] lists,int start,int end) {
        //3 4
        //3 45
        int mid = start + (end - start) / 2;
        ListNode l=null;
        ListNode r=null;
        if (start+2 < end) {
            l = help(lists, start, mid);
            r = help(lists, mid + 1, end);
        }
        else
            if (start+2 == end) {

                l = help(lists, start, mid);
                r=lists[end];
            }
        else
            if (start+1 == end)
        {
            l = lists[start];
            r=lists[end];
        }
            else
        if (start == end)
            return lists[start];
        ListNode mergeHead=null;
        ListNode head=null;
        if(l==null&&r!=null) return r;
        else if(l!=null&&r==null) return l;
        else if(l!=null&&r!=null) {
            while (l != null && r != null) {

                if (l.val < r.val)
                {
                    System.out.println();
                    ListNode   temp =l;
                    l=l.next;
                    temp.next=null;
                    if(head==null)
                    {
                        head=temp;
                        mergeHead=head;
                    }
                    else
                    {
                        head.next=temp;
                        head=head.next;
                    }
                }
                else
                {
                    ListNode   temp =r;
                    r=r.next;
                    temp.next=null;
                    if(head==null)
                    {
                        head=temp;
                        mergeHead=head;
                    }
                    else
                    {
                        head.next=temp;
                        head=head.next;
                    }

                }
            }
            if(l==null&&r!=null)
                head.next=r;
            else
                if(l!=null&&r==null)
                head.next=l;
                return mergeHead;
            }

        return mergeHead;
    }
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head=null;
        if (lists.length>0)
            head =help(lists,0,lists.length-1);
        return head;
    }


}
