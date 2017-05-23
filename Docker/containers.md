## Containers

Containers - Running instances of Docker images — containers **run the actual applications**. A container includes an application and all of its dependencies. It shares the kernel with other containers, and runs as an isolated process in user space on the host OS

### Containers layer

The container layer is the read-write layer that is added to each container that is ran. This is the layer in which the changes applied are stored. This layer is deleted when the container is removed and thus cannot be used for persistent storage.

To recover container where you have made a change: Get its <Container_ID>, then use **start** `docker container start <CONTAINER_ID>`. Finally, access to it interactively (in case you need it)

### Volumes

Data is not persisted outside of a container by default. Volume bypasses the union filesystem and is not dependent on a container’s lifecycle.


Get Data Storage of an container (c1 in this example)

* Volumen NOT DEFINED
```sh
docker container inspect -f "{{ json .GraphDriver }}" c1 | python -m json.tool
{
    "Data": {
        "LowerDir": "/graph/overlay2/bd26f1eeb8924527772e844c6826aa9f68f0ba1e11e294fbe6f17be9eca2
31ef-init/diff:/graph/overlay2/5e90456d9b0f50b41a5ddeb28587362116dc1dca9ae2a5739b1efa18f2b236e4/d
iff",
        "MergedDir": "/graph/overlay2/bd26f1eeb8924527772e844c6826aa9f68f0ba1e11e294fbe6f17be9eca
231ef/merged",
        "UpperDir": "/graph/overlay2/bd26f1eeb8924527772e844c6826aa9f68f0ba1e11e294fbe6f17be9eca2
31ef/diff",
        "WorkDir": "/graph/overlay2/bd26f1eeb8924527772e844c6826aa9f68f0ba1e11e294fbe6f17be9eca23
1ef/work"
    },
    "Name": "overlay2"
}
```

* Volumen DEFINED
```sh
## 1. Get the volumen
> docker container inspect -f "{{ json .Mounts }}"  c1 | python -m json.tool
[
    {
        "Destination": "/data",
        "Driver": "local",
        "Mode": "",
        "Name": "dc07a44b028feb596df46623f752f1219e36077167d19b116f4799eae663fe2e",
        "Propagation": "",
        "RW": true,
        "Source": "/graph/volumes/dc07a44b028feb596df46623f752f1219e36077167d19b116f4799eae663fe2
e/_data",
        "Type": "volume"
    }
]
## 2. Get the data
> cd /graph/volumes/dc07a44b028feb596df46623f752f1219e36077167d19b116f4799eae663fe2
e/_data
```

### Volumen API command

Since Docker 1.9

Mount folder for a container. OST_PATH and CONTAINER_PATH can be a folder or file. HOST_PATH must exist before running this command.

```sh
> docker volume --help
Usage:  docker volume COMMAND

Manage volumes

Options:
      --help   Print usage

Commands:
  create      Create a volume
  inspect     Display detailed information on one or more volumes
  ls          List volumes
  prune       Remove all unused volumes
  rm          Remove one or more volumes

Run 'docker volume COMMAND --help' for more information on a command.
```

Bind-mounting is very useful in development as it enables, for instance, to share source code on the host with the container. For instance, take the /foo folder in the host OS and make it available in the Docker container at /bar. This way, you can use all the text editors, IDEs, and other tools you already have installed to make changes in /foo and you’ll see them reflected immediately in the Docker container in /bar

```sh
# docker container run -v HOST_PATH:CONTAINER_PATH [OPTIONS] IMAGE [CMD]
> docker run -v /foo:/bar brikis98/my-rails-app
```

Create a volume and mount it for a container
```sh
# 1 - Create volumen
> docker volume create --name html
html
# 2 - Inspect the volumen. Mountpoint defined here is the path on the Docker host where the volume can be accessed, using the name but not the ID
> docker volume inspect html
[
    {
        "Driver": "local",
        "Labels": {},
        "Mountpoint": "/graph/volumes/html/_data",
        "Name": "html",
        "Options": {},
        "Scope": "local"
    }
]
# 3 - Mount html it on /usr/share/nginx/html of a container. We will use a Nginx and  /usr/share/nginx/html is the default folder served
> docker container run --name www -d -p 8080:80 -v html:/usr/share/nginx/html nginx
```


### Container API commands

#### Explore them

```sh
> docker container --help

Commands:
  attach      "Attach to a running container"
  commit      "Create a new image from a containers changes"
  cp          "Copy files/folders between a container and the local filesystem"
  create      "Create a new container"
  diff        "Inspect changes to files or directories on a container filesystem"
  exec        "Run a command in a running container"
  export      "Export a containers filesystem as a tar archive"
  inspect     "Display detailed information on one or more containers"
  kill        "Kill one or more running containers"
  logs        "Fetch the logs of a container"
  ls          "List containers"
  pause       "Pause all processes within one or more containers"
  port        "List port mappings or a specific mapping for the container"
  prune       "Remove all stopped containers"
  rename      "Rename a container"
  restart     "Restart one or more containers"
  rm          "Remove one or more containers"
  run         "Run a command in a new container"
  start       "Start one or more stopped containers"
  stats       "Display a live stream of container(s) resource usage statistics"
  stop        "Stop one or more running containers"
  top         "Display the running processes of a container"
  unpause     "Unpause all processes within one or more containers"
  update      "Update configuration of one or more containers"
  wait        "Block until one or more containers stop then print their exit codes"

Run 'docker container COMMAND --help' for more information on a command
```

