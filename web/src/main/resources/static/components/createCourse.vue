<template>
  <div id="courseList-wrap">
<!--    用户创建的课程列表页-->
    <div class="create">
      <el-button type="warning" @click="createVisible = true">创建课程</el-button>
    </div>
    <div id="course-container">
<!--      课程容器-->
      <div class="card-wrap" v-for="item in courseList" :key="item.id">
        <el-card :body-style="{ padding: '0px' }" shadow="hover" class="card">
          <img :src="item.courseAvatar" class="image">
          <div style="padding: 14px;">
            <span>{{item.courseName}}</span>
            <div class="bottom">
              <div>
                <span class="el-icon-time" style="margin-right: 5px"></span>
                <time class="time">{{ item.createTime }}</time>
              </div>
<!--              <el-button type="primary" class="button" size="mini" @click="courseDetail">课程详情</el-button>-->
<!--              选项菜单-->
              <el-dropdown placement="top-start">
                <span class="el-dropdown-link">
                  <i class="el-icon-more el-icon--right"></i>
                </span>
                <template #dropdown>
                  <el-dropdown-menu>
                    <el-dropdown-item @click="deleteCourse((item.id))">删除课程</el-dropdown-item>
                    <el-dropdown-item @click="resOp((item.id))">资源管理</el-dropdown-item>
                    <el-dropdown-item @click="videoOp((item.id))">视频管理</el-dropdown-item>
                    <el-dropdown-item @click="gocCourse(item.id)">进入课程</el-dropdown-item>
                    <el-dropdown-item>课程详情</el-dropdown-item>
                  </el-dropdown-menu>
                </template>
              </el-dropdown>
            </div>
          </div>
        </el-card>
      </div>
    </div>
<!--    分页组件-->
    <div id="page">
      <el-pagination
          background
          :page-size="pageSize"
          layout="prev, pager, next"
          :total="totalPage"
          :current-page="currentPage"
          hide-on-single-page="true"
          @prev-click="change"
          @next-click="change">
      </el-pagination>
    </div>
<!--创建课程对话框-->
    <el-dialog title="创建Sacp课程" v-model="createVisible" width="500px" center>
      <el-form :model="form2">
        <el-form-item label="课程封面：" label-width="90px">
          <el-image
              style="width: 60px; height: 60px;border-radius: 30px"
              :src="form2.courseAvatar"
              fit="fit">
            <template #error>
              <div class="image-slot" style="text-align: center">
                <i class="el-icon-picture-outline"></i>
              </div>
            </template>
          </el-image>
          <el-upload
              class="upload-demo"
              action="https://upload-z1.qiniup.com"
              auto-upload="false"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload"
              :http-request="uploadRequest">
            <el-button size="small" type="primary">点击上传</el-button>
          </el-upload>
        </el-form-item>
        <el-form-item label="课程名称：" label-width="90px">
          <el-input placeholder="请输入课程名称" v-model="form2.courseName" clearable autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="课程分类：" label-width="90px">
          <el-dropdown size="medium" split-button type="primary" placement="bottom-end" @command="handCommand">
            {{ form2.classifyName }}
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item v-for="item in classifyList" :command="item.classifyName+' '+item.id">{{ item.classifyName }}</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </el-form-item>
        <el-form-item label="课程描述：" label-width="90px" show-password>
          <el-input
              type="textarea"
              show-word-limit="true"
              maxlength="2000"
              minlength="10"
              :autosize="{ minRows: 4}"
              placeholder="请输入课程描述"
              v-model="form2.descr">
          </el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="createVisible = false">取 消</el-button>
          <el-button type="primary" @click="createCourse">确 定</el-button>
        </span>
      </template>
    </el-dialog>
