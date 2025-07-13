// PROBLEM - (2410) Maximum Matching of Players With Trainers

// SOLUTION :-

import "sort"

func matchPlayersAndTrainers(players []int, trainers []int) int {
    sort.Ints(players)
    sort.Ints(trainers)

    match := 0
    i, j := 0, 0

    for i < len(players) && j < len(trainers) {
        if players[i] <= trainers[j] {
            match++
            i++
            j++
        } else {
            j++ // Current trainer too weak, try next
        }
    }

    return match
}
