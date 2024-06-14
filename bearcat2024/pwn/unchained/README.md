# Running Locally
The challenge can be run locally by running the following

```bash
get_docker_libs_libc_ld.sh ubuntu:20.04
cp main main.local
ld_patch.sh main.local
LD_LIBRARY_PATH="$(pwd)" ./main.local
```

## __get_docker_libs_libc_ld.sh__
Pull down the specified docker container and copy its ld-x.xx.so and libc-x.xx.so to your current directory.

## __ld_patch.sh__
Patch the specified binary to use the ld-*.so in your current directory. Just remember to `LD_PRELOAD` or `LD_LOAD_LIBRARY` the correct libc when launching the binary. This is meant to make CTF challenges runnable locally even if they are made for a completely different version of linux:

