public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        int len = word.length();
        if (len == 0){return null;}
        Deque<Character> deque = new LinkedListDeque<>();
        for (int i = 0; i < len; i++){
            deque.addLast(word.charAt(i));
        }
        return deque;
    }
    public boolean isPalindrome(String word){
        if (word.length()==0){return true;}
        int left = 0;
        int right = word.length()-1;
        while(left < right){
            if (word.charAt(left) != word.charAt(right)){return false;}
            left ++;
            right --;
        }
        return true;
    }
    public boolean isPalindrome(String word, CharacterComparator cc){

        int len = word.length();
        if (len<2){return true;}
        for (int i= 0; i < len/2; i++){
            if (!cc.equalChars(word.charAt(i),word.charAt(len-1-i))){return false;}

        }
        return true;

    }
}
