import re

# Define input and output file paths
input_file = 'wordlist'
output_file = 'output_file.txt'

# Compile regex pattern to match everything after the first space
pattern = re.compile(r'\s+(.*)')

# Open input file for reading and output file for writing
with open(input_file, 'r') as infile, open(output_file, 'w') as outfile:
    for line in infile:
        match = pattern.search(line)
        if match:
            # Write the matched group (everything after the first space) to the output file
            outfile.write(match.group(1) + '\n')