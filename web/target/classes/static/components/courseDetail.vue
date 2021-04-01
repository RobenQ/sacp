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
        <el-button :disabled="!isJoin" class="btn" type="primary" @click="joinCourse(courseInfo.course.id)">加入课程</el-button>
        <el-button class="btn" type="warning" @click="goCommunicate(courseInfo.course.forumId)">讨论区</el-button>
      </div>
      <div id="course-study">已有 {{courseInfo.course.learnerNumber}} 人在学习</div>
    </div>
  </div>

<!--  信息部分-->
  <div class="course-datail">
    <el-tabs type="border-card">
      <el-tab-pane label="课程简介">
        <div style="width: 100%" v-html="courseInfo.course.descr"></div>
      </el-tab-pane>
      <el-tab-pane label="学习视频">
        <courseVideo :courseId="courseInfo.course.id" :face="courseInfo.course.courseAvatar"></courseVideo>
      </el-tab-pane>
      <el-tab-pane label="资源下载">
        <courseResource :courseId="courseInfo.course.id"></courseResource>
      </el-tab-pane>

      <el-tab-pane>
        <template #label>
          <el-badge :value="discussionList.length==0?0:discussionList.length" class="item">
            评论
          </el-badge>
        </template>
        <div class="post-toreplay">
          <div class="tip">说说你的看法：</div>
          <el-input
              type="textarea"
              show-word-limit="true"
              maxlength="100"
              minlength="5"
              :autosize="{ minRows: 3}"
              placeholder="请输入评论内容..."
              v-model="textarea">
          </el-input>
          <div class="post-sure-reply">
            <el-button type="warning" round @click="postReply">发布</el-button>
          </div>
        </div>
        <div class="replay-wrap">
          <div class="post-wrap2" v-for="item in discussionList">
            <div class="post-header">
              <div>
                <el-avatar :size="40" :src="item.memberAvatar"></el-avatar>
              </div>
              <div class="poster">
                <div class="poster-name">{{ item.memberNickname }}</div>
                <div class="post-time">发表于：{{item.createTime}}</div>
              </div>
            </div>
            <div class="post-text">
              {{item.context}}
            </div>
            <div class="post-footer">
              <div class="post-footer-left">
              </div>
              <div class="post-footer-right">
<!--                <div><i class="el-icon-star-off" style="margin-right: 3px"></i>18</div>-->
              </div>
            </div>
          </div>
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</div>
</template>

<script>
import {getCourseById,joinCourse,addDiscussion,getReply} from '../mjs/course.mjs'
import courseVideo from "./courseVideo.vue";
import courseResource from "./courseResource.vue";
export default {
  name: "courseDetail",
  data(){
    return{
      courseInfo:{},
      isJoin:true,
      textarea:'',
      discussionList:[]
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
      const res2 = await getReply(courseId)
      this.discussionList = res2.result
    },
    async postReply(){
      if (!this.$store.state.sacpId || this.$store.state.sacpId === ''){
        this.$message.warning("请登录！登陆后刷新页面。")
        return
      }else if (this.textarea === ''){
        this.$message.warning("评论内容不能为空！")
        return;
      }else{
        const datas = {
          sacpId:this.$store.state.sacpId,
          courseId:this.$route.params.courseId,
          context:this.textarea
        }
        const res = await addDiscussion(datas)
        if (res.code===200){
          const res2 = await getReply(this.$route.params.courseId)
          this.discussionList = res2.result
          this.textarea = ''
          this.$message.success(res.message)
          return
        }else {
          return
          this.$message.warning("评论失败！")
        }
      }
    },
    async joinCourse(data){
      console.log(data)
      if (!this.$store.state.sacpId || this.$store.state.sacpId === ''){
        this.$message.warning("你还没有登录，请登录后刷新页面。")
        return
      }
      const datas = {
        sacpId:this.$store.state.sacpId,
        courseId:this.$route.params.courseId
      }
      const res = await joinCourse(datas)
      if (res.code===200){
        var i = this.courseInfo.course.learnerNumber
        this.courseInfo.course.learnerNumber = i+1
        this.$message.success(res.message)
      }else {
        this.$message.warning("加入课程失败！")
      }
    },
    goCommunicate(data){
      const newPage = this.$router.resolve({path: '/courseBlock/'+data})
      window.open(newPage.href,'_blank')
    }
  }
}
</script>

<style scoped>
.el-badge__content{
  top: 10px !important;
  /*left: 5px;*/
}

#tab-3 .el-badge{
  width: 45px !important;
}

.post-footer-right{
  width: 50%;
  height: 25px;
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
  align-items: center;
  box-sizing: border-box;
}

.post-footer-right div{
  font-size: 16px;
  color: #606266;
}

.post-footer-right div:nth-child(1),.post-footer-right div:nth-child(2){
  margin-right: 20px;
}

.post-wrap2:last-child{
  border: none;
}

.post-text{
  width: 100%;
  font-size: 16px;
  font-weight: 540;
  color: #303133;
}

.post-text::selection {
  color: #fff;
  background: #ff594a;
}

.post-footer{
  width: 100%;
  margin-top: 20px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  box-sizing: border-box;
}

.post-footer-left{
  max-width: 50%;
  height: 25px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  box-sizing: border-box;
}

.poster{
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
  box-sizing: border-box;
}

.poster-name{
  font-size: 16px;
  /*color: #303133;*/
  color: #606266;
}

.post-time{
  font-size: 12px;
  color: #909399;
}

.post-header{
  width: 100%;
  height: 50px;
  margin-bottom: 10px;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
  box-sizing: border-box;
}

.post-wrap2{
  width: 100%;
  padding: 10px 10px 10px 10px;
  border-bottom: #DCDFE6 1px solid;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: flex-start;
  box-sizing: border-box;
}

.replay-wrap{
  width: 100%;
  padding: 10px 10px 20px 10px;
  border-bottom: #DCDFE6 1px solid;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: flex-start;
  box-sizing: border-box;
}

.post-sure-reply{
  width: 100%;
  margin-top: 20px;
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
  align-items: center;
  box-sizing: border-box;
}

.tip{
  color: #909399;
  margin-top: 30px;
  margin-bottom: 20px;
  font-size: 16px;
  font-weight: 550;
}

.post-toreplay{
  width: 100%;
  padding: 10px 10px 20px 10px;
  border-bottom: #DCDFE6 1px solid;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: flex-start;
  box-sizing: border-box;
}

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