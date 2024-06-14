def bruteforce_mask_string(s):
    possibilities = ['ABCDEFGHIJKLMNOPQRSTUVWXYZ']
    length = len(s)
    for i in range(length):
        if s[i] == '$':
            new_possibilities = []
            for possibility in possibilities:
                for char in possibility:
                    new_possibility = possibility[:i] + char + possibility[i+1:]
                    new_possibilities.append(new_possibility)
            possibilities = new_possibilities
    return possibilities

# Example usage:
encrypted_string = "$*$D$_$D$N$"
possible_strings = bruteforce_mask_string(encrypted_string)
print("Possible original strings:")
for s in possible_strings:
    print(s)


#wonder man
#wolverine
#ms marvel

# $*$*$_$a$v$l$0$3
# $*$D$_$D$N$*$M$r$e$2$2$