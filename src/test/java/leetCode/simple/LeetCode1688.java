package leetCode.simple;

import org.junit.Test;

//给你一个整数 n ，表示比赛中的队伍数。比赛遵循一种独特的赛制：
//
//
// 如果当前队伍数是 偶数 ，那么每支队伍都会与另一支队伍配对。总共进行 n / 2 场比赛，且产生 n / 2 支队伍进入下一轮。
// 如果当前队伍数为 奇数 ，那么将会随机轮空并晋级一支队伍，其余的队伍配对。总共进行 (n - 1) / 2 场比赛，且产生 (n - 1) / 2 + 1
// 支队伍进入下一轮。
//
//
// 返回在比赛中进行的配对次数，直到决出获胜队伍为止。
//
//
//
// 示例 1：
//
// 输入：n = 7
//输出：6
//解释：比赛详情：
//- 第 1 轮：队伍数 = 7 ，配对次数 = 3 ，4 支队伍晋级。
//- 第 2 轮：队伍数 = 4 ，配对次数 = 2 ，2 支队伍晋级。
//- 第 3 轮：队伍数 = 2 ，配对次数 = 1 ，决出 1 支获胜队伍。
//总配对次数 = 3 + 2 + 1 = 6
//
//
// 示例 2：
//
// 输入：n = 14
//输出：13
//解释：比赛详情：
//- 第 1 轮：队伍数 = 14 ，配对次数 = 7 ，7 支队伍晋级。
//- 第 2 轮：队伍数 = 7 ，配对次数 = 3 ，4 支队伍晋级。
//- 第 3 轮：队伍数 = 4 ，配对次数 = 2 ，2 支队伍晋级。
//- 第 4 轮：队伍数 = 2 ，配对次数 = 1 ，决出 1 支获胜队伍。
//总配对次数 = 7 + 3 + 2 + 1 = 13
public class LeetCode1688 {

    private int count = 0;

    /**
     * num表示队伍数,返回结果表示配对次数
     *
     * @param num
     * @return
     */
    private void helpNumberOfMatches(int num) {
        if (num == 1) {
            return;
        }
        if (num == 2) {
            count = count + 1;
            return;
        }
        count += num / 2;
        helpNumberOfMatches(num % 2 == 0 ? num / 2 : num / 2 + 1);
    }

    public int numberOfMatches(int n) {
        helpNumberOfMatches(n);
        return count;
    }

    @Test
    public void testNumberOfMatches() {
        int n = 14;
        int result = numberOfMatches(n);
        System.out.println("result=" + result);
    }
}
