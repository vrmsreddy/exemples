package com.hyzx.xschool.domain.repository;

import com.hyzx.xschool.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author royguo
 */
public interface UserRepository extends JpaRepository<User, Long> {

  User findByNickname(String nickname);

  User findByNicknameAndTypeIn(String nickname, List<Integer> userTypes);

  Page<User> findByMobile(String mobile, Pageable pageable);

  Page<User> findByNicknameContaining(String nick, Pageable pageable);

  Page<User> findByNicknameContainingAndMobile(String nick, String mobile, Pageable pageable);
}
