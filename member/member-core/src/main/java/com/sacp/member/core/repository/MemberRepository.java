package com.sacp.member.core.repository;

import com.sacp.member.core.entity.Account;
import com.sacp.member.core.entity.AccountExample;
import com.sacp.member.core.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MemberRepository {

    @Autowired
    private AccountMapper accountMapper;

    public Account getInfoByNickName(String nickName){
        AccountExample example = new AccountExample();
        example.createCriteria().andNickNameEqualTo(nickName);
        List<Account> accounts = accountMapper.selectByExample(example);
        return accounts.get(0);
    }
}
