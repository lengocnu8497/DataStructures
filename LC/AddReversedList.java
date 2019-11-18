/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l3 = null;
        int temp = 0;
        int carry = 0;
        while(l1 != null || l2 != null)
        {
            
            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;
            temp = (carry != 0) ? x + y + carry : x + y; 
            carry = temp / 10;
            temp %= 10;
            if(l3 == null)
                l3 = new ListNode(temp);
            else l3.next = new ListNode(temp);
            l1 = l1.next;
            l2 = l2.next;
        }
        // case 99 + 10
        if(carry > 0)
            l3.next = new ListNode(carry);
        return l3;
    }
}