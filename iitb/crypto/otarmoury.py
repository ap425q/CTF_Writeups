import sys
import random
import string
from Crypto.Util.number import bytes_to_long, long_to_bytes
from secret import FLAG

NUM_CHUNKS = 64
CHUNK_LEN = 8
LEFT_CHUNKS = []
RIGHT_CHUNKS = []
N = 36893488147419103259
G = 2

CHOICE_BITS = random.getrandbits(NUM_CHUNKS)

oblivious_transfer_done = False
password = None


def init_chunks():
    global LEFT_CHUNKS, RIGHT_CHUNKS
    LEFT_CHUNKS = [bytes_to_long(''.join(random.choices(string.ascii_letters+string.digits, k=CHUNK_LEN)).encode('utf-8')) for _ in range(NUM_CHUNKS)]
    RIGHT_CHUNKS = [bytes_to_long(''.join(random.choices(string.ascii_letters+string.digits, k=CHUNK_LEN)).encode('utf-8')) for _ in range(NUM_CHUNKS)]

def print_chunks():
    print("Here are the chunks:")
    for i in range(NUM_CHUNKS):
        print(f"{i+1}) {LEFT_CHUNKS[i]} {RIGHT_CHUNKS[i]}")


def receive_from_ot_sender(x, y, z0, z1, chunk_idx, bit):
    print(f"Query: {{x: {x}, y: {y}, z0: {z0}, z1: {z1}}}")
    z = [z0, z1]
    m = [LEFT_CHUNKS[chunk_idx], RIGHT_CHUNKS[chunk_idx]]
    u = [random.randint(0, N-1) for _ in range(2)]
    v = [random.randint(0, N-1) for _ in range(2)]
    w = [(pow(x, u[i], N)*pow(G, v[i], N))%N for i in range(2)]
    k = [(pow(z[i], u[i], N)*pow(y, v[i], N))%N for i in range(2)]
    c = [(m[i]*k[i])%N for i in range(2)]
    # print(f"W & C {w} and {c}")
    return (w, c)


def perform_oblivious_transfer():
    global oblivious_transfer_done, password
    password = ""
    for chunk_idx in range(NUM_CHUNKS):
        alpha, beta, gamma = random.randint(2, N-1), random.randint(2, N-1), random.randint(2, N-1)
        bit = (CHOICE_BITS >> chunk_idx) & 1
        # print(f"bit : {bit}")
        # print(f"Choice bits : {CHOICE_BITS}")
        if bit == 0:
            zeta_0 = pow(G, alpha*beta, N)
            zeta_1 = pow(G, gamma, N)
        else:
            zeta_0 = pow(G, gamma, N)
            zeta_1 = pow(G, alpha*beta, N)
        ex = pow(G, alpha, N)
        wai = pow(G, beta, N)

        w, c = receive_from_ot_sender(ex, wai, zeta_0, zeta_1, chunk_idx, bit)
        # print(f"W and C {w} {c}")

        k = pow(w[bit], beta, N)
        m = (c[bit]*pow(k, -1, N))%N
        password += long_to_bytes(m).decode('utf-8')
        print(password)
    oblivious_transfer_done = True
    print("Oblivious transfer done!")


def submit_password():
    global oblivious_transfer_done, password
    if not oblivious_transfer_done:
        print("You need to perform an oblivious transfer first!")
        return
    print("Enter the password: ", end='')
    password_submission = input()
    if password_submission == password:
        print("\nYou could look beyond oblivion! Here's your flag:")
        print(FLAG)
        sys.exit(0)
    else:
        print("\nYou're not worthy of the flag!")
        sys.exit(0)


if __name__ == "__main__":
    print("Welcome to the Oblivious Transfer Armoury!\n")
    init_chunks()

    print_chunks()

    while True:
        print("\nMake your choice:")
        print("1) Interact with a candidate")
        print("2) Submit the password\n")

        print("Enter your choice: ", end='')
        try:
            choice = int(input())
            print()
            if choice == 1:
                perform_oblivious_transfer()
            elif choice == 2:
                submit_password()
            else:
                print("Invalid choice. Bye!")
                sys.exit(0)
        except ValueError:
            print("You don't even know how to enter a number? Bye!")
            sys.exit(0)
