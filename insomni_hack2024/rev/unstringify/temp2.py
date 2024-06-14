from Crypto.Cipher import AES

def bytes_to_state(bytes_data):
    state = [[0] * 4 for _ in range(4)]
    for i in range(4):
        for j in range(4):
            state[j][i] = bytes_data[i * 4 + j]
    return state

def state_to_bytes(state):
    bytes_data = b''
    for i in range(4):
        for j in range(4):
            bytes_data += bytes([state[j][i]])
    return bytes_data

def aes_round(state, round_key):
    cipher = AES.new(round_key, AES.MODE_ECB)
    state_bytes = state_to_bytes(state)
    encrypted_state = cipher.encrypt(state_bytes)
    return bytes_to_state(encrypted_state)

# Input data
ymm2_data = bytes.fromhex("6C6534356566347D6465356E306E72657935306265723569494E537B72756D6D")
ymm3_data = bytes.fromhex("0003195A5B005902000315015C09015E42530D164D06420D2752423A630B540F")

# Ensure key length is 256 bits
key_256 = ymm3_data[:32]

# Convert data to 128-bit state and round key
state = bytes_to_state(ymm2_data)
round_key = key_256

# Perform one round of AES encryption
result_state = aes_round(state, round_key)

# Display the output
print("Output of one round of AES encryption:")
print(state_to_bytes(result_state).hex().upper())
