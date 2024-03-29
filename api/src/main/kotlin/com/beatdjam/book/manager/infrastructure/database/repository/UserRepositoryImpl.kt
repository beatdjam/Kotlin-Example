package com.beatdjam.book.manager.infrastructure.database.repository

import com.beatdjam.book.manager.domain.model.User
import com.beatdjam.book.manager.domain.repository.UserRepository
import com.beatdjam.book.manager.infrastructure.database.mapper.UserDynamicSqlSupport
import com.beatdjam.book.manager.infrastructure.database.mapper.UserMapper
import com.beatdjam.book.manager.infrastructure.database.mapper.selectByPrimaryKey
import com.beatdjam.book.manager.infrastructure.database.mapper.selectOne
import com.beatdjam.book.manager.infrastructure.database.record.UserRecord
import org.mybatis.dynamic.sql.SqlBuilder.isEqualTo
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl(private val mapper: UserMapper) : UserRepository {
    override fun find(email: String): User? = mapper
        .selectOne { where(UserDynamicSqlSupport.User.email, isEqualTo(email)) }
        ?.let(::toModel)

    override fun find(id: Long): User? = mapper.selectByPrimaryKey(id)?.let(::toModel)

    private fun toModel(record: UserRecord) = User(
        record.id!!,
        record.email!!,
        record.password!!,
        record.name!!,
        record.roleType!!
    )
}