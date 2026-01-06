// PROBLEM : (1161) Maximum level Sum of BInary Tree

// SOLUTION :

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int max = Integer.MIN_VALUE;
    int levelMax = 0;
    public int maxLevelSum(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        bfsMaxLevel(queue, 1);
        return levelMax;
    }


    void bfsMaxLevel(LinkedList<TreeNode> queue, int level) {
        if (queue.isEmpty()) {
            return;
        }
        int count = 0;
        LinkedList<TreeNode> queueLeaf = new LinkedList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.removeLast();
            count += node.val;
            if (node.left != null ) {
                queueLeaf.addFirst(node.left);
            }
            if (node.right != null ) {
                queueLeaf.addFirst(node.right);
            }
        }
        if (count > max) {
            levelMax = level;
            max = count;
        }
        bfsMaxLevel(queueLeaf, level+1);
    }
}
