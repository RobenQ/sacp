<template>
  <div class="info-container">
    <div class="face-wrap">
      <el-avatar :size="150" :src="member.avatar"></el-avatar>
    </div>
    <div class="nickName">
      {{member.nickName}}
      <i v-if="member.gender === '男'" class="el-icon-user-solid" style="color: #00A8F3;margin-left: 5px"></i>
      <i v-if="member.gender === '女'" class="el-icon-user-solid" style="color: #f30014"></i>
    </div>
    <div style="margin-top: 20px">
      <!--        <el-button type="warning" round>修改信息</el-button>-->
      <el-button type="danger" round @click="newpwd">更改登录密码</el-button>
    </div>

<!--    修改密码对话框-->
    <el-dialog title="修改Sacp登录密码" v-model="dialogVisible" width="500px" center>
      <el-form :model="form1">
        <el-form-item label="原密码：" label-width="100px">
          <el-input placeholder="请输入原密码" v-model="form1.op" clearable autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="新密码：" label-width="100px" show-password>
          <el-input placeholder="请输入新密码" v-model="form1.np" type="password" clearable autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="确认新密码：" label-width="100px" show-password>
          <el-input placeholder="请输入再次新密码" v-model="form1.np2" type="password" clearable autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
    <span class="dialog-footer">
      <el-button @click="dialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="modifyPwd">确 定</el-button>
    </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import {getUserInfo,modifyPassword} from '../mjs/user.mjs'
export default {
  name: "myInfo",
  data(){
    return{
      member:{},
      dialogVisible:false,
      form1: {
        sacpId:'',
        np:'',
        np2:'',
        op:''
      }
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
    },
    newpwd(){
      if (!this.$store.state.sacpId || this.$store.state.sacpId===''){
        this.$message({
          showClose: false,
          message: '请先登录!',
          type: "warning",
          duration:3000
        })
        return
      }
      this.form1.sacpId = this.$store.state.sacpId
      this.dialogVisible = true
    },
    async modifyPwd(){
      if (this.form1.np !== this.form1.np2){
        this.$message({
          showClose: false,
          message: '两次输入的新密码不一致，请重新输入！',
          type: "warning",
          duration:3000
        })
        return
      }
      const datas = {
        sacpId: this.form1.sacpId,
        op:this.form1.op,
        np:this.form1.np
      }
      const res = await modifyPassword(datas)
      this.$message({
        showClose: false,
        message: res.message,
        type: "warning",
        duration:3000
      })
      this.dialogVisible = false
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