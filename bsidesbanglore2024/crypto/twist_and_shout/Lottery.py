import socket
import sys
import threading
import base64
from SecretFunctions import flag, initialize_rng

def generate_ticket(r):
    return str(base64.b16encode(r.randbytes(4)))[2:-1]

def run_lottery(client_socket):
    r = initialize_rng() # instance of random.Random()
    lotto_tickets = 1000
    client_socket.send(b"Welcome to our in-progress casino!\n")
    while True:
        client_socket.send(b"Would you like to play the random lotto (our only game)? Y/n\n")
        x = str(client_socket.recv(1024), "UTF-8")
        x = x.lower().strip()

        if x == "n":
            client_socket.send(b"Goodbye!\n")
            break
        elif x == "" or x == "y":
            if lotto_tickets <= 0:
                client_socket.send(b"Sorry! Out of lotto tickets :(\n")
                break
            lotto_tickets -= 1
            client_socket.send(bytes(f"Playing the lottery! You have { lotto_tickets } tickets remaining\n", "UTF-8"))

            drawn_ticket = generate_ticket(r)

            client_socket.send(bytes(f"You drew { drawn_ticket }\n", "UTF-8"))
            client_socket.send(bytes(f"You can hit enter to accept your draw, or type a guess of your own!\n", "UTF-8"))
            user_input = str(client_socket.recv(1024), "UTF-8")
            user_input = user_input.strip()
            if user_input != "":
                drawn_ticket = user_input

            client_socket.send(bytes(f"Checking { drawn_ticket }...\n", "UTF-8"))
            winning_ticket = generate_ticket(r)
            if drawn_ticket == winning_ticket:
                client_socket.send(bytes(f"You're quite lucky! Here's a flag: { flag() }\n", "UTF-8"))
            else:
                client_socket.send(b"No luck... Give it another try -- I'm sure you'll win soon!\n")

        else:
            client_socket.send(b"Invalid Input\n")


def handle_connection(client_socket):
    try:
        run_lottery(client_socket)
    finally:
        client_socket.close()


def start_server():
    host = sys.argv[1] if len(sys.argv) > 1 else '0.0.0.0'
    port = int(sys.argv[2]) if len(sys.argv) > 2 else 1337
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.bind((host, port))
    server_socket.listen(15)

    print(f"Server listening on {host}:{port}")

    while True:
        client_socket, address = server_socket.accept()

        client_thread = threading.Thread(target=handle_connection, args=(client_socket,))
        client_thread.start()


start_server()
