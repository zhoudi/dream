package com.pkgplan.auth

import com.pkgplan.dream.Profile

class User {

	transient springSecurityService

	String username
	String password
    String email
    Date dateCreated
    Date dateExpired = new Date()
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

    Profile profile

	static constraints = {
		username blank: false, unique: true
		password blank: false
        email unique: false
        profile(nullable: true)
	}

	static mapping = {
        table('pkguser')
		password column: '`password`'
        profile(lazy: false)
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

    String toString() {
        return this.username
    }

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}
}