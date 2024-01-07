package com.sdt.proshop.config

import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
abstract class AbstractTestContainers {

    companion object {
        @JvmStatic
        @Container
        var mongoDBContainer: MongoDBContainer = MongoDBContainer("mongo:latest").withExposedPorts(27018)

        @JvmStatic
        @DynamicPropertySource
        fun registerDBContainer(registry: DynamicPropertyRegistry) {
            registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl)
        }
    }

}