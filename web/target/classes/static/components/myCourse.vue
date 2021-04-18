<template>
<div>
  <el-tabs v-model="activeName">
    <el-tab-pane label="我参加的" name="first">
      <joined-course></joined-course>
    </el-tab-pane>
    <el-tab-pane label="我创建的" name="second">
      <create-course></create-course>
    </el-tab-pane>
  </el-tabs>
</div>
</template>

<script>
import createCourse from "./createCourse.vue";
import joinedCourse from "./joinedCourse.vue";
export default {
  name: "myCourse",
  data(){
    return{
      activeName:'first'
    }
  },
  components:{
    createCourse,
    joinedCourse
  }
}
</script>

<style scoped>

</style>