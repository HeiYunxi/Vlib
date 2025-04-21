<template>
    <div>
      <p>角色管理</p>
        <el-card>
            <el-input style="width:440px" @clear="searchRole" clearable v-model="searchForm.roleName" placeholder="请输入角色" class="input-with-select">
                <template #append>
                    <el-button icon="Search" @click="searchUser" />
                </template>
            </el-input>
            &nbsp;&nbsp;&nbsp;
            <el-button type="primary" @click="() => router.push({ path: '/role/save', query: { userId:''} })">新增角色</el-button>
            <el-table :data="tableData" border style="width: 100%;margin-top:20px">
                <el-table-column prop="roleId" label="id" width="180" />
                <el-table-column prop="roleName" label="角色名称" width="180" />
                <el-table-column prop="description" label="角色描述" width="180" />
                <el-table-column label="操作" width="430">
                    <template #default="scope">
                        <el-button type="danger" size="small" @click="deleteRole(scope.row.roleId)">删除</el-button>
                        <el-button size="small" type="primary"
                            @click="() => router.push({ path: '/role/detail', query: { roleId: scope.row.roleId } })">角色详情</el-button>
                        <el-button size="small" type="success"
                          @click="() => router.push({ path: '/role/detail', query: { roleId: scope.row.roleId,edit:1 } })">编辑角色</el-button>
                        <el-button size="small"
                                 @click="showDialog(scope.row.roleId)">编辑角色菜单</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <!-- 分页 -->
            <el-pagination style="margin-top:20px" :current-page="searchForm.pageNum" :page-size="searchForm.pageSize"
                :page-sizes="[10, 20, 30, 40]" layout="->,total, sizes, prev, pager, next, jumper" :total="total"
                @size-change="handleSizeChange" @current-change="handleCurrentChange" />
        </el-card>
      <el-dialog
          title="选择菜单，可多选"
          v-model="dialogVisible"
          width="700px"
          :before-close="handleClose">
        <div style="text-align:center;">
          <el-tree
              :data="treeData"
              :check-strictly="true"
              show-checkbox
              :default-expand-all="true"
              ref="treeRef"
              node-key="id"
              :default-expanded-keys="defaultExpandedKeys"
              :default-checked-keys="defaultCheckedKeys"
              :props="defaultProps">
          </el-tree>
        </div>
        <div slot="footer" class="dialog-footer" style="text-align:center;padding-top: 20px;">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="handleConfirm">确 定</el-button>
        </div>
      </el-dialog>
    </div>
</template>

<script setup>
import userApi from "../../api/user";
import {onBeforeMount, onMounted, reactive, ref} from "vue";
import { ElMessage, ElMessageBox } from 'element-plus';
import { useRouter } from 'vue-router'
const router = useRouter();
// Dom 挂载之后
onMounted(() => {
    getRoleList();
})
let addOrEditTitle='';
let dialogFormVisible=false;
onBeforeMount(async () => {

  const editForm=reactive({
      roleId: ''
      }
  )
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
// 获取角色列表
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
const searchUser = () => {
    searchForm.pageNum = 1;
    getRoleList();
}
// 删除用户ID
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
            ElMessage.success("删除角色成功")
            getRoleList();
        } else {
            ElMessage.error("删除角色失败")
        }
    }).catch(() => {
        ElMessage({
            type: 'info',
            message: '取消删除',
        })
    })
}

/******************树开始********************/
const dialogVisible = ref(false); //是否打开弹框

const treeData = ref([])
const defaultProps = reactive({
  children: 'children',
  label: 'label'
})

const defaultCheckedKeys = ref([]);//默认选中的数组
const defaultExpandedKeys = ref([]);//默认展开的数组数组


const treeRef = ref(null);//选中的项的id的数组
let oldroleId=ref();

//
const showDialog = async(roleId) => {
  clearCache();
  console.log(roleId)
  //获取所有菜单列表
  const res = await userApi.getMenuAll({});
  oldroleId=roleId;
  if(res.data!=null){
    treeData.value=res.data.data;
    console.log(treeData.value);
  }
  //所有选中的角色对应菜单
  const resrole = await userApi.getRoleMenu({roleId:roleId});
  if(resrole.data!=null){
    defaultCheckedKeys.value=resrole.data.data;

  }
  dialogVisible.value = true;
};

const handleConfirm = async() => {
  if (treeRef.value) {
    const checkedKeys = treeRef.value.getCheckedKeys(); // 调用 Element Plus Tree 组件的 getCheckedKeys 方法

    console.log('选中的键:', checkedKeys);

    const res = await userApi.saveRoleMenu({ roleId: oldroleId,menuList:checkedKeys });
    if (res.data) {
      ElMessage.success("设置角色菜单成功")
    } else {
      ElMessage.error("角色菜单失败")
    }
    dialogVisible.value = false;
  }
  if (treeRef.value) {
    const checkedNodes = treeRef.value.getCheckedNodes(); // 调用 Element Plus Tree 组件的 getCheckedNodes 方法
    console.log('选中的node:', checkedNodes);
  }


};
//清除缓存
const clearCache = () => {
  //treeData.value = []
  defaultCheckedKeys.value = []
  defaultExpandedKeys.value=[]
};
/******************树结束********************/
</script>

<style lang="scss" scoped>

</style>