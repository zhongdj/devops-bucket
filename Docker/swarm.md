## Docker Swarm Mode

## Docker Swarm

Docker Engine 1.12 includes swarm mode for natively managing a cluster of Docker Engines called a swarm. Use the Docker CLI to create a swarm, deploy application services to a swarm, and manage swarm behavior.

A stack is a group of service that are deployed together. The docker-stack.yml in the current folder will be used to deploy the voting app as a stack.

Since Docker 1.13.1 and compose 1.11.1 : Start securing your swarm services using the latest compose reference that allows to specify secrets in your application stack

### Docker Swarm

Create a cluster of Dockers.

List commands 
```sh
> docker swarm --help

Usage:  docker swarm COMMAND

Manage Swarm

Options:
      --help   Print usage

Commands:
  init        Initialize a swarm
  join        Join a swarm as a node and/or manager
  join-token  Manage join tokens
  leave       Leave the swarm
  unlock      Unlock swarm
  unlock-key  Manage the unlock key
  update      Update the swarm

Run 'docker swarm COMMAND --help' for more information on a command.
```


create a Docker Swarm first

```sh
> docker swarm init --advertise-addr $(hostname -i)
Swarm initialized: current node (9d1dixqcq2u5aucvpfkvynb2s) is now a manager.

To add a worker to this swarm, run the following command:

    docker swarm join \
    --token SWMTKN-1-39x38r7rge07bbgf52wpxlb6klshuzifp9z0q5x63ngn76lk8u-9t5gb8a6yfa5khem8j1vwegaz \
    10.0.60.3:2377
```

Adding new node (machines) to the swarm created above

```sh
> docker swarm join \
--token SWMTKN-1-39x38r7rge07bbgf52wpxlb6klshuzifp9z0q5x63ngn76lk8u-9t5gb8a6yfa5khem8j1vwegaz \
10.0.60.3:2377
```

From Swarm manager/leader check the number of nodes in the swarm (running this command from the second terminal worker will fail as swarm related commands need to be issued against a swarm manager).

```sh
> docker node ls
ID                           HOSTNAME  STATUS  AVAILABILITY  MANAGER STATUS
82uh2o8jdee00isf7hocv1mcm    node2     Ready   Active
9d1dixqcq2u5aucvpfkvynb2s *  node1     Ready   Active        Leader
```

From Swarm manager/leader, deploy a stack

```sh
> docker stack deploy --compose-file=docker-stack.yml voting_stack
Creating network voting_stack_default
Creating network voting_stack_backend
Creating network voting_stack_frontend
Creating service voting_stack_visualizer
Creating service voting_stack_redis
Creating service voting_stack_db
Creating service voting_stack_vote
Creating service voting_stack_result
Creating service voting_stack_worker
```

Check the stack deployed from the first terminal.The output (in the below example) indicates the 6 services of the voting app’s stack (named voting_stack) have been deployed.

```sh
> docker stack ls
NAME          SERVICES
voting_stack  6
```

List services

```sh
> docker stack services voting_stack
ID            NAME                     MODE        REPLICAS  IMAGE
10rt1wczotze  voting_stack_visualizer  replicated  1/1       dockersample
s/visualizer:stable
8lqj31k3q5ek  voting_stack_redis       replicated  2/2       redis:alpine
nhb4igkkyg4y  voting_stack_result      replicated  2/2       dockersample
s/examplevotingapp_result:before
nv8d2z2qhlx4  voting_stack_db          replicated  1/1       postgres:9.4
ou47zdyf6cd0  voting_stack_vote        replicated  2/2       dockersample
s/examplevotingapp_vote:before
rpnxwmoipagq  voting_stack_worker      replicated  1/1       dockersample
s/examplevotingapp_worker:latest
```

Let’s list the tasks related to a services (i.e voting_stack_vote)
```sh
> docker service ps voting_stack_vote
ID            NAME                 IMAGE
      NODE   DESIRED STATE  CURRENT STATE           ERROR  PORTS
my7jqgze7pgg  voting_stack_vote.1  dockersamples/examplevotingapp_vote:be
fore  node1  Running        Running 56 seconds ago
3jzgk39dyr6d  voting_stack_vote.2  dockersamples/examplevotingapp_vote:be
fore  node2  Running        Running 58 seconds ago
```

### Docker services

Operate with the services, including scaling up or down the number of replicas.

List commands 
```sh
> docker service --help

Usage:	docker service COMMAND

Manage services

Options:
      --help   Print usage

Commands:
  create      Create a new service
  inspect     Display detailed information on one or more services
  logs        Fetch the logs of a service
  ls          List services
  ps          List the tasks of a service
  rm          Remove one or more services
  scale       Scale one or multiple replicated services
  update      Update a service

Run 'docker service COMMAND --help' for more information on a command.
```
## Docker node

Operate with nodes individually.

List commands 
```sh
> docker node --help

Usage:	docker node COMMAND

Manage Swarm nodes

Options:
      --help   Print usage

Commands:
  demote      Demote one or more nodes from manager in the swarm
  inspect     Display detailed information on one or more nodes
  ls          List nodes in the swarm
  promote     Promote one or more nodes to manager in the swarm
  ps          List tasks running on one or more nodes, defaults to current node
  rm          Remove one or more nodes from the swarm
  update      Update a node

Run 'docker node COMMAND --help' for more information on a command.
```
