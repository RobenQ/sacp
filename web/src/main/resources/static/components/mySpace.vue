<template>
  <div class="space-container">
    <div class="sapce-wrap">
      <div class="space-left">
        <el-card class="box-card">
          <el-menu
              default-active="/mySpace/myCourse"
              style="border: none"
              text-color="#303133"
              router="true"
              class="el-menu-vertical-demo course-menu">
            <el-menu-item index="1" route="" disabled="true">
              <template #title><span style="color:#e60a0a;font-size: 18px;font-weight: 800">个人中心</span></template>
            </el-menu-item>
            <el-menu-item index="/mySpace/myCourse">
              <i class="el-icon-notebook-1"></i>
              <template #title>我的课程</template>
            </el-menu-item>
            <el-menu-item index="/mySpace/myCommunication">
              <i class="el-icon-connection"></i>
              <template #title>我的互动</template>
            </el-menu-item>
            <el-menu-item index="/mySpace/myInfo">
              <i class="el-icon-user"></i>
              <template #title>个人信息</template>
            </el-menu-item>
          </el-menu>
        </el-card>
      </div>
      <div class="space-right">
        <el-card class="box-card" shadow="hover">
          <router-view></router-view>
        </el-card>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "mySpace"
}
</script>

<style scoped>
.space-container{
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
  box-sizing: border-box;
}

.sapce-wrap{
  width: 60%;
  margin-top: 20px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: flex-start;
  box-sizing: border-box;
}

.space-left{
  width: 20%;
}

.space-right{
  width: 79%;
}
</style>