<template>
  <div id="courseList-wrap">
    <div id="course-container">
      <div class="card-wrap">
        <el-card :body-style="{ padding: '0px' }" shadow="hover" class="card">
          <img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png" class="image">
          <div style="padding: 14px;">
            <span>Java进阶</span>
            <div class="bottom">
              <div>
                <span class="el-icon-user" style="margin-right: 5px"></span>
                <time class="time">{{ currentDate }}</time>
              </div>
              <el-button type="primary" class="button" size="mini" @click="courseDetail">课程详情</el-button>
            </div>
          </div>
        </el-card>
      </div>
      <div class="card-wrap">
        <el-card :body-style="{ padding: '0px' }" shadow="hover" class="card">
          <img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png" class="image">
          <div style="padding: 14px;">
            <span>Java进阶</span>
            <div class="bottom">
              <div>
                <span class="el-icon-user" style="margin-right: 5px"></span>
                <time class="time">{{ currentDate }}</time>
              </div>
              <el-button type="primary" class="button" size="mini">课程详情</el-button>
            </div>
          </div>
        </el-card>
      </div>
      <div class="card-wrap">
        <el-card :body-style="{ padding: '0px' }" shadow="hover" class="card">
          <img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png" class="image">
          <div style="padding: 14px;">
            <span>Java进阶</span>
            <div class="bottom">
              <div>
                <span class="el-icon-user" style="margin-right: 5px"></span>
                <time class="time">{{ currentDate }}</time>
              </div>
              <el-button type="primary" class="button" size="mini">课程详情</el-button>
            </div>
          </div>
        </el-card>
      </div>
      <div class="card-wrap">
        <el-card :body-style="{ padding: '0px' }" shadow="hover" class="card">
          <img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png" class="image">
          <div style="padding: 14px;">
            <span>Java进阶</span>
            <div class="bottom">
              <div>
                <span class="el-icon-user" style="margin-right: 5px"></span>
                <time class="time">{{ currentDate }}</time>
              </div>
              <el-button type="primary" class="button" size="mini">课程详情</el-button>
            </div>
          </div>
        </el-card>
      </div>
    </div>
    <div id="page">
      <el-pagination
          background
          page-size="6"
          layout="prev, pager, next"
          :total="120">
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  name: "courseList",
  data(){
    return{
      currentDate: '周老师'
    }
  },
  methods:{
    courseDetail(){
      const newPage = this.$router.resolve({path: '/courseDetail'})
      window.open(newPage.href,'_blank')
      // this.$router.push({path: '/courseDetail'})

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
  justify-content: space-between;
  align-items: flex-start;
  box-sizing: border-box;
}

#page{
  margin-top: 20px;
}

#course-container>.card-wrap:nth-child(n){
  margin-left: 10px;
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
  width: 232px;
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