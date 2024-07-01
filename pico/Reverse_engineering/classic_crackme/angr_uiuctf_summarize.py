#!/usr/bin/env python3

import angr
import claripy

success_addr = 0x40130E
fail_addr = 0x401356

# Create an angr project
project = angr.Project("./summarize", auto_load_libs=False)

# Create a symbolic bitvector for each of the six 9-digit integers
a = claripy.BVS('a', 32)  # Adjust the size based on how the input is processed
b = claripy.BVS('b', 32)
c = claripy.BVS('c', 32)
d = claripy.BVS('d', 32)
e = claripy.BVS('e', 32)
f = claripy.BVS('f', 32)

# Create initial state with symbolic variables
initial_state = project.factory.entry_state()

# Add constraints for 9-digit integers
initial_state.solver.add(a >= 100000000)
initial_state.solver.add(a <= 999999999)
initial_state.solver.add(b >= 100000000)
initial_state.solver.add(b <= 999999999)
initial_state.solver.add(c >= 100000000)
initial_state.solver.add(c <= 999999999)
initial_state.solver.add(d >= 100000000)
initial_state.solver.add(d <= 999999999)
initial_state.solver.add(e >= 100000000)
initial_state.solver.add(e <= 999999999)
initial_state.solver.add(f >= 100000000)
initial_state.solver.add(f <= 999999999)

# Add the symbolic variables to the state's memory
for sym in [a, b, c, d, e, f]:
    initial_state.memory.store(initial_state.regs.sp, sym)
    initial_state.regs.sp += 4  # Adjust based on the architecture

# Create a simulation manager
sm = project.factory.simgr(initial_state)

# Explore the binary to find the success address while avoiding the fail address
sm.explore(find=success_addr, avoid=fail_addr)

if sm.found:
    found_state = sm.found[0]
    flag_a = found_state.solver.eval(a, cast_to=int)
    flag_b = found_state.solver.eval(b, cast_to=int)
    flag_c = found_state.solver.eval(c, cast_to=int)
    flag_d = found_state.solver.eval(d, cast_to=int)
    flag_e = found_state.solver.eval(e, cast_to=int)
    flag_f = found_state.solver.eval(f, cast_to=int)
    
    print(f"Flag: {flag_a} {flag_b} {flag_c} {flag_d} {flag_e} {flag_f}")
else:
    print("No solution found")
