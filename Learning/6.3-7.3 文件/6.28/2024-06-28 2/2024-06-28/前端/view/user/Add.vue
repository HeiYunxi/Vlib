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
                <el-form-item label="角&nbsp;&nbsp;&nbsp;&nbsp;色:">
                 <el-select v-model="userDetail.type" style="width:300px;" placeholder="请选择类型" :selected="1">
                 <el-option label="学生" value="0" ></el-option>
                 <el-option label="教师" value="1"></el-option>
                 <el-option label="管理员" value="2" ></el-option>
                 </el-select>
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
    password: [{ required: true, message: "密码不能为空", trigger: "blur" }],
    account: [{ required: true, message: "账号不能为空", trigger: "blur" }],
});
const onSubmit = () => {
  if (!ruleFormRef) return;
  ruleFormRef.value.validate(async (valid) => {
    if (valid) {
      const res = await userApi.saveUser(userDetail);

      if (res.data!=null) {
        if (res.data) {
          ElMessage.info("新增用户成功");
          router.push("/user/list");
        } else {
          ElMessage.error("新增失败");
        }
      } else {
        ElMessage.error("服务器内部错误");
      }
    } else {
      alert(1);
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