import re

# Compile a regular expression pattern
pattern = re.compile('^uoftctf\\{([bdrw013]){9}\\}$')

# Define the worble function
def worble(s):
    s1 = 5
    s2 = 31

    for n in range(len(s)):
        s1 = (s1 + ord(s[n]) + 7) % 65521
        s2 = (s1 * s2) % 65521

    return (s2 << 16) | s1

# Define the shmorble function
def shmorble(s):
    r = ''

    for i in range(len(s)):
        r += s[len(s) - i - 1]

    return r

# Define the blorble function
def blorble(a, b):
    return format(a, 'x') + format(b, 'x')

# Main function
def main():
    flag = input('Enter flag: ')

    if not pattern.match(flag):
        print("Incorrect format!")
        return

    a = worble(flag)
    b = worble(flag[::-1][-1])

    print("Here's your flag:")
    print(shmorble(blorble(a, b)))

if __name__ == "__main__":
    main()
