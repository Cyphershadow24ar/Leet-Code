
# PROBLEM :- (1900) The Earliest and Latest Rounds Where Players Compete

class Solution(object):
    def earliestAndLatest(self, n, firstPlayer, secondPlayer):
        """
        :type n: int
        :type firstPlayer: int
        :type secondPlayer: int
        :rtype: List[int]
        """
        firstPlayer -= 1
        secondPlayer -= 1
        memo = {}

        def dfs(players, round_num):
            key = (tuple(sorted(players)), round_num)
            if key in memo:
                return memo[key]

            m = len(players)

            # Base case: do they meet this round?
            for i in range(m // 2):
                if {players[i], players[m - 1 - i]} == {firstPlayer, secondPlayer}:
                    return (round_num, round_num)

            results = []

            def simulate(i, current):
                if i >= m // 2:
                    if m % 2 == 1:
                        current.append(players[m // 2])
                    results.append(tuple(sorted(current)))
                    if m % 2 == 1:
                        current.pop()
                    return

                a = players[i]
                b = players[m - 1 - i]

                if a in (firstPlayer, secondPlayer):
                    current.append(a)
                    simulate(i + 1, current)
                    current.pop()
                elif b in (firstPlayer, secondPlayer):
                    current.append(b)
                    simulate(i + 1, current)
                    current.pop()
                else:
                    current.append(a)
                    simulate(i + 1, current)
                    current.pop()
                    current.append(b)
                    simulate(i + 1, current)
                    current.pop()

            simulate(0, [])

            min_r, max_r = float('inf'), float('-inf')
            for nxt in results:
                mn, mx = dfs(nxt, round_num + 1)
                min_r = min(min_r, mn)
                max_r = max(max_r, mx)

            memo[key] = (min_r, max_r)
            return memo[key]

        return list(dfs(tuple(range(n)), 1))
