<template>
  <div id="post-container">

    <div class="post-detail">

      <div class="post-detail-left">
        <el-card class="box-card" shadow="hover">
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
            <div class="post-text">
              截止目前，我还没有发现比米10u好的手机。
              我这款米10u目前找不到替代品，想换机都没办法(3x120，16+512)，无可挑剔的设计(不大不小的圆角、高端工艺封装的下巴、凸出不高的后置相机、非常舒服的曲面。。。)
              等等MIX4看看会不会超越它了。。
            </div>
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
          <div class="post-toreplay">
            <div class="tip">说说你的看法：</div>
            <el-input
                type="textarea"
                show-word-limit="true"
                maxlength="2000"
                minlength="10"
                :autosize="{ minRows: 4}"
                placeholder="请输入评论内容..."
                v-model="textarea">
            </el-input>
            <div class="post-sure-reply">
              <el-button type="warning" round @click="postReply">发布</el-button>
            </div>
          </div>
          <div class="replay-wrap">
            <div class="post-wrap2">
              <div class="post-header">
                <div>
                  <el-avatar :size="40" src="http://scap.moeneko.top/face.gif"></el-avatar>
                </div>
                <div class="poster">
                  <div class="poster-name">哎Coding</div>
                  <div class="post-time">发表于：{{postTime}}</div>
                </div>
              </div>
              <div class="post-text">
                截止目前，我还没有发现比米10u好的手机。
                我这款米10u目前找不到替代品，想换机都没办法(3x120，16+512)，无可挑剔的设计(不大不小的圆角、高端工艺封装的下巴、凸出不高的后置相机、非常舒服的曲面。。。)
                等等MIX4看看会不会超越它了。。
              </div>
              <div class="post-footer">
                <div class="post-footer-left">
                </div>
                <div class="post-footer-right">
                  <div><i class="el-icon-star-off" style="margin-right: 3px"></i>18</div>
                </div>
              </div>
            </div>
            <div class="post-wrap2">
              <div class="post-header">
                <div>
                  <el-avatar :size="40" src="http://scap.moeneko.top/face.gif"></el-avatar>
                </div>
                <div class="poster">
                  <div class="poster-name">哎Coding</div>
                  <div class="post-time">发表于：{{postTime}}</div>
                </div>
              </div>
              <div class="post-text">
                截止目前，我还没有发现比米10u好的手机。
                我这款米10u目前找不到替代品.
              </div>
              <div class="post-footer">
                <div class="post-footer-left">
                </div>
                <div class="post-footer-right">
                  <div><i class="el-icon-star-off" style="margin-right: 3px"></i>18</div>
                </div>
              </div>
            </div>
            <div class="post-wrap2">
              <div class="post-header">
                <div>
                  <el-avatar :size="40" src="http://scap.moeneko.top/face.gif"></el-avatar>
                </div>
                <div class="poster">
                  <div class="poster-name">哎Coding</div>
                  <div class="post-time">发表于：{{postTime}}</div>
                </div>
              </div>
              <div class="post-text">
                截止目前，我还没有发现比米10u好的手机。
              </div>
              <div class="post-footer">
                <div class="post-footer-left">
                </div>
                <div class="post-footer-right">
                  <div><i class="el-icon-star-off" style="margin-right: 3px"></i>18</div>
                </div>
              </div>
            </div>
          </div>
        </el-card>
      </div>
      <div class="post-detail-right">
        <el-card class="box-card course-card" shadow="hover">
          <div class="course-info">
            <div class="block"><el-avatar :size="90" src="http://scap.moeneko.top/face.gif"></el-avatar></div>
            <div class="d1">Java进阶</div>
            <div class="d2">2617人加入学习</div>
            <div class="d3"><el-button type="primary" round @click="goCourse">课程详情</el-button></div>
          </div>
        </el-card>
      </div>
    </div>

  </div>
</template>

<script>
export default {
  name: "postDetail",
  data(){
    return{
      textarea:'',
      postTime:new Date().format("yyyy-MM-dd HH:mm:ss")
    }
  },
  methods:{
    postReply(){
      this.$message({
        showClose: false,
        message: '发表成功!',
        type: "success",
        duration:5000
      });

    },
    goCourse(){
      this.$router.push({path: '/courseDetail'})
    }
  }
}
</script>

<style scoped>
#post-container{
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
  box-sizing: border-box;
}

.post-detail{
  width: 60%;
  margin-top: 20px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: flex-start;
  box-sizing: border-box;
}

.post-detail-left{
  width: 70%;
}

.post-wrap{
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

.post-wrap:last-child{
  border: none;
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

.tip{
  color: #909399;
  margin-top: 30px;
  margin-bottom: 20px;
  font-size: 16px;
  font-weight: 550;
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

.post-detail-right{
  width: 22%;
  position: fixed;
  top: 80px;
  left: 63%;
}

.course-info{
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  box-sizing: border-box;
}

.d1{
  font-size: 16px;
  color: #303133;
  margin-top: 10px;
  font-weight: 550;
}

.d2{
  font-size: 14px;
  /*color: #606266;*/
  color: #909399;
  margin-top: 10px;
}

.d3{
  margin-top: 10px;
}
</style>