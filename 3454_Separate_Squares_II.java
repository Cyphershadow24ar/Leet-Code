// PROBLEM : (3454) Separate Squares II

// SOLUTION :

import java.util.*;

class Solution {

    static class Event {
        long y;
        int x1, x2;
        int type; // +1 = add, -1 = remove
        Event(long y, int x1, int x2, int type) {
            this.y = y;
            this.x1 = x1;
            this.x2 = x2;
            this.type = type;
        }
    }

    static class SegmentTree {
        int[] count;
        long[] length;
        long[] xs;

        SegmentTree(long[] xs) {
            this.xs = xs;
            int n = xs.length * 4;
            count = new int[n];
            length = new long[n];
        }

        void update(int node, int l, int r, int ql, int qr, int val) {
            if (qr <= l || r <= ql) return;
            if (ql <= l && r <= qr) {
                count[node] += val;
            } else {
                int mid = (l + r) / 2;
                update(node * 2, l, mid, ql, qr, val);
                update(node * 2 + 1, mid, r, ql, qr, val);
            }
            if (count[node] > 0) {
                length[node] = xs[r] - xs[l];
            } else if (l + 1 == r) {
                length[node] = 0;
            } else {
                length[node] = length[node * 2] + length[node * 2 + 1];
            }
        }

        long query() {
            return length[1];
        }
    }

    public double separateSquares(int[][] squares) {
        int n = squares.length;
        List<Event> events = new ArrayList<>();
        List<Long> xVals = new ArrayList<>();

        for (int[] s : squares) {
            long x1 = s[0];
            long y1 = s[1];
            long l = s[2];
            long x2 = x1 + l;
            long y2 = y1 + l;

            xVals.add(x1);
            xVals.add(x2);

            events.add(new Event(y1, 0, 0, +1));
            events.add(new Event(y2, 0, 0, -1));
        }

        Collections.sort(xVals);
        long[] xs = xVals.stream().distinct().mapToLong(Long::longValue).toArray();
        Map<Long, Integer> xIndex = new HashMap<>();
        for (int i = 0; i < xs.length; i++) xIndex.put(xs[i], i);

        int idx = 0;
        for (int[] s : squares) {
            long x1 = s[0];
            long l = s[2];
            int xi1 = xIndex.get(x1);
            int xi2 = xIndex.get(x1 + l);
            events.get(idx).x1 = xi1;
            events.get(idx).x2 = xi2;
            events.get(idx + 1).x1 = xi1;
            events.get(idx + 1).x2 = xi2;
            idx += 2;
        }

        events.sort(Comparator.comparingLong(e -> e.y));
        SegmentTree st = new SegmentTree(xs);

        // First pass: compute total area
        long prevY = events.get(0).y;
        double totalArea = 0;
        for (Event e : events) {
            long dy = e.y - prevY;
            totalArea += dy * st.query();
            st.update(1, 0, xs.length - 1, e.x1, e.x2, e.type);
            prevY = e.y;
        }

        // Second pass: find split line
        st = new SegmentTree(xs);
        prevY = events.get(0).y;
        double curArea = 0;
        double half = totalArea / 2.0;

        for (Event e : events) {
            long dy = e.y - prevY;
            double nextArea = curArea + dy * st.query();
            if (nextArea >= half) {
                return prevY + (half - curArea) / st.query();
            }
            curArea = nextArea;
            st.update(1, 0, xs.length - 1, e.x1, e.x2, e.type);
            prevY = e.y;
        }

        return prevY;
    }
}
