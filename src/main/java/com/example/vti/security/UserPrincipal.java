package com.example.vti.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.vti.constraints.RoleType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPrincipal implements UserDetails{
	private Long userId;
	private String username;
	private String password;
	private Long departmentId;
	private String roleName;
	private Collection<? extends GrantedAuthority> authorities;
	
	@Override
    public String toString() {
        return "Username: " + username +
               ", Password: " + password +
               ", Department ID: " + departmentId +
               ", Role: " + roleName +
               ", Authorities: " + authorities;
    }
	
	public boolean isAdmin() {
		return RoleType.ADMIN.getValue().equals(this.getRoleName());
	}
}