<template>
  <div class="bind-container">
    <el-form ref="bindModel" :model="bindModel" :rules="rules" class="bind-form" label-position="left">
      <!-- <div class="title">hello !</div>
      <div class="title-tips">欢迎来到{{ title }}!</div> -->
      <el-form-item style="margin-top: 40px" prop="username">
        <span class="svg-container svg-container-admin">
          <svg-icon icon-class="user" />
        </span>
        <el-input v-model="bindModel.username" v-focus placeholder="请输入用户名" tabindex="1" type="text" />
      </el-form-item>
      <el-form-item style="margin-top: 40px" prop="phonenumber">
        <span class="svg-container svg-container-admin">
          <i class="el-icon-mobile" />
        </span>
        <el-input v-model="bindModel.phonenumber" v-focus placeholder="请输入手机号" tabindex="1" type="text" />
      </el-form-item>
      <el-form-item style="margin-top: 40px" prop="mailnumber">
        <span class="svg-container svg-container-admin">
          <i class="el-icon-notebook-2" />
        </span>
        <el-input v-model="bindModel.mailnumber" v-focus placeholder="请输入邮箱号" tabindex="1" type="text" />
      </el-form-item>
      <el-button :loading="loading" class="bind-btn" type="primary" @click="handleBind"> 绑定 </el-button>
    </el-form>
  </div>
</template>

<script>
import { isPassword } from '@/utils/validate'

export default {
  name: 'Bind',
  directives: {
    focus: {
      inserted(el) {
        el.querySelector('input').focus()
      }
    }
  },
  data() {
    const validateusername = (rule, value, callback) => {
      if (value.trim() === '') {
        callback(new Error('用户名不能为空'))
      } else {
        callback()
      }
    }
    const validatePassword = (rule, value, callback) => {
      if (!isPassword(value)) {
        callback(new Error('密码不能少于6位'))
      } else {
        callback()
      }
    }
    return {
      title: '辅导员日程管理系统',
      bindModel: {
        username: '',
        phonenumber: '',
        mailnumber: ''
      },
      rules: {
        username: [
          {
            required: true,
            trigger: 'blur',
            validator: validateusername
          }
        ],
        password: [
          {
            required: true,
            trigger: 'blur',
            validator: validatePassword
          }
        ]
      },
      loading: false,
      passwordType: 'password',
      redirect: undefined
    }
  },
  watch: {
    $route: {
      handler(route) {
        this.redirect = (route.query && route.query.redirect) || '/'
      },
      immediate: true
    }
  },
  created() {
    document.body.style.overflow = 'hidden'
  },
  beforeDestroy() {
    document.body.style.overflow = 'auto'
  },
  methods: {
    handlePassword() {
      this.passwordType === 'password' ? (this.passwordType = '') : (this.passwordType = 'password')
      this.$nextTick(() => {
        this.$refs.password.focus()
      })
    },
    handleBind() {
      this.$refs.bindModel.validate(valid => {
        if (valid) {
          this.loading = true
          this.$store
            .dispatch('user/bind', this.bindModel)
            .then(() => {
              this.$notify({
                type: 'success',
                message: '绑定成功',
                duration: 2000,
                position: 'top-right',
                showClose: false
              })
              //   this.$router.push({ path: '/' })
              this.loading = false
            })
            .catch(() => {
              this.loading = false
            })
        } else {
          return false
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.bind-container {
  height: 100vh;
  background-size: cover;

  .title {
    font-size: 54px;
    font-weight: 500;
    color: rgba(14, 18, 26, 1);
  }

  .title-tips {
    margin-top: 29px;
    font-size: 26px;
    font-weight: 400;
    color: rgba(14, 18, 26, 1);
    text-overflow: ellipsis;
    white-space: nowrap;
  }

  .bind-btn {
    display: inherit;
    width: 220px;
    height: 60px;
    margin-top: 5px;
    border: 0;

    &:hover {
      opacity: 0.9;
    }
  }

  .register-btn{
    display: inline-block;
    margin-top: 15px;
    // margin-left: 40px;
    margin-left: 0px;
  }

  .bind-form {
    position: relative;
    max-width: 100%;
    margin: calc((100vh - 425px) / 2) 10% 10%;
    overflow: hidden;

    .forget-password {
      width: 100%;
      margin-top: 40px;
      text-align: left;

      .forget-pass {
        width: 129px;
        height: 19px;
        font-size: 20px;
        font-weight: 400;
        color: rgba(92, 102, 240, 1);
      }
    }
  }

  .tips {
    margin-bottom: 10px;
    font-size: 14px;
    color: white;

    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }

  .title-container {
    position: relative;

    .title {
      margin: 0 auto 40px auto;
      font-size: 34px;
      font-weight: bold;
      color: blue;
      text-align: center;
    }
  }

  .svg-container {
    position: absolute;
    top: 5px;
    left: 15px;
    z-index: 999;
    font-size: 16px;
    color: #d7dee3;
    cursor: pointer;
    user-select: none;
  }

  .show-password {
    position: absolute;
    top: 14px;
    right: 25px;
    font-size: 16px;
    color: #d7dee3;
    cursor: pointer;
    user-select: none;
  }

  ::v-deep {
    .el-form-item {
      padding-right: 0;
      margin: 20px 0;
      color: #454545;
      background: transparent;
      border: 1px solid transparent;
      border-radius: 2px;

      &__content {
        min-height: 50px;
        line-height: 50px;
      }

      &__error {
        position: absolute;
        top: 100%;
        left: 18px;
        font-size: 12px;
        line-height: 18px;
        color: red;
      }
    }

    .el-input {
      box-sizing: border-box;

      input {
        height: 58px;
        padding-left: 45px;
        font-size: 14px;
        line-height: 58px;
        color: black;
        background: #f6f4fc;
        border: 0;
        caret-color: black;
      }
    }
  }
}
</style>
