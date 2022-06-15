package com.test;

import java.util.Arrays;
import java.util.Scanner;
import java.util.*;
public class Sushu {

    private static final Map<Integer,Boolean> map = new HashMap<>();

    private static boolean isPrime(int n){
        Boolean aBoolean = map.get(n);
        if(aBoolean!=null){
            return aBoolean.booleanValue();
        }
        boolean res = true;
        for(int i=2;i<n;i++){
            if(n%i==0){
                res = false;
                break;
            }
        }
        map.put(n,res);
        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int end = num/2;
        int[] res = new int[2];
        Arrays.fill(res,-1);
        for(int i = 2;i<=end;i++){
            if(isPrime(i) && num%i==0){
                int left = num/i;
                if(isPrime(left)){
                    res[0] = Math.min(i,left);
                    res[1] = Math.max(i,left);
                    break;
                }
            }
        }
        System.out.println(res[0]+" "+res[1]);
    }
}
