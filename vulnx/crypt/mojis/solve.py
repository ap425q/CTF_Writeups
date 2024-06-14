import itertools

# Define the emoji map based on the provided mappings
emoji_map = {
    'a': '😀💃',
    'b': '😂🌍',
    'c': '😍🍦',
    'd': '😎💻',
    'e': '🤔🕵',
    'f': '💔💘',
    'g': '🌈🌟',
    'h': '👻🎃',
    'i': '👸🤴',
    'j': '🌊🏖',
    'k': '🐶🐺',
    'l': '🐲🐉',
    'm': '🎶🎸',
    'n': '🌳🏞',
    'o': '🌙🌠',
    'p': '👓📚',
    'q': '🎩🃏',
    'r': '🚗🏎',
    's': '🌞☀',
    't': '🕰⏳',
    'u': '🐸🐳',
    'v': '🎬📽',
    'w': '🌪💨',
    'x': '💣💥',
    'y': '🫢🌲',
    'z': '👽🛸',
    '!': '🎉🎊',
    '~': '🌙🌟',
    '@': '🌠🔓',
    '#': '🔑🗝',
    '$': '💰💸',
    '%': '🥺📈',
    '^': '🔼🔽',
    '&': '🔗🔒',
    '*': '✨🌟',
    '(': '👥👽',
    ')': '🎸👤',
    '_': '🔗🔗',
    '+': '➕➖',
    ':': '💬💭',
    '"': '🎄💭',
    '{': '🤬😸',
    '}': '🔚🎉',
    ' ': '🤣😜',
    ',': '🫨🙄',
    '.': '🤒🥶',
}

# Load the encoded content from out.txt
with open('out.txt', 'r') as f:
    encoded_content = f.read().strip()

# Prepare to brute-force the mapping
keys = list(emoji_map.keys())
values = list(emoji_map.values())

# Known prefix
prefix = 'vulncon{'
prefix_length = len(prefix) * 2  # Each character is represented by two emojis

# Generate all possible permutations of the values
all_permutations = itertools.permutations(values)

def decode_prefix_with_mapping(encoded_content, trial_mapping):
    reverse_map = {v: k for k, v in trial_mapping.items()}
    decoded_prefix = ""
    for i in range(0, prefix_length, 2):
        emoji_pair = encoded_content[i:i+2]
        if emoji_pair in reverse_map:
            decoded_prefix += reverse_map[emoji_pair]
        else:
            return None
    return decoded_prefix

# Try all possible mappings
for perm in all_permutations:
    trial_mapping = dict(zip(keys, perm))
    decoded_prefix = decode_prefix_with_mapping(encoded_content, trial_mapping)
    if decoded_prefix == prefix:
        print("Found the flag prefix:", decoded_prefix)
        print("Mapping used:", trial_mapping)
        break
