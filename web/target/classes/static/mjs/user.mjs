import request from "./request.mjs";

export function login(data){
    return request({
        url:"/login",
        method:"post",
        data
    })
}

export function getUserInfo(data){
    return request({
        url:"/getUserInfo",
        method:"get",
        params:{
            sacpId:data
        }
    })
}

export function modifyPassword(data){
    return request({
        url:"/modifyPassword",
        method:"post",
        data
    })
}