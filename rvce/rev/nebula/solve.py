def main():
    # Initialize the 64-bit integers from the C code
    v4 = [
        0x4F0A4B425E58555F,
        0x7A575C664B094166,
        0x447756080E69404B
    ]
    
    # Print the 64-bit integers
    print("v4 values as strings:")
    
    # Convert these integers to their byte representation and then to string
    v4_str = ''.join(v.to_bytes(8, 'little').decode('latin1') for v in v4)
    
    print(v4_str)
    
    v7 = 24
    v6 = 57
    
    # XOR each byte with v6 and collect the results
    result = []
    for b in v4_str.encode('latin1')[:v7]:
        xor_result = v6 ^ b
        result.append(chr(xor_result))
        print(f"XOR({v6}, {b}) = {xor_result}  Result: {chr(xor_result)}")
    
    # Join the characters to form the final string
    result_str = ''.join(result)
    
    print("XORed result:", result_str)

# Run the main function
main()
