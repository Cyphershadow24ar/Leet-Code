// PROBLEM : (1382) Balance a Binary Search Tree

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
    private List<TreeNode> inorder = new ArrayList<>();

    public TreeNode balanceBST(TreeNode root) {
        // Step 1: store inorder traversal
        inorderTraversal(root);

        // Step 2: build balanced BST
        return buildBalancedBST(0, inorder.size() - 1);
    }

    private void inorderTraversal(TreeNode root) {
        if (root == null) return;
        inorderTraversal(root.left);
        inorder.add(root);
        inorderTraversal(root.right);
    }

    private TreeNode buildBalancedBST(int start, int end) {
        if (start > end) return null;

        int mid = start + (end - start) / 2;
        TreeNode root = inorder.get(mid);

        root.left = buildBalancedBST(start, mid - 1);
        root.right = buildBalancedBST(mid + 1, end);

        return root;
    }
}
