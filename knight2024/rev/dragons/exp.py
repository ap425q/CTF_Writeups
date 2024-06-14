#!usr/bin/env/python3

import angr
import claripy

base_addr = 0x400000
success_addr = 0x401a69
fail_addr = 0x401a7a

project = angr.Project("./dragons",main_opts = {"base_addr" : base_addr}, auto_load_libs=False)


initial_state = project.factory.entry_state()
print(initial_state)


sm = project.factory.simgr(initial_state)
sm.explore(find=success_addr,avoid=fail_addr)

print(sm)
print(sm.found[0].posix.dumps(0))