<template>
    <div>
      <!-- <p>角色管理</p> -->
        <el-card>
            <el-input style="width:440px" @clear="searchRole" clearable v-model="searchForm.roleName" placeholder="请输入角色名" class="input-with-select">
                <template #append>
                    <el-button icon="Search" @click="searchRole" />
                </template>
            </el-input>
            &nbsp;&nbsp;&nbsp;
            <el-button type="primary" @click="() => router.push({ path: '/role/save', query: { roleId:''} })">新增角色</el-button>

          <el-table :data="tableData" border style="width: 100%;margin-top:20px">
                <el-table-column prop="roleId" label="ID" width="180" />
                <el-table-column prop="roleName" label="角色" width="180" />
                <el-table-column prop="description" label="角色描述" width="180" />

                <el-table-column label="操作" width="330">
                    <template #default="scope">
                        <el-button type="danger" size="small" @click="deleteRole(scope.row.roleId)">删除</el-button>
                        <el-button size="small"
                            @click="() => router.push({ path: '/role/detail', query: { roleId: scope.row.roleId } })">角色详情</el-button>
                        <el-button size="small"
                          @click="() => router.push({ path: '/role/detail', query: { roleId: scope.row.roleId,edit:1 } })">编辑角色</el-button>
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
import userApi from "../../api/role";
import { onMounted, reactive, ref } from "vue";
import { ElMessage, ElMessageBox } from 'element-plus';
import { useRouter } from 'vue-router'
const router = useRouter();
// Dom 挂载之后
onMounted(() => {
  getRoleList();
})
// 用户数据
let tableData = ref([]);
let total = ref(0);
// 搜索条件
const searchForm = reactive({
    pageNum: 1,
  pageSize: 10,
  roleName: ''
})
// 获取用户列表
const getRoleList = async () => {
    const res = await userApi.getRoleList(searchForm);
    console.log(res);
    tableData.value = res.data.data;
    console.log(res.data.data);
    total.value = res.data.total;
}
const handleSizeChange = (pageSize) => {
    searchForm.pageSize = pageSize;
  getRoleList();
}
const handleCurrentChange = (pageNum) => {
    searchForm.pageNum = pageNum;
  getRoleList();
}
const searchRole = () => {
    searchForm.pageNum = 1;
  getRoleList();
}
// 删除用户
const deleteRole = (roleId) => {
    ElMessageBox.confirm(
        '确定要删除该角色信息吗?',
        {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
        }
    ).then(async () => {
        const res = await userApi.delRole({ roleId: roleId });
        if (res.data) {
            ElMessage.success("删除成功")
          getRoleList();
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