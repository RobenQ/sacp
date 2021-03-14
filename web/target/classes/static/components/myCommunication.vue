<template>
  <div>
    <el-tabs v-model="activeName">
      <el-tab-pane label="我发布的" name="first">我发布的</el-tab-pane>
      <el-tab-pane label="我回复的" name="second">我回复的</el-tab-pane>
      <el-tab-pane label="我点赞的" name="third">我点赞的</el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
export default {
  name: "myCommunication",
  data(){
    return{
      activeName:'first'
    }
  }
}
</script>

<style scoped>

</style>