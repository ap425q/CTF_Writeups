#!/usr/bin/env python3

import angr
import claripy

success_offset = 0xd70
fail_offsets = [0xd5c, 0xba4, 0xad8, 0x994]

project = angr.Project("./aarchcrackme", arch='AARCH64', auto_load_libs=False)

initial_state = project.factory.entry_state()

base_address = initial_state.project.loader.main_object.min_addr

success_addr = base_address + success_offset
fail_addrs = [base_address + offset for offset in fail_offsets]

print(initial_state)

sm = project.factory.simgr(initial_state)

sm.explore(find=success_addr, avoid=fail_addrs)

print(sm)

if sm.found:
    print(sm.found[0].posix.dumps(0))
else:
    print("No solution found")
