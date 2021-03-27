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

export function getVideoByCourseId(data){
    return request({
        url:"/getVideoByCourseId",
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

export function deleteVideo(data){
    return request({
        url:"/deleteVideo",
        method:"get",
        params:{
            videoId:data
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