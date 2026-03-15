// PROBLEM : (1622) Fancy Sequence

// SOLUTION :

import java.util.*;

class Fancy {

    long mod = 1000000007;
    ArrayList<Long> list;
    long mul;
    long add;

    public Fancy() {
        list = new ArrayList<>();
        mul = 1;
        add = 0;
    }

    public void append(int val) {
        long v = (val - add + mod) % mod;
        v = (v * modInverse(mul)) % mod;
        list.add(v);
    }

    public void addAll(int inc) {
        add = (add + inc) % mod;
    }

    public void multAll(int m) {
        mul = (mul * m) % mod;
        add = (add * m) % mod;
    }

    public int getIndex(int idx) {
        if (idx >= list.size()) return -1;

        long val = list.get(idx);
        val = (val * mul + add) % mod;

        return (int) val;
    }

    private long modInverse(long x) {
        return power(x, mod - 2);
    }

    private long power(long x, long y) {
        long res = 1;
        x %= mod;

        while (y > 0) {
            if ((y & 1) == 1)
                res = (res * x) % mod;

            x = (x * x) % mod;
            y >>= 1;
        }

        return res;
    }
}
