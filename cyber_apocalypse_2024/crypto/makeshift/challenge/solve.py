def decrypt_flag(ciphertext):
    flag = ''
    for i in range(0, len(ciphertext), 3):
        flag += ciphertext[i+2]
        flag += ciphertext[i]
        flag += ciphertext[i+1]
    return flag[::-1]

ciphertext = "!?}De!e3d_5n_nipaOw_3eTR3bt4{_THB"
original_flag = decrypt_flag(ciphertext)
print(original_flag)
