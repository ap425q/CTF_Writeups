#include <stdio.h>
#include <fcntl.h>
#include <stdlib.h>
#include <unistd.h>
const uid_t limit_uid = 1001;
int main(){
   setgid(limit_uid);
   setegid(limit_uid);
   setregid(limit_uid, limit_uid);
   setuid(limit_uid);
   seteuid(limit_uid);
   setreuid(limit_uid, limit_uid);
   system("/bin/sh");

   return 0;
}