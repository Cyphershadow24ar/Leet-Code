// PROBLEM : -

// SOLUTION :

class Solution {
public:
    int numSubmat(vector<vector<int>>& mat) {
        int m = mat.size(), n = mat[0].size();
        vector<int> heights(n, 0);
        int ans = 0;

        for (int i = 0; i < m; i++) {
            // Build histogram heights for row i
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) heights[j] = 0;
                else heights[j] += 1;
            }

            // Count submatrices ending at row i using monotonic stack
            ans += countSubmatrices(heights);
        }
        return ans;
    }

    int countSubmatrices(vector<int>& heights) {
        int n = heights.size();
        vector<int> sum(n, 0);
        stack<int> st;
        int res = 0;

        for (int i = 0; i < n; i++) {
            while (!st.empty() && heights[st.top()] >= heights[i]) {
                st.pop();
            }

            if (!st.empty()) {
                int prev = st.top();
                sum[i] = sum[prev] + heights[i] * (i - prev);
            } else {
                sum[i] = heights[i] * (i + 1);
            }
            res += sum[i];
            st.push(i);
        }
        return res;
    }
};
