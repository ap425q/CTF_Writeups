// gcc -z norelro -no-pie -fno-stack-protector sockem.c -o sockem

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

void ignore_me_setting_buffering(){
    setvbuf(stdin, NULL, _IONBF, 0);
    setvbuf(stdout, NULL, _IONBF, 0);
    setvbuf(stderr, NULL, _IONBF, 0);
    return;
}

int main(int argc, char* argv[]){
    ignore_me_setting_buffering();

    int key;
    char input[10];

    printf("Enter for a chance to win! : ");
    fgets(input, 20, stdin);

    if(key == 0xcafebabe){
        puts("Congrats! Here is your prize:");
        printf("Addr of system: %p\n", system);
        
        char speech[128];
        printf("Please say a few words about how it feels to win: ");
        read(0, speech, 200);
    }else{
        puts("Better luck next time :(");
        exit(1);
    }

    return 0;
}
