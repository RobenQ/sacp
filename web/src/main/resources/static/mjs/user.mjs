import request from "./request.mjs";

export function login(data){
    return request({
        url:"/login",
        method:"post",
        data
    })
}