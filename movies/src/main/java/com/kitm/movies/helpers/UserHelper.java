package com.kitm.movies.helpers;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;

import com.kitm.movies.security.services.UserDetailsImpl;

public class UserHelper {
	public static String getCurrentUserName() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

	public static Long getCurrentUserId() {
		return ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
	}

	public static List<String> getCurrentUserRoles() {
		return ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAuthorities()
				.stream()
				.map(grantedAuthority -> grantedAuthority.getAuthority())
				.toList();
	}
	
	public static boolean isUserAdmin() {
		List<String> roles = getCurrentUserRoles();
		return roles.contains("admin");
	}

	public static boolean isUserRegular() {
		List<String> roles = getCurrentUserRoles();
		return roles.contains("user");
	}
}
