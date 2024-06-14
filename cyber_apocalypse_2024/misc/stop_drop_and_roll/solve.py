from pwn import *

def play_game(scenarios):
    action = ""
    for i,scenario in enumerate(scenarios):
        if "GORGE" in scenario:
            action += "STOP"
        if "PHREAK" in scenario:
            action += "DROP"
        if "FIRE" in scenario:
            action += "ROLL"
        if i != len(scenarios) - 1:
            action += "-"
        
    # Send action to the server
    r.sendline(action)
    
    # Receive response from the server
    response = r.recvline().strip()
    print("Scenario:", scenarios)
    print("Action:", action)
    print("Response:", response)

# Set up the connection to the server
# Change the IP address and port accordingly
r = remote('83.136.253.226', 55134)

# Receive initial message and print it
print(r.recvuntil("ready? (y/n)"))

# Send 'y' to indicate readiness
r.sendline("y")

# Loop until the game is over
while True:
    # Receive the scenarios from the server
    r.recvline()
    scenarios = ''
    scenarios = str(r.recvline().strip(),encoding='utf-8').split(', ')
    print("Received scenarios:", scenarios)
    
    # Play the game with the received scenarios
    play_game(scenarios)
    
    # Check if the game is over
    game_over = r.recvline().strip()
    if game_over == "GAME OVER":
        print("Game over. Exiting.")
        break

# Close the connection
r.close()
