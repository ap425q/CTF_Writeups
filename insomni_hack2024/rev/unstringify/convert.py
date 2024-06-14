hex_string = "0FA2E431BCCF9A19139A0B3A22308FE493CA9D744B9957602E2D5E42E7CE6AE3"

# Convert hex string to bytes
hex_bytes = bytes.fromhex(hex_string)

# Convert bytes to ASCII
ascii_string = hex_bytes.decode('utf-16')

print("ASCII representation:", ascii_string)


#Key : 00 03 19 5A 5B 00 59 02 00 03 15 01 5C 09 01 5E 42 53 0D 16 4D 06 42 0D 27 52 42 3A 63 0B 54 0F
#final : 51 CC 0F BE A0 26 2A 14 6D 75 89 30 EC FD 46 1C 0F A3 B4 79 8B 62 C9 06 68 EA 03 BE 2A C7 F2 C4

2E D9 9A 95 C0 81 78 EF DB B2 E3 A6 04 20 C2 22 24 75 7E 02 30 FB BD 86 10 BD FC 07 F5 FC D6 8D


#key encryption : 62 15 43 75 66 F5 3A 29 66 72 5D 9F F2 8F 39 C8 F1 A3 8C CD F7 C2 28 9D D9 7D 72 3C 0E C5 81 C3
