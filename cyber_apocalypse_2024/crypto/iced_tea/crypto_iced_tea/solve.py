import os
from Crypto.Util.number import bytes_to_long as b2l, long_to_bytes as l2b
from Crypto.Util.Padding import unpad

ECB_MODE = 0x01
CBC_MODE = 0x02

class Cipher:
    def __init__(self, key, iv=None):
        self.BLOCK_SIZE = 64
        self.KEY = [b2l(key[i:i+self.BLOCK_SIZE//16]) for i in range(0, len(key), self.BLOCK_SIZE//16)]
        self.DELTA = 0x9e3779b9
        self.IV = iv
        if self.IV:
            self.mode = CBC_MODE
        else:
            self.mode = ECB_MODE
    
    def _xor(self, a, b):
        return b''.join(bytes([_a ^ _b]) for _a, _b in zip(a, b))

    def decrypt(self, ct):
        blocks = [ct[i:i+self.BLOCK_SIZE//8] for i in range(0, len(ct), self.BLOCK_SIZE//8)]
        
        pt = b''
        if self.mode == ECB_MODE:
            for block in blocks:
                pt += self.decrypt_block(block)
        elif self.mode == CBC_MODE:
            X = self.IV
            for block in blocks:
                pt += self._xor(X, self.decrypt_block(block))
                X = block
        return unpad(pt, self.BLOCK_SIZE//8)

    def decrypt_block(self, ct_block):
        c = b2l(ct_block)
        K = self.KEY
        msk = (1 << (self.BLOCK_SIZE//2)) - 1

        m0 = c >> (self.BLOCK_SIZE//2)
        m1 = c & msk

        s = self.DELTA << 5
        for i in range(32):
            m1 -= ((m0 << 4) + K[2]) ^ (m0 + s) ^ ((m0 >> 5) + K[3])
            m1 &= msk
            m0 -= ((m1 << 4) + K[0]) ^ (m1 + s) ^ ((m1 >> 5) + K[1])
            m0 &= msk
            s -= self.DELTA

        return l2b(m0) + l2b(m1)


if __name__ == '__main__':
    KEY = bytes.fromhex('850c1413787c389e0b34437a6828a1b2')
    ct_hex = 'b36c62d96d9daaa90634242e1e6c76556d020de35f7a3b248ed71351cc3f3da97d4d8fd0ebc5c06a655eb57f2b250dcb2b39c8b2000297f635ce4a44110ec66596c50624d6ab582b2fd92228a21ad9eece4729e589aba644393f57736a0b870308ff00d778214f238056b8cf5721a843'
    ct = bytes.fromhex(ct_hex)

    cipher = Cipher(KEY)
    plaintext = cipher.decrypt(ct)

    print(plaintext.decode())
