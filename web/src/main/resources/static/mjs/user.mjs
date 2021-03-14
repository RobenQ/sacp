import request from "./request.mjs";

export function test(){
    return request({
        url:"/test",
        method:"get"
    })
}