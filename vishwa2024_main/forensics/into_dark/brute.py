def vigenere_decrypt(ciphertext, key):
    decrypted_text = ""
    key_length = len(key)
    for i in range(len(ciphertext)):
        shift = ord(key[i % key_length].lower()) - ord('a')
        if ciphertext[i].isalpha():
            if ciphertext[i].islower():
                decrypted_text += chr(((ord(ciphertext[i]) - ord('a') - shift) % 26) + ord('a'))
            else:
                decrypted_text += chr(((ord(ciphertext[i]) - ord('A') - shift) % 26) + ord('A'))
        else:
            decrypted_text += ciphertext[i]
    return decrypted_text

def brute_force_vigenere(ciphertext, common_words):
    possible_keys = [chr(i) + chr(j) + chr(k) for i in range(97, 123) for j in range(97, 123) for k in range(97, 123)]
    for key in possible_keys:
        decrypted_text = vigenere_decrypt(ciphertext, key)
        if any(word in decrypted_text.lower() for word in common_words):
            print("Possible decryption with key {}: {}".format(key, decrypted_text))

# Example usage:
ciphertext = "Tdgem kawj uswhfv wsrewez kzmv kas rewvgg"
common_words = ["apply"]  # Common words in English
brute_force_vigenere(ciphertext, common_words)
