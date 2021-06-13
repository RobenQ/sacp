<template>
  <div id="courseList-wrap">
<!--    用户已经加入的课程列表页-->
    <div id="course-container">
<!--      课程容器-->
      <div class="card-wrap" v-for="item in courseList" :key="item.id">
        <el-card :body-style="{ padding: '0px' }" shadow="hover" class="card">
          <img :src="item.courseAvatar" class="image">
          <div style="padding: 14px;">
            <span>{{item.courseName}}</span>
            <div class="bottom">
              <div>
                <span class="el-icon-user" style="margin-right: 5px"></span>
                <time class="time">{{ item.memberName }}</time>
              </div>
<!--              选项菜单-->
              <el-dropdown placement="top-start">
                <span class="el-dropdown-link">
                  <i class="el-icon-more el-icon--right"></i>
                </span>
                <template #dropdown>
                  <el-dropdown-menu>
                    <el-dropdown-item @click="outCourse((item.id))">退出课程</el-dropdown-item>
                    <el-dropdown-item @click="goBlock((item.forumId))">交流社区</el-dropdown-item>
                    <el-dropdown-item @click="goCourse(item.id)">进入课程</el-dropdown-item>
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
  </div>
</template>

<script>
import {getJoinTotalPage,getJoinCourse,outCourse} from "../mjs/course.mjs";

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
      courseList: [],
      videoList:[],
      resList:[]
    }
  },
  created(){
    this.init()
    const data = {
      sacpId:this.$store.state.sacpId,
      currentPage:this.currentPage,
      pageSize:this.pageSize
    }
    this.getJoinCourse(data)
  },
  methods: {
    async init() {
      const sacpIds = this.$store.state.sacpId
      if (!sacpIds || sacpIds === '') {
        this.$message.error("请先登录！！")
        return
      }
      const res2 = await getJoinTotalPage(sacpIds)
      const res3 = res2.result
      this.totalPage = res3
    },
    async getJoinCourse(data) {
      const sacpIds = this.$store.state.sacpId
      if (!sacpIds || sacpIds === '') {
        this.$message.error("请先登录！！")
        return
      }
      const courses = await getJoinCourse(data)
      this.courseList = courses.result
    },
    change() {
      const data = {
        sacpId: this.$store.state.sacpId,
        currentPage: this.currentPage,
        pageSize: this.pageSize
      }
      this.getJoinCourse(data)
    },
    goCourse(data) {
      const newPage = this.$router.resolve({path: '/courseDetail/' + data})
      window.open(newPage.href, '_blank')
    },
    async outCourse(data){
      const res = await outCourse({sacpId:this.$store.state.sacpId,courseId:data})
      const datas = {
        sacpId:this.$store.state.sacpId,
        currentPage:this.currentPage,
        pageSize:this.pageSize
      }
      this.getJoinCourse(datas)
      this.$message.warning(res.message)
    },
    goBlock(data){
      const newPage = this.$router.resolve({path: '/courseBlock/' + data})
      window.open(newPage.href, '_blank')
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
  width: 218px;
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