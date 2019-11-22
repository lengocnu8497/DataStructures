class Solution {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> ss = new HashSet<>();
        int n = s.length();
        int i = 0, j = 0, maxLength = 0;
        // expand i and j
        while(i < n && j < n)
        {
            if(!ss.contains(s.charAt(j))) // if ss doesnt contain char, add char to set and               // move j forward and update length of substring
            {
                ss.add(s.charAt(j++));
                // update ss length
                maxLength = Math.max(maxLength, j-i);
            }
            else // if ss doesnt contain char -> char is repeated
            //meaning i and j has the sam char -> move the cursor at i up by removing char at             //i in ss
                ss.remove(s.charAt(i++));
        }
        return maxLength;
    }
}