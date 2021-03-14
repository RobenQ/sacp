<template>
  <div class="page-not-found">
    <el-image
        style="width: 300px; height: 300px"
        src="http://scap.moeneko.top/404.png"
        fit="contain">
    </el-image>
  </div>
</template>

<script>
export default {
  name: "page404"
}
</script>

<style scoped>
.page-not-found{
  width: 100%;
  height: 400px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  box-sizing: border-box;
}
</style>