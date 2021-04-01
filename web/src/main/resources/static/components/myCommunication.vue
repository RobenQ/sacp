<template>
  <div>
    <el-tabs v-model="activeName">
      <el-tab-pane label="我的发布" name="first">
        <div class="post-list" v-infinite-scroll="load">
          <div v-for="item in mineList" class="post-wrap">
            <div class="post-header">
              <div>
                <el-avatar :size="40" :src="item.memeber.avatar"></el-avatar>
              </div>
              <div class="poster">
                <div class="poster-name">{{ item.memeber.nickName }}</div>
                <div class="post-time">发表于：{{item.post.createTime}}</div>
              </div>
            </div>
            <span class="post-text" @click="goPost(item.post.id)">
              {{ item.post.txt }}
            </span>
            <div class="post-footer">
              <div class="post-footer-left">
                <div class="post-block1"><i class="el-icon-location-information" style="margin-right: 3px"></i><div>Java进阶</div></div>
                <div v-if="item.post.classifyId === 1" class="post-block2"><i class="el-icon-sugar" style="margin-right: 3px"></i><div>提问</div></div>
                <div v-if="item.post.classifyId === 2" class="post-block3"><i class="el-icon-bell" style="margin-right: 3px"></i><div>展示</div></div>
              </div>
              <div class="post-footer-right">
                <div><i class="el-icon-view" style="margin-right: 3px"></i>{{ item.post.viewerNumber }}</div>
                <div><i class="el-icon-chat-dot-square" style="margin-right: 3px"></i>{{ item.post.replyNumber }}</div>
                <div><i class="el-icon-star-off" style="margin-right: 3px"></i>{{ item.post.likesNumber }}</div>
                <el-button style="margin-left: 10px" type="danger" size="small" @click="deletePost(item.post.id)">删除</el-button>
              </div>
            </div>
          </div>
          <div v-if="end" style="width: 100%;text-align: center;margin-top: 10px;color: #909399;font-size: 12px">已经到底啦~~~</div>
        </div>
      </el-tab-pane>
      <el-tab-pane label="我的回复" name="second">
        <div class="replay-wrap">
          <div class="post-wrap2" v-for="item in replyList">
            <div class="post-header">
              <div>
                <el-avatar :size="40" :src="item.member.avatar"></el-avatar>
              </div>
              <div class="poster">
                <div class="poster-name">{{ item.member.nickName }}</div>
                <div class="post-time">发表于：{{ item.reply.createTime }}</div>
              </div>
            </div>
            <div class="post-text" @click="goPost(item.reply.postId)">
              {{ item.reply.context }}
            </div>
            <div class="post-footer">
              <div class="post-footer-left">
              </div>
              <div class="post-footer-right">
                <div><i class="el-icon-star-off" style="margin-right: 3px"></i>{{ item.reply.likesNumber }}</div>
                <el-button style="margin-left: 10px" type="danger" size="small" @click="deleteReply(item.reply.id)">删除</el-button>
              </div>
            </div>
          </div>
        </div>
      </el-tab-pane>
      <el-tab-pane label="我点赞的" name="third">该功能暂未开放</el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import {getPostByPage2, getPostTop5BySacpId,deletePost,getReplyBySacpId,deleteReply} from "../mjs/forum.mjs";

export default {
  name: "myCommunication",
  data(){
    return{
      activeName:'first',
      end:false,
      currentPage:1,
      pageSize:5,
      mineList:[],
      replyList:[]
    }
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
      const res = await getPostTop5BySacpId(this.$store.state.sacpId)
      this.mineList = res.result
      this.getMyReply()
    },
    async getMyReply(){
      const sacpIds = this.$store.state.sacpId
      if (!sacpIds || sacpIds === '') {
        this.$message.error("请先登录！！")
        return
      }
      const res = await getReplyBySacpId(sacpIds)
      this.replyList = res.result
    },
    async load(){
      if (!this.end){
        const datas = {
          sacpId: this.$store.state.sacpId,
          currentPage:this.currentPage,
          pageSize:this.pageSize
        }
        const res = await getPostByPage2(datas)
        const newPost = res.result
        if (newPost.length<=5){
          this.end  =true
        }
        for (var i = 0;i<newPost.length;i++){
          this.mineList.push(newPost[i])
        }
      }
    },
    goPost(data){
      const newPage = this.$router.resolve({path: '/postDetail/'+data})
      window.open(newPage.href,'_blank')
    },
    async deletePost(data){
      const datas = {
        sacpId:this.$store.state.sacpId,
        postId:data
      }
      const res = await deletePost(datas)
      this.$message.warning(res.message)
      this.init()
    },
    async deleteReply(data){
      const datas = {
        sacpId:this.$store.state.sacpId,
        replyId:data
      }
      const res = await deleteReply(datas)
      this.$message.warning(res.message)
      this.getMyReply()
    }
  }
}
</script>

<style scoped>
.post-list{
  overflow-y: auto;
  height: 1000px;
}

.post-wrap{
  width: 100%;
  /*height: 200px;*/
  padding: 10px 10px 10px 10px;
  border-bottom: #DCDFE6 2px solid;
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

.post-wrap:last-child{
  border: none;
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

.post-block1{
  height: 100%;
  padding: 5px 10px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  box-sizing: border-box;
  font-size: 12px;
  color: #fff;
  background-color: #00A8F3;
  border-radius: 13px;
}

.post-block2{
  height: 100%;
  padding: 5px 10px;
  margin-left: 10px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  box-sizing: border-box;
  font-size: 12px;
  color: #fff;
  background-color: #E6A23C;
  border-radius: 13px;
}

.post-block3{
  height: 100%;
  padding: 5px 10px;
  margin-left: 10px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  box-sizing: border-box;
  font-size: 12px;
  color: #fff;
  background-color: #67C23A;
  border-radius: 13px;
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

.course-block-right{
  width: 24%;
  min-height: 300px;
}
</style>