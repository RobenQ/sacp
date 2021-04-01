<template>
  <div class="info-container">
    <div class="face-wrap">
      <el-avatar :size="150" :src="member.avatar"></el-avatar>
      <div class="nickName">
        {{member.nickName}}
        <i v-if="member.gender === '男'" class="el-icon-user-solid" style="color: #00A8F3;margin-left: 5px"></i>
        <i v-if="member.gender === '女'" class="el-icon-user-solid" style="color: #f30014"></i>
      </div>
      <div style="margin-top: 20px">
        <el-button type="warning" round>修改信息</el-button>
        <el-button type="danger" round>更改密码</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import {getUserInfo} from '../mjs/user.mjs'
export default {
  name: "myInfo",
  data(){
    return{
      member:{}
    }
  },
  created(){
    this.init()
  },
  methods:{
    async init(){
      if (!this.$store.state.sacpId || this.$store.state.sacpId===''){
        this.$message({
          showClose: false,
          message: '请先登录!',
          type: "warning",
          duration:3000
        })
        return
      }
      const res = await getUserInfo(this.$store.state.sacpId)
      this.member = res.result
    }
  }
}
</script>

<style scoped>
.nickName{
  font-size: 20px;
  font-weight: 600;
  margin-top: 24px;
}

.info-container{
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
  box-sizing: border-box;
}
.face-wrap{
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
  box-sizing: border-box;
}
</style>