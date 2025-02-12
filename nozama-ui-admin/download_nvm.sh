#!/bin/bash
#https://github.com/nvm-sh/nvm/releases
# Download and install nvm:
curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.40.1/install.sh | bash

# in lieu of restarting the shell
\. "$HOME/.nvm/nvm.sh"

# Download and install Node.js:
nvm install 22

# Verify the Node.js version:
node -v # Should print "v22.14.0".
nvm current # Should print "v22.14.0".

# Verify npm version:
npm -v # Should print "10.9.2".

#build the app

npm run build

# run the react app

npm start

# run the react app with 0.0.0.0 host

npm start --host 0.0.0.0