<!--视频管理对话框-->
    <el-dialog title="视频管理" v-model="videoManage" :fullscreen="true" center>
      <el-button @click="videoManage = false">关 闭</el-button>
      <el-button type="primary" @click="uploadVideo = true">上传视频</el-button>
      <el-table style="width: 100% !important;" :data="videoList" empty-text="没有数据">
        <el-table-column width="200px" property="videoName" label="视频名称" width="150"></el-table-column>
        <el-table-column width="700px" property="videoUrl" label="视频链接" width="200"></el-table-column>
        <el-table-column width="200px" property="uploadTime" label="上传时间" width="200"></el-table-column>
        <el-table-column width="100px" property="orders" label="显示排序"></el-table-column>
        <el-table-column width="200px" label="操作">
          <template #default="scope">
            <el-button type="danger" @click="deleteVideo(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
          background
          :page-size="pageSize"
          layout="prev, pager, next"
          :total="totalPage2"
          :current-page="currentPage2"
          hide-on-single-page="false"
          @prev-click="change2"
          @next-click="change2">
      </el-pagination>
<!--      视频上传-->
      <el-dialog
          width="40%"
          title="上传视频"
          v-model="uploadVideo"
          :close-on-click-modal="false"
          append-to-body>
        <el-form :model="form3">
            <el-form-item label="视频名称：" label-width="90px">
              <el-input placeholder="请输入视频名称" v-model="form3.videoName" clearable autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="展示排序：" label-width="90px" show-password>
              <el-input
                  type="number"
                  show-word-limit="true"
                  maxlength="20"
                  minlength="1"
                  placeholder="数字越大排序越靠前"
                  v-model="form3.orders">
              </el-input>
            </el-form-item>
          <el-form-item label="上传视频：" label-width="90px">
            <el-upload
                class="upload-demo"
                ref="videoUpload"
                :multiple="false"
                action="https://upload-z1.qiniup.com"
                :data="datas"
                :on-progress="uploadProcess"
                :show-file-list="false"
                :on-success="handleVideoSuccess"
                :on-error="handleError"
                :before-upload="videoBeforeUpload">
              <el-button size="small" type="primary">点击上传</el-button>
            </el-upload>
            <el-progress style="margin-top: 10px" :text-inside="true" :stroke-width="26" :percentage="progress1"></el-progress>
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="uploadVideo = false">取 消</el-button>
            <el-button :disabled="disableVideo" type="primary" @click="sureVideo">确 定</el-button>
          </span>
        </template>
      </el-dialog>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="videoManage = false">关 闭</el-button>
          <el-button type="primary" @click="uploadVideo = true">上传视频</el-button>
        </div>
      </template>
    </el-dialog>

<!--课程资源对话框-->
    <el-dialog title="课程资源管理" v-model="resManage" :fullscreen="true" center>
      <el-button @click="resManage = false">关 闭</el-button>
      <el-button type="primary" @click="uploadRes = true">上传资源</el-button>
      <el-table style="width: 100% !important;" :data="resList" empty-text="没有数据">
        <el-table-column width="200px" property="resourceName" label="资源名称" width="150"></el-table-column>
        <el-table-column width="700px" property="resourceUrl" label="资源链接" width="200"></el-table-column>
        <el-table-column width="200px" property="uploadTime" label="上传时间" width="200"></el-table-column>
        <el-table-column width="100px" property="orders" label="显示排序"></el-table-column>
        <el-table-column width="200px" label="操作">
          <template #default="scope">
            <el-button type="danger" @click="deleteRes(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
          background
          :page-size="pageSize"
          layout="prev, pager, next"
          :total="totalPage3"
          :current-page="currentPage3"
          hide-on-single-page="false"
          @prev-click="change3"
          @next-click="change3">
      </el-pagination>
      <!--      资源上传-->
      <el-dialog
          width="40%"
          title="上传资源"
          v-model="uploadRes"
          :close-on-click-modal="false"
          append-to-body>
        <el-form :model="form4">
          <el-form-item label="资源名称：" label-width="90px">
            <el-input placeholder="请输入资源名称" v-model="form4.resourceName" clearable autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="展示排序：" label-width="90px" show-password>
            <el-input
                type="number"
                show-word-limit="true"
                maxlength="20"
                minlength="1"
                placeholder="数字越大排序越靠前"
                v-model="form4.orders">
            </el-input>
          </el-form-item>
          <el-form-item label="上传资源：" label-width="90px">
            <el-upload
                class="upload-demo"
                ref="resUpload"
                :multiple="false"
                action="https://upload-z1.qiniup.com"
                :data="datas"
                :on-progress="uploadProcess2"
                :show-file-list="false"
                :on-success="handleResSuccess"
                :on-error="handleError"
                :before-upload="resBeforeUpload">
              <el-button size="small" type="primary">点击上传</el-button>
            </el-upload>
            <el-progress style="margin-top: 10px" :text-inside="true" :stroke-width="26" :percentage="progress2"></el-progress>
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="uploadRes = false">取 消</el-button>
            <el-button :disabled="disableRes" type="primary" @click="sureRes">确 定</el-button>
          </span>
        </template>
      </el-dialog>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="resManage = false">关 闭</el-button>
          <el-button type="primary" @click="uploadRes = true">上传资源</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import {
  deleteRes,
  getResByCourseId,
  getTotalResPage,
  deleteVideo,
  getAllClassify,
  createCourse,
  getUserCourse,
  getTotalPage,
  addVideo,
  getVideoByCourseId,
  getTotalVideoPage,
  addRes, deleteCourse
} from "../mjs/course.mjs";

