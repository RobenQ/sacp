package com.sacp.member.client.api;

import com.sacp.member.client.request.MemberRequest;
import com.sacp.member.client.response.LoginResponse;
import com.sacp.member.client.response.MemberResponse;

import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * 用户账号系统外部dubbo接口
 * @author zhoquing
 * @date 2021/04/02
 */
public interface MemberApi {
    /**
     * 根据用户昵称获取用户登录信息，一般用作登录返回对象
     * @param nickName 用户昵称
     * @return
     */
    public LoginResponse getAuthInfo(String nickName);

    /**
     * 根据请求字段获取用户，字段是动态的，增加一个字段就增加了一个查询匹配条件
     * @param request 查询请求对象
     * @return 查询到的账户列表，当查询单个账户信息时需要调用者自行判断列表是否是空
     */
    public List<MemberResponse> getAccount(MemberRequest request);

    /**
     * 注册账号，调用者不需要自行加密用户密码
     * @param request 注册账号所需信息
     * @return 是否注册成功
     * @throws NoSuchAlgorithmException 加密用户密码时加密失败异常
     */
    public boolean createMember(MemberRequest request) throws NoSuchAlgorithmException;

    /**
     * 修改登录密码，需要调用者自行进行业务判断，此接口不做任何业务逻辑判断
     * @param sacpId sacp平台用户ID
     * @param newPassword 新密码
     * @return 密码是否修改成功
     */
    public boolean modifyPassword(String sacpId,String newPassword);

    /**
     * 修改用户账户状态
     * @param sacpId sacp平台用户ID
     * @param status 新状态
     * @return 用户账户状态是否修改成功
     */
    public boolean modifyStatus(String sacpId,Integer status);

    /**
     * 获取所有会员的数量
     * @return 所有会员的数量值
     */
    public long countMember();
}
