def ultrabrend(message):
    if len(message) < 29:
        message = ((message + " ") * 29)[:29]

    digest = [0]*32

    for i in range(len(message)):
        digest[i % 29] ^= ord(message[i])

    t = digest[0] * digest[28]

    for i in range(28):
        digest[i] = (digest[i] + digest[i+1]) % 256

    digest[28] = t // 256
    digest[29] = t % 256
    digest[30] = 0xff ^ len(message)
    digest = digest[16:] + digest[:16]

    for i in range(32):
        digest[i] ^= digest[(i+1) % 32] ^ i

    return "".join(["{:02x}".format(i) for i in digest])


string = input("Enter a message: ")
print("Hash:", ultrabrend(string))