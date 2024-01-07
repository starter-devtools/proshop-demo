package com.sdt.proshop.config

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class TestContainersIT: AbstractTestContainers() {

//    @Autowired
//    protected lateinit var mongoTemplate: MongoTemplate

    companion object {
        @JvmStatic
        @BeforeAll
        fun beforeAll() {
            mongoDBContainer.start()
        }

        @JvmStatic
        @AfterAll
        fun afterAll() {
            mongoDBContainer.stop()
        }
    }

    @Test
    fun test() {
        assertThat(mongoDBContainer.isCreated).isTrue()
        assertThat(mongoDBContainer.isRunning).isTrue()
    }

}