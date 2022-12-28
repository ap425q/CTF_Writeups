#include <stdio.h>

void * random(char *param_1)

{
  char cVar1;
  void *pvVar2;
  char *pcVar3;
  char cVar4;
  int iVar5;
  
  pvVar2 = malloc(0xc4);
  cVar4 = *param_1;
  if (cVar4 != '\0') {
    iVar5 = 0;
    do {
      cVar1 = param_1[1];
      if ('0' < cVar4) {
        pcVar3 = (char *)((long)pvVar2 + (long)iVar5);
        do {
          *pcVar3 = cVar1;
          pcVar3 = pcVar3 + 1;
        } while (pcVar3 != (char *)((long)pvVar2 + (ulong)((int)cVar4 - 0x31) + (long)iVar5 + 1));
        iVar5 = iVar5 + -0x30 + (int)cVar4;
      }
      param_1 = param_1 + 2;
      cVar4 = *param_1;
    } while (cVar4 != '\0');
  }
  return pvVar2;
}