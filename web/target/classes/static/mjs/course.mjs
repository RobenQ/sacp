import request from "./request.mjs";

export function getAllClassify(){
    return request({
        url:"/getAllClassify",
        method:"get"
    })
}

//sacpId
export function getTotalPage(data){
    return request({
        url:"/getTotalPage",
        method:"get",
        params:{
            sacpId:data
        }
    })
}

export function getTotalPage2(data){
    return request({
        url:"/getTotalPage2",
        method:"get",
        params:{
            classifyId:data
        }
    })
}

export function createCourse(data){
    return request({
        url:"/createCourse",
        method:"post",
        data
    })
}

//sacpId分页查询
export function getUserCourse(data){
    return request({
        url:"/getUserCourse",
        method:"post",
        data
    })
}

export function getUserCourse2(data){
    return request({
        url:"/getUserCourse2",
        method:"post",
        data
    })
}

export function getCourseById(data){
    return request({
        url:"/getCourseById",
        method:"post",
        data
    })
}

export function addVideo(data){
    return request({
        url:"/addVideo",
        method:"post",
        data
    })
}

export function addRes(data){
    return request({
        url:"/addRes",
        method:"post",
        data
    })
}

export function getVideoByCourseId(data){
    return request({
        url:"/getVideoByCourseId",
        method:"post",
        data
    })
}

export function getResByCourseId(data){
    return request({
        url:"/getResByCourseId",
        method:"post",
        data
    })
}

export function getTotalVideoPage(data){
    return request({
        url:"/getTotalVideoPage",
        method:"get",
        params:{
            courseId:data
        }
    })
}

export function getTotalResPage(data){
    return request({
        url:"/getTotalResPage",
        method:"get",
        params:{
            courseId:data
        }
    })
}

export function deleteVideo(data){
    return request({
        url:"/deleteVideo",
        method:"get",
        params:{
            videoId:data
        }
    })
}

export function deleteRes(data){
    return request({
        url:"/deleteRes",
        method:"get",
        params:{
            resId:data
        }
    })
}

export function getAllVideo(data){
    return request({
        url:"/getAllVideo",
        method:"get",
        params:{
            courseId:data
        }
    })
}

export function getAllRes(data){
    return request({
        url:"/getAllRes",
        method:"get",
        params:{
            courseId:data
        }
    })
}

export function downloadRes(data){
    return request({
        url:data.url,
        method:"get",
        params:{
            attname:data.name
        }
    })
}

export function joinCourse(data){
    return request({
        url:"/joinCourse",
        method:"post",
        data
    })
}

export function addDiscussion(data){
    return request({
        url:"/addDiscussion",
        method:"post",
        data
    })
}

export function getReply(data){
    return request({
        url:"getReply",
        method:"get",
        params:{
            courseId:data
        }
    })
}

export function getJoinMb(data){
    return request({
        url:"getJoinMb",
        method:"get",
        params:{
            sacpId:data
        }
    })
}

export function getJoinTotalPage(data){
    return request({
        url:"/getJoinTotalPage",
        method:"get",
        params:{
            sacpId:data
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

export function outCourse(data){
    return request({
        url:"/outCourse",
        method:"post",
        data
    })
}

export function getHot(){
    return request({
        url:"/hotCourse",
        method:"get"
    })
}

export function getNew(){
    return request({
        url:"/newCourse",
        method:"get"
    })
}