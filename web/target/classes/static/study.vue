<template>
<div id="container" class="container">
<!--  学习中心页面，为上、左、右结构，上为轮播图，展示学习人数最多的的5个课程，左为课程分类列表，右为选中的课程分类对应的课程列表-->
  <el-carousel class="img-wap" height="200px" interval="4000" type="card">
    <el-carousel-item v-for="item in urls" :key="item.id">
      <el-tooltip placement="top">
        <template #content>
          点击进入课程：{{ item.courseName }}
        </template>
        <el-image style="width: 100%; height: 100%" :src="item.courseAvatar" fit="fit" @click="goCourse(item.id)"></el-image>
      </el-tooltip>
    </el-carousel-item>
  </el-carousel>
  <div class="course-wrap">
    <el-card class="box-card">
      <el-menu
          :default-active="defaultAvtive"
          style="border: none"
          text-color="#303133"
          class="el-menu-vertical-demo course-menu">
        <el-menu-item index="999" route="" disabled="true">
          <template #title><span style="color:#e60a0a;font-size: 18px;font-weight: 800">课程分类</span></template>
        </el-menu-item>
        <el-menu-item v-for="(item, index) in classifyList" :key="index" :index="item.id" @click="queryCourse(item.id)">
<!--          <i class="el-icon-cpu"></i>-->
          <template #title>{{ item.classifyName }}</template>
        </el-menu-item>
      </el-menu>
    </el-card>
    <div class="course-list">
      <router-view :key="$route.fullPath"></router-view>
    </div>
  </div>
</div>
</template>

<script>
import courseList from './components/courseList.vue'
import {getAllClassify,getHot} from './mjs/course.mjs'
export default {
  name: "study",
  data(){
    return{
      urls:[],
      classifyList:[],
      classifyId: ''
    }
  },
  components:{
    courseList
  },
  computed:{
    defaultAvtive(){
      if (this.classifyList.length!==0)
        return this.classifyList[0].id
    }
  },
  created(){
    this.init()
  },
  methods:{
    async init(){
      const res = await getAllClassify()
      this.classifyList = res.result
      const path = '/study/myCourse/'+this.classifyList[0].id
      this.$router.replace({
        path: path
      })
      const res2 = await getHot()
      this.urls = res2.result
    },
    queryCourse(data){
      const path = '/study/myCourse/'+data
      this.$router.replace({
        path: path
      })
    },
    goCourse(data){
      const newPage = this.$router.resolve({path: '/courseDetail/'+data})
      window.open(newPage.href,'_blank')
      // this.$router.push({path: '/courseDetail/'+data})

    }
  }
}
</script>

<style scoped>
#container{
  width: 100%;
  margin-top: 20px;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
  box-sizing: border-box;
}

.img-wap{
  width: 60%;
}

.course-wrap{
  width: 60%;
  min-height: 400px;
  margin-top: 20px;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: flex-start;
  box-sizing: border-box;
}

.course-menu{
  width: 100%;
  min-height: 450px;
  /*background-color: #EBEEF5;*/
}

.course-list{
  width: 80%;
  min-height: 400px;
}
</style>