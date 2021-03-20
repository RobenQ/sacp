import request from "./request.mjs";

export function getToken(){
    return request({
        url:"/upload/avatar",
        method:"post"
    })
}