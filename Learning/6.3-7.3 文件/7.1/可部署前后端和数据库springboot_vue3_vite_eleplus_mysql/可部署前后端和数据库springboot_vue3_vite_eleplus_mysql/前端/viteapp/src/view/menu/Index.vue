<template>
    <div>
      <p>菜单管理</p>
        <el-card>
            <el-input style="width:440px" @clear="searchMenu" clearable v-model="searchForm.cname" placeholder="请输入菜单名" class="input-with-select">
                <template #append>
                    <el-button icon="Search" @click="searchMenu" />
                </template>
            </el-input>
          &nbsp;&nbsp;&nbsp;
          <el-button type="primary" @click="() => router.push({ path: '/menu/save', query: { menuId:''} })">新增菜单</el-button>
            <el-table :data="tableData" border style="width: 100%;margin-top:20px">
                <el-table-column prop="menuId" label="id" width="50" />
                <el-table-column prop="cname" label="菜单名称" width="180" />
                <el-table-column prop="ename" label="菜单英文名" width="180" />
                <el-table-column prop="path" label="菜单地址" width="180" />
                <el-table-column prop="component" label="菜单路径" width="180" />
                <el-table-column prop="icon" label="图标" width="100" />
                <el-table-column prop="pid" label="父ID" width="70" />
                <el-table-column prop="sort" label="排序" width="70" />
                <el-table-column label="操作" width="330">
                    <template #default="scope">
                        <el-button type="danger" size="small" @click="deleteMenu(scope.row.menuId)" round>删除</el-button>
                        <el-button size="small" type="primary"
                            @click="() => router.push({ path: '/menu/detail', query: { menuId: scope.row.menuId } })">菜单详情</el-button>
                        <el-button size="small" type="success"
                          @click="() => router.push({ path: '/menu/detail', query: { menuId: scope.row.menuId,edit:1 } })">编辑菜单</el-button>
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
  getMenuList();
})
// 用户数据
let tableData = ref([]);
let total = ref(0);
// 搜索条件
const searchForm = reactive({
    pageNum: 1,
    pageSize: 10,
    cname: ''
})
// 获取菜单列表
const getMenuList = async () => {
    const res = await userApi.getMenuList(searchForm);
    console.log(res);
    tableData.value = res.data.data;
    console.log(res.data.data);
    total.value = res.data.total;
}
const handleSizeChange = (pageSize) => {
    searchForm.pageSize = pageSize;
  getMenuList();
}
const handleCurrentChange = (pageNum) => {
    searchForm.pageNum = pageNum;
  getMenuList();
}
const searchMenu = () => {
    searchForm.pageNum = 1;
  getMenuList();
}
// 删除菜单信息
const deleteMenu = (menuId) => {
    ElMessageBox.confirm(
        '确定要删除该菜单信息吗?',
        {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
        }
    ).then(async () => {
        const res = await userApi.delMenu({ menuId: menuId });
        if (res.data) {
            ElMessage.success("删除菜单成功")
            getMenuList();
        } else {
            ElMessage.error("删除菜单失败，请删除角色关联信息")
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