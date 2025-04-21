<template>
    <div>
        <el-card >
            <template #header>
                <div class="card-header">

                    <span>用户信息添加</span>
                    <el-button size="mini" style="float:right" @click="router.go(-1)">返回</el-button>&nbsp;&nbsp;&nbsp;

                </div>
            </template>
            <el-form :model="userDetail" :rules="rules" ref="ruleFormRef">
                <p v-if="route.query.edit=='1'">
                <el-form-item label="编号:">
                 {{ route.query.userId }}
                </el-form-item>
                </p>
                <p v-else>
                </p>
                <el-form-item label="用户名:" prop="userName">
                <el-input v-model="userDetail.userName" style="width:300px" placeholder="请输入姓名" class="input-with-select"></el-input>

                </el-form-item>
                <el-form-item label="账&nbsp;&nbsp;&nbsp;&nbsp;号:" prop="account">
                <el-input v-model="userDetail.account" style="width:300px" placeholder="请输入账号" class="input-with-select"></el-input>

                </el-form-item>
                <el-form-item label="邮&nbsp;&nbsp;箱:" prop="email">
                <el-input v-model="userDetail.email" style="width:300px" placeholder="请输入邮箱" class="input-with-select"></el-input>

                </el-form-item>
                <el-form-item label="密&nbsp;&nbsp;&nbsp;&nbsp;码:" prop="password">
                 <el-input v-model="userDetail.password" type="password" style="width:300px" placeholder="请输入密码"></el-input>
                </el-form-item>
                <el-form-item label="简&nbsp;&nbsp;&nbsp;&nbsp;介:" prop="intro">
                <el-input v-model="userDetail.intro" style="width:300px" placeholder="请输入简介" class="input-with-select"></el-input>

                </el-form-item>
                <el-form-item label="手机号:" prop="phone">
                <el-input v-model="userDetail.phone" style="width:300px" placeholder="请输入手机号" class="input-with-select"></el-input>

                </el-form-item>


              <el-button type="primary" @click="onSubmit()">保存</el-button>
              <el-button type="primary" @click="resetForm()">重置</el-button>
            </el-form>
        </el-card>
    </div>
</template>

<script setup>
import { onBeforeMount, reactive, ref } from 'vue';
import { useRouter, useRoute } from 'vue-router'
import userApi from "../../api/user";
import {ElMessage} from "element-plus";
const route = useRoute();
const router = useRouter();
const userDetail = reactive({
    userId: '',userName:'',account:'',email:'',password:'',intro:'',phone:'',type:''
})
const userInfo=ref([]);
onBeforeMount(async () => {
  userDetail.userName ='';
  userDetail.account ='';
  userDetail.password ='';
  userDetail.email ='';
  userDetail.intro ='';
  userDetail.phone ='';
  userDetail.type='0';

})
const ruleFormRef = ref();
const rules = reactive({
  userName: [{ required: true, message: "姓名不能为空", trigger: "blur" }],

  password: [
    { required: true, message: '请输入密码', transform: value => value, trigger: 'blur' },
    {
      type: 'string',
      message: '请输入不包含空格的字符',
      trigger: 'blur',
      transform(value) {
        if (value && value.indexOf(' ') === -1) {
          return value
        } else {
          return false
        }
      }
    },
    {
      trigger: 'blur',
      validator: (rule, value, callback) => {
        var passwordreg = /(?=.*\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{6,16}/
        if (!passwordreg.test(value)) {
          callback(new Error('密码必须由数字、字母、特殊字符组合,请输入6-16位'))
        }else{
          callback()
        }
      } }
  ],
  account: [{ required: true, message: "账号不能为空", trigger: "blur" }],
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
    }],
  intro: [
    {
      validator: function (rule, value, callback) {
        if (value.length > 200) {
          callback(new Error('简介长度不能超过200字'));
        } else {
          callback();
        }
      },
      trigger: 'blur'
    }
  ],
});
const onSubmit = () => {
  if (!ruleFormRef) return;
  ruleFormRef.value.validate(async (valid) => {
    if (valid) {
      const res = await userApi.saveUser(userDetail);

      if (res.data!=null) {
        if (res.data) {
          if(res.data.data == 0){
            ElMessage.error("该账号已注册");
          }else{
            ElMessage.info("新增用户成功");
            router.push("/user/list");
          }
        }else {
          ElMessage.error("新增失败");
        }
      } else {
        ElMessage.error("服务器内部错误");
      }
    } else {
      ElMessage.error("格式错误 ");
      return false;
    }
  });
};
const resetForm = () => {
  if (!ruleFormRef) return;
  ruleFormRef.value.resetFields();
};
</script>

<style lang="scss" scoped>

</style>