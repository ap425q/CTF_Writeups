import pygame
import subprocess

# Initialize Pygame
pygame.init()

# Set the size of the game window
window_size = (300, 300)
screen = pygame.display.set_mode(window_size)

# Set the title of the game window
pygame.display.set_caption("Tic Tac Toe")

# Set up the Tic Tac Toe board
board = [[" ", " ", " "], [" ", " ", " "], [" ", " ", " "]]
player = "X"

# Define the function that checks for a win
def check_win():
    for row in range(3):
        if board[row][0] == board[row][1] == board[row][2] != " ":
            return True
    for col in range(3):
        if board[0][col] == board[1][col] == board[2][col] != " ":
            return True
    if board[0][0] == board[1][1] == board[2][2] != " ":
        return True
    if board[0][2] == board[1][1] == board[2][0] != " ":
        return True
    return False

# Define the function that runs the game
def run_game():
    global player
    running = True
    while running:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                running = False
            elif event.type == pygame.MOUSEBUTTONDOWN:
                pos = pygame.mouse.get_pos()
                row = pos[1] // 100
                col = pos[0] // 100
                if board[row][col] == " ":
                    board[row][col] = player
                    if player == "X":
                        player = "O"
                    else:
                        player = "X"
        screen.fill((255, 255, 255))
        for row in range(3):
            for col in range(3):
                pygame.draw.rect(screen, (0, 0, 0), (col * 100, row * 100, 100, 100), 2)
                font = pygame.font.Font(None, 100)
                text = font.render(board[row][col], True, (0, 0, 0))
                text_rect = text.get_rect(center=(col * 100 + 50, row * 100 + 50))
                screen.blit(text, text_rect)
        pygame.display.flip()
        if check_win():
            print("Player", player, "wins!")
            # Launch the binary
            binary_path = "opt/win/root/root1/snake"
            subprocess.run([binary_path], check=True)
            running = False

# Start the game
run_game()

# Quit Pygame
pygame.quit()

