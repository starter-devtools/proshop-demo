package com.sdt.proshop.config

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class TestContainersIT: AbstractTestContainers() {

//    @Autowired
//    protected lateinit var mongoTemplate: MongoTemplate
    @Test
    fun test() {
        assertThat(mongoDBContainer.isCreated).isTrue()
        assertThat(mongoDBContainer.isRunning).isTrue()
    }

}