import java.util.*;

/*
 * @lc app=leetcode.cn id=130 lang=java
 *
 * [130] 被围绕的区域
 *
 * https://leetcode-cn.com/problems/surrounded-regions/description/
 *
 * algorithms
 * Medium (34.68%)
 * Total Accepted:    3.7K
 * Total Submissions: 10.6K
 * Testcase Example:  '[["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]'
 *
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 * 
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 * 
 * 示例:
 * 
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * 
 * 
 * 运行你的函数后，矩阵变为：
 * 
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * 
 * 
 * 解释:
 * 
 * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O'
 * 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 * 
 */
class Solution {
    public void solve(char[][] board) {
        if (board.length <= 1)
            return;
        if (board[0].length <= 1)
            return;
        int rowLen = board.length;
        int colLen = board[0].length;
        boolean[][] bound = new boolean[rowLen][colLen];
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < rowLen; i++) {
            if (board[i][0] == 'O') {
                bound[i][0] = true;
                q.offer(i * colLen);
            }
            if (board[i][colLen - 1] == 'O') {
                bound[i][colLen - 1] = true;
                q.offer(i * colLen + colLen - 1);
            }
        }
        for (int i = 0; i < colLen; i++) {
            if (board[0][i] == 'O') {
                bound[0][i] = true;
                q.offer(i);
            }
            if (board[rowLen - 1][i] == 'O') {
                bound[rowLen - 1][i] = true;
                q.offer(rowLen * colLen - colLen + i);
            }
        }
        while (!q.isEmpty()) {
            int index = q.poll();
            int indexI = index / colLen;
            int indexJ = index % colLen;
            if (indexI > 0)
                if (!bound[indexI - 1][indexJ]&&board[indexI - 1][indexJ] == 'O') {
                    bound[indexI - 1][indexJ] = true;
                    q.offer(index - colLen);
                }
            if (indexI < rowLen - 1)
                if (!bound[indexI + 1][indexJ]&&board[indexI + 1][indexJ] == 'O') {
                    bound[indexI + 1][indexJ] = true;
                    q.offer(index + colLen);
                }
            if (indexJ > 0)
                if (!bound[indexI][indexJ - 1]&&board[indexI][indexJ - 1] == 'O') {
                    bound[indexI][indexJ - 1] = true;
                    q.offer(index - 1);
                }
            if (indexJ < colLen - 1)
                if (!bound[indexI][indexJ + 1]&&board[indexI][indexJ + 1] == 'O') {
                    bound[indexI][indexJ + 1] = true;
                    q.offer(index + 1);
                }
        }
        for (int i = 1; i < rowLen - 1; i++)
            for (int j = 1; j < colLen; j++) {
                if (!bound[i][j])
                    board[i][j] = 'X';
            }
    }
}
