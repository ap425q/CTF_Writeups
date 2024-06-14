def to_identity_map(a):
    return ord(a) - 0x41
def from_identity_map(a):
    return chr(a % 26 + 0x41)
def decrypt(ciphertext):
    m = ''
    for i in range(len(ciphertext)):
        ch = ciphertext[i]
        if not ch.isalpha():
            dch = ch
        else:
            dch = from_identity_map(to_identity_map(ch) - i)
        m += dch
    return m

ciphertext = "DJF_CTA_SWYH_NPDKK_MBZ_QPHTIGPMZY_KRZSQE?!_ZL_CN_PGLIMCU_YU_KJODME_RYGZXL"
decrypted_text = decrypt(ciphertext)
print(decrypted_text)
