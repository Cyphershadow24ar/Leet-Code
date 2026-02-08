// PROBLEM : (110) Balanced Binary Tree

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
    public boolean isBalanced(TreeNode root) {
        // If checkHeight returns -1, it means the tree is unbalanced
        return checkHeight(root) != -1;
    }

    private int checkHeight(TreeNode node) {
        if (node == null) return 0;

        // Check left subtree
        int leftHeight = checkHeight(node.left);
        if (leftHeight == -1) return -1; // Already unbalanced

        // Check right subtree
        int rightHeight = checkHeight(node.right);
        if (rightHeight == -1) return -1; // Already unbalanced

        // Check if current node is balanced
        // The difference between heights must be <= 1
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        // Return the actual height if balanced
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
