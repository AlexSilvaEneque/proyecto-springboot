package com.web.logincrud.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "iduser")
	private Integer id;
	private String email;
	private String password;
	private String first_name;
    private String last_name;
	private String phone;
	
	
//	@JoinTable(name = "user_has_rol",
//	        joinColumns = @JoinColumn(name="id_user", referencedColumnName = "iduser"),
//	        inverseJoinColumns = @JoinColumn(name="id_rol", referencedColumnName = "idrol"))
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "roles_has_users",
		joinColumns = @JoinColumn(name="iduser", referencedColumnName = "iduser"),
		inverseJoinColumns = @JoinColumn(name="idrol", referencedColumnName = "idrol"))
	private Set<RolModel> roles = new HashSet<>();
	
	public UserModel() {}

	public UserModel(Integer id, String email, String password, String first_name, String last_name, String phone,
            Set<RolModel> roles) {
        super();
        this.id = id;
        this.email = email;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone = phone;
        this.roles = roles;
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<RolModel> getRoles() {
		return roles;
	}

	public void setRoles(Set<RolModel> roles) {
		this.roles = roles;
	}

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
