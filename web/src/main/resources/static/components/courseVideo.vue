<template>
<div class="course-video-container">
  <div class="video-list-wrap" v-for="(video,index) in videoList" :key="video.id">
    <el-card class="box-card list" shadow="hover" style="width: 100%" @click="play(video.videoUrl)">
      <div class="video-list">
        <div class="index">{{index+1}}</div>
        <div class="video-name">{{ video.videoName }}</div>
        <div class="player"><i class="el-icon-video-camera"></i></div>
<!--        <div class="download"><i class="el-icon-download"></i></div>-->
      </div>
    </el-card>
  </div>
<!--  视频播放器-->
  <el-dialog
      v-model="dialogTableVisible"
      :destroy-on-close="true"
      :close-on-click-modal="false"
      width="600px">
    <div class="close"><i class="el-icon-close" @click="dialogTableVisible = false"></i></div>
    <div style="width: 100%;position: static">
      <videoPlayer :video="videoUrl" :face="poster"></videoPlayer>
    </div>
  </el-dialog>
</div>
</template>

<script>
import videoPlayer from './videoPlayer.vue'
import {getAllVideo} from '../mjs/course.mjs'
export default {
  name: "courseVideo",
  props:['courseId','face'],
  data(){
    return{
      dialogTableVisible:false,
      videoUrl:'',
      poster:'',
      videoList:[]
    }
  },
  created(){
    // this.$nextTick(res=>{
    //   this.init()
    // })
    this.init()
  },
  methods:{
    async init(){
      const res = await getAllVideo(this.courseId)
      this.videoList = res.result
    },
    play(data){
      this.videoUrl = data
      this.poster = this.face
      this.dialogTableVisible = true
    }
  },
  components:{
    videoPlayer
  }
}
</script>

<style scoped>
.course-video-container{
  width: 100%;
  max-height: 800px;
  overflow: scroll;
  overflow-x: hidden;
  overflow-y: hidden;
  padding: 10px;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: flex-start;
  box-sizing: border-box;
}

.video-list-wrap{
  width: 100%;
  height: 70px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  box-sizing: border-box;
}

.video-list:hover{
  color: #409EFF;
  /*font-size: 20px;*/
}

.video-list{
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
  box-sizing: border-box;
}

.video-list:hover{
  cursor: pointer;
}

.video-name{
  margin-left: 20px;
}

.player{
  margin-left: 20px;
}

.close{
  background-color: black;
  border-radius: 20px;
  font-size: 40px;
  /*background-color: white;*/
  position: absolute;
  top: -20px;
  left: 580px;
  color: white;
  box-sizing: border-box;
  display: flex;
  justify-content: center;
  align-items: center;
  opacity: 0.8;
  z-index: 999;
}

</style>