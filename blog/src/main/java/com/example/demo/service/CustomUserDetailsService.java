// 20250708 add

package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

@Service  // Spring にこのクラスがサービスとして自動登録されるようにする
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Spring Security がログイン時に呼び出すメソッド。
     * ユーザー名をもとにデータベースからユーザー情報を取得し、
     * Spring Security の形式（UserDetails）に変換して返す。
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // ユーザー名からDB検索
        User user = userRepository.findByUsername(username);

        if (user == null) {
            // ユーザーが見つからない場合は例外を投げる
            throw new UsernameNotFoundException("ユーザーが見つかりません: " + username);
        }

        // 認証・権限管理を行うためのユーザー情報（UserDetails）を返却
        return new org.springframework.security.core.userdetails.User(
        	    user.getUsername(),
        	    user.getPassword(),
        	    Collections.singleton(new SimpleGrantedAuthority(user.getRole()))
        	);
    }
}
