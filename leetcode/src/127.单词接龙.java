import java.util.*;

/*
 * @lc app=leetcode.cn id=127 lang=java
 *
 * [127] 单词接龙
 *
 * https://leetcode-cn.com/problems/word-ladder/description/
 *
 * algorithms
 * Medium (31.91%)
 * Total Accepted:    4K
 * Total Submissions: 12.4K
 * Testcase Example:  '"hit"\n"cog"\n["hot","dot","dog","lot","log","cog"]'
 *
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord
 * 的最短转换序列的长度。转换需遵循如下规则：
 * 
 * 
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * 
 * 
 * 说明:
 * 
 * 
 * 如果不存在这样的转换序列，返回 0。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * 
 * 
 * 示例 1:
 * 
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * 
 * 输出: 5
 * 
 * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * ⁠    返回它的长度 5。
 * 
 * 
 * 示例 2:
 * 
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * 
 * 输出: 0
 * 
 * 解释: endWord "cog" 不在字典中，所以无法进行转换。
 * 
 */
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList.contains(endWord)) {
            boolean[] visited = new boolean[wordList.size()];
            for (boolean v : visited)
                v = false;
            Queue<String> q = new LinkedList<>();
            q.offer(beginWord);
            int cnt = 1;
            while (!q.isEmpty()) {
                cnt++;
                for (int i = q.size(); i > 0; i--) {
                    beginWord = q.poll();
                    for (int j = 0; j < wordList.size(); j++) {
                        String str = wordList.get(j);
                        if (!visited[j] && com(beginWord, str)) {
                            if (str.equals(endWord))
                                return cnt;
                            q.offer(str);
                            visited[j] = true;
                        }
                    }
                }
            }
            return 0;
        }
        return 0;
    }

    private boolean com(String beginWord, String str) {
        boolean chance = true;
        for (int i = 0; i < beginWord.length(); i++) {
            if (beginWord.charAt(i) != str.charAt(i))
                if (chance)
                    chance = false;
                else
                    return false;
        }
        return true;
    }
}
