# kotlin-spring-boot2
Simple REST API with data persisted in Cassandra, setup as a docker container

# Docker setup
- Setup docker for your desktop

# Cassandra
- Go to http://hub.docker.com/
- Search for Cassandra, and identify the version you'll use for example 3.11.2
- At a command prompt, enter:
```
$ docker pull cassandra:3.11.2
```
# Docker startup
Make sure docker daemon is running on your desktop

# Cassandra container startup
```
$ docker run --name cassandra -p 9042:9042 cassandra:3.11.2
```
Note: The name cassandra might be already used, so ignore the option or change the name. See docker ps -aq, docker kill, docker rm commands to resolve the conflicts.

# Cassandra command line console launch (aka cqlsh)
```
$ docker exec -it cassandra cqlsh
```
Note: In the above, cassandra name might be already used so you need to enter 'docker ps' to identify started containers and use the short hex id in place of cassandra.

# Cassandra schema creation
```
$ docker exec -it 7950adb94b49 cqlsh
Connected to Test Cluster at 127.0.0.1:9042.
[cqlsh 5.0.1 | Cassandra 3.11.2 | CQL spec 3.4.4 | Native protocol v4]
Use HELP for help.
cqlsh> CREATE SCHEMA hr WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};
cqlsh> USE hr;
cqlsh:hr> CREATE TABLE employee(id int primary key, name text, age int, department text, salary double);
cqlsh:hr> INSERT INTO employee (id, name, age, department, salary) VALUES ( 1, 'John', 30, 'Sales', 100.0);
cqlsh:hr> SELECT * FROM employee;

 id | age | department | name | salary
----+-----+------------+------+--------
  1 |  30 |      Sales | John |    100

(1 rows)
cqlsh:hr>
```

# Save locally your changes to image
Note: This does not work for data. A data container should be used instead.
```
$ docker commit 7950adb94b49 demo-cassandra
$ docker images
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
demo-cassandra      latest              3ee5ac61f7b6        11 seconds ago      324MB
cassandra           3.11.2              c6b513da2ff3        3 weeks ago         323MB
cassandra           latest              c6b513da2ff3        3 weeks ago         323MB
```

# Stop and cleanup
```
$ docker kill 7950adb94b49
$ docker rm 7950adb94b49
```
