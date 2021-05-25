<template>
<div id="co-container">
  <el-carousel class="co-img" height="200px" arrow="always" indicator-position="outside">
    <el-carousel-item v-for="item in urls" :key="item.id">
      <el-tooltip placement="top">
        <template #content>
          点击进入社区：{{ item.courseName }}
        </template>
        <el-image style="width: 100%; height: 100%" :src="item.courseAvatar" fit="fit" @click="goBlock(item.forumId)"></el-image>
      </el-tooltip>
    </el-carousel-item>
  </el-carousel>

<!--  主体-->
  <div class="co-main">
    <div class="co-left">
      <el-card class="box-card post-card">
        <div class="post-list" v-infinite-scroll="load">
          <div v-for="(item,index) in postList" class="post-wrap">
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
                <div class="post-block1"><i class="el-icon-location-information" style="margin-right: 3px"></i><div>{{ item.block.blockName }}</div></div>
                <div v-if="item.post.classifyId === 1" class="post-block2"><i class="el-icon-sugar" style="margin-right: 3px"></i><div>提问</div></div>
                <div v-if="item.post.classifyId === 2" class="post-block3"><i class="el-icon-bell" style="margin-right: 3px"></i><div>展示</div></div>
              </div>
              <div class="post-footer-right">
                <div><i class="el-icon-view" style="margin-right: 3px"></i>{{ item.post.viewerNumber }}</div>
                <div><i class="el-icon-chat-dot-square" style="margin-right: 3px"></i>{{ item.post.replyNumber }}</div>
                <div v-if="!item.like">
                  <i @click="like(item.post.id,index)" class="el-icon-star-off" style="margin-right: 3px"></i>{{ item.post.likesNumber }}
                </div>
                <div v-if="item.like">
                  <i @click="unLike(item.post.id,index)" class="el-icon-star-on" style="margin-right: 3px;color: #E6A23C;font-size: 16px;"></i>{{ item.post.likesNumber }}
                </div>
              </div>
            </div>
          </div>
          <div v-if="end" style="width: 100%;text-align: center;margin-top: 10px;color: #909399;font-size: 12px">主页只展示最新的20条创作内容</div>
        </div>
      </el-card>
    </div>

    <div class="co-right">
      <el-card class="box-card post-card">
        <el-menu v-if="islogin"
            style="border: none"
            class="el-menu-vertical-demo">
          <el-menu-item index="1" disabled>
            <template #title><span style="color:#e60a0a;font-size: 18px;font-weight: 800">我加入的课程</span></template>
          </el-menu-item>
          <el-menu-item v-for="(item,index) in mbList" index="index+1" @click="goBlock(item.id)">
            <template #title>{{ item.blockName }}</template>
          </el-menu-item>
        </el-menu>
        <div class="more">要查看已加入课程版块请前往个人中心</div>
      </el-card>
    </div>
  </div>
</div>
</template>

<script>

import {getJoinMb, getNew} from "./mjs/course.mjs";
import {checkAllowPost, getNewTop20, likePost, unLikePost} from "./mjs/forum.mjs";

export default {
  name: "communicate",
  data(){
    return{
      urls:[],
      islogin:false,
      mbList:[],
      postList:[],
      end:true
    }
  },
  created(){
    this.init()
  },
  methods:{
    async init(){
      if (this.$store.state.sacpId === ''){
        const res2 = await getNew()
        this.urls = res2.result
        this.islogin = false;
        const res3 = await getNewTop20()
        this.postList = res3.result
      }else {
        const res2 = await getNew()
        this.urls = res2.result
        const res = await getJoinMb(this.$store.state.sacpId)
        this.mbList = res.result
        this.islogin = true;
        const res3 = await getNewTop20()
        this.postList = res3.result
      }
    },
    goBlock(data){
      const newPage = this.$router.resolve({path: '/courseBlock/'+data})
      window.open(newPage.href,'_blank')
      // this.$router.push({path: '/courseDetail'})
    },
    async like(postId,index){
      await checkAllowPost()
      const datas = {
        sacpId:this.$store.state.sacpId,
        postId:postId
      }
      await likePost(datas)
      this.postList[index].post.likesNumber = this.postList[index].post.likesNumber+1
      this.postList[index].like = true
    },
    async unLike(postId,index){
      await checkAllowPost()
      const datas = {
        sacpId:this.$store.state.sacpId,
        postId:postId
      }
      await unLikePost(datas)
      this.postList[index].post.likesNumber = this.postList[index].post.likesNumber-1
      this.postList[index].like = false
    },
    goPost(data){
      const newPage = this.$router.resolve({path: '/postDetail/'+data})
      window.open(newPage.href,'_blank')
    }
  }
}
</script>

<style scoped>
.more{
  font-size: 10px;
  padding-top: 5px;
  border-top: 1px solid #00A8F3;
  display: flex;
  justify-content: center;
  align-items: center;
  box-sizing: border-box;
}

#co-container{
  width: 100%;
  min-height: 400px;
  margin-top: 20px;
  font-size: 30px;
  color: #606266;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
  box-sizing: border-box;
}

.co-img{
  width: 60%;
}

.co-main{
  width: 60%;
  margin-top: 20px;
  margin-bottom: 20px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: flex-start;
  box-sizing: border-box;
}

.co-left{
  width: 70%;
  min-height: 100px;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
  box-sizing: border-box;
}

.co-left .post-card{
  width: 100%;
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

.co-right{
  width: 28%;
  min-height: 300px;
}
</style>