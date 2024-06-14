from sympy import mod_inverse
from out import *
from random import randint
from re import search
def find_flag(p, output):
    for i in range(2**39, 2**40):  # Brute force possible flag values
        flag = "bi0sctf{%s}" % hex(i)[2:]  # Format the flag

        # Compute unknowns list
        unknowns = [f + i - (i % 1000) for i, f in zip(unknowns, search("{(.*)}", flag).group(1).encode())]

        # Compute the output based on the current flag
        computed_output = []
        for _ in range(100):
            aa = [randint(0, 2**1024) for _ in range(1000)]
            bb = [randint(0, 9) for _ in range(1000)]
            cc = [randint(0, 9) for _ in range(1000)]
            computed_output.append(sum([a + unknowns[b]^2 * unknowns[c]^3 for a, b, c in zip(aa, bb, cc)]) % p)

        # Check if the computed output matches the provided output
        if computed_output == output:
            return flag  # Return the flag if match found

# Call the function to find the flag
flag = find_flag(p, output)
print("Flag:", flag)
