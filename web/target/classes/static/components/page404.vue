<template>
  <div class="page-not-found">
<!--    404页面，当请求的路由不存在是显示该页面-->
    <el-image
        style="width: 300px; height: 300px"
        src="http://sacp.moeneko.top/404.png"
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