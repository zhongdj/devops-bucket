## Docker Swarm

Docker Engine 1.12 includes swarm mode for natively managing a cluster of Docker Engines called a swarm. Use the Docker CLI to create a swarm, deploy application services to a swarm, and manage swarm behavior.

A stack is a group of service that are deployed together. The docker-stack.yml in the current folder will be used to deploy the voting app as a stack.

### Workflow

Create a Docker Swarm first > Copy the join command (watch out for newlines) and paste it in the other terminal. > Deploy the stack

### Operation

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

From Swarm manager check the number of nodes in the swarm (running this command from the second terminal worker will fail as swarm related commands need to be issued against a swarm manager).

```sh
> docker node ls
ID                           HOSTNAME  STATUS  AVAILABILITY  MANAGER STATUS
82uh2o8jdee00isf7hocv1mcm    node2     Ready   Active
9d1dixqcq2u5aucvpfkvynb2s *  node1     Ready   Active        Leader
```

From Swarm manager, deploy a stack

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
