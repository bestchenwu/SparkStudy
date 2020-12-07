package leetCode.medium;

import org.junit.Test;

import java.util.Stack;

public class CharToStringTest {

    @Test
    public void testCharToString(){
       Stack<Character> stack = new Stack<>();
        stack.push('a');
        stack.push('b');
        stack.push('c');
       StringBuilder sb = new StringBuilder();
        stack.forEach(char1->sb.append(char1));
        System.out.println(sb.toString());
    }
}
