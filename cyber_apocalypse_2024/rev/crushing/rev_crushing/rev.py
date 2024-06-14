import struct

def deserialize(serialized_data):
    map_data = [[] for _ in range(256)]
    offset = 0

    while offset < len(serialized_data):
        length = struct.unpack('<Q', serialized_data[offset:offset+8])[0]
        offset += 8

        for _ in range(length):
            value = struct.unpack('<Q', serialized_data[offset:offset+8])[0]
            map_data[(value & 0xFF)].append(value)
            offset += 8

    return map_data

def list_len(a1):
    if not a1:
        return 0
    v3 = 1
    i = a1
    while i[1]:
        i = i[1]
        v3 += 1
    return v3

def reverse_serialize_and_output(serialized_data):
    map_data = [[] for _ in range(256)]
    offset = 0

    while offset < len(serialized_data):
        length = struct.unpack('<Q', serialized_data[offset:offset+8])[0]
        offset += 8

        for _ in range(length):
            value = struct.unpack('<Q', serialized_data[offset:offset+8])[0]
            char_index = value & 0xFF
            list_length = value >> 8
            offset += 8

            sublist = []
            for _ in range(list_length):
                sublist.append(struct.unpack('<Q', serialized_data[offset:offset+8])[0])
                offset += 8

            map_data[char_index] = sublist

    return map_data

def reverse_add_char_to_map(map_data, char, index):
    map_data[char].append(index)

def reverse_main(serialized_data):
    map_data = reverse_serialize_and_output(serialized_data)
    original_message = ''
    for char, sublist in enumerate(map_data):
        for value in sublist:
            original_message += chr(value & 0xFF)
    return original_message

# Read the serialized data from the file
with open('message.txt.cz', 'rb') as f:
    serialized_data = f.read()

# Reverse the serialization and output to get the original message
original_message = reverse_main(serialized_data)
print("Original Message:", original_message)
