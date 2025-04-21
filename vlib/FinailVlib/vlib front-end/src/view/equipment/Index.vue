<template>
  <div>
    <p>虚拟设备管理</p>
    <el-card>
      <el-input style="width:440px" @clear="searchEquipment" clearable v-model="searchForm.equipmentName"
                placeholder="请输入仿真设备名称" class="input-with-select">
        <template #append>
          <el-button icon="Search" @click="searchEquipment(value)"/>
        </template>
      </el-input>


      <el-button type="primary"
                 @click="() => router.push({ path: '/equipment/save', query: { equipmentId:''} })">新增设备
      </el-button>
      <el-button class="btn-seal" @click="exportExcel">导出结果</el-button>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <span>查看可用设备信息</span>
      <el-switch
          v-model="value"
          active-color="#4a90e2"
          inactive-color="#9b9b9b"
          @click="searchEquipment(value)">
      </el-switch>



      <el-table id="Equipment-table" :data="tableData" border style="width: 100%;margin-top:20px">

        <el-table-column class="table" id="col" prop="equipmentId" label="ID" width="50"/>
        <el-table-column class="table" id="col" prop="equipmentName" label="仿真设备名称" width="150"/>
        <el-table-column class="table" id="col" prop="equipmentNumber" label="仿真设备编号" width="150"/>
        <el-table-column class="table" id="col" prop="purpose" label="用途" width="80"/>
        <el-table-column class="table" id="col" prop="software_system" label="软件系统" width="100"/>
        <el-table-column prop="software_version" label="软件版本" width="100"/>
        <el-table-column :formatter="statusformatter" class="table" id="col" prop="status" label="状态" width="100"/>
        <el-table-column class="table" id="col" prop="supplierName" label="供应商" width="180"/>
        <el-table-column :formatter="typeformatter" class="table" id="col" prop="type" label="类型" width="100"/>

        <el-table-column label="操作" width="200">
          <template #default="scope">
            <el-button type="danger" size="small" @click="deleteSimulationEquipment(scope.row.equipmentId)">
              删除
            </el-button>
            <el-button size="small" type="primary"
                       @click="() => router.push({ path: '/equipment/detail', query: { equipmentId: scope.row.equipmentId } })">
              详情
            </el-button>
            <el-button size="small" type="success"
                       @click="() => router.push({ path: '/equipment/detail', query: { equipmentId: scope.row.equipmentId,edit:1 } })">
              编辑
            </el-button>

          </template>
        </el-table-column>
      </el-table>

      <!--不显示，用户导出-->
      <div v-show="false">
      <el-table id="Equipment-table-export" :data="tableData" border style="width: 100%;margin-top:20px" >
        <el-table-column class="table" id="col" prop="equipmentId" label="ID" width="50"/>
        <el-table-column class="table" id="col" prop="equipmentName" label="仿真设备名称" width="150"/>
        <el-table-column class="table" id="col" prop="equipmentNumber" label="仿真设备编号" width="150"/>
        <el-table-column class="table" id="col" prop="purpose" label="用途" width="80"/>
        <el-table-column class="table" id="col" prop="software_system" label="软件系统" width="100"/>
        <el-table-column prop="software_version" label="软件版本" width="100"/>
        <el-table-column class="table" id="col" prop="status" label="状态" width="80"/>
        <el-table-column class="table" id="col" prop="supplierName" label="供应商" width="80"/>
        <el-table-column class="table" id="col" prop="type" label="类型" width="80"/>
      </el-table>
      </div>

      <!-- 分页 -->
      <el-pagination style="margin-top:20px" :current-page="searchForm.pageNum" :page-size="searchForm.pageSize"
                     :page-sizes="[10, 20, 30, 40]" layout="->,total, sizes, prev, pager, next, jumper" :total="total"
                     @size-change="handleSizeChange" @current-change="handleCurrentChange"/>
    </el-card>


  </div>
</template>

<script setup>
import userApi from "../../api/equipment";
import {onMounted, reactive, ref} from "vue";
import {ElMessage, ElMessageBox} from 'element-plus';
import {useRouter} from 'vue-router'
import FileSaver from 'file-saver'
import * as XLSX from 'xlsx'

const value = ref(false)

const router = useRouter();
// Dom 挂载之后
onMounted(() => {
  getSimulationEquipmentList();
})

// 用户数据
let tableData = ref([]);
let total = ref(0);
// 搜索条件
const searchForm = reactive({
  pageNum: 1,
  pageSize: 10,
  flag: false,
  equipmentName: ''
})


// 获取设备列表
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
// 删除用户ID
const deleteSimulationEquipment = (equipmentId) => {
  ElMessageBox.confirm(
      '确定要删除该仿真设备信息吗?',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      }
  ).then(async () => {
    const res = await userApi.delSimulationEquipment({equipmentId: equipmentId});
    if (res.data) {
      ElMessage.success("删除仿真设备成功")
      getSimulationEquipmentList();
    } else {
      ElMessage.error("删除仿真设备失败")
    }
  }).catch(() => {
    ElMessage({
      type: 'info',
      message: '取消删除',
    })
  })
}


// 格式化列
const statusformatter = (row, column, cellValue, index) => {
  if (cellValue === 1) {
    return "可用";
  } else if (cellValue === 2) {
    return "正在维护";
  }
}
const typeformatter = (row, column, cellValue, index) => {
  if (cellValue === 1) {
    return "教学设备";
  } else if (cellValue === 2) {
    return "演示设备";
  }
}








const exportExcel = () => {
  //会默认使用科学计数法表示，则需要这样设置
  var wb = XLSX.utils.table_to_book(
      document.querySelector("#Equipment-table-export"),
      {raw: true}
  );
  // 获取二进制字符串作为输出
  var wbout = XLSX.write(wb, {
    bookType: 'xlsx',
    book: true,
    type: 'array',
  })
  try {
    FileSaver.saveAs(
        // Blob: 对象表示一个不可变 原始数据的类文件对象,不一定是JS原生格式的数据。
        // File: 基于Blob，继承了blob的功能并将其扩展使其支持用户系统上的文件。
        new Blob([wbout], {type: 'appliction/octet-stream'}),
        // 设置导出的文件名称可随意
        '设备列表.xlsx'
    )
  } catch (e) {
    if (typeof console != 'undefined') console.log(e, wbout);
  }
  // 返回一个新创建的Blob对象，其内容由参数中给定的数组串联组成。
  return wbout
}


</script>

<style lang="scss" scoped>

</style>