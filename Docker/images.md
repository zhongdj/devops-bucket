## Image Layer

Docker images are the basis of containers - The **file system and configuration** of our application which are used to create containers

### Base images vs Child images.

* Base images are images that have no parent images, usually images with an OS like ubuntu, alpine or debian.

* Child images are images that build on base images and add additional functionality.

### Official images vs User images.

* Official images (`image-name`) Docker, Inc. sponsors a dedicated team that is responsible for reviewing and publishing all Official Repositories content. Examples: `python`, `node`, ...

* User images (`user/image-name`) are images created and shared by users like you. They build on base images and add additional functionality. Typically these are formatted as user/image-name. The user value in the image name is your Docker Cloud user or organization name. Example: `cloudbees/jenkins-enterprise`

### API commands

#### Explore them

```sh
> docker image --help
...
Commands:
  build       "Build an image from a Dockerfile"
  history     "Show the history of an image"
  import      "Import the contents from a tarball to create a filesystem image"
  inspect     "Display detailed information on one or more images"
  load        "Load an image from a tar archive or STDIN"
  ls          "List images"
  prune       "Remove unused images"
  pull        "Pull an image or a repository from a registry"
  push        "Push an image or a repository to a registry"
  rm          "Remove one or more images"
  save        "Save one or more images to a tar archive (streamed to STDOUT by default)"
  tag         "Create a tag TARGET_IMAGE that refers to SOURCE_IMAGE"

Run 'docker image COMMAND --help' for more information on a command.
```

#### Put them into practice

Pull

```sh
# Pull image TAG as 12.04
docker image pull ubuntu:12.04
# Pull image TAG as latest
docker image pull ubuntu
```

Dockerfile, building an image. Recommended: Crete a folder to host all the necessary files (configuration), including your Dockerfile. Then build it within that folder (.).

Resources at `Dockerfiles/HelloWorld_NodeJS`

```sh
# v0.1 - Default Dockerfile - docker image build -t <REPO>:<TAG>
> docker image build -t hello:v0.1 .
# v0.2 - Dockerfile-v2 - docker image build -f <DOCKERFILE> -t <REPO>:<TAG>
> docker image build -f Dockerfile-v2 -t hello:v0.2 .
# Then... list images
> docker image ls
REPOSITORY                TAG                 IMAGE ID            CREATED             SIZE
hello                     v0.2                e4db9d76288a        18 seconds ago      49 MB
hello                     v0.1                8ea763260609        15 minutes ago      36.9 MB
# Then... run the image version you wish...
docker container run hello:v0.1
```

List images docker image ls/docker images -a 

```sh
> docker images -a
REPOSITORY             TAG                 IMAGE ID            CREATED             SIZE
seqvence/static-site   latest              92a386b6e686        2 hours ago        190.5 MB
nginx                  latest              af4b3d7d5401        3 hours ago        190.5 MB
python                 2.7                 1c32174fd534        14 hours ago        676.8 MB
postgres               9.4                 88d845ac7a88        14 hours ago        263.6 MB
containous/traefik     latest              27b4e0c6b2fd        4 days ago          20.75 MB
node                   0.10                42426a5cba5f        6 days ago          633.7 MB
redis                  latest              4f5f397d4b7c        7 days ago          177.5 MB
mongo                  latest              467eb21035a8        7 days ago          309.7 MB
alpine                 3.3                 70c557e50ed6        8 days ago          4.794 MB
java                   7                   21f6ce84e43c        8 days ago          587.7 MB
```

##### Images info

Getting image FULL description JSON: Architecture, Os, Size, ContainerConfig, DockerVersion, Config, etc

```sh
# docker image inspect <IMAGE-ID>
> docker image inspect alpine
# ... JSON description
```

Extract portions from the full JSON
```sh
> docker image inspect --format "{{ json .RootFS.Layers }}" alpine | python -m json.tool
[
    "sha256:23b9c7b43573dd164619ad59e9d51eda4095926729f59d5f22803bcbe9ab24c2"
]
> docker image inspect --format "{{ .Architecture }}" alpine
amd64
```

##### Cleaning Images

Remove all images

```sh
> docker image rm $(docker image ls -q)
```

Cleaning dangling images (like "garbage collector")
```sh
docker rmi $(docker images -f "dangling=true" -q)
```
