import request from "./request.mjs";

export function test(){
    return request({
        url:"/bilibili",
        method:"get"
    })
}