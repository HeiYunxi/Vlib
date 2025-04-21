<template>
    <div>
        <el-card >
            <template #header>
                <div class="card-header">

                    <span>编辑用户信息</span>
                    <el-button size="mini" style="float:right" @click="router.go(-1)">返回</el-button>&nbsp;&nbsp;&nbsp;
                    <el-button type="success" size="mini" style="float:right" @click="() => router.push({ path: '/user/detail', query: { id: scope.row.id,edit:1 } })">保存</el-button>


                </div>
            </template>
            <el-form>
                <p v-if="route.query.edit=='1'">
                <el-form-item label="编号:">
                 {{ route.query.id }}
                </el-form-item>
                </p>
                <p v-else>
                </p>
              <el-form-item label="姓&nbsp;&nbsp;&nbsp;&nbsp;名:">
                <el-input v-model="userDetail.userName" style="width:300px" placeholder="请输入用户姓名" class="input-with-select"></el-input>
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
                <el-select v-model="userDetail.type" style="width:300px;" placeholder="请选择角色类型" :selected="1">
                  <el-option label="学生" value="1" ></el-option>
                  <el-option label="教师" value="2" ></el-option>
                  <el-option label="管理员" value="3"></el-option>
                </el-select>
              </el-form-item>
            </el-form>
        </el-card>
    </div>
</template>

<script setup>
import { onBeforeMount, reactive, ref } from 'vue';
import { useRouter, useRoute } from 'vue-router'
import userApi from "../../api/user";
const route = useRoute();
const router = useRouter();
const userDetail = reactive({
  userId: '',userName:'',account:'',password:'',intro:'',email:'',phone:'',type:''
})
const userInfo=ref([]);
onBeforeMount(async () => {
    if (route.query.id) {
        const res = await userApi.getUserDetail({ userId: route.query.userId })
        console.log(res.data.data);
        //userInfo.value= res.data.data;
        Object.assign(userDetail, res.data.data);
        console.log(userDetail);
    }
  userDetail.username ='';
  userDetail.password ='';
  userDetail.roleid='0';
  userDetail.status='1';
})

</script>

<style lang="scss" scoped>

</style>