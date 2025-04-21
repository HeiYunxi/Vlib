<template>
  <div>
    <p>虚拟设备选择</p>
    <el-card>
      <el-input style="width:440px" @clear="searchEquipment" clearable v-model="searchForm.equipmentName"
                placeholder="请输入仿真设备名称" class="input-with-select">
        <template #append>
          <el-button icon="Search" @click="searchEquipment(value)"/>
        </template>
      </el-input>

      <!-- <el-switch
          v-model="value"
          active-color="#13ce66"
          inactive-color="#ff4949"
          @click="searchEquipment(value)">
      </el-switch>-->
      &nbsp;&nbsp;&nbsp;
      <el-table :data="tableData" border style="width: 100%;margin-top:20px">
        <el-table-column prop="equipmentId" label="ID" width="50"/>
        <el-table-column prop="equipmentName" label="仿真设备名称" width="150"/>
        <el-table-column prop="equipmentNumber" label="仿真设备编号" width="150"/>
        <el-table-column prop="purpose" label="用途" width="80"/>
        <el-table-column
            label="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;操作"
            width="250">
          <template #default="scope">
            <el-button size="small" type="info" plain
                       @click="reserve(scope.row.equipmentId)">预定
            </el-button>
            <!--                      <el-button size="small" type="success" plain-->
            <!--                                 @click="cancel">取消预定</el-button>-->
            <el-button size="small" type="warning"
                       @click="() => router.push({ path: '/equipment/detail', query: { equipmentId: scope.row.equipmentId} })">
              详情
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <!-- 分页 -->
      <el-pagination style="margin-top:20px" :current-page="searchForm.pageNum" :page-size="searchForm.pageSize"
                     :page-sizes="[10, 20, 30, 40]" layout="->,total, sizes, prev, pager, next, jumper" :total="total"
                     @size-change="handleSizeChange" @current-change="handleCurrentChange"/>
    </el-card>


  </div>
</template>

<script setup>
import userApi from "../../api/equipment.js";
import {onBeforeMount, onMounted, reactive, ref} from "vue";
import {ElMessage, ElMessageBox, ElTable} from 'element-plus';
import {useRoute, useRouter} from 'vue-router'

let userId = ref();
const router = useRouter();
const route = useRoute();
const value = ref(false)

const bookingInfo = ref([]);

// 挂载 DOM 之前
onBeforeMount(async () => {
  if (null != route.query.userId) {
    userId.value = route.query.userId;
    sessionStorage.setItem("userId", route.query.userId);
  }
  if (null == route.query.userId) {
    userId.value = sessionStorage.getItem("userId");
  }
  // if (route.query.equipmentId) {
    bookingDetail.userId = userId.value;
    // bookingDetail.equipmentId = route.query.equipmentId;
  // }
})

// Dom 挂载之后
onMounted(() => {
  getSimulationEquipmentList();
})

// 用户数据
let tableData = ref([]);
let total = ref(0);

const bookingDetail = reactive({
  userId: '',
  equipmentId: ''
})

// 搜索条件
const searchForm = reactive({
  pageNum: 1,
  pageSize: 10,
  flag: true,
  equipmentName: ''
})

// 获取仿真设备列表
const getSimulationEquipmentList = async () => {
  const res = await userApi.getSimulationEquipmentList(searchForm);
  console.log(res);
  tableData.value = res.data.data;
  console.log(res.data.data);
  total.value = res.data.total;
}
const handleSizeChange = (pageSize) => {
  searchForm.pageSize = pageSize;
  getSimulationEquipmentList();
}
const handleCurrentChange = (pageNum) => {
  searchForm.pageNum = pageNum;
  getSimulationEquipmentList();
}
const searchEquipment = (value) => {
  searchForm.pageNum = 1;
  searchForm.flag = value;
  getSimulationEquipmentList();
}


//点击预定
const reserve = async (equipmentId) => {

  // 获取用户和设备ID
  // const userId = olduserId.value;
  // const equipmentId = oldequipmentId.value;

  // 查询设备下的用户列表
  // const userList = await userApi.getSimulationEquipmentUserList({ equipmentId });
  // if (userList && userList.length > 0) {
  //   ElMessage.error("该设备已经被预定");
  //   return;
  // }
  bookingDetail.equipmentId = equipmentId;

  //保存用户预定数据
  const res = await userApi.bookingEquipment(bookingDetail);
  // 处理响应结果
  if (res.data != null) {
    if (res.data) {
      if (res.data.data == 0) {
        ElMessage.error("该账号已注册");
      } else {
        ElMessage.success("预定成功");
        router.push("/equipment/booking");
      }
    } else {
      ElMessage.error("预定失败");
    }
  } else {
    ElMessage.error("服务器内部错误");
  }
};

</script>

<style lang="scss" scoped>

</style>