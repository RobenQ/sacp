<template>
<div id="block-container">
  <div class="course-block-wrap">
    <el-card shadow="always" class="block-card">
      <div class="block-info">
          <el-image
              style="width: 100px; height: 100px;border-radius: 50px;margin-right: 20px"
              :src="blckInfo.blockAvatar"
              fit="fit">
          </el-image>
        <div class="block">
          <div class="block-name">{{ blckInfo.blockName }}</div>
          <div class="students">{{ blckInfo.learnerNumber }}人参与讨论</div>
        </div>
        <el-button type="primary" @click="goCourse(blckInfo.courseId)">课程详情</el-button>
      </div>
    </el-card>
  </div>

  <div class="course-block-main-wrap">
    <div class="course-block-main">
      <div class="block-post">
        <el-tabs type="border-card">
          <el-tab-pane label="全部">
            <div class="post-list" v-infinite-scroll="load">
              <div v-for="item in postList" class="post-wrap">
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
                  </div>
                </div>
              </div>
              <div v-if="end" style="width: 100%;text-align: center;margin-top: 10px;color: #909399;font-size: 12px">已经到底啦~~~</div>
            </div>
          </el-tab-pane>
          <el-tab-pane label="提问">
            <div class="post-list" v-infinite-scroll="load">
              <div v-for="item in classify1" class="post-wrap">
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
                  </div>
                </div>
              </div>
              <div v-if="end" style="width: 100%;text-align: center;margin-top: 10px;color: #909399;font-size: 12px">已经到底啦~~~</div>
            </div>
          </el-tab-pane>
          <el-tab-pane label="展示">
            <div class="post-list" v-infinite-scroll="load">
              <div v-for="item in classify2" class="post-wrap">
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
                  </div>
                </div>
              </div>
              <div v-if="end" style="width: 100%;text-align: center;margin-top: 10px;color: #909399;font-size: 12px">已经到底啦~~~</div>
            </div>
          </el-tab-pane>
        </el-tabs>
      </div>
<!--      右侧面板-->
      <div class="course-block-right">
        <div style="margin-bottom: 10px">
          <el-button style="width: 100%" type="warning" @click="dialogVisible = true">发表内容</el-button>
        </div>
        <el-card class="box-card post-card">
          <el-menu
              style="border: none"
              class="el-menu-vertical-demo">
            <el-menu-item index="1" disabled>
              <template #title><span style="color:#e60a0a;font-size: 18px;font-weight: 800">我加入的课程</span></template>
            </el-menu-item>
            <el-menu-item v-for="(item,index) in mbList" index="index+1" @click="goBlock(item.id)">
              <template #title>{{ item.blockName }}</template>
            </el-menu-item>
          </el-menu>
          <div class="more">请前往个人中心查看更多</div>
        </el-card>
      </div>
    </div>
  </div>

<!--发帖对话框-->
  <el-dialog title="发表新内容"
             v-model="dialogVisible"
             @opened="openDialog"
             @closed="closeDialog"
             fullscreen="true"
             append-to-body="true"
             center>
    <el-form :model="form">
      <el-form-item label="发表课程版块：" label-width="120px">
        <el-input placeholder="请输入内容" v-model="blckInfo.blockName" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="内容类型：" label-width="120px">
        <el-select v-model="classifyId" placeholder="请选择">
          <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="内容：：" label-width="120px">
        <div id="edit" style="width: 800px"></div>
      </el-form-item>
    </el-form>
    <template #footer>
    <span class="dialog-footer">
      <el-button @click="dialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="surePost">确定发表</el-button>
    </span>
    </template>
  </el-dialog>
</div>
</template>

<script>
import {getJoinMb} from "../mjs/course.mjs";
import {getBlockInfo,postPost,getTop5,getPostByPage} from "../mjs/forum.mjs";

