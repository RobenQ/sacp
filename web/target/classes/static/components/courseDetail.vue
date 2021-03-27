<template>
<div id="courseDetail-wrap">
  <div class="courseInfo-warp">
    <div class="course-face">
      <el-image
          style="width: 100%; height: 100%"
          :src="courseInfo.course.courseAvatar"
          fit="cover">
      </el-image>
    </div>
    <div class="course-info">
      <div id="course-name">{{courseInfo.course.courseName}}</div>
      <div id="course-teacher">
        <span class="el-icon-user" style="margin-right: 5px"></span>{{courseInfo.course.memberName}}
      </div>
      <div>
        <el-button class="btn" type="primary">学习课程</el-button>
        <el-button class="btn" type="warning" @click="goCommunicate(courseInfo.course.forumId)">讨论区</el-button>
      </div>
      <div id="course-study">已有 {{courseInfo.course.learnerNumber}} 人在学习</div>
    </div>
  </div>

<!--  信息部分-->
  <div class="course-datail">
    <el-tabs type="border-card">
      <el-tab-pane label="课程简介"><div style="width: 100%" v-html="courseInfo.course.descr"></div></el-tab-pane>
      <el-tab-pane label="学习视频">
        <courseVideo :courseId="courseInfo.course.id" :face="courseInfo.course.courseAvatar"></courseVideo>
      </el-tab-pane>
      <el-tab-pane label="资源下载"><courseResource></courseResource></el-tab-pane>
      <el-tab-pane label="评论">评论</el-tab-pane>
    </el-tabs>
  </div>
</div>
</template>

<script>
import {getCourseById} from '../mjs/course.mjs'
import courseVideo from "./courseVideo.vue";
import courseResource from "./courseResource.vue";
export default {
  name: "courseDetail",
  data(){
    return{
      courseInfo:{}
    }
  },
  components:{
    courseVideo,
    courseResource
  },
  created(){
    this.init()
  },
  methods:{
    async init(){
      const courseId = this.$route.params.courseId
      console.log(courseId)
      const res = await getCourseById({courseId:courseId})
      this.courseInfo = res.result
    },
    goCommunicate(data){
      const newPage = this.$router.resolve({path: '/courseBlock/'+data})
      window.open(newPage.href,'_blank')
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

.el-dialog__body{
  word-break: keep-all;
}
</style>