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
                <el-form-item label="用户名:">
                <el-input v-model="userDetail.username" style="width:300px" placeholder="请输入用户名" class="input-with-select"></el-input>

                </el-form-item>
                <el-form-item label="密&nbsp;&nbsp;&nbsp;&nbsp;码:">
                 <el-input v-model="userDetail.password" style="width:300px" placeholder="请输入用户名"></el-input>
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
    id: '',username:'',password:'',roleid:'',status:''
})
const userInfo=ref([]);
onBeforeMount(async () => {
    if (route.query.id) {
        const res = await userApi.getUserDetail({ id: route.query.id })
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