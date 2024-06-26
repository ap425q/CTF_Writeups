FROM ubuntu:20.04

WORKDIR /saviour

COPY saviour .
COPY run.sh /root/run.sh
RUN chmod +x /root/run.sh

RUN apt-get update && \
    apt-get install -y socat && \
    rm -rf /var/lib/apt/lists/*

EXPOSE 80

CMD ["/bin/bash", "/root/run.sh"]

