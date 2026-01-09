// PROBLEM : (865) Smallest Subtree With all the Deepest Nodes

// SOLUTION :

class Solution {
public:
    TreeNode* subtreeWithAllDeepest(TreeNode* root) {
        return dfs(root).second;
    }

private:
    pair<int, TreeNode*> dfs(TreeNode* root) {
        if (!root) return {0, nullptr};

        auto left = dfs(root->left);
        auto right = dfs(root->right);

        if (left.first > right.first)
            return {left.first + 1, left.second};
        if (right.first > left.first)
            return {right.first + 1, right.second};

        // equal depth → current node is LCA of deepest nodes
        return {left.first + 1, root};
    }
};
