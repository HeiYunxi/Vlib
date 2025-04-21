<template>
  <div>
    <el-card >
      <template #header>
        <div class="card-header">

          <span>用户注册</span>
          <el-button size="mini" style="float:right" @click="router.go(-1)">返回</el-button>&nbsp;&nbsp;&nbsp;

        </div>
      </template>
      <el-form :model="userDetail" :rules="rules" ref="ruleFormRef" label-width="80px">
<!--        <p v-if="route.query.edit=='1'">
          <el-form-item label="编号:">
            {{ route.query.id }}
          </el-form-item>
        </p>
        <p v-else>
        </p>-->
        <el-form-item label="用户姓名:">
          <el-input v-model="userDetail.userName" style="width:300px" placeholder="请输入用户名" class="input-with-select"></el-input>

        </el-form-item>
        <el-form-item label="用户账号:">
          <el-input v-model="userDetail.account" style="width:300px" placeholder="请输入用户账号" class="input-with-select"></el-input>

        </el-form-item>
        <el-form-item label="密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:">
          <el-input v-model="userDetail.password" type="password" style="width:300px" placeholder="请输入密码"></el-input>
        </el-form-item>
        <el-form-item label="邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱:">
          <el-input v-model="userDetail.email" style="width:300px" placeholder="请输入邮箱" class="input-with-select"></el-input>

        </el-form-item>
        <el-form-item label="备注信息:">
          <el-input v-model="userDetail.intro" style="width:300px" placeholder="请输入备注信息" class="input-with-select"></el-input>

        </el-form-item>
        <el-form-item label="手机号码:">
          <el-input v-model="userDetail.phone" style="width:300px" placeholder="请输入手机号码" class="input-with-select"></el-input>

        </el-form-item>
        <el-form-item label="角&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;色:">
          <el-select v-model="userDetail.roleid" style="width:300px;" disabled placeholder="请选择角色" :selected="1" >
            <el-option label="普通用户" value="0"></el-option>
            <el-option label="管理员" value="1" ></el-option>
          </el-select>
        </el-form-item>
        <!--<el-form-item label="状&nbsp;&nbsp;&nbsp;&nbsp;态:">
          <el-select v-model="userDetail.status" style="width:300px;" placeholder="请选择状态" :selected="1">
            <el-option label="有效" value="1" ></el-option>
            <el-option label="无效" value="0"></el-option>
          </el-select>
        </el-form-item>-->
        <el-button type="primary" @click="onSubmit()">注册</el-button>
        <el-button type="primary" @click="resetForm()">重置</el-button>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import md5 from 'js-md5';
import { onBeforeMount, reactive, ref } from 'vue';
import { useRouter, useRoute } from 'vue-router'
import userApi from "../api/user";
import {ElMessage} from "element-plus";
const route = useRoute();
const router = useRouter();
const userDetail = reactive({
  id: '',username:'',account:'',phone:'',intro:'',password:'',roleid:''
})
const userInfo=ref([]);
onBeforeMount(async () => {
  userDetail.username ='';
  userDetail.password ='';
  userDetail.roleid='1';
})
const ruleFormRef = ref();
const rules = reactive({
  account: [{ required: true, message: "账号不能为空", trigger: "blur" }],
  password: [{ required: true, message: "密码不能为空", trigger: "blur" }],
});
const onSubmit = () => {
  if (!ruleFormRef) return;
  ruleFormRef.value.validate(async (valid) => {
    if (valid) {
      //alert(md5(userDetail.password));
      // userDetail.password=md5(userDetail.password);
      const res = await userApi.register(userDetail);

      if (res.data!=null) {
        if (res.data) {
          ElMessage.info("用户注册成功");
          router.push("/login");
        } else {
          ElMessage.error("注册失败");
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

<style lang="scss" scoped>

</style>