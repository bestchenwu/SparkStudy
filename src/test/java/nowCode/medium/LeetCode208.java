package nowCode.medium;
//Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼
//写检查。
//
// 请你实现 Trie 类：
//
//
// Trie() 初始化前缀树对象。
// void insert(String word) 向前缀树中插入字符串 word 。
// boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回
//false 。
// boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否
//则，返回 false 。
//
//
//
//
// 示例：
//
//
//输入
//["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
//[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
//输出
//[null, null, true, false, true, null, true]
//
//解释
//Trie trie = new Trie();
//trie.insert("apple");
//trie.search("apple");   // 返回 True
//trie.search("app");     // 返回 False
//trie.startsWith("app"); // 返回 True
//trie.insert("app");
//trie.search("app");     // 返回 True
//
//
//
//
// 提示：
//
//
// 1 <= word.length, prefix.length <= 2000
// word 和 prefix 仅由小写英文字母组成
// insert、search 和 startsWith 调用次数 总计 不超过 3 * 10⁴ 次

import org.junit.Test;

class TreeNode {

    private TreeNode[] child;
    private boolean isEnd;

    public TreeNode() {
        this.child = new TreeNode[26];
        this.isEnd = false;
    }

    public boolean containsKey(char i) {
        return child[i - 'a'] != null;
    }

    public void put(char i) {
        if (containsKey(i)) {
            return;
        }
        child[i - 'a'] = new TreeNode();
    }

    public TreeNode get(char i) {
        return child[i - 'a'];
    }

    public void setIsEnd() {
        this.isEnd = true;
    }

    public boolean getIsEnd() {
        return this.isEnd;
    }

}

class Trie {

    private TreeNode root;

    public Trie() {
        this.root = new TreeNode();
    }

    public void insert(String word) {
        TreeNode tmp = root;
        for (int i = 0; i < word.length(); i++) {
            char item = word.charAt(i);
            if (!tmp.containsKey(item)) {
                tmp.put(item);
            }
            tmp = tmp.get(item);
        }
        tmp.setIsEnd();
    }

    private TreeNode searchPrefix(String prefix) {
        TreeNode tmp = root;
        for (int i = 0; i < prefix.length(); i++) {
            char item = prefix.charAt(i);
            if (!tmp.containsKey(item)) {
                return null;
            }
            tmp = tmp.get(item);
        }
        return tmp;
    }

    public boolean search(String word) {
        TreeNode treeNode = searchPrefix(word);
        return treeNode != null && treeNode.getIsEnd();
    }

    public boolean startsWith(String prefix) {
        TreeNode treeNode = searchPrefix(prefix);
        return treeNode != null;
    }
}

public class LeetCode208 {

    @Test
    public void testTrie() {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));   // 返回 True
        System.out.println(trie.search("app"));     // 返回 False
        System.out.println(trie.startsWith("app")); // 返回 True
        trie.insert("app");
        System.out.println(trie.search("app"));     // 返回 True
    }
}
