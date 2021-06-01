<template>
  <div id="courseList-wrap">
    <!--  学习中心的课程列表，通过路由参数传递课程分类ID，展示对应分类的课程列表-->
    <div id="course-container">
      <div class="card-wrap" v-for="item in courseList">
        <el-card :body-style="{ padding: '0px' }" shadow="hover" class="card">
          <img :src="item.courseAvatar" class="image">
          <div style="padding: 14px;">
            <span>{{ item.courseName }}</span>
            <div class="bottom">
              <div>
                <span class="el-icon-user" style="margin-right: 5px"></span>
                <time class="time">{{ item.memberName }}</time>
              </div>
              <el-button type="primary" class="button" size="mini" @click="courseDetail(item.id)">课程详情</el-button>
            </div>
          </div>
        </el-card>
      </div>
    </div>
    <div id="page">
      <el-pagination
          background
          :page-size="pageSize"
          layout="prev, pager, next"
          :total="totalPage"
          :current-page="currentPage"
          hide-on-single-page="true"
          @prev-click="change"
          @next-click="change">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import {getTotalPage2, getUserCourse2} from "../mjs/course.mjs";

export default {
  name: "courseList",
  data(){
    return{
      currentDate: '周老师',
      pageSize:12,
      currentPage:1,
      totalPage: 1,
      courseList:[]
    }
  },
  created(){
    this.init()
  },
  methods:{
    async init(){
      const classifyId = this.$route.params.classifyId
      const res2 = await getTotalPage2(classifyId)
      const res3 = res2.result
      console.log(res3)
      const a = res2/this.pageSize
      const b = res2%this.pageSize
      if (b!=0)
        this.totalPage = a+1
      else
        this.totalPage = a
      const data = {
        classifyId:this.$route.params.classifyId,
        currentPage:this.currentPage,
        pageSize:this.pageSize
      }
      this.getCourse(data)
    },
    async getCourse(data){
      const courses = await getUserCourse2(data)
      this.courseList = courses.result
      console.log(this.courseList)
    },
    change(){
      const data = {
        classifyId:this.$route.params.classifyId,
        currentPage:this.currentPage,
        pageSize:this.pageSize
      }
      this.getCourse(data)
    },
    courseDetail(data){
      const newPage = this.$router.resolve({path: '/courseDetail/'+data})
      window.open(newPage.href,'_blank')
      // this.$router.push({path: '/courseDetail/'+data})

    }
  }
}
</script>

<style scoped>

#courseList-wrap{
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  box-sizing: border-box;
}

#course-container{
  width: 100%;
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: flex-start;
  align-items: flex-start;
  box-sizing: border-box;
}

#page{
  margin-top: 20px;
}

#course-container>.card-wrap:nth-child(n){
  margin-left: 10px;
  margin-bottom: 10px;
}

#course-container>.card-wrap:nth-child(1){
  margin-top: 0px;
}
#course-container>.card-wrap:nth-child(2){
  margin-top: 0px;
}
#course-container>.card-wrap:nth-child(3){
  margin-top: 0px;
}

.card-wrap{
  width: 232px;
}

.time {
  font-size: 13px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.image {
  width: 100%;
  height: 150px;
  display: block;
}
</style>