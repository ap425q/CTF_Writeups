import random

random.seed(random.randbytes(8))

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

keys = list(emoji_map.keys())
vals = list(emoji_map.values())

random.shuffle(keys)
random.shuffle(vals)

emoji_map = dict(zip(keys, vals))

def encode(s):
    ct = list(map(lambda x: emoji_map[x], s))
    return ''.join(ct)

def decode(s):
    raise NotImplementedError

FLAG = open("flag.txt").read()
FLAG = list(FLAG)
ct = encode(FLAG)
open('out.txt', 'w').write(ct)