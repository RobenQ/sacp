<template>
<div id="block-container">
  <div class="course-block-wrap">
    <el-card shadow="always" class="block-card">
      <div class="block-info">
          <el-image
              style="width: 100px; height: 100px;border-radius: 50px;margin-right: 20px"
              src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
              fit="fit">
          </el-image>
        <div class="block">
          <div class="block-name">Java进阶</div>
          <div class="students">2617人参与讨论</div>
        </div>
        <el-button type="primary" @click="goCourse">课程详情</el-button>
      </div>
    </el-card>
  </div>

  <div class="course-block-main-wrap">
    <div class="course-block-main">
      <div class="block-post">
        <el-tabs type="border-card">
          <el-tab-pane label="全部">
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
              <span class="post-text" @click="goPost">
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
          </el-tab-pane>
          <el-tab-pane label="提问">提问</el-tab-pane>
          <el-tab-pane label="展示">展示</el-tab-pane>
        </el-tabs>
      </div>
      <div class="course-block-right">
        <el-card class="box-card post-card">
          <el-menu
              style="border: none"
              class="el-menu-vertical-demo">
            <el-menu-item index="1" disabled>
              <template #title><span style="color:#e60a0a;font-size: 18px;font-weight: 800">我加入的课程</span></template>
            </el-menu-item>
            <el-menu-item index="2" @click="goBlock">
              <template #title>Java进阶</template>
            </el-menu-item>
            <el-menu-item index="3">
              <template #title>OKR工作法</template>
            </el-menu-item>
            <el-menu-item index="4">
              <template #title>商务英语</template>
            </el-menu-item>
          </el-menu>
        </el-card>
      </div>
    </div>

  </div>
</div>
</template>

<script>
export default {
  name: "courseBlock",
  data(){
    return{
      postTime:new Date().format("yyyy-MM-dd HH:mm:ss")
    }
  },
  methods:{
    goCourse(){
      // const newPage = this.$router.resolve({path: '/courseBlock'})
      // window.open(newPage.href,'_blank')
      this.$router.push({path: '/courseDetail'})
    },
    goBlock(){
      this.$router.push({path: '/courseBlock'})
    },
    goPost(){
      const newPage = this.$router.resolve({path: '/postDetail'})
      window.open(newPage.href,'_blank')
    }
  }
}
</script>

<style scoped>
#block-container{
  width: 100%;
  margin-top: 20px;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
  box-sizing: border-box;
}

.course-block-wrap{
  width: 60%;
  height: 160px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  box-sizing: border-box;
}

.block-card{
  width: 100%;
  height: 100%;
  margin-top: 20px;
}

.block-info{
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
  box-sizing: border-box;
}

.block{
  height: 100%;
  margin-right: 100px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
  box-sizing: border-box;
}

.block-name{
  font-size: 26px;
  color: #303133;
}

.students{
  margin-top: 10px;
  color: #909399;
}

.course-block-main-wrap{
  width: 100%;
  margin-top: 20px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  box-sizing: border-box;
}

.course-block-main{
  width: 60%;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: flex-start;
  box-sizing: border-box;
}

.block-post{
  width: 75%;
}

.post-wrap{
  width: 100%;
  height: 200px;
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