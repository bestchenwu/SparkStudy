package com.test;

import java.util.Scanner;

public class Juyuwang {

    private static boolean[][] visited;

    private static int dfs(int[][] array,int row,int col,int n,int m){
        if(row>=n || col>=m){
            return 0;
        }else if(row<0 || col<0 ){
            return 0;
        }else{
//            if(row==n-1){
//                int count = 0;
//                while(col<m && array[row][col]==1){
//                    count+=1;
//                    col+=1;
//                }
//                return count;
//            }else if(col==m-1){
//                int count = 0;
//                while(row<n && array[row][col]==1){
//                    count+=1;
//                    row+=1;
//                }
//                return count;
//            }else{
//
//            }
            if(array[row][col]==0 || visited[row][col]){
                return 0;
            }
            visited[row][col] = true;
            return 1+dfs(array,row,col+1,n,m)+dfs(array,row+1,col,n,m)+dfs(array,row-1,col,n,m)+dfs(array,row,col-1,n,m);
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //n代表行数
        int n = scanner.nextInt();
        //m代表列数
        int m = scanner.nextInt();
        int[][] array = new int[n][m];
        for(int row = 0;row<n;row++){
            for(int col = 0;col<m;col++){
                array[row][col] = scanner.nextInt();
            }
        }

        int max = 0;
        int row = 0,col = 0;
        for(;row<n&&col<m;row+=1,col+=1){
            //初始化visited
            visited = new boolean[n][m];
            for(int i = 0;i<n;i++){
                for(int j = 0;j<m;j++){
                    visited[i][j] = false;
                }
            }
            max = Math.max(dfs(array,row,col,n,m),max);
        }
        System.out.println(max);
    }
}
