<template>
  <div class="course-resource-container">
    <div class="video-list-wrap" v-for="(o,index) in 4" :key="o">
      <el-card class="box-card list" shadow="hover" style="width: 100%">
        <div class="video-list">
          <div class="index">{{index+1}}</div>
          <div class="video-name">java基础</div>
<!--          <div class="player"><i class="el-icon-video-camera"></i></div>-->
          <div class="download"><i class="el-icon-download"></i></div>
        </div>
      </el-card>
    </div>

  </div>
</template>

<script>
export default {
  name: "courseResource"
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
</style>