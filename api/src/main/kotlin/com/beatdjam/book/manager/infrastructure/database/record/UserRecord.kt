/*
 * Auto-generated file. Created by MyBatis Generator
 */
package com.beatdjam.book.manager.infrastructure.database.record

import com.beatdjam.book.manager.domain.RoleType

data class UserRecord(
    var id: Long? = null,
    var email: String? = null,
    var password: String? = null,
    var name: String? = null,
    var roleType: RoleType? = null
)