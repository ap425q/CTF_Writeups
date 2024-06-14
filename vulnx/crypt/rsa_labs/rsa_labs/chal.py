from Crypto.Util.number import *
from Crypto.PublicKey.RSA import construct
from Crypto.Random.random import randrange


def next_prime(x):
    while not isPrime(x):
        x += 1
    return x


def keygen(nbits, e=0x10001):
    p = getPrime(nbits // 2)
    pbits = p.bit_length()
    r = randrange(2, pbits)
    mask = 2**(pbits-r-1)
    print("mask: ",mask)
    base = p ^ mask
    print("base: ",base)
    q = next_prime(base)
    print("P : ",p)
    print("Q : ",q)
    n = p * q
    priv = (p, q, e)
    pub = (n, e)
    return priv, pub


def encrypt(msg, pub):
    n, e = pub
    m = bytes_to_long(msg)
    c = pow(m, e, n)
    return long_to_bytes(c)


if __name__ == "__main__":

    FLAG = open("flag.txt", "rb").read()
    priv, pub = keygen(1024)
    ct = encrypt(FLAG, pub)

    open("flag2.txt.enc", "wb").write(ct)

    pubkey = construct(pub).export_key()
    open("pub2.pem", "wb").write(pubkey)
