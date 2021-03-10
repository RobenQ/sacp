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

    public List<Account> getAccount(Account account){
        AccountExample example = new AccountExample();
        AccountExample.Criteria criteria = example.createCriteria();
        if (account.getId()!=null && account.getId()!=0)
            criteria.andIdEqualTo(account.getId());
        if (account.getSacpId()!=null)
            criteria.andSacpIdEqualTo(account.getSacpId());
        if (account.getNickName()!=null)
            criteria.andNickNameEqualTo(account.getNickName());
        if (account.getGender()!=null)
            criteria.andGenderEqualTo(account.getGender());
        if (account.getStatus()!=null)
            criteria.andStatusEqualTo(account.getStatus());
        return accountMapper.selectByExample(example);
    }
}
