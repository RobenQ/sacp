import request from "./request.mjs";

export function getBlockInfo(data){
    return request({
        url:"/getBlockInfo",
        method:"get",
        params:{
            blockId:data
        }
    })
}

export function getJoinCourse(data){
    return request({
        url:"/getJoinCourse",
        method:"post",
        data
    })
}

export function postPost(data){
    return request({
        url:"/postPost",
        method:"post",
        data
    })
}

export function getTop5(data1,data2){
    return request({
        url:"/getTop5",
        method:"get",
        params:{
            blockId:data1,
            sacpId:data2
        }
    })
}

export function getPostByPage(data){
    return request({
        url:"/getPostByPage",
        method:"post",
        data
    })
}

export function getPost(data){
    return request({
        url:"/getPost",
        method:"get",
        params:{
            postId:data
        }
    })
}

export function replyPost(data){
    return request({
        url:"/replyPost",
        method:"post",
        data
    })
}

export function getPostReply(data1,data2){
    return request({
        url:"/getPostReply",
        method:"get",
        params:{
            postId:data1,
            sacpId:data2
        }
    })
}

export function getPostTop5BySacpId(data){
    return request({
        url:"/getPostBySacpId",
        method:"get",
        params:{
            sacpId:data
        }
    })
}

export function getPostByPage2(data){
    return request({
        url:"/getPostByPage2",
        method:"post",
        data
    })
}

export function deletePost(data){
    return request({
        url:"/deletePost",
        method:"post",
        data
    })
}

export function getReplyBySacpId(data){
    return request({
        url:"/getReplyBySacpId",
        method:"get",
        params:{
            sacpId:data
        }
    })
}

export function deleteReply(data){
    return request({
        url:"/deleteReply",
        method:"post",
        data
    })
}

export function likePost(data){
    return request({
        url:"/likePost",
        method:"post",
        data
    })
}

export function likeReply(data){
    return request({
        url:"/likeReply",
        method:"post",
        data
    })
}

export function unLikePost(data){
    return request({
        url:"/unLikePost",
        method:"post",
        data
    })
}

export function unLikeReply(data){
    return request({
        url:"/unLikeReply",
        method:"post",
        data
    })
}