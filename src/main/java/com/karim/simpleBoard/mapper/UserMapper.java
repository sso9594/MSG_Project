package com.karim.simpleBoard.mapper;

import com.karim.simpleBoard.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by sblim
 * Date : 2021-12-14
 * Time : 오후 3:46
 */
@Mapper//유저 정보를 DB에 저장하기 위한 mapper인터페이스
public interface UserMapper {
    UserVo getUserAccount(String userId);

    void saveUser(UserVo userVo);
}
