<template>
    <div>
        <el-card >
            <template #header>
                <div class="card-header">

                    <span>用户信息d</span>
                    <el-button size="mini" style="float:right" @click="router.go(-1)">返回</el-button>&nbsp;&nbsp;&nbsp;

                </div>
            </template>
            <el-form :model="userDetail" :rules="rules" ref="ruleFormRef">
                <p v-if="route.query.edit=='1'">
                <el-form-item label="编号:">
                 {{ route.query.id }}
                </el-form-item>
                </p>
                <p v-else>
                </p>
                <el-form-item label="用户名:">
                <el-input v-model="userDetail.username" style="width:300px" placeholder="请输入用户名" class="input-with-select"></el-input>

                </el-form-item>
                <el-form-item label="密&nbsp;&nbsp;&nbsp;&nbsp;码:">
                 <el-input v-model="userDetail.password" type="password" style="width:300px" placeholder="请输入密码"></el-input>
                </el-form-item>
                <el-form-item label="角&nbsp;&nbsp;&nbsp;&nbsp;色:">
                 <el-select v-model="userDetail.roleid" style="width:300px;" placeholder="请选择角色" :selected="1">
                 <el-option label="管理员" value="0" ></el-option>
                 <el-option label="普通用户" value="1"></el-option>
                 </el-select>
                </el-form-item>
                 <el-form-item label="状&nbsp;&nbsp;&nbsp;&nbsp;态:">
                   <el-select v-model="userDetail.status" style="width:300px;" placeholder="请选择状态" :selected="1">
                    <el-option label="有效" value="1" ></el-option>
                    <el-option label="无效" value="0"></el-option>
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
    id: '',username:'',password:'',roleid:'',status:''
})
const userInfo=ref([]);
onBeforeMount(async () => {
  userDetail.username ='';
  userDetail.password ='';
  userDetail.roleid='0';
  userDetail.status='1';
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