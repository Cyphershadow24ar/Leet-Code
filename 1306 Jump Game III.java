// PROBLEM : (1306) Jump Game III

// SOLUTION :


class Solution {
    public boolean canReach(int[] arr, int start) {
        int n = arr.length;

        boolean[] visited = new boolean[n];
        Queue<Integer> q = new ArrayDeque<>();

        q.offer(start);

        while (!q.isEmpty()) {
            int i = q.poll();

            if (i < 0 || i >= n || visited[i])
                continue;

            if (arr[i] == 0)
                return true;

            visited[i] = true;

            q.offer(i + arr[i]);
            q.offer(i - arr[i]);
        }

        return false;
    }
}
