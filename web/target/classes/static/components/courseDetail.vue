<template>
<div id="courseDetail-wrap">
  <div class="courseInfo-warp">
    <div class="course-face">
      <el-image
          style="width: 100%; height: 100%"
          src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
          fit="cover">
      </el-image>
    </div>
    <div class="course-info">
      <div id="course-name">Java进阶</div>
      <div id="course-teacher">
        <span class="el-icon-user" style="margin-right: 5px"></span>周老师
      </div>
      <div>
        <el-button class="btn" type="primary">学习课程</el-button>
        <el-button class="btn" type="warning" @click="goCommunicate">讨论区</el-button>
      </div>
      <div id="course-study">已有 {{study}} 人在学习</div>
    </div>
  </div>

<!--  信息部分-->
  <div class="course-datail">
    <el-tabs type="border-card">
      <el-tab-pane label="课程简介"><div style="width: 100%" v-html="courseDetail"></div></el-tab-pane>
      <el-tab-pane label="学习视频"><courseVideo></courseVideo></el-tab-pane>
      <el-tab-pane label="资源下载"><courseResource></courseResource></el-tab-pane>
      <el-tab-pane label="评论">评论</el-tab-pane>
    </el-tabs>
  </div>
</div>
</template>

<script>
import {test} from '../mjs/course.mjs'
import courseVideo from "./courseVideo.vue";
import courseResource from "./courseResource.vue";
export default {
  name: "courseDetail",
  data(){
    return{
      study:2617,
      courseDetail:''
    }
  },
  components:{
    courseVideo,
    courseResource
  },
  created(){
    // test().then(res=>{
    //   this.courseDetail = res
    // }).catch(error => {
    //   console.log(error);
    // })
  },
  methods:{
    goCommunicate(){
      const newPage = this.$router.resolve({path: '/courseBlock'})
      window.open(newPage.href,'_blank')
      // this.$router.push({path: '/courseDetail'})
    }
  }
}
</script>

<style scoped>
#courseDetail-wrap{
  width: 100%;
  margin-top: 20px;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
  box-sizing: border-box;
}
.courseInfo-warp{
  width: 60%;
  height: 250px;
  margin-top: 20px;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
  box-sizing: border-box;
}
.course-face{
  width: 400px;
  height: 250px;
  margin-right: 30px;
}

.course-info{
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: flex-start;
  box-sizing: border-box;
}
#course-name{
  font-size: 25px;
  color: #303133;
}
#course-teacher{
  margin-top: 10px;
}
#course-study{
  margin-top: 10px;
  color: #909399;
  font-size: 12px;
}
.btn{
  margin-top: 50px;
}
.course-datail{
  width: 60%;
  margin-top: 50px;
  min-height: 200px;

}
</style>