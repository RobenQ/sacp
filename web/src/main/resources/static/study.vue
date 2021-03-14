<template>
<div id="container" class="container">
  <el-carousel class="img-wap" height="200px" interval="4000" type="card">
    <el-carousel-item v-for="item in urls" :key="item.id">
      <el-image style="width: 100%; height: 100%" :src="item.img" fit="fit"></el-image>
    </el-carousel-item>
  </el-carousel>
  <div class="course-wrap">
    <el-card class="box-card">
      <el-menu
          default-active="2"
          style="border: none"
          text-color="#303133"
          class="el-menu-vertical-demo course-menu">
        <el-menu-item index="1" route="" disabled="true">
          <template #title><span style="color:#e60a0a;font-size: 18px;font-weight: 800">课程分类</span></template>
        </el-menu-item>
        <el-menu-item index="2" route="">
          <i class="el-icon-cpu"></i>
          <template #title>计算机</template>
        </el-menu-item>
        <el-menu-item index="3" route="">
          <i class="el-icon-document"></i>
          <template #title>英语</template>
        </el-menu-item>
        <el-menu-item index="4" route="">
          <i class="el-icon-sell"></i>
          <template #title>经济</template>
        </el-menu-item>
      </el-menu>
    </el-card>
    <div class="course-list">
      <courseList></courseList>
    </div>
  </div>
</div>
</template>

<script>
import courseList from './components/courseList.vue'
export default {
  name: "study",
  data(){
    return{
      urls:[
        {
          id:1,
          img:"http://qpt5cenoi.hb-bkt.clouddn.com/%E9%9B%A8%E5%B9%95.071d2514.jpg"
        },
        {
          id:2,
          img:"http://qpt5cenoi.hb-bkt.clouddn.com/%E4%B8%8B%E5%B1%B1.a2153639.jpg"
        },
        {
          id:3,
          img:"http://qpt5cenoi.hb-bkt.clouddn.com/%E9%BA%BB%E9%9B%80.5397f335.jpg"
        },
        {
          id:4,
          img:"http://qpt5cenoi.hb-bkt.clouddn.com/%E4%B8%8B%E5%B1%B1.a2153639.jpg"
        },
      ]
    }
  },
  components:{
    courseList
  },
  created(){
    this.init()
  },
  methods:{
    init(){
      // test().then(res=>{
      //   console.log(res)
      //   this.msg = res
      // })
    }
  }
}
</script>

<style scoped>
#container{
  width: 100%;
  margin-top: 20px;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
  box-sizing: border-box;
}

.img-wap{
  width: 60%;
}

.course-wrap{
  width: 60%;
  min-height: 400px;
  margin-top: 20px;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: flex-start;
  box-sizing: border-box;
}

.course-menu{
  width: 100%;
  min-height: 450px;
  /*background-color: #EBEEF5;*/
}

.course-list{
  width: 80%;
  min-height: 400px;
}
</style>