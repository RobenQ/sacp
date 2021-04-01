<template>
<div id="co-container">
  <el-carousel class="co-img" height="200px" interval="4000" direction="vertical"
               :autoplay="true" arrow="always">
    <el-carousel-item v-for="item in urls" :key="item.id">
      <el-image style="width: 100%; height: 100%" :src="item.img" fit="fit"></el-image>
    </el-carousel-item>
  </el-carousel>

<!--  主体-->
  <div class="co-main">
    <div class="co-left">
      <el-card class="box-card post-card">
        <div class="post-wrap">
          <div class="post-header">
            <div>
              <el-avatar :size="40" src="http://sacp.moeneko.top/face.gif"></el-avatar>
            </div>
            <div class="poster">
              <div class="poster-name">哎Coding</div>
              <div class="post-time">发表于：{{postTime}}</div>
            </div>
          </div>
          <el-image
              style="width: 100%; height: 200px;border-radius: 5px"
              src="http://qpt5cenoi.hb-bkt.clouddn.com/%E9%9B%A8%E5%B9%95.071d2514.jpg"
              fit="fit">
          </el-image>
          <span class="post-text">
            截止目前，我还没有发现比米10u好的手机。
            我这款米10u目前找不到替代品，想换机都没办法(3x120，16+512)，无可挑剔的设计(不大不小的圆角、高端工艺封装的下巴、凸出不高的后置相机、非常舒服的曲面。。。)
            等等MIX4看看会不会超越它了。。
          </span>
          <div class="post-footer">
            <div class="post-footer-left">
              <div class="post-block1"><i class="el-icon-location-information" style="margin-right: 3px"></i><div>Java进阶</div></div>
              <div class="post-block2"><i class="el-icon-sugar" style="margin-right: 3px"></i><div>提问</div></div>
            </div>
            <div class="post-footer-right">
              <div><i class="el-icon-view" style="margin-right: 3px"></i>232</div>
              <div><i class="el-icon-chat-dot-square" style="margin-right: 3px"></i>67</div>
              <div><i class="el-icon-star-off" style="margin-right: 3px"></i>18</div>
            </div>
          </div>
        </div>

        <div class="post-wrap">
          <div class="post-header">
            <div>
              <el-avatar :size="40" src="http://scap.moeneko.top/face.gif"></el-avatar>
            </div>
            <div class="poster">
              <div class="poster-name">哎Coding</div>
              <div class="post-time">发表于：{{postTime}}</div>
            </div>
          </div>
          <el-image
              style="width: 100%; height: 200px;border-radius: 5px"
              src="http://qpt5cenoi.hb-bkt.clouddn.com/%E9%9B%A8%E5%B9%95.071d2514.jpg"
              fit="fit">
          </el-image>
          <span class="post-text">
            截止目前，我还没有发现比米10u好的手机。
            我这款米10u目前找不到替代品，想换机都没办法(3x120，16+512)，无可挑剔的设计(不大不小的圆角、高端工艺封装的下巴、凸出不高的后置相机、非常舒服的曲面。。。)
            等等MIX4看看会不会超越它了。。
          </span>
          <div class="post-footer">
            <div class="post-footer-left">
              <div class="post-block1"><i class="el-icon-location-information" style="margin-right: 3px"></i><div>Java进阶</div></div>
              <div class="post-block2"><i class="el-icon-sugar" style="margin-right: 3px"></i><div>提问</div></div>
            </div>
            <div class="post-footer-right">
              <div><i class="el-icon-view" style="margin-right: 3px"></i>232</div>
              <div><i class="el-icon-chat-dot-square" style="margin-right: 3px"></i>67</div>
              <div><i class="el-icon-star-off" style="margin-right: 3px"></i>18</div>
            </div>
          </div>
        </div>

        <div class="post-wrap">
          <div class="post-header">
            <div>
              <el-avatar :size="40" src="http://sacp.moeneko.top/face.gif"></el-avatar>
            </div>
            <div class="poster">
              <div class="poster-name">哎Coding</div>
              <div class="post-time">发表于：{{postTime}}</div>
            </div>
          </div>
          <el-image
              style="width: 100%; height: 200px;border-radius: 5px"
              src="http://qpt5cenoi.hb-bkt.clouddn.com/%E9%9B%A8%E5%B9%95.071d2514.jpg"
              fit="fit">
          </el-image>
          <span class="post-text">
            截止目前，我还没有发现比米10u好的手机。
            我这款米10u目前找不到替代品，想换机都没办法(3x120，16+512)，无可挑剔的设计(不大不小的圆角、高端工艺封装的下巴、凸出不高的后置相机、非常舒服的曲面。。。)
            等等MIX4看看会不会超越它了。。
          </span>
          <div class="post-footer">
            <div class="post-footer-left">
              <div class="post-block1"><i class="el-icon-location-information" style="margin-right: 3px"></i><div>Java进阶</div></div>
              <div class="post-block2"><i class="el-icon-sugar" style="margin-right: 3px"></i><div>提问</div></div>
            </div>
            <div class="post-footer-right">
              <div><i class="el-icon-view" style="margin-right: 3px"></i>232</div>
              <div><i class="el-icon-chat-dot-square" style="margin-right: 3px"></i>67</div>
              <div><i class="el-icon-star-off" style="margin-right: 3px"></i>18</div>
            </div>
          </div>
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

import {getJoinMb} from "./mjs/course.mjs";

export default {
  name: "communicate",
  data(){
    return{
      urls:[
        {
          id:1,
          img:"http://qpt5cenoi.hb-bkt.clouddn.com/%E9%9B%A8%E5%B9%95.071d2514.jpg"
        },
        {
          id:2,
          img:"http://qpt5cenoi.hb-bkt.clouddn.com/%E4%B8%8B%E5%B1%B1.a2153639.jpg"
        },
        {
          id:3,
          img:"http://qpt5cenoi.hb-bkt.clouddn.com/%E9%BA%BB%E9%9B%80.5397f335.jpg"
        },
        {
          id:4,
          img:"http://qpt5cenoi.hb-bkt.clouddn.com/%E4%B8%8B%E5%B1%B1.a2153639.jpg"
        },
      ],
      islogin:false,
      mbList:[]
    }
  },
  created(){
    this.init()
  },
  methods:{
    async init(){
      if (this.$store.state.sacpId === ''){
        this.islogin = false;
        return
      }else {
        const res = await getJoinMb(this.$store.state.sacpId)
        this.mbList = res.result
        this.islogin = true;
      }
    },
    goBlock(data){
      const newPage = this.$router.resolve({path: '/courseBlock/'+data})
      window.open(newPage.href,'_blank')
      // this.$router.push({path: '/courseDetail'})
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
  height: 400px;
  padding: 10px 0px 10px 0px;
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
  margin-top: 5px;
}

.post-wrap:last-child{
  border: none;
}

.post-text{
  width: 100%;
  margin-top: 10px;
  font-size: 16px;
  font-weight: 540;
  color: #303133;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 3;
  overflow: hidden;
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