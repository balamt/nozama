#!/bin/bash

# Create a self-signed certificate for localhost.
# The certificate is valid for 55 years.
# The certificate is valid for the following domains:
# - localhost
# - *docker*
# - *nozama*
# - host.docker.internal
# - *local*
# - *docker-*
# - 127.0.0.1

#Also use the below details for the certificate ext file:
#authorityKeyIdentifier = keyid,issuer
#basicConstraints = CA:FALSE
#keyUsage = digitalSignature, nonRepudiation, keyEncipherment, dataEncipherment
#subjectAltName = @alt_names

# Use the below details for the certificate :
# Country Name (2 letter code) [AU]:IN
# State or Province Name (full name) [Some-State]:Tamil Nadu
# Locality Name (eg, city) []:Chennai
# Organization Name (eg, company) [Internet Widgits Pty Ltd]:balamt.dev
# Organizational Unit Name (eg, section) []:IT
# Common Name (e.g. server FQDN or YOUR name) []:nozama
# Email Address []:mail@balamurugan.co.in

#decalare variable for temp folder to store the files
TEMP_DIR=tmp
CA_KEY=CA.key

# Create a folder to place all the files:
mkdir -p $TEMP_DIR

# Required
domain=$1
commonname=$domain
# Change to your company details
country=IN
state=Tamil Nadu
locality=Chennai
organization=balamt.dev
organizationalunit=IT
email=mail@balamurugan.co.in
# Optional
password=$2

if [ -z "$domain" ]
then
    echo "Domain name not present."
    echo "Usage $0 [domain name]"
    exit 99
fi

if [ -z "$password" ]
then
    echo "Password not present."
    echo "Usage $0 [domain name] [password]"
    exit 99
fi

echo "Generating key request for $domain"
# Generate a key
openssl genrsa -des3 -passout pass:$password -out $domain.key 2048 -noout
# Remove passphrase from the key
openssl rsa -passin pass:$password -in $domain.key -out $domain.key
# Generate certificate request
openssl req -new -key $domain.key -out $domain.csr -subj "/C=$country/ST=$state/L=$locality/O=$organization/OU=$organizationalunit/CN=$commonname/emailAddress=$email"