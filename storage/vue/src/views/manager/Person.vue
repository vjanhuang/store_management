<template>
  <el-card style="width: 500px; margin: 10px auto">
    <el-form label-width="80px">
      <el-upload
          class="avatar-uploader"
          :action="$baseUrl + '/file/upload'"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
      >
        <img v-if="form.avatar" :src="form.avatar" class="avatar">
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
      </el-upload>

      <el-form-item label="用户名">
        <el-input v-model="form.username" disabled autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="姓名">
        <el-input v-model="form.name" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="电话">
        <el-input v-model="form.phone" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="地址">
        <el-input type="textarea" v-model="form.address" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="save">确 定</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script>

export default {
  name: "Person",
  data() {
    return {
      form: {},
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      this.request.get("/user/" + this.user.id).then(res => {
        this.form = res.data || {}
      })
    },
    save() {
      this.request.put("/user", this.form).then(res => {
        if (res.code === '200') {
          this.$message.success("操作成功")
          // 更新浏览器存储的用户信息
          this.form.token = this.user.token
          localStorage.setItem("user", JSON.stringify(this.form))

          // 触发父级更新User的方法
          this.$emit("refreshUser")
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    handleAvatarSuccess(res) {
      this.form.avatar = res
    }
  }
}
</script>

<style>
.avatar-uploader {
  text-align: center;
  padding-bottom: 10px;
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 138px;
  height: 138px;
  line-height: 138px;
  text-align: center;
}
.avatar {
  width: 138px;
  height: 138px;
  display: block;
}
</style>
