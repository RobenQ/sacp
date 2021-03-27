<template>
<div>
  <el-tabs v-model="activeName">
    <el-tab-pane label="我参加的" name="first">我参加的</el-tab-pane>
    <el-tab-pane label="我创建的" name="second">
      <create-course></create-course>
    </el-tab-pane>
  </el-tabs>
</div>
</template>

<script>
import createCourse from "./createCourse.vue";
export default {
  name: "myCourse",
  data(){
    return{
      activeName:'first'
    }
  },
  components:{
    createCourse
  }
}
</script>

<style scoped>

</style>