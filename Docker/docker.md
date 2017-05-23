
#### Workflows

* Pull image > Run de Container (Modify if it was necessary) > Push image
* Dockerfile (FROM image, then customizing it as CODE) > Create a image > Run de Container
* Run a Container | Build a Dockerfile > List containers > Stop a Container

#### Misc

Docker daemon - The background service running on the host that manages building, running and distributing Docker containers

`/graph/overlay2`  > where the images and containers layers are stored

Displaying simple docker client-server details
```
> docker version
Client:
 Version:      17.03.1-ce
 API version:  1.27
 Go version:   go1.7.5
 Git commit:   c6d412e
 Built:        Tue Mar 28 00:40:02 2017
 OS/Arch:      darwin/amd64

Server:
 Version:      17.03.1-ce
 API version:  1.27 (minimum version 1.12)
 Go version:   go1.7.5
 Git commit:   c6d412e
 Built:        Fri Mar 24 00:00:50 2017
 OS/Arch:      linux/amd64
 Experimental: true
 ```

Displaying system wide information regarding the Docker installation
```sh
> docker info
Containers: 0
 Running: 0
 Paused: 0
 Stopped: 0
Images: 0
Server Version: 1.12.6
Storage Driver: devicemapper
 Pool Name: docker-202:1-395334-pool
 Pool Blocksize: 65.54 kB
 Base Device Size: 10.74 GB
 Backing Filesystem: xfs
 Data file: /dev/loop0
 Metadata file: /dev/loop1
 Data Space Used: 11.8 MB
 Data Space Total: 107.4 GB
 Data Space Available: 6.995 GB
 Metadata Space Used: 581.6 kB
 Metadata Space Total: 2.147 GB
 Metadata Space Available: 2.147 GB
 Thin Pool Minimum Free Space: 10.74 GB
 Udev Sync Supported: true
 Deferred Removal Enabled: false
 Deferred Deletion Enabled: false
 Deferred Deleted Device Count: 0
 Data loop file: /var/lib/docker/devicemapper/devicemapper/data
 WARNING: Usage of loopback devices is strongly discouraged for production use. Use `--storage-opt dm.thinpooldev` to specify a custom block storage device.
 Metadata loop file: /var/lib/docker/devicemapper/devicemapper/metadata
 Library Version: 1.02.93-RHEL7 (2015-01-28)
Logging Driver: json-file
Cgroup Driver: cgroupfs
Plugins:
 Volume: local
 Network: null host bridge overlay
Swarm: inactive
Runtimes: runc
Default Runtime: runc
Security Options:
Kernel Version: 4.4.41-36.55.amzn1.x86_64
Operating System: Amazon Linux AMI 2016.09
OSType: linux
Architecture: x86_64
CPUs: 1
Total Memory: 995.4 MiB
Name: ip-172-31-7-166
ID: WRPU:RER2:4KGB:4FJ3:SBFB:VEA5:UY3Q:IXXN:VJO5:3D3E:EY7U:DQPP
Docker Root Dir: /var/lib/docker
Debug Mode (client): false
Debug Mode (server): false
Registry: https://index.docker.io/v1/
Insecure Registries:
 127.0.0.0/8
```

Enabling Docker API REST on Docker Host to connect any Remote computer
Open the `/etc/default/docker` file, search for DOCKER_OPTS and add values
Ref: http://scriptcrunch.com/enable-docker-remote-api/ (Note: wrong file)

```sh
DOCKER_OPTS=' [...] -H tcp://0.0.0.0:4243 -H unix:///var/run/docker.sock'
```
