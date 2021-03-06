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

# Save locally your changes to images
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
# Using a volume or data-only container
- Create a logical volume
```
$ docker volume create db-setup
```
- Run a container and mount the volume at /db-setup mountpoint.
```
$ docker run -d -p9042:9042 --name democass --mount source=db-setup,target=/db-setup demo-cassandra
```
- Copy a script from desktop to mounted volume
```
$ docker container cp \desktop\path\to\db-init.sql democass:/db-setup
```
- Invoke cqlsh to run the script and prepare the schema
```
$ docker exec -it democass cqlsh -f /db-setup/db-init.sql
```
- Stop the container
```
$ docker kill democass
```
- Restart it and continue working with prepared database schema
```
$ docker start democass
$ docker exec -it democass cqlsh
Connected to Test Cluster at 127.0.0.1:9042.
[cqlsh 5.0.1 | Cassandra 3.11.2 | CQL spec 3.4.4 | Native protocol v4]
Use HELP for help.
cqlsh> use hr;
cqlsh:hr> select * from employee;

 id | age | department | name | salary
----+-----+------------+------+--------

(0 rows)
cqlsh:hr>
```
- Stop the container, remove it, as well as the volume
```
$ docker kill democass
$ docker rm democass
$ docker volume rm db-setup
```
