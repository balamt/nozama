# Create SSL Self Sign Certf

-[Reference](https://www.section.io/engineering-education/how-to-get-ssl-https-for-localhost/)

## Create CA - CA.key file
```
openssl genrsa -out CA.key -des3 2048
# Asks for passphrase - password
# Re enter the same passphrase
# CA.key file will be created
```

## Create root CA Certificate using the CA.key which we created above
```
E:\workspace\java\nozama-cert\cert\CA>openssl req -x509 -sha256 -new -nodes -days 7300 -key CA.key -out CA.pem
Enter pass phrase for CA.key: password
You are about to be asked to enter information that will be incorporated
into your certificate request.
What you are about to enter is what is called a Distinguished Name or a DN.
There are quite a few fields but you can leave some blank
For some fields there will be a default value,
If you enter '.', the field will be left blank.
-----
Country Name (2 letter code) [AU]:IN
State or Province Name (full name) [Some-State]:Tamil Nadu
Locality Name (eg, city) []:Chennai
Organization Name (eg, company) [Internet Widgits Pty Ltd]:balamt.in
Organizational Unit Name (eg, section) []:IT
Common Name (e.g. server FQDN or YOUR name) []:nozama
Email Address []:mail@balamurugan.co.in
```

## Create ext file for the domain (localhost or nozama)

```
touch localhost.ext 
or
notepad localhost.ext

After creating the ext file, paste the below content and modify accordingly

	authorityKeyIdentifier = keyid,issuer
	basicConstraints = CA:FALSE
	keyUsage = digitalSignature, nonRepudiation, keyEncipherment, dataEncipherment
	subjectAltName = @alt_names

	[alt_names]
	DNS.1 = localhost
	IP.1 = 127.0.0.1


or

	authorityKeyIdentifier = keyid,issuer
	basicConstraints = CA:FALSE
	keyUsage = digitalSignature, nonRepudiation, keyEncipherment, dataEncipherment
	subjectAltName = @alt_names

	[alt_names]
	DNS.1 = nozama
	DNS.2 = localhost
	DNS.3 = nozama-*
	DNS.4 = *nozama-*
	IP.1 = 127.0.0.1

```

## Now we need to create key for the domain (localhost / nozama)

```
E:\workspace\java\nozama-cert\cert\CA>cd localhost

E:\workspace\java\nozama-cert\cert\CA\localhost>openssl genrsa -out localhost.key -des3 2048
Generating RSA private key, 2048 bit long modulus (2 primes)
Enter pass phrase for localhost.key: password
Verifying - Enter pass phrase for localhost.key: password

or

E:\workspace\java\nozama-cert\cert\CA>cd nozama

E:\workspace\java\nozama-cert\cert\CA\nozama>openssl genrsa -out nozama.key -des3 2048
Generating RSA private key, 2048 bit long modulus (2 primes)
Enter pass phrase for nozama.key: nozama19
Verifying - Enter pass phrase for nozama.key: nozama19
```


## Now we need to create the CSR

```
E:\workspace\java\nozama-cert\cert\CA\localhost>openssl req -new -key localhost.key -out lopenssl req -new -key localhost.key -out localhost.csr
Enter pass phrase for localhost.key:
You are about to be asked to enter information that will be incorporated
into your certificate request.
What you are about to enter is what is called a Distinguished Name or a DN.
There are quite a few fields but you can leave some blank
For some fields there will be a default value,
If you enter '.', the field will be left blank.
-----
Country Name (2 letter code) [AU]:IN
State or Province Name (full name) [Some-State]:Tamil Nadu
Locality Name (eg, city) []:Chennai
Organization Name (eg, company) [Internet Widgits Pty Ltd]:balamt.in
Organizational Unit Name (eg, section) []:IT
Common Name (e.g. server FQDN or YOUR name) []:balamt.in
Email Address []:balamurugan.th@gmail.com

Please enter the following 'extra' attributes
to be sent with your certificate request
A challenge password []:password
An optional company name []:balamt.in

E:\workspace\java\nozama-cert\cert\CA\localhost>

or

E:\workspace\java\nozama-cert\cert\CA\nozama>openssl req -new -key nozama.key -out nozama.csr
Enter pass phrase for nozama.key:
You are about to be asked to enter information that will be incorporated
into your certificate request.
What you are about to enter is what is called a Distinguished Name or a DN.
There are quite a few fields but you can leave some blank
For some fields there will be a default value,
If you enter '.', the field will be left blank.
-----
Country Name (2 letter code) [AU]:IN
State or Province Name (full name) [Some-State]:Tamil Nadu
Locality Name (eg, city) []:Chennai
Organization Name (eg, company) [Internet Widgits Pty Ltd]:balamt.in
Organizational Unit Name (eg, section) []:IT
Common Name (e.g. server FQDN or YOUR name) []:balamt.in
Email Address []:balamurugan.th@gmail.com

Please enter the following 'extra' attributes
to be sent with your certificate request
A challenge password []:nozama19
An optional company name []:balamt.in

E:\workspace\java\nozama-cert\cert\CA\nozama>

```

## Use CA to sign the domain certificate 

```

E:\workspace\java\nozama-cert\cert\CA>openssl x509 -req -in ./localhost/localhost.csr -CA CA.pem -CAkey CA.key -CAcreateserial -days 7300 -sha256 -extfile ./localhost/localhost.ext -out ./localhost/localhost.crt
Signature ok
subject=C = IN, ST = Tamil Nadu, L = Chennai, O = balamt.in, OU = IT, CN = balamt.in, emailAddress = balamurugan.th@gmail.com
Getting CA Private Key
Enter pass phrase for CA.key: password


E:\workspace\java\nozama-cert\cert\CA>openssl x509 -req -in ./nozama/nozama.csr -CA CA.pem -CAkey CA.key -CAcreateserial -days 7300 -sha256 -extfile ./nozama/nozama.ext -out ./nozama/nozama.crt
Signature ok
subject=C = IN, ST = Tamil Nadu, L = Chennai, O = balamt.in, OU = IT, CN = balamt.in, emailAddress = balamurugan.th@gmail.com
Getting CA Private Key
Enter pass phrase for CA.key: password

E:\workspace\java\nozama-cert\cert\CA>

```

## We need decrypted key of domain key which we creates (localhost.key and nozama.key)

```

E:\workspace\java\nozama-cert\cert\CA\localhost>openssl rsa -in localhost.key -out localhost.decrypted.key
Enter pass phrase for localhost.key: password
writing RSA key

or

E:\workspace\java\nozama-cert\cert\CA\nozama>openssl rsa -in nozama.key -out nozama.decrypted.key
Enter pass phrase for nozama.key: nozama19
writing RSA key

```

## Convert crt to p12 

```
E:\workspace\java\nozama-cert\cert\CA\localhost>openssl pkcs12 -export -in localhost.crt -inkey localhost.key -out localhost.p12
Enter pass phrase for localhost.key: password
Enter Export Password:password
Verifying - Enter Export Password:password

or

E:\workspace\java\nozama-cert\cert\CA\nozama>openssl pkcs12 -export -in nozama.crt -inkey nozama.key -out nozama.p12
Enter pass phrase for nozama.key: nozama19
Enter Export Password:nozama19
Verifying - Enter Export Password:nozama19
```

## Create JKS from p12 file using keytool

```


E:\workspace\java\nozama-cert\cert\CA\localhost>keytool -importkeystore -srckeystore localhost.p12 -srcstoretype PKCS12 -destkeystore localhost.jks -deststoretype JKS
Importing keystore localhost.p12 to localhost.jks...
Enter destination keystore password:password
Re-enter new password:password
Enter source keystore password:password
Entry for alias 1 successfully imported.
Import command completed:  1 entries successfully imported, 0 entries failed or cancelled

Warning:
The JKS keystore uses a proprietary format. It is recommended to migrate to PKCS12 which is an industry standard format using "keytool -importkeystore -srckeystore localhost.jks -destkeystore localhost.jks -deststoretype pkcs12".


or

E:\workspace\java\nozama-cert\cert\CA\nozama>keytool -importkeystore -srckeystore nozama.p12 -srcstoretype PKCS12 -destkeystore nozama.jks -deststoretype JKS

Importing keystore nozama.p12 to nozama.jks...
Enter destination keystore password:nozama19
Re-enter new password:nozama19
Enter source keystore password:nozama19
Entry for alias 1 successfully imported.
Import command completed:  1 entries successfully imported, 0 entries failed or cancelled

Warning:
The JKS keystore uses a proprietary format. It is recommended to migrate to PKCS12 which is an industry standard format using "keytool -importkeystore -srckeystore nozama.jks -destkeystore nozama.jks -deststoretype pkcs12".


```

## Change alias name in keystore (jks)

```

keytool -changealias -alias "1" -destalias "nozama" -keypass nozama19 -keystore nozama.jks -storepass nozama19

# for keypass - give key password
# for storepass - give jks file password

```

https://stackoverflow.com/questions/7580508/getting-chrome-to-accept-self-signed-localhost-certificate