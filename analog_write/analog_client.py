import socket

HOST='192.168.2.3'
PORT=5002
s=socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((HOST, PORT))

print "write something to send: "
data = raw_input()

for i in range(0, 3 - len(data)):
    data = "0" + data
s.send(data)
