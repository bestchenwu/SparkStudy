package com.test;

import java.util.Scanner;

public class Zichuan {

    /**
     * s1表示在S的开始位置,l1表示在L中的开始位置
     *
     * @param S
     * @param L
     * @return
     */
    public static boolean isMatch(String S,String L){
        int s1 = 0,l1 = 0;
        while(s1<S.length() && l1<L.length()){
            if(S.charAt(s1)==L.charAt(l1)){
                s1+=1;
                l1+=1;
            }else{
                //l1往后找,看能不能找到位置
                char char1 = S.charAt(s1);
                while(l1<L.length() && L.charAt(l1)!=char1){
                    l1+=1;
                }
                s1+=1;
                l1+=1;
            }
        }
        if(s1>=S.length()&&l1<=L.length()) {
            return true;
        }else{
            return false;
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String S = scanner.nextLine();
        String L = scanner.nextLine();
        int res = -1;
        if(S.length()<=L.length()){
            int s1 = 0;
            for(;s1<S.length();s1++){
                boolean flag = isMatch(S,L);
                if(flag){
                    res = L.lastIndexOf(S.charAt(S.length()-1));
                    break;
                }
            }
        }
        System.out.println(res);
    }
}
