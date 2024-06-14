import numpy as np

def reverse_transform(num):
    for guess_num in range(1, 100000):  # Assuming a reasonable range
        transformed_num = guess_num
        transformed_num += 9
        transformed_num += 23
        transformed_num -= 5
        transformed_num //= 6
        transformed_num -= 55
        transformed_num *= 9
        transformed_num **= 4
        transformed_num += org  # Assuming org is known
        if transformed_num == num:
            return guess_num
    return None  # Return None if no match is found within the range

def decrypt(ciphertext, poly):
    decrypted_text = ""
    for char in ciphertext:
        decrypted_number = ord(char)
        decrypted_number = reverse_transform(decrypted_number)
        original_number = np.roots(poly - decrypted_number)
        original_number = int(np.real(original_number[0]))
        decrypted_text += str(original_number)
    return decrypted_text

# Load the encrypted text from flag.txt
with open('flag.txt', 'r') as file:
    encrypted_text = file.read().strip()

# Load the polynomial coefficients and org from key.txt
with open('key.txt', 'rb') as file:
    coefficients = [int(coeff) for coeff in file.readline().split(',')]
    org = int(file.readline())

# Create a numpy polynomial object
poly = np.poly1d(coefficients)

# Decrypt the text
decrypted_text = decrypt(encrypted_text, poly)
print("Decrypted text:", decrypted_text)
