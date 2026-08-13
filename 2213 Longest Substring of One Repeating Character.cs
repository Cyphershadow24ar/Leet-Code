// PROBLEM : (2213) Longest Substring of One Repeating Character

// SOLUTION :

public class Solution {
    private char[] sArr;
    private int[] pre, suf, maxLen;
    private char[] leftChar, rightChar;

    public int[] LongestRepeating(string s, string queryCharacters,
                                  int[] queryIndices) {
        int n = s.Length;
        sArr = s.ToCharArray();
        pre = new int[4 * n];
        suf = new int[4 * n];
        maxLen = new int[4 * n];
        leftChar = new char[4 * n];
        rightChar = new char[4 * n];

        Build(1, 0, n - 1);
        int k = queryIndices.Length;
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            Update(1, 0, n - 1, queryIndices[i], queryCharacters[i]);
            ans[i] = maxLen[1];
        }
        return ans;
    }

    private void PushUp(int u, int l, int r) {
        int mid = (l + r) >> 1;
        int leftLen = mid - l + 1, rightLen = r - mid;
        int left = u << 1, right = u << 1 | 1;
        leftChar[u] = leftChar[left];
        rightChar[u] = rightChar[right];
        pre[u] = pre[left];
        if (pre[left] == leftLen && rightChar[left] == leftChar[right]) {
            pre[u] = pre[left] + pre[right];
        }
        suf[u] = suf[right];
        if (suf[right] == rightLen && rightChar[left] == leftChar[right]) {
            suf[u] = suf[right] + suf[left];
        }
        maxLen[u] = Math.Max(maxLen[left], maxLen[right]);
        if (rightChar[left] == leftChar[right]) {
            maxLen[u] = Math.Max(maxLen[u], suf[left] + pre[right]);
        }
    }

    private void Build(int u, int l, int r) {
        if (l == r) {
            pre[u] = 1;
            suf[u] = 1;
            maxLen[u] = 1;
            leftChar[u] = sArr[l];
            rightChar[u] = sArr[l];
            return;
        }
        int mid = (l + r) >> 1;
        Build(u << 1, l, mid);
        Build(u << 1 | 1, mid + 1, r);
        PushUp(u, l, r);
    }

    private void Update(int u, int l, int r, int pos, char ch) {
        if (l == r) {
            leftChar[u] = ch;
            rightChar[u] = ch;
            return;
        }
        int mid = (l + r) >> 1;
        if (pos <= mid) {
            Update(u << 1, l, mid, pos, ch);
        } else {
            Update(u << 1 | 1, mid + 1, r, pos, ch);
        }
        PushUp(u, l, r);
    }
}
