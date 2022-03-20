#!/bin/bash
#https://github.com/nvm-sh/nvm/releases
curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.1/install.sh | bash
nvm install node
npm rebuild