export default {
  name: "courseBlock",
  data(){
    return{
      postTime:new Date().format("yyyy-MM-dd HH:mm:ss"),
      mbList:[],
      blckInfo:{},
      postList:[],
      dialogVisible:false,
      form:{},
      classifyId:1,
      txt:'',
      textarea:'',
      options:[
        {
          label:'提问',
          value: 1
        },
        {
          label:'展示',
          value:2
        }
      ],
      editor:{},
      currentPage:1,
      pageSize:5,
      end:false
    }
  },
  created(){
    this.init()
  },
  computed:{
    classify1(){
      return this.postList.filter((list)=>{
        return list.post.classifyId === 1
      })
    },
    classify2(){
      return this.postList.filter((list)=>{
        return list.post.classifyId === 2
      })
    }
  },
  methods:{
    async init(){
      const res = await getJoinMb(this.$store.state.sacpId)
      this.mbList = res.result
      const res2 = await getBlockInfo(this.$route.params.blockId)
      this.blckInfo = res2.result
      const res3 = await getTop5(this.blckInfo.id)
      this.postList = res3.result
      if (this.postList.length<=5){
        this.end = true
      }
    },
    openDialog(){
      const E = window.wangEditor
      const editor = new E('#edit')
      editor.config.height = 350

      editor.config.onchange = this.onChange
      editor.config.onchangeTimeout = 500
      //禁言上传网络图片和视频功能
      editor.config.showLinkImg = false
      editor.config.showLinkVideo = false

      var that = this
      editor.config.customUploadImg = (resultFiles, insertImgFn)=>{
        that.uploadImg(resultFiles,insertImgFn)
      }
      editor.config.customUploadVideo = (resultFiles, insertVideoFn)=>{
        that.uploadVideo(resultFiles,insertVideoFn)
      }
      editor.create()
      this.editor = editor

    },
    closeDialog(){
      this.editor.destroy()
      this.editor = null
    },
    onChange(){
      this.textarea = this.editor.txt.html()
      this.txt = this.editor.txt.text()
    },
    async uploadImg(resultFiles, insertImgFn){
      // resultFiles 是 input 中选中的文件列表
      // insertImgFn 是获取图片 url 后，插入到编辑器的方法
      var imgUrl = ''
      await axios.post("/upload/avatar").then(res=>{
        const putExtra = {}
        const config = {}
        const observable = qiniu.upload(resultFiles[0], new Date().format("yyyyMMddHHmmss")+resultFiles[0].name, res.data, putExtra, config)
        const subscription = observable.subscribe(next=>{
              console.log("正在上传......")
            },
            error=>{
              console.log(error)
            },
            complete=>{
              let key = complete.key;
              imgUrl = "http://sacp.moeneko.top/" + key;
              // 上传图片，返回结果，将图片插入到编辑器中
              insertImgFn(imgUrl)
            })
        return true
      }).catch(e=>{
        this.$message.error('上传头像失败!');
        return false
      })
    },
    async uploadVideo(resultFiles, insertVideoFn){
      // resultFiles 是 input 中选中的文件列表
      // insertImgFn 是获取图片 url 后，插入到编辑器的方法
      var imgUrl = ''
      await axios.post("/upload/avatar").then(res=>{
        const putExtra = {}
        const config = {}
        const observable = qiniu.upload(resultFiles[0], new Date().format("yyyyMMddHHmmss")+resultFiles[0].name, res.data, putExtra, config)
        const subscription = observable.subscribe(next=>{
              console.log("正在上传......")
            },
            error=>{
              console.log(error)
            },
            complete=>{
              let key = complete.key;
              imgUrl = "http://sacpvideo.moeneko.top/" + key;
              // 上传图片，返回结果，将图片插入到编辑器中
              insertVideoFn(imgUrl)
            })
        return true
      }).catch(e=>{
        this.$message.error('上传头像失败!');
        return false
      })
    },
    async surePost(){
      if (this.textarea===''){
        this.$message.warning("内容不能为空");
        return
      }
      const datas = {
        blockId:this.blckInfo.id,
        classifyId:this.classifyId,
        sacpId:this.$store.state.sacpId,
        txt:this.txt,
        context:this.textarea
      }
      await postPost(datas)
      this.$message.success("发表成功");
      this.dialogVisible = false
      const res3 = await getTop5(this.blckInfo.id)
      this.postList = res3.result
      if (this.postList.length<=5){
        this.end = true
      }
    },
    async load(){
      if (!this.end){
        const datas = {
          blockId: this.$route.params.blockId,
          currentPage:this.currentPage,
          pageSize:this.pageSize
        }
        const res = await getPostByPage(datas)
        const newPost = res.result
        if (newPost.length<=5){
          this.end  =true
        }
        for (var i = 0;i<newPost.length;i++){
          this.postList.push(newPost[i])
        }
      }
    },
    goCourse(data){
      const newPage = this.$router.resolve({path: '/courseDetail/'+data})
      window.open(newPage.href,'_blank')
    },
    goBlock(data){
      // const newPage = this.$router.resolve({path: '/courseBlock/'+data})
      // window.open(newPage.href,'_blank')
      this.$router.push({path: '/courseBlock/'+data})
    },
    goPost(data){
      const newPage = this.$router.resolve({path: '/postDetail/'+data})
      window.open(newPage.href,'_blank')
    }
  }
}
</script>

<style scoped>
.post-list{
  overflow-y: auto;
  height: 1000px;
}

.post-list::-webkit-scrollbar {
  display: none;
}

.more{
  font-size: 10px;
  padding-top: 5px;
  border-top: 1px solid #00A8F3;
  display: flex;
  justify-content: center;
  align-items: center;
  box-sizing: border-box;
}

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