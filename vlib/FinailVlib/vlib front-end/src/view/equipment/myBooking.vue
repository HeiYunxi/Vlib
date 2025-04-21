<template>
  <div>
    <p>虚拟设备选择</p>
    <el-card>
      <el-input style="width:440px" @clear="searchEquipment" clearable v-model="searchForm.equipmentName" placeholder="请输入仿真设备名称" class="input-with-select">
        <template #append>
          <el-button icon="Search" @click="searchEquipment({userId})" />
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
        <el-table-column prop="equipmentId" label="ID" width="50" />
        <el-table-column prop="equipmentName" label="仿真设备名称" width="150" />
        <el-table-column prop="equipmentNumber" label="仿真设备编号" width="150" />
        <el-table-column prop="purpose" label="用途" width="80" />
        <el-table-column label="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;操作" width="250">
          <template #default="scope">
<!--            <el-button size="small" type="info" plain-->
<!--                       @click="reserve">预定</el-button>-->
            <el-button size="small" type="success" plain
                       @click="cancel(scope.row.equipmentId)">取消预定</el-button>
            <el-button size="small" type="warning"
                       @click="() => router.push({ path: '/equipment/detail', query: { equipmentId: scope.row.equipmentId} })">详情</el-button>
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
import userApi from "../../api/equipment.js";
import {onBeforeMount, onMounted, reactive, ref} from "vue";
import {ElMessage, ElMessageBox, ElTable} from 'element-plus';
import {useRoute, useRouter} from 'vue-router'

const router = useRouter();
const route = useRoute();
let userId=ref();

onBeforeMount(async () => {
  if (null != route.query.userId) {
    userId.value = route.query.userId;
    sessionStorage.setItem("userId",route.query.userId);
  }
  if(null==route.query.userId){
    userId.value=sessionStorage.getItem("userId");
  }
  bookingDetail.userId = userId.value;
  searchForm.userId = userId.value
})

// Dom 挂载之后
onMounted(() => {
  getSimulationEquipmentList();
})


// 用户数据
let tableData = ref([]);
let total = ref(0);

const bookingDetail = reactive({
  userId: userId,
  equipmentId: ''
})

// 搜索条件
const searchForm = reactive({
  pageNum: 1,
  pageSize: 10,
  //oldUserId:userId,
  equipmentName: ''
})
// 获取仿真设备列表
const getSimulationEquipmentList = async () => {
  const res = await userApi.getUserEquipment(searchForm);
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
const searchEquipment = (userId) => {
  searchForm.pageNum = 1;
  //searchForm.oldUserId = userId;
  getSimulationEquipmentList();
}


// 取消预定
const cancel = async (equipmentId) => {
  try {
    // 获取用户和设备ID
    // olduserId = userId.value;
    bookingDetail.userId = userId;
    bookingDetail.equipmentId = equipmentId;

    // 发送取消预订请求
    const res = await userApi.cancelBookingEquipment(bookingDetail);

    // 处理响应结果
    if (res.data) {
      ElMessage.success("取消预订成功");
      router.push("/equipment/myBooking");
    } else {
      ElMessage.error("取消预订失败");
    }
  } catch (error) {
    console.error('请求出错:', error);
    ElMessage.error("取消预订过程中发生错误");
  }
};
</script>

<style lang="scss" scoped>

</style>