export default {
  name: "createCourse",
  data(){
    return{
      disableVideo:true,
      disableRes:true,
      createVisible:false,
      pageSize:12,
      currentPage:1,
      totalPage: 1,
      currentPage2:1,
      totalPage2: 1,
      currentPage3:1,
      totalPage3: 1,
      form2:{
        courseAvatar:'http://scap.moeneko.top/face.gif',
        sacpId:'',
        courseName:'',
        descr:'',
        classifyId:'',
        classifyName:'请选择课程分类'
      },
      form3:{
        sacpId:'',
        courseId:'',
        videoName:'',
        videoUrl:'',
        orders:''
      },
      form4:{
        sacpId:'',
        courseId:'',
        resourceName:'',
        resourceUrl:'',
        orders:''
      },
      classifyList: [],
      courseList: [],
      videoList:[],
      resList:[],
      datas:{
        token:'',
        key:''
      },
      videoManage:false,
      uploadVideo:false,
      progress1:0,
      resManage:false,
      uploadRes:false,
      progress2:0
    }
  },
  watch:{
    form3:{
      handler:'isVideoUpdate',
      deep:true
    },
    form4:{
      handler:'isResUpdate',
      deep:true
    }
  },
  created(){
    this.init()
    const data = {
      sacpId:this.$store.state.sacpId,
      currentPage:this.currentPage,
      pageSize:this.pageSize
    }
    this.getCourse(data)
  },
  methods: {
    async init() {
      const sacpIds = this.$store.state.sacpId
      this.form3.sacpId = sacpIds
      if (!sacpIds || sacpIds === '') {
        this.$message.error("请先登录！！")
        return
      }
      const res = await getAllClassify()
      this.classifyList = res.result

      const res2 = await getTotalPage(sacpIds)
      const res3 = res2.result
      this.totalPage = res3

    },
    isResUpdate(){
      // if (!this.form4.sacpId || this.form4.sacpId === ''){
      //   this.disableRes = true
      //   return
      // }
      if (!this.form4.courseId || this.form4.courseId === ''){
        this.disableRes = true
        return
      }
      if (!this.form4.resourceName || this.form4.resourceName === ''){
        this.disableRes = true
        return
      }
      if (!this.form4.resourceUrl || this.form4.resourceUrl === ''){
        this.disableRes = true
        return
      }
      if (!this.form4.orders || this.form4.orders === ''){
        this.disableRes = true
        return
      }
      this.disableRes = false
    },
    isVideoUpdate(){
      if (!this.form3.sacpId || this.form3.sacpId === ''){
        this.disableVideo = true
        return
      }
      if (!this.form3.courseId || this.form3.courseId === ''){
        this.disableVideo = true
        return
      }
      if (!this.form3.videoName || this.form3.videoName === ''){
        this.disableVideo = true
        return
      }
      if (!this.form3.videoUrl || this.form3.videoUrl === ''){
        this.disableVideo = true
        return
      }
      if (!this.form3.orders || this.form3.orders === ''){
        this.disableVideo = true
        return
      }
      this.disableVideo = false
    },
    async getCourse(data) {
      const sacpIds = this.$store.state.sacpId
      if (!sacpIds || sacpIds === '') {
        this.$message.error("请先登录！！")
        return
      }
      const courses = await getUserCourse(data)
      this.courseList = courses.result
    },
    change() {
      const data = {
        sacpId: this.$store.state.sacpId,
        currentPage: this.currentPage,
        pageSize: this.pageSize
      }
      this.getCourse(data)
    },
    change2() {
      const req = {
        courseId:this.form3.courseId,
        currentPage:this.currentPage2,
        pageSize:this.pageSize
      }
      const res = getVideoByCourseId(req)
      this.videoList = res.result
    },
    async createCourse() {
      this.form2.sacpId = this.$store.state.sacpId
      if (!this.form2.sacpId || this.form2.sacpId === '') {
        this.$message.error("请先登录！！")
        return
      }
      if (!this.form2.courseAvatar || this.form2.courseAvatar === '') {
        this.$message({
          showClose: true,
          message: '头像上传失败或没有选择头像',
          type: 'warning'
        })
        return
      }
      if (!this.form2.courseName || this.form2.courseName === '') {
        this.$message({
          showClose: true,
          message: '请输入课程名称',
          type: 'warning'
        })
        return
      }
      if (!this.form2.descr | this.form2.descr === '') {
        this.$message({
          showClose: true,
          message: '课程描述不能为空！',
          type: 'warning'
        })
        return
      }
      const res = await createCourse(this.form2)
      if (res.code == 200) {
        this.$message.success("创建成功")
        this.createVisible = false
        const data = {
          sacpId: this.$store.state.sacpId,
          currentPage: this.currentPage,
          pageSize: this.pageSize
        }
        this.getCourse(data)
      } else {
        this.$message.error("创建失败！")
      }
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error('上传图片只能是 JPG 格式!');
        return false
      }
      if (!isLt2M) {
        this.$message.error('上传图片大小不能超过 2MB!');
        return false
      }
      return true
    },
    uploadRequest(request) {
      axios.post("/upload/avatar").then(res => {
        console.log(res.data)
        const putExtra = {}
        const config = {}
        const observable = qiniu.upload(request.file, new Date().format("yyyyMMddHHmmss") + request.file.name, res.data, putExtra, config)
        const subscription = observable.subscribe(next => {
              console.log("正在上传......")
            },
            error => {
              this.form2.courseAvatar = ''
              console.log(error)
            },
            complete => {
              let hash = complete.hash;
              let key = complete.key;
              this.form2.courseAvatar = "http://sacp.moeneko.top/" + key;
            })
        return true
      }).catch(e => {
        this.$message.error('上传头像失败!');
        return false
      })
    },
    handCommand(command) {
      var res = command.split(' ')
      this.form2.classifyName = res[0]
      this.form2.classifyId = res[1]
    },
    gocCourse(data) {
      const newPage = this.$router.resolve({path: '/courseDetail/' + data})
      window.open(newPage.href, '_blank')
    },
    async videoOp(data) {
      this.form3.courseId = data
      const total = await getTotalVideoPage(data)
      this.totalPage2 = total.result
      const req = {
        courseId:data,
        currentPage:this.currentPage2,
        pageSize:this.pageSize
      }
      const res = await getVideoByCourseId(req)
      this.videoList = res.result
      console.log(res)
      this.videoManage = true
    },
    async deleteCourse(data){
      await deleteCourse(data)
      this.$message.success("删除成功！")
      this.init()
      const data2 = {
        sacpId:this.$store.state.sacpId,
        currentPage:this.currentPage,
        pageSize:this.pageSize
      }
      this.getCourse(data2)
    },
    async resOp(data) {
      this.form4.courseId = data
      const total = await getTotalResPage(data)
      this.totalPage3 = total.result
      const req = {
        courseId:data,
        currentPage:this.currentPage3,
        pageSize:this.pageSize
      }
      const res = await getResByCourseId(req)
      this.resList = res.result
      console.log(res)
      this.resManage = true
    },
    videoBeforeUpload(file) {
      const that = this;
      const isVideo = file.type === 'video/mp4' || file.type === 'video/ogg' || file.type === 'video/flv' || file.type === 'video/avi' || file.type === 'video/wmv' || file.type === 'video/rmvb';
      const isLt1000M = file.size / 1024 / 1024 < 1000;
      if (!isVideo) {
        this.$message.warning('请上传正确格式的视频！');
        return false
      } else {
        if (!isLt1000M) {
          this.$message.warning('上传视频文件大小不能超过 1GB!')
          return false
        }
      }
      this.datas.key = new Date().format("yyyyMMddHHmmss") + file.name
      return new Promise((resolve, reject) => {
        axios.post("/upload/avatar").then(res => {
          this.datas.token = res.data
          resolve(true);
        }).catch(e => {
          that.$message.error('获取上传凭证失败!');
          console.log(err);
          reject(false);
        })
      });
    },
    resBeforeUpload(file) {
      const that = this;
      const isLt100M = file.size / 1024 / 1024 < 100;
      if (!isLt100M) {
        this.$message.warning('上传视频文件大小不能超过 100MB!')
        return false
      }
      this.datas.key = new Date().format("yyyyMMddHHmmss") + file.name
      return new Promise((resolve, reject) => {
        axios.post("/upload/avatar").then(res => {
          this.datas.token = res.data
          resolve(true);
        }).catch(e => {
          that.$message.error('获取上传凭证失败!');
          console.log(err);
          reject(false);
        })
      });
    },
    handleError(err, file, fileList) {
      // 上传失败异常处理
      const error = JSON.parse(JSON.stringify(err));
      console.log(err)
      console.log(error)
      this.$message.error(error.status.toString());
      this.progress1 = 0;
      this.progress2 = 0;
    },
    uploadProcess(event, file, fileList){
      this.progress1 = Math.floor(event.percent);
    },
    uploadProcess2(event, file, fileList){
      this.progress2 = Math.floor(event.percent);
    },
    handleVideoSuccess(response){
      this.form3.videoUrl = "http://sacpvideo.moeneko.top/"+response.key
    },
    handleResSuccess(response){
      this.form4.resourceUrl = "http://sacpdoc.moeneko.top/"+response.key
    },
    async sureVideo(){
      const res = await addVideo(this.form3)
      if (res.code === 200){
        this.uploadVideo = false
        this.videoOp(this.form3.courseId)
        this.$message.success("上传成功")
        this.form3.videoName = ''
        this.form3.videoUrl = ''
        this.form3.orders = ''
        this.progress1 = 0
      }else {
        this.$message.error("上传失败")
      }
    },
    async sureRes(){
      const res = await addRes(this.form4)
      if (res.code === 200){
        this.uploadRes = false
        this.resOp(this.form4.courseId)
        this.$message.success("上传成功")
        this.form4.resourceName = ''
        this.form4.resourceUrl = ''
        this.form4.orders = ''
      }else {
        this.$message.error("上传失败")
      }
    },
    async deleteVideo(data){
      const res = await deleteVideo(data.id)
      if (res.code ===200){
        this.videoOp(this.form3.courseId)
        this.$message.success("删除成功!")
      }else {
        this.$message.error("删除失败!")
      }
    },
    async deleteRes(data){
      const res = await deleteRes(data.id)
      if (res.code ===200){
        this.resOp(this.form4.courseId)
        this.$message.success("删除成功!")
      }else {
        this.$message.error("删除失败!")
      }
    }

  }
}
</script>

<style scoped>
#courseList-wrap{
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  box-sizing: border-box;
}

#course-container{
  width: 100%;
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: flex-start;
  align-items: flex-start;
  box-sizing: border-box;
}

.create{
  width: 100%;
  padding: 10px;
  display: flex;
  justify-content: flex-end;
}

#page{
  margin-top: 20px;
}

#course-container>.card-wrap:nth-child(n){
  margin-right: 10px;
  margin-bottom: 10px;
}

#course-container>.card-wrap:nth-child(1){
  margin-top: 0px;
}
#course-container>.card-wrap:nth-child(2){
  margin-top: 0px;
}
#course-container>.card-wrap:nth-child(3){
  margin-top: 0px;
}

.card-wrap{
  width: 225px;
}

.time {
  font-size: 13px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.image {
  width: 100%;
  height: 150px;
  display: block;
}
</style>