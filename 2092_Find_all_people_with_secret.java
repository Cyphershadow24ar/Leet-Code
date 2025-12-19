// PROBLEM : (2092) Find All People With Secret

// SOLUTION :

import java.util.*;

class Solution {
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {

        // People who know the secret
        boolean[] knows = new boolean[n];
        knows[0] = true;
        knows[firstPerson] = true;

        // Sort meetings by time
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[2], b[2]));

        int i = 0;
        while (i < meetings.length) {
            int time = meetings[i][2];

            // Graph for this specific time
            Map<Integer, List<Integer>> graph = new HashMap<>();
            Set<Integer> people = new HashSet<>();

            // Collect all meetings at the same time
            while (i < meetings.length && meetings[i][2] == time) {
                int x = meetings[i][0];
                int y = meetings[i][1];

                graph.computeIfAbsent(x, k -> new ArrayList<>()).add(y);
                graph.computeIfAbsent(y, k -> new ArrayList<>()).add(x);

                people.add(x);
                people.add(y);
                i++;
            }

            // BFS for each connected component
            Set<Integer> visited = new HashSet<>();
            for (int person : people) {
                if (visited.contains(person)) continue;

                Queue<Integer> q = new LinkedList<>();
                List<Integer> component = new ArrayList<>();
                boolean hasSecret = false;

                q.offer(person);
                visited.add(person);

                while (!q.isEmpty()) {
                    int cur = q.poll();
                    component.add(cur);

                    if (knows[cur]) hasSecret = true;

                    for (int nei : graph.getOrDefault(cur, Collections.emptyList())) {
                        if (!visited.contains(nei)) {
                            visited.add(nei);
                            q.offer(nei);
                        }
                    }
                }

                // If anyone in component knows the secret, spread it
                if (hasSecret) {
                    for (int p : component) {
                        knows[p] = true;
                    }
                }
            }
        }

        // Collect result
        List<Integer> result = new ArrayList<>();
        for (int p = 0; p < n; p++) {
            if (knows[p]) result.add(p);
        }

        return result;
    }
}
