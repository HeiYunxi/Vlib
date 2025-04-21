<template>
  <div>
    <el-card>
      <el-input style="width:440px" @clear="searchUser" clearable v-model="searchForm.account" placeholder="请输入账号" class="input-with-select">
        <template #append>
          <el-button icon="Search" @click="searchUser" />
        </template>
      </el-input>
      &nbsp;&nbsp;&nbsp;
      <el-button type="primary" @click="() => router.push({ path: '/user/save', query: { userId:''} })">新增用户</el-button>
      <el-table :data="tableData" border style="width: 100%;margin-top:20px">
        <el-table-column prop="userId" label="id" width="180" />
        <el-table-column prop="userName" label="用户名" width="180" />
        <el-table-column prop="account" label="账号" width="180" />
        <el-table-column prop="phone" label="手机号" width="180" />
        <el-table-column label="操作" width="330">
          <template #default="scope">
            <el-button type="danger" size="small" @click="deleteUser(scope.row.userId)">删除</el-button>
            <el-button size="small"
                       @click="() => router.push({ path: '/user/detail', query: { userId: scope.row.userId } })">详情</el-button>
            <el-button size="small"
                       @click="() => router.push({ path: '/user/detail', query: { userId: scope.row.userId,edit:1 } })">编辑</el-button>
            <el-button size="small"
                       @click="handleOpen(scope.row.userId)">角色设置</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!-- 分页 -->
      <el-pagination style="margin-top:20px" :current-page="searchForm.pageNum" :page-size="searchForm.pageSize"
                     :page-sizes="[10, 20, 30, 40]" layout="->,total, sizes, prev, pager, next, jumper" :total="total"
                     @size-change="handleSizeChange" @current-change="handleCurrentChange" />
    </el-card>
    <el-dialog
        title="角色设置"
        v-model="dialogVisible"
        width="700px"
        :before-close="handleClose">
      <div style="text-align:center;">
        <el-transfer v-model="transferValue" :titles = "['未选择','已选择']" :data="data"></el-transfer>
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
import { onMounted, reactive, ref } from "vue";
import { ElMessage, ElMessageBox } from 'element-plus';
import { useRouter } from 'vue-router';
const router = useRouter();
// Dom 挂载之后
onMounted(() => {
  getUserList();
})
let olduserId=ref();
const data = ref([]);
const transferValue = ref([]);//选中的权限集合

const dialogVisible = ref(false);
// 用户数据
let tableData = ref([]);

let userRoleData = reactive({
  roleId: '',
  roleName: ''
})
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
  tableData.value = res.data.data;
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
// 删除用户ID
const deleteUser = (userId) => {
  ElMessageBox.confirm(
      '确定要删除该用户信息吗?',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      }
  ).then(async () => {
    const res = await userApi.delUser({ userId: userId });
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
const handleOpen = async(userId) => {
  //清除缓存
  clearCache();
  olduserId.value=userId;
  dialogVisible.value = true;
  // 获取所有角色数据列表
  const res = await userApi.getRoleMap({ userId: userId });
  userRoleData.value= res.data.data;
  for (let i = 0; i < userRoleData.value.length; i++) {
    data.value.push({
      key: userRoleData.value[i].roleId,
      label: userRoleData.value[i].roleName,
      disabled: false
    });
  }
    // 获取当前用户所选中角色数据列表
     const userres = await userApi.getUserRoleMap({ userId: userId });
     userRoleData.value=userres.data.data;
     for (let i = 0; i < userRoleData.value.length; i++){
      transferValue.value.push(userRoleData.value[i].roleId)//如果回显已选中的数据就将查询出的roleId放到此集合中
     }


};
//点击弹窗右上角叉叉触发触发
const handleClose = (done) => {
  done();
};
//点击弹窗中确定触发
const handleConfirm = async() => {
  if(transferValue.value.length ==0){
    ElMessage.error("用户角色至少选择一项");
    return;
  }
  //保存用户角色数据
 const res = await userApi.saveUserRole({ userId: olduserId.value,rolelist: transferValue.value});
  if (res.data) {
    ElMessage.success("用户角色设置成功");
  } else {
    ElMessage.error("用户角色设置失败");
  }
  //关闭窗口
  dialogVisible.value = false;
  //调用接口处理数据
  console.log(transferValue.value)
  transferValue.value.forEach(function(element) {
    console.log(element);
  });
};
//清除缓存
const clearCache = () => {
  transferValue.value = []
  data.value = []
};

</script>

<style lang="scss" scoped>

</style>