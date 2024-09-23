package com.example.SpringJWt.Configure;

import com.example.SpringJWt.Repo.UserInfoRepo;
import com.example.SpringJWt.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserInfoRepo repo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Optional<UserInfo> UserInfo =repo.findByName(username);
        return UserInfo.map(UserDetailsUsrInfo::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not Found"));
    }
}
