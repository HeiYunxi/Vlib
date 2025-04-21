<template>
    <div>
        <el-card>
            <el-input style="width:440px" @clear="searchUser" clearable v-model="searchForm.account" placeholder="请输入账号" class="input-with-select">
                <template #append>
                    <el-button icon="Search" @click="searchUser" />
                </template>
            </el-input>
            &nbsp;&nbsp;&nbsp;
            <el-button type="primary" @click="() => router.push({ path: '/user/save', query: { id:''} })">新增用户</el-button>
            <el-table :data="tableData" border style="width: 100%;margin-top:20px">
                <el-table-column prop="userID" label="ID" width="180" />
                <el-table-column prop="userName" label="用户名" width="180" />
                <el-table-column prop="account" label="账号" width="180" />
                <el-table-column prop="phone" label="手机号" width="180" />
                <el-table-column label="操作" width="330">
                    <template #default="scope">
                        <el-button type="danger" size="small" @click="deleteUser(scope.row.id)">删除</el-button>
                        <el-button size="small"
                            @click="() => router.push({ path: '/user/detail', query: { id: scope.row.id } })">详情</el-button>
                        <el-button size="small"
                          @click="() => router.push({ path: '/user/detail', query: { id: scope.row.id,edit:1 } })">编辑</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <!-- 分页 -->
            <el-pagination style="margin-top:20px" :current-page="searchForm.pageNum" :page-size="searchForm.pageSize"
                :page-sizes="[10, 20, 30, 40]" layout="->,total, sizes, prev, pager, next, jumper" :total="total"
                @size-change="handleSizeChange" @current-change="handleCurrentChange" />
        </el-card>
    </div>
</template>

<script setup>
import userApi from "../../api/user";
import { onMounted, reactive, ref } from "vue";
import { ElMessage, ElMessageBox } from 'element-plus';
import { useRouter } from 'vue-router'
const router = useRouter();
// Dom 挂载之后
onMounted(() => {
    getUserList();
})
// 用户数据
let tableData = ref([]);
let total = ref(0);
// 搜索条件
const searchForm = reactive({
    pageNum: 1,
  pageSize: 10,
    account: ''
})
// 获取用户列表
const getUserList = async () => {
    const res = await userApi.getUserList(searchForm);
    console.log(res);
    tableData.value = res.data.data;
    console.log(res.data.data);
    total.value = res.data.total;
}
const handleSizeChange = (pageSize) => {
    searchForm.pageSize = pageSize;
    getUserList();
}
const handleCurrentChange = (pageNum) => {
    searchForm.pageNum = pageNum;
    getUserList();
}
const searchUser = () => {
    searchForm.pageNum = 1;
    getUserList();
}
// 删除用户
const deleteUser = (id) => {
    ElMessageBox.confirm(
        '确定要删除该用户信息吗?',
        {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
        }
    ).then(async () => {
        const res = await userApi.delUser({ id: id });
        if (res.data) {
            ElMessage.success("删除成功")
            getUserList();
        } else {
            ElMessage.error("删除失败")
        }
    }).catch(() => {
        ElMessage({
            type: 'info',
            message: '取消删除',
        })
    })
}
</script>

<style lang="scss" scoped>

</style>