#### Put them into practice

##### Running Containers

Interactive (`docker container run -ti ...`) vs Backgroud (`docker container run -d ...`) where they can expose services like HTTP API, databases, etc.

Start running a container in background mode and then, jump into the running container. For this, we provide a name (`--name`) for the running container, later will use the `exec` command and the `-ti` options (to get an interactive `tty`).

```sh
# 1 - Running Backgroud mode using the –name option
> docker container run -d --name mongo mongo:3.2
...
9f5c4c4045321a20b24d9a9cd932de4d0e93bb7b033f1e6214b9af14ee3b2c17
# 2 - docker container exec -ti <CONTAINER_ID>/<CONTAINER_NAME> bash
>  docker container exec -ti mongo bash
```

You can also run a second container (in the same machine) at the same time from the same image, specifying a different custom host port 

```sh
docker container run --name static-site-2 -e AUTHOR="Your Name" -d -p 8888:80 seqvence/static-site
```

This example shows that we can create a container, add all the libraries and binaries in it and then commit this one in order to create an image. We can then use that image as we would do for any other images. This approach is not the recommended one as it is not very portable. The way to go is Dockerfile

```sh
# 1 Running an interactive shell in a ubuntu container
# docker container run -ti <CONTAINER-ID> bash/sh
> docker container run -ti ubuntu bash
Unable to find image 'ubuntu:latest' locally
latest: Pulling from library/ubuntu
aafe6b5e13de: Pull complete
...
Digest: sha256:f3a61450ae43896c4332bda5e78b453f4a93179045f20c8181043b26b5e79028
Status: Downloaded newer image for ubuntu:latest
# Alternatively, > docker container run -ti alpine sh
# 2 Tools
# 3 Close interactive mode
> exit
# 4 Get container ID
# 5 Commit your changes, using the ID retreived, in order to commit the container and create an image out of it.
# docker container commit CONTAINER_ID
> docker container commit CONTAINER_ID
# 6 Once it has been commited, we can see the newly created image in the list of available images.
> docker image ls
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
<none>              <none>              fb7289db5c19        6 minutes ago       157MB
ubuntu              latest              f7b3f317ec73        8 hours ago         117MB
# 7 Tag your images
# docker image tag IMAGE_ID TAG_NAME
> docker image tag fb7289db5c19  ourfiglet
# 8 run image
> docker image ls
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
ourfiglet           latest              fb7289db5c19        14 minutes ago      157MB
ubuntu              latest              f7b3f317ec73        8 hours ago         117MB
```

Exit container while remains running: `Control-P / Control-Q` 

##### Create a new image

```sh
# docker container commit CONTAINER_ID
> docker container commit df3c2b338013
sha256:fb7289db5c19737488a204986340f227c3c9b7f650e632a5b8dcae8a9aee39d5
```

##### Getting Info

List containers: docker ps / docker container ls. Adding `-a` shows all containers (including non running container)

```sh
> docker container ls
CONTAINER ID        IMAGE                  COMMAND                  CREATED             STATUS
    PORTS               NAMES
b28c6afea099        seqvence/static-site   "/bin/sh -c 'cd /u..."   5 seconds ago       Up 4 seconds
    80/tcp, 443/tcp     stoic_thompson
```

Getting just the <CONTAINER_ID> 
```sh
> docker container ls -aq
3ddd90af6a6d
7561e6f03ef4
```

Getting container FULL description JSON: State, HostConfig, Config, NetworkSettings, ...

```sh
# docker inspect <CONTAINER_ID>
> docker inspect b28c6afea099
# ... JSON description
```

Extract portions from the full JSON

Getting just network
```sh
> docker container port static-site 
443/tcp -> 0.0.0.0:32772
80/tcp -> 0.0.0.0:32773
```

Copy file from Containers > Host 
```sh
# docker cp <containerId>:/file/path/within/container /host/path/target
> docker cp my-apache-app:usr/local/apache2/conf/httpd.conf .
```

##### Cleaning Containers

Stop > Remove all container (Graceful way)
```sh
> docker container stop $(docker container ls -aq)
> docker container rm $(docker container ls -aq)
```

Stop a Container

```sh
# docker stop <CONTAINER-ID>
> docker stop b28c6afea099
b28c6afea099
```

Operate in a container by pattern (i.e. running in the port 8081). Then you can run `docker stop`, `docker rm` 

```sh
> docker ps -a | grep '9081->' | awk '{print $1}' | xargs docker rm
52a9510b0001
```
