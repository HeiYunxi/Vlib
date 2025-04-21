<template>
    <div>
        <el-card class="box-card">
            <template #header>
                <div class="card-header">
                    <span>用户详情</span>&nbsp;&nbsp;&nbsp;
                    <el-button size="mini" style="float:right" @click="router.go(-1)">返回</el-button>
                </div>
            </template>
            <el-form :model="userDetail" :rules="rules" ref="ruleFormRef">
                <el-form-item label="编&nbsp;&nbsp;&nbsp;&nbsp;号:">
                  <el-input v-model="userDetail.userId" style="width:300px" :readonly="true"  class="input-with-select"></el-input>
                </el-form-item>

              <el-form-item label="用户名:">
                <el-input v-model="userDetail.userName" style="width:300px" placeholder="请输入姓名" class="input-with-select"></el-input>

              </el-form-item>
              <el-form-item label="账&nbsp;&nbsp;&nbsp;&nbsp;号:">
                <el-input v-model="userDetail.account" style="width:300px" placeholder="请输入账号" class="input-with-select"></el-input>

              </el-form-item>
              <el-form-item label="邮&nbsp;&nbsp;&nbsp;&nbsp;箱:">
                <el-input v-model="userDetail.email" style="width:300px" placeholder="请输入邮箱" class="input-with-select"></el-input>

              </el-form-item>
              <el-form-item label="密&nbsp;&nbsp;&nbsp;&nbsp;码:">
                <el-input v-model="userDetail.password" type="password" style="width:300px" placeholder="请输入密码"></el-input>
              </el-form-item>
              <el-form-item label="简&nbsp;&nbsp;&nbsp;&nbsp;介:">
                <el-input v-model="userDetail.intro" style="width:300px" placeholder="请输入简介" class="input-with-select"></el-input>

              </el-form-item>
              <el-form-item label="手机号:">
                <el-input v-model="userDetail.phone" style="width:300px" placeholder="请输入手机号" class="input-with-select"></el-input>

              </el-form-item>


              <p v-if="route.query.userId=='' || route.query.edit=='1'">
                <el-button type="primary" @click="onSubmit()">保存</el-button>
              </p>
              <p v-else>
              </p>
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
let oldpassword='';
const userInfo=ref([]);
onBeforeMount(async () => {
    if (route.query.userId) {
        const res = await userApi.getUserDetail({ userId: route.query.userId })
        console.log(res.data.data);
        userInfo.value= res.data.data;
        userDetail.userName = res.data.data[0].userName;
        userDetail.account = res.data.data[0].account;
        userDetail.password = res.data.data[0].password;
        userDetail.phone = res.data.data[0].phone;
        userDetail.email = res.data.data[0].email;
        userDetail.type = res.data.data[0].type;
        userDetail.intro = res.data.data[0].intro;
        userDetail.userId=res.data.data[0].userId;
        oldpassword=res.data.data[0].password;//保留原始密码
    }
})
const ruleFormRef = ref();
const rules = reactive({
  username: [{ required: true, message: "账号不能为空", trigger: "blur" }],
  password: [{ required: true, message: "密码不能为空", trigger: "blur" }],
});
const onSubmit = () => {
  if (!ruleFormRef) return;
  ruleFormRef.value.validate(async (valid) => {
    if (valid) {
      if(userDetail.password.length>24&&userDetail.password!=oldpassword){
         ElMessage.info("密码长度不能超过24位");
         return;
        }
      const res = await userApi.updateUser(userDetail);

      if (res.data!=null) {
        if (res.data) {
          alert(res.data);
          // proxy.$commonJs.changeView('/home');
          ElMessage.info("修改成功");
          router.push("/user/list");
        } else {
          ElMessage.error("修改失败");
        }
      } else {
        ElMessage.error("服务器内部错误");
      }
    } else {

      return false;
    }
  });
};
</script>

<style lang="scss" scoped>

</style>