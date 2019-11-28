package leetCode.medium;

/**
 * https://leetcode-cn.com/problems/spiral-matrix-ii/
 *
 * 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
 *
 * 示例:
 *
 * 输入: 3
 * 输出:
 * [
 *  [ 1, 2, 3 ],
 *  [ 8, 9, 4 ],
 *  [ 7, 6, 5 ]
 * ]
 *
 * 输入4，则输出:
 * [
 *  [1,2,3,4],
 *  [12,13,14,5],
 *  [11,16,15,6],
 *  [10,9,8,7]
 * ]
 *
 */
public class LeetCode59 {

    private void output(int[][] nums,int row,int column,int rowUpdate,int columnUpdate,int index,int n){
        //最开始columnUpdate为1,rowUpdate为0
        //如果遇到了column达到极限,同时row还在第一行,则columnUpdate不动,rowUpdate+1
        if(index<=n*n){
            nums[row][column]=index;
            if(rowUpdate!=0){
                int rowTemp=row+rowUpdate;
                if(rowTemp==n||nums[rowTemp][column]!=0){
                    //说明到达了数组的拐角
                    //尝试性先将column+1,判断是否存在值,如果存在则回溯
                    int columnUpdateTemp=column+1;
                    if(columnUpdateTemp<n&&nums[row][columnUpdateTemp]==0){
                        columnUpdate=1;
                    }else{
                        columnUpdate=-1;
                    }
                    rowUpdate=0;
                }
            }else{
                int columnTemp = column+columnUpdate;
                if(columnTemp==n || columnTemp == -1 || nums[row][columnTemp]!=0 ){
                    //说明达到了数组的拐角
                    int rowUpdateTemp = row+1;
                    if(rowUpdateTemp<n&&nums[rowUpdateTemp][column]==0){
                        rowUpdate = 1;
                    }else{
                        rowUpdate=-1;
                    }
                    columnUpdate=0;
                }
            }
            row = row+rowUpdate;
            column = column+columnUpdate;
            index+=1;
            output(nums,row,column,rowUpdate,columnUpdate,index,n);

        }
    }

    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        output(result,0,0,0,1,1,n);
        return result;
    }

    public static void main(String[] args) {
        LeetCode59 leetCode59 = new LeetCode59();
        int[][] results = leetCode59.generateMatrix(4);
        System.out.println(results);
    }
}
