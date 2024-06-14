def interpret_brainfuck(code):
    memory = [0] * 30000  # Initialize memory cells
    pointer = 0  # Pointer to the current memory cell
    output = ""  # Output string

    code_ptr = 0  # Pointer to the current position in the code
    while code_ptr < len(code):
        command = code[code_ptr]

        if command == ">":
            pointer += 1
        elif command == "<":
            pointer -= 1
        elif command == "+":
            memory[pointer] = (memory[pointer] + 1) % 256  # Ensure cell value stays in range [0, 255]
        elif command == "-":
            memory[pointer] = (memory[pointer] - 1) % 256  # Ensure cell value stays in range [0, 255]
        elif command == ".":
            output += chr(memory[pointer])
        elif command == ",":
            # Input operation; not implemented in this simplified version
            pass
        elif command == "[":
            if memory[pointer] == 0:
                # Skip to the matching ']'
                loop_count = 1
                while loop_count != 0:
                    code_ptr += 1
                    if code[code_ptr] == "[":
                        loop_count += 1
                    elif code[code_ptr] == "]":
                        loop_count -= 1
        elif command == "]":
            if memory[pointer] != 0:
                # Jump back to the matching '['
                loop_count = 1
                while loop_count != 0:
                    code_ptr -= 1
                    if code[code_ptr] == "]":
                        loop_count += 1
                    elif code[code_ptr] == "[":
                        loop_count -= 1

        code_ptr += 1

    return output

# Your Brainfuck code
code = ",>,>,>,>,>,>,>,>,>,>,>,>,>,>,>,>,>,>,>,>,>,>,>,>,>,>,>,>,>,>,>,>,>,>,>,>,>,>,>,>,>,>,>,>,>,>,><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<----------------------------------------->---------------------------------------->---------------------------------------->----------------------->------------------------------------->++++++++++++++++>------>------------------------>----------------------------------------------------------->-------------------->--->------------------------------------------------------->+++++++++>------------>----------------------------------------------------------->+++>------------>------>------------------------------------------------------->+++++++>+++++++++>--->------------>---------------------------------------------------------->++++++++>------------>----------------------->--->---------------------------------------------------------->++++++++>------------>+++++++++>++++++++++>------------------------->---------------------------------------------------------->+++>---->------------>--->------------------------------------------------------->+++++++>------->++++++++++++++++++>-------------------------------------------------------------------------------------------------><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<.>.>.>.>.>.>.>.>.>.>.>.>.>.>.>.>.>.>.>.>.>.>.>.>.>.>.>.>.>.>.>.>.>.>.>.>.>.>.>.>.>"
print(interpret_brainfuck(code))