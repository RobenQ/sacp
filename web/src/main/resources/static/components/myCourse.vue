<template>
<div>
  <el-tabs v-model="activeName">
    <el-tab-pane label="我参加的" name="first">我参加的</el-tab-pane>
    <el-tab-pane label="我创建的" name="second">我创建的</el-tab-pane>
  </el-tabs>
</div>
</template>

<script>
export default {
  name: "myCourse",
  data(){
    return{
      activeName:'first'
    }
  }
}
</script>

<style scoped>

</style>