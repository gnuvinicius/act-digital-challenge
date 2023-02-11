#!/bin/bash
echo 'Acquire::ForceIPv4 "true";' | sudo tee /etc/apt/apt.conf.d/99force-ipv4

# instalar docker

sudo apt-get -y install apt-transport-https ca-certificates curl gnupg-agent software-properties-common
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu bionic stable"
sudo apt-get update -y
sudo apt install docker-ce -y

# instalar docker-compose
sudo curl -L "https://github.com/docker/compose/releases/download/1.25.5/docker-compose-linux-x86_64" -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose
sudo usermod -aG docker $(whoami)

# rodar docker-compose modo daemon
# cd /vagrant
# docker-compose up

sudo groupadd docker
sudo usermod -aG docker $USER
newgrp docker