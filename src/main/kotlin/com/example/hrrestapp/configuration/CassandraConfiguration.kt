package com.example.hrrestapp.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.data.cassandra.config.AbstractReactiveCassandraConfiguration
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories

@Configuration
@EnableReactiveCassandraRepositories
class CassandraConfiguration: AbstractReactiveCassandraConfiguration() {

    override fun getKeyspaceName() = "hr"

    override fun getContactPoints()= "localhost"

    override fun getEntityBasePackages(): Array<String> = arrayOf("com.example.hrrestapp.domain")
}