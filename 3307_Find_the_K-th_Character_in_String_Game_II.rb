# PROBLEM 3307 - Find the K-th Character in String Game II

# SOLUTION :

# @param {Integer} k
# @param {Integer[]} operations
# @return {Character}
def kth_character(k, operations)
  lengths = [1]

  # Step 1: Compute lengths of the word after each operation
  operations.each do |op|
    next_length = lengths[-1] * 2
    lengths << next_length
    break if next_length >= k
  end

  shift = 0
  i = lengths.length - 1

  while i > 0
    prev_length = lengths[i - 1]
    op = operations[i - 1]

    if op == 0
      k -= prev_length if k > prev_length
    else # op == 1
      if k > prev_length
        k -= prev_length
        shift += 1
      end
    end
    i -= 1
  end

  # Final character is 'a' shifted by `shift`, wrapping around after 'z'
  ((('a'.ord - 'a'.ord + shift) % 26) + 'a'.ord).chr
end
