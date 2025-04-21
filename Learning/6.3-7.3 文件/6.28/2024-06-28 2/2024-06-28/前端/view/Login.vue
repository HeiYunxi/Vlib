<template>
  <div>
    <el-container>
      <el-header>
        <div style="width:400px">
          <p style="float: left;">
            <el-icon color="#409EFF" :size="50">
              <ElementPlus />
            </el-icon>
          </p>
          <p style="float: left;font-size: 25px; font-weight: bold">
            欢迎登录仿真实训
          </p>
        </div>

      </el-header>
      <el-main>
        <el-card class="login_card">
          <el-form :model="form" :rules="rules" ref="ruleFormRef" label-width="80px">
            <el-form-item label="账号：" prop="username">
              <el-input v-model="form.account" placeholder="请输入账号" />
            </el-form-item>
            <el-form-item label="密码：" prop="password">
              <el-input v-model="form.password" type="password" placeholder="请输入密码"  />
            </el-form-item>
            <el-form-item label="角&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;色:">
              <el-select v-model="form.type" style="width:300px;"  placeholder="请选择角色" :selected="1">
                <el-option label="学生" value="1"></el-option>
                <el-option label="老师" value="2"></el-option>
                <el-option label="管理员" value="3" ></el-option>
              </el-select>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSubmit()">登录</el-button>
              <el-button type="primary" @click="resetForm()">重置</el-button>
              <el-button type="primary" @click="() => router.push({ path: '/register', query: {} })">注册</el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </el-main>
      <el-footer>
        <p>关于我们 | 联系我们 | 人才招聘 | 广告服务 | 友情链接 | DEMO社区 | DEMO公益</p>
        <p>Copyright © 2001-2022 <el-tag>公众号：你猜</el-tag></p>
      </el-footer>
    </el-container>
  </div>
</template>

<script setup>
import userApi from "../api/user";
import {reactive, ref, getCurrentInstance, onBeforeMount} from "vue";
import { ElMessage } from "element-plus";
import router from "../router/index";
const { proxy } = getCurrentInstance();

const form = reactive({
  account: '',
  password: '',
  type:''
});
onBeforeMount(async () => {
   form.type='1';
})
const ruleFormRef = ref();
const rules = reactive({
  password: [{ required: true, message: "密码不能为空", trigger: "blur" }],
  account: [{ required: true, message: "账号不能为空", trigger: "blur" }],
});
const onSubmit = () => {
  if (!ruleFormRef) return;
  ruleFormRef.value.validate(async (valid) => {
    if (valid) {
      const res = await userApi.login(form);

      if (res.data!=null) {
        //alert(res.data.code);
        if (res.data.code==200) {
          // proxy.$commonJs.changeView('/home');
          
          router.push({path:"/home",query:{ userId: res.data.userId}});
        } else {
          ElMessage.error("账号密码错误");
        }
      } else {
        ElMessage.error("服务器内部错误");
      }
    } else {
      return false;
    }
  });
};
const resetForm = () => {
  if (!ruleFormRef) return;
  ruleFormRef.value.resetFields();
};
</script>

<style scoped>
.el-container {
  height: 800px;
}

.el-header {
  height: 10%;
}

.el-main {
  height: 80%;
  background-image: url("../assets/img/bgg.jpg");
  background-repeat: no-repeat;
  background-size: 100% 120%;
  background-position-y: bottom;
}

.login_card {
  margin: 100px 200px;
  width: 20%;
  min-width: 300px;
  height: 200px;
  border-radius: 10px;
}

.el-footer {
  height: 10%;
  text-align: center;
}
</style>
