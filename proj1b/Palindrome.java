import java.util.LinkedList;

public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> deque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            deque.addLast(word.charAt(i));
        }
        return deque;

    }
    public boolean isPalindrome(String word){
        Deque<Character> deque = (LinkedListDeque<Character>)wordToDeque(word);
        int i=0, l=word.length();
        while(i<l/2){
            if(word.charAt(i)!=word.charAt(l-i-1)){
                return false;
            }
            i++;
        }
        return true;
    }
    public boolean isPalindrome(String word, CharacterComparator cc){
        cc=new OffByOne();
        Deque<Character> deque = (LinkedListDeque<Character>)wordToDeque(word);
        int i=0, l=word.length();
        while(i<l/2){
            if(!cc.equalChars(deque.get(i), deque.get(l-i-1)))
            return false;
            i++;
        }
        return true;
    }

}
