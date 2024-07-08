PERM = [2, 0, 1, 3, 9, 7, 4, 10, 5, 14, 6, 11, 12, 8, 13, 15]
REVERSE_PERM = [PERM.index(i) for i in range(16)]

def apply_reverse_perm(s):
    return ''.join(s[REVERSE_PERM[i]] for i in range(16))

censored_text = "owuwspdgrtejiiud"
original_text = apply_reverse_perm(censored_text)
print(original_text)
