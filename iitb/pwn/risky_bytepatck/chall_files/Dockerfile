FROM parrotsec/core


RUN apt update && apt upgrade -y
COPY chall /chall
WORKDIR /chall
RUN groupadd -r ctfplayer && useradd -r -g ctfplayer ctfplayer
RUN apt install socat -y
RUN chmod o+x /chall/executable
USER ctfplayer
EXPOSE 9999
ENTRYPOINT socat TCP-LISTEN:9999,reuseaddr,fork EXEC:"/chall/executable"
