<template>
  <div class="course-resource-container">
    <div class="video-list-wrap" v-for="(res,index) in resourceList" :key="res">
      <el-card class="box-card list" shadow="hover" style="width: 100%">
        <div class="video-list">
          <div class="index">{{index+1}}</div>
          <div class="video-name">{{ res.resourceName }}</div>
<!--          <div class="player"><i class="el-icon-video-camera"></i></div>-->
          <div class="download" @click="downloadRes({url:res.resourceUrl,name:res.resourceName})"><i class="el-icon-download"></i></div>
        </div>
      </el-card>
    </div>

  </div>
</template>

<script>
import {getAllRes} from "../mjs/course.mjs";

export default {
  name: "courseResource",
  props:['courseId'],
  data(){
    return{
      resourceList:[]
    }
  },
  created(){
    this.init()
  },
  methods:{
    async init(){
      const res = await getAllRes(this.courseId)
      this.resourceList = res.result
    },
    downloadRes(data){
      const str = data.url.split(".")
      const ext = str[str.length-1]
      window.location.href = data.url+"?attname="+data.name+"."+ext
    }
  }
}
</script>

<style scoped>
.course-resource-container{
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

.video-name{
  margin-left: 20px;
}

.player{
  margin-left: 20px;
}

.download{
  margin-left: 20px;
}

.download:hover{
  cursor: pointer;
}
</style>