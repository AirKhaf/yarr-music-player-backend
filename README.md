## YAAR MUSIC PLAYER BACKEND

### Generate RSA access keys before starting and app:
Keys format - **PKCS8**

Keys are used for authorization and encoding/decoding/validating JWT tokens
1. Generate the pair of keys:
``` bash
openssl genrsa -out keypair.pem 2048
```
2. Extract public key to **app.pub**:
```
openssl rsa -in keypair.pem -pubout -out app.pub
```
3. Extract private key to **app.key**:
```
openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out app.key
```
4. Put **app.pub** и **app.key** to the *./src/main/resources* directory
