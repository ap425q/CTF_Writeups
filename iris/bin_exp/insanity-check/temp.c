#include<stdio.h>
#include<string.h>


int main(){
const char suffix[] = "! Welcome to IrisCTF2024. If you have any questions you can contact us at test@example.com\0\0\0\0";

const char temp[] = "Hi there, ";
printf("%ld",strlen(temp));

printf("%ld",sizeof(suffix));


return 0;


}