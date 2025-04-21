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
                  <el-input v-model="userDetail.id" style="width:300px" :readonly="true"  class="input-with-select"></el-input>
                </el-form-item>
                <el-form-item label="用户名:">
                <el-input v-model="userDetail.username" style="width:300px" placeholder="请输入用户名" class="input-with-select"></el-input>
                </el-form-item>
                <el-form-item label="密&nbsp;&nbsp;&nbsp;&nbsp;码:">
                    <el-input type="password" style="width:300px;" v-model="userDetail.password"/>
                </el-form-item>
                <el-form-item label="角&nbsp;&nbsp;&nbsp;&nbsp;色:">
                  <el-select v-model="userDetail.roleid" style="width:300px;"   placeholder="请选择状态" >
                    <el-option label="管理员" value="1" ></el-option>
                    <el-option label="普通用户" value="0"></el-option>
                  </el-select>

                </el-form-item>
                 <el-form-item label="状&nbsp;&nbsp;&nbsp;&nbsp;态:">
                   <el-select v-model="userDetail.status" style="width:300px;"   placeholder="请选择状态" >
                     <el-option label="有效" value="1" ></el-option>
                     <el-option label="无效" value="0"></el-option>
                   </el-select>
                 </el-form-item>
              <p v-if="route.query.id=='' || route.query.edit=='1'">
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
    id: '',username:'',password:'',roleid:'',status:''
})
const userInfo=ref([]);
onBeforeMount(async () => {
    if (route.query.id) {
        const res = await userApi.getUserDetail({ id: route.query.id })
        console.log(res.data.data);
        userInfo.value= res.data.data;
        userDetail.username = res.data.data[0].username;
        userDetail.password = res.data.data[0].password;
        userDetail.roleid = res.data.data[0].roleid;
        userDetail.status = res.data.data[0].status;
        userDetail.id=res.data.data[0].id;

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
      const res = await userApi.updateUser(userDetail);

      if (res.data!=null) {
        if (res.data) {
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
      alert(1);
      return false;
    }
  });
};
</script>

<style lang="scss" scoped>

</style>