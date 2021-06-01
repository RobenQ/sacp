<template>
<div>
<!--  个人中心我的课程页面-->
  <el-tabs v-model="activeName">
    <el-tab-pane label="我参加的" name="first">
      <joined-course></joined-course>
    </el-tab-pane>
    <el-tab-pane label="我创建的" name="second">
      <create-course></create-course>
    </el-tab-pane>
    <el-tab-pane label="我的评价" name="third">
      <div class="replay-wrap">
        <div class="post-wrap2" v-for="item in replyList">
          <div class="post-header">
            <div>
              <el-avatar :size="40" :src="item.memberAvatar"></el-avatar>
            </div>
            <div class="poster">
              <div class="poster-name">{{ item.memberNickname }}</div>
              <div class="post-time">评价时间：{{ item.createTime }}</div>
            </div>
          </div>
          <div class="post-text" @click="goCourse(item.courseId)">
            {{ item.context }}
          </div>
          <div class="post-footer">
            <div class="post-footer-left">
            </div>
            <div class="post-footer-right">
<!--              <div><i class="el-icon-star-off" style="margin-right: 3px"></i>{{ item.reply.likesNumber }}</div>-->
              <el-button style="margin-left: 10px" type="danger" size="small" @click="deleteReply(item.id)">删除</el-button>
            </div>
          </div>
        </div>
      </div>
    </el-tab-pane>
  </el-tabs>
</div>
</template>

<script>
import createCourse from "./createCourse.vue";
import joinedCourse from "./joinedCourse.vue";
import {deleteCourseReply, getCourseReplyBySacpId} from "../mjs/course.mjs";
export default {
  name: "myCourse",
  data(){
    return{
      activeName:'first',
      replyList:[]
    }
  },
  components:{
    createCourse,
    joinedCourse
  },
  created(){
    this.init()
  },
  methods:{
    async init(){
      const sacpIds = this.$store.state.sacpId
      if (!sacpIds || sacpIds === '') {
        this.$message.error("请先登录！！")
        return
      }
      const res = await getCourseReplyBySacpId(sacpIds)
      this.replyList = res.result
    },
    goCourse(data){
      const newPage = this.$router.resolve({path: '/courseDetail/'+data})
      window.open(newPage.href,'_blank')
    },
    async deleteReply(data){
      const datas = {
        sacpId:this.$store.state.sacpId,
        replyId:data
      }
      const res = await deleteCourseReply(datas)
      this.$message.warning(res.message)
      this.init()
    }
  }
}
</script>

<style scoped>
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

.post-text{
  width: 100%;
  min-height: 65px;
  font-size: 16px;
  font-weight: 540;
  color: #303133;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 3;
  overflow: hidden;
}

.post-text:hover{
  cursor: pointer;
  color: #409EFF;
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
</style>