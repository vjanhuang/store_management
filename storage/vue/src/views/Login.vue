
<!---->
<template>
  <div class="wrapper">
    <div style="margin: 200px auto;  width: 360px; height: 320px;">
      <div style="margin: 20px 0; color: dodgerblue; text-align: center; font-size: 30px"><b>仓库管理系统</b></div>
      <el-form :model="user" :rules="rules" ref="userForm" style="padding: 0 20px">
        <el-form-item prop="username">
          <el-input size="medium" prefix-icon="el-icon-user" v-model="user.username"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input size="medium" prefix-icon="el-icon-lock" show-password v-model="user.password"></el-input>
        </el-form-item>
        <!--        <el-form-item>-->
        <!--          <div style="display: flex">-->
        <!--            <el-input size="mid" v-model="code" style="width: 200px"></el-input>-->
        <!--            <span @click="refreshCode" style="cursor: pointer; flex: 1;">-->
        <!--              <Identify :identifyCode="identifyCode"></Identify>-->
        <!--           </span>-->
        <!--          </div>-->
        <!--        </el-form-item>-->

        <el-form-item>
          <el-button style="width: 100%; background-color: dodgerblue; border: 1px solid dodgerblue; color: white" size="medium" @click="login">登录</el-button>

        </el-form-item>
        <el-form-item style="margin: 10px 0;">
          <el-button style="color: orange;" size="medium" type="text" @click="handleRegister">立即注册</el-button>
<!--          <el-button style="color: dodgerblue" type="text" size="medium" @click="handlePass">找回密码</el-button>-->
        </el-form-item>
      </el-form>
    </div>


    <el-dialog title="找回密码" :visible.sync="dialogFormVisible" width="400px" >
      <el-form label-width="80px" style="padding-right: 20px">
        <el-form-item label="用户名">
          <el-input v-model="pass.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="pass.phone" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="passwordBack">重置密码</el-button>
      </div>
    </el-dialog>

    <el-dialog title="注册" :visible.sync="dialogFormVisible1" width="400px" >
      <el-form label-width="80px" style="padding-right: 20px" ref="registerForm" :model="register" :rules="registerRules">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="register.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="register.password" autocomplete="off" show-password></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="confirmPassword">
          <el-input v-model="register.confirmPassword" autocomplete="off" show-password></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible1 = false">取 消</el-button>
        <el-button type="primary" @click="doRegister">注 册</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import Identify from "@/components/Identify";

export default {
  name: "Login",
  data() {
    return {
      user: {},
      pass: {},
      register: {},
      code: '',
      dialogFormVisible: false,
      dialogFormVisible1: false,
      // 图片验证码
      identifyCode: '',
      // 验证码规则
      identifyCodes: '3456789ABCDEFGHGKMNPQRSTUVWXY',
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
        ],
      },
      registerRules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
        ],
        confirmPassword: [
          { required: true, message: '请确认密码', trigger: 'blur' },
        ],
      }
    }
  },
  components: {Identify},
  mounted() {
    // this.refreshCode()
  },
  methods: {
    login() {
      // if (this.code !== this.identifyCode.toLowerCase()) {
      //   this.$message({
      //     type: "error",
      //     message: "验证码错误"
      //   })
      //   return;
      // }
      this.$refs['userForm'].validate((valid) => {
        if (valid) {  // 表单校验合法
          this.request.post("/login", this.user).then(res => {
            if(res.code === '200') {
              localStorage.setItem("user", JSON.stringify(res.data))  // 存储用户信息到浏览器
              this.$router.push("/")
              this.$message.success("登录成功")
            } else {
              this.$message.error(res.msg)
            }
          })
        }
      });
    },
    handleRegister() {
      this.register = {}
      this.dialogFormVisible1 = true
    },
    doRegister() {
      this.$refs['registerForm'].validate((valid) => {
        if (valid) {  // 表单校验合法
          this.request.post("/register", this.register).then(res => {
            if(res.code === '200') {
              this.$message.success("注册成功")
              this.dialogFormVisible1 = false
            } else {
              this.$message.error(res.msg)
            }
          })
        }
      });
    },
    handlePass() {
      this.dialogFormVisible = true
      this.pass = {}
    },
    passwordBack() {
      this.request.put("/resetPassword", this.pass).then(res => {
        if (res.code === '200') {
          this.$message.success("重置密码成功，新密码为：123，请登录后尽快修改密码")
          this.dialogFormVisible = false
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    // 切换验证码
    refreshCode() {
      this.identifyCode = ''
      this.makeCode(this.identifyCodes, 4)
    },
    // 生成随机验证码
    makeCode(o, l) {
      for (let i = 0; i < l; i++) {
        this.identifyCode += this.identifyCodes[Math.floor(Math.random() * (this.identifyCodes.length))]
      }
    }
  }
}
</script>

<style>
.wrapper {
  height: 100vh;
  /*background-image: url("../assets/bg.jpg");*/
  /*background-size: cover;*/
  /*background-color: rgb(102, 177, 255);*/
  background: linear-gradient(137deg, #afd9f7,#f0f2ff);
  overflow: hidden;
}
</style>
