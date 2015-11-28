
# Instructions for building Virtual Machine
This folder contains the files needed to build a virtual machine for Ref-Finder using Vagrant.
***
#### Pre-requisites:

1. [Vagrant](https://www.vagrantup.com/downloads.html). 
2. [VirtualBox](https://www.virtualbox.org/wiki/Downloads).
3. SSH Authentication must be enabled and functioning. 

###Steps to spin up a Virtual Machine for the tool Ref-Finder :

1. Download the [Vagrantfile] (https://github.com/SoftwareEngineeringToolDemos/FSE-2010-Ref-Finder/blob/master/build-vm/Vagrantfile). Save it in a folder where you want to install the VM.

2. From the host, navigate to that folder (via bash on Linux Machine or Powershell or CommandPrompt on Windows Machine) and execute the command :  
      ```vagrant up```

3. Wait for the VM to be started.Now the script automatically executes the commands.The Virtual Machine will boot up and can be viewed from the Virtual Box. 

#### Provisioning:

1. Ubuntu Desktop 14.04.
2. JDK 1.8 ( Can be checked using ```java -version``` in the terminal)

#### Username and Password

* username: *vagrant*
* password: *vagrant*

### Acknowledgments
1. Vagrant box used: boxcutter/ubuntu1504-desktop from [here](https://atlas.hashicorp.com/boxcutter/boxes/ubuntu1504-desktop)
2.Commands for java installation from [here](http://www.webupd8.org/2012/09/install-oracle-java-8-in-ubuntu-via-ppa.html)
3.Commands for eclipse installation from [here](http://blog.versioneye.com/2015/05/05/setting-up-a-dev-environment-with-vagrant/)
4.Commands for Vagrantfile from [here](https://help.ubuntu.com/) 
5.https://docs.vagrantup.com/v2/provisioning/shell.html
