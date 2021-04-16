package com.sacp.member.core.repository;

import com.sacp.member.core.entity.Account;
import com.sacp.member.core.entity.AccountExample;
import com.sacp.member.core.mapper.AccountMapper;
import com.sacp.member.core.util.SacpIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class MemberRepository {

    @Autowired
    private AccountMapper accountMapper;

    public long countMember(){
        AccountExample example = new AccountExample();
        example.createCriteria().andIdIsNotNull();
        return accountMapper.countByExample(example);
    }

    public boolean updatePasswordBysacpId(String sacpId,String newPassword){
        AccountExample example = new AccountExample();
        example.createCriteria().andSacpIdEqualTo(sacpId);
        Account account = new Account();
        account.setPassword(newPassword);
        account.setModifyTime(new Date());
        int i = accountMapper.updateByExampleSelective(account, example);
        return i==1?true:false;
    }

    public boolean updateStatusBysacpId(String sacpId,Integer status){
        AccountExample example = new AccountExample();
        example.createCriteria().andSacpIdEqualTo(sacpId);
        Account account = new Account();
        account.setStatus(status);
        account.setModifyTime(new Date());
        int i = accountMapper.updateByExampleSelective(account, example);
        return i==1?true:false;
    }

    public Account getInfoByNickName(String nickName){
        AccountExample example = new AccountExample();
        example.createCriteria().andNickNameEqualTo(nickName);
        List<Account> accounts = accountMapper.selectByExample(example);
        if (accounts.size()==0)
            return null;
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

    public boolean insertMemebrt(Account account){
        int i = accountMapper.insertSelective(account);
        return i==1?true:false;
    }
}
