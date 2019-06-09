package model

import javax.persistence.*

@Entity
data class User(@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
                @Column(name = "id")
                var id : Long? = null,
                @Column(name = "login")
                var login : String? = null,
                @Column(name = "password")
                var pass : String? = null)
