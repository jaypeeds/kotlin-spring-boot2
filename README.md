# kotlin-spring-boot2
Simple REST API with data persisted in Cassandra, setup as a docker container

# Docker setup
- Setup docker for your desktop
- Go to http://hub.docker.com/

# Cassandra
- Search for Cassandra, and identify the version you'll use for example 3.11.2
- At a comamnd prompt, enter:
```
docker pull cassandra:3.11.2
```
# Docker startup
Make sure docker daemon is running on your desktop

# Cassandra container startup
```
docker run --name cassandra -p 9042:9042 cassandra:3.11.2
```
Note: The name cassandra might be already use, so ignore the option or change the name

# Cassandra command line console launch (aka cqlsh)
```
docker exec -it cassandra cqlsh
```
Note: In the above, cassandra name might be already used so you need to enter 'docker ps' to identify started containers and use the short hex id in place of cassandra.

# Cassandra schema creation
```
docker exec -it 7950adb94b49 cqlsh
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
