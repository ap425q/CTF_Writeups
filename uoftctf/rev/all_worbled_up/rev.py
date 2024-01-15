def worble(s):
    s1 = 5
    s2 = 31

    for n in range(len(s)):
        s1 = (s1 + ord(s[n]) + 7) % 65521
        s2 = (s1 * s2) % 65521

    return (s2 << 16) | s1

def shmorble(s):
    r = ''

    for i in range(len(s)):
        r += s[len(s) - i - 1]

    return r

def blorble(a, b):
    return format(a, 'x') + format(b, 'x')

def reverse_blorble(s):
    # Split the string into two parts
    part1, part2 = s[:8], s[8:]

    # Convert hexadecimal strings back to integers
    a = int(part1, 16)
    b = int(part2, 16)

    return a, b

def reverse_shmorble(s):
    # Reverse the string
    return s[::-1]

def reverse_worble(value, flag):
    # Extract s1 and s2 from the value
    s1 = value & 0xFFFF
    s2 = (value >> 16) & 0xFFFF

    # Initialize result with a starting character
    result = 'a'

    # Reverse the process in the worble function
    for _ in range(len(flag)):
        n = (ord(result[-1]) - 7 - s1) % 65521
        s1 = (s1 - n - 7) % 65521
        result += chr(n)

    return result

# Given output flag
output_flag = "a81c0750d48f0750"

# Reverse the blorble, shmorble, and worble functions
a, b = reverse_blorble(output_flag)
reversed_shmorble = reverse_shmorble(shmorble(blorble(a, b)))
reversed_worble = reverse_worble(a, reversed_shmorble)

print("Reversed worble:", reversed_worble)
print("Reversed shmorble:", reversed_shmorble)

