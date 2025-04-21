<template>
  <div>
    <el-main>
      <el-card>
        <template #header>
          <div class="card-header">

            <span>用户注册</span>
            <el-button size="mini" style="float:right" @click="router.go(-1)">返回</el-button>&nbsp;&nbsp;&nbsp;

          </div>
        </template>
        <el-form :model="userDetail" :rules="rules" ref="ruleFormRef" label-width="90px">
          <!--        <p v-if="route.query.edit=='1'">
                    <el-form-item label="编号:">
                      {{ route.query.id }}
                    </el-form-item>
                  </p>
                  <p v-else>
                  </p>-->
          <el-form-item label="用户姓名:" prop="userName">
            <el-input v-model="userDetail.userName" style="width:300px" placeholder="请输入用户名"
                      class="input-with-select"></el-input>
          </el-form-item>
          <el-form-item label="用户账号:" prop="account">
            <el-input v-model="userDetail.account" style="width:300px" placeholder="请输入用户账号"
                      class="input-with-select"></el-input>
          </el-form-item>
          <el-form-item label="密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:" prop="password">
            <el-input v-model="userDetail.password" type="password" style="width:300px"
                      placeholder="请输入密码"></el-input>
          </el-form-item>
          <el-form-item label="邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱:" prop="email">
            <el-input v-model="userDetail.email" style="width:300px" placeholder="请输入邮箱"
                      class="input-with-select"></el-input>
          </el-form-item>
          <el-form-item label="备注信息:" prop="intro">
            <el-input v-model="userDetail.intro" style="width:300px" placeholder="请输入信息"
                      class="input-with-select"></el-input>
          </el-form-item>
          <el-form-item label="手机号码:" prop="phone">
            <el-input v-model="userDetail.phone" style="width:300px" placeholder="请输入手机号码"
                      class="input-with-select"></el-input>
          </el-form-item>
          <el-form-item label="角&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;色:">
            <el-select v-model="userDetail.type" style="width:300px;" disabled placeholder="请选择角色" :selected="1">
              <el-option label="学生" value="1"></el-option>
              <el-option label="教师" value="2"></el-option>
              <el-option label="管理员" value="3"></el-option>
            </el-select>
          </el-form-item>
          <!--<el-form-item label="状&nbsp;&nbsp;&nbsp;&nbsp;态:">
            <el-select v-model="userDetail.status" style="width:300px;" placeholder="请选择状态" :selected="1">
              <el-option label="有效" value="1" ></el-option>
              <el-option label="无效" value="0"></el-option>
            </el-select>
          </el-form-item>-->
          <el-form-item>
            <el-button type="primary" @click="onSubmit()">注册</el-button>
            <el-button type="primary" @click="resetForm()">重置</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </el-main>
  </div>
</template>

<script setup>
import md5 from 'js-md5';
import {onBeforeMount, reactive, ref} from 'vue';
import {useRouter, useRoute} from 'vue-router'
import userApi from "../api/user";
import {ElMessage} from "element-plus";

const route = useRoute();
const router = useRouter();
const userDetail = reactive({
  Id: '', userName: '', account: '', phone: '', email: '', intro: '', password: '', type: ''
})
const ruleFormRef = ref();
onBeforeMount(async () => {
  userDetail.userName = '';
  userDetail.account = '';
  userDetail.password = '';
  userDetail.phone = '';
  userDetail.intro = '';
  userDetail.email = '';
  userDetail.type = '1';
})
const rules = reactive({
  userName: [{required: true, message: "用户姓名不能为空", trigger: "blur"}],
  account: [{required: true, message: "账号不能为空", trigger: "blur"}],
  password: [{required: true, message: "密码不能为空", trigger: "blur"}],
  phone: [{
    required: true,
    message: "请输入手机号码",
    trigger: "blur"
  },
    {
      validator: function (rule, value, callback) {
        if (/^1[34578]\d{9}$/.test(value) == false) {
          callback(new Error("手机号格式错误"));
        } else {
          callback();
        }
      },
      trigger: "blur"
    }],
  intro: [],
  email: [{
    required: true,
    message: "请输入邮箱",
    trigger: "blur"
  },
    {
      validator: function (rule, value, callback) {
        if (
            /^\w{1,64}@[a-z0-9\-]{1,256}(\.[a-z]{2,6}){1,2}$/i .test(value) == false
        ) {
          callback(new Error("邮箱格式错误"));
        } else {
          callback();
        }
      },
      trigger: "blur"
    }]
});
const onSubmit = () => {
  if (!ruleFormRef) return;
  ruleFormRef.value.validate(async (valid) => {
    if (valid) {
      //userDetail.password=md5(userDetail.password);

      const res = await userApi.register(userDetail);

      if (res.data != null) {
        if (res.data) {
          ElMessage.info("用户注册成功");
          router.push("/login");
        } else {
          ElMessage.error("用户注册失败");
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
  //location.reload();
  ruleFormRef.value.resetFields();
  //this.$refs.ruleFormRef.resetFields()
};
</script>

<style lang="scss" scoped>

</style>