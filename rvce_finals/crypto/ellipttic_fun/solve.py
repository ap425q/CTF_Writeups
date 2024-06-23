# Elliptic curve parameters
a = 12
b = 34
p = 97

# Point Z
Z = (4, 7m)

# Check if Z satisfies the elliptic curve equation
x, y = Z

# Left-hand side: y^2 mod p
lhs = (y * y) % p

# Right-hand side: x^3 + ax + b mod p
rhs = (x**3 + a*x + b) % p

# Check if lhs equals rhs
if lhs == rhs:
    print(f"Point Z = ({x}, {y}) satisfies the elliptic curve equation mod {p}.")
else:
    print(f"Point Z = ({x}, {y}) does not satisfy the elliptic curve equation mod {p}.")
